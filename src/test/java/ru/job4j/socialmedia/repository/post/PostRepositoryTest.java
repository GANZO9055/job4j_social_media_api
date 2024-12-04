package ru.job4j.socialmedia.repository.post;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.user.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void deleteAllPost() {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @AfterAll
    public void deleteAll() {
        postRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void whenSavePostThenFindPostById() {
        User user = new User("test1", "test1@mail.com", "test1");
        Post post = new Post("postTest1", "test1", user);

        userRepository.save(user);
        postRepository.save(post);

        var result = postRepository.findById(post.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(post);
    }

    @Test
    void whenSavePostsThenFindAllPost() {
        User user = new User("test1", "test1@mail.com", "test1");
        Post post1 = new Post("postTest1", "test1", user);
        Post post2 = new Post("postTest2", "test2", user);
        Post post3 = new Post("postTest3", "test3", user);

        userRepository.save(user);
        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);

        var result = (List<Post>) postRepository.findAll();

        assertThat(postRepository.count()).isEqualTo(3);
        assertThat(result).isEqualTo(List.of(post1, post2, post3));
    }
}