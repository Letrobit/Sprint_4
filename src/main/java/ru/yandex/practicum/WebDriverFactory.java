/*package ru.yandex.practicum; Эта страница не нужна, не работает на моем компьютере :(

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {


    public static WebDriver getWebDriver(String browserType) {
        if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setBinary("C:\\Program Files (x86)\\WebDriver\\bin\\firefox.exe");
            return new FirefoxDriver(options);

        }
        else if (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }
}
*/