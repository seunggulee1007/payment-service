package com.ealrybird.paymentservice.application.service;

import com.ealrybird.paymentservice.common.UseCase;
import com.ealrybird.paymentservice.application.port.out.SavePaymentPort;
import com.ealrybird.paymentservice.application.port.in.CheckoutCommand;
import com.ealrybird.paymentservice.application.port.in.CheckoutUseCase;
import com.ealrybird.paymentservice.application.port.out.LoadProductPort;
import com.ealrybird.paymentservice.domain.*;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CheckoutService implements CheckoutUseCase{

    private final LoadProductPort loadProductPort;
    private final SavePaymentPort savePaymentPort;

    @Override
    public Mono<CheckoutResult> checkout(CheckoutCommand command) {
        return loadProductPort.getProducts(command.getCartId(), command.getProductIds())
            .collectList()
            .map(products -> createPaymentEvent(command, products))
            .flatMap(paymentEvent -> savePaymentPort.save(paymentEvent).thenReturn(paymentEvent))
            .map(paymentEvent -> new CheckoutResult(paymentEvent.totalAmount(),
                                                              paymentEvent.orderId(),
                                                              paymentEvent.orderName()));
    }

    private PaymentEvent createPaymentEvent(CheckoutCommand command, List<Product> products) {
        return new PaymentEvent(
            command.getBuyerId(),
            command.getIdempotencyKey(),
            String.join(",",products.stream().map(Product::name).toList()),
            products.stream()
                .map(
                    p -> new PaymentOrder(
                        p.sellerId(),
                        command.getIdempotencyKey(),
                        p.id(),
                        p.amount(),
                        PaymentStatus.NOT_STARTED
                    )
                ).toList()
        );
    }

}
