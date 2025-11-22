package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "flight", schema = "mg_booking")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 200)
    @NotNull
    @Column(name = "flight_number", nullable = false, length = 200)
    private String flightNumber;

    @NotNull
    @Column(name = "route_id", nullable = false)
    private Integer route;

    @NotNull
    @Column(name = "aircraft_id", nullable = false)
    private Integer aircraft;

    @NotNull
    @Column(name = "departure_time", nullable = false)
    private Instant departureTime;

    @NotNull
    @Column(name = "arrival_time", nullable = false)
    private Instant arrivalTime;

    @NotNull
    @Column(name = "actual_departure_time", nullable = false)
    private LocalTime actualDepartureTime;

    @NotNull
    @Column(name = "actual_arrival_time", nullable = false)
    private LocalTime actualArrivalTime;

    @Size(max = 50)
    @NotNull
    @Column(name = "departure_gate", nullable = false, length = 50)
    private String departureGate;

    @Size(max = 50)
    @NotNull
    @Column(name = "arrival_gate", nullable = false, length = 50)
    private String arrivalGate;

    @Column(name = "available_seats")
    private Integer availableSeats;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    @NotNull
    @Column(name = "flight_date", nullable = false)
    private LocalDate flightDate;

}