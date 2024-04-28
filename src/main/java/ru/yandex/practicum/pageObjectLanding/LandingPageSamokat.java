package ru.yandex.practicum.pageObjectLanding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import static java.time.Duration.ofSeconds;

public class LandingPageSamokat {

    private final WebDriver driver;
    private final String questionLocator = "accordion__heading-%s";
    private final String answerLocator = "//div[contains(@id, 'accordion__panel')][.='%s']";

    private  final By OrderButtonHeader = By.xpath(".//div[@class='Header_Nav__AGCXC']//button[@class='Button_Button__ra12g' and text()='Заказать']");
    private  final By OrderButtonMain = By.xpath("//div[@class='Home_FinishButton__1_cWm']/*");
    public static String landingPageURL = "https://qa-scooter.praktikum-services.ru/";

    private final By cookieCloseLocator = By.id("rcc-confirm-button");
    public LandingPageSamokat(WebDriver driver){
        this.driver = driver;
    }
    public void closeCookie(){
        driver.findElement(cookieCloseLocator).click();
    }
    public void expandQuestion(int index){
        WebElement element = driver.findElement(By.id(String.format(questionLocator, index)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
    public boolean answerIsDisplayed(String expectedAnswer){
        WebElement element = driver.findElement(By.xpath(String.format(answerLocator, expectedAnswer)));
        new WebDriverWait(driver, ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(answerLocator, expectedAnswer))));
        return element.isDisplayed();
    }
    public void clickOrderButtonHeader(){
        driver.findElement(OrderButtonHeader).click();
    }
    public void clickOrderButtonMain(){
        WebElement element = driver.findElement(OrderButtonMain);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }
}
