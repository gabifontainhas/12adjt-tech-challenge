package br.com.gabifontainhas.techchallenge.dto;

import br.com.gabifontainhas.techchallenge.entity.Client;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ClientDTO {
    public record PostRequest(
            @NotBlank
            String email,
            @NotBlank
            String name,
            @NotBlank
            String password,
            @Valid
            @NotNull
            AddressDTO.Request address,
            @NotBlank
            String phoneNumber
    ) {
    }

    public record PutRequest(
            @NotBlank
            String name,
            @NotBlank
            String phoneNumber,
            @Valid
            @NotNull
            AddressDTO.Request address
    ) {
    }

    public record Response(
            Long id,
            String email,
            String name,
            String login,
            LocalDate lastUpdate,
            AddressDTO.Response address,
            String phoneNumber
    ) {
        public Response(Client c) {
            this(c.getId(), c.getEmail(), c.getName(), c.getLogin(), c.getLastUpdate(), new AddressDTO.Response(c.getAddress()), c.getPhoneNumber());
        }
    }
}
