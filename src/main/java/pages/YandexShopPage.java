package pages;

import custom.drivers.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YandexShopPage {
    /**
     *Поле веб-драйвер
     */
    private WebDriver chromeDriver;
    /**
     * Поле селектора кнопки категорий
     * Поле селектора категорий
     * Поле селектора подкатегорий
     */
    private String selectorCategoriesButton = "//button[@id='catalogPopupButton']";
    private String selectorCategories = "//li[@data-zone-name='category-link']";
    private String selectorSubcategories = "//ul[@data-autotest-id='subItems']/li";
    /**
     * Поле селектора цены от
     * Поле селектора цены до
     */
    private String selectorLowerPrice = "//fieldset[contains(legend,'Цена')]//*[label[contains(.,'от')] and input]/input";
    private String selectorUpperPrice = "//fieldset[contains(legend,'Цена')]//*[label[contains(.,'до')] and input]/input";
    /**
     * Поле селектора брендов
     */
    private String selectorBrands = "//fieldset[contains(legend,'Производитель')]//li/div";
    /**
     * Поле селектора кнопки показывать по
     */
    private String selectorShowItemsButton = "//button[contains(.,'Показывать по') and @aria-expanded]";
    /**
     * Поле селектора продуктов
     * Поле селектора названия продукта
     * Поле селектора описания продукта
     * Поле селектора цены продукта
     */
    private String selectorProducts = "//div[contains(@data-zone-name,'snippetList')]/article";
    private String selectorNameProduct = ".//h3";
    private String selectorDescriptionProduct = ".//h3//parent::div//parent::div//ul";
    private String selectorPriceProduct = ".//div[@data-zone-name='price']//a[1]";
    /**
     * Поле селектора поиска
     * Поле селектора кнопки поиска
     */
    private String selectorSearchProduct = "//input[contains(@placeholder,'Искать товар')]";
    private String selectorSearchButton = "//button[contains(span,'Найти')]";
    /**
     *Метод для установки значений полям
     * @param chromeDriver
     */
    public YandexShopPage(WebDriver chromeDriver){
        this.chromeDriver=chromeDriver;
    }
    /**
     * Инициализация списка категорий
     * Инициализация списка подкатегорий
     * Инициализация списка брендов
     * Инициализация списка продуктов
     * Инициализация списка продуктов в виде мапы
     */
    List<WebElement> categories = new ArrayList<>();
    List<WebElement> subcategories = new ArrayList<>();
    List<WebElement> brands = new ArrayList<>();
    List<WebElement> products = new ArrayList<>();
    List<Map<String,String>> collectProducts = new ArrayList<>();
    /**
     * Метод возвращения веб-драйвера
     * @return веб-драйвер класса
     */
    public WebDriver getDriver(){
        return chromeDriver;
    }

    /**
     * Метод для возвращения веб-элемента продуктов
     * @return веб-элемента продуктов класса
     */
    public List<WebElement> getProducts(){
        return products;
    }

    /**
     * Метод получения списка продуктов
     */
    private void getCollectProducts(){
        collectProducts.clear();
        products = chromeDriver.findElements(By.xpath(selectorProducts));
        for(WebElement product : products){
            collectProducts.add(Map.of(
                "WEB_ELEMENT",product.getText(),
                    "NAME",product.findElement(By.xpath(selectorNameProduct)).getText(),
                    "DESCRIPTION",product.findElement(By.xpath(selectorDescriptionProduct)).getText(),
                    "PRICE",product.findElement(By.xpath(selectorPriceProduct)).getText()
            ));
        }
    }

    /**
     * Метод перехода на страницу подкатегории
     * @param nameCategory
     * @param nameSubcategory
     */
    public void goToCategory(String nameCategory,String nameSubcategory){
        Actions action = new Actions(chromeDriver);
        chromeDriver.findElement(By.xpath(selectorCategoriesButton)).click();
        categories = chromeDriver.findElements(By.xpath(selectorCategories));
        action.moveToElement(categories.stream().filter(x->x.getText().equals(nameCategory)).findFirst().get()).perform();
        subcategories = chromeDriver.findElements(By.xpath(selectorSubcategories));
        subcategories.stream().filter(x->x.getText().equals(nameSubcategory)).findFirst().get().click();
    }

    /**
     * Метод фильтрации товара по цене
     * @param lowerPrice
     * @param upperPrice
     */
    public void inputPrice(String lowerPrice,String upperPrice){
        chromeDriver.findElement(By.xpath(selectorLowerPrice)).click();
        chromeDriver.findElement(By.xpath(selectorLowerPrice)).sendKeys(lowerPrice);
        chromeDriver.findElement(By.xpath(selectorUpperPrice)).click();
        chromeDriver.findElement(By.xpath(selectorUpperPrice)).sendKeys(upperPrice);
    }

    /**
     * Метод фильтрации товара по бренду
     * @param nameBrand
     */
    public void chooseBrand(String nameBrand){
        brands = chromeDriver.findElements(By.xpath(selectorBrands));
        brands.stream().filter(x->x.getText().equals(nameBrand)).findFirst().get().click();
        Waits.waitUntilGreyWindowInvisible();
    }

    /**
     * Метод изменения максимального числа продуктов на странице
     * @param maxItems
     * @return список продуктов
     */
    public List<Map<String,String>> getMaxItemsPerPage(String maxItems){
        chromeDriver.findElement(By.xpath(selectorShowItemsButton)).click();
        chromeDriver.findElement(By.xpath("//button[contains(.,'Показывать по "+maxItems+"') and @data-tid]")).click();
        Waits.waitUntilGreyWindowInvisible();
        getCollectProducts();
        return collectProducts;
    }

    /**
     * Метод поиска товара по названию
     * @param productToSearch
     * @return список продуктов
     */
    public List<Map<String,String>> searchProductsByName(int productToSearch){
        String nameProduct = collectProducts.get(productToSearch-1).get("NAME");
        chromeDriver.findElement(By.xpath(selectorSearchProduct)).click();
        chromeDriver.findElement(By.xpath(selectorSearchProduct)).sendKeys(nameProduct);
        chromeDriver.findElement(By.xpath(selectorSearchButton)).click();
        Waits.waitUntilElementPresents(selectorProducts);
        getCollectProducts();
        return collectProducts;
    }

}
