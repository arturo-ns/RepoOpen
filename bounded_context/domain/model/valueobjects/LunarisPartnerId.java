package com.prueba.bounded_context.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.UUID;

@Embeddable
@Getter
public class LunarisPartnerId {
    @Column(name = "partner_id", nullable = false, unique = true)
    private String value;

    public LunarisPartnerId() {
        this.value = UUID.randomUUID().toString();
    }

    public LunarisPartnerId(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("LunarisPartnerId cannot be null or empty");
        }
        try {
            UUID.fromString(value);
            this.value = value;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid GUID format for LunarisPartnerId: " + value);
        }
    }

    public static LunarisPartnerId generate() {
        return new LunarisPartnerId();
    }
}
