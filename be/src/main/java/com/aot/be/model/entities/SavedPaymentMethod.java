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
@Table(name = "saved_payment_method", schema = "mg_booking")
public class SavedPaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer user;

    @NotNull
    @Column(name = "payment_method_id", nullable = false)
    private Integer paymentMethod;

    @Size(max = 100)
    @Column(name = "card_token", length = 100)
    private String cardToken;

    @Size(max = 50)
    @Column(name = "card_type", length = 50)
    private String cardType;

    @Size(max = 100)
    @Column(name = "masked_card_number", length = 100)
    private String maskedCardNumber;

    @Size(max = 50)
    @Column(name = "cardholder_name", length = 50)
    private String cardholderName;

    @Size(max = 20)
    @Column(name = "expiry_month", length = 20)
    private String expiryMonth;

    @Size(max = 20)
    @Column(name = "expiry_year", length = 20)
    private String expiryYear;

    @Size(max = 200)
    @Column(name = "issuing_bank", length = 200)
    private String issuingBank;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @Size(max = 100)
    @Column(name = "created_date", length = 100)
    private String createdDate;

    @Size(max = 100)
    @Column(name = "last_used_date", length = 100)
    private String lastUsedDate;

}