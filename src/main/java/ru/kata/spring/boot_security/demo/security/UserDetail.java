package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class UserDetail implements UserDetails {                                                                         // это класс-обертка над классом User, позволяет работать не напрямую с User.
    private final User user;                                                                                             // Но этот класс предоставляет всю инфу о User

    public UserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {                                                     //Spring Security не различает ROLE и Authorities

        Set<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

 /*   @Override
    public String getUsername() {
        return this.user.getName();             // в прошлой задаче был этот метод, т.к. был вход по name
    }*/

    @Override
    public String getUsername() {
        return this.user.getPassword();             // заменила getName на getPassword
    }

    @Override
    public boolean isAccountNonExpired() {                                                                               // проверяем, не просрочен ли аккаунт
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {                                                                                // проверяем, не заблокирован ли аккаунт
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {                                                                           // проверяем, не просрочен ли пароль
        return true;
    }

    @Override
    public boolean isEnabled() {                                                                                         // проверяем, включен ли аккаунт
        return true;
    }

    public User getUser() {                                                                                              // добавила вручную метод, позволяющий получать доступ к аутентифицированному юзеру, и всем его полям
        return this.user;
    }
}
