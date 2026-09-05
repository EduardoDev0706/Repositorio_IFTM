import javax.swing.JOptionPane;

public class App {

    public static void main(String[] args) {
        
        int opcao = 0;
        
        do {
            String menu = 
            "Escolha uma opção:\n\n" +
                          
            "1. Adicionar Inteiros\n" +
                          
            "2. Adicionar Floats\n" +
                          
            "3. Sair";
            
            try {
                opcao = leInt(menu);
            } catch (NumberFormatException e) {
                // Se o usuário clicar em Cancelar ou digitar algo inválido, a opção se torna 0
                opcao = 0;
            }

            switch (opcao) {
                case 1:
                    // Relaciona com as classes Dados1 e Adicao para inteiros
                    int valor1Int = leInt("Digite o primeiro valor inteiro:");
                    int valor2Int = leInt("Digite o segundo valor inteiro:");
                    
                    Dados1 dadosInt = new Dados1(valor1Int, valor2Int);
                    Adicao1 adicaoInt = new Adicao1();
                    
                    int resultadoInt = adicaoInt.addInt(dadosInt.getA(), dadosInt.getB());
                    String mensagemInt = adicaoInt.exibeInt(dadosInt.getA(), dadosInt.getB(), resultadoInt);

                    JOptionPane.showMessageDialog(null, mensagemInt);
                    break;
                
                case 2:
                    // Relaciona com as classes Dados1 e Adicao para floats
                    float valor1Float = leFloat("Digite o primeiro valor flutuante:");
                    float valor2Float = leFloat("Digite o segundo valor flutuante:");

                    Dados1 dadosFloat = new Dados1(valor1Float, valor2Float);
                    Adicao1 adicaoFloat = new Adicao1();
                    
                    float resultadoFloat = adicaoFloat.addFloat(dadosFloat.getC(), dadosFloat.getD());
                    String mensagemFloat = adicaoFloat.exibeFloat(dadosFloat.getC(), dadosFloat.getD(), resultadoFloat);

                    JOptionPane.showMessageDialog(null, mensagemFloat);
                    break;
                
                case 3:
                    // Opção para sair do programa
                    JOptionPane.showMessageDialog(null, "Saindo...");
                    break;
                
                default:
                    // Lida com opções inválidas ou o clique em Cancelar
                    JOptionPane.showMessageDialog(null, "Opção inválida. Por favor, tente novamente.");
            }

        } while (opcao != 3); 
    }

    public static int leInt(String mensagem) {
        String input = JOptionPane.showInputDialog(null, mensagem);
        // Lida com o caso do usuário clicar em Cancelar
        if (input == null) {
            return 0;
        }
        return Integer.parseInt(input);
    }
    
    public static float leFloat(String mensagem) {
        String input = JOptionPane.showInputDialog(null, mensagem);
        // Lida com o caso do usuário clicar em Cancelar
        if (input == null) {
            return 0;
        }
        return Float.parseFloat(input);
    }
}