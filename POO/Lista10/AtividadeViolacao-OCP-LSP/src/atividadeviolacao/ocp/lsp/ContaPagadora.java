package atividadeviolacao.ocp.lsp;

public interface ContaPagadora extends Conta {
    void pagarBoleto(double valor);
}
