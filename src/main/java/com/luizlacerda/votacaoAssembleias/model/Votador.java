package com.luizlacerda.votacaoAssembleias.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Votador {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID votadorID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pauta_id")
    private Pauta pautaID;
    @NonNull
    private String cpf;
    private LocalDateTime dateDeVotacao;

    public Votador() {
    }

    public Votador(Pauta pautaID, @NonNull String cpf, LocalDateTime dateDeVotacao) {
        this.pautaID = pautaID;
        this.cpf = cpf;
        this.dateDeVotacao = dateDeVotacao;
    }
}
