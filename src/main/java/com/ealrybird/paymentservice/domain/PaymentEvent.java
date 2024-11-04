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
    List<PaymentOrder> paymentOrders,
    boolean isPaymentDone
) {

    public PaymentEvent(Long buyerId, String orderId, String orderName, List<PaymentOrder> paymentOrders) {
        this(null, buyerId, orderName, orderId, null, null, null, null, paymentOrders, false);
    }

    public PaymentEvent(Long key, String orderId, String orderName, Long buyerId, Boolean isPaymentDone, List<PaymentOrder> list) {
        this(key, buyerId, orderName, orderId, null, null, null, null, list, isPaymentDone);
    }

    public Long totalAmount() {
        return paymentOrders.stream().map(PaymentOrder::amount)
            .reduce(BigDecimal.ZERO, BigDecimal::add).longValue();
    }

    public boolean isPaymentDone() {
        return isPaymentDone;
    }

}
