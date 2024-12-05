package com.luizlacerda.votacaoAssembleias.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PautaInputDTO {
    private String pautaName;

    private int pautaTempoDeAbertura;

    public PautaInputDTO() {
    }

    public PautaInputDTO(String pautaName, int pautaTempoDeAbertura){
        this.pautaName = pautaName;
        this.pautaTempoDeAbertura = pautaTempoDeAbertura;
    }
}
