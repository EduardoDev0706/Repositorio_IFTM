package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {

    @NotBlank(message = "ID do cliente é obrigatório")
    private String id;

    @NotBlank(message = "Nome do cliente é obrigatório")
    private String nome;

    private String cargo;
    private String endereco;
    private String cidade;
    private String cep;
    private String pais;
    private String telefone;
    private String fax;
}
