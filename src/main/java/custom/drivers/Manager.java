package custom.drivers;

import custom.properties.TestData;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Manager {
    /**
     *Поле веб-драйвер
     */
    private static WebDriver currentDriver;

    /**
     * Метод для возвращения веб-драйвера
     * @return веб-драйвер класса
     */
    public static WebDriver getCurrentDriver(){
        return currentDriver;
    }

    /**
     * Метод инициализации браузера
     */
    public static void initChrome(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(List.of("start-maximized"));
        try{
            currentDriver = new ChromeDriver(options);
        }catch (SessionNotCreatedException e){
            Assertions.fail("Данный драйвер не совместим с текущим браузером. Используйте другой драйвер");
        }
        setDriverDefaultSettings();
    }

    /**
     * Метод установки настройки драйвера по умолчанию
     */
    public static void setDriverDefaultSettings(){
        currentDriver.manage().timeouts().implicitlyWait(TestData.driverProps.defaultTimeout(), TimeUnit.SECONDS);
        currentDriver.manage().deleteAllCookies();
    }

    /**
     * Метод остановки работы драйвера
     */
    public static void killCurrentDriver(){
        if(currentDriver!=null){
            currentDriver.quit();
            currentDriver = null;
        }
    }
}
