package br.com.gabifontainhas.techchallenge.dto;

import br.com.gabifontainhas.techchallenge.entity.Owner;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OwnerDTO {

    @Schema(name = "OwnerDTO.PostRequest")
    public record PostRequest(
            @Schema(description = "E-mail address", example = "michaelscott@dundermifflin")
            @NotBlank
            String email,

            @Schema(description = "Full name of the restaurant owner", example = "Michael Scott")
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

            @Schema(description = "The registered commercial name of the restaurant", example = "Dunder Mifflin")
            @NotBlank
            String restaurantName
    ) {
    }
    @Schema(name = "OwnerDTO.PutRequest")
    public record PutRequest(

            @Schema(description = "Full name of the restaurant owner", example = "Michael Scott")
            @NotBlank
            String name,

            @Schema(description = "Detailed address information associated with the customer")
            @Valid
            @NotNull
            AddressDTO.Request address,

            @Schema(description = "The registered commercial name of the restaurant", example = "Dunder Mifflin")
            @NotBlank
            String restaurantName
    ) {
    }

    @Schema(name = "OwnerDTO.Response")
    public record Response(
            @Schema(description = "Unique identifier of the owner", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
            Long id,

            @Schema(description = "E-mail address", example = "michaelscott@dundermifflin")
            String email,

            @Schema(description = "Full name of the restaurant owner", example = "Michael Scott")
            String name,

            @Schema(description = "Unique username for system authentication. It's the same as the e-mail address", example = "michaelscott@dundermifflin")
            String login,

            @Schema(description = "Timestamp of the last record modification", example = "2026-04-23")
            LocalDate lastUpdate,

            @Schema(description = "Detailed address information associated with the customer")
            @Valid
            @NotNull
            AddressDTO.Response address,

            @Schema(description = "The registered commercial name of the restaurant", example = "Dunder Mifflin")
            String restaurantName
    ) {
        public Response(Owner o) {
            this(o.getId(), o.getEmail(), o.getName(), o.getLogin(), o.getLastUpdate(), new AddressDTO.Response(o.getAddress()), o.getRestaurantName());
        }
    }
}