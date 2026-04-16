public class ContaPoupanca implements Conta {
    private double saldo;

    public ContaPoupanca() {
        this.saldo = 0.0;
    }

    @Override
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            System.out.println("Depósito realizado com sucesso na Conta Poupança!");
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }

    @Override
    public void sacar(double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("Saque realizado com sucesso na Conta Poupança!");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    @Override
    public double getSaldo() {
        return this.saldo;
    }
}

