package com.example.moretech.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Services {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "wheelchair_service_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "wheelchair_service_activity"))
    })
    private Service wheelchair;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "blind_service_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "blind_service_activity"))
    })
    private Service blind;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "nfc_for_bank_cards_service_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "nfc_for_bank_cards_service_activity"))
    })
    private Service nfcForBankCards;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "qr_read_service_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "qr_read_service_activity"))
    })
    private Service qrRead;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "supports_usd_service_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "supports_usd_service_activity"))
    })
    private Service supportsUsd;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "supports_charge_rub_service_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "supports_charge_rub_service_activity"))
    })
    private Service supportsChargeRub;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "supports_eur_service_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "supports_eur_service_activity"))
    })
    private Service supportsEur;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "serviceCapability", column = @Column(name = "supports_rub_service_capability")),
            @AttributeOverride(name = "serviceActivity", column = @Column(name = "supports_rub_service_activity"))
    })
    private Service supportsRub;
}

