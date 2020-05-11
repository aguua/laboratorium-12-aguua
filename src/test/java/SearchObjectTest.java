import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchObjectTest {

    private static WebDriver driver;
    private SearchObject page;
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void testSearchDuckDuck() throws Exception {
        String url = "https://duckduckgo.com/";
        page = new SearchObject(driver, url);
        page.search("wikipedia");
        page.waitUntilTitle("at DuckDuckGo");
        assertTrue(page.assertTitleContains("wikipedia at DuckDuckGo"));
    }
    @Test
    public void testSearchGoogle() throws Exception {
        String url = "https://google.com/";
        page = new SearchObject(driver, url);
        page.search("wikipedia");
        page.waitUntilTitle("Szukaj w Google");
        assertTrue(page.assertTitleContains("wikipedia - Szukaj w Google"));
    }

    @Test
    public void testSearchBing() throws Exception {
        String url = "https://www.bing.com";;
        page = new SearchObject(driver, url);
        page.search("wikipedia");
        page.waitUntilTitle("wikipedia - Bing");
        assertTrue(page.assertTitleContains("wikipedia - Bing"));
    }
}
