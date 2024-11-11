package com.mgbooking.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUser {
    private Integer id;
    @JsonProperty("fullName")
    private String fullName;
    private String email;
    private String phone;
    private String password;
    @JsonProperty("Address")
    private String Address;
    private Integer id_City;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getFull_name() {
        return fullName;
    }

    public void setFull_name(String full_name) {
        this.fullName = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getId_City() {
        return id_City;
    }

    public void setId_City(Integer id_City) {
        this.id_City = id_City;
    }


}