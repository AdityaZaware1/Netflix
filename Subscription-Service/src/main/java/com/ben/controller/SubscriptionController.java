package com.ben.controller;

import com.ben.entity.Subscription;
import com.ben.enums.SubscriptionPlans;
import com.ben.response.PaymentResponse;
import com.ben.response.PlanResponse;
import com.ben.service.SubscriptionService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService service;

    @GetMapping("/plans")
    public List<PlanResponse> getAllPlans() {
        return Arrays.stream(SubscriptionPlans.values())
                .map(plan -> new PlanResponse(plan.getName(), plan.getPrice()))
                .collect(Collectors.toList());
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<PaymentResponse> createSubscriptionPlan(
            @PathVariable Long userId,
            @RequestBody Subscription subscription) throws RazorpayException {

        PaymentResponse paymentResponse = service.addSubscription(subscription, userId);

        return ResponseEntity.ok(paymentResponse);
    }

    @GetMapping("/update/{userId}/{paymentLinkId}")
    public ResponseEntity<Boolean> updatePaymentOrderStatus(
            @PathVariable Long userId,
            @RequestBody Subscription subscription,
            @PathVariable String paymentLinkId) throws RazorpayException {


        return ResponseEntity.ok(service.updateSubscription(subscription, userId, paymentLinkId));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Subscription> getSubscription(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSubscription(id));
    }

    @GetMapping("/get/{paymentLinkId}")
    public ResponseEntity<Subscription> getSubscriptionByPaymentLinkId(@PathVariable String paymentLinkId) {
        return ResponseEntity.ok(service.getSubscriptionByPaymentLinkId(paymentLinkId));
    }
}
