public class Gerente extends CargoEstrategico {
    private Departamento departamento;
    private Endereco endereco;

    public Gerente(String nome, double salarioBase, Departamento departamento, String logradouroEndereco) {
        super(nome, salarioBase);
        this.departamento = departamento;
        this.endereco = new Endereco(logradouroEndereco);
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public double calcularSalarioLiquido() throws CalculoInvalidoException { // [cite: 58]
        Validacoes.validarValorPositivo(this.salarioBase); // [cite: 59]

        double bruto = this.salarioBase + (this.salarioBase * (this.kpiScore / 100)); // [cite: 60]
        double imposto = (bruto > 7000) ? bruto * 0.275 : (bruto > 3000) ? bruto * 0.15 : 0; // [cite: 62]

        return bruto - imposto; // [cite: 63]
    }
}
