package TestPages;

import WebPages.PageFactoryDuckDuckGo;
import WebPages.PageObjectDuckDuckGo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageFactoryTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void testSearchDuck() throws Exception {
        PageFactoryDuckDuckGo duckPage = PageFactory.initElements(driver, PageFactoryDuckDuckGo.class);
        duckPage.search("wikipedia");
        assertTrue(duckPage.assertTitleContains("wikipedia at DuckDuckGo"));
    }


    @Test
    public void testSearchResult() throws Exception{
        PageFactoryDuckDuckGo duckPage = PageFactory.initElements(driver, PageFactoryDuckDuckGo.class);
        duckPage.firstSearchResult("wikipedia");
        assertTrue(duckPage.assertTitleContains("Wikipedia"));
    }

    @AfterAll
    public static void tearDownAfterClass() throws Exception {
        driver.quit();
    }
}
