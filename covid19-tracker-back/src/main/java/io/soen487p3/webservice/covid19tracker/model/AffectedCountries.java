package io.soen487p3.webservice.covid19tracker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AffectedCountries {
    @JsonProperty(value = "affected_countries")
    List<String> affectedCountries;

    public List<String> getAffectedCountries() {
        return affectedCountries;
    }

    public void setAffectedCountries(List<String> affectedCountries) {
        this.affectedCountries = affectedCountries;
    }
}
