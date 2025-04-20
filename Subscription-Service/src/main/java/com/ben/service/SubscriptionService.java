package com.ben.service;

import com.ben.dto.UserDto;
import com.ben.entity.Subscription;
import com.ben.entity.SubscriptionPlans;
import com.ben.response.PaymentResponse;

public interface SubscriptionService {

    public SubscriptionPlans getAllSubscriptionsDetails();

    public Subscription addSubscription(Subscription subscription);

    public String createOrder(Long amount, UserDto userDto);

    public PaymentResponse cretePaymentLink(Long amount, UserDto userDto);
}
