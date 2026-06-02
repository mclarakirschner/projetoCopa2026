package com.fatec.time.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.time.dtos.PartidaResponse;
import com.fatec.time.entities.Partida;
import com.fatec.time.services.PartidaService;

@RestController
@RequestMapping("/partidas")
public class PartidaController {

    @Autowired
    private PartidaService service;

    @GetMapping
    public ResponseEntity<List<PartidaResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Partida> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // add delete, create e update
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Partida> save(@RequestBody Partida partida) {

        Partida p = service.save(partida);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();

        return ResponseEntity.created(location).body(p);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
            @RequestBody Partida partida) {

        service.update(partida, id);
        return ResponseEntity.noContent().build();
    }
}