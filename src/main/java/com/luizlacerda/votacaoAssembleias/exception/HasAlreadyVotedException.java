package com.luizlacerda.votacaoAssembleias.exception;

public class HasAlreadyVotedException extends RuntimeException{
    public HasAlreadyVotedException(String message) {
        super(message);
    }
}
