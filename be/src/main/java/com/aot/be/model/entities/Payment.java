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
@Table(name = "payment", schema = "mg_booking")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "payment_method_id", nullable = false)
    private Integer paymentMethod;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer user;

    @Column(name = "save_payment_id")
    private Integer savePayment;

    @NotNull
    @Column(name = "amount", nullable = false, precision = 10)
    private BigDecimal amount;

    @NotNull
    @Column(name = "booking_id", nullable = false)
    private Integer booking;

    @NotNull
    @Column(name = "payment_date", nullable = false)
    private Instant paymentDate;

    @Size(max = 200)
    @Column(name = "transaction_id", length = 200)
    private String transactionId;

    @Size(max = 50)
    @Column(name = "note", length = 50)
    private String note;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

}