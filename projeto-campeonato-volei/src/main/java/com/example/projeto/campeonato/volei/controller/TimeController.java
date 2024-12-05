package com.example.projeto.campeonato.volei.controller;


import com.example.projeto.campeonato.volei.domain.Time;
import com.example.projeto.campeonato.volei.dto.TimeDto;
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
    public List<TimeDto> listar(){
        return repository.findAll()
                .stream()
                .map(TimeDto::new)
                .toList();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody Time time){
        repository.save(new Time(time.getNome(), time.getNomeTecnico(), time.getCidade(), time.getOrcamento()));
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
    public ResponseEntity<String> deletar(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            try {
                Time time = repository.getReferenceById(id);
                repository.delete(time);
                return ResponseEntity.ok("Time deletado com sucesso.");
            } catch (Exception e) {
                return ResponseEntity.status(409).body("Verifique se o time existe, ou se há jogadores nesse time." +
                        " Caso haja, exclua os jogadores primeiro");
            }
        }
        return ResponseEntity.badRequest().body("Time não encontrado.");
    }

}
