import java.util.Scanner;

public class App {
    public static int[] le() {
        Scanner scanner = new Scanner(System.in);
        int[] lados = new int[3];

        System.out.println("--- Leitura dos Lados do Triângulo ---");

        System.out.print("Digite o comprimento do Lado A: ");
        lados[0] = scanner.nextInt();
        System.out.print("Digite o comprimento do Lado B: ");
        lados[1] = scanner.nextInt();
        System.out.print("Digite o comprimento do Lado C: ");
        lados[2] = scanner.nextInt();

        return lados;
    }

    public static void exibe(Triangulo triangulo) {
        String tipo = triangulo.tipoTriangulo();
        System.out.println("\n-------------------------------------");
        System.out.println("Tipo de Triângulo Formado: " + tipo.toUpperCase());
        System.out.println("-------------------------------------");
    }

    public static void main(String[] args) {
        int[] lados = le();
        int a = lados[0];
        int b = lados[1];
        int c = lados[2];

        boolean podeFormar = ValidaTriangulo.verifica(a, b, c);

        if (podeFormar) {
            System.out.println("\nOs lados (" + a + ", " + b + ", " + c + ") PODEM formar um triângulo.");

            Triangulo meuTriangulo = new Triangulo(a, b, c);

            exibe(meuTriangulo);
        } else {
            System.out.println("\nOs lados (" + a + ", " + b + ", " + c + ") NÃO PODEM formar um triângulo.");
            System.out.println("O programa será finalizado.");
            return;
        }
    }
}
