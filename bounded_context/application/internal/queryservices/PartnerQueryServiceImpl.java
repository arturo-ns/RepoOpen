package com.prueba.bounded_context.application.internal.queryservices;

import com.prueba.bounded_context.domain.model.aggregates.Partner;
import com.prueba.bounded_context.domain.model.queries.GetPartnerByIdQuery;
import com.prueba.bounded_context.domain.services.PartnerQueryService;
import com.prueba.bounded_context.infrastructure.persistence.jpa.repositories.PartnerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PartnerQueryServiceImpl implements PartnerQueryService {
    private final PartnerRepository partnerRepository;

    public PartnerQueryServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public Optional<Partner> handle(GetPartnerByIdQuery query) {
        return partnerRepository.findById(query.partnerId());
    }
}
