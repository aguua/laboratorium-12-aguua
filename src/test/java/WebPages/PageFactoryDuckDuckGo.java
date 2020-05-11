package WebPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageFactoryDuckDuckGo {

    public WebDriver driver;
    private WebElement q;
    @FindBy(id = "r1-0")
    private WebElement r1;
    private final Wait<WebDriver> wait;

    public PageFactoryDuckDuckGo(WebDriver driver){
        this.driver = driver;
        driver.get("https://duckduckgo.com/");
        wait = new WebDriverWait(driver,10);
    }


    public void search(String text) throws Exception{
        q.sendKeys(text);   // to samo co: driver.findElement(By.id("q")).sendKeys(text);
        q.submit();
        waitUntilTitle(text + " at DuckDuckGo");
    }

    public void firstSearchResult(String text) throws  Exception{
        search(text);
        waitUntilPresent("r1-0");
        r1.click();
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
