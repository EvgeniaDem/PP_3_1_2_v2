package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    void saveUser(User user);

    User updateUserById(Long id, User updatedUser);

    void deleteUserById(Long id);
}
