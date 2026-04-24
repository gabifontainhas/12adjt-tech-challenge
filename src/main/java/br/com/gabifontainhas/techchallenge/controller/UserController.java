package br.com.gabifontainhas.techchallenge.controller;

import br.com.gabifontainhas.techchallenge.dto.ApiErrorDoc;
import br.com.gabifontainhas.techchallenge.dto.UserDTO;
import br.com.gabifontainhas.techchallenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "User", description = "User Management: Operations related to any kind of users, like deleting, retrieving and changing password")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Retrieve all users", description = "Returns a paginated list of users.")
    @ApiResponse(responseCode = "200", description = "User data retrieved successfully.")
    @GetMapping
    public ResponseEntity<List<UserDTO.Response>> findAllUsers(@RequestParam int size, @RequestParam int offset) {
        var userList = userService.findAll(size, offset);
        var userDTOList = userList.stream()
                .map(UserDTO.Response::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userDTOList);
    }

    @Operation(summary = "Get user details by ID", description = "Fetches a single user's detailed information based on the provided unique identifier.")
    @ApiResponse(responseCode = "200", description = "User data retrieved successfully.")
    @ApiResponse(responseCode = "404", description = "User not found with the provided ID",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.UserNotFoundDTO.class)))
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO.Response> findUserById(@PathVariable Long id) {
        var userDTO = new UserDTO.Response(userService.findUserById(id));
        return ResponseEntity.ok(userDTO);
    }

    @Operation(summary = "Get user details by name", description = "Fetches a single user's detailed information based on the provided exact name.")
    @ApiResponse(responseCode = "200", description = "User data retrieved successfully.")
    @ApiResponse(responseCode = "404", description = "User not found with the provided name",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.UserNotFoundByNameDTO.class)))
    @GetMapping("/user")
    public ResponseEntity<List<UserDTO.Response>> findUserByName(@RequestParam String name) {
        var userList = userService.findUserByName(name);
        var userDTOList = userList.stream().map(UserDTO.Response::new).collect(Collectors.toList());
        return ResponseEntity.ok(userDTOList);
    }

    @Operation(summary = "Delete a user", description = "Permanently removes a customer from the system.")
    @ApiResponse(responseCode = "204", description = "User delete operation completed successfully, no content to return.")
    @ApiResponse(responseCode = "404", description = "User not found with the provided ID",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.UserNotFoundDTO.class)))
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/password")
    @ApiResponse(responseCode = "204", description = "Password updated successfully, no content to return.")
    @ApiResponse(responseCode = "422", description = "The current password provided is incorrect",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.IncorrectPasswordDTO.class)))
    @ApiResponse(responseCode = "404", description = "User not found with the provided ID",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ApiErrorDoc.UserNotFoundDTO.class)))
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody @Valid UserDTO.ChangePassword passwordDTO) {
        this.userService.updatePassword(id, passwordDTO.oldPassword(), passwordDTO.newPassword());
        return ResponseEntity.noContent().build();
    }


}
