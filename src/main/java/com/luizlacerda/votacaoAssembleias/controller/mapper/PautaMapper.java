package com.luizlacerda.votacaoAssembleias.controller.mapper;

import com.luizlacerda.votacaoAssembleias.controller.dto.PautaDTO;
import com.luizlacerda.votacaoAssembleias.controller.dto.PautaInputDTO;
import com.luizlacerda.votacaoAssembleias.model.Pauta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class PautaMapper {

    public PautaDTO toDTO(Pauta pauta){
        return PautaDTO.builder()
                .pautaName(pauta.getPautaName())
                .pautaDataDeCriacao(pauta.getPautaDataDeCriacao())
                .pautaTempoDeAbertura(pauta.getPautaTempoDeAbertura())
                .pautaResultado(pauta.getPautaResultado())
                .build();
    }

    public List<PautaDTO> toDTO(List<Pauta> pautaList){
        List<PautaDTO> pautaDtoList =  pautaList.stream().map(this::toDTO).toList();
        return pautaDtoList;
    }

    public List<Pauta> toEntity(List<PautaInputDTO> pautaDTOList){
        List<Pauta> pautaList =  pautaDTOList.stream().map(this::toEntity).toList();
        return pautaList;
    }

    public Pauta toEntity(PautaInputDTO pautaDTO){
        return new Pauta(
                pautaDTO.getPautaName(),
                LocalDateTime.now(),
                (pautaDTO.getPautaTempoDeAbertura() == 0) ? 10 :pautaDTO.getPautaTempoDeAbertura()
        );
    }
}
