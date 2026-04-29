public class EmpregadoComissionado extends Empregado {
    private double vendasBrutasSemanais;
    private double taxaComissao;

    public EmpregadoComissionado(String nome, String sobrenome, String numeroSeguroSocial, double vendasBrutasSemanais, double taxaComissao) {
        super(nome, sobrenome, numeroSeguroSocial);
        setVendasBrutasSemanais(vendasBrutasSemanais);
        setTaxaComissao(taxaComissao);
    }

    public double getVendasBrutasSemanais() {
        return vendasBrutasSemanais;
    }

    public void setVendasBrutasSemanais(double vendasBrutasSemanais) {
        if (vendasBrutasSemanais < 0.0) {
            throw new IllegalArgumentException("As vendas brutas devem ser maiores ou iguais a zero.");
        }
        this.vendasBrutasSemanais = vendasBrutasSemanais;
    }

    public double getTaxaComissao() {
        return taxaComissao;
    }

    public void setTaxaComissao(double taxaComissao) {
        if (taxaComissao <= 0.0 || taxaComissao >= 1.0) {
            throw new IllegalArgumentException("A taxa de comissão deve ser > 0 e < 1.");
        }
        this.taxaComissao = taxaComissao;
    }

    @Override
    public double calculaPagamento() {
        return getVendasBrutasSemanais() * getTaxaComissao();
    }

    @Override
    public String toString() {
        return String.format("Empregado Comissionado: %s\nVendas Brutas Semanais: $%.2f; Taxa da comissão: %.2f", 
                super.toString(), getVendasBrutasSemanais(), getTaxaComissao());
    }
}