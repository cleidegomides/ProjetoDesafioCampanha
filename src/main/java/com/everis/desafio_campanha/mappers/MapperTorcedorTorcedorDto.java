package com.everis.desafio_campanha.mappers;

import com.everis.desafio_campanha.dto.TorcedorDto;
import com.everis.desafio_campanha.entites.Torcedor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {MapperTimeCoracaoTimeCoracaoDTO.class})
public interface MapperTorcedorTorcedorDto {
    @Mapping(source = "dataNascimento", target = "dataNascimento", dateFormat = "dd/MM/yyyy")
    TorcedorDto toDto(Torcedor torcedor);

    @Mapping(source = "dataNascimento", target = "dataNascimento", dateFormat = "dd/MM/yyyy")
    Torcedor toEntity(TorcedorDto torcedorDto);
}
