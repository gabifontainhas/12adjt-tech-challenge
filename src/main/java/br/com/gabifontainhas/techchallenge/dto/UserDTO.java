package br.com.gabifontainhas.techchallenge.dto;

import br.com.gabifontainhas.techchallenge.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDTO {
    @Schema(name = "UserDTO.Response")
    public record Response(
            @Schema(description = "Unique identifier of the user", example = "42", accessMode = Schema.AccessMode.READ_ONLY)
            Long id,

            @Schema(description = "E-mail address", example = "angela.schrute@dundermifflin.com")
            String email,

            @Schema(description = "Full name", example = "Angela Schrute")
            String name,

            @Schema(description = "Unique username for system authentication. It's the same as the e-mail address", example = "angela.schrute@dundermifflin.com")
            String login,

            @Schema(description = "Timestamp of the last record modification", example = "2026-04-23", accessMode = Schema.AccessMode.READ_ONLY)
            LocalDate lastUpdate,

            @Schema(description = "Detailed address information associated with the customer")
            AddressDTO.Response address
           ) {
        public Response(User u) {
            this(u.getId(), u.getEmail(), u.getName(), u.getLogin(), u.getLastUpdate(), new AddressDTO.Response(u.getAddress()));
        }
    }

    @Schema(name = "UserDTO.ChangePassword")
    public record ChangePassword(

            @Schema(
                    description = "The new secure password. Must be at least 4 characters long.",
                    example = "****",
                    format = "password",
                    minLength = 4
            )
            @NotBlank
            @Size(min = 4)
            String newPassword,

            @Schema(
                    description = "Current password for identity verification",
                    example = "****",
                    format = "password"
            )
            @NotBlank
            String oldPassword) {
    }
}
