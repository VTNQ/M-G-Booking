package com.mgbooking.client.DTO;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;


public class SecurityCode {

    @SerializedName("id")
    private Integer id;
    @SerializedName("valueCode")

    private String valueCode;
    @SerializedName("startAt")

    private LocalDate startAt;
    @SerializedName("end")

    private LocalDate endAt;
    @SerializedName("location")

    private String location;

    @SerializedName("dob")
    private LocalDate dob;
    @SerializedName("frontSecurityCode")

    private String frontSecurityCode;

    @SerializedName("backSecurityCode")
    private String backSecurityCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValueCode() {
        return valueCode;
    }

    public void setValueCode(String valueCode) {
        this.valueCode = valueCode;
    }

    public LocalDate getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDate startAt) {
        this.startAt = startAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDate endAt) {
        this.endAt = endAt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getFrontSecurityCode() {
        return frontSecurityCode;
    }

    public void setFrontSecurityCode(String frontSecurityCode) {
        this.frontSecurityCode = frontSecurityCode;
    }

    public String getBackSecurityCode() {
        return backSecurityCode;
    }

    public void setBackSecurityCode(String backSecurityCode) {
        this.backSecurityCode = backSecurityCode;
    }

}