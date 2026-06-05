package com.fatec.time.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.time.dtos.PartidaRequest;
import com.fatec.time.dtos.PartidaResponse;
import com.fatec.time.mappers.PartidaMapper;
import com.fatec.time.repositories.PartidaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository repository;

    public List<PartidaResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(PartidaMapper::toDTO)
                .toList();
    }

    public PartidaResponse findById(Long id) {
        return repository.findById(id)
                .map(PartidaMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Partida não cadastrada"));
    }

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Partida não cadastrada");
    }

    public PartidaResponse save(PartidaRequest partida) {

        Partida p = repository.save(PartidaMapper.toEntity(partida));
        return PartidaMapper.toDTO(p);
    }

    public void update(PartidaRequest partida, Long id) {

        Partida p = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Partida não cadastrada"));

        p.setTimeCasa(partida.timeCasa());
        p.setTimeVisitante(partida.timeVisitante());
        p.setGolsCasa(partida.golsCasa());
        p.setGolsVisitante(partida.golsVisitante());

        repository.save(p);
    }
}