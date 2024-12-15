package ru.job4j.socialmedia.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.repository.post.PostRepository;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public void createNewPost(Post post) {
        postRepository.save(post);
    }

    @Transactional
    public Integer updatePost(Post newPost) {
        return postRepository.updateTitleAndDescriptionById(
                newPost.getTitle(),
                newPost.getDescription(),
                newPost.getId()
        );
    }

    @Transactional
    public Integer deletePost(Integer id) {
        return postRepository.deletePostById(id);
    }

    @Transactional
    public void deleteAllPosts() {
        postRepository.deleteAll();
    }
}
