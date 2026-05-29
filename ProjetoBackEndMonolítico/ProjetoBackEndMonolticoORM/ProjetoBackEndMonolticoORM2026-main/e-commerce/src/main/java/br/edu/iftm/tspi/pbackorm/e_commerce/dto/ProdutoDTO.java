package br.edu.iftm.tspi.pbackorm.e_commerce.dto;

import br.edu.iftm.tspi.pbackorm.e_commerce.dto.annotation.CategoriaExists;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDTO {
    private Integer id;

    @NotBlank(message  = "Nome do Produto é obrigatório")
    private String nome;

    @Positive(message = "O preço do produto deve ser maior do que 0")
    private Double preco;

    @Min(value = 0, message= "Não pode ter estoque negativo")
    private Short estoque;

    private String caminhoImagem;

    @CategoriaExists
    @NotNull
    private Integer idCategoria;
}
