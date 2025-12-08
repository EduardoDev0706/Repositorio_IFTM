import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Ex5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> listaConvidados = new HashSet<>();

        int opcao = -1;

        System.out.println("Sistema de Gestão de Eventos");

        while (opcao != 0) {
            exibirMenu();

            try {
                System.out.print("Escolha uma opção: ");
                // Ler como String e converter para evitar que pule de linha automaticamente
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1: // Adicionar
                    System.out.print("Digite o nome do convidado: ");
                    String nomeAdd = scanner.nextLine().trim();

                    if (!nomeAdd.isEmpty()) {
                        if (listaConvidados.add(nomeAdd)) {
                            System.out.println(">> Sucesso> '" + nomeAdd + "' adicionado.");
                        } else {
                            System.out.println(">> Aviso: '" + "' JÁ ESTÁ na lista.");
                        }
                    } else {
                        System.out.println(">> Erro: Nome não pode ser vazio.");
                    }
                    break;

                case 2: // Remover
                    System.out.print("Nome do convidado para remover: ");
                    String nomeRem = scanner.nextLine().trim();

                    if (listaConvidados.remove(nomeRem)) {
                        System.out.println(">> Sucesso: '" + nomeRem + "'foi removido.");
                    } else {
                        System.out.println(">> Erro: '" + nomeRem + "'não foi encontrado.");
                    }
                    break;

                case 3: // Verificar existência
                    System.out.print("Nome para pesquisar: ");
                    String nomeBusca = scanner.nextLine().trim();

                    if (listaConvidados.contains(nomeBusca)) {
                        System.out.println(">> Sim, '" + nomeBusca + "' está confirmado.");
                    } else {
                        System.out.println(">> Não, '" + nomeBusca + "' não está na lista.");
                    }
                    break;

                case 4: // Exibir todos
                    System.out.println("\n--- Lista de Convidados ---");
                    if (listaConvidados.isEmpty()) {
                        System.out.println("(Lista vazia)");
                    } else {
                        for (String convidado : listaConvidados) {
                            System.out.println("- " + convidado);
                        }
                    }
                    break;

                case 5: // Quantidade
                    System.out.println(">> Total de convidados: " + listaConvidados.size());
                    break;

                case 6: // Apagar tudo
                    if (listaConvidados.isEmpty()) {
                        System.out.println(">> A lista já está vazia.");
                    } else {
                        listaConvidados.clear();
                        System.out.println(">> Todos os convidados foram removidos.");
                    }
                    break;
                    
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                    
                default:
                    System.out.println("Opção inválida!");
            }
            System.out.println("----------------------------");
        }

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\nMENU PRINCIPAL:");
        System.out.println("1 - Adicionar convidado");
        System.out.println("2 - Remover convidado");
        System.out.println("3 - Verificar se convidado está na lista");
        System.out.println("4 - Exibir todos os convidados");
        System.out.println("5 - Ver quantidade de cadastrados");
        System.out.println("6 - Apagar todos os convidados");
        System.out.println("0 - Sair");
    }
}