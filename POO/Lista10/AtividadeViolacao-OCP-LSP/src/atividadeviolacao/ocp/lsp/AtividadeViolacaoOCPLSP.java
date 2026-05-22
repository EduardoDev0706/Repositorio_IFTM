package atividadeviolacao.ocp.lsp;

public class AtividadeViolacaoOCPLSP {
    
    public static void main(String[] args) {
        ProcessadorPagamento pg = new ProcessadorPagamento();
        
        // 1. Testando a Conta Corrente
        ContaCorrente cc = new ContaCorrente(1000);        
        
        // Instanciamos as estratégias (OCP na veia)
        EstrategiaPagamento pgBoleto = new PagamentoBoleto();
        EstrategiaPagamento pgSaque = new PagamentoSaque(); 
        
        // Passamos o objeto, não mais a String
        pg.processar(cc, 100, pgBoleto);
        pg.processar(cc, 100, pgSaque);
        
        System.out.println("Saldo da conta corrente: " + cc.getSaldo());
       
        // 2. Testando a Conta Poupança
        ContaPoupanca cp = new ContaPoupanca(1000);
        
        // A Poupança só consegue executar o que é compatível com a interface dela.
        // Se a classe PagamentoBoleto foi desenhada para aceitar apenas "ContaPagadora",
        // tentar passar "cp" (que é apenas "Conta") no pgBoleto causaria erro de compilação!
        
        pg.processar(cp, 100, pgSaque);
        System.out.println("\nSaldo da conta poupanca: " + cp.getSaldo());
    }
}