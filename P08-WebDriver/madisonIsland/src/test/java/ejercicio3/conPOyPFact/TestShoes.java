package ejercicio3.conPOyPFact;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.*;

public class TestShoes {
    WebDriver driver;
    MyAccountPage accP;

    @BeforeAll
    static void setDriver() {
        WebDriverManager.chromedriver().setup();
        Cookies.storeCookiesToFile("ejercicio1@test.com", "ejercicio1", "cookies.data");
    }

    @BeforeEach
    public void setUp() {
        ChromeOptions co = new ChromeOptions();
        // recuperamos el valor de la propiedad chromeHeadless definida en surefire
        boolean headless = Boolean.parseBoolean(System.getProperty("chromeHeadless"));
        if (headless) {
            co.addArguments("--headless");
        }


        // ahora creamos una instancia de CromeDriver a partir de chromeOptions
        driver = new ChromeDriver(co);

        Cookies.loadCookiesFromFile(driver);

        driver.get("http://demo.magento.recolize.com/customer/account/");
        accP = PageFactory.initElements(driver, MyAccountPage.class);
    }



    @Test
    public void R6_requirement_PO_compareShoes_should_clear_comparison_when_TwoShoes_are_compared_and_cleared(){
        //1
        Assertions.assertEquals("My Account", accP.getMyAccountTitle());
        // 2.
        ShoesPage shoesPage = accP.goShoesPage();

        //3
        Assertions.assertEquals("Shoes - Accessories", shoesPage.getShoesTitle());
        //4
        shoesPage.selectShoeToCompare(5);
        shoesPage.selectShoeToCompare(6);

        //5
        ProductComparisonPage comparisonPage = shoesPage.submitCompare();

        // 6.
        Assertions.assertEquals("Products Comparison List - Magento Commerce",
                comparisonPage.getProductsComparisonTitle());

        //7
        shoesPage = comparisonPage.close();

        // 8.
        Assertions.assertEquals("Shoes - Accessories", shoesPage.getShoesTitle());

        // 9.
        shoesPage.clearAll();

        // 10.
        Assertions.assertEquals("The comparison list was cleared.",
                shoesPage.getMessage());
    }
    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
