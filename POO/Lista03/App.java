import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        JogadorDeFutebol jogador = leDados();
        jogador.exibeDados();
        System.out.println(calculaTempoParaAposentar(jogador));
    }

    public static JogadorDeFutebol leDados()
    {
        Scanner myObj = new Scanner(System.in);
        String nome;
        String posicao;
        int anoNascimento;
        String nacionalidade;
        double altura;
        double peso;

        System.out.println("Digite o nome do jogador:");
        nome = myObj.nextLine();

        System.out.println("Digite a posição do jogador (Ataque, Defesa, Meio de Campo):");
        posicao = myObj.nextLine();

        System.out.println("Digite o ano de nascimento do jogador:");
        anoNascimento = myObj.nextInt();
        myObj.nextLine();

        System.out.println("Digite a nacionalidade do jogador:");
        nacionalidade = myObj.nextLine();

        System.out.println("Digite a altura do jogador (em metros, ex: 1,80):");
        altura = myObj.nextDouble();

        System.out.println("Digite o peso do jogador (em kg, ex: 85,0):");
        peso = myObj.nextDouble();

        return new JogadorDeFutebol(nome, posicao, anoNascimento, nacionalidade, altura, peso);
    }

    public static String calculaTempoParaAposentar(JogadorDeFutebol jogador)
    {
        int idadeAtual = jogador.calculaIdade();
        int idadeAposentadoria;
        String posicao = jogador.posicao.toLowerCase();

        if (posicao.contains("ataque"))
        {
            idadeAposentadoria = 35;
        } else if (posicao.contains("defesa"))
        {
            idadeAposentadoria = 40;
        } else if (posicao.contains("meio"))
        {
            idadeAposentadoria = 38;
        } else 
        {
            return "Posição inválida. Não é possível calcular a idade de aposentadoria";
        }

        int anosRestantes = idadeAposentadoria - idadeAtual;
        if (anosRestantes < 0)
        {
            anosRestantes = 0; // Caso tenha passado da idade, o valor retorna como zero
        }

        return String.format("Faltam %d anos para o jogador %s se aposentar.", anosRestantes, jogador.getNome());
    }
}
