package io.soen487p3.webservice.covid19tracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class WorldStats {
    @JsonProperty(value = "total_cases")
    private int totalCases;

    @JsonProperty(value = "total_deaths")
    private int totalDeaths;

    @JsonProperty(value = "total_recovered")
    private int totalRecovered;

    @JsonProperty(value = "new_cases")
    private int newCases;

    @JsonProperty(value = "new_deaths")
    private int newDeaths;

    @JsonProperty(value = "statistic_taken_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date statisticTakenAt;

    public WorldStats() {
    }

    public int getTotalCases() {
        return totalCases;
    }

    public void setTotalCases(int totalCases) {
        this.totalCases = totalCases;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public int getNewCases() {
        return newCases;
    }

    public void setNewCases(int newCases) {
        this.newCases = newCases;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public Date getStatisticTakenAt() {
        return statisticTakenAt;
    }

    public void setStatisticTakenAt(Date statisticTakenAt) {
        this.statisticTakenAt = statisticTakenAt;
    }
}
