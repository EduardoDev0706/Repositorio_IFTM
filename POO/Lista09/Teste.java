public class Teste {
    public static void main(String[] args) {
        // Instanciando os empregados com os dados da imagem
        EmpregadoAssalariado empAssalariado = new EmpregadoAssalariado("John", "Smith", "111-11-1111", 800.00);
        EmpregadoHorista empHorista = new EmpregadoHorista("Karen", "Price", "222-22-2222", 16.75, 40.00);
        EmpregadoComissionado empComissionado = new EmpregadoComissionado("Sue", "Jones", "333-33-3333", 10000.00, 0.06);
        EmpregadoMisto empMisto = new EmpregadoMisto("Bob", "Lewis", "444-44-4444", 10000.00, 0.06, 300.00);

        // ==========================================
        // SEM POLIMORFISMO
        // ==========================================
        System.out.println("SEM POLIMORFISMO");
        System.out.println("Empregados Processados Individualmente:\n");

        System.out.println(empAssalariado);
        System.out.printf("Total Ganho: $%.2f\n\n", empAssalariado.calculaPagamento());

        System.out.println(empHorista);
        System.out.printf("Total Ganho: $%.2f\n\n", empHorista.calculaPagamento());

        System.out.println(empComissionado);
        System.out.printf("Total Ganho: $%.2f\n\n", empComissionado.calculaPagamento());

        System.out.println(empMisto);
        System.out.printf("Total Ganho: $%.2f\n\n", empMisto.calculaPagamento());

        // ==========================================
        // COM POLIMORFISMO
        // ==========================================
        System.out.println("--------------------------------------------------");
        System.out.println("COM POLIMORFISMO");
        System.out.println("Empregados Processados Polimorficamente:\n");

        // Criando um array do tipo da superclasse
        Empregado[] empregados = new Empregado[4];
        empregados[0] = empAssalariado;
        empregados[1] = empHorista;
        empregados[2] = empComissionado;
        empregados[3] = empMisto;

        // Iterando sobre o array
        for (Empregado empregadoAtual : empregados) {
            System.out.println(empregadoAtual);
            
            System.out.printf("Total Ganho: $%.2f\n\n", empregadoAtual.calculaPagamento());
        }
    }
}