package ru.job4j.socialmedia.repository.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.post.PostRepository;
import ru.job4j.socialmedia.repository.subscription.SubscriptionRepository;
import ru.job4j.socialmedia.repository.tape.TapeRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TapeRepository tapeRepository;

    @BeforeEach
    public void deleteAllUser() {
        tapeRepository.deleteAll();
        postRepository.deleteAll();
        subscriptionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void whenSaveUserThenFindUserById() {
        User user = new User();
        user.setName("test1");
        user.setEmail("test1@mail.com");
        user.setPassword("test1");

        userRepository.save(user);
        var result = userRepository.findById(user.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(user);
    }

    @Test
    public void whenSaveUsersThenFindAll() {
        User user1 = new User("test1", "test1@mail.com", "test1");
        User user2 = new User("test2", "test2@mail.com", "test2");

        userRepository.save(user1);
        userRepository.save(user2);

        var result = (List<User>) userRepository.findAll();

        assertThat(userRepository.count()).isEqualTo(2);
        assertThat(result).isEqualTo(List.of(user1, user2));
    }
}