package ru.job4j.socialmedia.service.post;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmedia.dto.UserPostDto;
import ru.job4j.socialmedia.mappers.UserPostMapper;
import ru.job4j.socialmedia.model.File;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.model.User;
import ru.job4j.socialmedia.repository.post.PostRepository;
import ru.job4j.socialmedia.repository.user.UserRepository;
import ru.job4j.socialmedia.service.file.FileService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final FileService fileService;
    private final UserRepository userRepository;
    private final UserPostMapper userPostMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Post createNewPostWithFile(Post post, File file) {
        fileService.createFile(file);
        return postRepository.save(post);
    }

    @Transactional
    public Post createNewPostWithoutFile(Post post) {
        return postRepository.save(post);
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
        fileService.deleteFileById(id);
        return postRepository.deletePostById(id);
    }

    @Transactional
    public void deleteAllPosts() {
        postRepository.deleteAll();
        fileService.deleteAllFile();
    }

    @Transactional
    public Optional<Post> findPostById(Integer id) {
        return postRepository.findById(id);
    }

    public List<UserPostDto> getUserPostDto(List<Integer> idUsers) {

        List<Post> postList = postRepository.findAllByUserIdIn(idUsers);
        List<User> userList = userRepository.findAllById(idUsers);

        Map<Integer, List<Post>> posts = postList.stream()
                .collect(Collectors.groupingBy(post -> post.getUser().getId()));

        return userList.stream()
                .map(user -> {
                    List<Post> listPosts = posts.getOrDefault(user.getId(), List.of());
                    return userPostMapper.getDtoFromModel(user, listPosts);
                })
                .collect(Collectors.toList());
    }

}
