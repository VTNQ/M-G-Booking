package com.mgbooking.server.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;


@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(max = 100) String getUsername() {
        return username;
    }

    public void setUsername(@Size(max = 100) String username) {
        this.username = username;
    }

    public @Size(max = 100) String getFullName() {
        return fullName;
    }

    public void setFullName(@Size(max = 100) String fullName) {
        this.fullName = fullName;
    }

    public @Size(max = 100) String getEmail() {
        return email;
    }

    public void setEmail(@Size(max = 100) String email) {
        this.email = email;
    }

    public @Size(max = 20) String getPhone() {
        return phone;
    }

    public void setPhone(@Size(max = 20) String phone) {
        this.phone = phone;
    }

    public @Size(max = 200) String getAddress() {
        return address;
    }

    public void setAddress(@Size(max = 200) String address) {
        this.address = address;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public SecurityCode getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(SecurityCode securityCode) {
        this.securityCode = securityCode;
    }

    public @Size(max = 20) String getOtp() {
        return otp;
    }

    public void setOtp(@Size(max = 20) String otp) {
        this.otp = otp;
    }

    public @Size(max = 200) String getAvatar() {
        return avatar;
    }

    public void setAvatar(@Size(max = 200) String avatar) {
        this.avatar = avatar;
    }

    public @Size(max = 200) String getPassword() {
        return password;
    }

    public void setPassword(@Size(max = 200) String password) {
        this.password = password;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public @Size(max = 100) String getAccountType() {
        return accountType;
    }

    public void setAccountType(@Size(max = 100) String accountType) {
        this.accountType = accountType;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Instant getCreatedOTP() {
        return createdOTP;
    }

    public void setCreatedOTP(Instant createdOTP) {
        this.createdOTP = createdOTP;
    }

    @Size(max = 100)
    
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Size(max = 100)
    
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Size(max = 100)
    
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Size(max = 20)
    
    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Size(max = 200)
    
    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "city_id")
    private Integer cityId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_code_id")
    private SecurityCode securityCode;

    @Size(max = 20)
    @Column(name = "OTP", length = 20)
    private String otp;

    @Size(max = 200)
    @Column(name = "avatar", length = 200)
    private String avatar;

    @Size(max = 200)
    @Column(name = "password", length = 200)
    private String password;

    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("1")
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @Size(max = 100)
    
    @ColumnDefault("'ROLE_USER'")
    @Column(name = "account_type", nullable = false, length = 100)
    private String accountType;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "CreatedOTP")
    private Instant createdOTP;

}