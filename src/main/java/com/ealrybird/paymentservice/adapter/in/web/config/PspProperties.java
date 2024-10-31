package com.ealrybird.paymentservice.adapter.in.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("psp.toss")
public class PspProperties {

    private String secretKey;
    private String url;

}
