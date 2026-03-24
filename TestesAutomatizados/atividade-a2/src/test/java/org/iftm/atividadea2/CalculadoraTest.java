package org.iftm.atividadea2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {

    @Test
    public void testConstrutorVazio() {
        // Arrange
        Calculadora calc;
        
        // Act
        calc = new Calculadora();
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(0, resultado, "A memória inicial deve ser 0");
    }

    @Test
    public void testConstrutorComParametroPositivo() {
        // Arrange
        Calculadora calc;
        
        // Act
        calc = new Calculadora(3);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(3, resultado, "A memória deve inicializar com 3");
    }

    @Test
    public void testConstrutorComParametroNegativo() {
        // Arrange
        Calculadora calc;
        
        // Act
        calc = new Calculadora(-3);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(-3, resultado, "A memória deve inicializar com -3");
    }

    @Test
    public void testSomarPositivo() {
        // Arrange
        Calculadora calc = new Calculadora(3);
        
        // Act
        calc.somar(5);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(8, resultado, "3 + 5 deve ser 8");
    }

    @Test
    public void testSomarNegativo() {
        // Arrange
        Calculadora calc = new Calculadora(3);
        
        // Act
        calc.somar(-2);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(1, resultado, "3 + (-2) deve ser 1");
    }

    @Test
    public void testSubtrairPositivo() {
        // Arrange
        Calculadora calc = new Calculadora(10);
        
        // Act
        calc.subtrair(4);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(6, resultado, "10 - 4 deve ser 6");
    }

    @Test
    public void testSubtrairNegativo() {
        // Arrange
        Calculadora calc = new Calculadora(10);
        
        // Act
        calc.subtrair(-4);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(14, resultado, "10 - (-4) deve ser 14");
    }

    @Test
    public void testMultiplicarPositivo() {
        // Arrange
        Calculadora calc = new Calculadora(5);
        
        // Act
        calc.multiplicar(3);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(15, resultado, "5 * 3 deve ser 15");
    }

    @Test
    public void testMultiplicarNegativo() {
        // Arrange
        Calculadora calc = new Calculadora(5);
        
        // Act
        calc.multiplicar(-2);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(-10, resultado, "5 * (-2) deve ser -10");
    }

    @Test
    public void testDividirPositivo() throws Exception {
        // Arrange
        Calculadora calc = new Calculadora(20);
        
        // Act
        calc.dividir(4);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(5, resultado, "20 / 4 deve ser 5");
    }

    @Test
    public void testDividirNegativo() throws Exception {
        // Arrange
        Calculadora calc = new Calculadora(20);
        
        // Act
        calc.dividir(-5);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(-4, resultado, "20 / (-5) deve ser -4");
    }

    @Test
    public void testDividirPorZero() {
        // Arrange
        Calculadora calc = new Calculadora(20);
        
        // Act & Assert
        assertThrows(Exception.class, () -> {
            calc.dividir(0);
        }, "Deve lançar Exception ao dividir por zero");
    }

    @Test
    public void testExponenciarUm() throws Exception {
        // Arrange
        Calculadora calc = new Calculadora(5);
        
        // Act
        calc.exponenciar(1);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(5, resultado, "5 elevado a 1 deve ser 5");
    }

    @Test
    public void testExponenciarDez() throws Exception {
        // Arrange
        Calculadora calc = new Calculadora(2);
        
        // Act
        calc.exponenciar(10);
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(1024, resultado, "2 elevado a 10 deve ser 1024");
    }

    @Test
    public void testExponenciarErroValorMaiorQueDez() {
        // Arrange
        Calculadora calc = new Calculadora(2);
        
        // Act & Assert
        assertThrows(Exception.class, () -> {
            calc.exponenciar(20);
        }, "Deve lançar Exception ao tentar elevar a um valor maior que 10");
    }

    @Test
    public void testZerarMemoria() {
        // Arrange
        Calculadora calc = new Calculadora(50);
        
        // Act
        calc.zerarMemoria();
        int resultado = calc.getMemoria();
        
        // Assert
        assertEquals(0, resultado, "A memória deve ser 0 após chamar zerarMemoria()");
    }
}
