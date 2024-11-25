package com.luizlacerda.votacaoAssembleias.service;

import com.luizlacerda.votacaoAssembleias.controller.dto.RetornoCPFApi;
import com.luizlacerda.votacaoAssembleias.controller.dto.VoteDTO;
import com.luizlacerda.votacaoAssembleias.exception.HasAlreadyVotedException;
import com.luizlacerda.votacaoAssembleias.exception.VotingTimeExpiredException;
import com.luizlacerda.votacaoAssembleias.model.Pauta;
import com.luizlacerda.votacaoAssembleias.model.Votador;
import com.luizlacerda.votacaoAssembleias.model.Vote;
import com.luizlacerda.votacaoAssembleias.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PautaService pautaService;
    private final VotadorServer votadorServer;

    private final CpfValidatorServer cpfValidatorServer;

    public void votarEmPauta(VoteDTO voteDTO) {
        Optional<Pauta> pautaFound = this.pautaService.getPautaById(voteDTO.getPautaId());
        if(pautaFound.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Pauta pauta = pautaFound.get();
        LocalDateTime dataFinal = pauta.getPautaDataDeCriacao().plusMinutes(Long.valueOf(pauta.getPautaTempoDeAbertura()));
        if (LocalDateTime.now().isAfter(dataFinal)){
            throw new VotingTimeExpiredException("Tempo de votação expirado.");
        }
        RetornoCPFApi isCPFValid = this.cpfValidatorServer.validateCPF(voteDTO.getCpfVoter());

        if(!isCPFValid.getValid()){
            throw new VotingTimeExpiredException("CPF Invalido.");
        }
        Boolean hasVoted = this.votadorServer.hasVotadorVoted(pauta.getPautaId(), voteDTO.getCpfVoter());
        if(hasVoted){
            throw new HasAlreadyVotedException("Usuario ja votou!");
        }
        Vote newVote = new Vote(pauta,LocalDateTime.now(),voteDTO.getVoto());
        this.voteRepository.save(newVote);
        Votador novoVotador = new Votador(pauta, voteDTO.getCpfVoter(), LocalDateTime.now());
        this.votadorServer.saveNewVotador(novoVotador);

    }
}
