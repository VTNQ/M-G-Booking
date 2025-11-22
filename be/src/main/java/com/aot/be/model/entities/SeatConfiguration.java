package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seat_configuration", schema = "mg_booking")
public class SeatConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "aircraft_id", nullable = false)
    private Integer aircraft;

    @NotNull
    @Column(name = "seat_class_id", nullable = false)
    private Integer seatClass;

    @NotNull
    @Column(name = "number_of_seat", nullable = false)
    private Integer numberOfSeat;

    @Size(max = 100)
    @NotNull
    @Column(name = "row_position", nullable = false, length = 100)
    private String rowPosition;

}