package com.prueba.bounded_context.interfaces.rest.resources;

public record PartnerResource(
    Long id,
    String partnerId,
    String representativeFirstName,
    String representativeLastName,
    String contactEmail,
    String contactPhone,
    String companyName,
    String city,
    String stateOrProvince,
    String country
) {}
