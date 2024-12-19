package com.example.projeto.campeonato.volei.service;

import com.example.projeto.campeonato.volei.domain.Jogador;
import com.example.projeto.campeonato.volei.dto.AtualizacaoJogadorDto;
import com.example.projeto.campeonato.volei.dto.CadastroJogadorDto;
import com.example.projeto.campeonato.volei.exception.EntidadeNaoEncontradaException;
import com.example.projeto.campeonato.volei.repository.JogadorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class JogadorServiceTest {

    @Mock
    private JogadorRepository repository;
    
    @InjectMocks
    private JogadorService service;


    @Captor
    private ArgumentCaptor<Jogador> jogadorArgumentCaptor;

    @Test
    void deveriaCadastrarJogador() {
        // ARRANGE
        CadastroJogadorDto cadastroJogadorDto = new CadastroJogadorDto(
                "Nome Teste", 25, "Atacante", 185, 500000.0
        );

        Jogador jogadorEsperado = new Jogador(cadastroJogadorDto);

        // ACT
        service.cadastrar(cadastroJogadorDto);

        // ASSERT
        then(repository).should().save(jogadorArgumentCaptor.capture());
        Jogador jogadorSalvo = jogadorArgumentCaptor.getValue();
        Assertions.assertEquals(jogadorEsperado, jogadorSalvo);
    }

    @Test
    void deveriaAtualizarJogadorComValoresCorretos() {
        // ARRANGE
        CadastroJogadorDto cadastroJogadorDto = new CadastroJogadorDto(
                "Nome antigo", 20, "Levantador", 190, 9000000.0
        );
        Jogador jogador = new Jogador(cadastroJogadorDto);
        jogador.setId(10);

        AtualizacaoJogadorDto dto = new AtualizacaoJogadorDto(
                10, "Novo Nome", 30, "Ponteiro", 185, 1000000.0
        );

        given(repository.getReferenceById(10)).willReturn(jogador);

        // ACT
        service.atualizar(dto);

        // ASSERT
        Assertions.assertAll(
                () -> Assertions.assertEquals(10, jogador.getId()),
                () -> Assertions.assertEquals("Novo Nome", jogador.getNome()),
                () -> Assertions.assertEquals(30, jogador.getIdade()),
                () -> Assertions.assertEquals("Ponteiro", jogador.getPosicao()),
                () -> Assertions.assertEquals(185, jogador.getAltura()),
                () -> Assertions.assertEquals(1000000.0, jogador.getValorCompra())
        );

    }

    @Test
    void deveriaDeletarJogadorComSucesso() {
        // ARRANGE
        Integer idJogador = 10;

        given(repository.existsById(idJogador)).willReturn(true);

        // ACT
        service.deletar(idJogador);

        // ASSERT
        then(repository).should().deleteById(idJogador);

        // Verifica que não houve outras interações com o repositório
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deveriaLancarExcecaoQuandoJogadorNaoExistir() {
        // ARRANGE
        Integer idJogador = 10;

        given(repository.existsById(idJogador)).willReturn(false);

        // ACT & ASSERT
        EntidadeNaoEncontradaException exception = Assertions.assertThrows(
                EntidadeNaoEncontradaException.class,
                () -> service.deletar(idJogador)
        );

        Assertions.assertEquals("Jogador com o ID 10 não encontrado.", exception.getMessage());
    }




}