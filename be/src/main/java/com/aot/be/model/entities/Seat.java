package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "seat", schema = "mg_booking")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "flight_id", nullable = false)
    private Integer flight;

    @NotNull
    @Column(name = "seat_class_id", nullable = false)
    private Integer seatClass;

    @Size(max = 50)
    @Column(name = "seat_number", length = 50)
    private String seatNumber;

    @NotNull
    @Column(name = "price", nullable = false, precision = 10)
    private BigDecimal price;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

    @Size(max = 100)
    @NotNull
    @Column(name = "row_position", nullable = false, length = 100)
    private String rowPosition;

    @Size(max = 100)
    @NotNull
    @Column(name = "column_position", nullable = false, length = 100)
    private String columnPosition;

}