package com.ealrybird.paymentservice.application.service;

import com.ealrybird.paymentservice.adapter.in.web.common.UseCase;
import com.ealrybird.paymentservice.application.port.in.CheckoutCommand;
import com.ealrybird.paymentservice.application.port.in.CheckoutUseCase;
import com.ealrybird.paymentservice.domain.CheckoutResult;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class CheckoutService implements CheckoutUseCase{

    @Override
    public Mono<CheckoutResult> checkout(CheckoutCommand command) {
        return null;
    }

}
