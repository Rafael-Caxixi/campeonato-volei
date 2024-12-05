package com.example.projeto.campeonato.volei.repository;

import com.example.projeto.campeonato.volei.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Integer> {

    boolean existsById(Integer id);

}
