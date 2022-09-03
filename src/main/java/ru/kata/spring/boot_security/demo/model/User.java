package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data                                                                                                                    // для каждого поля создаст getters & setters + метод toString + equals() + hashCode()
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String surname;

    @Column
    private String password;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)                                                  // указываем FetchType.EAGER, т.к. по умолчанию будет LAZY и сессия закроется до того,как мы прложим в контейнер Set <Role>
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},                                                               // показываем, с помощью какого столбца таблица user_role связана с таблицей user
            inverseJoinColumns = {@JoinColumn(name = "role_id")}                                                         // показываем, с помощью какого столбца таблица user_role связана с таблицей role
    )
    private List<Role> roles;                                                                                             // означает, что у каждого юзера может быть Set ролей
}