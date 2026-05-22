package atividadeviolacao.ocp.lsp;

public interface EstrategiaPagamento {
    void executar(Conta conta, double valor);
}


