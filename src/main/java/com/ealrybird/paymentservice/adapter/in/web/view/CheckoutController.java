package com.ealrybird.paymentservice.adapter.in.web.view;

import com.ealrybird.paymentservice.adapter.in.web.common.IdempootencyCreator;
import com.ealrybird.paymentservice.adapter.in.web.common.WebAdapter;
import com.ealrybird.paymentservice.adapter.in.web.config.PspProperties;
import com.ealrybird.paymentservice.adapter.in.web.request.CheckoutRequest;
import com.ealrybird.paymentservice.application.port.in.CheckoutCommand;
import com.ealrybird.paymentservice.application.port.in.CheckoutUseCase;
import com.ealrybird.paymentservice.domain.CheckoutResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
@WebAdapter
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutUseCase checkoutUseCase;


    @GetMapping("/checkout")
    public String checkout(Model model, CheckoutRequest request) {
        CheckoutCommand command = new CheckoutCommand(
            request.getCartId(),
            request.getBuyerId(),
            request.getProductIds(),
            IdempootencyCreator.create(request.getSeed())
        );

        return checkoutUseCase.checkout(command)
                .map( c -> {

                    model.addAttribute("orderId", c.getOrderId());
                    model.addAttribute("orderName", c.getOrderName());
                    model.addAttribute("amount", c.getAmount());
                    return "checkout";
                  }
                ).block();
    }

}
