package com.luizlacerda.votacaoAssembleias.exception;

public class VotingTimeExpiredException extends RuntimeException {
    public VotingTimeExpiredException(String message) {
        super(message);
    }
}