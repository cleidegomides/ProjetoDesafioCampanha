package com.everis.desafio_campanha.controllers;

import com.everis.desafio_campanha.dto.CampanhaDto;
import com.everis.desafio_campanha.dto.TimeCoracaoDto;
import com.everis.desafio_campanha.dto.TorcedorDto;
import com.everis.desafio_campanha.entites.Campanha;
import com.everis.desafio_campanha.repositorys.CampanhaRepository;
import com.everis.desafio_campanha.services.CampanhaService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@Slf4j
@RequestMapping("/")
public class CampanhaController {

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    private CampanhaRepository campanhaRepository;

    @ApiOperation(value = "Listar Todas as Campanhas do Banco de Dados")
    @GetMapping("/campanha/list")
    public ResponseEntity<List<CampanhaDto>> listaCampanhas(){
        List<Campanha> campanhas = campanhaService.procurarDataVencida();

        List<CampanhaDto> campanhaDtos = campanhas
                .stream()
                .filter(c-> c.getTimeCoracao()!=null)
                .map(campanha -> {
                    CampanhaDto campanhaDto = new CampanhaDto();
                    campanhaDto.setId(campanha.getId());
                    log.info("campanha: {}",campanha);

                    TimeCoracaoDto timeCoracaoDto = new TimeCoracaoDto();
                    timeCoracaoDto.setId(campanha.getTimeCoracao().getId());
                    timeCoracaoDto.setNomeTime(campanha.getTimeCoracao().getNomeTime());

                    campanhaDto.setTimeCoracao(timeCoracaoDto);

                    campanhaDto.setNomeCampanha(campanha.getNomeCampanha());
                    if (campanha.getDataVigenciaFinal() != null) {
                        campanhaDto.setDataVigenciaInicial(
                                campanha.getDataVigenciaInicial().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        );
                    } else {
                        campanhaDto.setDataVigenciaInicial("");
                    }

                    if (campanha.getDataVigenciaFinal() != null) {
                        campanhaDto.setDataVigenciaFinal(
                                campanha.getDataVigenciaFinal().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        );
                    } else {
                        campanhaDto.setDataVigenciaFinal("");
                    }

                    return campanhaDto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(campanhaDtos);
    }

    @ApiOperation(value = "Criar Campanhas no Banco de Dados")
    @PostMapping("/campanha/save")
    public ResponseEntity<CampanhaDto> criarCampanha(@RequestBody CampanhaDto campanhaDto){
        CampanhaDto campanhaDto1 = campanhaService.criarCampanha(campanhaDto);
        return ResponseEntity.ok(campanhaDto1);
    }

    @ApiOperation(value = "Deletar Campanhas do Banco de Dados")
    @DeleteMapping("/campanha/deletar/{id}")
    public void deletarCampanha(@PathVariable Long id){
        campanhaService.excluirCampanha(id);
    }

    @ApiOperation(value = "Atualizar as Campanhas no Banco de Dados")
    @PutMapping("/campanha/atualizar/{id}")
    public void atualizarCampanha(@RequestBody CampanhaDto campanhaDto){
        campanhaService.atualizarCampanha(campanhaDto);
    }

    @ApiOperation(value = "Consultar as Campanhas do Banco de Dados")
    @GetMapping("/campanha/{id}")
    public ResponseEntity<CampanhaDto> consultarCampanha(@PathVariable Long id){
        CampanhaDto campanhaDto = campanhaService.buscarCampanha(id);
        return ResponseEntity.ok(campanhaDto);
    }

    @ApiOperation(value = "Adicionar torcedor à Campanhas do Banco de Dados")
    @PostMapping("/campanha/{idcampanha}/add/torcedor")
    public ResponseEntity<CampanhaDto> adicionarTorcedor(@PathVariable Long idcampanha, Long idTorcedor){
        CampanhaDto campanhaDto = campanhaService.adicionarTorcedor(idTorcedor, idcampanha);

        return ResponseEntity.ok(campanhaDto);
    }

    @ApiOperation(value = "Listar torcedor da Campanha do Banco de Dados")
    @GetMapping("/campanha/torcedores")
    public ResponseEntity<List<TorcedorDto>> listaTorcedorCampanha(Long idCampanha){
        List<TorcedorDto> torcedorDtos = campanhaService.buscarTorcedoresDaCampanha(idCampanha);

        return ResponseEntity.ok(torcedorDtos);
    }
}


