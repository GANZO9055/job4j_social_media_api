package ru.job4j.socialmedia.repository.subscription;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.socialmedia.model.Subscription;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
}