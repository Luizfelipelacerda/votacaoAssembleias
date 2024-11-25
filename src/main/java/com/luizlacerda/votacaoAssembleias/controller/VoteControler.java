package com.luizlacerda.votacaoAssembleias.controller;

import com.luizlacerda.votacaoAssembleias.controller.dto.VoteDTO;
import com.luizlacerda.votacaoAssembleias.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votacao/v1/vote")
public class VoteControler {


    private final VoteService voteService;

    @PostMapping()
    public ResponseEntity vote(@RequestBody VoteDTO voteDTO){

        this.voteService.votarEmPauta(voteDTO);

        return ResponseEntity.ok().build();
    }
}
