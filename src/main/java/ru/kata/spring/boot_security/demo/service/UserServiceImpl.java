package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = usersRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UsernameNotFoundException("User not found by ID: " + id);
        }
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        usersRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUserById(Long id, User updatedUser) {
        Optional<User> userContainer = usersRepository.findById(id);
        if (userContainer.isPresent()) {
            User user = userContainer.get();
            user.setName(updatedUser.getName());
            user.setSurname(updatedUser.getSurname());
            return usersRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User not found by ID: " + id);
        }
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        usersRepository.deleteById(id);
    }
}