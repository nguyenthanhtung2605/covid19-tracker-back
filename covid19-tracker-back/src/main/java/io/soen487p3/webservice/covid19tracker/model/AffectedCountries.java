package io.soen487p3.webservice.covid19tracker.model;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AffectedCountries {
    @JsonProperty(value = "affected_countries")
    List<String> affectedCountries;
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public List<String> getAffectedCountries() {
        return affectedCountries;
    }

    public void setAffectedCountries(List<String> affectedCountries) {
        this.affectedCountries = affectedCountries;
    }
}
