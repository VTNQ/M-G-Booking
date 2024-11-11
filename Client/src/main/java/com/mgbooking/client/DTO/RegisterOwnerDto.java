package com.mgbooking.client.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterOwnerDto {
    @JsonProperty("fullname")
    private String fullname;

    @JsonProperty("Email")
    private String Email;

    @JsonProperty("Password")
    private String Password;

    @JsonProperty("ConfirmPassword")
    private String ConfirmPassword;

    // Getters and setters
    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullName) {
        this.fullname = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getConfirmPassword() {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.ConfirmPassword = confirmPassword;
    }
}
