package com.ealrybird.paymentservice.domain;

import java.math.BigDecimal;


public record Product(
    Long id,
    BigDecimal amount,
    Integer quantity,
    String name,
    Long sellerId
) {


}
