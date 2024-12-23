package ru.job4j.socialmedia.controller.post;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
        postService.createNewPostWithoutFile(post);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<Void> updatePost(@RequestBody Post post) {
        if (postService.updatePost(post) > 0) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletePost(@PathVariable("userId") Integer id) {
        if (postService.deletePost(id) > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
