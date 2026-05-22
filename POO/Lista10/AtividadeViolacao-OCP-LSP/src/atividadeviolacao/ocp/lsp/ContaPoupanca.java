
package atividadeviolacao.ocp.lsp;

/*

Resposta: 

Esta operação viola o princípio LSP. Utilizar o 'extends' para "reaproveitar" a variável saldo e o método sacar, também faz com que o método pagarBoleto seja utilizado, algo que a poupança não sabe fazer (e nem deve). Isso quebra o sistema, pois ao tentar realizar o pagamento de um boleto, o uso da conta poupança vai travar a operação.

Solução: É preciso arrumar a árvore de herança, no qual uma classe só pode herdar comportamentos que podem serem cumpridas inteiramente. Ambas devem herdar de uma abstração mais genérica que contenha apenas o que é comum as duas (guardar dinheiro).

*/

public class ContaPoupanca implements Conta {
    private double saldo;

    public ContaPoupanca(double saldo) {
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
    
    // Ela não precisa do pagarBoleto, e o sistema não vai exigir isso dela!
}