import java.util.ArrayList;
import java.util.Scanner;

public class ex3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação da ArrayList para armazenar números inteiros
        ArrayList<Integer> listaNumeros = new ArrayList<>();

        // a) Solicitar a quantidade de números (n)
        System.out.print("Digite a quanitdade de números a serem inseridos: ");
        int n = scanner.nextInt();

        // b) Solicitar ao usuário que insira os n números
        System.out.println("Digite os " + n + " números:");
        for (int i = 0; i < n; i++) {
            System.out.print("Número " + (i + 1) + ": ");
            int numero = scanner.nextInt();
            listaNumeros.add(numero);
        }

        // Separador visual
        System.out.println("--- Resultados ---");

        // c) Exibir os números da lista na ordem de inserção
        System.out.println("Lista original: " + listaNumeros);

        if (!listaNumeros.isEmpty()) {
            // d) Calcular e exibir a soma
            int soma = 0;
            for (Integer num : listaNumeros) {
                soma += num;
            }
            System.out.println("Soma dos números: " + soma);

            // e) Encontrar e exibir o maior número
            int maior = listaNumeros.get(0);
            for (Integer num : listaNumeros) {
                if (num > maior) {
                    maior = num;
                }
            }
            System.out.println("Maior número: " + maior);

            // f) Remover ímpares e exibir apenas pares
            listaNumeros.removeIf(numero -> numero % 2 != 0);

            System.out.println("Lista final (apenas pares): " + listaNumeros);
        } else {
            System.out.println("A lista está vazia.");
        }

        scanner.close();
    }
}
