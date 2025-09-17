import java.time.LocalDate;

public class JogadorDeFutebol {
    // Definição das variáveis
    private String nome;
    public String posicao;
    public int anoNascimento;
    public String nacionalidade;
    public double altura;
    public double peso;

    public JogadorDeFutebol(String nome, String posicao, int anoNascimento, String nacionalidade, double altura, double peso)
    {
        // Início do Construtor
        this.nome = nome;
        this.posicao = posicao;
        this.anoNascimento = anoNascimento;
        this.nacionalidade = nacionalidade;
        this.altura = altura;
        this.peso = peso;
    }

    public String getNome()
    {   
        // Chama a variável 'nome'
        return nome;
    }

    public int calculaIdade()
    {
        // Data Atual - Ano Nascimento
        return LocalDate.now().getYear() - this.anoNascimento;
    }

    public void exibeDados()
    {
        // Registra as informações fornecidas
        System.out.println("Nome: " + this.nome);
        System.out.println("Ano de Nascimento: " + this.anoNascimento);
        System.out.printf("Altura: %.2f m%n", this.altura);
        System.out.printf("Peso: %.1f kg%n", this.peso);
        System.out.println("Nacionalidade: " + this.nacionalidade);
        System.out.println("Posição: " + this.posicao);   
    }
}
