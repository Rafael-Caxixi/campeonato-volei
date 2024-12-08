package com.example.projeto.campeonato.volei.controller;


import com.example.projeto.campeonato.volei.domain.Jogador;
import com.example.projeto.campeonato.volei.domain.Time;
import com.example.projeto.campeonato.volei.dto.AtualizacaoTimeDto;
import com.example.projeto.campeonato.volei.dto.CadastroTimeDto;
import com.example.projeto.campeonato.volei.repository.TimeRepository;
import com.example.projeto.campeonato.volei.service.TimeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeRepository repository;

    @Autowired
    private TimeService service;

    @GetMapping
    public List<CadastroTimeDto> listar() {
        try {
            return service.listar();
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro no sistema: " + e);
        }

    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroTimeDto dto) {
        try {
            service.cadastrar(dto);
            return ResponseEntity.ok("Cadastro efetuado com sucesso");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao cadatrar: " + e.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody AtualizacaoTimeDto dto) {
        try {
            service.atualizar(dto);
            return ResponseEntity.ok("Time atualizado");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Integer id) {
        if (repository.existsById(id)) {
            try {
                service.deletar(id);
                return ResponseEntity.ok("Jogador deletado");
            } catch (RuntimeException e) {
                return ResponseEntity.badRequest().body("Jogador existe mas não foi possível deleta-lo");
            }
        } else {
            return ResponseEntity.badRequest().body("Jogador não encontrado");
        }
    }

}
