package com.aot.be.model.entities;

import com.aot.be.Enum.AirlineStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

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

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status= AirlineStatus.ACTIVE.getCode();

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssXXX")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssXXX")
    private OffsetDateTime updatedAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;
    @PrePersist
    public void prePersist() {
        if(this.airlineCode == null) {
            this.airlineCode = "AL"+String.format("%04d", (int)(Math.random()*999));
        }
    }

}