package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.CustomerDTO;
import br.com.gabifontainhas.techchallenge.entity.Customer;
import br.com.gabifontainhas.techchallenge.service.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")
@Tag(name = "Customer", description = "Login service for all users")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO.Response>> findAllUsers(@RequestParam int size, @RequestParam int offset) {
        var customerList = customerService.findAll(size, offset);
        var customerDTOList = customerList.stream()
                .map(CustomerDTO.Response::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOList);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO.Response> create(@Valid @RequestBody CustomerDTO.PostRequest dto) { //retornar o objeto
        var customer = this.customerService.create(new Customer(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerDTO.Response(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO.Response> update(@PathVariable Long id, @Valid @RequestBody CustomerDTO.PutRequest dto) { //retornar o objeto
        var customer = this.customerService.update(id, new Customer(dto));
        return ResponseEntity.ok(new CustomerDTO.Response(customer));
    }
}
