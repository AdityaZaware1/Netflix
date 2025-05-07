package com.ben.Subscription.Service.controller;


import com.ben.Subscription.Service.entity.Payment;
import com.ben.Subscription.Service.reposnse.PaymentResponse;
import com.ben.Subscription.Service.request.PaymentRequest;
import com.ben.Subscription.Service.service.PaymentService;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create/{id}")
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestBody PaymentRequest paymentRequest,
            @PathVariable Long id) {
        return ResponseEntity.ok(paymentService.cretePayment(paymentRequest, id));
    }


    @PostMapping("/make")
    public ResponseEntity<PaymentResponse> createRandomPayment(
            @RequestBody Payment paymentRequest) {
        return ResponseEntity.ok(paymentService.createRandomPayment(paymentRequest));
    }

    @GetMapping("/status/{paymentLinkId}")
    public ResponseEntity<Boolean> updateStatus(@PathVariable String paymentLinkId) throws RazorpayException {
        Boolean status = paymentService.updatePaymentStatus(paymentLinkId);
        return ResponseEntity.ok(status);
    }

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello");
    }

}
