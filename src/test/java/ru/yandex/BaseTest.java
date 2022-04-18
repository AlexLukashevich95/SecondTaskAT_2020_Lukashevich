package ru.yandex;

import custom.drivers.Manager;
import custom.drivers.Waits;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void before() {
        Manager.initChrome();
        Waits.initWait();
        chromeDriver = Manager.getCurrentDriver();
        chromeDriver.manage().window().maximize();
        Waits.waitImplicitLoadingPage(chromeDriver);

    }

    @AfterEach
    public void closeBellTest() {
        Manager.killCurrentDriver();
    }
}
