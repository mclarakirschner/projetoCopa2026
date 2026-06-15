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

@CrossOrigin(origins = "*") // permite que o angular acesse a API
@RestController // recebe requisições http e devolve respostas em json
@RequestMapping("/partidas")
public class PartidaController {

    @Autowired // O Spring cria automaticamente um objeto TimeService.
    private PartidaService service;

    @GetMapping // busca todas as partidas
    public ResponseEntity<List<PartidaResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}") // busca uma partida pelo id
    public ResponseEntity<PartidaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}") // deleta uma partida pelo id
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping // insere uma nova partida
    public ResponseEntity<PartidaResponse> save(@Valid @RequestBody PartidaRequest request) {

        PartidaResponse p = service.save(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(p.id())
                .toUri();

        return ResponseEntity.created(location).body(p);
    }

    @PutMapping("{id}") // atualiza uma partida pelo id
    public ResponseEntity<PartidaResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody PartidaRequest partida) {

        PartidaResponse response = service.update(id, partida);

        return ResponseEntity.ok(response);
    }
}