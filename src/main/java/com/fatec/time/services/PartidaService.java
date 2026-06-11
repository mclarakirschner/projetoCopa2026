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

                Partida p = repository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Partida não cadastrada"));

                return new PartidaResponse(
                                p.getId(),
                                p.getTimeCasa().getId(),
                                p.getTimeCasa().getNome(),
                                p.getTimeVisitante().getId(),
                                p.getTimeVisitante().getNome(),
                                p.getGolsCasa(),
                                p.getGolsVisitante());
        }

        public void deleteById(Long id) {

                if (!repository.existsById(id)) {
                        throw new EntityNotFoundException("Partida não cadastrada");
                }

                repository.deleteById(id);

                recalcularRanking();
        }

        public PartidaResponse save(PartidaRequest request) {

                Time casa = timeRepository.findById(request.timeCasaId())
                                .orElseThrow();

                Time visitante = timeRepository.findById(request.timeVisitanteId())
                                .orElseThrow();

                Partida p = PartidaMapper.toEntity(request, casa, visitante);

                Partida salva = repository.save(p);

                recalcularRanking();

                return PartidaMapper.toDTO(salva);
        }

        public PartidaResponse update(Long id, PartidaRequest request) {

                Partida p = repository.findById(id)
                                .orElseThrow();

                Time casa = timeRepository.findById(request.timeCasaId())
                                .orElseThrow();

                Time visitante = timeRepository.findById(request.timeVisitanteId())
                                .orElseThrow();

                p.setTimeCasa(casa);
                p.setTimeVisitante(visitante);
                p.setGolsCasa(request.golsCasa());
                p.setGolsVisitante(request.golsVisitante());

                Partida salva = repository.save(p);

                recalcularRanking();

                return PartidaMapper.toDTO(salva);
        }

        private void recalcularRanking() {

                List<Time> times = timeRepository.findAll();

                // zera pontos
                for (Time t : times) {
                        t.setPontos(0);
                }

                List<Partida> partidas = repository.findAll();

                for (Partida p : partidas) {

                        Time casa = p.getTimeCasa();
                        Time visitante = p.getTimeVisitante();

                        if (p.getGolsCasa() > p.getGolsVisitante()) {
                                casa.setPontos(casa.getPontos() + 3);
                        } else if (p.getGolsCasa() < p.getGolsVisitante()) {
                                visitante.setPontos(visitante.getPontos() + 3);
                        } else {
                                casa.setPontos(casa.getPontos() + 1);
                                visitante.setPontos(visitante.getPontos() + 1);
                        }
                }

                timeRepository.saveAll(times);
                timeRepository.flush();
        }
}