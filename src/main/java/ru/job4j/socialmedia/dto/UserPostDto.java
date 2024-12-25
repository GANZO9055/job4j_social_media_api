package ru.job4j.socialmedia.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.job4j.socialmedia.model.Post;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class UserPostDto {

    private Integer userId;
    private String username;
    private List<Post> posts;

}
