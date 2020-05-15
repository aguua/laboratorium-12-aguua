import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestForLoginAndSearch {

    private static WebDriver driver;
    private String testLogin = "aga.test.selenium@gmail.com";
    private  GitHubPageFactory gitHub;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
    }

    @BeforeEach
    public void setUp() {
        gitHub = new GitHubPageFactory(driver);
    }
    @Test
    public void testEmptyLoginAndPassword() throws Exception {
        gitHub.logIn("","");
        assertTrue(gitHub.assertHasError("Incorrect username or password."));
    }
    @Test
    public void testEmptyPassword() throws Exception {
        gitHub.logIn("user","");
        assertTrue(gitHub.assertHasError("Incorrect username or password."));
    }
    @Test
    public void testEmptyLogin() throws Exception {
        gitHub.logIn("","pass");
        assertTrue(gitHub.assertHasError("Incorrect username or password."));
    }

    @Test
    public void testIncorrectLogin() throws Exception {
        gitHub.logIn("not_existing@gmail.com","pass");
        assertTrue(gitHub.assertHasError("Incorrect username or password."));
    }

    @Test
    public void testCorrectLoginAndPassword() throws Exception {
        gitHub.logIn(testLogin, "selenium_passwd");
        gitHub.waitUntilTitle("GitHub");
        assertTrue(gitHub.assertTitleContains("GitHub"));
    }

    @Test
    public void testForgetPasswordPage(){
        gitHub.forgetPassword();
        assertEquals("Forgot your password? · GitHub", driver.getTitle());
    }

    @Test
    public void testSearchForLoggedUser() throws Exception {
        gitHub.logIn(testLogin, "selenium_passwd");
        gitHub.waitUntilTitle("GitHub");
        gitHub.search("java selenium");
        gitHub.waitUntilTitle("Search");
        assertTrue(gitHub.assertTitleContains("Search · java selenium"));
    }

    @Test
    public void testSearchWithoutLoggin() throws Exception {
        gitHub.search("java selenium");
        gitHub.waitUntilTitle("Search");
        assertTrue(gitHub.assertTitleContains("Search · java selenium"));
    }


    @AfterEach
    public void tearDown(){
        gitHub.tryLogOut();
    }


}
