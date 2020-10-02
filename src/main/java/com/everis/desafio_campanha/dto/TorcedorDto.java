package com.everis.desafio_campanha.dto;

import lombok.Data;


@Data
public class TorcedorDto {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String confirmarSenha = "";
    private String dataNascimento;
    private TimeCoracaoDto timeCoracao;
}
