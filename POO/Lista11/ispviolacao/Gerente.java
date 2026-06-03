package ispviolacao;

public class Gerente implements Funcionario, ElegivelAoDecimoTerceiro{
    
    @Override
    public String getCargo(){
        return "Gerente";
    }
    
    @Override
    public double calculaSalario(){
// lógica para calcular o salario do gerente
        return 8000.0;
    }
    
    @Override
    public double calcula13o(){
// lógica para calcular o décimo terceiro salario 
        return 8000.0;                  // do gerente
    }
}
