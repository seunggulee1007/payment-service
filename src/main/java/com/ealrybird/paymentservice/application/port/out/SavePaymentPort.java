package com.ealrybird.paymentservice.application.port.out;

import com.ealrybird.paymentservice.domain.PaymentEvent;
import reactor.core.publisher.Mono;

public interface SavePaymentPort {

    Mono<Void> save(PaymentEvent paymentEvent);

}
