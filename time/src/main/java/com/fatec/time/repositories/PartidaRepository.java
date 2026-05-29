package com.fatec.time.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.time.entities.Partida;

public interface PartidaRepository extends JpaRepository<Partida, Long> {

}
