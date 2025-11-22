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
@Table(name = "promotion", schema = "mg_booking")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "promotion_code", nullable = false, length = 100)
    private String promotionCode;

    @Size(max = 100)
    @NotNull
    @Column(name = "promotion_name", nullable = false, length = 100)
    private String promotionName;

    @Lob
    @Column(name = "description")
    private String description;

    @Size(max = 50)
    @NotNull
    @Column(name = "discount_type", nullable = false, length = 50)
    private String discountType;

    @Column(name = "discount_value", precision = 10)
    private BigDecimal discountValue;

    @Column(name = "max_discount", precision = 10)
    private BigDecimal maxDiscount;

    @NotNull
    @Column(name = "min_order_value", nullable = false, precision = 10)
    private BigDecimal minOrderValue;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private Instant startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private Instant endDate;

    @NotNull
    @Column(name = "total_quantity", nullable = false)
    private Integer totalQuantity;

    @Column(name = "used_quantity")
    private Integer usedQuantity;

    @Size(max = 50)
    @Column(name = "status", length = 50)
    private String status;

}