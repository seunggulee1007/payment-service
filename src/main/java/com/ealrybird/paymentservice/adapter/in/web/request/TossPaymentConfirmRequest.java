package com.ealrybird.paymentservice.adapter.in.web.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TossPaymentConfirmRequest {

    private String paymentKey;
    private String orderId;
    private Long amount;

}
