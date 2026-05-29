package com.fatec.time.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fatec.time.entities.Time;

public interface timeRepository extends JpaRepository<Time, Long> {

}
