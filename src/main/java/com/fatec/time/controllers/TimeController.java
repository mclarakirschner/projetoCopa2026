package com.fatec.time.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.time.dtos.TimeResponse;
import com.fatec.time.entities.Time;
import com.fatec.time.dtos.TimeRequest;
import com.fatec.time.services.TimeService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*") // permite que o angular acesse a API
@RestController // recebe requisições http e devolve respostas em json
@RequestMapping("/times")
public class TimeController {

    @Autowired // O Spring cria automaticamente um objeto TimeService.
    private TimeService service;

    @GetMapping // busca todos os times
    public ResponseEntity<List<TimeResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}") // busca um time pelo id
    public ResponseEntity<TimeResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/ranking") // busca todos os times em ordem de pontuação
    public ResponseEntity<List<Time>> ranking() {
        return ResponseEntity.ok(service.ranking());
    }

    @DeleteMapping("{id}") // deleta um time pelo id
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping // insere um novo time
    public ResponseEntity<TimeResponse> save(@Valid @RequestBody TimeRequest request) {

        TimeResponse p = service.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.id())
                .toUri();

        return ResponseEntity.created(location).body(p);

    }

    @PutMapping("{id}") // atualiza um time pelo id
    public ResponseEntity<Void> update(@PathVariable long id,
            @Valid @RequestBody TimeRequest request) {
        service.update(request, id);
        return ResponseEntity.noContent().build();
    }

}