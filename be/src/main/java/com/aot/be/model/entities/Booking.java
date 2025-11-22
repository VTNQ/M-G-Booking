package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "booking", schema = "mg_booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "booking_reference", nullable = false, length = 100)
    private String bookingReference;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer user;

    @NotNull
    @Column(name = "flight_id", nullable = false)
    private Integer flight;

    @Column(name = "employee_id")
    private Integer employee;

    @Column(name = "promotion_id")
    private Integer promotion;

    @Column(name = "booking_date")
    private Instant bookingDate;

    @NotNull
    @Column(name = "number_of_tickets", nullable = false)
    private Integer numberOfTickets;

    @NotNull
    @Column(name = "total_amount", nullable = false, precision = 10)
    private BigDecimal totalAmount;

    @Column(name = "discount_amount", precision = 10)
    private BigDecimal discountAmount;

    @NotNull
    @Column(name = "final_amount", nullable = false, precision = 10)
    private BigDecimal finalAmount;

    @Size(max = 100)
    @NotNull
    @Column(name = "booking_type", nullable = false, length = 100)
    private String bookingType;

    @Size(max = 50)
    @Column(name = "note", length = 50)
    private String note;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

}