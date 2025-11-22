package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "passenger_detail", schema = "mg_booking")
public class PassengerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "booking_id", nullable = false)
    private Integer booking;

    @NotNull
    @Column(name = "seat_id", nullable = false)
    private Integer seat;

    @Size(max = 50)
    @NotNull
    @Column(name = "passenger_name", nullable = false, length = 50)
    private String passengerName;

    @NotNull
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "gender", nullable = false)
    private Boolean gender = false;

    @Size(max = 100)
    @NotNull
    @Column(name = "id_number", nullable = false, length = 100)
    private String idNumber;

    @NotNull
    @Column(name = "nationality_id", nullable = false)
    private Integer nationalityId;

    @Size(max = 100)
    @NotNull
    @Column(name = "passenger_type", nullable = false, length = 100)
    private String passengerType;

    @NotNull
    @Column(name = "ticket_price", nullable = false, precision = 10)
    private BigDecimal ticketPrice;

    @Size(max = 20)
    @NotNull
    @Column(name = "check_in_status", nullable = false, length = 20)
    private String checkInStatus;

    @Column(name = "check_in_time")
    private Instant checkInTime;

}