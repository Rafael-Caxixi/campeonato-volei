package com.example.projeto.campeonato.volei.controller;

import com.example.projeto.campeonato.volei.domain.Jogador;
import com.example.projeto.campeonato.volei.repository.JogadorRepository;
import com.example.projeto.campeonato.volei.repository.TimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {

    @Autowired
    JogadorRepository jogadorRepository;

    @Autowired
    TimeRepository timeRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody Jogador jogador){
        int idTime = jogador.getTime_id();

        if(timeRepository.existsById(idTime)){
            jogadorRepository.save(jogador);
            return ResponseEntity.ok("Cadastro efetuado com sucesso");
        }

        return ResponseEntity.badRequest().body("Erro de parametro");
    }

}
