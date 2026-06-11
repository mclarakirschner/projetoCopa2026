package com.fatec.time.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.time.dtos.PartidaResponse;
import com.fatec.time.dtos.PartidaRequest;
import com.fatec.time.services.PartidaService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
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
    public ResponseEntity<PartidaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // add delete, create e update
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<PartidaResponse> save(@Valid @RequestBody PartidaRequest request) {

        PartidaResponse p = service.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.id())
                .toUri();

        return ResponseEntity.created(location).body(p);
    }

    @PutMapping("{id}")
    public ResponseEntity<PartidaResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PartidaRequest partida) {

        PartidaResponse response = service.update(id, partida);

        return ResponseEntity.ok(response);
    }
}