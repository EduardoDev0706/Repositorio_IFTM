import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Ex3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação da Lista inicial
        List<Integer> listaComDuplicatas = new ArrayList<>();

        System.out.println("Digite números (negativo para parar):");

        // Loop de entrada
        while (true) {
            System.out.print("> ");
            try {
                String input = scanner.nextLine();
                int numero = Integer.parseInt(input);

                if (numero < 0) {
                    break;
                }

                listaComDuplicatas.add(numero);
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }

        System.out.println("Lista Original");
        System.out.println(listaComDuplicatas);

        // Convertendo List -> HashSet
        Set<Integer> conjuntoTemporario = new HashSet<>(listaComDuplicatas);

        // Convertendo HashSet -> List
        List<Integer> listaLimpa = new ArrayList<>(conjuntoTemporario);

        System.out.println("Lista final");
        System.out.println(listaLimpa);

        scanner.close();
    }

}