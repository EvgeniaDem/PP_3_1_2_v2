package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * передаем email в качестве username, и Data JPA возвращает пользователя по этому имени из БД
     * Optional означает, что такой человек может быть найден, а может и не быть найден
     * Важно: метод findByEmail = findByНазвание поля!!! Строго название поля (у меня поле email)
     * @param email - эл. почта пользователя
     * @return - возвращаем пользователя, если он существует
     */
    Optional<User> findByEmail(String email);
}
