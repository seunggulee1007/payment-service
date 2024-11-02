package com.ealrybird.paymentservice.domain;

import java.math.BigDecimal;

public record PaymentOrder (
    Long id,
    Long paymentEventId,
    Long sellerId,
    Long buyerId,
    Long productId,
    String orderId,
    BigDecimal amount,
    PaymentStatus paymentStatus,
    boolean isLedgerUpdated,
    boolean isWalletUpdated
){

    public PaymentOrder(Long sellerId, String orderId, Long productId, BigDecimal amount, PaymentStatus paymentStatus) {
        this(null, null, sellerId, null, productId, orderId, amount, paymentStatus, false, false);
    }

}
