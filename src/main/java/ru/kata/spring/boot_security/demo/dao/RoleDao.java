package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {
    Role getByName(String name);

    List<Role> getAll();

    void add(Role role);
}
