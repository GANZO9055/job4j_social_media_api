package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "posts")
@RequiredArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String title;
    @NonNull
    private String description;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
