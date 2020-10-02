package com.everis.desafio_campanha.mappers;

import com.everis.desafio_campanha.dto.TimeCoracaoDto;
import com.everis.desafio_campanha.entites.TimeCoracao;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperTimeCoracaoTimeCoracaoDTO {
    TimeCoracaoDto toDto(TimeCoracao timeCoracao);
    TimeCoracao toEntity(TimeCoracaoDto timeCoracaoDto);
}
