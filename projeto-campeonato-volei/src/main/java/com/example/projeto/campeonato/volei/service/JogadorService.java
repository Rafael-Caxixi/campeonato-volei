package com.example.projeto.campeonato.volei.service;

import com.example.projeto.campeonato.volei.domain.Jogador;
import com.example.projeto.campeonato.volei.dto.AtualizacaoJogadorDto;
import com.example.projeto.campeonato.volei.dto.CadastroJogadorDto;
import com.example.projeto.campeonato.volei.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository repository;

    public List<CadastroJogadorDto> listar(){
        return repository.findAll()
                .stream()
                .map(CadastroJogadorDto::new)
                .toList();
    }

    public void cadastrar(CadastroJogadorDto dto){
        repository.save(new Jogador(dto));
    }

    public void atualizar(AtualizacaoJogadorDto dto){
        Jogador jogador = repository.getReferenceById(dto.id());
        jogador.AtualizarDto(dto);
    }

    public void deletar(Integer id){
        Jogador jogador = repository.getReferenceById(id);

        if(repository.existsById(id)){
            repository.delete(jogador);
        }
    }



}
