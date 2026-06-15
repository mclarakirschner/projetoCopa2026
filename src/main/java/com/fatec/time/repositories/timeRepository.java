package com.fatec.time.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.time.entities.Time;

public interface TimeRepository extends JpaRepository<Time, Long> {

    Time findByNome(String nome);

    List<Time> findAllByOrderByPontosDesc();
}
