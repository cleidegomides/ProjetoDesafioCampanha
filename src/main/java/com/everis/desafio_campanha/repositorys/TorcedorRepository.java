package com.everis.desafio_campanha.repositorys;

import com.everis.desafio_campanha.entites.Torcedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TorcedorRepository extends JpaRepository<Torcedor, Long> {
    Torcedor findByEmail(String email);
}
