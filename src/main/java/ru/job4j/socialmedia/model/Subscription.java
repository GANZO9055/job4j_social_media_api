package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "subscriptions")
@NoArgsConstructor
@RequiredArgsConstructor
public class Subscription {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_subscriber_id")
    private User subscriberUser;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_target_id")
    private User targetUser;
}
