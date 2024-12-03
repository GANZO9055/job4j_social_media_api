package ru.job4j.socialmedia.repository.message;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmedia.model.Message;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.friend.FriendRepository;
import ru.job4j.socialmedia.repository.post.PostRepository;
import ru.job4j.socialmedia.repository.tape.TapeRepository;
import ru.job4j.socialmedia.repository.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MessageRepositoryTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TapeRepository tapeRepository;
    @Autowired
    private FriendRepository friendRepository;

    @BeforeEach
    public void deleteAllSubscription() {
        friendRepository.deleteAll();
        tapeRepository.deleteAll();
        postRepository.deleteAll();
        messageRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void whenSaveMessageThenFindMessageById() {
        User user1 = new User("test1", "test1@mail.com", "test1");
        User user2 = new User("test2", "test2@mail.com", "test2");
        Message message = new Message("test1", user1, user2);

        userRepository.saveAll(List.of(user1, user2));
        messageRepository.save(message);

        var result = messageRepository.findById(message.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(message);
    }

    @Test
    void whenSaveMessagesThenFindAllMessages() {
        User user1 = new User("test1", "test1@mail.com", "test1");
        User user2 = new User("test2", "test2@mail.com", "test2");
        User user3 = new User("test3", "test3@mail.com", "test3");
        Message message1 = new Message("test1", user1, user2);
        Message message2 = new Message("test2", user2, user3);

        userRepository.saveAll(List.of(user1, user2, user3));
        messageRepository.saveAll(List.of(message1, message2));

        var result = (List<Message>) messageRepository.findAll();
        assertThat(messageRepository.count()).isEqualTo(2);
        assertThat(result).isEqualTo(List.of(message1, message2));
    }

}