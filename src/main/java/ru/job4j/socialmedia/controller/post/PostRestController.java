package ru.job4j.socialmedia.controller.post;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.service.post.PostService;

@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostRestController {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<Post> get(@PathVariable("postId") Integer id) {
        return postService.findPostById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPostWithoutFile(@RequestBody Post post) {
        return ResponseEntity.ok(postService.createNewPostWithoutFile(post));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@RequestBody Post post) {
        postService.updatePost(post);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable("userId") Integer id) {
        postService.deletePost(id);
    }
}
