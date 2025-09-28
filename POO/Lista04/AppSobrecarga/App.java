package AppSobrecarga;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 
        int opcao = 0;

        do {
            exibirMenu();

            try {
                // Lê a opção
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        criarCirculo(scanner);
                        break;
                    case 2:
                        criarTriangulo(scanner); 
                        break;
                    case 3:
                        criarQuadrado(scanner); // 
                        break;
                    case 4:
                        System.out.println("\nEncerrando o programa. Até logo!");
                        break;
                    default:
                        System.out.println("\nOpção inválida. Por favor, escolha uma opção entre 1 e 4.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("\nErro: Entrada inválida. Por favor, digite um número inteiro para a opção.");
                scanner.next(); // Limpa o buffer de entrada
                opcao = 0;
            } catch (Exception e) {
                System.out.println("\nOcorreu um erro: " + e.getMessage());
                opcao = 0;
            }

            if (opcao != 4) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine(); // Consome a linha pendente
                scanner.nextLine(); // Espera pelo ENTER
            }

        } while (opcao != 4);

        scanner.close();
    }

    // Menu
    private static void exibirMenu() {
        System.out.println("\n=============================");
        System.out.println("   FIGURAS GEOMÉTRICAS");
        System.out.println("=============================");
        System.out.println("1 – Círculo");
        System.out.println("2 – Triângulo");
        System.out.println("3 – Quadrado");
        System.out.println("4 – SAIR");
        System.out.print("DIGITE A OPÇÃO: ");
    }

    private static void criarCirculo(Scanner scanner) {
        try {
            System.out.print("Digite o raio do Círculo: ");
            double raio = scanner.nextDouble();
            System.out.print("Digite a coordenada X do centro: ");
            double xcentro = scanner.nextDouble();
            System.out.print("Digite a coordenada Y do centro: ");
            double ycentro = scanner.nextDouble();

            // Instancia o Círculo com o construtor de 3 parâmetros (o foco principal)
            Circulo circulo = new Circulo(raio, xcentro, ycentro);
            circulo.exibe();

        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro de entrada. Certifique-se de digitar números válidos para as dimensões.");
            scanner.nextLine(); // Limpa o buffer
        }
    }

    private static void criarTriangulo(Scanner scanner) {
        try {
            System.out.print("Digite o valor da base do Triângulo: ");
            double base = scanner.nextDouble();
            System.out.print("Digite o valor da altura do Triângulo: ");
            double altura = scanner.nextDouble();

            Triangulo triangulo = new Triangulo(base, altura);
            triangulo.exibe();

        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro de entrada. Certifique-se de digitar números válidos para as dimensões.");
            scanner.nextLine(); // Limpa o buffer
        }
    }

    private static void criarQuadrado(Scanner scanner) {
        try {
            System.out.print("Digite o valor do lado do Quadrado: ");
            double lado = scanner.nextDouble();

            Quadrado quadrado = new Quadrado(lado);
            quadrado.exibe();

        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro de entrada. Certifique-se de digitar números válidos para a dimensão.");
            scanner.nextLine(); // Limpa o buffer
        }
    }
}
