package br.edu.iftm.tspi.pbackorm.e_commerce.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="clientes")
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @Column(name="ClienteID",nullable=false,columnDefinition="CHAR(5)")
    private String id;

    private String nome;

    private String cargo;

    private String endereco;

    private String cidade;

    private String cep;

    private String pais;

    private String telefone;

    @Column(name="fax")
    private String fax;

    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos;
    

}
