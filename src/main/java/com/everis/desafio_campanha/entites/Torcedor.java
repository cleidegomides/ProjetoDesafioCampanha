package com.everis.desafio_campanha.entites;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;



@Data
@Entity
public class Torcedor {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha = "";
    private String confirmarSenha;
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name= "nomeTime")
    private TimeCoracao timeCoracao;
}
