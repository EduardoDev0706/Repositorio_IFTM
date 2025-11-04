// Estudo de Construtores

public class Produto {
    private String nome;
    private double preco;

    // Construtor
    public Produto (String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }
}

// Criacao do objeto
Produto livro = new Produto ("O senhor dos anéis",50.0);

