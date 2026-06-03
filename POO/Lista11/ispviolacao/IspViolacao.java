package ispviolacao;

import java.util.ArrayList;
import java.util.List;

public class IspViolacao {
    
    public static void main(String[] args) {
        
        // Instanciando os colaboradores
        Funcionario programador = new Programador();
        Funcionario gerente = new Gerente();
        Funcionario estagiario = new Estagiario();
        
        // Criando uma lista geral da empresa
        List<Funcionario> quadroDeFuncionarios = new ArrayList<>();
        quadroDeFuncionarios.add(programador);
        quadroDeFuncionarios.add(gerente);
        quadroDeFuncionarios.add(estagiario);
        
        System.out.println("--- FOLHA DE PAGAMENTO MENSAL ---");
        // Todos os funcionários recebem o salário normal
        for (Funcionario funcionario : quadroDeFuncionarios) {
            System.out.println("Cargo: " + funcionario.getCargo() + 
                               " | Pagamento: R$ " + funcionario.calculaSalario());
        }
        
        System.out.println("\n--- PAGAMENTO DO 13º SALÁRIO ---");
        // Apenas os elegíveis entram nesta lógica
        for (Funcionario funcionario : quadroDeFuncionarios) {
            
            // Verificamos se o funcionário possui a interface de 13º
            if (funcionario instanceof ElegivelAoDecimoTerceiro) {
                ElegivelAoDecimoTerceiro elegivel = (ElegivelAoDecimoTerceiro) funcionario;
                System.out.println("Cargo: " + funcionario.getCargo() + 
                                   " | 13º: R$ " + elegivel.calcula13o());
            }
        }
    }
}