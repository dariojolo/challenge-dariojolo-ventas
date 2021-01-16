package com.dariojolo.app.ventas.servicioventas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {

    @Bean("clienteRest")
    public RestTemplate registrarRestTemplate() {
        return new RestTemplate();
    }

    @Bean("webClient")
    public WebClient.Builder getWebClientBuilder(){
        return WebClient.builder();
    }
}
