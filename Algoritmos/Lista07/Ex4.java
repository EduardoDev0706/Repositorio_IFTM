import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ex4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Criação do HashSet para Strings
        Set<String> palavras = new HashSet<>();

        System.out.println("Banco de Palavras");
        System.out.println("Digite palavras para adicionar.");
        System.out.println("Digite 'fim' para encerrar a inserção.");

        // Loop da inserção
        while (true) {
            System.out.print("Palavra: ");
            String entrada = scanner.nextLine();

            // Verifica se o usuário digitou "fim"
            if (entrada.equalsIgnoreCase("fim")) {
                break;
            }

            palavras.add(entrada);
        }

        System.out.println("Total de palavras únicas armazenadas: " + palavras.size());
        
        // Verificação da Existência
        System.out.print("Digite uma palavra para buscar no conjunto: ");
        String busca = scanner.nextLine();

        // A busca é Case-Sensitive
        boolean existe = palavras.contains(busca);

        if (existe) {
            System.out.println(">> A palavra '" + busca + "' ESTÁ no conjunto!");
        } else {
            System.out.println(">> A palavra '" + busca + "' NÃO ESTÁ no conjunto!");
        }

        scanner.close();
    }
}