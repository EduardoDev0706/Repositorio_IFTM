public class Fibonacci {

    private static long num_calls;

    public static long fib(int n) {
        // Incrementa o contador para cada chamada da função
        num_calls++;

        // Casos base
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        // Passo recursivo
        return fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        // Código para ler o número de casos de teste N
        // e o valor de X para cada caso.

        // Dentro do loop para cada caso de teste X:
        
        // Zere o contador antes de cada cálculo
        num_calls = 0; 
        
        long result = fib(X);
        
        // Imprima o resultado no formato especificado:
        System.out.println("fib(" + X + ") = " + (num_calls - 1) + " calls = " + result);
    }
}