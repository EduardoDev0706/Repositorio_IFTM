package AppSobrecarga;

public class Quadrado {
    private double lado;

    // Construtor que recebe um parâmetro para inicializar o lado
    public Quadrado(double lado)
    {
        this.lado = lado;
    }

    // Método que calcula a área do quadrado
    public double area()
    {
        return this.lado * this.lado;
    }

    // Método exibe() que mostra a área do quadrado
    public void exibe()
    {
        double valorArea = area();
        System.out.printf("A área do quadrado de lado = %.2f vale %.2f\n", 
        lado, valorArea);
    }
}
