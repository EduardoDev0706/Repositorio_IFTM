public abstract class Empregado implements Pagavel {
    private final String nome;
    private final String sobrenome;
    private final String numemroSeguroSocial;

    public Empregado(String nome, String sobrenome, String numeroSeguroSocial) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.numemroSeguroSocial = numeroSeguroSocial;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNumeroSeguroSocial() {
        return numemroSeguroSocial;
    }

    @Override
    public String toString() {
        return String.format("%s %s\nNúmero do Seguro Social: %s", 
                getNome(), getSobrenome(), getNumeroSeguroSocial());
    }
}
