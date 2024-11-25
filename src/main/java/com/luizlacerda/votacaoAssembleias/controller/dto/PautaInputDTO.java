package com.luizlacerda.votacaoAssembleias.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaInputDTO {
    private String pautaName;

    private int pautaTempoDeAbertura;

}
