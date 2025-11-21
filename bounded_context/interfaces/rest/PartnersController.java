package com.prueba.bounded_context.interfaces.rest;

import com.prueba.bounded_context.domain.model.commands.CreatePartnerCommand;
import com.prueba.bounded_context.domain.services.PartnerCommandService;
import com.prueba.bounded_context.interfaces.rest.resources.CreatePartnerResource;
import com.prueba.bounded_context.interfaces.rest.resources.PartnerResource;
import com.prueba.bounded_context.interfaces.rest.transform.CreatePartnerCommandFromResourceAssembler;
import com.prueba.bounded_context.interfaces.rest.transform.PartnerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/partners", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Partners", description = "Available Partner Endpoints")
public class PartnersController {
    private final PartnerCommandService partnerCommandService;

    public PartnersController(PartnerCommandService partnerCommandService) {
        this.partnerCommandService = partnerCommandService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a new partner", description = "Create a new partner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Partner created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input or business rule violation"),
            @ApiResponse(responseCode = "409", description = "Partner already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PartnerResource> createPartner(@Valid @RequestBody CreatePartnerResource resource) {
        var createPartnerCommand = CreatePartnerCommandFromResourceAssembler.toCommandFromResource(resource);
        var partner = partnerCommandService.handle(createPartnerCommand);
        
        if (partner.isEmpty()) {
            throw new RuntimeException("Error creating partner");
        }
        
        var partnerResource = PartnerResourceFromEntityAssembler.toResourceFromEntity(partner.get());
        return new ResponseEntity<>(partnerResource, HttpStatus.CREATED);
    }
}
