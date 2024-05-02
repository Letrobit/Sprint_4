import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import ru.yandex.practicum.WebDriverFactory;
import ru.yandex.practicum.pageObjectLanding.LandingPageSamokat;
import ru.yandex.practicum.pageObjectOrderCustomerInfo.CustomerInfoSamokatPage;
import ru.yandex.practicum.pageObjectOrderDeliveryInfo.OrderInfoSamokatPage;

import static org.junit.Assert.assertTrue;



@RunWith(Parameterized.class)
public class OrderTest {
    private final By locator;
    //private WebDriverFactory webDriverFactory= new WebDriverFactory();
    private WebDriver driver;
    //имя
    private final String nameData;
    //фамилия
    private final String surnameData;
    //адрес
    private final String adressData;
    //станция метро
    private final String metroData;
    //телефон
    private final String phoneData;
    //Дата заказа
    private final String arrivalTimeData;
    //Срок аренды
    private final String arendaTimeData;
    private LandingPageSamokat landingPageSamokat;
    private CustomerInfoSamokatPage customerInfoSamokat;
    private OrderInfoSamokatPage orderInfoSamokatPage;

    public OrderTest(String nameData, String surnameData, String adressData, String metroData, String phoneData, String arrivalTimeData, String arendaTimeData, By locator) {
        this.nameData = nameData;
        this.surnameData = surnameData;
        this.adressData = adressData;
        this.metroData = metroData;
        this.phoneData = phoneData;
        this.arrivalTimeData = arrivalTimeData;
        this.arendaTimeData = arendaTimeData;
        this.locator=locator;
    }
    @Parameterized.Parameters
    public static Object[][] getCustomerInfo() {
        return new Object[][]{
                {"Имя", "Фамилия", "Бухарестская 123", "Арбатская", "77777777777","01.02.2025", "сутки", LandingPageSamokat.OrderButtonHeader},
                {"Лев", "Тимченко", "ул. Пушкина дом Колотушкина", "Черкизовская", "66666666666","01.02.2026", "двое суток", LandingPageSamokat.OrderButtonMain}
        };
    }
    @Before
    public void setup(){
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();
        driver.get(LandingPageSamokat.landingPageURL);
        landingPageSamokat = new LandingPageSamokat(driver);
        customerInfoSamokat = new CustomerInfoSamokatPage(driver);
        orderInfoSamokatPage = new OrderInfoSamokatPage(driver);

    }
    @Test
    public void enterCustomerInfoTest(){
        landingPageSamokat.closeCookie();
        landingPageSamokat.clickOrderButton(locator);
        customerInfoSamokat.fillCustomerInfo(nameData,surnameData,adressData,metroData,phoneData);
        customerInfoSamokat.clickNextButton();
        orderInfoSamokatPage.enterRequiredData(arrivalTimeData,arendaTimeData);
        orderInfoSamokatPage.orderButtonClick();
        orderInfoSamokatPage.confirmOrderButtonClick();
        assertTrue("Заказ не сделан",orderInfoSamokatPage.getOrderConfirmation());
    }
    /* @Test
    public void enterCustomerInfoTestMainButton(){
        LandingPageSamokat landingPageSamokat = new LandingPageSamokat(driver);
        landingPageSamokat.closeCookie();
        landingPageSamokat.clickOrderButtonMain();
        CustomerInfoSamokatPage customerInfoSamokat = new CustomerInfoSamokatPage(driver);
        customerInfoSamokat.fillCustomerInfo(nameData,surnameData,adressData,metroData,phoneData);
        customerInfoSamokat.clickNextButton();
        OrderInfoSamokatPage orderInfoSamokatPage = new OrderInfoSamokatPage(driver);
        orderInfoSamokatPage.enterRequiredData(arrivalTimeData,arendaTimeData);
        orderInfoSamokatPage.orderButtonClick();
        orderInfoSamokatPage.confirmOrderButtonClick();
        assertTrue("Заказ не сделан",orderInfoSamokatPage.getOrderConfirmation());
    }*/
    @After
    public void tearDown() {
        driver.quit();
    }

}
