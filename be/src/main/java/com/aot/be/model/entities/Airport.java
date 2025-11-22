package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "airport", schema = "mg_booking")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "airport_code", nullable = false, length = 100)
    private String airportCode;

    @Size(max = 100)
    @NotNull
    @Column(name = "airport_name", nullable = false, length = 100)
    private String airportName;

    @NotNull
    @Column(name = "city_id", nullable = false)
    private Integer cityId;

    @Size(max = 100)
    @NotNull
    @Column(name = "contact_info", nullable = false, length = 100)
    private String contactInfo;

}