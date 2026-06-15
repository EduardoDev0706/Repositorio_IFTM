package org.iftm.gerenciadorveterinarios.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

import javax.persistence.*;


@Entity
@Table(name = "tb_veterinario")
public class Veterinario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String especialidade;
    private BigDecimal salario;
    
    public Veterinario() {
    }

    public Veterinario(Integer id, String nome, String email, String especialidade, BigDecimal salario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.especialidade = especialidade;
        this.salario = salario;
    }

    public void setId(Integer id){
        this.id = id;
    }
    
    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    

    //private Instant dataContratacao;
}
