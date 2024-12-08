package com.example.projeto.campeonato.volei.controller;

import com.example.projeto.campeonato.volei.domain.Contratacao;
import com.example.projeto.campeonato.volei.domain.Jogador;
import com.example.projeto.campeonato.volei.domain.Time;
import com.example.projeto.campeonato.volei.dto.CancelamentoDeContratacaoDto;
import com.example.projeto.campeonato.volei.dto.ContratacaoDto;
import com.example.projeto.campeonato.volei.dto.SolicitacaoContratacaoDto;
import com.example.projeto.campeonato.volei.exception.ContratacaoDuplicada;
import com.example.projeto.campeonato.volei.exception.EntidadeNaoEncontradaException;
import com.example.projeto.campeonato.volei.exception.SaldoInsuficienteException;
import com.example.projeto.campeonato.volei.repository.ContratacaoRepository;
import com.example.projeto.campeonato.volei.repository.JogadorRepository;
import com.example.projeto.campeonato.volei.repository.TimeRepository;
import com.example.projeto.campeonato.volei.service.ContratacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contratacoes")
public class ContratacaoController {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private ContratacaoRepository contratacaoRepository;

    @Autowired
    private ContratacaoService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<ContratacaoDto>> listarContratacoes(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.listar(id));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid SolicitacaoContratacaoDto dto) {
        try {
            service.cadastrar(dto);
            return ResponseEntity.ok("Contratação feita com sucesso");
        } catch (SaldoInsuficienteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (ContratacaoDuplicada e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/cancelar-contratacao")
    @Transactional
    public ResponseEntity<String> cancelarContratacao(@RequestBody @Valid CancelamentoDeContratacaoDto dto){
        try{
            service.cancelarContratacao(dto);
            return ResponseEntity.ok("Cancelamento feito com sucesso");
        } catch (EntidadeNaoEncontradaException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
