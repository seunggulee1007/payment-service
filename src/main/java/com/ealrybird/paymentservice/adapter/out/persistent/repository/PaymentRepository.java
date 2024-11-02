package com.ealrybird.paymentservice.adapter.out.persistent.repository;

import com.ealrybird.paymentservice.domain.PaymentEvent;
import reactor.core.publisher.Mono;

public interface PaymentRepository {

    Mono<Void> save(PaymentEvent paymentEvent);

}
