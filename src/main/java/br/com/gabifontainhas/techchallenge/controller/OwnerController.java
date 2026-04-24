package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.ApiErrorDoc;
import br.com.gabifontainhas.techchallenge.dto.OwnerDTO;
import br.com.gabifontainhas.techchallenge.entity.Owner;
import br.com.gabifontainhas.techchallenge.service.OwnerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/owners")
@Tag(name = "Owner", description = "Owner Management: Operations related to creating, retrieving and updating owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @Operation(summary = "Retrieve all owners", description = "Returns a paginated list of owners.")
    @ApiResponse(responseCode = "200", description = "Owner data retrieved successfully.")
    @GetMapping
    public ResponseEntity<List<OwnerDTO.Response>> findAllOwners(@RequestParam int size, @RequestParam int offset) {
        var ownerList = ownerService.findAll(size, offset);
        var ownerDTOList = ownerList.stream()
                .map(OwnerDTO.Response::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ownerDTOList);
    }

    @Operation(summary = "Create a new owner", description = "Persists a new owner record in the database. E-mail must be unique. Returns the created entity with its generated ID")
    @ApiResponse(responseCode = "201", description = "Owner successfully created.")
    @ApiResponse(responseCode = "422", description = "E-mail already exists",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.OwnerEmailAlreadyExistsDTO.class)))
    @PostMapping
    public ResponseEntity<OwnerDTO.Response> create(@Valid @RequestBody OwnerDTO.PostRequest dto) {
        var owner = this.ownerService.create(new Owner(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(new OwnerDTO.Response(owner));
    }

    @Operation(summary = "Update an existing owner", description = "Updates the owner information identified by the ID. It's possible to update partial information, like restaurant name and address. Sensitive fields like e-mail remain immutable. Returns the updated entity")
    @ApiResponse(responseCode = "200", description = "Owner successfully updated.")
    @ApiResponse(responseCode = "404", description = "Owner not found with the provided ID.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.OwnerNotFoundDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO.Response> update(@PathVariable Long id, @Valid @RequestBody OwnerDTO.PutRequest dto) {
        var owner = this.ownerService.update(id, new Owner(dto));
        return ResponseEntity.ok(new OwnerDTO.Response(owner));
    }
}
