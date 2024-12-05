package com.luizlacerda.votacaoAssembleias.controller;

import com.luizlacerda.votacaoAssembleias.controller.dto.PautaDTO;
import com.luizlacerda.votacaoAssembleias.controller.dto.PautaInputDTO;
import com.luizlacerda.votacaoAssembleias.service.PautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PautaControllerTest {

    @Mock
    private PautaService pautaService;
    @InjectMocks
    private PautaController pautaController;

    private PautaDTO pautaDTO;
    private PautaInputDTO pautaInputDTO;
    @BeforeEach
    void createPautaObject(){
        pautaDTO = PautaDTO.builder()
                .pautaName("teste")
                .pautaDataDeCriacao(LocalDateTime.now())
                .pautaTempoDeAbertura(60)
                .build();
    }

    @BeforeEach
    void createPautaInsputObject(){
        pautaInputDTO = PautaInputDTO.builder()
                .pautaName("teste")
                .pautaTempoDeAbertura(60)
                .build();
    }


    @DisplayName("Teste para validar que o metodo retorna o status http 200")
    @Test
    void testGetAllPautasReturnHttp200() {


        List<PautaDTO> listaPauta = List.of(this.pautaDTO);

        Mockito.when(pautaService.getAllPautas()).thenReturn(listaPauta);

        ResponseEntity<List<PautaDTO>> result = pautaController.getAllPautas();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().get(0).getPautaName()).isEqualTo("teste");
    }


    @DisplayName("Teste para validar que o metodo cria uma nova pauta.")
    @Test
    void testCreateNewPauta(){

        Mockito.when(pautaService.createNewPauta(pautaInputDTO)).thenReturn(pautaDTO);

        ResponseEntity<PautaDTO> result = pautaController.createNewPauta(pautaInputDTO);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getPautaName()).isEqualTo(pautaInputDTO.getPautaName());
        assertThat(result.getBody().getPautaTempoDeAbertura()).isEqualTo(pautaInputDTO.getPautaTempoDeAbertura());
    }



}