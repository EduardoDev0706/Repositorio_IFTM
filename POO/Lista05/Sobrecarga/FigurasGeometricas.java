public class FigurasGeometricas {
    
    // Variáveis Privadas
    private double lado;
    private double base;
    private double altura;
    private double xc;
    private double yc;
    private double raio;

    private static final double PI = 3.141592653589793;

    public FigurasGeometricas(double lado)
    {
        this.lado = lado;
    }

    public FigurasGeometricas(double base, double altura)
    {
        this.base = base;
        this.altura = altura;
    }

    public FigurasGeometricas(double xc, double yc, double raio)
    {
        this.xc = xc;
        this.yc = yc;
        this.raio = raio;
    }

    public double areaQuadrado()
    {
        return this.lado * this.lado;
    }

    public double areaTriangulo()
    {
        return (this.base * this.altura) / 2;
    }

    public double areaCirculo()
    {
        return PI * this.raio * this.raio;
    }

    public String exibeQuadrado()
    {
        double valorArea = areaQuadrado();
        return String.format("A área do quadrado de lado = %.2f vale %.2f", lado, valorArea);
    }

    public String exibeTriangulo()
    {
        double valorArea = areaTriangulo();
        return String.format("A área do triângulo de base = %.2f e de altura = %.2f vale %.2f", 
        base, altura, valorArea);
    }

    public String exibeCirculo()
    {
        
        double valorArea = areaCirculo();
        return String.format("A área do círculo de centro (%.1f , %.1f) e de raio = %.2f vale %.2f", 
        xc, yc, raio, valorArea);
    }
}
