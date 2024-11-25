package com.luizlacerda.votacaoAssembleias.controller.dto;

import lombok.Data;

@Data
public class RetornoCPFApi {

    private Boolean valid;

    private String formatted;
}
