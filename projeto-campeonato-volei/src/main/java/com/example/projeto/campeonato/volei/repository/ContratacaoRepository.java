package com.example.projeto.campeonato.volei.repository;

import com.example.projeto.campeonato.volei.domain.Contratacao;
import com.example.projeto.campeonato.volei.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContratacaoRepository extends JpaRepository<Contratacao, Integer> {

    List<Contratacao> findAllByTimeId(Integer timeId);
}
