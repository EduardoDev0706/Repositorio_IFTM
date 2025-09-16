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
                case 7:
                    exibirSomaImparesEPares(myObj);
                    break;
                case 8:
                    exibirNumeroPrimo(myObj);
                    break;
                case 9:
                    exibirFatorialDois(myObj);
                    break;
                case 10:
                    exibirMMC(myObj);
                    break;
                case 11:
                    exibirMDC(myObj);
                    break;
                case 12:
                    exibirFibonacci(myObj);
                    break;
                case 13:
                    exibirPA(myObj);
                    break;
                case 14:
                    exibirPG(myObj);
                    break;
                case 15:
                    exibirTabuada(myObj);
                    break;
                case 16:
                    exibirNumeroPerfeito(myObj);
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
    public static void exibirSomaImparesEPares(Scanner myObj) {
        // Exibir o resultado para o usuário
        int[] resultados = contadorDeValoresImparesEPares(myObj);
        exibirContador(resultados);
    }

    public static int[] contadorDeValoresImparesEPares(Scanner myObj) {
        int somaPares = 0, qtdPares = 0;
        int somaImpares = 0, qtdImpares = 0;
        int somaPositivos = 0, qtdPositivos = 0;
        int somaNegativos = 0, qtdNegativos = 0;
        int numeroLido;

        System.out.println("Digite N números, sejam positivos ou negativos (digite 0 para finalizar):");

        do {
            while (!myObj.hasNextInt()) {
                System.out.println("Entrada inválida, por favor digite um número inteiro.");
                myObj.next();
            }

            numeroLido = myObj.nextInt();
            myObj.nextLine();

            if (numeroLido != 0) {
                // Contador de somas impares e pares
                if (numeroLido % 2 == 0) {
                    somaPares += numeroLido;
                    qtdPares++;
                } else {
                    somaImpares += numeroLido;
                    qtdImpares++;
                }

                // Contador de somas positivas negativas e positivas
                if (numeroLido > 0) {
                    somaPositivos += numeroLido;
                    qtdPositivos++;
                } else {
                    somaNegativos += numeroLido;
                    qtdNegativos++;
                }
            }

        } while (numeroLido != 0);

        return new int[] {
                somaPares, qtdPares,
                somaImpares, qtdImpares,
                somaPositivos, qtdPositivos,
                somaNegativos, qtdNegativos
        };
    }

    public static void exibirContador(int[] resultados) {
        // Mapeamento dos índices da array:
        // [0] = somaPares, [1] = qtdPares
        // [2] = somaImpares, [3] = qtdImpares
        // [4] = somaPositivos, [5] = qtdPositivos
        // [6] = somaNegativos, [7] = qtdNegativos

        System.out.println("----------------------------------------");
        System.out.println("--- Resultados dos Cálculos ---");
        System.out.println("Soma de números pares: " + resultados[0]);
        System.out.println("Quantidade de números pares: " + resultados[1]);
        System.out.println("Soma de números ímpares: " + resultados[2]);
        System.out.println("Quantidade de números ímpares: " + resultados[3]);
        System.out.println("Soma de números positivos: " + resultados[4]);
        System.out.println("Quantidade de números positivos: " + resultados[5]);
        System.out.println("Soma de números negativos: " + resultados[6]);
        System.out.println("Quantidade de números negativos: " + resultados[7]);
        System.out.println("----------------------------------------");
    }

    // Aplicação 08
    public static void exibirNumeroPrimo(Scanner myObj) {
        // Exibe o resultado ao usuário
        int numero = leitorNumeroPrimo(myObj);
        boolean ehPrimo = operadorNumeroPrimo(numero);

        if (ehPrimo) {
            System.out.println("O número " + numero + " é primo.");
        } else {
            System.out.println("O número " + numero + " não é primo.");
        }

    }

    public static int leitorNumeroPrimo(Scanner myObj) {
        System.out.println("Digite um número e verifique se é primo: ");

        int numeroLido = myObj.nextInt();
        myObj.nextLine();

        return numeroLido;
    }

    public static boolean operadorNumeroPrimo(int numero) {
        if (numero <= 1) {
            System.out.println("O valor inserido não pode ser um número primo.");
            return false;
        }

        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }

        return true;

    }

    // Aplicação 09
    public static void exibirFatorialDois(Scanner myObj) {
        // Exibe o resultado para o usuário
        int numero = leiorFatorialDois(myObj);
        long resultado = operadorFatorialDois(numero);

        if (resultado != -1) {
            System.out.println("O fatorial de " + numero + " é " + resultado);
        }

    }

    public static int leiorFatorialDois(Scanner myObj) {
        System.out.println("Digite o valor de um número natural: ");
        int numero = myObj.nextInt();
        myObj.nextLine();

        return numero;
    }

    public static long operadorFatorialDois(int numero) {
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

    // Aplicação 10
    public static void exibirMMC(Scanner myObj) {
        // Exibe o resultado para o usuário
        int[] valores = leitorMMC(myObj);
        int a = valores[0];
        int b = valores[1];

        if (a == 0 || b == 0) {
            System.out.println("O MMC de números que incluem zero é zero.");
            return;
        }

        // Calcula o MDC usando o algoritmo de Euclides
        int mdc = calculoMDC(a, b);

        // Usa o MDC para calcualar o MMC
        long mmc = operadorMMC(a, b, mdc);

        // Exibe o resultado
        System.out.println("O MMC de " + a + " e " + b + " é " + mmc);

    }

    public static int[] leitorMMC(Scanner myObj) {

        System.out.println("Insira um número: ");
        int a = myObj.nextInt();
        System.out.println("Adicione outro número: ");
        int b = myObj.nextInt();
        myObj.nextLine();

        return new int[] { a, b };
    }

    public static int calculoMDC(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    public static long operadorMMC(int a, int b, int mdc) {
        return (long) Math.abs(a) / mdc * Math.abs(b);
    }

    // Aplicação 11
    public static void exibirMDC(Scanner myObj) {
        // Exibe o resultado ao usuário
        int[] valores = leitorMDC(myObj);
        int a = valores[0];
        int b = valores[1];

        if (a == 0) {
            System.out.println("Qualquer divisor inteiro de a é o zero absoluto.");
        }

        int mdc = operadorMDC(a, b);

        // Exibe o resultado
        System.out.println("O MDC de " + a + " e " + b + " é " + mdc);
    }

    public static int[] leitorMDC(Scanner myObj) {

        System.out.println("Insira um número: ");
        int a = myObj.nextInt();
        System.out.println("Adicione outro número: ");
        int b = myObj.nextInt();
        myObj.nextLine();

        return new int[] { a, b };
    }

    public static int operadorMDC(int a, int b) {
        {
            while (b != 0) {
                int temp = b;
                b = a % b;
                a = temp;
            }
            return Math.abs(a);
        }

    }

    // Aplicação 12
    public static void exibirFibonacci(Scanner myObj) {
        // Exibe o resultado ao usuário
        int valorTermo = leitorFibonacci(myObj);
        int resultado = operadorFibonacci(valorTermo);

        System.out.println("O " + valorTermo + "º termo de Fibonacci é: " + resultado);

    }

    public static int leitorFibonacci(Scanner myObj) {
        System.out.println("Insira um valor inteiro para o termo: ");
        int termo = myObj.nextInt();
        myObj.nextLine();

        return termo;
    }

    public static int operadorFibonacci(int termo) {
        if (termo <= 1) {
            return termo;
        }
        return operadorFibonacci(termo - 1) + operadorFibonacci(termo - 2);
    }

    // Aplicação 13
    public static void exibirPA(Scanner myObj) {
        // Exibe o resultado ao usuário
        int[] valores = leitorPA(myObj);
        int a1 = valores[0]; // Primeiro termo
        int r = valores[1]; // Razão
        int posicaoPA = valores[2]; // Posição

        int resultado = operadorPA(a1, r, posicaoPA);

        System.out.println("O termo da PA na posição " + posicaoPA + " é: " + resultado);

    }

    public static int[] leitorPA(Scanner myObj) {
        System.out.println("Insira o primeiro termo da PA: ");
        int primeiroTermo = myObj.nextInt();

        System.out.println("Agora, insira a razão da PA: ");
        int razaoPA = myObj.nextInt();

        System.out.println("E por fim, insira a posição do termo que você quer encontrar: ");
        int posicaoPA = myObj.nextInt();
        myObj.nextLine();

        return new int[] { primeiroTermo, razaoPA, posicaoPA };
    }

    public static int operadorPA(int primeiroTermo, int razao, int posicao) {
        return primeiroTermo + (posicao - 1) * razao;
    }

    // Aplicação 14
    public static void exibirPG(Scanner myObj) {
        // Exibe o resultado ao usuário
        int valores[] = leitorPG(myObj);
        double a1 = valores[0]; // Primeiro Termo PG
        double q = valores[1]; // Razão
        int n = valores[2]; // Número de Termos

        double resultado = operadorPG(a1, q, n);

        System.out.println("A soma dos primeiros " + n + " termos da PG é: " + resultado);
    }

    public static int[] leitorPG(Scanner myObj) {
        System.out.println("Insira o primeiro termo da PG: ");
        int primeiroTermo = myObj.nextInt();

        System.out.println("Agora, insira a razão da PG: ");
        int razaoPG = myObj.nextInt();

        System.out.println("E por fim, insira o número de termos da sua PG: ");
        int numTermos = myObj.nextInt();
        myObj.nextLine();

        return new int[] { primeiroTermo, razaoPG, numTermos };
    }

    public static double operadorPG(double a1, double q, int n) {
        if (q == 1) {
            return n * a1;
        }

        return a1 * (Math.pow(q, n) - 1) / (q - 1);
    }

    // Aplicação 15
    public static void exibirTabuada(Scanner myObj) {
        // Exibe o resultado ao usuário
        int valor = leitorTabuada(myObj);
        operadorTabuada(valor);
    }

    public static int leitorTabuada(Scanner myObj) {
        System.out.println("Insira um valor inteiro de 1 a 10 para ver a tabuada: ");
        int valor = myObj.nextInt();
        myObj.nextLine();

        return valor;
    }

    public static void operadorTabuada(int n) {
        if (n >= 1 && n <= 10) {
            System.out.println("--- Tabuada do " + n + " ---");

            for (int i = 1; i <= 10; i++) {
                int resultado = n * i;
                System.out.println(n + " x " + i + " = " + resultado);
            }
        } else {
            System.out.println("Número inválido. Insira um valor entre 1 a 10");
        }

    }

    // Aplicação 16
    public static void exibirNumeroPerfeito(Scanner myObj) {
        // Exibe o resultado ao usuário
        int valor = leitorNumeroPerfeito(myObj);
        boolean ehPerfeito = operadorNumeroPerfeito(valor);

        if (ehPerfeito) {
            System.out.println(valor + " é um número perfeito.");
        } else {
            System.out.println(valor + " não é um número perfeito.");
        }

    }

    public static int leitorNumeroPerfeito(Scanner myObj) {
        System.out.println("Insira um número inteiro e verifique se é perfeito: ");
        int numero = myObj.nextInt();
        myObj.nextLine();

        return numero;
    }

    public static boolean operadorNumeroPerfeito(int n) {
        if (n <= 1) {
            return false;
        }

        int somaDivisores = 1;

        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                somaDivisores += i;
            }
        }

        return somaDivisores == n;
    }
}
