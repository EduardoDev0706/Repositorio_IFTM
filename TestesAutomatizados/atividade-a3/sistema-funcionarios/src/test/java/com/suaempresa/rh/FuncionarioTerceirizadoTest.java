package com.suaempresa.rh;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTerceirizadoTest {

    // --- REGRAS DE NEGÓCIO DO TERCEIRIZADO ---
    private static final double DESPESAS_MAXIMAS_PERMITIDAS = 1000.00;
    private static final double MULTIPLICADOR_BONUS_DESPESA = 1.10; // 10% de bônus

    private FuncionarioTerceirizado terceirizado;

    @BeforeEach
    public void setUp() {
        terceirizado = new FuncionarioTerceirizado();
        
        // Configuração base válida para que testes foquem apenas nas despesas
        int horasBaseValidas = 100;
        double valorHoraBaseValido = 20.0;
        
        terceirizado.setHorasTrabalhadas(horasBaseValidas);
        terceirizado.setValorHora(valorHoraBaseValido); 
    }

    // --- TESTES DE DESPESAS ADICIONAIS ---

    @Test
    public void testSetDespesasAdicionaisAcimaDoLimiteGeraErro() {
        // Arrange
        double despesaAcimaDoLimite = DESPESAS_MAXIMAS_PERMITIDAS + 0.01;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            terceirizado.setDespesasAdicionais(despesaAcimaDoLimite);
        });
        assertEquals("As despesas adicionais não podem ultrapassar R$ 1000.00 nem ser negativas.", exception.getMessage());
    }

    @Test
    public void testSetDespesasAdicionaisNegativasGeraErro() {
        // Arrange
        double despesaNegativa = -1.0;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            terceirizado.setDespesasAdicionais(despesaNegativa);
        });
        assertEquals("As despesas adicionais não podem ultrapassar R$ 1000.00 nem ser negativas.", exception.getMessage());
    }

    @Test
    public void testSetDespesasAdicionaisValidasNaoLancaExcecao() {
        // Arrange
        double despesaValida = 500.0;

        // Act & Assert
        assertDoesNotThrow(() -> terceirizado.setDespesasAdicionais(despesaValida));
        assertEquals(despesaValida, terceirizado.getDespesasAdicionais());
    }

    // --- TESTES DE CÁLCULO DE PAGAMENTO TERCEIRIZADO ---

    @Test
    public void testCalcularPagamentoTerceirizadoComDespesasAplicaBonusCorretamente() {
        // Arrange
        double despesaAdicional = 500.0;
        terceirizado.setDespesasAdicionais(despesaAdicional);
        
        // A base configurada no setUp é 100 * 20 = 2000
        double pagamentoBasePrevisto = 2000.0;
        double valorDoBonusCalculado = despesaAdicional * MULTIPLICADOR_BONUS_DESPESA; // 550.0
        double pagamentoTotalEsperado = pagamentoBasePrevisto + valorDoBonusCalculado; // 2550.0
        
        // Act
        double pagamentoCalculado = terceirizado.calcularPagamento();
        
        // Assert
        assertEquals(pagamentoTotalEsperado, pagamentoCalculado, 0.01);
    }
    
    @Test
    public void testCalcularPagamentoTerceirizadoSalvaPagamentoQueFicariaAbaixoDaBase() {
        // Arrange: Simulando um salário base que ficaria abaixo do piso de 1518.00 (Ex: 70h * R$20 = R$1400.00)
        int horasBaixas = 70;
        double valorHoraNormal = 20.0;
        terceirizado.setHorasTrabalhadas(horasBaixas);
        terceirizado.setValorHora(valorHoraNormal); 
        
        // Adicionando despesa que vai elevar o total para um valor legal (200 * 1.1 = 220) -> Total: 1620.0
        double despesaSalvadora = 200.0;
        terceirizado.setDespesasAdicionais(despesaSalvadora);
        double pagamentoTotalEsperado = 1620.0;
        
        // Act & Assert: Garantindo que a exceção de piso salarial não será lançada
        assertDoesNotThrow(() -> {
            double pagamentoFinal = terceirizado.calcularPagamento();
            assertEquals(pagamentoTotalEsperado, pagamentoFinal, 0.01);
        });
    }
}