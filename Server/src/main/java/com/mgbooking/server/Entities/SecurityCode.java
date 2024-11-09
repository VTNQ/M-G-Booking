package com.mgbooking.server.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "security_code")
public class SecurityCode {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "`value_ code`", nullable = false, length = 50)
    private String valueCode;

    @Column(name = "start_at", nullable = false)
    private LocalDate startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDate endAt;

    @Column(name = "location", nullable = false, length = 100)
    private String location;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "value", nullable = false, length = 100)
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}