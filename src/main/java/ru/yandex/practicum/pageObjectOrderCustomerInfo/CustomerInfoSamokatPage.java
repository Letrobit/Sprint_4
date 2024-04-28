package ru.yandex.practicum.pageObjectOrderCustomerInfo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class CustomerInfoSamokatPage {
    private final WebDriver driver;


    //имя
    private final By nameInputLocator = By.xpath("//input[@placeholder='* Имя']");

    //фамилия
    private final By surnameInputLocator = By.xpath("//input[@placeholder='* Фамилия']");
    //адрес
    private final By adressInputLocator = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //станция метро
    private final By metroInputLocator = By.xpath("//input[@placeholder='* Станция метро']");
    private final String stationMenuItemLocator = "//div[text()='%s']";
    //телефон
    private final By phoneInputLocator = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By nextButtonLocator = By.xpath("//button[text()='Далее']");

    public CustomerInfoSamokatPage(WebDriver driver) {
        this.driver = driver;
    }
    public void fillCustomerInfo(String name, String surname, String adress, String metro, String phone) {
        WebElement nameInput = driver.findElement(nameInputLocator);
        nameInput.sendKeys(name);

        WebElement surnameInput = driver.findElement(surnameInputLocator);
        surnameInput.sendKeys(surname);

        WebElement adressInput = driver.findElement(adressInputLocator);
        adressInput.sendKeys(adress);

        WebElement phoneInput = driver.findElement(phoneInputLocator);
        phoneInput.sendKeys(phone);

        WebElement metroInput = driver.findElement(metroInputLocator);
        metroInput.click();
        WebElement StationMenu = driver.findElement(By.xpath(String.format(stationMenuItemLocator, metro)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", StationMenu);
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(StationMenu));
        StationMenu.click();

    }
    public void clickNextButton(){
        WebElement nextButton = driver.findElement(nextButtonLocator);
        nextButton.click();
    }

}
