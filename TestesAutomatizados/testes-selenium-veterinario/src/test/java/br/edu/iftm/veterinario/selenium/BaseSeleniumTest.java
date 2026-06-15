package br.edu.iftm.veterinario.selenium;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseSeleniumTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected final String baseUrl = System.getProperty("app.url", "http://localhost:8080");

    @BeforeEach
    void abrirNavegador() {
        WebDriverManager.chromedriver().clearDriverCache().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        if (Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1366,768");
        }

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterEach
    void fecharNavegador() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void abrirHome() {
        driver.get(baseUrl + "/home");
        esperarPaginaCarregar();
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(.,'Veterinarios') or contains(.,'Veterinários')]")
        ));
    }

    protected void cadastrarVeterinario(String nome, String email, String especialidade, String salario) {
        driver.get(baseUrl + "/form");
        esperarPaginaCarregar();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("nome"))).sendKeys(nome);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("inputEmail"))).sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("inputEspecialidade"))).sendKeys(especialidade);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("inputSalario"))).sendKeys(salario);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(.,'Cadastrar')]")
        )).click();

        wait.until(ExpectedConditions.urlContains("/home"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[normalize-space()='" + nome + "']")
        ));
    }

    protected WebElement localizarLinhaPeloNome(String nome) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[td[normalize-space()='" + nome + "']]")
        ));
    }

    protected boolean existeTextoNaPagina(String texto) {
        return !driver.findElements(By.xpath("//*[contains(normalize-space(), '" + texto + "')]")).isEmpty();
    }

    protected String gerarSufixoUnico() {
        return String.valueOf(System.currentTimeMillis());
    }

    protected void esperarPaginaCarregar() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete"));
    }
}