package com.ealrybird.paymentservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckoutResult {

    private Long amount;

    private String orderId;

    private String orderName;

}
