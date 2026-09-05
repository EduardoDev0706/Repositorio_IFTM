public abstract class Colaborador implements Remuneravel {
    protected String nome; 
    protected double salarioBase; 
    public static int totalColaboradores = 0; 

    public Colaborador(String nome, double salarioBase) { 
        this.nome = nome;
        this.salarioBase = salarioBase;
        totalColaboradores++;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }
}
