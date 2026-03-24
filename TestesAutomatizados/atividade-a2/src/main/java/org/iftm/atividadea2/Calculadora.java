package org.iftm.atividadea2;

public class Calculadora {
    private int memoria;

    public Calculadora() {
        this.memoria = 0; // Ajustado para iniciar em 0
    }

    public Calculadora(int memoria) {
        this.memoria = memoria;
    }

    public int getMemoria() {
        return this.memoria;
    }

    public void zerarMemoria() {
        this.memoria = 0;
    }

    public void somar(int valor) {
        this.memoria += valor;
    }

    public void subtrair(int valor) {
        this.memoria -= valor; // Ajustado para realizar a subtração
    }

    public void multiplicar(int valor) {
        this.memoria *= valor; // Ajustado para realizar a multiplicação
    }

    public void dividir(int valor) throws ArithmeticException {
        if (valor == 0) // Ajustado para validar apenas divisão por zero
            throw new ArithmeticException("Divisão por zero!!!");
        this.memoria = this.memoria / valor;
    }

    public void exponenciar(int valor) throws ArithmeticException {
        if (valor > 10)
            throw new ArithmeticException("Expoente incorreto, valor máximo é 10.");
        
        int base = this.memoria;
        if (valor == 0) {
            this.memoria = 1;
        } else {
            // Ajustado para multiplicar a base pelo número correto de vezes
            for (int i = 1; i < valor; i++) {
                this.memoria *= base;
            }
        }
    }
}