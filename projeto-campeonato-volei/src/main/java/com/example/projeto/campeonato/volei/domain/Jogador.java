package com.example.projeto.campeonato.volei.domain;

import com.example.projeto.campeonato.volei.dto.AtualizacaoJogadorDto;
import com.example.projeto.campeonato.volei.dto.CadastroJogadorDto;
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

    private Double valorCompra;

    @OneToOne(mappedBy = "jogador", fetch = FetchType.LAZY)
    private Contratacao contratacao;


    public Jogador(CadastroJogadorDto dto) {
        this.nome = dto.nome();
        this.idade = dto.idade();
        this.posicao = dto.posicao();
        this.altura = dto.altura();
        this.valorCompra = dto.valorCompra();
    }

    public void AtualizarDto(AtualizacaoJogadorDto dto){
        this.nome = dto.nome();
        this.idade = dto.idade();
        this.posicao = dto.posicao();
        this.altura = dto.altura();
        this.valorCompra = dto.valorCompra();
    }


}
