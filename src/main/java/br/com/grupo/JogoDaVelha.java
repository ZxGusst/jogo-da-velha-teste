package br.com.grupo;

public class JogoDaVelha {

    private char[] tabuleiro;

    public JogoDaVelha() {
        tabuleiro = new char[9];
        reiniciar();
    }

    public void reiniciar() {
        for (int i = 0; i < 9; i++) {
            tabuleiro[i] = ' ';
        }
    }

    public boolean fazerJogada(int posicao, char jogador) {
        if (posicao < 0 || posicao > 8) {
            throw new IllegalArgumentException("Posição inválida: deve ser entre 0 e 8");
        }
        if (tabuleiro[posicao] != ' ') {
            return false;
        }
        tabuleiro[posicao] = jogador;
        return true;
    }

    public char verificarVencedor() {
        int[][] combos = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        for (int[] combo : combos) {
            char a = tabuleiro[combo[0]];
            if (a != ' ' && a == tabuleiro[combo[1]] && a == tabuleiro[combo[2]]) {
                return a;
            }
        }
        return ' ';
    }

    public boolean isTabuleiroCheio() {
        for (char c : tabuleiro) {
            if (c == ' ') return false;
        }
        return true;
    }

    public boolean isJogoEncerrado() {
        return verificarVencedor() != ' ' || isTabuleiroCheio();
    }

    public char[] getTabuleiro() {
        return tabuleiro.clone();
    }
}
