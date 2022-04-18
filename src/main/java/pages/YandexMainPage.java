package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class YandexMainPage {
    /**
     *Поле веб-драйвер
     */
    private WebDriver chromeDriver;
    /**
     *Поле селектора сервисов
     */
    private String selectorServicesItems = "//li[contains(@class,'services-new__list-item')]/a";
    /**
     *Метод для установки значений полям
     * @param chromeDriver
     */
    public YandexMainPage(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
    }
    /**
     *Метод возвращения веб-драйвера
     * @return веб-драйвер класса
     */
    public WebDriver getDriver(){
        return chromeDriver;
    }
    /**
     *Инициализация списка сервисов
     */
    List<WebElement> servicesItems = new ArrayList<>();
    /**
     *Метод получения списка сервисов
     */
    public List<WebElement> getServicesItems(){
        servicesItems = chromeDriver.findElements(By.xpath(selectorServicesItems));
        return servicesItems;
    }
    /**
     *Метод перехода на страницу сервиса
     * @param nameService
     */
    public boolean goToServicePage(String nameService){
        WebElement service = servicesItems.stream().filter(x->x.getText().contains(nameService)).findFirst().get();
        service.click();
        List<String> tabs = new ArrayList<>(chromeDriver.getWindowHandles());
        for(String tab : tabs){
            chromeDriver.switchTo().window(tab);
            if(chromeDriver.getTitle().contains(nameService)){
                return true;
            }
        }
        return false;
    }



}
