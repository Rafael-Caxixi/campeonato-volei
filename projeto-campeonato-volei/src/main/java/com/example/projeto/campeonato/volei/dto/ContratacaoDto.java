package com.example.projeto.campeonato.volei.dto;

import com.example.projeto.campeonato.volei.domain.Contratacao;
import com.example.projeto.campeonato.volei.domain.Jogador;
import com.example.projeto.campeonato.volei.domain.Time;

import java.time.LocalDateTime;

public record ContratacaoDto(String nomeTime, String nomeJogador) {

    public ContratacaoDto(Contratacao contratacao) {
        this(contratacao.getTime().getNome(), contratacao.getJogador().getNome());
    }
}