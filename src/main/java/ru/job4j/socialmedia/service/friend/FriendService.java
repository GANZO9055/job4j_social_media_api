package ru.job4j.socialmedia.service.friend;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.socialmedia.model.Friend;
import ru.job4j.socialmedia.model.Subscription;
import ru.job4j.socialmedia.repository.friend.FriendRepository;
import ru.job4j.socialmedia.service.subscription.SubscriptionService;

@Service
@AllArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final SubscriptionService subscriptionService;

    @Transactional
    public void createFriend(Friend friend, Subscription subscription) {
        friendRepository.save(friend);
        subscriptionService.saveSubscription(subscription);
    }

    @Transactional
    public void deleteFriendById(Integer id) {
        friendRepository.deleteById(id);
        subscriptionService.deleteSubscriptionById(id);
    }
}