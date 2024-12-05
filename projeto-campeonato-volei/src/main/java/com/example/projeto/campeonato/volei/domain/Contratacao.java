package com.example.projeto.campeonato.volei.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contratacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Contratacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dataCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    private Time time;

    @OneToOne(fetch = FetchType.LAZY)
    private Jogador jogador;

    public Contratacao(Time time, Jogador jogador) {
        this.dataCompra = LocalDateTime.now();
        this.time = time;
        this.jogador = jogador;
    }
}
