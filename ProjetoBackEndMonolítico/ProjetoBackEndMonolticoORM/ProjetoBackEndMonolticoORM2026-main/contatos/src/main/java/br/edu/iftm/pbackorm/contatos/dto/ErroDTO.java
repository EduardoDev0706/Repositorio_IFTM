package br.edu.iftm.pbackorm.contatos.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class ErroDTO {

    private String mensagem;

    private List<String> erros;

    private LocalDateTime dataHora;

}
