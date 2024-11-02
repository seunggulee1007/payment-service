package com.ealrybird.paymentservice.adapter.out.web.product.client;

import com.ealrybird.paymentservice.domain.Product;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MockProductClient implements ProductClient {

    @Override
    public Flux<Product> getProducts(Long cartId, List<Long> productIds) {
        return Flux.fromIterable(
            productIds.stream()
                .map(id ->
                         new Product(id,
                                     new BigDecimal(id + 10000),
                                     2,
                                     "test_product_" + id,
                                     1L
                         )).toList()
        );
    }

}
