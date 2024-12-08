package com.mgbooking.server.DTOS.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountAdmin {
    private Integer id;
    @JsonProperty("fullName")
    private String fullName;

    public AccountAdmin(Integer id, String fullName, String email, String phone, String address, String nameCountry) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;

        Address = address;
        this.nameCountry = nameCountry;
    }

    private String email;
    private String phone;
    private String password;
    @JsonProperty("Address")
    private String Address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    private String nameCountry;


}
