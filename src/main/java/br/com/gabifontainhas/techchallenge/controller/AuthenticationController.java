package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.LoginDTO;
import br.com.gabifontainhas.techchallenge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    private final UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO.Response> loginUser(
            @Valid
            @RequestBody
            LoginDTO.Request dto) {
        var loginResponse = this.userService.loginUser(dto.login(), dto.password());
        return ResponseEntity.ok(loginResponse);
    }

}
