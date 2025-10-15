public class ValidaTriangulo {
    public static boolean verifica(int a, int b, int c)
    {
        // Verifica se todos os lados são positivos
        if (a <= 0 || b <= 0 || c <= 0)
        {
            return false;
        }

        // Verifica a desigualdade triangular
        boolean condicao1 = (a + b) > c;
        boolean condicao2 = (a + c) > b; 
        boolean condicao3 = (b + c) > a;

        return condicao1 && condicao2 && condicao3;
    }
}
