import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import java.util.List;

public class LoadableComponentClass extends LoadableComponent<LoadableComponentClass> {

    private final WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement user;
    @FindBy(id = "password")
    private WebElement pass;
    @FindBy(className = "btn_action")
    private WebElement button;
    @FindBy(xpath = "//*[@id=\"login_button_container\"]/div/form/h3")
    private WebElement error;
    @FindBy(xpath = "//span[@class='fa-layers-counter shopping_cart_badge']")
    private WebElement cardCount;
    @FindAll(@FindBy(xpath = "//button[@class='btn_primary btn_inventory']"))
    private List<WebElement> addToCard;
    @FindAll(@FindBy(xpath = "//button[@class='btn_secondary btn_inventory']"))
    private List<WebElement> removeFromCard;


    public LoadableComponentClass (WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://www.saucedemo.com/index.html");
        PageFactory.initElements(driver, this);
    }

    @Override
    protected void load() {
        driver.get("https://www.saucedemo.com/index.html");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        if(!url.contains("saucedemo"))  throw new Error("Page error!");
    }

    public void logIn(String name, String password) {
        user.sendKeys(name);
        pass.sendKeys(password);
        button.click();
    }

    public String getError() {
        return error.getText();
    }

    public Object getAddress() {
        return driver.getCurrentUrl();
    }

    //i nie jest stale dla danego elementu- nie zawsze 0 = Sauce Labs Backpack
    public void addItemToCard(int i){
        //System.out.println("Possible to add "+ addToCard.size() + " items");
        addToCard.get(i).click();
    }

    public void removeItemFromCard(int i){
        //System.out.println("Possible to remove "+ removeFromCard.size() + " items");
        removeFromCard.get(i).click();
    }

    public int getCardItemAmount(){
        int count = 0;
        if (isElementPresent(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']"))) {
            count = Integer.parseInt(cardCount.getText());
        }
        return count;
    }

    public void removeAllFromCard(){
        while(removeFromCard.size() >0){
            removeItemFromCard(0);
        }
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

}