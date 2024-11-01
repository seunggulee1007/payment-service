package com.ealrybird.paymentservice.adapter.in.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CheckoutRequest {

    private Long cartId = 1L;

    private List<Long> productIds = List.of(1L,2L,3L);

    private Long buyerId = 1L;

    private String seed = LocalDateTime.now().toString();

}
