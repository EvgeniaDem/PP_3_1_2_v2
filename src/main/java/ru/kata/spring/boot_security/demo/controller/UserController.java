package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.security.UserDetail;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {     // получаем объект Authentification из потока (т.е. юзера, успешно прошедшего аутентификацию)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();    // т.е. мы доставем его из поктока методом getContext()
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();  // получаем Principal (данные пользователя) из объекта Authentification
        System.out.println("User: " + userDetail.getUser());                            // downcasting (UserDerail) - т.к. в UserDetails есть метод getUser
        return "hello";
    }
}
