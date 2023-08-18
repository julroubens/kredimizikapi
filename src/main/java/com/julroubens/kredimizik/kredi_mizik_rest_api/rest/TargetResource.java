package com.julroubens.kredimizik.kredi_mizik_rest_api.rest;

import com.julroubens.kredimizik.kredi_mizik_rest_api.model.TargetDTO;
import com.julroubens.kredimizik.kredi_mizik_rest_api.service.TargetService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/targets", produces = MediaType.APPLICATION_JSON_VALUE)
public class TargetResource {

    private final TargetService targetService;

    public TargetResource(final TargetService targetService) {
        this.targetService = targetService;
    }

    @GetMapping
    public ResponseEntity<List<TargetDTO>> getAllTargets() {
        return ResponseEntity.ok(targetService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TargetDTO> getTarget(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(targetService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createTarget(@RequestBody @Valid final TargetDTO targetDTO) {
        final Long createdId = targetService.create(targetDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateTarget(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final TargetDTO targetDTO) {
        targetService.update(id, targetDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteTarget(@PathVariable(name = "id") final Long id) {
        targetService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
