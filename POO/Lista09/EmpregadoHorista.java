public class EmpregadoHorista extends Empregado {
    private double salarioHora;
    private double horasTrabalhadas;

    public EmpregadoHorista(String nome, String sobrenome, String numeroSeguroSocial, double salarioHora, double horasTrabalhadas) {
        super(nome, sobrenome, numeroSeguroSocial);
        setSalarioHora(salarioHora);
        setHorasTrabalhadas(horasTrabalhadas);
    }

    public double getSalarioHora() {
        return salarioHora;
    }

    public void setSalarioHora(double salarioHora) {
        if (salarioHora < 0.0) {
            throw new IllegalArgumentException("O salário por hora deve ser maior ou igual a zero.");
        }
        this.salarioHora = salarioHora;
    }

    public double getHorasTrabalhadas() {
        return horasTrabalhadas;
    }

    public void setHorasTrabalhadas(double horasTrabalhadas) {
        if (horasTrabalhadas < 0.0 || horasTrabalhadas > 168.0) { // 168 = horas em uma semana
            throw new IllegalArgumentException("As horas trabalhadas devem estar entre 0 e 168.");
        }
        this.horasTrabalhadas = horasTrabalhadas;
    }

    @Override
    public double calculaPagamento() {
        if (getHorasTrabalhadas() <= 40) {
            return getSalarioHora() * getHorasTrabalhadas();
        } else {
            return (40 * getSalarioHora()) + ((getHorasTrabalhadas() - 40) * getSalarioHora() * 1.5);
        }
    }

    @Override
    public String toString() {
        return String.format("Empregado Horista: %s\nValor por Hora: $%.2f; Horas Trabalhadas: %.2f", 
                super.toString(), getSalarioHora(), getHorasTrabalhadas());
    }
}