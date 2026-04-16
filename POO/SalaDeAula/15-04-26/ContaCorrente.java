public class ContaCorrente implements Conta {
    private double saldo;
    private double taxa;

    public ContaCorrente(double taxa) {
        this.saldo = 0.0;
        this.taxa = taxa;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito realizado com sucesso na Conta Corrente!");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    @Override
    public void sacar(double valor) {
        double totalSaque = valor + this.taxa;
        if (valor > 0 && this.saldo >= totalSaque) {
            this.saldo -= totalSaque;
            System.out.println("Saque de R$ " + valor + " realizado! (Taxa cobrada: R$ " + this.taxa + ")");
        } else {
            System.out.println("Saldo insuficiente (lembre-se da taxa de R$ " + this.taxa + ") ou valor inválido.");
        }
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }
}