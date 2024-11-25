package com.luizlacerda.votacaoAssembleias.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class PautaDTO {
    private String pautaName;

    private LocalDateTime pautaDataDeCriacao;

    private int pautaTempoDeAbertura;

    private String pautaResultado;
}
