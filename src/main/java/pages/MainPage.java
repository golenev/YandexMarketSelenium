package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {

    public MainPage(WebDriver webDriver, WebDriverWait webDriverWait, Actions actions) {
        super(webDriver, webDriverWait, actions);
    }

    @FindBy(xpath = "(//*[@data-tid-prop='89fe4462'])[1]")
    public WebElement catalogButton;
    @FindBy(css = "//div[contains(text(),'Маркет')]")
    public WebElement transitionToMarket;
    @FindBy(xpath = "//span[contains(text(),'Зоотовары')]")
    public WebElement zooProducts;
    @FindBy(xpath = "(//a[@class='egKyN _1mqvV _3kgUl'][contains(text(),'Лакомства')])[1]")
    public WebElement goodies;



    public void clickAtCatalog()  {
        catalogButton.click();

    }
    public void aimAtZooProd() {
        actions.moveToElement(zooProducts).perform();
    }
    public void zooChapter(){
        goodies.click();
    }



}
