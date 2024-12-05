package com.luizlacerda.votacaoAssembleias.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VotingTimeExpiredException.class)
    public ResponseEntity<String> handleVotingTimeExpiredException(VotingTimeExpiredException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(HasAlreadyVotedException.class)
    public ResponseEntity<String> handleHasAlreadyVotedException(HasAlreadyVotedException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CpfInvalidException.class)
    public ResponseEntity<String> handleCpfInvalidException(CpfInvalidException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoNamePautaException.class)
    public ResponseEntity handleNoNamePautaException(NoNamePautaException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(PautaDoesExistException.class)
    public ResponseEntity handlePautaDoesExistException(PautaDoesExistException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

}
