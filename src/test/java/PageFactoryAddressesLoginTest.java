import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.*;

public class PageFactoryAddressesLoginTest {

    private static WebDriver driver;
    private String testLogin = "aga.test.selenium@gmail.com";
    private String testPass = "selenium_passwd";
    PageFactoryAddresses homePage;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
    }
    @BeforeEach
    public void setUp(){
        homePage = new PageFactoryAddresses(driver);
    }


    @Test
    public void testLogin() throws Exception {
        homePage.loginAt(testLogin, testPass);
        homePage.waitUntilPresent(By.className("navbar-text"));  // login zalogowanego użytkownika po prawej stronie
        assertTrue(homePage.assertLogged());
    }

    @Test
    public void testLoginOut() throws Exception {
        homePage.loginAt(testLogin, testPass);
        homePage.waitUntilPresent(By.className("navbar-text"));  // login zalogowanego użytkownika po prawej stronie
        homePage.logOut();
        assertFalse(homePage.assertLogged());
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

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        driver = null;
    }

    @AfterEach
    public void tryLogout(){
        if (homePage.isElementPresent(By.className("navbar-text"))) //kiedy użytkownik jest zalogowany
        {
            homePage.logOut();
            homePage.waitUntilPresent(By.id("sign-in"));
            homePage.backHome();
        }
    }


}
