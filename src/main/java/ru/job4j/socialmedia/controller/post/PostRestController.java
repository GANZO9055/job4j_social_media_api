package ru.job4j.socialmedia.controller.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.job4j.socialmedia.dto.UserPostDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.service.post.PostService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "PostRestController", description = "PostRestController management APIs")
@Validated
@RestController
@RequestMapping("/api/post")
@AllArgsConstructor
public class PostRestController {

    private final PostService postService;

    @Operation(
            summary = "Retrieve a Post by postId",
            tags = {"Post", "get"}
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Post.class), mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
            }
    )
    @GetMapping("/{postId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Post> get(@PathVariable("postId")
                                    @NotNull
                                    @Min(value = 1, message = "номер поста должен быть 1 или более")
                                    Integer id) {
        return postService.findPostById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Create Post",
            tags = {"Post", "create"}
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Post.class), mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
            }
    )
    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Post> createPostWithoutFile(@Valid @RequestBody Post post) {
        postService.createNewPostWithoutFile(post);
        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @Operation(
            summary = "Update Post",
            tags = {"Post", "update"}
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
                    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
            }
    )
    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> updatePost(@RequestBody Post post) {
        if (postService.updatePost(post) > 0) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Delete Post by postId",
            tags = {"Post", "delete"}
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
                    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
            }
    )
    @DeleteMapping("/{postId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Void> deletePost(@PathVariable("postId")
                                           @NotNull
                                           @Min(value = 1, message = "номер поста должен быть 1 или более")
                                           Integer id) {
        if (postService.deletePost(id) > 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
            summary = "Getting a list of user posts",
            description = "Getting a list of user posts, using ID users",
            tags = {"Post", "get"}
    )
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = UserPostDto.class), mediaType = "application/json")}),
                    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema())})
            }
    )
    @GetMapping("/posts")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<UserPostDto> getListPostsByUserId(@RequestParam List<Integer> idUsers) {
        return postService.getUserPostDto(idUsers);
    }
}
