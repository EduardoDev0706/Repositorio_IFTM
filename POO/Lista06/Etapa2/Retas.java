package Etapa2;

public class Retas {
    private double x1;
    private double y1;
    private double x2;
    private double y2;

    // Contador de retas instanciadas com sucesso
    public static int cont = 0;

    public Retas(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        Retas.cont++;
    }

    // Novo método: Validação movida para a classe Retas
    public static boolean valida(double x1, double y1, double x2, double y2) {
        return x1 >= 0 && y1 >= 0 && x2 >= 0 && y2 >= 0;
    }

    public double comprimento() {
        double deltaX = x2 - x1;
        double deltaY = y2 - y1;
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    public String exibe() {
        String compFormatado = String.format("%.2f", comprimento());
        
        return "Reta #" + cont + " definida pelos pontos: (" + x1 + ", " + y1 + ") e (" + x2 + ", " + y2 + ")" + 
        "\nComprimento da reta: " + compFormatado;
    }
}

