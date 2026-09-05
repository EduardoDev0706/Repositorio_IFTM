public class Desenvolvedor extends Operacional {
    
    public Desenvolvedor(String nome, double salarioBase) { 
        super(nome, salarioBase);
    }

    @Override
    public double calcularSalarioLiquido() throws CalculoInvalidoException { // [cite: 68]
        Validacoes.validarValorPositivo(this.salarioBase); 
        
        // Adicional de 5% e desconto de 11% 
        double comAdicional = this.salarioBase * 1.05;
        double salarioLiquido = comAdicional - (comAdicional * 0.11);
        return salarioLiquido; 
    }
}