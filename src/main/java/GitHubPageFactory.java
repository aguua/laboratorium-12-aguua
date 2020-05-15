import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GitHubPageFactory extends LoadableComponent<GitHubPageFactory> {
    public WebDriver driver;
    private final Wait<WebDriver> wait;

    private WebElement q;
    private WebElement login_field;
    private WebElement password;
    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;
    @FindBy(xpath = "//div[@class='container-lg px-2']")
    private WebElement error;
    @FindBy(xpath = "//a[@href='/password_reset']")
    private WebElement passwdReset;
    @FindBy(xpath = "//summary[@data-ga-click='Header, show menu, icon:avatar']")
    private WebElement userMenu;
    @FindBy(xpath = "//button[@data-ga-click='Header, sign out, icon:logout']")
    private WebElement singOutButton;



    public GitHubPageFactory (WebDriver driver) {
        this.driver = driver;
        driver.get("https://github.com");
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }
    @Override
    protected void load() {
        driver.get("https://github.com");
    }
    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        if(!url.contains("github"))  throw new Error("Page error!");
    }

    // actions
    public void search(String ask){
        q.sendKeys(ask, Keys.ENTER );
    }

    public void goToSingInPage(){
        driver.get("https://github.com/login");
        waitUntilTitle("Sign in");
    }
    public void logIn(String user, String passwd){
        goToSingInPage();
        login_field.sendKeys(user);
        password.sendKeys(passwd);
        submitButton.click();
    }

    public void tryLogOut(){
        if (isElementPresent(By.xpath("//summary[@data-ga-click='Header, show menu, icon:avatar']"))){
            userMenu.click();
            waitUntilPresent(By.xpath("//button[@data-ga-click='Header, sign out, icon:logout']"));
            singOutButton.submit();
            waitUntilTitle("The world’s leading software development platform · GitHub");
        }
    }

    public void forgetPassword(){
        goToSingInPage();
        passwdReset.click();
    }



    // utils
    public void waitUntilTitle(String title){
        wait.until(ExpectedConditions.titleContains(title));
    }

    public void waitUntilPresent(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    // assertions
    public boolean assertTitleContains(String title) throws Exception{
        Boolean result = driver.getTitle().contains(title);
        System.out.println(driver.getTitle());
        return(result);
    }

    public boolean assertHasError(String err) throws Exception{
        return error.getText().contains(err);
    }



}
