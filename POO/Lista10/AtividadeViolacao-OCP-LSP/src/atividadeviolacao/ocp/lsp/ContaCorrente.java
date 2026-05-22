package atividadeviolacao.ocp.lsp;

public class ContaCorrente implements ContaPagadora {

    private double saldo;

    public ContaCorrente(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public void sacar(double valor) {
        this.saldo -= valor;
    }

    @Override
    public void pagarBoleto(double valor) {
        this.saldo -= valor;
        System.out.println("Boleto pago. Saldo: " + this.saldo);
    }
}

