package com.luizlacerda.votacaoAssembleias.Enum;

public enum Voto {
    SIM("sim"),
    NAO("nao");

    public final String label;

    private Voto(String label) {
        this.label = label;
    }
}
