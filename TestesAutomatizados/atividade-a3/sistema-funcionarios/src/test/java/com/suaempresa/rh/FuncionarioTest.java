package com.suaempresa.rh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    private Funcionario funcionario;

    @BeforeEach
    public void setUp() {
        funcionario = new Funcionario();
    }

    // --- TESTES DE HORAS TRABALHADAS ---

    @Test
    public void testarModificarHorasAbaixoLimiteInferiorGeraErro() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(19);
        });
        assertEquals("As horas trabalhadas devem ser no mínimo 20 e no máximo 160.", exception.getMessage());
    }

    @Test
    public void testarModificarHorasAcimaLimiteSuperiorGeraErro() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(161);
        });
        assertEquals("As horas trabalhadas devem ser no mínimo 20 e no máximo 160.", exception.getMessage());
    }

    @Test
    public void testarModificarHorasComValoresValidosProduzPagamentoEsperado() {
        funcionario.setHorasTrabalhadas(100);
        funcionario.setValorHora(20.0);
        
        // 100 horas * R$ 20.00 = R$ 2000.00 (Dentro do limite de 1518 a 10000)
        assertEquals(2000.0, funcionario.calcularPagamento(), 0.01);
    }

    // --- TESTES DE VALOR HORA ---

    @Test
    public void testSetValorHoraAbaixoDoLimiteGeraErro() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setValorHora(15.17);
        });
        assertEquals("O valor da hora deve estar entre R$ 15.18 e R$ 151.80.", exception.getMessage());
    }

    @Test
    public void testSetValorHoraAcimaDoLimiteGeraErro() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setValorHora(151.81);
        });
        assertEquals("O valor da hora deve estar entre R$ 15.18 e R$ 151.80.", exception.getMessage());
    }

    @Test
    public void testSetValorHoraValidoNaoLancaExcecao() {
        assertDoesNotThrow(() -> funcionario.setValorHora(50.0));
        assertEquals(50.0, funcionario.getValorHora());
    }

    // --- TESTES DE CÁLCULO DE PAGAMENTO (LIMITES) ---

    @Test
    public void testCalcularPagamentoAbaixoDoSalarioMinimoGeraErro() {
        funcionario.setHorasTrabalhadas(20);
        funcionario.setValorHora(20.0); // 20 * 20 = 400 (Abaixo de 1518)
        
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            funcionario.calcularPagamento();
        });
        assertEquals("O pagamento total não pode ser inferior a R$ 1518.00 ou superior a R$ 10000.00.", exception.getMessage());
    }

    @Test
    public void testCalcularPagamentoAcimaDoTetoGeraErro() {
        funcionario.setHorasTrabalhadas(150);
        funcionario.setValorHora(100.0); // 150 * 100 = 15000 (Acima de 10000)
        
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            funcionario.calcularPagamento();
        });
        assertEquals("O pagamento total não pode ser inferior a R$ 1518.00 ou superior a R$ 10000.00.", exception.getMessage());
    }
}