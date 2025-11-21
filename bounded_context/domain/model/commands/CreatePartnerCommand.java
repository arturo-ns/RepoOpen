package com.prueba.bounded_context.domain.model.commands;

public record CreatePartnerCommand(
    String firstName,
    String lastName,
    String contactPhone,
    String contactEmail,
    String companyName,
    String city,
    String stateOrProvince,
    String country
) {}
