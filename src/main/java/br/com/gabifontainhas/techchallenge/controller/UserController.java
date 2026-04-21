package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.UserDTO;
import br.com.gabifontainhas.techchallenge.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO.Response>> findAllUsers(@RequestParam int size, @RequestParam int offset) {
        var userList = userService.findAll(size, offset);
        var userDTOList = userList.stream()
                .map(UserDTO.Response::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO.Response> findUserById(@PathVariable Long id) {
        var userDTO = new UserDTO.Response(userService.findUserById(id));
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserDTO.Response>> findUserByName(@RequestParam String name) {
        var userList = userService.findUserByName(name);
        var userDTOList = userList.stream().map(UserDTO.Response::new).collect(Collectors.toList());
        return ResponseEntity.ok(userDTOList);
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid UserDTO.ChangePassword passwordDTO) {
        this.userService.updatePassword(id, passwordDTO.oldPassword(), passwordDTO.newPassword());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
