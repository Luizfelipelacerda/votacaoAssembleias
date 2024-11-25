package com.luizlacerda.votacaoAssembleias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "Pauta")
public class Pauta {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pauta_id")
    private UUID pautaId;

    private String pautaName;

    private LocalDateTime pautaDataDeCriacao;

    private int pautaTempoDeAbertura;

    private String pautaResultado;

    public Pauta(){}

    public Pauta(String name, LocalDateTime dataDeCriacao, int dataAbertura){
        this.pautaName = name;
        this.pautaDataDeCriacao = dataDeCriacao;
        this.pautaTempoDeAbertura = dataAbertura;
    }

    public Pauta(UUID pautaId, String name, LocalDateTime dataDeCriacao, int dataAbertura, String pautaResultado){
        this.pautaId = pautaId;
        this.pautaName = name;
        this.pautaDataDeCriacao = dataDeCriacao;
        this.pautaTempoDeAbertura = dataAbertura;
        this.pautaResultado = pautaResultado;
    }
}
