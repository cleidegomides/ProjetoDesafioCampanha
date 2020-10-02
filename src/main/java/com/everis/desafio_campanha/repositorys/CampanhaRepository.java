package com.everis.desafio_campanha.repositorys;

import com.everis.desafio_campanha.entites.Campanha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface CampanhaRepository extends JpaRepository<Campanha, Long> {
    List<Campanha> findByDataVigenciaInicialGreaterThanEqualAndDataVigenciaFinalLessThanEqual(LocalDate dataInicial, LocalDate dataFinal);

    List<Campanha> findByDataVigenciaFinalGreaterThanEqual(LocalDate data);
}
