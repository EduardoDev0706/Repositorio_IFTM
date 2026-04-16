// A interface define o contrato que as contas deverão seguir

public interface Conta {
    void depositar(double valor);
    void sacar(double valor);
    double getSaldo();
}


