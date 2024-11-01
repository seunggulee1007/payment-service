package com.ealrybird.paymentservice.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CheckoutCommand {

    private Long cartId;

    private Long buyerId;

    private List<Long> productIds;

    private String idempotencyKey;

}
