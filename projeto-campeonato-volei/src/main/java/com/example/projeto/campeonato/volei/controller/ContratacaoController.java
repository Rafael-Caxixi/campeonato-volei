package com.example.projeto.campeonato.volei.controller;

import com.example.projeto.campeonato.volei.domain.Contratacao;
import com.example.projeto.campeonato.volei.domain.Jogador;
import com.example.projeto.campeonato.volei.domain.Time;
import com.example.projeto.campeonato.volei.dto.ContratacaoDto;
import com.example.projeto.campeonato.volei.dto.SolicitacaoContratacaoDto;
import com.example.projeto.campeonato.volei.repository.ContratacaoRepository;
import com.example.projeto.campeonato.volei.repository.JogadorRepository;
import com.example.projeto.campeonato.volei.repository.TimeRepository;
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

    @GetMapping("/{id}")
    public List<ContratacaoDto> listarContratacoes(@PathVariable Integer id) {
        return contratacaoRepository.findAllByTimeId(id)
                .stream()
                .map(ContratacaoDto::new)
                .toList();
    }



    @PostMapping
    @Transactional
    public ResponseEntity<String> cadatrar(@RequestBody @Valid SolicitacaoContratacaoDto dto){
        Time time = timeRepository.getReferenceById(dto.idTime());
        Jogador jogador = jogadorRepository.getReferenceById(dto.idJogador());
        try{
        Contratacao contratacao = new Contratacao(time,jogador);
        contratacaoRepository.save(contratacao);
        return ResponseEntity.ok("Contratação feita com sucesso");
        } catch (Exception e) {
         return ResponseEntity.badRequest().body("Time ou jogador não existe");
        }
    }
}
