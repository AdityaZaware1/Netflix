package com.ben.service.impl;

import com.ben.dto.UserDto;
import com.ben.entity.Subscription;
import com.ben.enums.PaymentOrderStatus;
import com.ben.external.UserService;
import com.ben.kafka.KafkaProducer;
import com.ben.repo.SubscriptionRepo;
import com.ben.response.PaymentResponse;
import com.ben.service.SubscriptionService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepo subscriptionRepo;
    private final KafkaProducer kafkaProducer;
    private final UserService userService;

    @Value("${stripe.api.key}")
    private String apiKey;

    @Value("${stripe.api.secret}")
    private String secretKey;


    @Override
    public PaymentResponse addSubscription(Subscription subscription, Long userId) throws RazorpayException {

        UserDto userDto = userService.getUserById(userId);

        Long amount = subscription.getPrice();

        Subscription subscription1 = new Subscription();

        subscription1.setPrice(amount);
        subscription1.setPlanType(subscription.getPlanType());

        subscription1.setUserId(userId);
        subscription1.setStartDate(subscription.getStartDate());

        if (subscription.getPrice() == 199) {

            LocalDateTime now = LocalDateTime.now();
            LocalDateTime after30Days = now.plusDays(30);
            subscription1.setEndDate(after30Days);
        }
        else if(subscription.getPrice() == 899) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime after180Days = now.plusDays(180);
            subscription1.setEndDate(after180Days);
        }
        else {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime after360Days = now.plusDays(360);
            subscription1.setEndDate(after360Days);
        }

        Subscription save = subscriptionRepo.save(subscription1);

        PaymentResponse paymentResponse = new PaymentResponse();

        PaymentLink paymentLink = cretePaymentLink(amount, userDto, save.getId());

        String paymentLinkUrl = paymentLink.get("short_url");
        String paymentUrlId = paymentLink.get("id");

        paymentResponse.setPaymentUrl(paymentLinkUrl);
        paymentResponse.setGetPaymentLinkId(paymentUrlId);

        save.setPaymentLinkId(paymentUrlId);
        subscriptionRepo.save(save);

        return paymentResponse;
    }



    @Override
    public PaymentLink cretePaymentLink(Long amount, UserDto userDto, Long order ) throws RazorpayException {

        Long Amount = amount * 100;

        RazorpayClient razorpayClient = new RazorpayClient(apiKey, secretKey);

        JSONObject paymentLinkRequest = new JSONObject();

        paymentLinkRequest.put("amount", Amount);
        paymentLinkRequest.put("currency", "INR");

        JSONObject customer = new JSONObject();
        customer.put("name", userDto.getUsername());
        customer.put("email", userDto.getEmail());
        paymentLinkRequest.put("customer", customer);

        JSONObject notify = new JSONObject();
        notify.put("email", userDto.getEmail());
        paymentLinkRequest.put("notify", notify);


        paymentLinkRequest.put("callback_url", "http://localhost:8086/api/payment/success"+order);
        paymentLinkRequest.put("callback_method", "get");

        PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

        return paymentLink;
    }

    @Override
    public Boolean updateSubscription(Subscription subscription, Long userId, String paymentLinkId) throws RazorpayException {

        if(subscription.getPaymentOrderStatus().equals(PaymentOrderStatus.PENDING)) {

            RazorpayClient razorpayClient = new RazorpayClient(apiKey, secretKey);

            Payment payment = razorpayClient.payments.fetch(paymentLinkId);
            Integer amount = payment.get("amount");
            Integer status = payment.get("status");

            if(status.equals("captured")) {

                // kafka produce

                kafkaProducer.send(userId.toString());
                subscription.setPaymentOrderStatus(PaymentOrderStatus.COMPLETED);
                subscriptionRepo.save(subscription);

                return true;
            }
        }

        return false;
    }

    @Override
    public Subscription getSubscription(Long id) {
        Subscription subscription = subscriptionRepo.findById(id).get();

        if (subscription == null) {
            throw new RuntimeException("Subscription not found");
        }

        return subscription;
    }

    @Override
    public Subscription getSubscriptionByPaymentLinkId(String paymentLinkId) {
        Subscription subscription = subscriptionRepo.getSubscriptionByPaymentLinkId(paymentLinkId);

        if (subscription == null) {
            throw new RuntimeException("Subscription not found");
        }

        return subscription;
    }
}
