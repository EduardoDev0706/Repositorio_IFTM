import java.util.Scanner;

public class App {
    // Contador de retas
    private static int contadorRetas = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static boolean valida(double x1, double x2, double y1, double y2)
    {
        return x1 >= 0 && y1 >= 0 && x2 >= 0 && y2 >= 0;
    }

    public static double leCoordenada(int num)
    {
        String nomeCoordenada = switch(num) {
            case 1 -> "x1";
            case 2 -> "x2";
            case 3 -> "y1";
            case 4 -> "y2";
            default -> "coordenada";
        };

        System.out.print("Digite o valor da coordenada " + nomeCoordenada + ": ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Entrada inválida. Por favor digite um número válido.");
            scanner.next();
            System.out.print("Digite o valor da coordenada " + nomeCoordenada + ": ");
        }
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        boolean repetir = true;

        System.out.println("--- Calculadora de Retas no 1º Quadrante ---");
        while (repetir) {
            // 1 - Ler as coordenadas
            double x1 = leCoordenada(1);
            double y1 = leCoordenada(2);
            double x2 = leCoordenada(3);
            double y2 = leCoordenada(4);

            // 2 - Se a coordenada for válida
            if (valida(x1, y1, x2, y2)) {
                System.out.println("\nCoordenadas Válidas. Prosseguindo...");

                // Instanciar a classe Retas
                Retas reta = new Retas(x1, x2, y1, y2);

                contadorRetas++;

                System.out.println("\n--- Resultado da Reta " + contadorRetas + " ---");
                System.out.println(reta.exibe());
                System.out.println("----------------------------------------");
            } else {
                System.out.println("\nErro: As coordenadas devem estar no 1º Quadrante (x >= 0 e y >= 0). Tente novamente.");
            }

            // Pergunta se deseja repetir
            System.out.println("\nDeseja calcular outra reta? (S/N)");
            String resposta = scanner.next();
            if (!resposta.equalsIgnoreCase("S")) {
                repetir = false;
            }
        }

         System.out.println("\nPrograma encerrado. Total de retas válidas calculadas: " + contadorRetas);
        scanner.close(); 
    }
}
