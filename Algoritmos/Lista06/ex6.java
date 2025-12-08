import java.util.ArrayList;
import java.util.Scanner;

// 1. Definição da classe Contato (O Molde)
class Contato {
    // Atributos encapsulados (private)
    private String nome;
    private String telefone;
    private String celular;
    private String email;

    // Método Construtor: Para criar o objeto já com os dados
    public Contato(String nome, String telefone, String celular, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.celular = celular;
        this.email = email;
    }

    // Getters: Métodos públicos para acessar os dados privados (se necessário)
    public String getNome() { return nome; }
    // (Outros getters poderiam ser criados aqui: getTelefone, getEmail...)

    // Sobrescrita do método toString para facilitar a impressão dos dados
    @Override
    public String toString() {
        return "Nome: " + nome + 
               " | Tel: " + telefone + 
               " | Cel: " + celular + 
               " | Email: " + email;
    }
}

// 2. Classe Principal (Onde o programa roda)
public class ex6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Criação da lista tipada para a classe Contato
        ArrayList<Contato> listaContatos = new ArrayList<Contato>();
        
        int opcao = 0;

        do {
            System.out.println("\n--- MENU AGENDA ---");
            System.out.println("1. Inserir Contato");
            System.out.println("2. Listar Contatos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpeza do buffer do teclado (bug do nextInt)

            switch (opcao) {
                case 1:
                    System.out.println("\n-- Novo Contato --");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    
                    System.out.print("Telefone: ");
                    String tel = scanner.nextLine();
                    
                    System.out.print("Celular: ");
                    String cel = scanner.nextLine();
                    
                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    // Instanciando o objeto Contato com os dados digitados
                    Contato novoContato = new Contato(nome, tel, cel, email);
                    
                    // Adicionando o objeto à lista
                    listaContatos.add(novoContato);
                    System.out.println("Contato cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println("\n-- Lista de Contatos --");
                    if (listaContatos.isEmpty()) {
                        System.out.println("A agenda está vazia.");
                    } else {
                        // Loop for-each para percorrer a lista de objetos
                        for (Contato c : listaContatos) {
                            // O Java chama automaticamente o método toString() do objeto
                            System.out.println(c); 
                        }
                    }
                    break;

                case 3:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 3);

        scanner.close();
    }
}