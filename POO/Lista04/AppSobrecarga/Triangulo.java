package AppSobrecarga;

public class Triangulo {
    
    private double base;
    private double altura;

    // Construtor que recebe dois parâmetros para inicializar base e altura
    public Triangulo(double base, double altura)
    {
        this.base = base;
        this.altura = altura;
    }

    // Calcula a área do triângulo
    public double area()
    {
        return (this.base * this.altura) / 2.0;
    }

    // Método exibe() que mostra a área do triângulo
    public void exibe()
    {
        double valorArea = area();
        System.out.printf("A área do triângulo de base = %.2f e de altura = %.2f vale %.2f\n", 
        base, altura, valorArea);
    }
}
