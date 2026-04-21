package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.ClientDTO;
import br.com.gabifontainhas.techchallenge.entity.Client;
import br.com.gabifontainhas.techchallenge.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO.Response>> findAllUsers(@RequestParam int size, @RequestParam int offset) {
        var clientList = clientService.findAll(size, offset);
        var clientDTOList = clientList.stream()
                .map(ClientDTO.Response::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientDTOList);
    }

    @PostMapping
    public ResponseEntity<ClientDTO.Response> create(@Valid @RequestBody ClientDTO.PostRequest dto) { //retornar o objeto
        var client = this.clientService.create(new Client(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientDTO.Response(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO.Response> update(@PathVariable Long id, @Valid @RequestBody ClientDTO.PutRequest dto) { //retornar o objeto
        var client = this.clientService.update(id, new Client(dto));
        return ResponseEntity.ok(new ClientDTO.Response(client));
    }
}
