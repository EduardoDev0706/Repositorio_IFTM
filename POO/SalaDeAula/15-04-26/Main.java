import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Inicialização dos objetos
        ContaPoupanca poupanca = new ContaPoupanca();
        ContaCorrente corrente = new ContaCorrente(5.00); // Taxa de R$ 5.00 por saque
        GeradorExtratos gerador = new GeradorExtratos();

        int opcaoPrincipal = -1;

        while (opcaoPrincipal != 0) {
            System.out.println("\n--- MENU PRINCIPAL ---");
            System.out.println("1. Acessar Conta Poupança");
            System.out.println("2. Acessar Conta Corrente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcaoPrincipal = scanner.nextInt();

            switch (opcaoPrincipal) {
                case 1:
                    exibirSubMenu(scanner, poupanca, gerador, "Conta Poupança");
                    break;
                case 2:
                    exibirSubMenu(scanner, corrente, gerador, "Conta Corrente");
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        
        scanner.close();
    }

    // Método privado para gerenciar o sub-menu, aplicando polimorfismo (recebe interface Conta)
    private static void exibirSubMenu(Scanner scanner, Conta conta, GeradorExtratos gerador, String nomeConta) {
        int opcaoSubMenu = -1;

        while (opcaoSubMenu != 0) {
            System.out.println("\n--- SUB-MENU: " + nomeConta + " ---");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Emitir Extrato");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opcaoSubMenu = scanner.nextInt();

            switch (opcaoSubMenu) {
                case 1:
                    System.out.print("Digite o valor para depósito: R$ ");
                    double valorDep = scanner.nextDouble();
                    conta.depositar(valorDep);
                    break;
                case 2:
                    System.out.print("Digite o valor para saque: R$ ");
                    double valorSaque = scanner.nextDouble();
                    conta.sacar(valorSaque);
                    break;
                case 3:
                    // Chama a classe GeradorExtratos via polimorfismo
                    System.out.println(gerador.exibeExtrato(conta));
                    break;
                case 0:
                    System.out.println("Voltando ao menu anterior...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}