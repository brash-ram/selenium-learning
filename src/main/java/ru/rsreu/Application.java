package ru.rsreu;

import ru.rsreu.service.SeleniumService;

public class Application {
    public static void main(String[] args) {
        SeleniumService seleniumService = new SeleniumService();
        seleniumService.run();
    }
}
