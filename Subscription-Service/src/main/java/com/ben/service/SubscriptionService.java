package com.ben.service;

import com.ben.dto.UserDto;
import com.ben.entity.Subscription;
import com.ben.response.PaymentResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

public interface SubscriptionService {



    public PaymentResponse addSubscription(Subscription subscription, Long userId) throws RazorpayException;

    public PaymentLink cretePaymentLink(Long amount, UserDto userDto, Long order) throws RazorpayException;

    public Boolean updateSubscription(Subscription subscription, Long userId, String paymentLinkId) throws RazorpayException;

    Subscription getSubscription(Long id);

    Subscription getSubscriptionByPaymentLinkId(String paymentLinkId);
}
