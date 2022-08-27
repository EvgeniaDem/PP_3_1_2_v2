package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data                           // для каждого поля создаст getters & setters + метод toString + equals() + hashCode()
@Entity                         // означает, что данный клас является сущностью (в отличии от joint-таблицы!)
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},       // показываем, с помощью какого столбца таблица user_role связана с таблицей user
            inverseJoinColumns = {@JoinColumn(name = "role_id")}  // показываем, с помощью какого столбца таблица user_role связана с таблицей role
    )
    private List<Role> roles;                                   // означает, что у каждого юзера может быть List ролей

    public void addRoleToUser(Role role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }
}