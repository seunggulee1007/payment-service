package com.ealrybird.paymentservice.adapter.in.web.view;

import com.ealrybird.paymentservice.adapter.in.web.common.WebAdapter;
import com.ealrybird.paymentservice.adapter.in.web.config.PspProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@WebAdapter
@RequiredArgsConstructor
public class CheckoutController {

    private final PspProperties pspProperties;

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("secretKey", pspProperties.getSecretKey());
        return "checkout";
    }

}
