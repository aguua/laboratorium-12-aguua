
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageFactoryAddresses {

    private static WebDriver driver;
    private final Wait<WebDriver> wait;

    @FindBy(id ="sign-in")
    private WebElement signInMenu;
    @FindBy(name = "session[email]")
    private WebElement inputEmail;
    @FindBy(name = "session[password]")
    private WebElement inputPassword;
    @FindBy(name = "commit")
    private WebElement submitButton;
    @FindBy(xpath = "//a[@href='/addresses']")
    private WebElement adressesMenu;
    @FindBy(xpath = "//a[@data-test='create']")
    private WebElement addNew;
    @FindBy(xpath = "//div[@data-test='notice']")
    private WebElement notice;
    @FindBy(id= "error_explanation")
    private WebElement error;
    @FindAll(@FindBy(xpath = "//tbody/tr"))
    List<WebElement> addedAddresses;
    @FindAll(@FindBy(xpath = "//input[@type='text']"))
    List<WebElement> inputElements;
    //0 FirstName, 1 LasName, 2 Address1, 3 Address2, 4 City, 5 ZipCode, 6 Note


    public PageFactoryAddresses(WebDriver driver) {
        this.driver = driver;
        driver.get("http://a.testaddressbook.com/");
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }


    public void loginAt(String user, String password){
        waitUntilTitle("Address Book");
        signInMenu.click();
        waitUntilTitle("Address Book - Sign In");
        inputEmail.sendKeys(user);
        inputPassword.sendKeys(password);
        submitButton.click();
    }

    public void enterAddress(){
        adressesMenu.click();
        waitUntilPresent(By.xpath("//a[@data-test='create']"));
    }


    public void addNewAddress_onlyRequired(String firstName,
                                           String lastName,
                                           String address1,
                                           String zipCode,
                                           String city) {
        addNew.click();
        waitUntilPresent(By.name("commit"));
        inputElements.get(0).sendKeys(firstName);
        inputElements.get(1).sendKeys(lastName);
        inputElements.get(2).sendKeys(address1);
        inputElements.get(4).sendKeys(city);
        inputElements.get(5).sendKeys(zipCode);
        submitButton.click();
    }

    public void enterValueToField(int field, String value){
        inputElements.get(field).sendKeys(value);
    }

    public void clickNewAddress(){
        addNew.click();
        waitUntilPresent(By.name("commit"));
    }
    public void submit(){
        submitButton.click();
    }

    public int getSavedAddressesCount(){
        return addedAddresses.size();
    }


    public void waitUntilPresent(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private void waitUntilTitle(String title){
        wait.until(ExpectedConditions.titleContains(title));
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean assertLogged() throws Exception{
        Boolean result = isElementPresent(By.className("navbar-text"));
        return(result);
    }

    public boolean assertLoggingError() throws Exception{
        return notice.getText().contains("Bad email or password.");
    }


    public boolean assertErrorOccurred() throws Exception{
        System.out.println(error.getText());
        return error.getText().contains("prohibited this address from being saved");
    }

    public boolean assertHasError(String err) throws Exception{
        return error.getText().contains(err);
    }

    public boolean assertAddressAdded() throws Exception{
        System.out.println(notice.getText());
        return notice.getText().contains("Address was successfully created.");
    }




}
