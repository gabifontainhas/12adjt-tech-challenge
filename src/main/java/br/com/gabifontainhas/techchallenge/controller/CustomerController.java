package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.ApiErrorDoc;
import br.com.gabifontainhas.techchallenge.dto.CustomerDTO;
import br.com.gabifontainhas.techchallenge.entity.Customer;
import br.com.gabifontainhas.techchallenge.service.CustomerService;
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
@RequestMapping("/v1/customers")
@Tag(name = "Customer", description = "Customer Management: Operations related to creating, retrieving and updating customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Retrieve all customers", description = "Returns a paginated list of customers.")
    @ApiResponse(responseCode = "200", description = "Customer data retrieved successfully.")
    @GetMapping
    public ResponseEntity<List<CustomerDTO.Response>> findAllUsers(@RequestParam int size, @RequestParam int offset) {
        var customerList = customerService.findAll(size, offset);
        var customerDTOList = customerList.stream()
                .map(CustomerDTO.Response::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOList);
    }

    @Operation(summary = "Get customer details by ID", description = "Fetches a single customer's detailed information based on the provided unique identifier.")
    @ApiResponse(responseCode = "200", description = "Customer data retrieved successfully.")
    @ApiResponse(responseCode = "404", description = "Customer not found with the provided ID",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.CustomerNotFoundDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO.Response> findCustomerById(@PathVariable Long id) {
        var customerDTO = new CustomerDTO.Response(customerService.findCustomerById(id));
        return ResponseEntity.ok(customerDTO);
    }

    @Operation(summary = "Get customer details by name", description = "Fetches a single customer's detailed information based on the provided exact name.")
    @ApiResponse(responseCode = "200", description = "Customer data retrieved successfully.")
    @ApiResponse(responseCode = "404", description = "Customer not found with the provided name",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.CustomerNotFoundByNameDTO.class)))
    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDTO.Response>> findUserByName(@RequestParam String name) {
        var customerList = customerService.findCustomerByName(name);
        var customerDTOList = customerList.stream().map(CustomerDTO.Response::new).collect(Collectors.toList());
        return ResponseEntity.ok(customerDTOList);
    }

    @Operation(summary = "Create a new customer", description = "Persists a new customer record in the database. E-mail must be unique. Returns the created entity with its generated ID")
    @ApiResponse(responseCode = "201", description = "Customer successfully created.")
    @ApiResponse(responseCode = "422", description = "E-mail already exists",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.CustomerEmailAlreadyExistsDTO.class)))
    @PostMapping
    public ResponseEntity<CustomerDTO.Response> create(@Valid @RequestBody CustomerDTO.PostRequest dto) {
        var customer = this.customerService.create(new Customer(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerDTO.Response(customer));
    }

    @Operation(summary = "Update an existing customer", description = "Updates the customer information identified by the ID. It's possible to update partial information, like phone number and address. Sensitive fields like e-mail remain immutable. Returns the updated entity")
    @ApiResponse(responseCode = "200", description = "Customer successfully updated.")
    @ApiResponse(responseCode = "404", description = "Customer not found with the provided ID.",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.CustomerNotFoundDTO.class)))
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO.Response> update(@PathVariable Long id, @Valid @RequestBody CustomerDTO.PutRequest dto) {
        var customer = this.customerService.update(id, new Customer(dto));
        return ResponseEntity.ok(new CustomerDTO.Response(customer));
    }
}
