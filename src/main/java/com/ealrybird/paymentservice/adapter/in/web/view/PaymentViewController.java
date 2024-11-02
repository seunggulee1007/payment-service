package com.ealrybird.paymentservice.adapter.in.web.view;

import com.ealrybird.paymentservice.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@WebAdapter
@RequiredArgsConstructor
public class PaymentViewController {

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }

    @GetMapping("/fail")
    public String failPage() {
        return "fail";
    }


}
