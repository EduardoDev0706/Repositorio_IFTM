package org.iftm.gerenciadorveterinarios.entities;
import javax.persistence.*;

// Feito por Maxsuel Silva

@Entity
public class Funcionario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String cargo;
    private Double salario;
    private Boolean emFerias;

    public Funcionario() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }
    public Double getSalario() { return salario; }
    public void setSalario(Double salario) { this.salario = salario; }
    public Boolean getEmFerias() { return emFerias; }
    public void setEmFerias(Boolean emFerias) { this.emFerias = emFerias; }
}