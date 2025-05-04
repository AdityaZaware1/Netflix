package com.ben.Subscription.Service.service;


import com.ben.Subscription.Service.entity.Payment;
import com.ben.Subscription.Service.reposnse.PaymentResponse;
import com.ben.Subscription.Service.request.PaymentRequest;

public interface PaymentService {


    public Payment cretePayment(PaymentRequest paymentRequest);

    public PaymentResponse createRandomPayment(PaymentRequest paymentRequest);

    public Payment getPaymentById(Long paymentId) throws Exception;
}
