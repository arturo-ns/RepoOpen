package com.prueba.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.regex.Pattern;

@Embeddable
@Getter
public class EmailAddress {
    private static final String EMAIL_PATTERN = 
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Column(name = "email", nullable = false)
    private String value;

    public EmailAddress() {
    }

    public EmailAddress(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }
        String trimmedEmail = email.trim().toLowerCase();
        if (!pattern.matcher(trimmedEmail).matches()) {
            throw new IllegalArgumentException("Invalid email address format: " + email);
        }
        this.value = trimmedEmail;
    }
}
