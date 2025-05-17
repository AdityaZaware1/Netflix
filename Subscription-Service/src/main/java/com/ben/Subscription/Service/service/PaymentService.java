package com.ben.Subscription.Service.service;


import com.ben.Subscription.Service.entity.Payment;
import com.ben.Subscription.Service.request.PaymentRequest;
import com.ben.Subscription.Service.response.PaymentResponse;
import com.razorpay.RazorpayException;

public interface PaymentService {


    PaymentResponse cretePayment(PaymentRequest paymentRequest, Long id);

    PaymentResponse createRandomPayment(Payment paymentRequest);

    Payment getPaymentById(Long paymentId) throws Exception;

    Boolean updatePaymentStatus(String paymentLinkId) throws RazorpayException;
}
