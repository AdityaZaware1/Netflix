package com.ben.User.Service.kafka;

import com.ben.User.Service.entity.User;
import com.ben.User.Service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final UserService userService;

    @KafkaListener(topics = "payment-topic", groupId = "subscription-group")
    public void enableSubscription(String userId) {

        Long id = Long.parseLong(userId);

        User user = userService.getUserById(id);
        user.setSubscribed(true);
        userService.updateUser(user);

    }


}
