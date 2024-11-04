package com.ealrybird.paymentservice.test;

import com.ealrybird.paymentservice.domain.PaymentEvent;

public interface PaymentDatabaseHelper {

    PaymentEvent getPaymentEvent(String orderId);

}
