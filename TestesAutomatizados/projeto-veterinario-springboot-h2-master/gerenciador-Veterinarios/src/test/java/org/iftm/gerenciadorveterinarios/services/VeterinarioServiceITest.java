package org.iftm.gerenciadorveterinarios.services;
import org.iftm.gerenciadorveterinarios.entities.Veterinario;
import org.iftm.gerenciadorveterinarios.repositories.VeterinarioRepository;
import org.iftm.gerenciadorveterinarios.services.VeterinarioService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest 
@Transactional 
public class VeterinarioServiceITest {

    @Autowired
    private VeterinarioService service; 

    @Autowired
    private VeterinarioRepository repository; 

    @Test
    void deveBuscarVeterinarioPorIdComSucessoComLimiteCaracteres() {
        // Arrange
        Integer idExistente = 1;

        // Act
        Optional<Veterinario> resultado = service.buscaVeterinariosPeloId(idExistente);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals("Conceição Evaristo", resultado.get().getNome()); 
    }

    @Test
    void deveSalvarVeterinarioNoBancoDeDados() {
        // Arrange
        Veterinario novoVet = new Veterinario(null, "Dra. Marcia", "marcia@gmail.com", "Grandes Animais", BigDecimal.valueOf(5500.0));

        // Act
        Veterinario salvo = service.salvar(novoVet);

        // Assert
        assertNotNull(salvo.getId(), "O banco H2 deveria ter gerado um ID automático!");
        assertEquals("Dra. Marcia", salvo.getNome());

        // Prova Real
        Optional<Veterinario> vetNoBanco = repository.findById(salvo.getId());
        assertTrue(vetNoBanco.isPresent());
        assertEquals("marcia@gmail.com", vetNoBanco.get().getEmail());
    }

    @Test
    void deveLancarExcecaoAoApagarIdNaoExistente() {
        // Arrange
        Integer idInexistente = 9999; 
        int quantidadeOriginal = 2;

        // Act & Assert
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            service.apagar(idInexistente);
        });

        int quantidadeAtual = service.buscaTodosVeterinarios().size();
        assertEquals("Veterinário não existe no banco de dados.", e.getMessage());
        assertEquals(quantidadeOriginal, quantidadeAtual);
    }
}