package com.prueba.bounded_context.interfaces.rest.transform;

import com.prueba.bounded_context.domain.model.commands.CreatePartnerCommand;
import com.prueba.bounded_context.interfaces.rest.resources.CreatePartnerResource;

public class CreatePartnerCommandFromResourceAssembler {
    public static CreatePartnerCommand toCommandFromResource(CreatePartnerResource resource) {
        return new CreatePartnerCommand(
            resource.firstName(),
            resource.lastName(),
            resource.contactPhone(),
            resource.contactEmail(),
            resource.companyName(),
            resource.city(),
            resource.stateOrProvince(),
            resource.country()
        );
    }
}
