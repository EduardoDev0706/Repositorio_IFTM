public class Pessoa {
    private String nome;
    private char sexo;
    private String naturalidade;

    public Pessoa(String nome, char sexo, String naturalidade) {
        this.nome = nome;
        this.sexo = sexo;
        this.naturalidade = naturalidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

}
