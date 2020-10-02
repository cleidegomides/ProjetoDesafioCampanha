package com.everis.desafio_campanha.repositorys;

import com.everis.desafio_campanha.entites.TimeCoracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeCoracaoRepository extends JpaRepository<TimeCoracao, Long> {
    TimeCoracao findByNomeTimeEquals(String nomeTime);
}
