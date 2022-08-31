package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetail;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUserInfo(Model model) {                                                                            // получаем объект Authentification из потока (т.е. юзера, успешно прошедшего аутентификацию)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();                          // мы достаем его из потока методом getContext()
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();                                              // получаем Principal (данные пользователя) из объекта Authentification
        User user = userDetail.getUser();                                                                                // downcasting (UserDerail) - т.к. в UserDetails есть метод getUser
        model.addAttribute("user", userService.getUserById(user.getId()));
        System.out.println("User: " + userDetail.getUser());
        return "/user/show";
    }
}
