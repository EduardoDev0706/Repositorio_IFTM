public abstract class CargoEstrategico extends Colaborador implements Avaliavel {
    protected double kpiScore = 0.0;

    public CargoEstrategico(String nome, double salarioBase) { // [cite: 43]
        super(nome, salarioBase);
    }

    @Override
    public void registrarKpi(double score) { 
        this.kpiScore = score;
    }
}