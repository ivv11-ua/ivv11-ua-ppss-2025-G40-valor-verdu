package Ejercicio1.sinPageObject;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestLogin {
    private WebDriver driver;

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    //Antes de cada test se ejecuta
    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://demo.magento.recolize.com/");
    }
    @Test
    public void R2_requirement_loginOK_should_login_with_success_when_user_account_exists() {
        // 1. Verificar título de la página de inicio
        Assertions.assertEquals("Madison Island", driver.getTitle());
        //2.Seleccionamos acount y después login
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
        driver.findElement(By.cssSelector("a[title='Log In']")).click();

        // 3. Verificar título de la página "Customer Login"
        Assertions.assertEquals("Customer Login", driver.getTitle());

        // 4. Rellenar login y enviar formulario
        driver.findElement(By.cssSelector("input#email")).sendKeys("ejercicio1@test.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        // 5. Verificar mensaje "This is a required field"
        Assertions.assertEquals("This is a required field.",
                driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText());

        // 6. Rellenamos el campo contraseña y volvemos a enviar el form
        driver.findElement(By.cssSelector("input#pass")).sendKeys("ejercicio1");
        driver.findElement(By.cssSelector("button#send2")).click();

        // 7. Verificar login con título "My Account"
        Assertions.assertEquals("My Account", driver.getTitle());
        Assertions.assertEquals("Hello, ejercicio1 ejercicio1 ejercicio1!", driver.findElement(By.cssSelector("p.hello")).getText());

    }
    @Test
    public void R3_requirement_loginFailed_should_fail_when_user_account_not_exists(){
        // 1. Verificar título de la página de inicio
        Assertions.assertEquals("Madison Island", driver.getTitle());
        //2.Seleccionamos acount y después login
        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div[2]/div/a")).click();
        driver.findElement(By.cssSelector("a[title='Log In']")).click();
        // 3. Verificar título de la página "Customer Login"
        Assertions.assertEquals("Customer Login", driver.getTitle());

        // 4. Rellenar login con password incorrecto
        driver.findElement(By.cssSelector("input#email")).sendKeys("ejercicio1@test.com");
        driver.findElement(By.cssSelector("input#pass")).sendKeys("incorrecto");
        driver.findElement(By.cssSelector("button#send2")).click();

        // 5. Verificar mensaje "Invalid login or password"
        Assertions.assertEquals("Invalid login or password.",
                driver.findElement(By.cssSelector("li.error-msg")).getText());
    }
}
