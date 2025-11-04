public class Cliente {
    private String nome;
    private String email;

    // 1. Construtor com 2 argumentos (Nome e Email)
    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // 2. Construtor com 1 argumento (Apenas Nome - Sobrecarga)
    public Cliente(String nome) {
        this(nome, "email_nao_informado@exemplo.com"); // Chama o construtor 1
    }
    
    // 3. Construtor com 0 argumentos (Sobrecarga)
    public Cliente() {
        this("Sem Nome"); // Chama o construtor 2
    }
}