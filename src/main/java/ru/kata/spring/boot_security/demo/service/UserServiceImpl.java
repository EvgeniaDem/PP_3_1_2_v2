package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserById(Long id) {
        try {
            return userDao.getUserById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        try {
            userDao.saveUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public User updateUserById(Long id, User updatedUser) {
        return userDao.updateUserById(id, updatedUser);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }
}