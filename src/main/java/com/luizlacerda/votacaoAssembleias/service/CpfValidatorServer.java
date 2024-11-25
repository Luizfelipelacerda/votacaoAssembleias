package com.luizlacerda.votacaoAssembleias.service;

import com.luizlacerda.votacaoAssembleias.controller.dto.RetornoCPFApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@Service
public class CpfValidatorServer {

    private final WebClient webClient;

    @Value("${ApiCpf.token}")
    private String token;;

    public RetornoCPFApi validateCPF(String cpf){
        String ApiUrl = "/v1/validator";

        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(ApiUrl) 
                        .queryParam("token",token)
                        .queryParam("value",cpf)
                        .queryParam("type","cpf")
                        .build())
                .retrieve()
                .bodyToMono(RetornoCPFApi.class)
                .block();
    }
}
