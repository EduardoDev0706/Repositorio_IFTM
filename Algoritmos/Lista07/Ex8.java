import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Ex8 {

    public static class Produto {
        private String id;
        private String nome;

        public Produto(String id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public String getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        @Override
        public String toString() {
            return "[ID: " + id + "] " + nome;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // O HashMap mapeia uma String (ID) para um objeto Produto
        Map<String, Produto> estoque = new HashMap<>();

        int opcao = -1;

        System.out.println("Gestão de Produtos");

        while (opcao != 0) {
            System.out.println("\n--- Menu ---");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Buscar Produto pelo ID");
            System.out.println("3 - Remover Produto pelo ID");
            System.out.println("4 - Listar todos os produtos");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch (opcao) {
                case 1: // Adicionar
                    System.out.print("Digite o ID do produto: ");
                    String idAdd = scanner.nextLine();

                    // Verifica se a chave já existe para não sobrescrever acidentalmente
                    if (estoque.containsKey(idAdd)) {
                        System.out.println(">> Erro: Já existe um produto com o ID '" + idAdd + "'.");
                    } else {
                        System.out.print("Digite o Nome do produto: ");
                        String nomeAdd = scanner.nextLine();

                        Produto novoProduto = new Produto(idAdd, nomeAdd);

                        // Armazena no mapa: Chave = id, Valor = objeto Produto
                        estoque.put(idAdd, novoProduto);
                        System.out.println(">> Produto cadastrado com sucesso!");
                    }
                    break;

                case 2: // Buscar
                    System.out.print("Digite o ID para buscar: ");
                    String idBusca = scanner.nextLine();

                    // Recupera o objeto direto pela chave
                    Produto produtoEncontrado = estoque.get(idBusca);

                    if (produtoEncontrado != null) {
                        System.out.println(">> Produto Encontrado: " + produtoEncontrado);
                    } else {
                        System.out.println(">> Produto não encontrado.");
                    }
                    break;

                case 3: // Remover
                    System.out.print("Digite o ID para remover: ");
                    String idRem = scanner.nextLine();

                    // Remove retorna o objeto removido ou null se não existia
                    if (estoque.remove(idRem) != null) {
                        System.out.println(">> Produto removido com sucesso.");
                    } else {
                        System.out.println(">> Erro: Produto não encontrado para remoção.");
                    }
                    break;

                case 4: // Listar
                    System.out.println("\n--- Lista Completa de Produtos ---");
                    if (estoque.isEmpty()) {
                        System.out.println("Estoque vazio.");
                    } else {
                        for (Produto p : estoque.values()) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}
