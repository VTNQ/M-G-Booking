package com.mgbooking.server.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Table(name = "Detail_Flight")
public class DetailFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Id_Flight", nullable = false)
    private Flight idFlight;


    @Column(name = "Type", nullable = false, length = 50)
    private String type;

    @Column(name = "Price", nullable = false, precision = 10)
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Flight getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(Flight idFlight) {
        this.idFlight = idFlight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

}