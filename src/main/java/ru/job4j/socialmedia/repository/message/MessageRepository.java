package ru.job4j.socialmedia.repository.message;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}
