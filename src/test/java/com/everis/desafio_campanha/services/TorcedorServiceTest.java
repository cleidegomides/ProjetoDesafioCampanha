package com.everis.desafio_campanha.services;

import com.everis.desafio_campanha.mappers.MapperTorcedorTorcedorDto;
import com.everis.desafio_campanha.repositorys.TorcedorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TorcedorServiceTest {

    @InjectMocks
    @Autowired
    private TorcedorService torcedorService;

    @Mock
    private TorcedorRepository torcedorRepository;

    private MapperTorcedorTorcedorDto mapperTorcedorTorcedorDto;

    @Test
    void criarTorcedor() {
    }

    @Test
    void buscarTorcedor() {
    }

    @Test
    void deletarTorcedor() {
    }

    @Test
    void listarTorcedores() {
    }
}