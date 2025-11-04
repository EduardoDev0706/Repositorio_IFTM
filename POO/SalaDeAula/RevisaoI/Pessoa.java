// Estudo de atributos privativos

public class Pessoa {
    private int idade;

    // Getter Acesso de Leitura
    public int getIdade() {
        return idade;
    }

    // Setter Acesso de Escrita com Controle
    public void setIdade (int idade) {
        if (idade >= 0) { // Validação
            this.idade = idade;
        } else { // Exceção
            System.out.println("Idade inválida.");
        }
    }
}
