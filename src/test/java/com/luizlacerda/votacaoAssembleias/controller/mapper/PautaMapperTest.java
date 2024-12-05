package com.luizlacerda.votacaoAssembleias.controller.mapper;

import com.luizlacerda.votacaoAssembleias.controller.dto.PautaDTO;
import com.luizlacerda.votacaoAssembleias.controller.dto.PautaInputDTO;
import com.luizlacerda.votacaoAssembleias.model.Pauta;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PautaMapperTest {

    @InjectMocks
    private PautaMapper pautaMapper;

    @Test
    @DisplayName("Teste para validar que o metodo retorna um objeto PautaDTO")
    void TestThatMethodtoDTOWillReturnADTO() {
        //When
        UUID uuid = UUID.randomUUID();
        String name = "teste";
        LocalDateTime now = LocalDateTime.now();
        Pauta pauta = new Pauta(uuid,name, now,60,"");
        //DO
        PautaDTO pautaDTO = pautaMapper.toDTO(pauta);
        //Assert
        assertEquals(pauta.getPautaName(),pautaDTO.getPautaName());
        assertEquals(pauta.getPautaDataDeCriacao(),pautaDTO.getPautaDataDeCriacao());
        assertEquals(pauta.getPautaTempoDeAbertura(),pautaDTO.getPautaTempoDeAbertura());
    }
    @Test
    void testThatMethodToDTOWillRetornTDOList() {
        List<Pauta> pautaList = List.of(
                new Pauta(UUID.randomUUID(),"teste01", LocalDateTime.now(),60,""),
                new Pauta(UUID.randomUUID(),"teste02", LocalDateTime.now(),50,""),
                new Pauta(UUID.randomUUID(),"teste03", LocalDateTime.now(),40,"")
        );
        //DO
        List<PautaDTO> pautaDTOS = this.pautaMapper.toDTO(pautaList);
        //Assert
        assertEquals(pautaDTOS.get(0).getPautaName(), pautaDTOS.get(0).getPautaName());
        assertEquals(pautaDTOS.get(0).getPautaTempoDeAbertura(), pautaDTOS.get(0).getPautaTempoDeAbertura());
        assertEquals(pautaDTOS.get(1).getPautaName(), pautaDTOS.get(1).getPautaName());
        assertEquals(pautaDTOS.get(1).getPautaTempoDeAbertura(), pautaDTOS.get(1).getPautaTempoDeAbertura());
        assertEquals(pautaDTOS.get(2).getPautaName(), pautaDTOS.get(2).getPautaName());
        assertEquals(pautaDTOS.get(2).getPautaTempoDeAbertura(), pautaDTOS.get(2).getPautaTempoDeAbertura());
    }

    @Test
    void testThatMethodtoEntityReturnEntity() {
        PautaInputDTO pautaInputDTO = new PautaInputDTO("teste01",0);

        //DO
        Pauta pauta = this.pautaMapper.toEntity(pautaInputDTO);

        //Assert
        assertEquals(pautaInputDTO.getPautaName(),pauta.getPautaName());
        assertEquals(pauta.getPautaTempoDeAbertura(), 10);
        assertTrue(pauta.getPautaDataDeCriacao() != null);

    }

    @Test
    void testThatMethodtoEntityReturnEntityList() {
        List<PautaInputDTO> pautaInputDTOS = List.of(
                new PautaInputDTO("teste01",0),
                new PautaInputDTO("teste02",20),
                new PautaInputDTO("teste03",60)
        );
        //DO
        List<Pauta> pautaList = this.pautaMapper.toEntity(pautaInputDTOS);

        //Assert
        assertEquals(pautaList.get(0).getPautaName(),pautaInputDTOS.get(0).getPautaName());
        assertTrue(pautaList.get(0).getPautaDataDeCriacao() != null);
        assertEquals(pautaList.get(1).getPautaName(),pautaInputDTOS.get(1).getPautaName());
        assertTrue(pautaList.get(1).getPautaDataDeCriacao() != null);
        assertEquals(pautaList.get(2).getPautaName(),pautaInputDTOS.get(2).getPautaName());
        assertTrue(pautaList.get(2).getPautaDataDeCriacao() != null);
    }
}