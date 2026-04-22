package br.com.gabifontainhas.techchallenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginDTO {
    @Schema(description = "Entity for login operation")
    public record Request(
            @Schema(description = "User login", example = "gabriela.sordi@teste.com")
            @NotBlank
            String login,
            @Schema(description = "User password", example = "Teste@123")
            @NotBlank
            String password
    ) {
    }

    public record Response(
            String token,
            String login
    ) {
    }
}
