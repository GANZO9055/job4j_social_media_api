package ru.job4j.socialmedia.service.subscription;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmedia.model.Subscription;
import ru.job4j.socialmedia.repository.subscription.SubscriptionRepository;

@Service
@AllArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Transactional
    public void saveSubscription(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    @Transactional
    public void deleteSubscriptionById(Integer id) {
        subscriptionRepository.deleteById(id);
    }
}
