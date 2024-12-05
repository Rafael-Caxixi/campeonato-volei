package com.example.projeto.campeonato.volei.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private Integer id;

    private String nome;

    private LocalDateTime dataCriacao;

    private String nomeTecnico;

    private String cidade;

    private Double orcamento;

    @OneToMany(mappedBy = "time", fetch = FetchType.LAZY)
    private List<Contratacao> contratacoes = new ArrayList<>();


    public Time(String nome, String nomeTecnico, String cidade, Double orcamento) {
        this.nome = nome;
        this.dataCriacao = LocalDateTime.now();
        this.nomeTecnico = nomeTecnico;
        this.cidade = cidade;
        this.orcamento = orcamento;
    }
}
