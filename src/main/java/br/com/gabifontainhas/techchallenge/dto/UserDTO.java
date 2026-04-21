package br.com.gabifontainhas.techchallenge.dto;

import br.com.gabifontainhas.techchallenge.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDTO {

    public record Response(Long id, String email, String name, String login, LocalDate lastUpdate) {
        public Response(User u) {
            this(u.getId(), u.getEmail(), u.getName(), u.getLogin(), u.getLastUpdate());
        }
    }

    public record ChangePassword(
            @NotBlank @Size(min = 4) String newPassword,
            @NotBlank String oldPassword) {
    }
}
