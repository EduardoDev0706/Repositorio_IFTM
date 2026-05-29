package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaDTO {

    private Integer id;

    @NotBlank(message  ="O campo de nome da categoria é obrigatório")
    private String nome;

    private String descricao;

}
