package com.everis.desafio_campanha.services;

import com.everis.desafio_campanha.dto.CampanhaDto;
import com.everis.desafio_campanha.dto.TorcedorDto;
import com.everis.desafio_campanha.entites.Campanha;
import com.everis.desafio_campanha.entites.TimeCoracao;
import com.everis.desafio_campanha.entites.Torcedor;
import com.everis.desafio_campanha.exceptions.CampanhaNotFoundException;
import com.everis.desafio_campanha.exceptions.TorcedorNotFoundException;
import com.everis.desafio_campanha.mappers.MapperCampanhaCampanhaDTO;
import com.everis.desafio_campanha.mappers.MapperTorcedorTorcedorDto;
import com.everis.desafio_campanha.repositorys.CampanhaRepository;
import com.everis.desafio_campanha.repositorys.TorcedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CampanhaService {

    @Autowired
    private CampanhaRepository campanhaRepository;

    @Autowired
    private TorcedorRepository torcedorRepository;

    @Autowired
    private MapperCampanhaCampanhaDTO mapperCampanhaCampanhaDTO;

    @Autowired
    private MapperTorcedorTorcedorDto mapperTorcedorTorcedorDto;

    public CampanhaDto criarCampanha(CampanhaDto campanhaDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Campanha> listaCampanhasDB = campanhaRepository
                .findByDataVigenciaInicialGreaterThanEqualAndDataVigenciaFinalLessThanEqual(
                        LocalDate.parse(campanhaDto.getDataVigenciaInicial(), formatter),
                        LocalDate.parse(campanhaDto.getDataVigenciaFinal(), formatter)
                );

        Campanha campanha = mapperCampanhaCampanhaDTO.toEntity(campanhaDto);


        calculaNovaDataVigencia(campanha, listaCampanhasDB);

        listaCampanhasDB.add(campanha);

        campanhaRepository.save(campanha);

        CampanhaDto campanhaDto1 = mapperCampanhaCampanhaDTO.toDto(campanha);


        return campanhaDto1;
    }

    public Boolean temDataFinalIgual(LocalDate dataFinal, List<Campanha> listaCampanhasDB) {
        return listaCampanhasDB
                .stream()
                .anyMatch(campanha -> {
                    return campanha.getDataVigenciaFinal().equals(dataFinal);
                });
    }

    public void calculaNovaDataVigencia(Campanha novaCampanha, List<Campanha> listaCampanhasDB) {
        listaCampanhasDB
                .forEach(campanha -> {
                    if (
                            temDataFinalIgual(campanha.getDataVigenciaFinal().plusDays(1), listaCampanhasDB) &&
                            campanha.getDataVigenciaFinal().equals(novaCampanha.getDataVigenciaFinal())
                    ) {
                        campanha.setDataVigenciaFinal(campanha.getDataVigenciaFinal().plusDays(1));
                    }

                    campanha.setDataVigenciaFinal(campanha.getDataVigenciaFinal().plusDays(1));
                });
    }

    public List<Campanha> procurarDataVencida() {
        List<Campanha> campanhaLista = campanhaRepository.findByDataVigenciaFinalGreaterThanEqual(LocalDate.now());
        return campanhaLista;

    }

    public List<Campanha> buscarTodasAsCampanhas() {
        List<Campanha> campanhas = campanhaRepository.findAll();
        return campanhas;

    }

    public void excluirCampanha(Long id) {
        Campanha campanha = campanhaRepository.findById(id).get();

        if (campanha == null) {
            throw new CampanhaNotFoundException("Campanha não existente!");
        }

        campanhaRepository.delete(campanha);
    }

    public void atualizarCampanha(CampanhaDto campanhaDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Campanha campanha = campanhaRepository.findById(campanhaDto.getId()).get();

        if (campanha == null) {
            throw new CampanhaNotFoundException("Campanha não encontrada");
        }

        campanha.setNomeCampanha(campanhaDto.getNomeCampanha());

        TimeCoracao timeCoracao = new TimeCoracao();
        timeCoracao.setId( campanhaDto.getTimeCoracao().getId() );
        timeCoracao.setNomeTime( campanhaDto.getTimeCoracao().getNomeTime() );

        campanha.setTimeCoracao( timeCoracao );

        campanha.setDataVigenciaInicial(LocalDate.parse(campanhaDto.getDataVigenciaInicial(), formatter));
        campanha.setDataVigenciaFinal(LocalDate.parse(campanhaDto.getDataVigenciaFinal(), formatter));

        campanhaRepository.save(campanha);
    }

    public CampanhaDto buscarCampanha(Long id) {

        Campanha campanha = campanhaRepository.findById(id).get();

        if (campanha == null){
            throw new CampanhaNotFoundException("Campanha não encontrada!");
        }

        CampanhaDto campanhaDto = mapperCampanhaCampanhaDTO.toDto(campanha);


        return campanhaDto;
    }

    public CampanhaDto adicionarTorcedor(Long idTorcedor, Long idCampanha){
        Campanha campanha = campanhaRepository.findById(idCampanha).get();

        if(campanha == null){
            throw new CampanhaNotFoundException("Campanha não existe para o id informado");
        }

        Torcedor torcedor = torcedorRepository.findById(idTorcedor).get();

        if( torcedor == null ){
            throw new TorcedorNotFoundException("Torcedor não encontrado no banco de dados para o ID informado.");
        }

        campanha.getTorcedores().add( torcedor );

        campanhaRepository.save(campanha);

        CampanhaDto campanhaDto = mapperCampanhaCampanhaDTO.toDto(campanha);

        return campanhaDto;
    }

    public List<TorcedorDto> buscarTorcedoresDaCampanha(Long idCampanha){
        Campanha campanha = campanhaRepository.findById(idCampanha).get();

        List<TorcedorDto> torcedorDtos = campanha.getTorcedores()
                .stream()
                .map(torcedor -> {
                    TorcedorDto torcedorDto = mapperTorcedorTorcedorDto.toDto(torcedor);

                    return torcedorDto;
                })
                .collect(Collectors.toList());

        return torcedorDtos;
    }


}