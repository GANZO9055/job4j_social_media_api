package ru.job4j.socialmedia.repository.friend;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Friend;

public interface FriendRepository extends CrudRepository<Friend, Integer> {
}
