package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "/auth/login";
    }

    // метод для кастомного login
    @GetMapping("/logout")
    public String logoutPage(){
        return "/auth/logout";
    }

    // метод для регистрации нового пользователя (добавление в таблицу)
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "/auth/registration";
    }


}
