import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ex2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação dos conjuntos
        HashSet<Integer> conjunto1 = new HashSet<>();
        HashSet<Integer> conjunto2 = new HashSet<>();
        HashSet<Integer> interseccao = new HashSet<>();

        // Entrada de dados do Primeiro Conjunto
        System.out.println("Conjunto 01");
        preencherConjunto(scanner, conjunto1);

        // Entrada de dados do Segundo Conjunto
        System.out.println("Conjunto 02");
        preencherConjunto(scanner, conjunto2);

        // Lógica de Interseção Manual (Sem retainAll)
        for (Integer numero : conjunto1) {
            
            if (conjunto2.contains(numero)) {
                interseccao.add(numero);
            }
        }

        System.out.println("--- Resultados ---");
        System.out.println("Conjunto 1: " + conjunto1);
        System.out.println("Conjunto 2: " + conjunto2);
        System.out.println("Intersecção: " + interseccao);
    }

    public static void preencherConjunto(Scanner scanner, Set<Integer> conjunto) {
        System.out.println("Digite números inteiros (digite um negativo para parar):");

        while(true) {
            System.out.print("> ");
            try {
                int numero = Integer.parseInt(scanner.nextLine());

                if (numero < 0) {
                    break; // Sai do loop se for negativo
                }

                conjunto.add(numero);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números inteiros válidos.");
            }
        }
    }
}