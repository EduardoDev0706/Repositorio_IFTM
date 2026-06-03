package ispviolacao;

public class Programador implements Funcionario, ElegivelAoDecimoTerceiro {
    
    @Override
    public String getCargo(){
        return "Programador";
    }
    
    @Override
    public double calculaSalario(){
// lógica para calcular o salario do programador
        return 4500.0;            
    }
        
    @Override
    public double calcula13o(){
// lógica para calcular o décimo terceiro salario
        return 4500.0;                //do programador
    }    
}
