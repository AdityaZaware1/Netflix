package com.ben.Subscription.Service.service.impl;

import com.ben.Subscription.Service.entity.Payment;
import com.ben.Subscription.Service.enums.PaymentOrderStatus;
import com.ben.Subscription.Service.repo.PaymentRepo;
import com.ben.Subscription.Service.reposnse.PaymentResponse;
import com.ben.Subscription.Service.request.PaymentRequest;
import com.ben.Subscription.Service.service.PaymentService;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepo paymentRepo;

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Override
    public Payment cretePayment(PaymentRequest paymentRequest) {

        Payment payment = new Payment();

        payment.setUserId(paymentRequest.getUserId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setUserName(paymentRequest.getUserName());
        payment.setStatus(PaymentOrderStatus.PENDING);

        return paymentRepo.save(payment);
    }

    @Override
    public PaymentResponse createRandomPayment(PaymentRequest paymentRequest) {
        try {
            RazorpayClient razorpayClient = new RazorpayClient(apiKey, apiSecret);

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", paymentRequest.getAmount());
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", false);
            paymentLinkRequest.put("description", "Payment for Service");

            JSONObject customer = new JSONObject();

            String userName = "ben";
            String email = "gYt6P@example.com";
            customer.put("name", userName);
            customer.put("email", email);

            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("email", true);
            notify.put("sms", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("callback_url", "http://localhost:8085/api/payment?order_id=" + paymentRequest.getUserId());
            paymentLinkRequest.put("callback_method", "get");

            PaymentLink paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);

            // ✅ Log and return payment link
            System.out.println("Payment Link Created: " + paymentLink.toString());

            String shortUrl = paymentLink.get("short_url");

            return new PaymentResponse(shortUrl);
        }

        catch (RazorpayException e) {
            System.err.println("Error creating Razorpay Payment Link: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Payment link creation failed: " + e.getMessage());
        }
    }

    @Override
    public Payment getPaymentById(Long paymentId) throws Exception {
        return paymentRepo.findById(paymentId)
                .orElseThrow(() -> new Exception("Payment order not found"));
    }
}
