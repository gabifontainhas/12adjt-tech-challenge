package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.ClientDTO;
import br.com.gabifontainhas.techchallenge.dto.OwnerDTO;
import br.com.gabifontainhas.techchallenge.entity.Owner;
import br.com.gabifontainhas.techchallenge.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<OwnerDTO.Response>> findAllUsers(@RequestParam int size, @RequestParam int offset) {
        var ownerList = ownerService.findAll(size, offset);
        var ownerDTOList = ownerList.stream()
                .map(OwnerDTO.Response::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ownerDTOList);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody OwnerDTO.PostRequest dto) { //retornar o objeto
        this.ownerService.create(new Owner(dto));
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody OwnerDTO.PutRequest dto) { //retornar o objeto
        this.ownerService.update(id, new Owner(dto));
        return ResponseEntity.status(201).build();
    }
}
