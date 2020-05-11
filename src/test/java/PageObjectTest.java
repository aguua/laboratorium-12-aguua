
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageObjectTest {

    private static WebDriver driver;
    private PageObjectDuckDuckGo duckPage;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void testSearchDuck() throws Exception {
        duckPage = new PageObjectDuckDuckGo(driver);
        duckPage.search();
        assertTrue(duckPage.assertTitleContains("wikipiedia at DuckDuckGo"));
    }

    @Test
    public void testSearchResult() throws Exception{
        duckPage = new PageObjectDuckDuckGo(driver);
        duckPage.firstSearchResult();
        assertTrue(duckPage.assertTitleContains("Wikipedia"));
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        driver.quit();
    }

}
