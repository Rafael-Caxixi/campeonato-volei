package com.example.projeto.campeonato.volei.dto;

import com.example.projeto.campeonato.volei.domain.Time;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CadastroTimeDto(@NotNull String nome, @NotBlank String nomeTecnico,@NotBlank String cidade,@NotNull Double orcamento){

    public CadastroTimeDto(Time time){
        this(time.getNome(),time.getNomeTecnico(), time.getCidade(), time.getOrcamento());
    }
}
