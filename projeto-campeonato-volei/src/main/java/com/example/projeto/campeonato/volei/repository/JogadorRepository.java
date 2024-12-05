package com.example.projeto.campeonato.volei.repository;

import com.example.projeto.campeonato.volei.domain.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
}
