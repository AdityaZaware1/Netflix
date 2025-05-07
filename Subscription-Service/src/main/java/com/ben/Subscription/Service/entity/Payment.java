package com.ben.Subscription.Service.entity;

import com.ben.Subscription.Service.enums.PaymentOrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    private Long amount;

    @Enumerated(EnumType.STRING)
    private PaymentOrderStatus status = PaymentOrderStatus.PENDING;
    private Long userId;
    private String paymentLinkId;
    private String userName;
}
