import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectDuckDuckGo {

    public WebDriver driver;
    private final Wait<WebDriver> wait;

    public PageObjectDuckDuckGo(WebDriver driver){
        this.driver = driver;
        driver.get("https://duckduckgo.com/");
        wait = new WebDriverWait(driver,10);
    }

    public void search() throws Exception{
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search_form_input_homepage")));
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("wikipiedia");
        driver.findElement(By.id("search_button_homepage")).submit();
        waitUntilTitle("wikipiedia at DuckDuckGo");
    }

    public void firstSearchResult() throws  Exception{
        search();
        waitUntilPresent("r1-0");
        driver.findElement(By.id("r1-0")).click();
    }

    private void waitUntilPresent(String element_id){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(element_id)));
    }
    private void waitUntilTitle(String title){
        wait.until(ExpectedConditions.titleContains(title));
    }

    public boolean assertTitleContains(String title) throws Exception{
        Boolean result = driver.getTitle().contains(title);
        System.out.println(driver.getTitle());
        return(result);
    }
}
