import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;

public class PageFactoryAddressesTest {

    private static WebDriver driver;
    private String testLogin = "aga.test.selenium@gmail.com";
    private String testPass = "selenium_passwd";
/*
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void testLogin() throws Exception {
        PageFactoryAddresses homePage = new PageFactoryAddresses(driver);
        homePage.loginAt(testLogin, testPass);
        homePage.waitUntilPresent(By.className("navbar-text"));  // login zalogowanego użytkownika po prawej stronie
        assertTrue(homePage.assertLogged());
    }

    @Test
    public void testLoginFailNoEmail() throws Exception {
        PageFactoryAddresses homePage = new PageFactoryAddresses(driver);
        homePage.loginAt("", testPass);
        assertTrue(homePage.assertLoggingError());
    }
    @Test
    public void testLoginFailNoPassword() throws Exception {
        PageFactoryAddresses homePage = new PageFactoryAddresses(driver);
        homePage.loginAt(testLogin, "");
        assertTrue(homePage.assertLoggingError());
    }
    @Test
    public void testLoginFail() throws Exception {
        PageFactoryAddresses homePage = new PageFactoryAddresses(driver);
        homePage.loginAt("", "");
        assertTrue(homePage.assertLoggingError());
    }
    @Test
    public void testLoginFailWrongData() throws Exception {
        PageFactoryAddresses homePage = new PageFactoryAddresses(driver);
        homePage.loginAt(testLogin, "pass");
        assertTrue(homePage.assertLoggingError());
    }


    @Test
    public void testAddSuccess() throws Exception {
        PageFactoryAddresses homePage = new PageFactoryAddresses(driver);
        homePage.loginAt(testLogin, testPass);
        homePage.enterAddress();
        homePage.addNewAddress_onlyRequired("Ala", "Nowak", "Mezotanska 11", "87-009", "Zgierz");
        homePage.waitUntilPresent(By.xpath("//div[@data-test='notice']"));
        assertTrue(homePage.assertAddressAdded());
    }

    @Test
    public void testAddTwiceSuccess() throws Exception {
        PageFactoryAddresses homePage = new PageFactoryAddresses(driver);
        homePage.loginAt(testLogin, testPass);
        homePage.enterAddress();
        int before = homePage.getSavedAddressesCount();

        homePage.addNewAddress_onlyRequired("Ala", "Nowak", "Mezotanska 11", "87-009", "Zgierz");
        homePage.waitUntilPresent(By.xpath("//div[@data-test='notice']"));
        homePage.enterAddress();
        homePage.addNewAddress_onlyRequired("Adam", "Kowalski", "Grunwaldzka 111", "989-082", "Gdańsk");
        homePage.waitUntilPresent(By.xpath("//div[@data-test='notice']"));
        homePage.enterAddress();

        int after =  homePage.getSavedAddressesCount();
        int saved = after - before;
        assertEquals(2, saved);
    }

    @Test
    public void testTryAddWithoutName() throws Exception {
        PageFactoryAddresses homePage = new PageFactoryAddresses(driver);
        homePage.loginAt(testLogin, testPass);
        homePage.enterAddress();
        homePage.clickNewAddress();
        homePage.enterValueToField(1, "Mzór");
        homePage.enterValueToField(2, "Kalicka 11");
        homePage.enterValueToField(4, "Gdynia");
        homePage.enterValueToField(5, "11-123");
        homePage.submit();
        homePage.waitUntilPresent(By.id("error_explanation"));
        assertAll("Should have this errors",
                () -> assertTrue(homePage.assertErrorOccurred()),
                () -> assertTrue(homePage.assertHasError("First name can't be blank"))
        );
    }

    @Test
    public void testTryAddFailForEmpty() throws Exception {
        PageFactoryAddresses homePage = new PageFactoryAddresses(driver);
        homePage.loginAt(testLogin, testPass);
        homePage.enterAddress();
        homePage.addNewAddress_onlyRequired("", "", "", "", "");
        homePage.waitUntilPresent(By.id("error_explanation"));

        assertAll("Should have this errors",
                () -> assertTrue(homePage.assertErrorOccurred()),
                () -> assertTrue(homePage.assertHasError("First name can't be blank")),
                () -> assertTrue(homePage.assertHasError("Last name can't be blank")),
                () -> assertTrue(homePage.assertHasError("Address1 can't be blank")),
                () -> assertTrue(homePage.assertHasError("City can't be blank")),
                () -> assertTrue(homePage.assertHasError("Zip code can't be blank"))
        );
    }





    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        driver = null;
    }
    */

}
