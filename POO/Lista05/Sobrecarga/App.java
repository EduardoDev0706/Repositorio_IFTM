import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Cria uma instância da classe App
        App app = new App();
        app.menu();

        scanner.close();
    }

    // Menu
    public void menu() {
        int opcaoFigura = 0;

        do {
            try {
                System.out.println("FIGURA");
                System.out.println("1 – Círculo");
                System.out.println("2 – Triângulo");
                System.out.println("3 – Quadrado");
                System.out.println("4 – SAIR");
                System.out.print("DIGITE A OPÇÃO: ");

                opcaoFigura = scanner.nextInt();

                if (opcaoFigura >= 1 && opcaoFigura <= 3) {
                    Object[] resultadoLeitura = le(opcaoFigura);

                    if (resultadoLeitura != null) {
                        FigurasGeometricas figura = (FigurasGeometricas) resultadoLeitura[0];
                        int tipoFigura = (int) resultadoLeitura[1];

                        submenu(figura, tipoFigura);
                    }
                } else if (opcaoFigura != 4) {
                    System.out.println("Opção Inválida. Digite uma opção entre 1 e 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada Inválida. Digite um número inteiro.");
                scanner.next();
                opcaoFigura = 0;
            }
        } while (opcaoFigura != 4);
        System.out.println("Programa Encerrado.");
    }

    public Object[] le(int opcao) {
        try {
            switch (opcao) {
                case 1: // Círculo
                    System.out.println("\n--- LENDO DADOS DO CÍRCULO ---");
                    System.out.print("Digite o X do centro (xc): ");
                    double xc = scanner.nextDouble();
                    System.out.print("Digite o Y do centro (yc): ");
                    double yc = scanner.nextDouble();
                    System.out.print("Digite o Raio: ");
                    double raio = scanner.nextDouble();
                    return new Object[] { new FigurasGeometricas(xc, yc, raio), 1 };

                case 2: // Triângulo
                    System.out.println("\n--- LENDO DADOS DO TRIÂNGULO ---");
                    System.out.print("Digite a Base: ");
                    double base = scanner.nextDouble();
                    System.out.print("Digite a Altura: ");
                    double altura = scanner.nextDouble();
                    return new Object[] { new FigurasGeometricas(base, altura), 2 };

                case 3: // Quadrado
                    System.out.println("\n--- LENDO DADOS DO QUADRADO ---");
                    System.out.print("Digite o Lado: ");
                    double lado = scanner.nextDouble();
                    return new Object[] { new FigurasGeometricas(lado), 3 };
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro de entrada: Digite um valor numérico válido.");
            scanner.next();
        }
        return null;
    }

    public void submenu(FigurasGeometricas figura, int tipo) {
        int opcaoAcao = 0;

        do {
            try {
                System.out.println("\n--- SUBMENU AÇÕES ---");
                System.out.println("AÇÕES");
                System.out.println("1 – Exibir Dados");
                System.out.println("2 – Área");
                System.out.println("3 – Voltar");
                System.out.print("DIGITE A OPÇÃO: ");

                opcaoAcao = scanner.nextInt();

                System.out.println("-------------------");
                switch (opcaoAcao) {
                    case 1: // Exibir Dados (Chama o método 'exibe' que já contém a área)
                        switch (tipo) {
                            case 1:
                                System.out.println(figura.exibeCirculo());
                                break;
                            case 2:
                                System.out.println(figura.exibeTriangulo());
                                break;
                            case 3:
                                System.out.println(figura.exibeQuadrado());
                                break;
                        }
                        break;
                    case 2: // Área
                        // Exibe apenas a área
                        double area = 0.0;
                        switch (tipo) {
                            case 1:
                                area = figura.areaCirculo();
                                break;
                            case 2:
                                area = figura.areaTriangulo();
                                break;
                            case 3:
                                area = figura.areaQuadrado();
                                break;
                        }
                        System.out.printf("A Área da figura é: %.2f\n", area);
                        break;
                    case 3: // Voltar
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
                System.out.println("-------------------------");
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
                scanner.next(); // Limpa o buffer
                opcaoAcao = 0;
            }
        } while (opcaoAcao != 3);
    }
}
