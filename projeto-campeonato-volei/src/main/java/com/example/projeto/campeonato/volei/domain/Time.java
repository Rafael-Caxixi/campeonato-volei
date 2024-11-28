package com.example.projeto.campeonato.volei.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "times")
@Entity(name = "Time")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Date data_criacao;

    private String nome_tecnico;

    private String cidade;

    public Time(String nome, Date data_criacao, String nome_tecnico, String cidade) {
        this.nome = nome;
        this.data_criacao = data_criacao;
        this.nome_tecnico = nome_tecnico;
        this.cidade = cidade;
    }
}