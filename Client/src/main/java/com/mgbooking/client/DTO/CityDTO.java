package com.mgbooking.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CityDTO {
    private Integer id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country_id")
    private int country_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
