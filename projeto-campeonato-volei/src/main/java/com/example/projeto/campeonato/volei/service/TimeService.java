package com.example.projeto.campeonato.volei.service;

import com.example.projeto.campeonato.volei.domain.Time;
import com.example.projeto.campeonato.volei.dto.AtualizacaoTimeDto;
import com.example.projeto.campeonato.volei.dto.CadastroTimeDto;
import com.example.projeto.campeonato.volei.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    private TimeRepository repository;

    public List<CadastroTimeDto> listar(){
        return repository.findAll()
                .stream()
                .map(CadastroTimeDto::new)
                .toList();
    }

    public void cadastrar(CadastroTimeDto dto){
        repository.save(new Time(dto));
    }

    public void atualizar(AtualizacaoTimeDto dto){
        Time time = repository.getReferenceById(dto.id());
        time.AtualizarTime(dto);
    }

    public void deletar(Integer id){
                Time time = repository.getReferenceById(id);
                repository.delete(time);
    }

}
