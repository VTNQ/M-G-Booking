package com.mgbooking.server.DTOS;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mgbooking.server.Entities.City;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ResultFlightDTO {
    private String imageUrl;
    private int id;
    private String nameCity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Instant arrivalTime;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Instant getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Instant arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Instant getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Instant departureTime) {
        this.departureTime = departureTime;
    }

    private Instant departureTime;
    public ResultFlightDTO(int id,String imageUrl,String nameCity, Instant arrivalTime, Instant departureTime,BigDecimal price) {
        this.imageUrl = imageUrl;
        this.nameCity=nameCity;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.price = price;
        this.id=id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getImageUrl() {
        return imageUrl;
    }



    public void setImageUrl(String imageUrl) {}

}
