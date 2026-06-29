package com.iftm.client.resources;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.iftm.client.services.ClientService;

//necessário para utilizar o MockMVC
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientResourceIntegrationTest {
    @Autowired
    private MockMvc mockMVC;

    @Autowired
    private ClientService service;

    /**
     * Caso de testes : Verificar se o endpoint get/clients/ retorna todos os clientes existentes
     * Arrange:
     * - base de dado : 12 clientes
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Conceição Evaristo', '10619244881', 1500.0, TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:00Z', 2);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Lázaro Ramos', '10619244881', 2500.0, TIMESTAMP WITH TIME ZONE '1996-12-23T07:00:00Z', 2);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Clarice Lispector', '10919444522', 3800.0, TIMESTAMP WITH TIME ZONE '1960-04-13T07:50:00Z', 2);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Carolina Maria de Jesus', '10419244771', 7500.0, TIMESTAMP WITH TIME ZONE '1996-12-23T07:00:00Z', 0);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Gilberto Gil', '10419344882', 2500.0, TIMESTAMP WITH TIME ZONE '1949-05-05T07:00:00Z', 4);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Djamila Ribeiro', '10619244884', 4500.0, TIMESTAMP WITH TIME ZONE '1975-11-10T07:00:00Z', 1);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Jose Saramago', '10239254871', 5000.0, TIMESTAMP WITH TIME ZONE '1996-12-23T07:00:00Z', 0);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Toni Morrison', '10219344681', 10000.0, TIMESTAMP WITH TIME ZONE '1940-02-23T07:00:00Z', 0);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Yuval Noah Harari', '10619244881', 1500.0, TIMESTAMP WITH TIME ZONE '1956-09-23T07:00:00Z', 0);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Chimamanda Adichie', '10114274861', 1500.0, TIMESTAMP WITH TIME ZONE '1956-09-23T07:00:00Z', 0);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Silvio Almeida', '10164334861', 4500.0, TIMESTAMP WITH TIME ZONE '1970-09-23T07:00:00Z', 2);
INSERT INTO tb_client (name, cpf, income, birth_date, children) VALUES('Jorge Amado', '10204374161', 2500.0, TIMESTAMP WITH TIME ZONE '1918-09-23T07:00:00Z', 0);     * - Uma PageRequest default
     * @throws Exception 
     */
    @Test
    @DisplayName("Verificar se o endpoint get/clients/ retorna todos os clientes existentes")
    public void testarEndPointListarTodosClientesRetornaCorreto() throws Exception{
        //arrange
        int quantidadeClientes = 12;
        int quantidadeLinhasPagina = 12;

        //act

        ResultActions resultados = mockMVC.perform(get("/clients/").accept(MediaType.APPLICATION_JSON));

        //assign
        resultados
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[?(@.id == '%s')]",7L).exists())
            .andExpect(jsonPath("$.content[?(@.id == '%s')]",4L).exists())
            .andExpect(jsonPath("$.content[?(@.id == '%s')]",8L).exists())
            .andExpect(jsonPath("$.content[?(@.name == '%s')]","Toni Morrison").exists())
            .andExpect(jsonPath("$.totalElements").exists())
            .andExpect(jsonPath("$.totalElements").value(quantidadeClientes))
            .andExpect(jsonPath("$.numberOfElements").exists())
            .andExpect(jsonPath("$.numberOfElements").value(quantidadeLinhasPagina))
            .andExpect(jsonPath("$.content[*].id", containsInAnyOrder(4,10,3,1,6,5,12,7,2,11,8,9)));
    }
}
