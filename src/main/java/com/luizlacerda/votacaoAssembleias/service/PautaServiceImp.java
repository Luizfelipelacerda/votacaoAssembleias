package com.luizlacerda.votacaoAssembleias.service;

import com.luizlacerda.votacaoAssembleias.controller.dto.PautaDTO;
import com.luizlacerda.votacaoAssembleias.controller.dto.PautaInputDTO;
import com.luizlacerda.votacaoAssembleias.controller.mapper.PautaMapper;
import com.luizlacerda.votacaoAssembleias.exception.NoNamePautaException;
import com.luizlacerda.votacaoAssembleias.model.Pauta;
import com.luizlacerda.votacaoAssembleias.repository.PautaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PautaServiceImp implements PautaService {

    private final PautaRepository pautaRepository;
    private final PautaMapper pautaMapper;

    public List<PautaDTO> getAllPautas() {
        List<Pauta> allPautas = this.pautaRepository.findAll();
        List<PautaDTO>  allPautaDTO = pautaMapper.toDTO(allPautas);
        return allPautaDTO;
    }


    public PautaDTO createNewPauta(PautaInputDTO newPautaDTO) {
        if(newPautaDTO == null || newPautaDTO.getPautaName() == null || newPautaDTO.getPautaName().isEmpty()){
            throw new NoNamePautaException("Pauta nulla");
        }
        Pauta newPauta = this.pautaMapper.toEntity(newPautaDTO);
        Pauta pautaSalva = this.pautaRepository.save(newPauta);
        PautaDTO pautaDTOSalva = this.pautaMapper.toDTO(pautaSalva);
        return pautaDTOSalva;

    }

    public Optional<Pauta> getPautaById(UUID pautaId){
        return this.pautaRepository.findById(pautaId);
    }
}
