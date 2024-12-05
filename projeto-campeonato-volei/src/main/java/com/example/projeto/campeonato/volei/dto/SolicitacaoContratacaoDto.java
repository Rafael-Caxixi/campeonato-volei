package com.example.projeto.campeonato.volei.dto;

import jakarta.validation.constraints.NotNull;

public record SolicitacaoContratacaoDto(@NotNull Integer idTime, @NotNull Integer idJogador) {
}
