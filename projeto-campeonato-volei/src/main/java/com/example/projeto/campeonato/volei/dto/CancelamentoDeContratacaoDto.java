package com.example.projeto.campeonato.volei.dto;

import jakarta.validation.constraints.NotNull;

public record CancelamentoDeContratacaoDto(@NotNull Integer idTime,@NotNull Integer idJogador) {
}
