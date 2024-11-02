package com.ealrybird.paymentservice.application.port.out;

import com.ealrybird.paymentservice.domain.Product;
import reactor.core.publisher.Flux;

import java.util.List;

public interface LoadProductPort {

    Flux<Product> getProducts(Long cartId, List<Long> productIds);

}
