package custom.steps;
import custom.helpers.Screenshoter;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import pages.YandexMainPage;
import pages.YandexShopPage;

import java.util.List;
import java.util.Map;

public class Steps {
    @Step("Переход на страницу {nameService}")
    public static void goToServicePage(YandexMainPage yandexMainPage, String nameService){
        Screenshoter.getScreen(yandexMainPage.getDriver(),"goToServicePage");
        Assertions.assertTrue(yandexMainPage.getServicesItems().stream().anyMatch(x->x.getText().contains(nameService)));
        yandexMainPage.goToServicePage(nameService);
    }

    @Step("Переход к категории {nameCategory} и подкатегории {nameSubcategory}")
    public static void goToCategory(YandexShopPage yandexShopPage, String nameCategory, String nameSubcategory){
        yandexShopPage.goToCategory(nameCategory,nameSubcategory);
        Screenshoter.getScreen(yandexShopPage.getDriver(),"goToCategory");
    }

    @Step("Вставить цену от {lowerPrice} до {upperPrice}")
    public static void inputPrice(YandexShopPage yandexShopPage, String lowerPrice, String upperPrice){
        yandexShopPage.inputPrice(lowerPrice,upperPrice);
        Screenshoter.getScreen(yandexShopPage.getDriver(),"inputPrice");
    }

    @Step("Выбор бренда {nameBrand}")
    public static void chooseBrand(YandexShopPage yandexShopPage, String nameBrand){
        yandexShopPage.chooseBrand(nameBrand);
        Screenshoter.getScreen(yandexShopPage.getDriver(),"chooseBrand");
    }

    @Step("Проверка {productsQuantity} продуктов на странице")
    public static void checkmaxItemsPerPage(List<Map<String,String>> resultMaxItems,YandexShopPage yandexShopPage, int productsQuantity){
        Screenshoter.getScreenOfProducts(yandexShopPage.getDriver(),yandexShopPage.getProducts(),"checkmaxItemsPerPage");
        Assertions.assertEquals(resultMaxItems.size(),productsQuantity);
    }

    @Step("Проверка поиска по имени {whichProductToSearch} продукта продуктов на странице")
    public static void checkSearchProductsByName(List<Map<String,String>> resultSearchItems,int productToSearch,YandexShopPage yandexShopPage){
        String nameProduct = resultSearchItems.get(productToSearch-1).get("NAME");
        Screenshoter.getScreenOfSearchedProduct(yandexShopPage.getDriver(),yandexShopPage.getProducts(),nameProduct,"checkSearchProductsByName");
        Assertions.assertTrue(resultSearchItems.stream().anyMatch(x->x.get("NAME").equals(nameProduct)));
    }
}
