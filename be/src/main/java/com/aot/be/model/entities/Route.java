package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "route", schema = "mg_booking")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "departure_airport_id", nullable = false)
    private Integer departureAirport;

    @NotNull
    @Column(name = "arrival_airport_id", nullable = false)
    private Integer arrivalAirport;

    @NotNull
    @Column(name = "distance_km", nullable = false)
    private Integer distanceKm;

    @NotNull
    @Column(name = "flight_duration_minutes", nullable = false)
    private Integer flightDurationMinutes;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

}