package com.luizlacerda.votacaoAssembleias.repository;

import com.luizlacerda.votacaoAssembleias.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoteRepository extends JpaRepository<Vote, UUID> {
}