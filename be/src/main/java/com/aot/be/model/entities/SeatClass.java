package com.aot.be.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "seat_class", schema = "mg_booking")
public class SeatClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "carry_on_baggage_kg", nullable = false)
    private Integer carryOnBaggageKg;

    @NotNull
    @Column(name = "checked_baggage_kg", nullable = false)
    private Integer checkedBaggageKg;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "allow_change", nullable = false)
    private Boolean allowChange = false;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "allow_refund", nullable = false)
    private Boolean allowRefund = false;

    @Column(name = "change_fee_percentage")
    private Integer changeFeePercentage;

    @Column(name = "refund_fee_percentage")
    private Integer refundFeePercentage;

}