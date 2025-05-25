package ejercicio2.conPO;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestLogin2 {
    WebDriver driver;
    HomePage poHome;
    CustomerLoginPage poCustomerLogin;
    MyAccountPage poMyAccount;

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        poHome = new HomePage(driver);
    }


    @Test
    public void R4_requierement_PO_loginOK_should_login_with_success_when_user_account_exists() {
        Assertions.assertEquals("Madison Island", poHome.getHomePageTitle());
        poCustomerLogin = poHome.goLogin();
        Assertions.assertEquals("Customer Login", poCustomerLogin.getCustomerLoginTitle());
        poCustomerLogin.loginOK("ejercicio1@test.com", "ejercicio1");
        poMyAccount = new MyAccountPage(driver);
        Assertions.assertEquals("My Account", poMyAccount.getAccountTitle());
    }
    @Test
    public void R5_requierement_PO_loginFailed_should_fail_when_user_account_not_exists(){
        Assertions.assertEquals("Madison Island", poHome.getHomePageTitle());
        poCustomerLogin = poHome.goLogin();
        Assertions.assertEquals("Customer Login", poCustomerLogin.getCustomerLoginTitle());
        Assertions.assertEquals("Invalid login or password.",
                poCustomerLogin.loginFailed("ejercicio1@test.com", "incorrecto"));

    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

}
