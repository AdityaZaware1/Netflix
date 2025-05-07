package com.ben.Subscription.Service.repo;

import com.ben.Subscription.Service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepo extends JpaRepository<Payment, Long> {
    Payment findPaymentsByPaymentLinkId(String paymentLinkId);
}
