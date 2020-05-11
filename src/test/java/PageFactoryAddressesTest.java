import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.*;

public class PageFactoryAddressesTest {

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
    public void setUpLogin(){
        homePage = new PageFactoryAddresses(driver);
        homePage.loginAt(testLogin, testPass);
    }


    @Test
    public void testAddSuccess() throws Exception {
        homePage.enterAddress();
        homePage.addNewAddress_onlyRequired("Ala", "Nowak", "Mezotanska 11", "87-009", "Zgierz");
        homePage.waitUntilPresent(By.xpath("//div[@data-test='notice']"));
        assertTrue(homePage.assertAddressAdded());
    }

    @Test
    public void testAddTwiceSuccess() throws Exception {
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

    @AfterEach
    public void tryLogout(){
        if (homePage.isElementPresent(By.className("navbar-text"))) //kiedy użytkownik jest zalogowany
        {
            homePage.logOut();
            homePage.waitUntilPresent(By.id("sign-in"));
        }
        homePage.backHome();
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        driver = null;
    }


}
