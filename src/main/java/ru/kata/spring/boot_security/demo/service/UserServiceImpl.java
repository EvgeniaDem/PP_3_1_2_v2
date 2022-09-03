package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UsersRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {


    private final RoleRepository roleRepository;
    private final UsersRepository usersRepository;

    public UserServiceImpl(RoleRepository roleRepository, UsersRepository usersRepository) {
        this.roleRepository = roleRepository;
        this.usersRepository = usersRepository;
    }

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
        //тянем все роли из бд
        List<Role> allRoles = roleRepository.findAll();
        //создаем роль/роли конкретные для этого нового юзера
        Role userRole = allRoles.get(1);
        Role adminRole = allRoles.get(0);
        //создаем пустой список ролей для новго юзера
        List<Role> userRoles = new ArrayList<>();
        //в список ролей нового юзера добавляем все его роли
        userRoles.add(userRole);
        userRoles.add(adminRole);
        //сетим юзеру список ролей
        user.setRoles(userRoles);
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