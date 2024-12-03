package ru.job4j.socialmedia.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requesterUser;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiverUser;
}
