package com.example.projeto.campeonato.volei.domain;

import com.example.projeto.campeonato.volei.dto.AtualizacaoJogadorDto;
import com.example.projeto.campeonato.volei.dto.AtualizacaoTimeDto;
import com.example.projeto.campeonato.volei.dto.CadastroTimeDto;
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


    public Time(CadastroTimeDto dto) {
        this.nome = dto.nome();
        this.dataCriacao = LocalDateTime.now();
        this.nomeTecnico = dto.nomeTecnico();
        this.cidade = dto.cidade();
        this.orcamento = dto.orcamento();
    }

    public void AtualizarTime(AtualizacaoTimeDto dto){
        this.nome = dto.nome();
        this.dataCriacao = LocalDateTime.now();
        this.nomeTecnico = dto.nomeTecnico();
        this.cidade = dto.cidade();
        this.orcamento = dto.orcamento();
    }

    public void contratar(Double valorCompra){
            this.orcamento -= valorCompra;
    }


}
