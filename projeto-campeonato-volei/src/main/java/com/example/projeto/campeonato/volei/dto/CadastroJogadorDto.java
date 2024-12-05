package com.example.projeto.campeonato.volei.dto;

import com.example.projeto.campeonato.volei.domain.Jogador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroJogadorDto(@NotBlank String nome, @NotNull Integer idade, @NotBlank String posicao, @NotNull Integer altura, @NotNull Double valorCompra) {

    public CadastroJogadorDto(Jogador jogador){
        this(jogador.getNome(), jogador.getIdade(), jogador.getPosicao(), jogador.getAltura(), jogador.getValorCompra());
    }
}