import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CompareProductsPage;
import pages.ForKittenPage;
import pages.MainPage;
import java.util.concurrent.TimeUnit;

public class YandexMarketTest {

    WebDriver webDriver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    public static void startBrowser() {
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    void setupBrowser() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriverWait = new WebDriverWait(webDriver, 10);
        actions = new Actions(webDriver);
        webDriver.get("chrome://settings/clearBrowserData"); //очищаем кэш
        webDriver.manage().deleteAllCookies();
    }
    @Order(1)
    @Test
    @DisplayName("Открываем главную страницу")
    public void testOpenMainPage()  {
        webDriver.get("https://yandex.ru/");
        Cookie cookie = new Cookie("Session_id", "3:1638711482.5.0.1638711482720:bt1RXQ:22.1|793436904.0.2|3:244675.903429.vA8QiJZpY6KpxiIkFLpzFcIgoSU");
        webDriver.manage().addCookie(cookie);
        webDriver.navigate().refresh();
        webDriver.navigate().refresh();
        webDriver.get("chrome://settings/clearBrowserData");
        webDriver.manage().deleteAllCookies();
        webDriver.get("https://market.yandex.ru");
        webDriver.manage().addCookie(cookie);
        webDriver.navigate().refresh();
    }
    @Order(2)
    @Test
    @DisplayName("Переходим в каталог")
    public void loginTest() throws InterruptedException {
        MainPage mainPage = new MainPage(webDriver, webDriverWait, actions);
        webDriver.get("chrome://settings/clearBrowserData");
        webDriver.navigate().refresh();
        Cookie cookie = new Cookie("Session_id", "3:1638711482.5.0.1638711482720:bt1RXQ:22.1|793436904.0.2|3:244675.903429.vA8QiJZpY6KpxiIkFLpzFcIgoSU");
        webDriver.get("https://market.yandex.ru?");
        webDriver.manage().addCookie(cookie);
        TimeUnit.SECONDS.sleep(15);
        mainPage.clickAtCatalog();
        TimeUnit.SECONDS.sleep(2);
        mainPage.aimAtZooProd();
        TimeUnit.SECONDS.sleep(7);
        mainPage.zooChapter();
        TimeUnit.SECONDS.sleep(2);
    }

    @Test
    @DisplayName("Лакомства для кошек")
    public void goodiesForCat() throws InterruptedException {
        ForKittenPage forKitten = new ForKittenPage(webDriver, webDriverWait, actions);
        webDriver.navigate().refresh();
        Cookie cookie = new Cookie("Session_id", "3:1638711482.5.0.1638711482720:bt1RXQ:22.1|793436904.0.2|3:244675.903429.vA8QiJZpY6KpxiIkFLpzFcIgoSU");
        webDriver.get("https://market.yandex.ru/catalog--lakomstva-dlia-koshek/62819/list?hid=15963668&local-offers-first=0");
        webDriver.manage().addCookie(cookie); //пытаемся залогиниться через куки
        webDriver.navigate().refresh();
        TimeUnit.SECONDS.sleep(16); //чтобы успеть ввести капчу вручную
        forKitten.scrollDown(); //идём вниз по странице
        TimeUnit.SECONDS.sleep(2);
        forKitten.inputPrice("50", "150"); //вводим цены в фильтр
        TimeUnit.SECONDS.sleep(2);
        forKitten.scrollDown(); //ещё чуть ниже
        forKitten.selectDelivery(); //чек-бокс С доставкой
        TimeUnit.SECONDS.sleep(2);
        forKitten.scrollUp(); //поднимаемся чуть выше
        forKitten.selectWhiskas(); //выбираем Вискас
        TimeUnit.SECONDS.sleep(2);
        forKitten.addToComparison1(); //добавляем в сравнение первый товар
        TimeUnit.SECONDS.sleep(2);
        forKitten.selectWhiskas();
        TimeUnit.SECONDS.sleep(2);
        forKitten.selectDreamies();
        TimeUnit.SECONDS.sleep(2);
        forKitten.addToComparison2(); //добавляем в сравнение второй товар
        TimeUnit.SECONDS.sleep(2);
        forKitten.compareAllProd(); //переходим в раздел сравнение товаров
        TimeUnit.SECONDS.sleep(2);
        CompareProductsPage compareProductsPage = new CompareProductsPage(webDriver, webDriverWait, actions);
        compareProductsPage.checkNamesProd();
        //проверяем соответствие названий товаров в списке сравнения тем, что мы добавили
        Assertions.assertEquals("Лакомство для кошек Dreamies Подушечки с говядиной", compareProductsPage.firstElementToCompare.getText());
        Assertions.assertEquals("Корм для кошек (желе) Whiskas Аппетитный Микс Говядина/Язык/Овощи 75 г", compareProductsPage.secondElementToCompare.getText());
        Assertions.assertTrue(compareProductsPage.checkSumPrices() < 300); //Проверяем, что сумма меньше 300
        compareProductsPage.checkSumPrices(); //Проверяем общую стоимость товаров в спивка сравнения
        actions.moveToElement(compareProductsPage.firstElementToCompare);
        compareProductsPage.delProd(); //удаляем один товар из списка Сравнения
        TimeUnit.SECONDS.sleep(2);
        Assertions.assertTrue(compareProductsPage.secondElementToCompare.isDisplayed()); //Проверяем, что из списка сравнения товаров удалён один товар
        compareProductsPage.delAllList();
        TimeUnit.SECONDS.sleep(2); //Очищаем Список сравнения товаров
        compareProductsPage.nothingToCompare.isDisplayed();  //Проряем, что список Сравнение товаров очищен
    }
    @Attachment(value = "Attachment Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @AfterEach
    public void onTestFailure() {
        makeScreenshot();
    }

    @AfterEach
    public void tearsFall() {
        webDriver.quit();
    }
}
