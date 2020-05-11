import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoadableComponentTest {
    private static WebDriver driver;
    private LoadableComponentClass page;
    private String password = "secret_sauce";

    @BeforeAll
    public static void setUpBeforeClass() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
    }

    @BeforeEach
    public void setup() {
        page = new LoadableComponentClass(driver).get(); //go to page, check errors, returns page
    }

    @Test
    public void testLoadedGet() {
        LoadableComponentClass test = page.get();
        assertEquals(test, page);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "standard_user",
            "problem_user",
            "performance_glitch_user",
    })
    public void testSuccessfulLogin(String user) {
        page.logIn(user, password);
        assertEquals("https://www.saucedemo.com/inventory.html", page.getAddress());
    }

    @Test
    public void testTryLoginLockedUser() {
        page.logIn("locked_out_user", password);
        assertEquals("Epic sadface: Sorry, this user has been locked out.", page.getError());
    }

    @Test
    public void testLoginFailNoPassword() {
        page.logIn("fake_user", "");
        assertEquals("Epic sadface: Password is required", page.getError());
    }

    @Test
    public void testLoginFailNoUsername() {
        page.logIn("", password);
        assertEquals("Epic sadface: Username is required", page.getError());
    }

    @Test
    public void testLoginFailBlankFields() {
        page.logIn("", "");
        assertEquals("Epic sadface: Username is required", page.getError());
    }

    @Test
    public void testEmptyCard(){
        page.logIn("standard_user", password);
        assertEquals(0, page.getCardItemAmount());
    }

    @Test
    public void testAddItemToCard(){
        page.logIn("standard_user", password);
        page.addItemToCard(0);
        assertEquals(1, page.getCardItemAmount());
    }

    @Test
    public void testAddManyItemsToCard(){
        page.logIn("standard_user", password);
        page.addItemToCard(0);
        page.addItemToCard(2);
        page.addItemToCard(3);
        assertEquals(3, page.getCardItemAmount());
    }

    @Test
    public void testRemoveItemFromCard(){
        page.logIn("standard_user", password);
        page.addItemToCard(0);
        page.removeItemFromCard(0);
        assertEquals(0, page.getCardItemAmount());
    }

    @Test
    public void testAddAndRemoveItems(){
        page.logIn("standard_user", password);
        page.addItemToCard(0);
        page.addItemToCard(3);
        page.addItemToCard(1);
        page.removeItemFromCard(1);
        page.removeItemFromCard(0);
        page.addItemToCard(4);
        assertEquals(2, page.getCardItemAmount());
    }

    @AfterEach
    public void clearCard(){
        page.removeAllFromCard();
    }

}
