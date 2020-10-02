package com.everis.desafio_campanha.controllers;

import com.everis.desafio_campanha.dto.TimeCoracaoDto;
import com.everis.desafio_campanha.dto.TorcedorDto;
import com.everis.desafio_campanha.entites.Torcedor;
import com.everis.desafio_campanha.services.TorcedorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/torcedor")
public class TorcedorController {

    @Autowired
    private TorcedorService torcedorService;


    @ApiOperation(value = "Criar Torcedor no Banco de Dados")
    @PostMapping("/criartorcedor")
    public void criarTor(@RequestBody TorcedorDto torcedorDto){
        torcedorService.criarTorcedor(torcedorDto);
    }

    @ApiOperation(value = "Consultar Torcedor no Banco de Dados")
    @GetMapping("/consultar/{id}")
    public ResponseEntity<TorcedorDto> consultarTorcedor(@PathVariable Long id){
        TorcedorDto torcedorDto = torcedorService.buscarTorcedor(id);
        return ResponseEntity.ok(torcedorDto);
    }

    @ApiOperation(value = "Deletar Torcedo no Banco de Dados")
    @DeleteMapping("/deletar/{id}")
    public void deletarTorcedor(@PathVariable Long id){
        torcedorService.deletarTorcedor(id);
    }


    @ApiOperation(value = "Listar Todos Torcedores no Banco de Dados")
    @GetMapping("listartorcedores")
    public ResponseEntity<List<TorcedorDto>> listarTorcedores(){
        List<Torcedor> torcedores = torcedorService.listarTorcedores();

        List<TorcedorDto> torcedorDtos = torcedores
                .stream()
                .filter(t -> t.getTimeCoracao() != null)
                .map(torcedor -> {
                    TorcedorDto torcedorDto = new TorcedorDto();
                    torcedorDto.setId(torcedor.getId());
                    torcedorDto.setNome((torcedor.getNome()));
                    torcedorDto.setDataNascimento(torcedor.getDataNascimento().toString());
                    torcedorDto.setEmail(torcedor.getEmail());

                    TimeCoracaoDto timeCoracaoDto = new TimeCoracaoDto();
                    timeCoracaoDto.setId(torcedor.getTimeCoracao().getId());
                    timeCoracaoDto.setNomeTime(torcedor.getTimeCoracao().getNomeTime());

                    torcedorDto.setTimeCoracao(timeCoracaoDto);

                    return torcedorDto;
                })
                .collect(Collectors.toList());
                return ResponseEntity.ok(torcedorDtos);

    }
}

