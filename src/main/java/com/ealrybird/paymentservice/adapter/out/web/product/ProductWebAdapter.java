package com.ealrybird.paymentservice.adapter.out.web.product;

import com.ealrybird.paymentservice.adapter.out.web.product.client.ProductClient;
import com.ealrybird.paymentservice.application.port.out.LoadProductPort;
import com.ealrybird.paymentservice.common.WebAdapter;
import com.ealrybird.paymentservice.domain.Product;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.List;

@WebAdapter
@RequiredArgsConstructor
public class ProductWebAdapter implements LoadProductPort {

    private final ProductClient productClient;

    @Override
    public Flux<Product> getProducts(Long cartId, List<Long> productIds) {
        return productClient.getProducts(cartId, productIds);
    }

}
