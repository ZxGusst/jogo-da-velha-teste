package br.com.grupo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes do Jogo da Velha")
class JogoDaVelhaTest {

    private JogoDaVelha jogo;

    @BeforeEach
    void setUp() {
        jogo = new JogoDaVelha();
    }

    @Test
    @DisplayName("Tabuleiro inicial deve estar vazio")
    void testTabuleiroInicialVazio() {
        char[] tab = jogo.getTabuleiro();
        for (char c : tab) {
            assertEquals(' ', c);
        }
    }

    @Test
    @DisplayName("Jogada válida deve retornar true")
    void testJogadaValida() {
        boolean resultado = jogo.fazerJogada(0, 'X');
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Jogada em posição ocupada deve retornar false")
    void testJogadaPosicaoOcupada() {
        jogo.fazerJogada(4, 'X');
        boolean resultado = jogo.fazerJogada(4, 'O');
        assertFalse(resultado);
    }

    @Test
    @DisplayName("Posição inválida deve lançar exceção")
    void testPosicaoInvalida() {
        assertThrows(IllegalArgumentException.class, () -> jogo.fazerJogada(9, 'X'));
    }

    @Test
    @DisplayName("X vence na linha horizontal superior")
    void testVencedorLinhaHorizontal() {
        jogo.fazerJogada(0, 'X');
        jogo.fazerJogada(1, 'X');
        jogo.fazerJogada(2, 'X');
        assertEquals('X', jogo.verificarVencedor());
    }

    @Test
    @DisplayName("O vence na coluna vertical")
    void testVencedorColunaVertical() {
        jogo.fazerJogada(0, 'O');
        jogo.fazerJogada(3, 'O');
        jogo.fazerJogada(6, 'O');
        assertEquals('O', jogo.verificarVencedor());
    }

    @Test
    @DisplayName("X vence na diagonal principal")
    void testVencedorDiagonal() {
        jogo.fazerJogada(0, 'X');
        jogo.fazerJogada(4, 'X');
        jogo.fazerJogada(8, 'X');
        assertEquals('X', jogo.verificarVencedor());
    }

    @Test
    @DisplayName("Sem vencedor retorna espaço")
    void testSemVencedor() {
        jogo.fazerJogada(0, 'X');
        jogo.fazerJogada(1, 'O');
        assertEquals(' ', jogo.verificarVencedor());
    }

    @Test
    @DisplayName("Tabuleiro cheio sem vencedor é empate")
    void testEmpate() {
        // X O X
        // X X O
        // O X O
        jogo.fazerJogada(0, 'X'); jogo.fazerJogada(1, 'O'); jogo.fazerJogada(2, 'X');
        jogo.fazerJogada(3, 'X'); jogo.fazerJogada(4, 'X'); jogo.fazerJogada(5, 'O');
        jogo.fazerJogada(6, 'O'); jogo.fazerJogada(7, 'X'); jogo.fazerJogada(8, 'O');
        assertTrue(jogo.isTabuleiroCheio());
        assertEquals(' ', jogo.verificarVencedor());
    }

    @Test
    @DisplayName("Reiniciar deve limpar o tabuleiro")
    void testReiniciar() {
        jogo.fazerJogada(0, 'X');
        jogo.fazerJogada(4, 'O');
        jogo.reiniciar();
        char[] tab = jogo.getTabuleiro();
        for (char c : tab) {
            assertEquals(' ', c);
        }
    }

    @Test
    @DisplayName("Jogo encerrado quando há vencedor")
    void testJogoEncerradoComVencedor() {
        jogo.fazerJogada(0, 'X');
        jogo.fazerJogada(1, 'X');
        jogo.fazerJogada(2, 'X');
        assertTrue(jogo.isJogoEncerrado());
    }

    // ─── TESTE QUE FALHA PROPOSITALMENTE ─────────────────────────────────────
    @Test
    @DisplayName("FALHA INTENCIONAL - demonstra detecção de erro no CI")
    void testFalhaIntencional() {
        jogo.fazerJogada(0, 'X');
        // Afirmação errada de propósito: X não pode vencer sem linha completa
        assertEquals('X', jogo.verificarVencedor(), "FALHA INTENCIONAL: Este teste deve falhar!");
    }
}
