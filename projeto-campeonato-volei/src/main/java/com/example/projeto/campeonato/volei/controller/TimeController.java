package com.example.projeto.campeonato.volei.controller;


import com.example.projeto.campeonato.volei.domain.Time;
import com.example.projeto.campeonato.volei.repository.TimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeRepository repository;

    @GetMapping
    public ResponseEntity<List<Time>> listar(){
        List lista = repository.findAll();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody Time time){
        repository.save(time);
        return ResponseEntity.ok("Cadastro efetuado com sucesso");
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody Time time){
        repository.save(time);
        return ResponseEntity.ok("Time atualizado");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Long id){
        Time time = repository.getReferenceById(id);
        if(repository.existsById(id)){
            repository.delete(time);
            return ResponseEntity.ok("Time deletado");
        }
        return ResponseEntity.badRequest().build();
    }

}
