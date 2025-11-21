package com.prueba.bounded_context.domain.services;

import com.prueba.bounded_context.domain.model.aggregates.Partner;
import com.prueba.bounded_context.domain.model.commands.CreatePartnerCommand;

import java.util.Optional;

public interface PartnerCommandService {
    Optional<Partner> handle(CreatePartnerCommand command);
}
