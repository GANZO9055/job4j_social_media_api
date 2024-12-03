package ru.job4j.socialmedia.repository.subscription;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmedia.model.Subscription;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.friend.FriendRepository;
import ru.job4j.socialmedia.repository.post.PostRepository;
import ru.job4j.socialmedia.repository.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SubscriptionRepositoryTest {

    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void deleteAllSubscription() {
        postRepository.deleteAll();
        subscriptionRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void whenSaveSubscriptionThenFindSubscriptionById() {
        User user1 = new User("test1", "test1@mail.com", "test1");
        User user2 = new User("test2", "test2@mail.com", "test2");
        Subscription subscription = new Subscription(user1, user2);

        userRepository.saveAll(List.of(user1, user2));
        subscriptionRepository.save(subscription);

        var result = subscriptionRepository.findById(subscription.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(subscription);
    }

    @Test
    void whenSaveSubscriptionsThenFindAllSubscriptions() {
        User user1 = new User("test1", "test1@mail.com", "test1");
        User user2 = new User("test2", "test2@mail.com", "test2");
        User user3 = new User("test3", "test3@mail.com", "test3");
        Subscription subscription1 = new Subscription(user1, user2);
        Subscription subscription2 = new Subscription(user2, user3);

        userRepository.saveAll(List.of(user1, user2, user3));
        subscriptionRepository.saveAll(List.of(subscription1, subscription2));

        var result = (List<Subscription>) subscriptionRepository.findAll();
        assertThat(subscriptionRepository.count()).isEqualTo(2);
        assertThat(result).isEqualTo(List.of(subscription1, subscription2));
    }
}