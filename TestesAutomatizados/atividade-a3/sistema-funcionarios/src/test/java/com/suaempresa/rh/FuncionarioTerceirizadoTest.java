package com.suaempresa.rh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTerceirizadoTest {

    private FuncionarioTerceirizadoTest terceirizado;

    @BeforeEach
    public void setUp() {
        terceirizado = new FuncionarioTerceirizado();
        // Setup base válido para a maioria dos testes
        terceirizado.setHorasTrabalhadas(100);
        terceirizado.setValorHora(20.0); // Base = 2000.00
    }

    // --- TESTES DE DESPESAS ADICIONAIS ---

    @Test
    public void testSetDespesasAdicionaisAcimaDoLimiteGeraErro() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            terceirizado.setDespesasAdicionais(1000.01);
        });
        assertEquals("As despesas adicionais não podem ultrapassar R$ 1000.00 nem ser negativas.", exception.getMessage());
    }

    @Test
    public void testSetDespesasAdicionaisNegativasGeraErro() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            terceirizado.setDespesasAdicionais(-1.0);
        });
        assertEquals("As despesas adicionais não podem ultrapassar R$ 1000.00 nem ser negativas.", exception.getMessage());
    }

    @Test
    public void testSetDespesasAdicionaisValidasNaoLancaExcecao() {
        assertDoesNotThrow(() -> terceirizado.setDespesasAdicionais(500.0));
        assertEquals(500.0, terceirizado.getDespesasAdicionais());
    }

    // --- TESTES DE CÁLCULO DE PAGAMENTO TERCEIRIZADO ---

    @Test
    public void testCalcularPagamentoTerceirizadoComDespesasAplicaBonusCorretamente() {
        terceirizado.setDespesasAdicionais(500.0);
        // Base: 100 * 20 = 2000
        // Adicional: 500 * 1.10 = 550
        // Total esperado: 2550.00
        assertEquals(2550.0, terceirizado.calcularPagamento(), 0.01);
    }
    
    @Test
    public void testCalcularPagamentoTerceirizadoSalvaPagamentoQueFicariaAbaixoDaBase() {
        // Horas e valor que dariam base de R$ 1400 (abaixo do piso)
        terceirizado.setHorasTrabalhadas(70);
        terceirizado.setValorHora(20.0); 
        
        // Adicionando despesas de R$ 200 (200 * 1.1 = 220)
        // Total: 1400 + 220 = 1620 (Agora é válido e > 1518)
        terceirizado.setDespesasAdicionais(200.0);
        
        assertDoesNotThrow(() -> {
            double pagamento = terceirizado.calcularPagamento();
            assertEquals(1620.0, pagamento, 0.01);
        });
    }
}