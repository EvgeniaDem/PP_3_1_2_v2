package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
// для каждого поля создаст getters & setters + метод toString + equals() + hashCode()
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private int age;

    @Column(name = "email", unique = true)
    private String email;

    @Column
    private String password;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    // указываем FetchType.EAGER, т.к. по умолчанию будет LAZY и сессия закроется до того,как мы прложим в контейнер Set <Role>
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},                                                               // показываем, с помощью какого столбца таблица user_role связана с таблицей user
            inverseJoinColumns = {@JoinColumn(name = "role_id")}                                                         // показываем, с помощью какого столбца таблица user_role связана с таблицей role
    )
    private List<Role> roles;                                                                                             // означает, что у каждого юзера может быть Set ролей

    @Transient
    public boolean isAdmin() {
        return roles.stream()
                .map(Role::getRole)
                .anyMatch(role -> role.equals("ADMIN"));
    }

    @Transient
    public boolean isUser() {
        return roles.stream()
                .map(Role::getRole)
                .anyMatch(role -> role.equals("USER"));
    }

    @Transient
    public List<String> getRoleNames() {
        return roles == null ? List.of() : roles.stream()
                .map(Role::getRole)
                .collect(Collectors.toList());
    }
}