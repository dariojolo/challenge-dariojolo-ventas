package com.dariojolo.app.ventas.servicioventas.services;

import com.dariojolo.app.ventas.servicioventas.entities.PreciosValidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class WebClientService {

    @Autowired
    private RestTemplate clienteRest;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public PreciosValidos getPreciosLimite(String modelo) {
        return clienteRest.getForObject("https://servicio-productos.herokuapp.com/api/products/precio/" + modelo, PreciosValidos.class);
    }

    public PreciosValidos getPreciosLimiteWebClient(String modelo) {

         return webClientBuilder.build()
            .get()
            .uri("https://servicio-productos.herokuapp.com/api/products/precio/{modelo}",modelo)
            .retrieve()
            .bodyToMono(PreciosValidos.class)
            .block();
    }
}
