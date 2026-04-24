package br.com.gabifontainhas.techchallenge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginDTO {

    @Schema(name = "LoginDTO.Request")
    public record Request(
            @Schema(description = "User login", example = "gabriela.sordi@teste.com")
            @NotBlank
            String login,
            @Schema(description = "User password", example = "Teste@123")
            @NotBlank
            String password
    ) {
    }

    @Schema(name = "LoginDTO.Response")
    public record Response(
            @Schema(description = "Generated token for login", example = "0f138924-72c3-4958-af0f-05850dd4cd3d")
            String token,
            @Schema(description = "User login", example = "gabriela.sordi@teste.com")
            String login
    ) {
    }
}
