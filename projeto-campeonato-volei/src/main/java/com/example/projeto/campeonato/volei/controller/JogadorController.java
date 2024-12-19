package com.example.projeto.campeonato.volei.controller;

import com.example.projeto.campeonato.volei.dto.AtualizacaoJogadorDto;
import com.example.projeto.campeonato.volei.dto.CadastroJogadorDto;
import com.example.projeto.campeonato.volei.repository.JogadorRepository;
import com.example.projeto.campeonato.volei.repository.TimeRepository;
import com.example.projeto.campeonato.volei.service.JogadorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    JogadorService service;

    @Autowired
    TimeRepository timeRepository;

    @GetMapping
    public List<CadastroJogadorDto> listar(){
        try{
        return service.listar();
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu algum erro: " + e);
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroJogadorDto jogador){

        try{
            service.cadastrar(jogador);
            return ResponseEntity.ok("Cadastro efetuado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody AtualizacaoJogadorDto dto){
        try{
        service.atualizar(dto);
            return ResponseEntity.ok("Atualização feita com sucesso");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Integer id){
        try{
            service.deletar(id);
            return ResponseEntity.ok("Jogador deletado");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
