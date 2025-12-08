import java.util.Scanner;
import java.util.ArrayList;

public class ex1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> listaDeNumeros = new ArrayList<>();

        System.out.println("Digite números inteiros positivos (digite um negativo para parar):");

        while (true) {
            int numero = scanner.nextInt();

            // Condição de parada: se for negativo, interrompe o loop
            if (numero < 0) {
                break;
            }

            listaDeNumeros.add(numero);
        }

        // Processamento dos dados armazenados na lista
        int soma = 0;
        for (Integer n : listaDeNumeros) {
            soma += n;
        }

        System.out.println("Quantidade de números fornecidos: " + listaDeNumeros.size());
        System.out.println("Soma dos números: " + soma);
        
        scanner.close();
    }
}