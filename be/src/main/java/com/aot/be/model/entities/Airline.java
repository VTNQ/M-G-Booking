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
@Table(name = "airline", schema = "mg_booking")
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @NotNull
    @Column(name = "airline_code", nullable = false, length = 100)
    private String airlineCode;

    @Size(max = 100)
    @NotNull
    @Column(name = "airline_name", nullable = false, length = 100)
    private String airlineName;

    @Size(max = 100)
    @Column(name = "logo_url", length = 100)
    private String logoUrl;

    @NotNull
    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @Size(max = 200)
    @Column(name = "website", length = 200)
    private String website;

    @Size(max = 50)
    @Column(name = "hotline", length = 50)
    private String hotline;

    @Size(max = 50)
    @NotNull
    @ColumnDefault("'0'")
    @Column(name = "status", nullable = false, length = 50)
    private String status;

}