
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginOnFactory {

    //working on onet.pl, github.pl

    public WebDriver driver;
    private final Wait<WebDriver> wait;
    @FindBy(name = "login")
    private WebElement inputEmail;
    @FindBy(name = "password")
    private WebElement inputPassword;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    public LoginOnFactory(WebDriver driver, String url) {
        this.driver = driver;
        driver.get(url);
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void logIn(String user, String passwd){
        inputEmail.sendKeys(user);
        inputPassword.sendKeys(passwd);
        submitButton.click();
    }

    public boolean assertTitleContains(String title) throws Exception{
        Boolean result = driver.getTitle().contains(title);
        System.out.println(driver.getTitle());
        return(result);
    }

    public boolean isElementPresent(WebDriver d, By by) {
        try {
            d.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


}
