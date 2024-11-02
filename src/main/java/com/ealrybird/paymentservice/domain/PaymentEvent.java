package com.ealrybird.paymentservice.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PaymentEvent(
    Long id,
    Long buyerId,
    String orderName,
    String orderId,
    String paymentKey,
    PaymentType paymentType,
    PaymentMethod paymentMethod,
    LocalDateTime approvedAt,
    List<PaymentOrder> paymentOrders
) {

    public PaymentEvent(Long buyerId, String orderId, String orderName, List<PaymentOrder> paymentOrders) {
        this(null, buyerId, orderName, orderId, null, null, null, null, paymentOrders);
    }

    public Long totalAmount() {
        return paymentOrders.stream().map(PaymentOrder::amount)
            .reduce(BigDecimal.ZERO, BigDecimal::add).longValue();
    }
}
