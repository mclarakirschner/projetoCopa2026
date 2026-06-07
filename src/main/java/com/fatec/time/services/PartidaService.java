package com.fatec.time.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.time.dtos.PartidaRequest;
import com.fatec.time.dtos.PartidaResponse;
import com.fatec.time.entities.Partida;
import com.fatec.time.entities.Time;
import com.fatec.time.mappers.PartidaMapper;
import com.fatec.time.repositories.PartidaRepository;
import com.fatec.time.repositories.TimeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository repository;
    @Autowired
    private TimeRepository timeRepository;

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

        Partida p = PartidaMapper.toEntity(partida);

        // buscar times
        Time casa = timeRepository.findByNome(partida.timeCasa());
        Time fora = timeRepository.findByNome(partida.timeVisitante());

        int golsCasa = partida.golsCasa();
        int golsFora = partida.golsVisitante();

        // lógica de pontos
        if (golsCasa > golsFora) {
            casa.setPontos(casa.getPontos() + 3);
        } else if (golsCasa < golsFora) {
            fora.setPontos(fora.getPontos() + 3);
        } else {
            casa.setPontos(casa.getPontos() + 1);
            fora.setPontos(fora.getPontos() + 1);
        }

        // salvar times atualizados
        timeRepository.save(casa);
        timeRepository.save(fora);

        // salvar partida
        Partida saved = repository.save(p);

        return PartidaMapper.toDTO(saved);
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