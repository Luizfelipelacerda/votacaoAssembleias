package com.luizlacerda.votacaoAssembleias.service;

import com.luizlacerda.votacaoAssembleias.controller.dto.PautaDTO;
import com.luizlacerda.votacaoAssembleias.controller.dto.PautaInputDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PautaService {

    List<PautaDTO> getAllPautas();
    PautaDTO createNewPauta(PautaInputDTO newPautaDTO);
}
