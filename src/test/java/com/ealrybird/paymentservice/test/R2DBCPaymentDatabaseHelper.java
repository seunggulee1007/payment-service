package com.ealrybird.paymentservice.test;

import com.ealrybird.paymentservice.domain.PaymentEvent;
import com.ealrybird.paymentservice.domain.PaymentOrder;
import com.ealrybird.paymentservice.domain.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.BigInteger;

@RequiredArgsConstructor
public class R2DBCPaymentDatabaseHelper implements PaymentDatabaseHelper {

    private final DatabaseClient databaseClient;

    private final TransactionalOperator transactionalOperator;

    @Override
    public PaymentEvent getPaymentEvent(String orderId) {
        return Mono.from(databaseClient.sql(selectPaymentQuery())
                             .bind("orderId", orderId)
                             .fetch()
                             .all()
                             .groupBy(
                                 map -> ((BigInteger)map.get("payment_event_id")).longValue()
                             ).flatMap(groupFlex -> groupFlex.collectList().map(results ->
                                                                                    new PaymentEvent(
                                                                                        groupFlex.key(),
                                                                                        (String)results.getFirst().get("order_id"),
                                                                                        (String)results.getFirst().get("order_name"),
                                                                                        (Long)results.getFirst().get("buyer_id"),
                                                                                        (Boolean)results.getFirst().get("is_payment_done"),
                                                                                        results.stream().map(result -> new PaymentOrder(
                                                                                                                 (Long)result.get("id"),
                                                                                                                 groupFlex.key(),
                                                                                                                 (Long)result.get("seller_id"),
                                                                                                                 (String)result.get("order_id"),
                                                                                                                 (Long)result.get("product_id"),
                                                                                                                 (BigDecimal)result.get("amount"),
                                                                                                                 PaymentStatus.get((String)result.get(
                                                                                                                     "payment_order_status")),
                                                                                                                 (Boolean)result.get("ledger_updated"),
                                                                                                                 (Boolean)result.get("wallet_updated")
                                                                                                             )
                                                                                        ).toList()
                                                                                    )
                                       )
            )).block();
    }

    private String selectPaymentQuery() {
        return """
            select * from payment_events pe
            inner join payment_orders po
            on pe.order_id = po.order_id
            where pe.order_id = :orderId
            """.trim();
    }

}
