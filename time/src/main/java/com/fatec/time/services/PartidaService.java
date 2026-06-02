package com.fatec.time.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.time.dtos.PartidaResponse;
import com.fatec.time.entities.Partida;
import com.fatec.time.repositories.PartidaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository repository;

    public List<PartidaResponse> findAll() {
        return repository.findAll();
    }

    public Partida findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Partida não encontrada"));
    }

    // add delete create e update

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Partida não cadastrada");
        }
    }

    public Partida save(Partida partida) {
        return repository.save(partida);
    }

    public void update(Partida partida, Long id) {

        Partida p = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Partida não cadastrada"));

        p.setTimeCasa(partida.getTimeCasa());
        p.setTimeVisitante(partida.getTimeVisitante());
        p.setGolsCasa(partida.getGolsCasa());
        p.setGolsVisitante(partida.getGolsVisitante());

        repository.save(p);
    }

}
