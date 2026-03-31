package com.suaempresa.rh;

public class FuncionarioTerceirizado extends Funcionario {
    
    private double despesasAdicionais;

    public FuncionarioTerceirizado() {
        // Usada para acessar a classe pai imediatamente (herança)
        super();
    }

    public FuncionarioTerceirizado(String nome, int horasTrabalhadas, double valorHora, double despesasAdicionais) {
        super(nome, horasTrabalhadas, valorHora);
        setDespesasAdicionais(despesasAdicionais);
    }

    public double getDespesasAdicionais() {
        return despesasAdicionais;
    }

    public void setDespesasAdicionais(double despesasAdicionais) {
        // Regra de Negócio: Despesas
        if (despesasAdicionais < 0 || despesasAdicionais > 1000.00) {
            throw new IllegalArgumentException("As despesas adicionais não podem ultrapassar R$ 1000.00 nem ser negativas.");
        }
        this.despesasAdicionais = despesasAdicionais;
    }

    @Override
    public double calcularPagamento() {
        double pagamentoBase = getHorasTrabalhadas() * getValorHora();
        // Regra de Negócio: Bônus de 10%
        double pagamentoTotal = pagamentoBase + (despesasAdicionais * 1.10);
        
        validarLimitesTotaisPagamento(pagamentoTotal);
        
        return pagamentoTotal;
    }
}