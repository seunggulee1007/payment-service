package com.ealrybird.paymentservice.adapter.in.web.view;

import com.ealrybird.paymentservice.common.IdempootencyCreator;
import com.ealrybird.paymentservice.common.WebAdapter;
import com.ealrybird.paymentservice.adapter.in.web.request.CheckoutRequest;
import com.ealrybird.paymentservice.application.port.in.CheckoutCommand;
import com.ealrybird.paymentservice.application.port.in.CheckoutUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        checkoutUseCase.checkout(command)
                .map( c -> {

                    model.addAttribute("orderId", c.getOrderId());
                    model.addAttribute("orderName", c.getOrderName());
                    model.addAttribute("amount", c.getAmount());
                    return "checkout";
                  }
                ).block();
        return "checkout";
    }

}
