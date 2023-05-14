package ru.rsreu.xpath;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.rsreu.driver.Driver;

import java.util.List;

public class XPathTests {

    private static final Driver driver = Driver.getInstance();

    private final String NAME_LAST_CITIZEN = "Буланов Владимир Матвеевич";

    @BeforeAll
    public static void setupSite() {
        String site = "https://admrzn.ru/gorod-ryazan/pochetnye-grazhdane";
        driver.get().navigate().to(site);
    }


    @Test
    public void numberHonoraryCitizensByClassTest() {
        List<WebElement> elementList = driver.get()
                .findElement(By.className("records"))
                .findElements(By.className("item"));
        Assertions.assertEquals(10, elementList.size());
    }

    @Test
    public void numberHonoraryCitizensByTagTest() {
        List<WebElement> elementList = driver.get()
                .findElement(By.className("records"))
                .findElements(By.tagName("a"));
        Assertions.assertEquals(10, elementList.size());
    }

    @Test
    public void numberHonoraryCitizensByLinkTextTest() {
        List<WebElement> elementList = driver.get()
                .findElements(By.cssSelector("#articles > div > a"));
        Assertions.assertEquals(10, elementList.size());
    }

    @Test
    public void numberHonoraryCitizensTest() {
        List<WebElement> elementList = driver.get().findElements(By.xpath("//*[@id=\"articles\"]/div/child::*"));
        Assertions.assertEquals(10, elementList.size());
    }

    @Test
    public void lastHonoraryCitizenTest() {
        WebElement lastCitizen = driver.get().findElement(By.xpath("//*[@id=\"articles\"]/div/a[last()]"));
        Assertions.assertEquals(NAME_LAST_CITIZEN, lastCitizen.getAttribute("title"));
    }

    @Test
    public void numberMenuTest() {
        List<WebElement> elementList = driver.get()
                .findElements(By.xpath("//*[@id=\"left\"]/div[1]/div/a[1]/following-sibling::*"));
        Assertions.assertEquals(9-1, elementList.size());
    }

    @Test
    public void lastMenuTest() {
        WebElement lastMenu = driver.get().findElement(By.xpath("//*[@id=\"left\"]/div[1]/div/a[last()]/."));
        String expected = "Карта Рязани";
        Assertions.assertEquals(expected, lastMenu.getText());
    }

    @Test
    public void numberHonoraryCitizensOnPartLinkTest() {
        List<WebElement> elementList = driver.get()
                .findElements(By.xpath(
                        "//*[@id=\"articles\"]/div/child::*[contains(@href,'pochetnye-grazhdane')]"
                ));
        Assertions.assertEquals(10, elementList.size());
    }

    @Test
    public void positionHonoraryCitizensOnNameTest() {
        WebElement citizenOnPosition = driver.get()
                .findElement(By.xpath(
                        "//*[@id=\"articles\"]/div/child::*[position() = 10]"
                ));
        Assertions.assertEquals(NAME_LAST_CITIZEN, citizenOnPosition.getAttribute("title"));
    }

    @Test
    public void sum() {
        String xpathQuery = "count(//*[@id=\"articles\"]/div/child::*) + count(\"//*[@id=\"left\"]/div[1]/div/child::*\")";
    }

    @Test
    public void getDescriptionForCitizen() {
        WebElement citizenOnPosition = driver.get()
                .findElement(By.xpath(
                        "//*[@id=\"articles\"]/div/child::*[position() = 10]/descendant::*[@class=\"short\"]"
                ));
        System.out.println(citizenOnPosition.getText());
        Assertions.assertNotNull(citizenOnPosition);
    }
}
