package com.ealrybird.paymentservice.adapter.out.web.product;

import com.ealrybird.paymentservice.common.WebAdapter;
import com.ealrybird.paymentservice.application.port.out.LoadProductPort;
import com.ealrybird.paymentservice.domain.Product;
import reactor.core.publisher.Flux;

import java.util.List;

@WebAdapter
public class ProductWebAdapter implements LoadProductPort {

    @Override
    public Flux<Product> getProducts(Long cartId, List<Long> productIds) {
        return null;
    }

}
