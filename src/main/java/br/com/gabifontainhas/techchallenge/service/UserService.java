package br.com.gabifontainhas.techchallenge.service;

import br.com.gabifontainhas.techchallenge.dto.LoginDTO;
import br.com.gabifontainhas.techchallenge.dto.UserDTO;
import br.com.gabifontainhas.techchallenge.entity.User;
import br.com.gabifontainhas.techchallenge.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(int size, int offset) {
        return userRepository.findAllUsers(size, offset);
    }

    public User findUserById(Long id) {
        return userRepository.findUserById(id).get();
    }

    public List<User> findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    public void updatePassword(Long id, String oldPassword, String newPassword) {
        var user = userRepository.findUserById(id).get();
        user.updatePassword(newPassword, oldPassword);
        userRepository.save(user);
    }

    public LoginDTO.Response loginUser(@NotBlank @NotNull String login, @NotBlank @NotNull String password) {
        if(userRepository.existsUserByLoginAndPassword(login, password)) {
            return new LoginDTO.Response("token_placeholder", login);
        }
        else {
            throw new RuntimeException();
        }
    }
}

