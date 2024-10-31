package com.ealrybird.paymentservice.adapter.out.web.config;

import com.ealrybird.paymentservice.adapter.in.web.config.PspProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class TossWebClientConfiguration {

    private final PspProperties pspProperties;


    @Bean
    public WebClient tossPaymentWebClient() {
        String secretKey = Base64.getEncoder().encodeToString((pspProperties.getSecretKey() + ":").getBytes());

        return WebClient.builder()
            .baseUrl(pspProperties.getUrl())
            .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + secretKey)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
            .clientConnector(reactorClientHttpConnector())
            .codecs(ClientCodecConfigurer::defaultCodecs)
            .build();
    }

    private ClientHttpConnector reactorClientHttpConnector() {
        ConnectionProvider provider = ConnectionProvider.builder("toss-payment")
            .build();
        return new ReactorClientHttpConnector(HttpClient.create(provider));
    }

}
