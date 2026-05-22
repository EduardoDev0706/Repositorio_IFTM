package atividadeviolacao.ocp.lsp;

public class PagamentoBoleto implements EstrategiaPagamento {
    @Override
    public void executar(Conta conta, double valor) {
        // Verifica se a conta injetada assinou o contrato ContaPagadora
        if (conta instanceof ContaPagadora) {
            // Faz o cast e executa
            ((ContaPagadora) conta).pagarBoleto(valor);
        } else {
            System.out.println("Erro: Esta conta não suporta pagamento de boletos.");
        }
    }
}

