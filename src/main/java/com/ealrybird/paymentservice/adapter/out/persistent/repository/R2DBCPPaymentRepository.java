package com.ealrybird.paymentservice.adapter.out.persistent.repository;

import com.ealrybird.paymentservice.domain.PaymentEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class R2DBCPPaymentRepository implements PaymentRepository {

    private final DatabaseClient databaseClient;
    private final TransactionalOperator transactionalOperator;

    @Override
    public Mono<Void> save(PaymentEvent paymentEvent) {
        return insertPaymentEvent(paymentEvent)
            .flatMap(selectPaymentEventId())
            .flatMap(insertPaymentOrders(paymentEvent))
            .as(transactionalOperator::transactional)
            .then()
        ;
    }

    private Function<Object, Mono<Long>> insertPaymentOrders(PaymentEvent paymentEvent) {
        return paymentEventId -> {
            String valueClauses = paymentEvent.paymentOrders().stream()
                .map(order -> String.format("(%d, %d, %d, %d, '%s')".trim(),
                                            paymentEventId,
                                            order.sellerId(),
                                            order.productId(),
                                            order.amount(),
                                            order.paymentStatus()))
                .collect(Collectors.joining(", "));
            return databaseClient.sql(insertPaymentOrderQuery(valueClauses))
                .fetch()
                .rowsUpdated();
        };
    }

    private Function<Long, Mono<?>> selectPaymentEventId() {
        return count -> databaseClient.sql("select LAST_INSERT_ID()".trim())
            .fetch()
            .first()
            .map(map -> ((BigInteger)map.get("LAST_INSERT_ID()")).longValue())
            ;
    }

    private Mono<Long> insertPaymentEvent(PaymentEvent paymentEvent) {
        return databaseClient.sql(insertPaymentEventQuery())
            .bind("buyerId", paymentEvent.buyerId())
            .bind("orderName", paymentEvent.orderName())
            .bind("orderId", paymentEvent.orderId())
            .fetch()
            .rowsUpdated();
    }

    private String insertPaymentEventQuery() {
        return "INSERT INTO payment_events (buyer_id, order_name, order_id) VALUES (:buyerId, :orderName, :order_id)".trim();
    }

    private String insertPaymentOrderQuery(String valueClauses) {
        return String.format("INSERT INTO payment_orders (payment_event_id, seller_id, product_id, amount, status) VALUES (%s)".trim(), valueClauses);
    }

}
