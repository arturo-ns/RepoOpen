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
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<?> createPartner(@Valid @RequestBody CreatePartnerResource resource) {
        try {
            var createPartnerCommand = CreatePartnerCommandFromResourceAssembler.toCommandFromResource(resource);
            var partner = partnerCommandService.handle(createPartnerCommand);
            
            if (partner.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new com.prueba.shared.interfaces.rest.resources.MessageResource("Error creating partner"));
            }
            
            var partnerResource = PartnerResourceFromEntityAssembler.toResourceFromEntity(partner.get());
            return new ResponseEntity<>(partnerResource, HttpStatus.CREATED);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new com.prueba.shared.interfaces.rest.resources.MessageResource("Error: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new com.prueba.shared.interfaces.rest.resources.MessageResource("Error: " + e.getMessage()));
        }
    }
}
