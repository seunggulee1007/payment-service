package com.ealrybird.paymentservice.domain;

import lombok.Data;

@Data
public class CheckoutResult {

    private Long amount;

    private String orderId;

    private String orderName;

}
