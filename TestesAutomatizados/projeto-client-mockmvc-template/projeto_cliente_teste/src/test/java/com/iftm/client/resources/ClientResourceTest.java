package com.iftm.client.resources;

// Necessário para utilizar o MockMVC
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.iftm.client.dto.ClientDTO;
import com.iftm.client.entities.Client;
import com.iftm.client.services.ClientService;
import com.iftm.client.services.exceptions.ResourceNotFoundException;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientResourceTest {
    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private ClientService service;

    /**
     * Caso de testes : Verificar se o endpoint get/clients/ retorna todos os clientes existentes
     * Arrange:
     * - camada service simulada com mockito
     * - base de dado : 3 clientes
     * new Client(7l, "Jose Saramago", "10239254871", 5000.0, Instant.parse("1996-12-23T07:00:00Z"), 0);
     * new Client(4l, "Carolina Maria de Jesus", "10419244771", 7500.0, Instant.parse("1996-12-23T07:00:00Z"), 0);
     * new Client(8l, "Toni Morrison", "10219344681", 10000.0, Instant.parse("1940-02-23T07:00:00Z"), 0);
     * - Uma PageRequest default
     * @throws Exception
     */

    /**
        TESTE 1: FIND ALL
    */
    @Test
    @DisplayName("Verificar se o endpoint get/clients/ retorna todos os clientes existentes")
    public void testarEndPointListarTodosClientesRetornaCorreto() throws Exception{
        // Arrange
        int quantidadeClientes = 3;
        List<ClientDTO> listaClientes = new ArrayList<>();
        listaClientes.add(new ClientDTO(new Client(7L, "Jose Saramago", "10239254871", 5000.0, Instant.parse("1996-12-23T07:00:00Z"), 0)));
        listaClientes.add(new ClientDTO(new Client(4L, "Carolina Maria de Jesus", "10419244771", 7500.0, Instant.parse("1996-12-23T07:00:00Z"), 0)));
        listaClientes.add(new ClientDTO(new Client(8L, "Toni Morrison", "10219344681", 10000.0,Instant.parse("1940-02-23T07:00:00Z"), 0)));

        Page<ClientDTO> page = new PageImpl<>(listaClientes);

        Mockito.when(service.findAllPaged(Mockito.any())).thenReturn(page);        

        // Act
        ResultActions resultados = mockMVC.perform(get("/clients/")
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultados
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content").exists())
            .andExpect(jsonPath("$.content").isArray())
            .andExpect(jsonPath("$.content[?(@.id == '%s')]",7L).exists())
            .andExpect(jsonPath("$.content[?(@.id == '%s')]",4L).exists())
            .andExpect(jsonPath("$.content[?(@.id == '%s')]",8L).exists())
            .andExpect(jsonPath("$.content[?(@.name == '%s')]","Toni Morrison").exists())
            .andExpect(jsonPath("$.totalElements").exists())
            .andExpect(jsonPath("$.totalElements").value(quantidadeClientes));
    }

    /**
        TESTE 2: FIND BY ID - Sucesso
    */
    @Test 
    @DisplayName("Verificar se o endpoint get/clients/{id} retorna o cliente correto quando o id existe")
    public void testarEndPointBuscaPorIdRetornaCliente() throws Exception {
        // Arrange
        Long existingId = 3L;
        Client cliente = new Client(existingId, "Clarice Lispector", "10919444522", 3800.0, Instant.parse("1960-04-13T07:50:00Z"), 2);
        ClientDTO clientDTO = new ClientDTO(cliente);

        Mockito.when(service.findById(existingId)).thenReturn(clientDTO);

        // Act
        ResultActions resultados = mockMVC.perform(get("/clients/id/{id}", existingId)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultados
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(existingId))
            .andExpect(jsonPath("$.name").value("Clarice Lispector"))
            .andExpect(jsonPath("$.cpf").value("10919444522"))
            .andExpect(jsonPath("$.income").value(3800.0))
            .andExpect(jsonPath("$.children").value(2));
    }

    /**
        TESTE 3: FIND BY ID - FALHA (404 NOT FOUND)
    */
    @Test
    @DisplayName("Verificar se o endpoint get/clients/{id} retorna 404 Not Found quando o id não existe")
    public void testarEndPointBuscaPorIdRetornaNotFound() throws Exception {
        // Arrange
        Long nonExistingId = 33L;
        
        // Simula o service lançando a exceção
        Mockito.when(service.findById(nonExistingId)).thenThrow(new ResourceNotFoundException("Entity not found"));

        // Act
        ResultActions resultados = mockMVC.perform(get("/clients/id/{id}", nonExistingId)
                .accept(MediaType.APPLICATION_JSON));

        // Assert
        resultados
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error").value("Resource not found"))
            .andExpect(jsonPath("$.message").value("Entity not found"))
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.path").exists())
            .andExpect(jsonPath("$.timestamp").exists());
    }
}

