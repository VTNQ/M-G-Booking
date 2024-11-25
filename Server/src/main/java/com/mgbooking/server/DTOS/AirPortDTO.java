package com.mgbooking.server.DTOS;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class AirPortDTO {
    private Integer id;
    @NotNull(message = "Name is required")
    private String name;
    @Min(value = 1,message = "City is required")
    @JsonProperty("city_id")
    private int city_id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull(message = "Name is Required") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name is Required") String name) {
        this.name = name;
    }

    public @Min(value = 1,message = "City is required") int getCity_id() {
        return city_id;
    }

    public void setCity_id(@Min(value = 1,message = "City is required") int city_id) {
        this.city_id = city_id;
    }
}
