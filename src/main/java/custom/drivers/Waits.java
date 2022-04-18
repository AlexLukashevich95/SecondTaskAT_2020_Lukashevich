package custom.drivers;

import custom.properties.TestData;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Waits {
    private static int timeout = TestData.driverProps.defaultTimeout();
    public static WebDriverWait wait;

    public static void initWait(){
        wait = new WebDriverWait(Manager.getCurrentDriver(),timeout);
    }

    public static void waitImplicitLoadingPage(WebDriver chromeDriver){
        chromeDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
    }

    public static void waitUntilElementPresents(String xpath){
        Waits.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    public static void waitUntilGreyWindowInvisible(){
        new WebDriverWait(Manager.getCurrentDriver(),timeout).until(ExpectedConditions.invisibilityOf(Manager.getCurrentDriver().findElement(By.xpath("//div[@class='_2Lvbi _1oZmP']"))));
    }
}
