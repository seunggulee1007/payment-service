package com.ealrybird.paymentservice.adapter.out.web.excutor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TossPaymentExecutor {

    private final WebClient tossPaymentClient;
    private static final String uri = "/v1/payments/confirm";

    public Mono<String> execute(String paymentKey, String orderId, String amount) {
        return tossPaymentClient.post()
                .uri(uri)
                .bodyValue(
                    String.format(
                    """
                    {
                      "paymentKey": "%s",
                      "orderId": "%s",
                      "amount": "%s"
                    }
                    """, paymentKey, orderId, amount).trim()
                )
                .retrieve()
                .bodyToMono(String.class);
    }
}
