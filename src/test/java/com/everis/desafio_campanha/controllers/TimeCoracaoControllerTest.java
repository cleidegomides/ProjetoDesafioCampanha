package com.everis.desafio_campanha.controllers;

import com.everis.desafio_campanha.dto.TimeCoracaoDto;
import com.everis.desafio_campanha.mappers.MapperTimeCoracaoTimeCoracaoDTO;
import com.everis.desafio_campanha.repositorys.TimeCoracaoRepository;
import com.everis.desafio_campanha.services.TimeCoracaoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TimeCoracaoControllerTest {

    @InjectMocks
    @Autowired
    private TimeCoracaoService timeCoracaoService;

    @Mock
    private TimeCoracaoRepository timeCoracaoRepository;

    private MapperTimeCoracaoTimeCoracaoDTO mapperTimeCoracaoTimeCoracaoDTO;

    @Test
    void criarTimeCoracao() {
        //Cenario
        TimeCoracaoDto timeCoracaoDto = new TimeCoracaoDto();
        timeCoracaoDto.setId(0L);
        timeCoracaoDto.setNomeTime("TimeFlamengoTest");

        //Acao
        TimeCoracaoDto response = timeCoracaoService.criarTime(timeCoracaoDto);

        //Validacao
        assertEquals(response, timeCoracaoDto);
    }

    @Test
    void consultarTimeCoracao() {
    }

    @Test
    void listarTodosTimeCoracao() {
    }

    @Test
    void excluirTimeCoracao() {
    }
}