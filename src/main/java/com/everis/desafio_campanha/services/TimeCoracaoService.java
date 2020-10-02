package com.everis.desafio_campanha.services;

import com.everis.desafio_campanha.dto.TimeCoracaoDto;
import com.everis.desafio_campanha.entites.TimeCoracao;
import com.everis.desafio_campanha.exceptions.TimeDoCoracaoNotFoundException;
import com.everis.desafio_campanha.mappers.MapperTimeCoracaoTimeCoracaoDTO;
import com.everis.desafio_campanha.repositorys.TimeCoracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeCoracaoService {
    @Autowired
    private TimeCoracaoRepository timeCoracaoRepository;

    @Autowired
    private MapperTimeCoracaoTimeCoracaoDTO mapperTimeCoracaoTimeCoracaoDTO;

    public TimeCoracaoDto criarTime(TimeCoracaoDto timeCoracaoDto){
        TimeCoracao timeCoracao = mapperTimeCoracaoTimeCoracaoDTO.toEntity(timeCoracaoDto);



        timeCoracaoRepository.save(timeCoracao);

        return timeCoracaoDto;
    }

    public TimeCoracaoDto buscarTimeCoracao(String nomeTime){
        TimeCoracao timeCoracaoDB = timeCoracaoRepository.findByNomeTimeEquals(nomeTime);

        if (timeCoracaoDB == null){
            throw new TimeDoCoracaoNotFoundException("Time não Cadastrado");
        }

        TimeCoracaoDto timeCoracaoDto1 = mapperTimeCoracaoTimeCoracaoDTO.toDto(timeCoracaoDB);

        return timeCoracaoDto1;
    }

    public List<TimeCoracaoDto> listarTimes() {
        List<TimeCoracao> timeCoracoes = timeCoracaoRepository.findAll();

        if (timeCoracoes == null){
            throw new TimeDoCoracaoNotFoundException("Não Existe Time Coração Cadastrado");
        }

        List<TimeCoracaoDto> timeCoracaoDtos = timeCoracoes
                                                    .stream()
                                                    .map(timeCoracao -> {
                                                        TimeCoracaoDto timeCoracaoDto = mapperTimeCoracaoTimeCoracaoDTO.toDto(timeCoracao);
//
                                                        return timeCoracaoDto;
                                                    })
                                                    .collect(Collectors.toList());

        return timeCoracaoDtos;
    }


}
