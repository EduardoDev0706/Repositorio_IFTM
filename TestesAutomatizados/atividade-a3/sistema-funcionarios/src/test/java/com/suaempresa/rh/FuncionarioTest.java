package com.suaempresa.rh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    // --- REGRAS DE NEGÓCIO (Documentação Viva) ---
    private static final int HORAS_MINIMAS_PERMITIDAS = 20;
    private static final int HORAS_MAXIMAS_PERMITIDAS = 160;
    
    private static final double VALOR_HORA_MINIMO = 15.18;
    private static final double VALOR_HORA_MAXIMO = 151.80;
    
    private static final double PISO_SALARIAL = 1518.00;
    private static final double TETO_SALARIAL = 10000.00;

    private Funcionario funcionario;

    @BeforeEach
    public void setUp() {
        funcionario = new Funcionario();
    }

    // --- TESTES DE HORAS TRABALHADAS ---

    @Test
    public void testarModificarHorasAbaixoLimiteInferiorGeraErro() {
        // Arrange (Preparação)
        int horasAbaixoDoMinimo = HORAS_MINIMAS_PERMITIDAS - 1;

        // Act & Assert (Ação e Verificação)
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(horasAbaixoDoMinimo);
        });
        assertEquals("As horas trabalhadas devem ser no mínimo 20 e no máximo 160.", exception.getMessage());
    }

    @Test
    public void testarModificarHorasAcimaLimiteSuperiorGeraErro() {
        // Arrange
        int horasAcimaDoMaximo = HORAS_MAXIMAS_PERMITIDAS + 1;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setHorasTrabalhadas(horasAcimaDoMaximo);
        });
        assertEquals("As horas trabalhadas devem ser no mínimo 20 e no máximo 160.", exception.getMessage());
    }

    @Test
    public void testarModificarHorasComValoresValidosProduzPagamentoEsperado() {
        // Arrange
        int horasTrabalhadas = 100;
        double valorHora = 20.0;
        double pagamentoEsperado = 2000.0; // 100 * 20.0
        
        // Act
        funcionario.setHorasTrabalhadas(horasTrabalhadas);
        funcionario.setValorHora(valorHora);
        double pagamentoCalculado = funcionario.calcularPagamento();
        
        // Assert
        assertEquals(pagamentoEsperado, pagamentoCalculado, 0.01);
    }

    // --- TESTES DE VALOR HORA ---

    @Test
    public void testSetValorHoraAbaixoDoLimiteGeraErro() {
        // Arrange
        double valorAbaixoDoMinimo = VALOR_HORA_MINIMO - 0.01;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setValorHora(valorAbaixoDoMinimo);
        });
        assertEquals("O valor da hora deve estar entre R$ 15.18 e R$ 151.80.", exception.getMessage());
    }

    @Test
    public void testSetValorHoraAcimaDoLimiteGeraErro() {
        // Arrange
        double valorAcimaDoMaximo = VALOR_HORA_MAXIMO + 0.01;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            funcionario.setValorHora(valorAcimaDoMaximo);
        });
        assertEquals("O valor da hora deve estar entre R$ 15.18 e R$ 151.80.", exception.getMessage());
    }

    @Test
    public void testSetValorHoraValidoNaoLancaExcecao() {
        // Arrange
        double valorHoraValido = 50.0;

        // Act & Assert
        assertDoesNotThrow(() -> funcionario.setValorHora(valorHoraValido));
        assertEquals(valorHoraValido, funcionario.getValorHora());
    }

    // --- TESTES DE CÁLCULO DE PAGAMENTO (LIMITES) ---

    @Test
    public void testCalcularPagamentoAbaixoDoSalarioMinimoGeraErro() {
        // Arrange: Combinação que gera pagamento inferior a 1518.00 (Ex: 20h * R$20 = R$400)
        int horasMinimas = HORAS_MINIMAS_PERMITIDAS;
        double valorHoraBaixo = 20.0; 
        
        funcionario.setHorasTrabalhadas(horasMinimas);
        funcionario.setValorHora(valorHoraBaixo); 
        
        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            funcionario.calcularPagamento();
        });
        assertEquals("O pagamento total não pode ser inferior a R$ 1518.00 ou superior a R$ 10000.00.", exception.getMessage());
    }

    @Test
    public void testCalcularPagamentoAcimaDoTetoGeraErro() {
        // Arrange: Combinação que gera pagamento superior a 10000.00 (Ex: 150h * R$100 = R$15000)
        int horasAltas = 150;
        double valorHoraAlto = 100.0; 
        
        funcionario.setHorasTrabalhadas(horasAltas);
        funcionario.setValorHora(valorHoraAlto); 
        
        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            funcionario.calcularPagamento();
        });
        assertEquals("O pagamento total não pode ser inferior a R$ 1518.00 ou superior a R$ 10000.00.", exception.getMessage());
    }
}