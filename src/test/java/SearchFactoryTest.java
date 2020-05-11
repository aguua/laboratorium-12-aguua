
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchFactoryTest {
    private static WebDriver driver;
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void testSearchDuckDuck() throws Exception {
        String url = "https://duckduckgo.com/";
        SearchFactory page = new SearchFactory(driver, url);
        page.search("wikipedia");
        page.waitUntilTitle("at DuckDuckGo");
        assertTrue(page.assertTitleContains("wikipedia at DuckDuckGo"));
    }

    @Test
    public void testSearchGoogle() throws Exception {
        String url = "https://google.com/";
        SearchFactory page = new SearchFactory(driver, url);
        page.search("wikipedia");
        page.waitUntilTitle("Szukaj w Google");
        assertTrue(page.assertTitleContains("wikipedia - Szukaj w Google"));
    }

    @Test
    public void testSearchBing() throws Exception {
        String url = "https://www.bing.com";;
        SearchFactory page = new SearchFactory(driver, url);
        page.search("wikipedia");
        page.waitUntilTitle("wikipedia - Bing");
        assertTrue(page.assertTitleContains("wikipedia - Bing"));
    }

}
