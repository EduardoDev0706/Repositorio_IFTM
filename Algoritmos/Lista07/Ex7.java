import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Ex7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mapa Original (String -> Integer)
        Map<String, Integer> mapaOriginal = new HashMap<>();

        System.out.println("Preenchimento do Mapa");
        System.out.println("Digite 'Fim' no nome para encerrar.");

        // Loop de Entrada
        while(true) {
            System.out.print("Digite o Nome (Chave): ");
            String chave = scanner.nextLine();

            // Verifica parada
            if (chave.equalsIgnoreCase("Fim")) {
                break;
            }

            System.out.print("Digite o Código (Valor): ");
            try {
                int valor = Integer.parseInt(scanner.nextLine());

                // Adiciona o mapa original
                mapaOriginal.put(chave, valor);
            } catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número inteiro válido.");
            }
        }
        
        // Novo Mapa Invertido (Integer -> String)
        Map<Integer, String> mapaInvertido = new HashMap<>();

        // Iteramos sobre o conjunto de entradas do mapa original
        for (Map.Entry<String, Integer> par : mapaOriginal.entrySet()) {
            String chaveOriginal = par.getKey();
            Integer valorOriginal = par.getValue();

            // No novo mapa: A chave vira valor, e o valor vira chave
            mapaInvertido.put(valorOriginal, chaveOriginal);
        }

        System.out.println("\n=== Mapa Original (String -> Integer) ===");
        System.out.println(mapaOriginal);

        System.out.println("\n=== Mapa Invertido (Integer -> String) ===");
        System.out.println("Código | Nome");
        System.out.println("---------------");

        for (Integer codigo : mapaInvertido.keySet()) {
            System.out.println("  " + codigo + "  | " + mapaInvertido.get(codigo));
        }

        scanner.close();
    }
}