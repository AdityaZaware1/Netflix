package com.ben.enums;

public enum SubscriptionPlans {

    MONTHLY(199.0),
    QUARTERLY(899.0),
    ANNUAL(1599.0);

    private final Double price;

    SubscriptionPlans(Double price) {
        this.price = price;
    }

    public String getName() {
        return this.name();
    }

    public Double getPrice() {
        return price;
    }
}
