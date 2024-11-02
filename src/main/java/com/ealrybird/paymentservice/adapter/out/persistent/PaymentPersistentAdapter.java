package com.ealrybird.paymentservice.adapter.out.persistent;

import com.ealrybird.paymentservice.adapter.out.persistent.repository.PaymentRepository;
import com.ealrybird.paymentservice.application.port.out.SavePaymentPort;
import com.ealrybird.paymentservice.common.PersistentAdapter;
import com.ealrybird.paymentservice.domain.PaymentEvent;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@PersistentAdapter
@RequiredArgsConstructor
public class PaymentPersistentAdapter implements SavePaymentPort {

    private final PaymentRepository paymentRepository;

    @Override
    public Mono<Void> save(PaymentEvent paymentEvent) {
        return paymentRepository.save(paymentEvent);
    }

}
