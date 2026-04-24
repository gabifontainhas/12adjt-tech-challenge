package br.com.gabifontainhas.techchallenge.service;

import br.com.gabifontainhas.techchallenge.dto.LoginDTO;
import br.com.gabifontainhas.techchallenge.entity.User;
import br.com.gabifontainhas.techchallenge.exception.InvalidCredentialsException;
import br.com.gabifontainhas.techchallenge.exception.UserNotFoundException;
import br.com.gabifontainhas.techchallenge.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> findUserByName(String name) {
        var userList = userRepository.findUserByName(name);
        if (userList.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }
        else {
            return userList;
        }
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Could not delete: User with ID " + id + " not found");
        }
        this.userRepository.deleteById(id);
    }

    public void updatePassword(Long id, String oldPassword, String newPassword) {
        var user = userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user.updatePassword(newPassword, oldPassword);
        userRepository.save(user);
    }

    public LoginDTO.Response loginUser(String login, String password) {
        if (userRepository.existsUserByLoginAndPassword(login, password)) {
            return new LoginDTO.Response(UUID.randomUUID().toString(), login);
        } else {
            throw new InvalidCredentialsException("Authentication Failed");
        }
    }
}

