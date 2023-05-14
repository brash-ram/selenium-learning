package ru.rsreu.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.rsreu.driver.Driver;
import ru.rsreu.dto.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeleniumService {

    private final String SITE_RSREU_VPM = "http://www.rsreu.ru/faculties/fvt/kafedri/vpm/staff";

    private Driver driver;

    public SeleniumService() {
        this.driver = Driver.getInstance();
    }

    public void run() {
        driver.get().navigate().to(SITE_RSREU_VPM);
        List<Employee> employes = parseEmployeeVpm();
        for (Employee item : employes) {
            System.out.println("FIO - " + item.getName());
//            System.out.println("Position - " + item.getPosition());
        }
        driver.get().quit();
    }

    public List<Employee> parseEmployeeVpm() {
        List<Employee> result = new ArrayList<>();
        WebElement table = driver.get().findElement(By.xpath("//*[@id=\"content_padding\"]/div[2]/div/div/table"));
        for (WebElement item : table.findElements(By.tagName("tr"))) {
            try {
//                List<String> test = item.findElements(By.tagName("br")).stream().map(WebElement::getText).collect(Collectors.toList());
                result.add(new Employee(
                        item.findElement(By.tagName("strong")).getText()
                ));
            } catch (Exception ignored) {
            }

        }
        return result;
    }
}
