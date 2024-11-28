package com.example.projeto.campeonato.volei.domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "jogadores")
@Entity(name = "Jogador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer idade;

    private String posicao;

    private Integer altura;

    private Integer time_id;

    public Jogador(String nome, Integer idade, String posicao, Integer altura, Integer time_id) {
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.altura = altura;
        this.time_id = time_id;
    }


}
