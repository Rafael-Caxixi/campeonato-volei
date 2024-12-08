package com.example.projeto.campeonato.volei.dto;

import com.example.projeto.campeonato.volei.domain.Time;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoTimeDto(@NotNull Integer id,@NotNull String nome,
                                 @NotBlank String nomeTecnico, @NotBlank String cidade,
                                 @NotNull Double orcamento){
}
