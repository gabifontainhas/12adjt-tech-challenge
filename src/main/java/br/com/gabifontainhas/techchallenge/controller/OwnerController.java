package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.OwnerDTO;
import br.com.gabifontainhas.techchallenge.entity.Owner;
import br.com.gabifontainhas.techchallenge.service.OwnerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<OwnerDTO.Response> create(@Valid @RequestBody OwnerDTO.PostRequest dto) { //retornar o objeto
        var owner = this.ownerService.create(new Owner(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(new OwnerDTO.Response(owner));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerDTO.Response> update(@PathVariable Long id, @Valid @RequestBody OwnerDTO.PutRequest dto) { //retornar o objeto
        var owner = this.ownerService.update(id, new Owner(dto));
        return ResponseEntity.ok(new OwnerDTO.Response(owner));
    }
}
