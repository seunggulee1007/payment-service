package com.ealrybird.paymentservice.adapter.in.web.api;

import com.ealrybird.paymentservice.common.WebAdapter;
import com.ealrybird.paymentservice.adapter.in.web.request.TossPaymentConfirmRequest;
import com.ealrybird.paymentservice.adapter.out.web.excutor.TossPaymentExecutor;
import com.ealrybird.paymentservice.adapter.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@WebAdapter
@RequestMapping("/v1/toss")
@RestController
@RequiredArgsConstructor
public class TossPaymentController {

    private final TossPaymentExecutor tossPaymentExecutor;

    @PostMapping("/confirm")
    public Mono<ResponseEntity <ApiResponse<String>>> confirm (@RequestBody TossPaymentConfirmRequest request) {
        return tossPaymentExecutor.execute(
            request.getPaymentKey(),
            request.getOrderId(),
            Long.toString(request.getAmount())).map(a->
                ResponseEntity.ok().body(ApiResponse.with(HttpStatus.OK, "OK", a)
        ));
    }
}
