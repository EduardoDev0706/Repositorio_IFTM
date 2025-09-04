import java.util.Scanner;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        // Menu Principal

        int opcao;
        Scanner myObj = new Scanner(System.in);

        do {
            limparConsole();

            System.out.println("-----------------------------------------------------------");
            System.out.println("Seja bem-vindo a sua tabela de aplicações");
            System.out.println("Digite um número entre 1 - 17 para executar uma aplicação");
            System.out.println("1 - Comparação entre dois números inteiros");
            System.out.println("2 - Raízes da Equação de Segundo Grau");
            System.out.println("3 - Média Aritmética de dois números inteiros");
            System.out.println("4 - Lados de um triângulo");
            System.out.println("5 - Somatória de números positivo e negativos");
            System.out.println("6 - Fatores de um número natural");
            System.out.println("7 - Frequência de números ímpares e pares e suas somas");
            System.out.println("8 - Número primo(?)");
            System.out.println("9 - Número natural e seu fatorial");
            System.out.println("10 - MMC de dois números naturais");
            System.out.println("11 - MDC de dois números naturais");
            System.out.println("12 - Fibonacci");
            System.out.println("13 - PA");
            System.out.println("14 - Soma dos elementos de uma PG");
            System.out.println("15 - Tabuada de 1 a 10");
            System.out.println("16 - Número natural perfeito(?)");
            System.out.println("Escolha uma das opções e verifique seu resultado.");
            System.out.println();
            System.out.println("Digite 17 para fechar o programa.");

            opcao = myObj.nextInt();
            myObj.nextLine();

            // Lógica para Funcionamento do Programa
            switch (opcao) {
                case 1:
                    exibirComparacao(myObj);
                    break;
                case 2:
                    exibirBhaskara(myObj);
                    break;
                case 3:
                    exibirComparacaoMedia(myObj);
                    break;
                case 4:
                    exibirTriangulo(myObj);
                    break;
                case 5:
                    operacaoComOsValores(myObj);
                    break;
                case 6:
                    exibirFatorial(myObj);
                    break;
                case 17:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção Inválida.");
            }

            if (opcao != 17) {
                System.out.print("\nPressione ENTER para continuar");
                myObj.nextLine();
            }

        } while (opcao != 17);
        myObj.close();
    }

    // Função para limpar terminal
    public static void limparConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            System.out.println("Não foi possível limpar o console.");
        }
    }

    // Aplicação 01
    public static void exibirComparacao(Scanner myObj) {
        // Exibe para o usuário o resultado do programa
        int[] valores = inserirValoresParaComparacao(myObj);
        compararValores(valores[0], valores[1]);
    }

    public static int[] inserirValoresParaComparacao(Scanner myObj) {
        System.out.print("Digite um número inteiro: ");
        int x1 = myObj.nextInt();
        System.out.print("Digite outro número inteiro: ");
        int x2 = myObj.nextInt();
        myObj.nextLine();

        return new int[] { x1, x2 };
    }

    public static void compararValores(int x1, int x2) {
        if (x1 > x2) {
            System.out.println("O valor " + x1 + " é maior que " + x2 + ".");
        } else if (x1 < x2) {
            System.out.println("O valor " + x1 + " é menor que " + x2 + ".");
        } else {
            System.out.println("Os valores de " + x1 + " e " + x2 + " são iguais");
        }
    }

    // Aplicação 02
    public static void exibirBhaskara(Scanner myObj) {
        double[] coeficientes = inserirValoresParaBhaskara(myObj);
        calcularBhaskara(coeficientes[0], coeficientes[1], coeficientes[2]);
    }

    public static double[] inserirValoresParaBhaskara(Scanner myObj) {
        System.out.println("--- Raízes da Equação de Segundo Grau ---");
        System.out.print("Digite o valor do coeficiente a: ");
        double a = myObj.nextDouble();
        myObj.nextLine();

        System.out.print("Digite o valor do coeficiente b: ");
        double b = myObj.nextDouble();
        myObj.nextLine();

        System.out.print("Digite o valor do coeficiente c: ");
        double c = myObj.nextDouble();
        myObj.nextLine();

        return new double[] { a, b, c };
    }

    public static void calcularBhaskara(double a, double b, double c) {
        if (a == 0) {
            System.out.println("Não é uma equação de segundo grau.");
            return;
        }

        double delta = Math.pow(b, 2) - 4 * a * c;

        System.out.println("O valor de delta é: " + delta);

        if (delta < 0) {
            System.out.println("A equação não possui raizes reais.");
        } else if (delta == 0) {
            // **CORREÇÃO APLICADA AQUI**
            double x = -b / (2 * a);
            System.out.println("A equação possui uma raíz real: x = " + x);
        } else {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);
            System.out.println("A equação possui duas raízes reais:");
            System.out.println("x1 = " + x1);
            System.out.println("x2 = " + x2);
        }
    }

    // Aplicação 03
    public static void exibirComparacaoMedia(Scanner myObj) {
        // Exibe o resultado pro usuário
        int valores[] = inserirValoresMediaAritmetica(myObj);
        fazerMediaDeValores(valores[0], valores[1]);
    }

    public static int[] inserirValoresMediaAritmetica(Scanner myObj) {
        System.out.println("Insira um valor inteiro: ");
        int x1 = myObj.nextInt();
        System.out.println("Digite outro valor inteiro: ");
        int x2 = myObj.nextInt();
        myObj.nextLine();

        return new int[] { x1, x2 };
    }

    public static void fazerMediaDeValores(int x1, int x2) {
        int mediaDeValores = (x1 + x2) / 2;
        System.out.println("A média dos valores de x1: " + x1 + " e x2: " + x2 + " é: " + mediaDeValores);
    }

    // Aplicação 04
    public static void exibirTriangulo(Scanner myObj) {
        // Exibe o resultado ao usuário
        double[] medidas = medidasTriangulo(myObj);
        double a = medidas[0];
        double b = medidas[1];
        double c = medidas[2];

        if (verificadorDeTriangulo(a, b, c)) {
            // Lógica para determinar o tipo de triângulo
            if (a == b && b == c) {
                System.out.println("Esse triângulo é real e é do tipo Equilátero.");
            } else if (a == b || a == c || b == c) {
                System.out.println("Esse triângulo é real e é do tipo Isósceles.");
            } else {
                System.out.println("Esse triângulo é real e é do tipo Escaleno.");
            }
        } else {
            System.out.println("Esse triângulo é irreal.");
        }
    }

    public static double[] medidasTriangulo(Scanner myObj) {
        System.out.println("Inserindo valores do triângulo: ");
        System.out.print("\nInsira o primeiro valor: ");
        double a = myObj.nextDouble();
        System.out.print("Insira o segundo valor: ");
        double b = myObj.nextDouble();
        System.out.print("Insira o terceiro valor: ");
        double c = myObj.nextDouble();
        myObj.nextLine();

        return new double[] { a, b, c };
    }

    public static boolean verificadorDeTriangulo(double a, double b, double c) {
        return a + b > c && a + c > b && b + c > a;
    }

    // Aplicação 05
    public static void exibirSomatoria(int[] contadores) {
        // Demonstração do resultado para o usuário
        System.out.println("----------------------------------------");
        System.out.println("Somas positivas = " + contadores[0]);
        System.out.println("Somas negativas = " + contadores[1]);
    }

    public static int[] contadorDeValores(Scanner myObj) {
        int somasPositivas = 0;
        int somasNegativas = 0;
        int numeroLido;

        System.out.println("Digite N números (digite 0 para finalizar):");

        do {
            numeroLido = myObj.nextInt();
            myObj.nextLine();

            if (numeroLido > 0) {
                somasPositivas++;
            } else if (numeroLido < 0) {
                somasNegativas++;
            }

        } while (numeroLido != 0);

        return new int[] { somasPositivas, somasNegativas };
    }

    public static void operacaoComOsValores(Scanner myObj) {
        // Chama a função que realiza a contagem
        int[] resultados = contadorDeValores(myObj);

        // Passa o array de resultados para a função de exibição
        exibirSomatoria(resultados);

    }

    // Aplicação 06
    public static void exibirFatorial(Scanner myObj) {
        // Exibe o resultado para o usuário
        String continuar;

        do {
            int[] valores = leitorFatorial(myObj);
            int numero = valores[0];

            long resultado = operadorFatorial(numero);

            if (resultado != -1) {
                System.out.println("O fatorial de " + numero + " é " + resultado);
            }

            System.out.println("\nQuer repetir a operação com outro valor? Digite 'S' ou 's'.");
            continuar = myObj.nextLine();

        } while (continuar.equalsIgnoreCase("s"));
    }

    public static int[] leitorFatorial(Scanner myObj) {
        System.out.println("Digite o valor de um número inteiro: ");
        int numero = myObj.nextInt();
        myObj.nextLine();

        return new int[] { numero };
    }

    public static long operadorFatorial(int numero) {

        if (numero < 0) {
            System.out.println("Erro: O fatorial não é definido para números negativos.");
            return -1;
        }

        if (numero == 0) {
            return 1;
        }

        long fatorial = 1;
        for (int i = 1; i <= numero; i++) {
            fatorial = fatorial * i;
        }

        return fatorial;
    }

    // Aplicação 07
    

}

