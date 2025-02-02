package ru.job4j.socialmedia.repository.tape;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.job4j.socialmedia.model.Tape;
import ru.job4j.socialmedia.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TapeRepository extends JpaRepository<Tape, Integer> {

    List<Tape> findByUser(User user);

    List<Tape> findByCreatedPostGreaterThanEqualAndCreatedPostLessThanEqual(
            LocalDateTime startTime,
            LocalDateTime endTime
    );

    Page<Tape> findByOrderByCreatedPostDesc(Pageable pageable);
}
