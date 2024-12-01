package com.mgbooking.client.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class Airline {
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    private Integer id;


    private String name;


    @JsonIgnore
    private Country country;

}