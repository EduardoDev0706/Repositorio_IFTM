public class EmpregadoMisto extends EmpregadoComissionado {
    private double salarioBase;

    public EmpregadoMisto(String nome, String sobrenome, String numeroSeguroSocial, double vendasBrutasSemanais, double taxaComissao, double salarioBase) {
        super(nome, sobrenome, numeroSeguroSocial, vendasBrutasSemanais, taxaComissao);
        setSalarioBase(salarioBase);
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 0.0) {
            throw new IllegalArgumentException("O salário-base deve ser maior ou igual a zero.");
        }
        this.salarioBase = salarioBase;
    }

    @Override
    public double calculaPagamento() {
        return getSalarioBase() + super.calculaPagamento();
    }

    @Override
    public String toString() {
        return String.format("Empregado Misto: %s %s\nNúmero do Seguro Social: %s\nVendas Brutas Semanais: $%.2f; Taxa da Comissão: %.2f; Salário-base: $%.2f",
                getNome(), getSobrenome(), getNumeroSeguroSocial(), getVendasBrutasSemanais(), getTaxaComissao(), getSalarioBase());
    }
}