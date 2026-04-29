public class EmpregadoAssalariado extends Empregado {
    private double salarioSemanal;

    public EmpregadoAssalariado(String nome, String sobrenome, String numeroSeguroSocial, double salarioSemanal) {
        super(nome, sobrenome, numeroSeguroSocial);
        setSalarioSemanal(salarioSemanal);
    }

    public double getSalarioSemanal() {
        return salarioSemanal;
    }

    public void setSalarioSemanal(double salarioSemanal) {
        if (salarioSemanal < 0.0) {
            throw new IllegalArgumentException("O salário semanal deve ser maior ou igual a zero.");
        }
        this.salarioSemanal = salarioSemanal;
    }

    @Override
    public double calculaPagamento() {
        return getSalarioSemanal();
    }

    @Override
    public String toString() {
        return String.format("Empregado Assalariado: %s\nSalário Semanal: $%.2f", 
                super.toString(), getSalarioSemanal());
    }
}