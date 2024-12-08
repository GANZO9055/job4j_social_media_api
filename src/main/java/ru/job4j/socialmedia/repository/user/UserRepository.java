package ru.job4j.socialmedia.repository.user;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = """
            SELECT * FROM users
            WHERE email = :email AND password = :password
            """, nativeQuery = true
    )
    Optional<User> findByEmailAndPassword(
            @Param("email") String email,
            @Param("password") String password
    );

    @Query(value = """
            SELECT * FROM subscriptions
            WHERE user_target_id = :id
            """, nativeQuery = true
    )
    List<User> findByIdsubscriptions(@Param("id") Integer id);

    @Query(value = """
            SELECT * FROM friends
            WHERE requester_id = :id
            """, nativeQuery = true
    )
    List<User> findByIdfriends(@Param("id") Integer id);

    @Query(value = """
            SELECT p.user_id, p.title, p.description
            FROM posts AS p
            JOIN tapes AS t ON p.id = t.post_id
            JOIN subscriptions AS s ON s.user_subscriber_id = p.user_id
            WHERE s.user_target_id = :id
            ORDER BY t.created_post DESC
            """, nativeQuery = true
    )
    List<Post> findByIdposts(@Param("id") Integer id, Pageable pageable);
}
