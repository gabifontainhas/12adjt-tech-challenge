package br.com.gabifontainhas.techchallenge.dto;

import br.com.gabifontainhas.techchallenge.entity.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddressDTO {
    @Schema(name = "AddressDTO.Request")
    public record Request(

            @Schema(description = "Street name", example = "Main Street")
            @NotBlank
            String street,

            @Schema(description = "The building or house number. Can include letters for complexes.", example = "1230-B")
            @NotBlank
            String number,

            @Schema(description = "City name", example = "Sao Paulo")
            @NotBlank
            String city,

            @Schema(description = "State abbreviation (e.g., SP, RJ)", example = "SP")
            @NotBlank
            String state,

            @Schema(description = "Postal code or Zip code", example = "10001-000", pattern = "^[0-9]{5}-[0-9]{3}$")
            @NotBlank
            String zipcode
    ) {
    }
    @Schema(name = "AddressDTO.Response")
    public record Response(
            @Schema(description = "Unique identifier of the address", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
            Long id,

            @Schema(description = "Street name", example = "Main Street")
            String street,

            @Schema(description = "The building or house number. Can include letters for complexes.", example = "1230-B")
            String number,

            @Schema(description = "City name", example = "Sao Paulo")
            String city,

            @Schema(description = "State abbreviation (e.g., SP, RJ)", example = "SP")
            String state,

            @Schema(description = "Postal code or Zip code", example = "10001-000", pattern = "^[0-9]{5}-[0-9]{3}$")
            String zipcode
    ) {
        public Response(Address a) {
            this(a.getId(), a.getStreet(), a.getNumber(), a.getCity(), a.getState(), a.getZipcode());
        }
    }

}
