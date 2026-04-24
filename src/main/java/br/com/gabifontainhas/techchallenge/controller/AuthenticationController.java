package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.ApiErrorDoc;
import br.com.gabifontainhas.techchallenge.dto.LoginDTO;
import br.com.gabifontainhas.techchallenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@Tag(name = "Authentication", description = "Login service for all users")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Inform user's login and password for authentication")
    @ApiResponse(responseCode = "200", description = "Returns user's login and generated token")
    @ApiResponse(responseCode = "401", description = "Authentication Failed",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.AuthenticationFailedDTO.class)))
    @PostMapping("/login")

    public ResponseEntity<LoginDTO.Response> loginUser(
            @Valid
            @RequestBody
            LoginDTO.Request dto) {

        var loginResponse = this.userService.loginUser(dto.login(), dto.password());
        return ResponseEntity.ok(loginResponse);
    }
}
