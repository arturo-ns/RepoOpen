package com.prueba.bounded_context.application.internal.commandservices;

import com.prueba.bounded_context.domain.exceptions.*;
import com.prueba.bounded_context.domain.model.aggregates.Partner;
import com.prueba.bounded_context.domain.model.commands.CreatePartnerCommand;
import com.prueba.bounded_context.domain.services.PartnerCommandService;
import com.prueba.bounded_context.infrastructure.persistence.jpa.repositories.PartnerRepository;
import com.prueba.shared.domain.model.valueobjects.EmailAddress;
import com.prueba.shared.domain.model.valueobjects.PersonName;
import com.prueba.shared.domain.model.valueobjects.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerCommandServiceImpl implements PartnerCommandService {
    private final PartnerRepository partnerRepository;

    public PartnerCommandServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Optional<Partner> handle(CreatePartnerCommand command) {
        // Validar regla de negocio: No permite dos partners con mismo firstName, lastName, companyName y country
        if (partnerRepository.existsByRepresentativeNameAndCompanyNameAndCountry(
                command.firstName(), command.lastName(), command.companyName(), command.country())) {
            throw new PartnerAlreadyExistsException(
                "A partner representative with the same first name, last name, company name, and country already exists"
            );
        }

        // Crear value objects (validan formato automáticamente)
        PersonName representativeName;
        PhoneNumber contactPhone;
        EmailAddress contactEmail;

        try {
            representativeName = new PersonName(command.firstName(), command.lastName());
        } catch (IllegalArgumentException e) {
            throw new InvalidPersonNameException("Invalid representative name: " + e.getMessage());
        }

        try {
            contactPhone = new PhoneNumber(command.contactPhone());
        } catch (IllegalArgumentException e) {
            throw new InvalidPhoneNumberException("Invalid phone number: " + e.getMessage());
        }

        try {
            contactEmail = new EmailAddress(command.contactEmail());
        } catch (IllegalArgumentException e) {
            throw new InvalidEmailAddressException("Invalid email address: " + e.getMessage());
        }

        // Validar campos obligatorios
        if (command.companyName() == null || command.companyName().trim().isEmpty()) {
            throw new ValidationException("Company name is required");
        }
        if (command.city() == null || command.city().trim().isEmpty()) {
            throw new ValidationException("City is required");
        }
        if (command.country() == null || command.country().trim().isEmpty()) {
            throw new ValidationException("Country is required");
        }

        // Crear y guardar el partner
        try {
            Partner partner = new Partner(
                representativeName,
                contactPhone,
                contactEmail,
                command.companyName(),
                command.city(),
                command.stateOrProvince(),
                command.country()
            );
            Partner savedPartner = partnerRepository.save(partner);
            return Optional.of(savedPartner);
        } catch (Exception e) {
            throw new ValidationException("Error saving partner: " + e.getMessage());
        }
    }
}
