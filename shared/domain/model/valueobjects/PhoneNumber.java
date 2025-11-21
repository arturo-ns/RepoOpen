package com.prueba.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.regex.Pattern;

@Embeddable
@Getter
public class PhoneNumber {
    // Formato internacional: +[código país][número] (ej: +1234567890, +34612345678)
    private static final String PHONE_PATTERN = "^\\+[1-9]\\d{1,14}$";
    
    private static final Pattern pattern = Pattern.compile(PHONE_PATTERN);

    @Column(name = "phone", nullable = false)
    private String value;

    public PhoneNumber() {
    }

    public PhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }
        String trimmedPhone = phone.trim();
        if (!pattern.matcher(trimmedPhone).matches()) {
            throw new IllegalArgumentException("Invalid phone number format. Must be in international format (e.g., +1234567890): " + phone);
        }
        this.value = trimmedPhone;
    }
}
