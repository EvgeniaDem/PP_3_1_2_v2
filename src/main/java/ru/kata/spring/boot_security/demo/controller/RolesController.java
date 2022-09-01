package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class RolesController {
    //private static final List<Role> ROLES = Arrays.asList(new Role("ADMIN"), new Role("USER"));  // уточнить, что справа от =

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

/*    @RequestMapping("/init")
    public ModelAndView init() {
        List<Role> roles = roleService.getAll();
        if (roles == null || roles.isEmpty()) {
            ROLES.forEach(roleService::add);
        }
        User user = userService.getUserByEmail("ADMIN");
        if (user == null) {
            user = new User("ADMIN", "ADMIN", Collections.singletonList(new Role("ADMIN")));
            userService.saveUser(user);
        }
        return new ModelAndView("/login");
    }*/
}
