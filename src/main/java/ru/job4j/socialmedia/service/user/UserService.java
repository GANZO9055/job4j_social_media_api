package ru.job4j.socialmedia.service.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Validated
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public User createUser(@Valid User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public List<User> findByIdFriends(Integer id) {
        return userRepository.findByIdFriends(id);
    }

    @Transactional
    public List<User> findByIdSubscriptions(Integer id) {
        return userRepository.findByIdSubscriptions(id);
    }

    @Transactional
    public List<Post> findByIdPosts(Integer id, Pageable pageable) {
        return userRepository.findByIdPosts(id, pageable);
    }

    @Transactional
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
}
