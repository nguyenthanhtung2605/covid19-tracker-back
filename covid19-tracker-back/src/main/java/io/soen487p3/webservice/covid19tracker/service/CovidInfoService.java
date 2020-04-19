package io.soen487p3.webservice.covid19tracker.service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import io.soen487p3.webservice.covid19tracker.model.AffectedCountries;
import io.soen487p3.webservice.covid19tracker.model.CountriesStats;
import io.soen487p3.webservice.covid19tracker.model.CountryStats;
import io.soen487p3.webservice.covid19tracker.model.WorldStats;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class CovidInfoService {
    private String getJsonResponse(String url, String apiName) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", apiName+".p.rapidapi.com")
                .addHeader("x-rapidapi-key", "4ea5a4a71dmsh0e2653f69e3659cp1fda4djsn1b15463c9f78")
                .addHeader("Accept", "application/json")
                .build();

        Response response;
        response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            String jsonData = response.body().string();

            return jsonData;
        }
        return null;

    }

    public WorldStats fetchWorldStats() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php")
                .get()
                .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "4ea5a4a71dmsh0e2653f69e3659cp1fda4djsn1b15463c9f78")
                .addHeader("Accept", "application/json")
                .build();

        Response response;
        response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            String jsonData = response.body().string();

            JSONObject obj = new JSONObject(jsonData);

            NumberFormat formatInt = NumberFormat.getInstance(Locale.US);
            Number numberTotalCases = 0;
            Number numberTotalDeaths = 0;
            Number numberTotalRecovered = 0;
            Number numberNewCases = 0;
            Number numberNewDeaths = 0;

            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date dateStatisticTakenAt = null;
            try {
                numberTotalCases = formatInt.parse(obj.getString("total_cases"));
                numberTotalDeaths = formatInt.parse(obj.getString("total_deaths"));
                numberTotalRecovered = formatInt.parse(obj.getString("total_recovered"));
                numberNewCases = formatInt.parse(obj.getString("new_cases"));
                numberNewDeaths = formatInt.parse(obj.getString("new_deaths"));
                dateStatisticTakenAt = formatDate.parse(obj.getString("statistic_taken_at"));

            } catch (ParseException e) {
                e.printStackTrace();
            }

            WorldStats ws = new WorldStats();
            ws.setTotalCases(numberTotalCases.intValue());
            ws.setTotalDeaths(numberTotalDeaths.intValue());
            ws.setTotalRecovered(numberTotalRecovered.intValue());
            ws.setNewCases(numberNewCases.intValue());
            ws.setNewDeaths(numberNewDeaths.intValue());
            ws.setStatisticTakenAt(dateStatisticTakenAt);

            return ws;

        }else {
            return null;
        }
    }

    public CountryStats fetchCountryStats(String country) throws IOException, JSONException {

        String url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/latest_stat_by_country.php?country="+country;
        String apiName = "coronavirus-monitor";
        String jsonResponse = getJsonResponse(url, apiName);
        JSONObject obj = new JSONObject(jsonResponse);

        String textCountry = "";
        int intCountryID = 0;
        String textCountryName = "";

        NumberFormat formatInt = NumberFormat.getInstance(Locale.US);
        Number numberTotalCases = 0;
        Number numberNewCases = 0;
        Number numberActiveCases = 0;
        Number numberTotalDeaths = 0;
        Number numberNewDeaths = 0;
        Number numberTotalRecovered = 0;
        Number numberSeriousCritical = 0;
        Number numberTotalCasesPer1M = 0;

        SimpleDateFormat formatDate = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss.SSS");
        Date dateRecordDate = null;
        try {
            textCountry = obj.getString("country");
            JSONArray arr = obj.getJSONArray("latest_stat_by_country");
            intCountryID = Integer.parseInt(arr.getJSONObject(0).getString("id"));
            textCountryName = arr.getJSONObject(0).getString("country_name");
            numberTotalCases = formatInt.parse(arr.getJSONObject(0).getString("total_cases"));
            numberNewCases = formatInt.parse((arr.getJSONObject(0).getString("new_cases").equals("") ? "0" :
                    arr.getJSONObject(0).getString("new_cases")));
            numberActiveCases = formatInt.parse(arr.getJSONObject(0).getString("active_cases").equals("") ? "0" :
                    arr.getJSONObject(0).getString("active_cases"));
            numberTotalDeaths = formatInt.parse(arr.getJSONObject(0).getString("total_deaths").equals("") ? "0" :
                    arr.getJSONObject(0).getString("total_deaths"));
            numberTotalRecovered = formatInt.parse(arr.getJSONObject(0).getString("total_recovered").equals("") ?
                    "0" :arr.getJSONObject(0).getString("total_recovered"));
            numberSeriousCritical = formatInt.parse(arr.getJSONObject(0).getString("serious_critical").equals("") ?
                    "0" : (arr.getJSONObject(0).getString("serious_critical")));
            numberNewDeaths = formatInt.parse((arr.getJSONObject(0).getString("new_deaths").equals("") ? "0" :
                    arr.getJSONObject(0).getString("new_deaths")));
            numberTotalCasesPer1M = formatInt.parse(arr.getJSONObject(0).getString("total_cases_per1m"));
            dateRecordDate = formatDate.parse(arr.getJSONObject(0).getString("record_date"));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        CountryStats cs = new CountryStats();
        cs.setCountry(textCountry);
        cs.setCountryId(intCountryID);
        cs.setCountryName(textCountryName);
        cs.setTotalCases(numberTotalCases.intValue());
        cs.setNewCases(numberNewCases.intValue());
        cs.setActiveCases(numberActiveCases.intValue());
        cs.setTotalDeaths(numberTotalDeaths.intValue());
        cs.setTotalRecovered(numberTotalRecovered.intValue());
        cs.setNewDeaths(numberNewDeaths.intValue());
        cs.setSeriousCritical(numberSeriousCritical.intValue());
        cs.setSeriousCritical(numberTotalCasesPer1M.intValue());
        cs.setRecordDate(dateRecordDate);

        return cs;
    }

    public AffectedCountries fetchAffectedCountries() throws IOException, JSONException {

        String url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/affected.php";
        String apiName = "coronavirus-monitor";
        String jsonResponse = getJsonResponse(url, apiName);

        JSONObject obj = new JSONObject(jsonResponse);

        List<String> listAffectedCountry = new ArrayList<>();
        JSONArray arr = obj.getJSONArray("affected_countries");
        for (int i = 0; i < arr.length(); i++) {
            String affectedCountry = arr.getString(i);
            if (!affectedCountry.equals("")){
                listAffectedCountry.add(affectedCountry);
            }

        }
        AffectedCountries acs = new AffectedCountries();
        acs.setAffectedCountries(listAffectedCountry);
        return acs;
    }

    public CountriesStats fetchCountriesStats() throws IOException, JSONException {

        String url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php";
        String apiName = "coronavirus-monitor";
        String jsonResponse = getJsonResponse(url, apiName);

        JSONObject obj = new JSONObject(jsonResponse);

        JSONArray arr = obj.getJSONArray("countries_stat");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateStatisticTakenAt = null;

        try {
            dateStatisticTakenAt = formatDate.parse(obj.getString("statistic_taken_at"));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        CountriesStats css = new CountriesStats();
        css.setStatisticTakenAt(dateStatisticTakenAt);

        List<CountryStats> listCountriesStats = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++) {
            String countryName = arr.getJSONObject(i).getString("country_name");
            if (!countryName.equals("")) {
                CountryStats cs = new CountryStats();
                cs.setCountryName(countryName);

                NumberFormat formatInt = NumberFormat.getInstance(Locale.US);
                Number numberTotalCases = 0;
                Number numberActiveCases = 0;
                Number numberNewCases = 0;
                Number numberTotalDeaths = 0;
                Number numberNewDeaths = 0;
                Number numberTotalRecovered = 0;
                Number numberSeriousCritical = 0;
                Number numberTotalCasesPer1M = 0;

                try {
                    numberTotalCases = formatInt.parse(arr.getJSONObject(i).getString("cases"));
                    numberNewCases = formatInt.parse(arr.getJSONObject(i).getString("new_cases"));
                    numberActiveCases = formatInt.parse(arr.getJSONObject(i).getString("active_cases"));
                    numberTotalDeaths = formatInt.parse(arr.getJSONObject(i).getString("deaths"));
                    numberTotalRecovered = formatInt.parse(arr.getJSONObject(i).getString("total_recovered"));
                    numberSeriousCritical = formatInt.parse(arr.getJSONObject(i).getString("serious_critical"));
                    numberNewDeaths = formatInt.parse(arr.getJSONObject(i).getString("new_deaths"));
                    numberTotalCasesPer1M = formatInt.parse(arr.getJSONObject(i).getString("total_cases_per_1m_population"));

                } catch (ParseException e) {
//                    e.printStackTrace();
                }

                cs.setTotalCases(numberTotalCases.intValue());
                cs.setNewCases(numberNewCases.intValue());
                cs.setActiveCases(numberActiveCases.intValue());
                cs.setTotalDeaths(numberTotalDeaths.intValue());
                cs.setTotalRecovered(numberTotalRecovered.intValue());
                cs.setNewDeaths(numberNewDeaths.intValue());
                cs.setSeriousCritical(numberSeriousCritical.intValue());
                cs.setSeriousCritical(numberTotalCasesPer1M.intValue());
                listCountriesStats.add(cs);
            }
        }
        css.setCountriesStats(listCountriesStats);
        return css;
    }

    public String fetchCanadaStats() throws IOException {
        String url = "https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats?country=Canada";
        String apiName = "covid-19-coronavirus-statistics";
        String jsonResponse = getJsonResponse(url, apiName);
        return jsonResponse;
    }

    public CountryStats fetchCanadaStatsSmall() throws IOException, JSONException {
        CountryStats CanadaStatsSmall = fetchCountryStats("canada");
        return CanadaStatsSmall;
    }

    public String fetchHistoryByCountry(String country) throws IOException {
        String url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_particular_country.php?country="+country;
        String apiName = "coronavirus-monitor";
        String jsonResponse = getJsonResponse(url, apiName);
        return jsonResponse;
    }

    public String fetchHistoryByCountryByDate(String country, String date) throws IOException {
        String url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/history_by_particular_country_by_date.php?country="+country+"&date="+date;;
        String apiName = "coronavirus-monitor";
        String jsonResponse = getJsonResponse(url, apiName);
        return jsonResponse;
    }

}
