package com.luizlacerda.votacaoAssembleias.controller.dto;

import com.luizlacerda.votacaoAssembleias.Enum.Voto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class VoteDTO {

    private UUID pautaId;
    private String cpfVoter;
    private Voto Voto;
}
