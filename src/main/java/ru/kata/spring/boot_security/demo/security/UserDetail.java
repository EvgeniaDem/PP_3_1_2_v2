package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.Collections;

public class UserDetail implements UserDetails { // это - класс-обертка над классом User, позволяет работать не напрямую с User. Но этот класс предоставляет всю инфу о User
    private final User user;

    public UserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {                                         //Spring Security не различает ROLE и Authorities
        return null;
        //return Collections.singletonList(new SimpleGrantedAuthority(user.getRoles()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {               // проверяем, не просрочен ли аккаунт
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {                // проверяем, не заблокирован ли аккаунт
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {           // проверяем, не просрочен ли пароль
        return true;
    }

    @Override
    public boolean isEnabled() {                         // проверяем, включен ли аккаунт
        return true;
    }

    public User getUser(){       // добавили вручную метод, позволяющий получать доступ к аутентифицированному юзеру, и всем его полям
        return this.user;
    }
}
