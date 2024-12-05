package com.luizlacerda.votacaoAssembleias.service;

import com.luizlacerda.votacaoAssembleias.controller.dto.PautaDTO;
import com.luizlacerda.votacaoAssembleias.controller.dto.PautaInputDTO;
import com.luizlacerda.votacaoAssembleias.controller.mapper.PautaMapper;
import com.luizlacerda.votacaoAssembleias.exception.NoNamePautaException;
import com.luizlacerda.votacaoAssembleias.model.Pauta;
import com.luizlacerda.votacaoAssembleias.repository.PautaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.beans.ExceptionListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PautaServiceImpTest {

    private List<Pauta> listaPauta;
    private PautaDTO pautaDTO;
    private PautaInputDTO pautaInputDTO;

    @InjectMocks
    private PautaServiceImp pautaService;
    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private PautaMapper pautaMapper;

    @Test
    void getAllPautas() {
        String name = "pautaTeste";
        LocalDateTime now = LocalDateTime.now();
        listaPauta = List.of(new Pauta(name, now, 60));
        //When
        Mockito.when(pautaRepository.findAll()).thenReturn(listaPauta);

        Mockito.when(pautaMapper.toDTO(listaPauta)).thenReturn(List.of(PautaDTO
                .builder()
                .pautaName(name)
                .pautaTempoDeAbertura(60)
                .pautaDataDeCriacao(now)
                .build()));
        //Do
        List<PautaDTO> allPautas = pautaService.getAllPautas();

        //Assert
        assertThat(allPautas.get(0).getPautaName()).isEqualTo(name);
        assertThat(allPautas.get(0).getPautaTempoDeAbertura()).isEqualTo(60);
        assertThat(allPautas.get(0).getPautaDataDeCriacao()).isEqualTo(now);
    }

    @Test
    void throwNoNamePautaExceptionIfPautaNulla(){
        //When
        PautaInputDTO pautaInputDTO1 = new PautaInputDTO();
        //Do
        NoNamePautaException exception = assertThrows(NoNamePautaException.class, ()-> pautaService.createNewPauta(pautaInputDTO1), "teste");
        //Assert
        assertEquals("Pauta nulla", exception.getMessage());
    }

    @DisplayName("Teste para validar que o metodo retorna o status http 200")
    @Test
    void createNewPauta() {
        String nome = "pauta teste";
        int tempoAbertura = 10;
        LocalDateTime dataCriacao = LocalDateTime.now();
        //when
        PautaInputDTO pautaInputDTO1 = new PautaInputDTO(nome, tempoAbertura);
        Pauta pauta = new Pauta(UUID.randomUUID(), nome,dataCriacao ,tempoAbertura, "");
        Mockito.when(this.pautaMapper.toEntity(pautaInputDTO1)).thenReturn(pauta);
        Mockito.when(this.pautaRepository.save(pauta)).thenReturn(pauta);
        Mockito.when(this.pautaMapper.toDTO(pauta)).thenReturn(PautaDTO
                .builder()
                .pautaName(nome)
                .pautaDataDeCriacao(dataCriacao)
                .pautaTempoDeAbertura(tempoAbertura)
                .pautaResultado("")
                .build());
        //Do
        PautaDTO newPauta = this.pautaService.createNewPauta(pautaInputDTO1);


        ArgumentCaptor<Pauta> captor = ArgumentCaptor.forClass(Pauta.class);
        Mockito.verify(pautaRepository).save(captor.capture());

        Pauta capturedPauta = captor.getValue();
        //Assert
        assertThat(newPauta.getPautaName()).isEqualTo(capturedPauta.getPautaName());
        assertThat(newPauta.getPautaTempoDeAbertura()).isEqualTo(capturedPauta.getPautaTempoDeAbertura());
        assertThat(newPauta.getPautaDataDeCriacao()).isEqualTo(capturedPauta.getPautaDataDeCriacao());
    }

    @Test
    void getPautaById() {
        UUID uuid = UUID.randomUUID();
        String nome = "pauta teste";
        int tempoAbertura = 10;
        LocalDateTime dataCriacao = LocalDateTime.now();
        Pauta pauta = new Pauta(uuid, nome, dataCriacao, tempoAbertura, "");
        Optional<Pauta> optionalPauta = Optional.of(pauta);
        //When
        Mockito.when(this.pautaRepository.findById(uuid)).thenReturn(optionalPauta);

        //Do
        Pauta pautaById = this.pautaService.getPautaById(uuid).get();

        //Assert
        assertThat(pautaById.getPautaId()).isEqualTo(uuid);
        assertThat(pautaById.getPautaName()).isEqualTo(nome);
        assertThat(pautaById.getPautaDataDeCriacao()).isEqualTo(dataCriacao);
    }
}