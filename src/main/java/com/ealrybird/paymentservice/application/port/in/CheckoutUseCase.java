package com.ealrybird.paymentservice.application.port.in;

import com.ealrybird.paymentservice.domain.CheckoutResult;
import reactor.core.publisher.Mono;

public interface CheckoutUseCase {

    Mono<CheckoutResult> checkout(CheckoutCommand command);

}
