package ru.job4j.socialmedia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.socialmedia.dto.UserPostDto;
import ru.job4j.socialmedia.model.Post;
import ru.job4j.socialmedia.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPostMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "username")
    @Mapping(source = "posts", target = "posts")
    UserPostDto getDtoFromModel(User user, List<Post> posts);
}
