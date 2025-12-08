import java.util.ArrayList;
import java.util.Scanner;

// 1. Classe Cliente (O Modelo)
class Cliente {
    // Atributos encapsulados
    private String nome;
    private String cpf;
    private int idade;

    // Construtor
    public Cliente(String nome, String cpf, int idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    // Getter para o CPF (Necessário para a busca/remoção)
    public String getCpf() {
        return cpf;
    }

    // Método toString para facilitar a exibição
    @Override
    public String toString() {
        return "Nome: " + nome + " | CPF: " + cpf + " | Idade: " + idade;
    }
}

// 2. Classe Principal (O Sistema)
public class ex7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        int opcao = 0;

        do {
            System.out.println("\n=== GESTÃO DE CLIENTES ===");
            System.out.println("1. Inserir novo Cliente");
            System.out.println("2. Listar todos os clientes");
            System.out.println("3. Buscar Cliente pelo CPF");
            System.out.println("4. Remover cliente pelo CPF");
            System.out.println("5. Apagar Lista (Remover todos)");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer do teclado

            switch (opcao) {
                case 1: // Inserir
                    System.out.println("\n-- Novo Cadastro --");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Idade: ");
                    int idade = scanner.nextInt();
                    
                    // Cria o objeto e adiciona na lista
                    listaClientes.add(new Cliente(nome, cpf, idade));
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 2: // Listar
                    System.out.println("\n-- Lista de Clientes --");
                    if (listaClientes.isEmpty()) {
                        System.out.println("A lista está vazia.");
                    } else {
                        for (Cliente c : listaClientes) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 3: // Buscar por CPF
                    System.out.print("\nDigite o CPF para busca: ");
                    String cpfBusca = scanner.nextLine();
                    boolean encontrado = false;

                    for (Cliente c : listaClientes) {
                        // Compara o CPF digitado com o CPF do objeto atual
                        if (c.getCpf().equals(cpfBusca)) {
                            System.out.println("Cliente Encontrado: " + c);
                            encontrado = true;
                            break; // Para o loop pois já achou
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Cliente com CPF " + cpfBusca + " não encontrado.");
                    }
                    break;

                case 4: // Remover por CPF
                    System.out.print("\nDigite o CPF para remover: ");
                    String cpfRemover = scanner.nextLine();
                    Cliente clienteParaRemover = null;

                    // Passo 1: Encontrar o objeto na lista
                    for (Cliente c : listaClientes) {
                        if (c.getCpf().equals(cpfRemover)) {
                            clienteParaRemover = c; // Guarda a referência do objeto
                            break;
                        }
                    }

                    // Passo 2: Remover o objeto se ele foi encontrado
                    if (clienteParaRemover != null) {
                        listaClientes.remove(clienteParaRemover);
                        System.out.println("Cliente removido com sucesso!");
                        System.out.println("Lista atualizada possui " + listaClientes.size() + " clientes.");
                    } else {
                        System.out.println("Erro: CPF não encontrado na lista.");
                    }
                    break;

                case 5: // Apagar Tudo
                    if (listaClientes.isEmpty()) {
                        System.out.println("\nA lista já está vazia.");
                    } else {
                        listaClientes.clear(); // Método que limpa o ArrayList
                        System.out.println("\nTodos os clientes foram removidos.");
                    }
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 6);

        scanner.close();
    }
}