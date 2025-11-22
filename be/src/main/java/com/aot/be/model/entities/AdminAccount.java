package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_account", schema = "mg_booking")
public class AdminAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Size(max = 200)
    @NotNull
    @Column(name = "password", nullable = false, length = 200)
    private String password;

    @Size(max = 50)
    @NotNull
    @Column(name = "account_type", nullable = false, length = 50)
    private String accountType;

}