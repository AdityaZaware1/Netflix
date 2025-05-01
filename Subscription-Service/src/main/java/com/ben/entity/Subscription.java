package com.ben.entity;

import com.ben.enums.PaymentOrderStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String planType;
    private Long price;

    private String paymentLinkId;

    @Column(nullable = false)
    private Long userId;

    PaymentOrderStatus paymentOrderStatus = PaymentOrderStatus.PENDING;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
