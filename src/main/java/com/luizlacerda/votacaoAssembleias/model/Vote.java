package com.luizlacerda.votacaoAssembleias.model;

import com.luizlacerda.votacaoAssembleias.Enum.Voto;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "vote")
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID votoId;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pautaId;
    private LocalDateTime dataDeVoto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Voto voto;

    public Vote() {
    }

    public Vote(Pauta pauta, LocalDateTime dataDeVoto, Voto voto) {
        this.pautaId = pauta;
        this.dataDeVoto = LocalDateTime.from(dataDeVoto) ;
        this.voto = voto;
    }

}
