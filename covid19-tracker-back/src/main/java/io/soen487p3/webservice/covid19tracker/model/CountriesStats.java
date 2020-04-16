package io.soen487p3.webservice.covid19tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class CountriesStats {
    @JsonProperty(value = "countries_stat")
    List<CountryStats> countriesStats;

    @JsonProperty(value = "statistic_taken_at")
    private Date statisticTakenAt;

    public List<CountryStats> getCountriesStats() {
        return countriesStats;
    }

    public void setCountriesStats(List<CountryStats> countriesStats) {
        this.countriesStats = countriesStats;
    }

    public Date getStatisticTakenAt() {
        return statisticTakenAt;
    }

    public void setStatisticTakenAt(Date statisticTakenAt) {
        this.statisticTakenAt = statisticTakenAt;
    }
}
