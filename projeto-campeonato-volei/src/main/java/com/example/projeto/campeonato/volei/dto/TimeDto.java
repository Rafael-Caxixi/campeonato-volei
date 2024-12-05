package com.example.projeto.campeonato.volei.dto;

import com.example.projeto.campeonato.volei.domain.Time;

import java.time.LocalDateTime;

public record TimeDto (Integer id, String nome, LocalDateTime dataCriacao,String nomeTecnico, String cidade){

    public TimeDto(Time time){
        this(time.getId(), time.getNome(), time.getDataCriacao(), time.getNomeTecnico(), time.getCidade());
    }
}
