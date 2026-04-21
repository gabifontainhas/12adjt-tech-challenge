package br.com.gabifontainhas.techchallenge.dto;

import br.com.gabifontainhas.techchallenge.entity.Owner;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OwnerDTO {
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
            String restaurantName
    ) {
    }

    public record PutRequest(
            @NotBlank
            String name,
            @Valid
            @NotNull
            AddressDTO.Request address,
            @NotBlank
            String restaurantName
    ) {
    }

    public record Response(
            Long id,
            String email,
            String name,
            String login,
            LocalDate lastUpdate,
            @Valid
            @NotNull
            AddressDTO.Response address,
            String restaurantName
    ) {
        public Response(Owner o) {
            this(o.getId(), o.getEmail(), o.getName(), o.getLogin(), o.getLastUpdate(), new AddressDTO.Response(o.getAddress()), o.getRestaurantName());
        }
    }
}