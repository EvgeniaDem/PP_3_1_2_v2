package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetail;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.sql.SQLException;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();                          // т.е. мы доставем его из поктока методом getContext()
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();                                              // получаем Principal (данные пользователя) из объекта Authentification
        User user = userDetail.getUser();
        model.addAttribute("user", userService.getUserById(user.getId()));
        System.out.println("User: " + userDetail.getUser());                                                             // downcasting (UserDerail) - т.к. в UserDetails есть метод getUser
        return "/user/show";
    }

/*    @GetMapping("/edit")
    public String showEditForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();    // т.е. мы доставем его из поктока методом getContext()
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();                        // получаем Principal (данные пользователя) из объекта Authentification
        User user = userDetail.getUser();
        model.addAttribute("user", userService.getUserById(user.getId()));
        return "/user/edit";
    }

    @PatchMapping
    public String editUserById(@ModelAttribute("user") User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();    // т.е. мы доставем его из поктока методом getContext()
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();                        // получаем Principal (данные пользователя) из объекта Authentification
        User currentUser = userDetail.getUser();
        userService.updateUserById(currentUser.getId(), user);
        return "redirect:/user";
    }*/

/*    @GetMapping("/new")
    public String createNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/user/new";
    }

    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/user";
    }*/
/*    @GetMapping("/{id}/edit")
    public String showEditForm(Model model, @PathVariable("id") Long id)  {
        model.addAttribute("user", userService.getUserById(id));
        return "user/edit";
    }*/

/*    @PatchMapping("/{id}")
    public String editUserById(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUserById(id, user);
        return "redirect:/user";
    }*//*    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/user";
    }*/


/*    @GetMapping("/login")
    //добавила метод для аутентификции пользователя
    public String loginPage() {
        return "auth/login";
    }*/

}
