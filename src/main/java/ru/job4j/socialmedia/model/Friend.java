package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "friends")
@NoArgsConstructor
@RequiredArgsConstructor
public class Friend {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private Boolean status;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requesterUser;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverUser;
}
