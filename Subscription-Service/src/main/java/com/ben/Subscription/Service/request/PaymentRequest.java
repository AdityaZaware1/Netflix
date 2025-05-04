package com.ben.Subscription.Service.request;

import com.ben.Subscription.Service.enums.PaymentOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    private String userName;
    private Long amount;
    private Long userId;


}
