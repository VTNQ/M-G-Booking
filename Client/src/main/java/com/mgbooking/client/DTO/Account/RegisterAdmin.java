package com.mgbooking.client.DTO.Account;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterAdmin {
    private Integer id;
    @JsonProperty("fullName")
    private String fullName;
    private String email;
    private String phone;
    private String password;
    @JsonProperty("Address")
    private String Address;
    private int id_Country;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId_Country() {
        return id_Country;
    }

    public void setId_Country(int id_Country) {
        this.id_Country = id_Country;
    }

    public int getSecurity_code() {
        return Security_code;
    }

    public void setSecurity_code(int security_code) {
        Security_code = security_code;
    }

    private int Security_code;

}
