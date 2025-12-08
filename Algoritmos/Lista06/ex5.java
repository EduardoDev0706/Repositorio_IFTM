import java.util.ArrayList;
import java.util.Scanner;

public class ex5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> nomes = new ArrayList<>();

        System.out.println("--- Cadastro de Nomes ---");
        
        // 1. Cadastrar os 5 nomes
        for (int i = 0; i < 5; i++) {
            System.out.print("Digite o nome " + (i + 1) + ": ");
            String nomeDigitado = scanner.nextLine();
            nomes.add(nomeDigitado);
        }

        System.out.println("\nQual dos nomes abaixo você deseja excluir da lista?");

        // 2. Exibir nomes enumerados de 1 a 5
        // Usamos um loop for tradicional para usar o índice 'i' na numeração
        for (int i = 0; i < nomes.size(); i++) {
            // (i + 1) transforma o índice 0 em visualização 1
            System.out.println((i + 1) + ". " + nomes.get(i));
        }

        // 3. Solicitar o número para exclusão
        System.out.print("\nDigite o número correspondente ao nome: ");
        int escolha = scanner.nextInt();

        // Validação simples para evitar erros (opcional, mas recomendada)
        if (escolha >= 1 && escolha <= nomes.size()) {
            
            // *** O PULO DO GATO ***
            // O usuário escolheu, por exemplo, 3. Mas na lista o índice é 2.
            // Então removemos o (escolha - 1).
            String nomeRemovido = nomes.remove(escolha - 1);
            
            System.out.println("\nSucesso! O nome '" + nomeRemovido + "' foi removido.");
            
            // 4. Mostrar os nomes restantes
            System.out.println("Nomes restantes na lista: " + nomes);
            
        } else {
            System.out.println("\nNúmero inválido! A exclusão não foi realizada.");
        }

        scanner.close();
    }
}