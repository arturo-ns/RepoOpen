package com.prueba.bounded_context.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreatePartnerResource(
    @NotBlank(message = "First name is required")
    String firstName,
    
    @NotBlank(message = "Last name is required")
    String lastName,
    
    @NotBlank(message = "Contact phone is required")
    @Pattern(regexp = "^\\+[1-9]\\d{1,14}$", message = "Phone number must be in international format (e.g., +1234567890)")
    String contactPhone,
    
    @NotBlank(message = "Contact email is required")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", 
             message = "Invalid email address format")
    String contactEmail,
    
    @NotBlank(message = "Company name is required")
    String companyName,
    
    @NotBlank(message = "City is required")
    String city,
    
    String stateOrProvince,
    
    @NotBlank(message = "Country is required")
    String country
) {}
