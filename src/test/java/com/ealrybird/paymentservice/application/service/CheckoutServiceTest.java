package com.ealrybird.paymentservice.application.service;

import com.ealrybird.paymentservice.application.port.in.CheckoutCommand;
import com.ealrybird.paymentservice.application.port.in.CheckoutUseCase;
import com.ealrybird.paymentservice.domain.PaymentEvent;
import com.ealrybird.paymentservice.domain.PaymentOrder;
import com.ealrybird.paymentservice.test.PaymentDatabaseHelper;
import com.ealrybird.paymentservice.test.PaymentTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Import(PaymentTestConfiguration.class)
class CheckoutServiceTest {

    @Autowired
    private CheckoutUseCase checkoutUseCase;
    @Autowired
    private PaymentDatabaseHelper paymentDatabaseHelper;

    @Test
    @DisplayName("")
    void shouldSavePaymentEventAndPaymentOrderSuccessFully() {
        // given
        String orderId = UUID.randomUUID().toString();
        CheckoutCommand checkoutCommand = new CheckoutCommand(
            1L, 1L, List.of(1L, 2L, 3L), orderId
        );
        // when
        StepVerifier.create(checkoutUseCase.checkout(checkoutCommand))
            .expectNextMatches(checkoutResult -> {
                assertNotNull(checkoutResult);
                assertEquals(60000, checkoutResult.getAmount().intValue());
                assertEquals(orderId, checkoutResult.getOrderId());
                assertEquals("test_product_1,test_product_2,test_product_3", checkoutResult.getOrderName());
                return true;
            })
            .verifyComplete();
        // then
        PaymentEvent paymentEvent = paymentDatabaseHelper.getPaymentEvent(orderId);

        assertThat(paymentEvent.orderId()).isEqualTo(orderId);
        assertThat(paymentEvent.paymentOrders()).hasSameSizeAs(checkoutCommand.getProductIds());
        assertFalse(paymentEvent.isPaymentDone());
        assertFalse(paymentEvent.paymentOrders().stream().noneMatch(PaymentOrder::isLedgerUpdated));

    }

}