package ru.job4j.socialmedia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.job4j.socialmedia.dto.PostDto;
import ru.job4j.socialmedia.model.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(source = "id", target = "postId")
    @Mapping(source = "title", target = "title")
    PostDto getDtoFromModelPost(Post post);
}
