package com.julroubens.kredimizik.kredi_mizik_rest_api.rest;

import com.julroubens.kredimizik.kredi_mizik_rest_api.model.InteractionDTO;
import com.julroubens.kredimizik.kredi_mizik_rest_api.service.InteractionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/interactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class InteractionResource {

    private final InteractionService interactionService;

    public InteractionResource(final InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    @GetMapping
    public ResponseEntity<List<InteractionDTO>> getAllInteractions() {
        return ResponseEntity.ok(interactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteractionDTO> getInteraction(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(interactionService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createInteraction(
            @RequestBody @Valid final InteractionDTO interactionDTO) {
        final Long createdId = interactionService.create(interactionDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateInteraction(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final InteractionDTO interactionDTO) {
        interactionService.update(id, interactionDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteInteraction(@PathVariable(name = "id") final Long id) {
        interactionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
