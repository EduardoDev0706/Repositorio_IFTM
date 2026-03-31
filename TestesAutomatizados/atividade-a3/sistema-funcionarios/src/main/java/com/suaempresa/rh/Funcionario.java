package com.suaempresa.rh;

public class Funcionario {
    private String nome;
    private int horasTrabalhadas;
    private double valorHora;

    public Funcionario() {}

    public Funcionario(String nome, int horasTrabalhadas, double valorHora) {
        setNome(nome);
        setHorasTrabalhadas(horasTrabalhadas);
        setValorHora(valorHora);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(int horasTrabalhadas) {
        // Regra de Negócio: Horas Mensais
        if (horasTrabalhadas < 20 || horasTrabalhadas > 160) {
            throw new IllegalArgumentException("As horas trabalhadas devem ser no mínimo 20 e no máximo 160.");
        }
        this.horasTrabalhadas = horasTrabalhadas;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        // Regra de Negócio: Valor Hora
        if (valorHora < 15.18 || valorHora > 151.80) {
            throw new IllegalArgumentException("O valor da hora deve estar entre R$ 15.18 e R$ 151.80.");
        }
        this.valorHora = valorHora;
    }

    public double calcularPagamento() {
        double pagamento = horasTrabalhadas * valorHora;
        validarLimitesTotaisPagamento(pagamento);
        return pagamento;
    }

    // Regra de Negócio: Limite Salarial (Protegido para reuso do Terceirizado)
    protected void validarLimitesTotaisPagamento(double pagamentoTotal) {
        if (pagamentoTotal < 1518.00 || pagamentoTotal > 10000.00) {
            throw new IllegalStateException("O pagamento total não pode ser inferior a R$ 1518.00 ou superior a R$ 10000.00.");
        }
    }
}