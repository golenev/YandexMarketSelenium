package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForKittenPage extends AbstractPage {

    public ForKittenPage(WebDriver webDriver, WebDriverWait webDriverWait, Actions actions) {
        super(webDriver, webDriverWait, actions);
    }

    @FindBy(id = "glpricefrom")
    public WebElement buttonMinPrice;
    @FindBy(id = "glpriceto")
    public WebElement buttonMaxPrice;
    @FindBy(xpath = "//span[text()='Доставка курьером']")
    public WebElement courierDelivery;
    @FindBy(xpath = "//span[@class=\"_1o8_r xUzR2\"][contains(text(),'Whiskas')]")
    public WebElement checkWhiskas;
    @FindBy(xpath = "(//div[@class='_1FODI'])[1]")
    public WebElement comparison1;
    @FindBy(xpath = "//span[@class=\"_1o8_r xUzR2\"][contains(text(),'Dreamies')]")
    public WebElement checkDreamies;
    @FindBy(xpath = "(//div[@class='_1FODI'])[2]")
    public WebElement comparison2;
    @FindBy(xpath = "//a[contains(text(),'Сравнить')]")
    public WebElement compareAll;

    public void scrollDown() {

        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0,500)");
    }

    public void scrollUp() {

        ((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0,-25)");
    }

    public void inputPrice(String number, String number2) {
        buttonMinPrice.click();
        buttonMinPrice.sendKeys(number);
        buttonMaxPrice.click();
        buttonMaxPrice.sendKeys(number2);
    }

    public void selectDelivery() {
        courierDelivery.click();
    }

    public void selectWhiskas() {
        checkWhiskas.click();
    }

    public void addToComparison1() {
        comparison1.click();
    }

    public void selectDreamies() {

        checkDreamies.click();
    }

    public void addToComparison2() {
        comparison2.click();
    }

    public void compareAllProd() {
        compareAll.click();
    }
}
