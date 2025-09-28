package AppSobrecarga;

public class Circulo {
    // Atritubos privativos
    private double raio;
    private double xcentro;
    private double ycentro;

    // Constante PI para o cálculo da área
    private static final double PI =  3.141592653589793;

    // Construtor 1: Recebe 3 parâmentros para inicializar todos os atributos
    public Circulo(double raio, double xcentro, double ycentro)
    {
        this.raio = raio;
        this.xcentro = xcentro;
        this.ycentro = ycentro;
    }

    // Construtor 2: Recebe 2 parâmetros para inicializar xcentro e ycentro
    public Circulo(double xcentro, double ycentro)
    {
        this.xcentro = xcentro;
        this.ycentro = ycentro;
        this.raio = 0.0;
    }

    // Método area() (Versão sem parâmetros)
    public double area()
    {
        return PI * this.raio * this.raio;
    }

    // Método area() sobrecarregado (Versão com parâmentro)
    public double area(double r)
    {
        return PI * r * r;
    }

    //  Método exibe() sobrecarregado
    public void exibe()
    {
        double valorArea = area();
        System.out.printf("A área do círculo de centro (%.1f , %.1f) e de raio = %.2f vale %.2f\n", 
        xcentro, ycentro, raio, valorArea);
    }
}
