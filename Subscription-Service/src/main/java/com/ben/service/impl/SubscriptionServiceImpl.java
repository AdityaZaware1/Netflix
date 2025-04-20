package com.ben.service.impl;

import com.ben.dto.UserDto;
import com.ben.entity.Subscription;
import com.ben.entity.SubscriptionPlans;
import com.ben.kafka.KafkaProducer;
import com.ben.repo.SubscriptionRepo;
import com.ben.response.PaymentResponse;
import com.ben.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepo subscriptionRepo;
    private final KafkaProducer kafkaProducer;

    @Value("${stripe.api.key}")
    private String apiKey;

    @Value("${stripe.api.secret}")
    private String secretKey;

    @Override
    public SubscriptionPlans getAllSubscriptionsDetails() {
        SubscriptionPlans subscriptionPlans = new SubscriptionPlans();

        return subscriptionPlans;
    }

    @Override
    public Subscription addSubscription(Subscription subscription) {



        return null;
    }

    @Override
    public String createOrder(Long amount, UserDto userDto) {
        return "";
    }

    @Override
    public PaymentResponse cretePaymentLink(Long amount, UserDto userDto) {
        return null;
    }
}
