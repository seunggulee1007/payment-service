package com.ealrybird.paymentservice.adapter.in.web.view;

import com.ealrybird.paymentservice.adapter.in.web.common.WebAdapter;
import com.ealrybird.paymentservice.adapter.in.web.config.PspProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
