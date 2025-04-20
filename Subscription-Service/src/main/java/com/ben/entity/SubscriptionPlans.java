package com.ben.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriptionPlans {

    public static final String BASIC = "MONTHLY";
    public static final Double BASIC_PRICE = 199.0;
    public static final String PREMIUM = "QUARTERLY";
    public static final Double PREMIUM_PRICE = 899.0;
    public static final String ENTERPRISE = "ANNUAL";
    public static final Double ENTERPRISE_PRICE = 1599.0;
}
