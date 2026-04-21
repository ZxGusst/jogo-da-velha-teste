package br.com.grupo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JogoDaVelha jogo = new JogoDaVelha();
        char jogadorAtual = 'X';

        System.out.println("=== JOGO DA VELHA ===");

        while (!jogo.isJogoEncerrado()) {
            imprimirTabuleiro(jogo.getTabuleiro());
            System.out.printf("Vez do jogador %c. Escolha a posição (0-8): ", jogadorAtual);

            int pos = scanner.nextInt();

            try {
                boolean jogadaOk = jogo.fazerJogada(pos, jogadorAtual);
                if (!jogadaOk) {
                    System.out.println("Posição ocupada! Tente outra.");
                    continue;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Erro: " + e.getMessage());
                continue;
            }

            char vencedor = jogo.verificarVencedor();
            if (vencedor != ' ') {
                imprimirTabuleiro(jogo.getTabuleiro());
                System.out.printf("Jogador %c venceu!%n", vencedor);
                break;
            }

            if (jogo.isTabuleiroCheio()) {
                imprimirTabuleiro(jogo.getTabuleiro());
                System.out.println("Empate!");
                break;
            }

            jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
        }

        scanner.close();
    }

    private static void imprimirTabuleiro(char[] t) {
        System.out.printf("%n %c | %c | %c %n", t[0], t[1], t[2]);
        System.out.println("---+---+---");
        System.out.printf(" %c | %c | %c %n", t[3], t[4], t[5]);
        System.out.println("---+---+---");
        System.out.printf(" %c | %c | %c %n%n", t[6], t[7], t[8]);
    }
}
