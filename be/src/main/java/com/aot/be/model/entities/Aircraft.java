package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "aircraft", schema = "mg_booking")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "airline_id", nullable = false)
    private Integer airline;

    @Size(max = 100)
    @NotNull
    @Column(name = "aircraft_registration", nullable = false, length = 100)
    private String aircraftRegistration;

    @Size(max = 100)
    @NotNull
    @Column(name = "aircraft_type", nullable = false, length = 100)
    private String aircraftType;

    @NotNull
    @Column(name = "total_seat", nullable = false)
    private Integer totalSeat;

    @NotNull
    @Column(name = "year_manufactured", nullable = false)
    private Integer yearManufactured;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "last_maintenance_date")
    private LocalDate lastMaintenanceDate;

}