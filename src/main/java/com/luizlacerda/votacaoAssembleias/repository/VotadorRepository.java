package com.luizlacerda.votacaoAssembleias.repository;

import com.luizlacerda.votacaoAssembleias.model.Votador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VotadorRepository extends JpaRepository<Votador, UUID> {

    @Query(
            value = "select * from Votador where pauta_id = ?1 and cpf = ?2",
            nativeQuery = true
    )
    Optional<Votador> hasVotadorVoted(UUID pautaID, String cpf);
}
