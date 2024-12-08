package ru.job4j.socialmedia.repository.post;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.socialmedia.model.Post;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE posts
            SET title = :title1, description = :description2
            WHERE id = :id3
            """
    )
    int updateTitleAndDescriptionById(
            @Param("title1") String title,
            @Param("description2") String description,
            @Param("id3") Integer id
    );

    @Modifying(clearAutomatically = true)
    @Query("""
            DELETE FROM posts
            WHERE id = :id
            """
    )
    int deletePostById(@Param("id") Integer id);

    @Modifying(clearAutomatically = true)
    @Query(
            value = """
            DELETE FROM files
            WHERE post_id = :id
            """, nativeQuery = true
    )
    int deleteFileById(@Param("id") Integer id);
}
