package io.soen487p3.webservice.covid19tracker.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import io.soen487p3.webservice.covid19tracker.model.CountriesStats;
import io.soen487p3.webservice.covid19tracker.model.CountryStats;

public class Utils {
   public static int[] getCanadaDetail(String canadaStats){
      List canadaDetailInfo = (JSON.parseObject(canadaStats)
              .getJSONObject("data"))
              .getJSONArray("covid19Stats");
      List<Integer> data = new ArrayList<>();
      for(Object o : canadaDetailInfo){
         if(((Map) JSON.parse(o.toString())).get("province") != "Recovered"){
            data.add((Integer) ((Map) JSON.parse(o.toString())).get("confirmed"));
         }
      }
      int[] myData = new int[data.size()];
      for(int i = 0; i < data.size(); ++i){
         myData[i] = data.get(i);
      }
   return myData;
   }

   public static Map<String, Integer> getGlobalBrief(CountriesStats countriesStats){
      Map<String, Integer> list = new HashMap<>();
      for(CountryStats countryStats : countriesStats.getCountriesStats()){
         if(countryStats.getCountryName().equals("USA"))
            list.put("United States", countryStats.getTotalCases());
         else list.put(countryStats.getCountryName(), countryStats.getTotalCases());
      }
      return list;
   }
}
