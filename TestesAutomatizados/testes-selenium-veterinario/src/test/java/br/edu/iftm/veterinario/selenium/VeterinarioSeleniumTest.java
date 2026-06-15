package br.edu.iftm.veterinario.selenium;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VeterinarioSeleniumTest extends BaseSeleniumTest {

    @Test
    @DisplayName("Cadastrar veterinário")
    void deveCadastrarVeterinario() {
        String sufixo = gerarSufixoUnico();
        String nome = "Veterinario " + sufixo;
        String email = "vet" + sufixo + "@teste.com";
        String especialidade = "pequenos";
        String salario = "3200";

        driver.get(baseUrl + "/form");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome"))).sendKeys(nome);
        driver.findElement(By.id("inputEmail")).sendKeys(email);
        driver.findElement(By.id("inputEspecialidade")).sendKeys(especialidade);
        driver.findElement(By.id("inputSalario")).sendKeys(salario);

        driver.findElement(By.xpath("//button[contains(.,'Cadastrar')]")).click();

        WebElement linha = localizarLinhaPeloNome(nome);

        assertEquals(nome, linha.findElement(By.xpath("./td[1]")).getText());
        assertEquals(especialidade, linha.findElement(By.xpath("./td[2]")).getText());
        assertEquals(email, linha.findElement(By.xpath("./td[3]")).getText());
        assertTrue(linha.getText().contains("3200"));
    }

    @Test
    @DisplayName("Pesquisar veterinário")
    void devePesquisarVeterinario() {
        String sufixo = gerarSufixoUnico();
        String nome = "Pesquisa " + sufixo;
        String email = "pesquisa" + sufixo + "@teste.com";

        cadastrarVeterinario(nome, email, "grandes", "4100");

        driver.get(baseUrl + "/find");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nome")))
                .sendKeys("Pesquisa " + sufixo);

        driver.findElement(By.xpath("//button[contains(.,'Consultar')]")).click();

        WebElement linha = localizarLinhaPeloNome(nome);

        assertEquals(nome, linha.findElement(By.xpath("./td[1]")).getText());
        assertEquals(email, linha.findElement(By.xpath("./td[3]")).getText());
    }

    @Test
    @DisplayName("Alterar veterinário")
    void deveAlterarVeterinario() {
        String sufixo = gerarSufixoUnico();
        String codigo = sufixo.substring(sufixo.length() - 3);

        String nome = "AnaVet" + codigo;
        String email = "ana" + sufixo + "@teste.com";

        String novoNome = "BiaVet" + codigo;
        String novoEmail = "bia" + sufixo + "@teste.com";

        cadastrarVeterinario(nome, email, "aves", "5000");

        WebElement linha = localizarLinhaPeloNome(nome);
        linha.findElement(By.cssSelector("a.btn-warning")).click();

        WebElement campoNome = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("nome"))
        );

        assertEquals(nome, campoNome.getAttribute("value"));
        assertEquals(email, driver.findElement(By.id("inputEmail")).getAttribute("value"));
        assertEquals("aves", driver.findElement(By.id("inputEspecialidade")).getAttribute("value"));
        assertEquals("5000.00", driver.findElement(By.id("inputSalario")).getAttribute("value"));

        campoNome.clear();
        campoNome.sendKeys(novoNome);

        WebElement campoEmail = driver.findElement(By.id("inputEmail"));
        campoEmail.clear();
        campoEmail.sendKeys(novoEmail);

        WebElement campoEspecialidade = driver.findElement(By.id("inputEspecialidade"));
        campoEspecialidade.clear();
        campoEspecialidade.sendKeys("silvestres");

        WebElement campoSalario = driver.findElement(By.id("inputSalario"));
        campoSalario.clear();
        campoSalario.sendKeys("6500");

        driver.findElement(By.xpath("//button[contains(.,'Atualizar')]")).click();

        WebElement linhaAlterada = localizarLinhaPeloNome(novoNome);

        assertEquals(novoNome, linhaAlterada.findElement(By.xpath("./td[1]")).getText());
        assertEquals("silvestres", linhaAlterada.findElement(By.xpath("./td[2]")).getText());
        assertEquals(novoEmail, linhaAlterada.findElement(By.xpath("./td[3]")).getText());
        assertFalse(existeTextoNaPagina(email));
    }

    @Test
    @DisplayName("Excluir veterinário")
    void deveExcluirVeterinario() {
        String sufixo = gerarSufixoUnico();
        String nome = "Excluir " + sufixo;
        String email = "excluir" + sufixo + "@teste.com";

        cadastrarVeterinario(nome, email, "felinos", "2800");

        WebElement linha = localizarLinhaPeloNome(nome);
        linha.findElement(By.cssSelector("a.btn-danger")).click();

        wait.until(ExpectedConditions.urlContains("/home"));

        List<WebElement> resultado = driver.findElements(
                By.xpath("//tbody/tr[td[normalize-space()='" + nome + "']]")
        );

        assertTrue(resultado.isEmpty());
        assertFalse(existeTextoNaPagina(email));
    }

    @Test
    @DisplayName("Listar veterinários")
    void deveListarVeterinarios() {
        abrirHome();

        WebElement tabela = driver.findElement(By.xpath("//table"));
        List<WebElement> linhas = driver.findElements(By.xpath("//tbody/tr[td]"));

        assertTrue(tabela.getText().contains("Nome"));
        assertTrue(tabela.getText().contains("Especialidade"));
        assertTrue(tabela.getText().contains("Email"));
        assertTrue(tabela.getText().contains("Salario"));
        assertFalse(linhas.isEmpty());

        assertTrue(
                existeTextoNaPagina("Conceição Evaristo") ||
                existeTextoNaPagina("Erica Queiroz Pinto")
        );
    }
}