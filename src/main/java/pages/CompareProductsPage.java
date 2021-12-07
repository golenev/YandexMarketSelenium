package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompareProductsPage extends AbstractPage {
    public CompareProductsPage(WebDriver webDriver, WebDriverWait webDriverWait, Actions actions) {
        super(webDriver, webDriverWait, actions);
    }

    @FindBy(xpath = "//a[contains(text(),'Лакомство для кошек Dreamies')]")
    public WebElement firstElementToCompare;
    @FindBy(xpath = "//a[contains(text(),'Корм для кошек (желе) Whiskas Аппетитный Микс Говя')]")
    public WebElement secondElementToCompare;
    @FindBy(css = "span[data-autotest-value='25']")
    public WebElement firstPrice;
    @FindBy(css = "span[data-autotest-value='25']")
    public WebElement secondPrice;
    @FindBy(xpath = "(//div[@aria-label='Удалить'])[1]")
    public WebElement deleteFirstProd;
    @FindBy(xpath = "(//button[contains(text(),'Удалить список')])[1]")
    public WebElement deleteAllList;
    @FindBy(xpath = "//h2[contains(text(),'Сравнивать пока нечего')]")
    public WebElement nothingToCompare;

    public void checkNamesProd() {
        firstElementToCompare.getText();
    }

    public int checkSumPrices() {
        int a = 0;
        int b = 0;
        a = Integer.parseInt(firstPrice.getText().replaceAll("[^0-9]", ""));
        b = Integer.parseInt(secondPrice.getText().replaceAll("[^0-9]", ""));
        int sum = a + b;
        return sum;
    }

    public void delProd() {
        actions.moveToElement(deleteFirstProd).perform();
        deleteFirstProd.click();
    }

    public void delAllList() {
        deleteAllList.click();
    }
}



