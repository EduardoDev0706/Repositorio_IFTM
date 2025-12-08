import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Ex6 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, Integer> mapaFrequencia = new HashMap<>();

        System.out.println("--- Contador de Frequência de Números ---");
        System.out.println("Digite números inteiros (digite um número negativo para encerrar):");

        while (true) {
            System.out.print("> ");

            try {
                int numero = Integer.parseInt(scanner.nextLine());

                // Condição de parada
                if (numero < 0) {
                    break;
                }

                // Lógica Principal
                if (mapaFrequencia.containsKey(numero)) {
                    // Caso o número já exista no mapa
                    // Usa a contagem atual
                    int contagemAtual = mapaFrequencia.get(numero);
                    // Atualiza o mapa com a contagem + 1
                    mapaFrequencia.put(numero, contagemAtual + 1);
                } else {
                    // Caso o número não exista no mapa
                    // Criamos o registro inicial com contagem 1
                    mapaFrequencia.put(numero, 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Apenas números inteiros.");
            }
        }

        scanner.close();

        System.out.println("\n--- Resultado da Contagem ---");
        System.out.println("Número | Ocorrências");
        System.out.println("---------------------");

        // Iterando sobre as chaves do mapa para exibir os valores
        for (Integer chave : mapaFrequencia.keySet()) {
            Integer valor = mapaFrequencia.get(chave);
            System.out.println("   " + chave + "   |    " + valor);
        }
    }
}