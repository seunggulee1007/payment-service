package com.ealrybird.paymentservice.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentMethod {

    EASY_PAY("간편결제"),
    ;
    private final String description;

}
