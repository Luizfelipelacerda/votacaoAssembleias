package com.luizlacerda.votacaoAssembleias.controller;

import com.luizlacerda.votacaoAssembleias.controller.dto.PautaDTO;
import com.luizlacerda.votacaoAssembleias.controller.dto.PautaInputDTO;
import com.luizlacerda.votacaoAssembleias.model.Pauta;
import com.luizlacerda.votacaoAssembleias.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/votacao/v1/pauta")
public class PautaController {

    private final PautaService pautaService;


    @GetMapping()
    public ResponseEntity<List<PautaDTO>> getAllPautas(){

        List<PautaDTO> AllPautas = this.pautaService.getAllPautas();
        return ResponseEntity.ok(AllPautas);
    }

    @PostMapping("/createPauta")
    public ResponseEntity<PautaDTO> createNewPauta(@RequestBody PautaInputDTO newPautaDTO){
        PautaDTO newPauta = this.pautaService.createNewPauta(newPautaDTO);
        return ResponseEntity.ok(newPauta);
    }
    


}
