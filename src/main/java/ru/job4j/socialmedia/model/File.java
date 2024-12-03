package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "files")
@NoArgsConstructor
@RequiredArgsConstructor
public class File {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;
    @NonNull
    private String path;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
