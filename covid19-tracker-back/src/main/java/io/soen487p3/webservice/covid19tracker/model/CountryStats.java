package io.soen487p3.webservice.covid19tracker.model;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CountryStats {
//    @JsonProperty(value = "id")
    @JsonIgnoreProperties
    private int countryId;

//    @JsonProperty(value = "country")
    @JsonIgnoreProperties
    private String country;

    @JsonProperty(value = "country_name")
    private String countryName;

    @JsonProperty(value = "total_cases")
    private int totalCases;

    @JsonProperty(value = "new_cases")
    private int newCases;

    @JsonProperty(value = "active_cases")
    private int activeCases;

    @JsonProperty(value = "total_deaths")
    private int totalDeaths;

    @JsonProperty(value = "new_deaths")
    private int newDeaths;

    @JsonProperty(value = "total_recovered")
    private int totalRecovered;

    @JsonProperty(value = "serious_critical")
    private int seriousCritical;

    @JsonProperty(value = "total_cases_per1m")
    private int totalCasesPer1M;

    @JsonProperty(value = "record_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date recordDate;


   @Override
   public String toString() {
      return JSON.toJSONString(this);
   }

    public CountryStats() {
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public void setActiveCases(int activeCases) {
        this.activeCases = activeCases;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public void setSeriousCritical(int seriousCritical) {
        this.seriousCritical = seriousCritical;
    }

    public void setTotalCasesPer1M(int totalCasesPer1M) {
        this.totalCasesPer1M = totalCasesPer1M;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getTotalCases() {
        return totalCases;
    }

    public int getNewCases() {
        return newCases;
    }

    public int getActiveCases() {
        return activeCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public int getSeriousCritical() {
        return seriousCritical;
    }

    public int getTotalCasesPer1M() {
        return totalCasesPer1M;
    }

    public Date getRecordDate() {
        return recordDate;
    }
}
