package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;
import ru.kata.spring.boot_security.demo.security.UserDetail;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {                                                           // я добавила этот класс
    private final UsersRepository usersRepository;

    @Autowired
    public UserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByName(username);

        if (user.isEmpty()) {                                                                                            // проверяем, есть ли юзер с таким UserName
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetail(user.get());
    }
}
