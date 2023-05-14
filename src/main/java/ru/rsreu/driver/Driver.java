package ru.rsreu.driver;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Driver {

    private static Driver instance;

    private final WebDriver driver;

    private Wait<WebDriver> waitDriver;

    public static Driver getInstance() {
        if (instance == null) {
            synchronized (Driver.class) {
                instance = new Driver();
            }
        }
        return instance;
    }

    public Driver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        waitDriver = new WebDriverWait(driver, Duration.ofMillis(10000))
                .ignoreAll(List.of(
                    StaleElementReferenceException.class,
                    NoSuchElementException.class,
                    MoveTargetOutOfBoundsException.class
            ));
        driver.manage().window().minimize();
    }

    public WebDriver get() {
        return driver;
    }

    public Wait<WebDriver> getWaitChrome() {
        return waitDriver;
    }
}
