package com.prueba.bounded_context.domain.services;

import com.prueba.bounded_context.domain.model.aggregates.Partner;
import com.prueba.bounded_context.domain.model.queries.GetPartnerByIdQuery;

import java.util.Optional;

public interface PartnerQueryService {
    Optional<Partner> handle(GetPartnerByIdQuery query);
}
