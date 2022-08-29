package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(Long id) throws SQLException;

    void saveUser(User user) throws SQLException;

    User updateUserById(Long id, User updatedUser);

    void deleteUserById(Long id);
}
