import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchObject {


    public WebDriver driver;
    private final Wait<WebDriver> wait;

    public  SearchObject (WebDriver driver, String url){
        this.driver = driver;
        driver.get(url);
        wait = new WebDriverWait(driver,10);
    }

    public void search(String ask){
        driver.findElement(By.name("q")).sendKeys(ask, Keys.ENTER);
    }

    public void waitUntilTitle(String title){
        wait.until(ExpectedConditions.titleContains(title));
    }

    public boolean assertTitleContains(String title) throws Exception{
        Boolean result = driver.getTitle().contains(title);
        System.out.println(driver.getTitle());
        return(result);
    }
}
