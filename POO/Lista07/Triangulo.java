public class Triangulo {
    private int ladoA;
    private int ladoB;
    private int ladoC;

    public Triangulo(int ladoA, int ladoB, int ladoC)
    {
        this.ladoA = ladoA;
        this.ladoB = ladoB;
        this.ladoC = ladoC;
    }

    public int getLadoA()
    {
        return ladoA;
    }

    public int getLadoB()
    {
        return ladoB;
    }

    public int getLadoC()
    {
        return ladoC;
    }

    public void setLadoA(int ladoA) {
        this.ladoA = ladoA;
    }

    public void setLadoB(int ladoB) {
        this.ladoB = ladoB;
    }

    public void setLadoC(int ladoC) {
        this.ladoC = ladoC;
    }

    public String tipoTriangulo()
    {
        if (ladoA == ladoB && ladoB == ladoC)
        {
            return "equilátero";
        } else if (ladoA == ladoB && ladoA == ladoC || ladoB == ladoC)
        {
            return "isósceles";
        } else {
            return "escaleno";
        }
    }
}
