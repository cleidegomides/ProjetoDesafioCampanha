package com.everis.desafio_campanha.dto;



import lombok.Data;


@Data
public class CampanhaDto {
    private Long id;
    private String nomeCampanha;
    private String dataVigenciaInicial;
    private String dataVigenciaFinal;
    private TimeCoracaoDto timeCoracao;
}
