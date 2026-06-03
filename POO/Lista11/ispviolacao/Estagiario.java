package ispviolacao;

public class Estagiario implements Funcionario{
    
    @Override
    public String getCargo(){
        return "Estagiário";
    }
    
    @Override
    public double calculaSalario(){
// lógica para calcular o salario do estagiário
        return 1200.0;
    }
}
