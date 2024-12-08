package com.example.projeto.campeonato.volei.repository;

import com.example.projeto.campeonato.volei.domain.Contratacao;
import com.example.projeto.campeonato.volei.domain.Time;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContratacaoRepository extends JpaRepository<Contratacao, Integer> {

    List<Contratacao> findAllByTimeId(Integer timeId);

    boolean existsByTimeIdAndJogadorId(@NotNull Integer integer, @NotNull Integer integer1);

    Integer getReferenceByTimeIdAndJogadorId(@NotNull Integer integer, @NotNull Integer integer1);

    Contratacao findByTimeIdAndJogadorId(@NotNull Integer idTime, @NotNull Integer idJogador);

    @Transactional
    @Modifying
    @Query("DELETE FROM Contratacao c WHERE c.time.id = :timeId AND c.jogador.id = :jogadorId")
    void deleteByTimeIdAndJogadorId(@Param("timeId") Integer timeId, @Param("jogadorId") Integer jogadorId);;
}
