package ru.job4j.socialmedia.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.socialmedia.model.User;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String username);

    Boolean existsByName(String username);

    Boolean existsByEmail(String email);
}
