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
    private Integer id;

    private String nome;

    private Integer idade;

    private String posicao;

    private Integer altura;

    private Integer timeId;

    private Double valorCompra;

    public Jogador(String nome, Integer idade, String posicao, Integer altura, Integer timeId, Double valorCompra) {
        this.nome = nome;
        this.idade = idade;
        this.posicao = posicao;
        this.altura = altura;
        this.timeId = timeId;
        this.valorCompra = valorCompra;
    }


}
