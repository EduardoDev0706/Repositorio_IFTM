package org.iftm.gerenciadorveterinarios.entities;
import javax.persistence.*;

// Feito por Eduardo do Carmo Pereira

@Entity
public class Animal {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String especie;
    private Integer idade;
    private Boolean internado;

    // Construtores, Getters e Setters obrigatórios
    public Animal() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }
    public Boolean getInternado() { return internado; }
    public void setInternado(Boolean internado) { this.internado = internado; }
}
