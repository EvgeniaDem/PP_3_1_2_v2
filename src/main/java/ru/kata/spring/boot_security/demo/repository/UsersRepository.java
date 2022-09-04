package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
    @EntityGraph(value = "graph.User.roles")
    Optional<User> findByName(String name);                                                                              // передаем имя юзера, и Data JPA возвращает пользователя по этому имени из БД
}                                                                                                                        // Optional означает, что такой человек может быть найден, а может и не быть найден
                                                                                                                         // Важно: метод findByName = findByНазвание поля!!! Строго название поля (у меня поле name)