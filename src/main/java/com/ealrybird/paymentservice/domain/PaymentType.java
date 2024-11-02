package com.ealrybird.paymentservice.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PaymentType {

    NORMAL("일반결제"),
    ;
    private final String description;
}
