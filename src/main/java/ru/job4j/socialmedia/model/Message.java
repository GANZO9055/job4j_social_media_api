package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "messages")
@NoArgsConstructor
@RequiredArgsConstructor
public class Message {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(name = "content")
    private String content;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_sender_id")
    private User senderUser;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "user_receiver_id")
    private User receiverUser;

}
