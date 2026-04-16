public class GeradorExtratos {
    public String exibeExtrato(Conta conta) {
        // O método getSaldo() é chamado de forma polimórfica
        return String.format("\n=== EXTRATO DA CONTA ===\nSaldo atual: R$ %.2f\n========================", conta.getSaldo());
    }
}