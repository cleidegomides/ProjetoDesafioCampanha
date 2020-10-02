package com.everis.desafio_campanha.controllers;

import com.everis.desafio_campanha.dto.TimeCoracaoDto;
import com.everis.desafio_campanha.services.TimeCoracaoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timecoracao")
public class TimeCoracaoController {

    @Autowired
    private TimeCoracaoService timeCoracaoService;

    @ApiOperation(value = "Criar Time no Banco de Dados")
    @PostMapping
    public ResponseEntity<TimeCoracaoDto> criarTimeCoracao(@RequestBody TimeCoracaoDto timeCoracaoDto){
        TimeCoracaoDto timeCoracaoDto1 = timeCoracaoService.criarTime(timeCoracaoDto);
        return ResponseEntity.ok( timeCoracaoDto1 );
    }

    @ApiOperation(value = "Consultar Time no Banco de Dados")
    @GetMapping("/{nomeTime}")
    public ResponseEntity<TimeCoracaoDto> consultarTimeCoracao(@PathVariable String nomeTime){
        TimeCoracaoDto timeCoracaoDto = timeCoracaoService.buscarTimeCoracao(nomeTime);
        return ResponseEntity.ok(timeCoracaoDto);
    }

    @ApiOperation(value = "Listar todos os Times do Banco de Dados")
    @GetMapping
    public ResponseEntity<List<TimeCoracaoDto>> listarTodosTimeCoracao(){
        List<TimeCoracaoDto> timeCoracaoDto = timeCoracaoService.listarTimes();
        return ResponseEntity.ok(timeCoracaoDto);
    }

}
