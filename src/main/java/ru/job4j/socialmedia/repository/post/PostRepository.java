package ru.job4j.socialmedia.repository.post;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {
}