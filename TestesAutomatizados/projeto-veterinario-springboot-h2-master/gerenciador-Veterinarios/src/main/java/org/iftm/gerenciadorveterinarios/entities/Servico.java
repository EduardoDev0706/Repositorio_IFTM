package org.iftm.gerenciadorveterinarios.entities;
import javax.persistence.*;

// Feito por Gabriel Manzi
@Entity
public class Servico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double valor;
    private Integer tempoMinutos;
    private Boolean disponivel;

    public Servico() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }
    public Integer getTempoMinutos() { return tempoMinutos; }
    public void setTempoMinutos(Integer tempoMinutos) { this.tempoMinutos = tempoMinutos; }
    public Boolean getDisponivel() { return disponivel; }
    public void setDisponivel(Boolean disponivel) { this.disponivel = disponivel; }
}