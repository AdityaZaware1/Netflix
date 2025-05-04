package com.ben.Subscription.Service.controller;

import com.ben.Subscription.Service.entity.Payment;
import com.ben.Subscription.Service.reposnse.PaymentResponse;
import com.ben.Subscription.Service.request.PaymentRequest;
import com.ben.Subscription.Service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<Payment> createPayment(
            @RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.cretePayment(paymentRequest));
    }


    @PostMapping("/make")
    public ResponseEntity<PaymentResponse> createRandomPayment(
            @RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.createRandomPayment(paymentRequest));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }
}
