import java.util.Scanner;

public class JogoDaVelha {

    static Scanner scanner = new Scanner(System.in);
    static char[][] tabuleiro = new char[3][3];

    public static void main(String[] args) {
        // Inicializa o tabuleiro
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                tabuleiro[l][c] = ' ';
            }
        }

        boolean jogoEmAndamento = true;
        char jogadorAtual = 'X';
        int jogadas = 0;
        int jogada;

        while (jogoEmAndamento) {
            // Exibe o tabuleiro e lê a jogada do jogador
            jogada = interfaceJogo(jogadorAtual);

            // Valida a jogada
            if (validacao(jogada)) {
                // Converte a jogada para coordenadas de matriz e a insere
                int linha = (jogada - 1) / 3;
                int coluna = (jogada - 1) % 3;
                tabuleiro[linha][coluna] = jogadorAtual;
                jogadas++;

                // Verifica a vitória
                if (vitoria()) {
                    exibeFim(jogadorAtual);
                    jogoEmAndamento = false;
                }
                // Verifica o empate (velha)
                else if (empate()) {
                    exibeFim('E');
                    jogoEmAndamento = false;
                }
                // Alterna o jogador
                else {
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                }
            } else {
                // Exibe mensagem de jogada inválida
                restricao();
            }
        }
    }

    public static int interfaceJogo(char jogadorAtual) {
        // Exibe o tabuleiro
        System.out.println("\n--- Jogo da Velha ---");
        System.out.println("  1 | 2 | 3  ");
        System.out.println("  --|---|--");
        System.out.println("  4 | 5 | 6  ");
        System.out.println("  --|---|--");
        System.out.println("  7 | 8 | 9  ");
        System.out.println("\nTabuleiro atual:");
        for (int i = 0; i < 3; i++) {
            System.out.println(" " + tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);
            if (i < 2) {
                System.out.println("---|---|---");
            }
        }

        // Lê a jogada
        System.out.print("\nJogador " + jogadorAtual + ", digite sua jogada (1-9): ");
        int jogada = scanner.nextInt();
        return jogada;
    }
    
    public static boolean validacao(int jogada) {
        if (jogada < 1 || jogada > 9) {
            return false;
        }

        int linha = (jogada - 1) / 3;
        int coluna = (jogada - 1) % 3;

        return tabuleiro[linha][coluna] == ' ';
    }

    // 3) void restricao( )
    public static void restricao() {
        System.out.println("Jogada inválida! A posição não existe ou já está ocupada.");
    }

    // 4) boolean empate( )
    public static boolean empate() {
        for (int l = 0; l < 3; l++) {
            for (int c = 0; c < 3; c++) {
                if (tabuleiro[l][c] == ' ') {
                    return false; // Ainda existem espaços vazios, não é empate
                }
            }
        }
        return true; // Tabuleiro completamente preenchido
    }

    public static boolean vitoria() {
        // Verifica linhas e colunas
        for (int i = 0; i < 3; i++) {
            // Linhas
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != ' ') {
                return true;
            }
            // Colunas
            if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[0][i] != ' ') {
                return true;
            }
        }
        // Verifica diagonais
        // Diagonal principal
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != ' ') {
            return true;
        }
        // Diagonal secundária
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2] != ' ') {
            return true;
        }

        return false;
    }

    public static void exibeFim(char jogadorVencedor) {
        if (jogadorVencedor == 'E') { // 'E' para empate
            System.out.println("\nO jogo terminou em EMPATE!");
        } else {
            System.out.println("\nParabéns! O jogador " + jogadorVencedor + " venceu!");
        }
    }
}