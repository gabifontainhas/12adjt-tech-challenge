package br.com.gabifontainhas.techchallenge.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LoginDTO {
    public record Request(
            @NotBlank String login,
            @NotBlank String password
    ) {
    }

    public record Response(
            String token,
            String login
    ) {
    }
}
