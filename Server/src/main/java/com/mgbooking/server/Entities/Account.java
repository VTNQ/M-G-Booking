package com.mgbooking.server.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "city_id", nullable = false)
    private Integer cityId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "security_code_id")
    private SecurityCode securityCode;

    @Column(name = "OTP", length = 20)
    private String otp;

    @Column(name = "avatar", length = 200)
    private String avatar;

    @Column(name = "password", length = 200)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @ColumnDefault("1")
    @JoinColumn(name = "level_id", nullable = false)
    private Level level;

    @ColumnDefault("'ROLE_USER'")
    @Column(name = "account_type", nullable = false, length = 100)
    private String accountType;

    @Column(name = "country_id")
    private Integer countryId;

}