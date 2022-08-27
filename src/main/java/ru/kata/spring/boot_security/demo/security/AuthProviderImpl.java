package ru.kata.spring.boot_security.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.service.UserDetailService;

import java.util.Collections;

// этот класс добавила я. Здесь прописываем логику аутентификации
// здесь: сравниваем пароль, введенный с формы с паролем, указанным в БД + то же самое с User name

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserDetailService userDetailService;

    @Autowired
    public AuthProviderImpl(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {    // в параметрах метода лежат credentials (логин и пароль), а возвращаемое значение -Principal
        String username = authentication.getName();    // получили имя пользователя, теперь надо найти такого пользователя в таблице
        UserDetails userDetail = userDetailService.loadUserByUsername(username);

        String password = authentication.getCredentials().toString(); // получаем пароль через getCredentials() и мы получаем его в виде строки String
        if (!password.equals(userDetail.getPassword())) {         // сравниваем полученный пароль с тем, что у нас в UserDetail
            throw new BadCredentialsException("Incorrect password");
        }
        return new UsernamePasswordAuthenticationToken(userDetail, password, Collections.emptyList()); // возвращаем объект Principal (он содержит данные об аутентифицированном юзере) с его паролем и списком прав
    }

    @Override
    public boolean supports(Class<?> authentication) {    // технический метод, показывает Спрингу для какого объекта работает Authentification
        return true;
    }
}
