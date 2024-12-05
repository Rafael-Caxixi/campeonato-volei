package com.example.projeto.campeonato.volei.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoJogadorDto(@NotNull Integer id,@NotBlank String nome, @NotNull Integer idade, @NotBlank String posicao,
                                    @NotNull Integer altura, @NotNull Double valorCompra) {

}
