package ru.rsreu.xpath;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.rsreu.driver.Driver;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class ChromeDriverTests {

    private static final Driver driver = Driver.getInstance();

    @BeforeAll
    public static void setupSite() {
        String site = "https://admrzn.ru/gorod-ryazan/pochetnye-grazhdane";
        driver.get().navigate().to(site);
    }

    @Test
    public void navigationTest() {
        List<WebElement> menu = driver.get()
                .findElements(By.xpath("//*[@id=\"articles\"]/div/child::*"));
        List<String> links = menu.stream()
                .map(webElement -> webElement.getAttribute("href"))
                .collect(Collectors.toList());
        for (String link : links) {
            driver.get().navigate().to(link);
            driver.get().navigate().back();
            driver.get().navigate().forward();
        }
    }

    @Test
    public void waitWebDriverTest() {
        assertTrue(driver.getWaitChrome().until(titleIs("Почетные граждане - Администрация города Рязани")));
    }
}
