package atividadeviolacao.ocp.lsp;

/*

Resposta:

Falha no Princípio OCP. Toda vez que ser necessário adicionar um novo método de pagamento, como o "PIX" ou "Cartão de Crédito", será preciso adicionar um novo IF/ELSE. Isso pode quebrar a lógica de pagamento do BOLETO, por exemplo.

Solução: Usando Poliformismo, vou inverter a lógica do ProcessadorPagamento para que ele desconheça como cada operação funciona, apenas mandar que execute.

*/
class ProcessadorPagamento {
   public void processar(Conta conta, double valor, EstrategiaPagamento operacao) {
    operacao.executar(conta, valor);
   }
}