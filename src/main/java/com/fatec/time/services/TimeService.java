package com.fatec.time.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.time.entities.Time;
import com.fatec.time.dtos.TimeRequest;
import com.fatec.time.dtos.TimeResponse;
import com.fatec.time.mappers.TimeMapper;
import com.fatec.time.repositories.TimeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TimeService {

    @Autowired
    private TimeRepository repository;

    public List<TimeResponse> findAll() { // Busca todos os times cadastrados.
        return repository.findAll()
                .stream()
                .map(TimeMapper::toDTO)
                .toList();
    }

    public TimeResponse findById(Long id) { // Busca um time pelo id.
        return repository.findById(id)
                .map(TimeMapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Time não cadastrado"));
    }

    public void deleteById(Long id) { // Deleta um time pelo id.
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Time não cadastrado");
    }

    public TimeResponse save(TimeRequest time) { // Salva um time.

        Time t = repository.save(TimeMapper.toEntity(time));
        return TimeMapper.toDTO(t);
    }

    public void update(TimeRequest time, Long id) { // Atualiza um time.

        Time t = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time não cadastrado"));

        t.setNome(time.nome());
        t.setGrupo(time.grupo());

        repository.save(t);
    }

    public List<Time> ranking() { // Retorna a lista de times ordenada por pontos.
        return repository.findAllByOrderByPontosDesc();
    }
}