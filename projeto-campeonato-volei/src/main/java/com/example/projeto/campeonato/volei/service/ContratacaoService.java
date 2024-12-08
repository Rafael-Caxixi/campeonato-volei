package com.example.projeto.campeonato.volei.service;

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
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratacaoService {

    @Autowired
    private ContratacaoRepository contratacaoRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    public List<ContratacaoDto> listar(Integer id){
        return contratacaoRepository.findAllByTimeId(id)
                .stream()
                .map(ContratacaoDto::new)
                .toList();
    }

    public void cadastrar(SolicitacaoContratacaoDto dto) {
        Time time = timeRepository.findById(dto.idTime())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Id de time não existe"));
        Jogador jogador = jogadorRepository.findById(dto.idJogador())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Id de jogador não existe"));

        if(contratacaoRepository.existsByTimeIdAndJogadorId(dto.idTime(), dto.idJogador())){
            throw new ContratacaoDuplicada("Essa contrataçao já existe");
        }
        else if (time.getOrcamento() < jogador.getValorCompra()) {
            throw new SaldoInsuficienteException("Saldo insuficiente");
        }

        Contratacao contratacao = new Contratacao(time, jogador);
        time.contratar(jogador.getValorCompra());
        contratacaoRepository.save(contratacao);
    }

    public void cancelarContratacao(@Valid CancelamentoDeContratacaoDto dto) {
        Time time = timeRepository.findById(dto.idTime())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Id de time não existe"));

        Jogador jogador = jogadorRepository.findById(dto.idJogador())
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Id de jogador não existe"));

        Contratacao contratacao = contratacaoRepository.findByTimeIdAndJogadorId(dto.idTime(), dto.idJogador());
        if (contratacao == null) {
            throw new EntidadeNaoEncontradaException("Contratação não encontrada");
        }

        contratacaoRepository.deleteByTimeIdAndJogadorId(dto.idTime(), dto.idJogador());
    }

}
