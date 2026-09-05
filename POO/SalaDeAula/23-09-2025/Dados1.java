public class Dados1 {
    
    private int a, b;
    private float c, d;

    public Dados1() {}

    // Construtor com sobrecarga para inteiros
    public Dados1(int a, int b)
    {
        this.a = a;
        this.b = b;
    }

    // Construtor com sobrecarga para floats
    public Dados1(float c, float d)
    {
        this.c = c;
        this.d = d;
    }

    public int getA()
    {
        return this.a;
    }

        public int getB()
    {
        return this.b;
    }

        public float getC()
    {
        return this.c;
    }

        public float getD()
    {
        return this.d;
    }

}
