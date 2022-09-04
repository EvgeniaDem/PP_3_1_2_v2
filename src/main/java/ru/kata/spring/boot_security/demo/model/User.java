package ru.kata.spring.boot_security.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data                                                                                                                    // для каждого поля создаст getters & setters + метод toString + equals() + hashCode()
@Entity
@NoArgsConstructor
@Table(name = "users")
// используем для сохранения lazy initialisation
@NamedEntityGraph(name = "graph.User.roles",
        attributeNodes = @NamedAttributeNode("roles"))
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

    // by default Lazy initialisation. We use @NamedEntityGraph to fetch both an entity (User) and an association (role) before the session is closed
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")},                                                               // показываем, с помощью какого столбца таблица user_role связана с таблицей user
            inverseJoinColumns = {@JoinColumn(name = "role_id")}                                                         // показываем, с помощью какого столбца таблица user_role связана с таблицей role
    )
    private List<Role> roles;
}