package io.soen487p3.webservice.covid19tracker.controller;

import io.soen487p3.webservice.covid19tracker.model.AffectedCountries;
import io.soen487p3.webservice.covid19tracker.model.CountriesStats;
import io.soen487p3.webservice.covid19tracker.model.CountryStats;
import io.soen487p3.webservice.covid19tracker.model.WorldStats;
import io.soen487p3.webservice.covid19tracker.service.CovidInfoService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("user/api/v1/coronavirus")
public class CovidInfoController {
    @Autowired
    private CovidInfoService covidInfoService;

    @RequestMapping(method = RequestMethod.GET, value = "/worldstats")
    public WorldStats getWorldStats() throws IOException, JSONException {
        return covidInfoService.fetchWorldStats();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/latest_stat_by_country/{country}")
    public CountryStats getWorldStats(@PathVariable String country) throws IOException, JSONException {
        return covidInfoService.fetchCountryStats(country);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cases_by_country")
    public CountriesStats getCountriesStats() throws IOException, JSONException {
        return covidInfoService.fetchCountriesStats();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/affected_countries")
    public AffectedCountries getAffectedCountries() throws IOException, JSONException {
        return covidInfoService.fetchAffectedCountries();
    }

    @RequestMapping(value = "/canada",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCanadaStats() throws IOException, JSONException {
        return covidInfoService.fetchCanadaStats();
    }

    @RequestMapping(value = "/canada_stat_small",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public CountryStats getCanadaStatsSmall() throws IOException, JSONException {
        return covidInfoService.fetchCanadaStatsSmall();
    }

    @RequestMapping(value = "/history_by_particular_country/{country}",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHistoryByCountry(@PathVariable String country) throws IOException, JSONException {
        return covidInfoService.fetchHistoryByCountry(country);
    }

    @RequestMapping(value = "/history_by_particular_country_by_date/{country}/{date}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getHistoryByCountryByDate(@PathVariable String country, @PathVariable String date) throws IOException, JSONException {
        return covidInfoService.fetchHistoryByCountryByDate(country, date);
    }

}
