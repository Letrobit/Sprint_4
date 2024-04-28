package ru.yandex.practicum.pageObjectOrderDeliveryInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;


public class OrderInfoSamokatPage {
    private final WebDriver driver;
    //Поле ввода даты начала аренды
    private final By arrivalTimeLocator = By.xpath("//input[@placeholder='* Когда привезти самокат']");

    //Поле ввода срока аренды
    private final By arendaTimeLocator = By.xpath("//div[text()='* Срок аренды']");
    //Выбор срока
    private final String arendaTimeItemLocator = "//div[text()='%s']";
    //Кнопка Далее
    private final By orderButtonLocator = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[text()='Заказать']");
    //Кнопка Да
    private final By confirmOrderButtonLocator = By.xpath("//button[text()='Да']");
    //Всплывающее окно Заказ оформлен
    private final By orderConfirmationLocator = By.xpath("//div[text()='Заказ оформлен']");
    private final By aboutArendaTextLocator = By.xpath("//div[text()='Про аренду']");
    public OrderInfoSamokatPage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterRequiredData(String arrivalTimeInput,String arendaTimeInput){
        WebElement arrivalTime = driver.findElement(arrivalTimeLocator);
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(arrivalTime));
        arrivalTime.sendKeys(arrivalTimeInput);
        driver.findElement(aboutArendaTextLocator).click();
        WebElement arendaTime = driver.findElement(arendaTimeLocator);
        arendaTime.click();
        WebElement arendaTimeItem = driver.findElement(By.xpath(String.format(arendaTimeItemLocator, arendaTimeInput)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", arendaTimeItem);
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(arendaTimeItem));
        arendaTimeItem.click();
    }
    public void orderButtonClick(){
        WebElement orderButton = driver.findElement(orderButtonLocator);
        orderButton.click();
    }
    public void confirmOrderButtonClick(){
        WebElement confirmOrderButton = driver.findElement(confirmOrderButtonLocator);
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        confirmOrderButton.click();
    }
    public boolean getOrderConfirmation(){
        WebElement orderConfirmation = driver.findElement(orderConfirmationLocator);
        boolean orderIsMade = (orderConfirmation.isDisplayed());
        return orderIsMade;
    }
}
