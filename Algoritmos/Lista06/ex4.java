import java.util.ArrayList;
import java.util.Scanner;

public class ex4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> listaConvidados = new ArrayList<>();

        System.out.println("--- Gerenciamento de Convidados ---");
        System.out.println("Digite os nomes dos convidados.");
        System.out.println("Digite 'fim' para encerrar a inserção.");
        System.out.println("-----------------------------------");

        // a) Loop infinito para adicionar nomes até digitar "fim"
        while (true) {
            System.out.print("Nome do convidado: ");
            String nome = scanner.nextLine();

            // Verifica condição de parada (ignora maiúsculas/minúsculas)
            if (nome.equalsIgnoreCase("fim")) {
                break;
            }

            // b) Verificar duplicidade antes de adicionar
            // O método .contains() retorna true se o item já existe na lista
            if (listaConvidados.contains(nome)) {
                System.out.println(">> Erro: Convidado existe (já foi adicionado).");
            } else {
                listaConvidados.add(nome);
                System.out.println("Convidado adicionado.");
            }
        }

        // c) Exibir a lista final
        System.out.println("\n--- Lista Final ---");
        if (listaConvidados.isEmpty()) {
            System.out.println("Nenhum convidado na lista.");
        } else {
            for (String convidado : listaConvidados) {
                System.out.println("- " + convidado);
            }
        }

        // d) Pesquisar por um nome
        System.out.println("\n--- Pesquisa de Convidado ---");
        System.out.print("Digite um nome para verificar se foi convidado: ");
        String nomeBusca = scanner.nextLine();

        if (listaConvidados.contains(nomeBusca)) {
            System.out.println("Sim! " + nomeBusca + " está na lista.");
        } else {
            System.out.println("Não! " + nomeBusca + " não foi encontrado.");
        }

        scanner.close();
    }
}