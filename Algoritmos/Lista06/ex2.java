import java.util.ArrayList;
import java.util.Scanner;


public class ex2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação do ArrayList tipado para String
        ArrayList<String> listaNomes = new ArrayList<>();

        System.out.println("Digite os nomes (digite 'FIM' para parar):");

        while (true) {
            String entrada = scanner.nextLine();

            // Verifica se o texto é "FIM"
            if (entrada.equalsIgnoreCase("FIM")) {
                break;
            }

            // Adiciona o nome à lista
            listaNomes.add(entrada);
        }

        System.out.println("\n--- Nomes na ordem inversa ---");

        // Percorre a lista de trás para a frente
        // Começa no índice (tamanho - 1) e vai até 0
        for (int i = listaNomes.size() - 1; i >= 0; i--) {
            System.out.println(listaNomes.get(i));
        }

        scanner.close();
    }
}