package br.com.gabifontainhas.techchallenge.dto;

import br.com.gabifontainhas.techchallenge.entity.Customer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomerDTO {

    @Schema(name = "CustomerDTO.PostRequest")
    public record PostRequest(
            @Schema(description = "E-mail address", example = "joao.silva@teste.com")
            @NotBlank
            String email,

            @Schema(description = "Full name", example = "Joao da Silva")
            @NotBlank
            String name,

            @Schema(description = "User's secure password for authentication. Must contain at least 4 characters.",
                    example = "****",
                    format = "password",
                    minLength = 4)
            @NotBlank
            @Size(min = 4)
            String password,

            @Schema(description = "Detailed address information associated with the customer")
            @Valid
            @NotNull
            AddressDTO.Request address,

            @Schema(description = "International format phone number. Numbers only", example = "5511999998888")
            @NotBlank
            String phoneNumber

    ) {
    }

    @Schema(name = "CustomerDTO.PutRequest")
    public record PutRequest(

            @Schema(description = "Full name", example = "Joao da Silva")
            @NotBlank
            String name,

            @Schema(description = "International format phone number. Numbers only", example = "5511999998888")
            @NotBlank
            String phoneNumber,

            @Schema(description = "Detailed address information associated with the customer")
            @Valid
            @NotNull
            AddressDTO.Request address
    ) {
    }

    @Schema(name = "CustomerDTO.Response")
    public record Response(
            @Schema(description = "Unique identifier of the customer", example = "42", accessMode = Schema.AccessMode.READ_ONLY)
            Long id,

            @Schema(description = "E-mail address", example = "joao.silva@teste.com")
            String email,

            @Schema(description = "Full name", example = "Joao da Silva")
            String name,

            @Schema(description = "Unique username for system authentication. It's the same as the e-mail address", example = "joao.silva@teste.com")
            String login,

            @Schema(description = "Timestamp of the last record modification", example = "2026-04-23", accessMode = Schema.AccessMode.READ_ONLY)
            LocalDate lastUpdate,

            @Schema(description = "Detailed address information associated with the customer")
            AddressDTO.Response address,

            @Schema(description = "International format phone number. Numbers only", example = "5511999998888")
            String phoneNumber
    ) {
        public Response(Customer c) {
            this(c.getId(), c.getEmail(), c.getName(), c.getLogin(), c.getLastUpdate(), new AddressDTO.Response(c.getAddress()), c.getPhoneNumber());
        }
    }
}
