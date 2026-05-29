package com.fatec.time.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.time.entities.Time;
import com.fatec.time.repositories.TimeRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TimeService {

    @Autowired
    private TimeRepository repository;

    public List<Time> findAll() {
        return repository.findAll();
    }

    public Time findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Time não encontrado"));
    }

    // add o save update e delete

    public void deleteById(Long id) {
        if (repository.existsById(id))
            repository.deleteById(id);
        else
            throw new EntityNotFoundException("Produto não cadastrado");
    }

    public Time save(Time time) {
        return repository.save(time);
    }

    public void update(Time time, Long id) {
        Time p = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não cadastrado"));

        p.setNome(time.getNome());
        p.setGrupo(time.getGrupo());
        p.setPontos(time.getPontos());

        repository.save(p);

    }
}
