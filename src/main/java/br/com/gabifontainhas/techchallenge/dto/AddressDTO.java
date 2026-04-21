package br.com.gabifontainhas.techchallenge.dto;

import br.com.gabifontainhas.techchallenge.entity.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddressDTO {
    public record Request(
            @NotBlank
            String street,
            @NotBlank
            String number,
            @NotBlank
            String city,
            @NotBlank
            String state,
            @NotBlank
            String zipcode
    ) {
    }
    public record Response(
            Long id,
            String street,
            String number,
            String city,
            String state,
            String zipcode
    ) {
        public Response(Address a) {
            this(a.getId(), a.getStreet(), a.getNumber(), a.getCity(), a.getState(), a.getZipcode());
        }
    }

}
