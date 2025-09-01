import java.util.Scanner;

public class JogoDaVelha {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Criação do tabuleiro na memória
        char[][] tabuleiro = new char[3][3];

        // Linhas
        for (int l = 0; l < 3; l++) {
            // Colunas
            for (int c = 0; c < 3; c++) {
                tabuleiro[l][c] = ' ';
            }
        }

        boolean jogoEmAndamento = true;
        char jogadorAtual = 'X';
        int jogadas = 0;

        // Desenha o tabuleiro para o usuario
        desenharTabuleiro(tabuleiro);

        while (jogoEmAndamento) {
            processarJogada(tabuleiro, jogadorAtual);
            jogadas++;

            // Desenha o tabuleiro atualizado
            desenharTabuleiro(tabuleiro);

            // Adiciona a lógica para verificar a vitória
            char vencedor = verificarVitoria(tabuleiro);
            if (vencedor != ' ')
            {
                System.out.println("Parabéns! O jogador " + vencedor + " venceu!");
                jogoEmAndamento = false;
            }
            else if (jogadas == 9)
            {
                System.out.println("O jogo terminou em empate!");
                jogoEmAndamento = false;
            }

            // Alterna o jogador
            if (jogadorAtual == 'X') {
                jogadorAtual = 'O';
            } else {
                jogadorAtual = 'X';
            }
        }

    }

    public static void processarJogada(char[][] tabuleiro, char jogadorAtual) {
        int posicao;
        boolean jogadaValida = false;

        while (!jogadaValida) {
            System.out.println("Jogador " + jogadorAtual + ", digite sua jogada (1-9):");
            posicao = scanner.nextInt();

            int linha = (posicao - 1) / 3;
            int coluna = (posicao - 1) % 3;

            // Valida a jogada
            if (posicao >= 1 && posicao <= 9 && tabuleiro[linha][coluna] == ' ') {
                tabuleiro[linha][coluna] = jogadorAtual;
                jogadaValida = true;
            } else {
                System.out.println("Posição inválida. Tente novamente.");
            }
        }
    }

    public static void desenharTabuleiro(char[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            // Imprime o conteúdo de uma linha inteira
            System.out.println(" " + tabuleiro[i][0] + " | " + tabuleiro[i][1] + " | " + tabuleiro[i][2]);

            // Verifica se não é a última linha para imprimir o separador horizontal
            if (i < 2) {
                System.out.println("---|---|---");
            }
        }
    }

    public static char verificarVitoria(char[][] tabuleiro) {
        // Verifica as linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][0] != ' ') {
                return tabuleiro[i][0];
            }
        }

        // Verifica as colunas
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != ' ') {
                return tabuleiro[0][j];
            }
        }

        // Verifica a diagonal principal
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[0][0] != ' ') {
            return tabuleiro[0][0];
        }
        // Verifica a diagonal secundária
        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0] && tabuleiro[0][2] != ' ') {
            return tabuleiro[0][2];
        }

        // Caso nenhuma das condições acima forem cumpridas, retorna um espaço para indicar que não há vencedores ainda
        return ' ';
    }

}