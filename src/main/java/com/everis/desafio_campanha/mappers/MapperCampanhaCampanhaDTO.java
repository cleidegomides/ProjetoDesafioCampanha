package com.everis.desafio_campanha.mappers;

import com.everis.desafio_campanha.dto.CampanhaDto;
import com.everis.desafio_campanha.entites.Campanha;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { MapperTimeCoracaoTimeCoracaoDTO.class, MapperTorcedorTorcedorDto.class })
public interface MapperCampanhaCampanhaDTO {
    @Mapping(source = "dataVigenciaInicial", target = "dataVigenciaInicial", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "dataVigenciaFinal", target = "dataVigenciaFinal", dateFormat = "dd/MM/yyyy")
    CampanhaDto toDto(Campanha campanha);

    @Mapping(source = "dataVigenciaInicial", target = "dataVigenciaInicial", dateFormat = "dd/MM/yyyy")
    @Mapping(source = "dataVigenciaFinal", target = "dataVigenciaFinal", dateFormat = "dd/MM/yyyy")
    Campanha toEntity(CampanhaDto campanhaDto);
}
