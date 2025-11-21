package com.prueba.bounded_context.interfaces.rest.transform;

import com.prueba.bounded_context.domain.model.aggregates.Partner;
import com.prueba.bounded_context.interfaces.rest.resources.PartnerResource;

public class PartnerResourceFromEntityAssembler {
    public static PartnerResource toResourceFromEntity(Partner partner) {
        return new PartnerResource(
            partner.getId(),
            partner.getPartnerIdValue(),
            partner.getRepresentativeFirstName(),
            partner.getRepresentativeLastName(),
            partner.getContactEmailValue(),
            partner.getContactPhoneValue(),
            partner.getCompanyName(),
            partner.getCity(),
            partner.getStateOrProvince(),
            partner.getCountry()
        );
    }
}
