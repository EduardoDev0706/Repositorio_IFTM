import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            // Exibe o menu
            System.out.println("                              M  E  N  U");
            System.out.println("1- ADIÇÃO");
            System.out.println("2- SUBTRAÇÃO");
            System.out.println("3- MULTIPLICAÇÃO");
            System.out.println("4- DIVISÃO");
            System.out.println("5- SAIR");
            System.out.print(" ESCOLHA A OPÇÃO: ");

            opcao = scanner.nextInt();

            // Pede os números para a operação, exceto para a opção "Sair"
            if (opcao >= 1 && opcao <= 4) {
                System.out.print("Digite o primeiro número inteiro: ");
                int num1 = scanner.nextInt();
                System.out.print("Digite o segundo número inteiro: ");
                int num2 = scanner.nextInt();

                // Usa switch-case para realizar a operação
                switch (opcao) {
                    case 1:
                        System.out.println("Resultado: " + (num1 + num2));
                        break;
                    case 2:
                        System.out.println("Resultado: " + (num1 - num2));
                        break;
                    case 3:
                        System.out.println("Resultado: " + (num1 * num2));
                        break;
                    case 4:
                        if (num2 != 0) {
                            System.out.println("Resultado: " + ((double) num1 / num2));
                        } else {
                            System.out.println("Divisão por zero não é permitida.");
                        }
                        break;
                }
            } else if (opcao == 5) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 5.");
            }

            System.out.println(); // Adiciona uma linha em branco

        } while (opcao != 5);

        scanner.close();
    }

}