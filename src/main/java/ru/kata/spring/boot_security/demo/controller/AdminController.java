package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // работает
    @GetMapping
    public String adminPage() {
        return "/admin/index";
    }

    // работает
    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "/admin/user";
    }

    // работает
    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));                                             // "user" - это ключ
        return "/admin/show";
    }

    // работает
    @GetMapping("/new")
    public String createNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "/admin/new";
    }

    // работает, но возвращает не на ту страницу (всегда на админа)
    @PostMapping()
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/all";
    }

    // Работает
    @GetMapping("/{id}/edit")
    public String showEditForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "/admin/edit";
    }

    // не работает (работает только когда есть метот в UserController)
    @PatchMapping("/{id}")
    public String editUserById(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUserById(id, user);
        return "redirect:/admin/all";
    }

    // работает, но возвращает не на ту страницу (всегда на админа) + админа удалить не могу!
    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/all";
    }
}
