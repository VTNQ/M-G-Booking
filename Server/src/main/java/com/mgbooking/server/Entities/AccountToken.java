package com.mgbooking.server.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Entity
@Table(name = "AccountTokens")
public class AccountToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Account getAccount() {
        return account;
    }

    public void setAccount(@NotNull Account account) {
        this.account = account;
    }

    public @Size(max = 255) @NotNull String getToken() {
        return token;
    }

    public void setToken(@Size(max = 255) @NotNull String token) {
        this.token = token;
    }

    public @Size(max = 50) @NotNull String getTokenType() {
        return tokenType;
    }

    public void setTokenType(@Size(max = 50) @NotNull String tokenType) {
        this.tokenType = tokenType;
    }

    public @NotNull Instant getExpiration() {
        return expiration;
    }

    public void setExpiration(@NotNull Instant expiration) {
        this.expiration = expiration;
    }

    public @NotNull Instant getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(@NotNull Instant issuedAt) {
        this.issuedAt = issuedAt;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AccountId", nullable = false)
    private Account account;

    @Size(max = 255)
    @NotNull
    @Column(name = "Token", nullable = false)
    private String token;

    @Size(max = 50)
    @NotNull
    @Column(name = "TokenType", nullable = false, length = 50)
    private String tokenType;

    @NotNull
    @Column(name = "Expiration", nullable = false)
    private Instant expiration;

    @NotNull
    @Column(name = "IssuedAt", nullable = false)
    private Instant issuedAt;

}