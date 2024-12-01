package com.example.projeto.campeonato.volei.controller;

import com.example.projeto.campeonato.volei.domain.Jogador;
import com.example.projeto.campeonato.volei.repository.JogadorRepository;
import com.example.projeto.campeonato.volei.repository.TimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    JogadorRepository jogadorRepository;

    @Autowired
    TimeRepository timeRepository;

    @GetMapping
    public ResponseEntity<List<Jogador>> listar(){
        List lista = jogadorRepository.findAll();
        if(lista.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody Jogador jogador){
        Integer idTime = jogador.getTimeId();

        if(timeRepository.existsById(idTime)){
            jogadorRepository.save(jogador);
            return ResponseEntity.ok("Cadastro efetuado com sucesso");
        }

        return ResponseEntity.badRequest().body("Erro de parametro");
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody Jogador jogador){
     jogadorRepository.save(jogador);
     return ResponseEntity.ok("Jogador atualizado");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Long id){
        Jogador jogador = jogadorRepository.getReferenceById(id);

        if(jogadorRepository.existsById(id)){
            jogadorRepository.delete(jogador);
            return ResponseEntity.ok("Jogador deletado");
        }
        return ResponseEntity.badRequest().body("Jogador não existe");

    }

}
