package com.luizlacerda.votacaoAssembleias.service;

import com.luizlacerda.votacaoAssembleias.model.Votador;
import com.luizlacerda.votacaoAssembleias.repository.VotadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VotadorServer {

    private final VotadorRepository votadorRepository;


    public Boolean hasVotadorVoted(UUID pautaID, String cpf){

        Optional<Votador> votador = this.votadorRepository.hasVotadorVoted(pautaID, cpf);

        if(votador.isPresent()){
            return true;
        }
        return false;
    }

    public void saveNewVotador(Votador novoVotador) {
        this.votadorRepository.save(novoVotador);
    }
}
