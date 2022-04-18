package ru.yandex;

import custom.properties.TestData;
import custom.steps.Steps;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.YandexMainPage;
import pages.YandexShopPage;

import java.util.List;
import java.util.Map;


public class Tests extends BaseTest {


    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска c помощью PF")
    @Test
    public void testPF(){
        chromeDriver.get(TestData.urlProps.baseUrlYandexLink());
        YandexMainPage yandexMainPage = new YandexMainPage(chromeDriver);
        Steps.goToServicePage(yandexMainPage,"Маркет");
        YandexShopPage yandexShopPage = new YandexShopPage(chromeDriver);
        Steps.goToCategory(yandexShopPage,"Компьютеры","Ноутбуки");
        Steps.inputPrice(yandexShopPage,"10000","900000");
        Steps.chooseBrand(yandexShopPage,"HP");
        Steps.chooseBrand(yandexShopPage,"Lenovo");
        List<Map<String,String>> resultMaxItems = yandexShopPage.getMaxItemsPerPage("12");
        Steps.checkmaxItemsPerPage(resultMaxItems,yandexShopPage,12);
        List<Map<String,String>> resultSearchItems = yandexShopPage.searchProductsByName(1);
        Steps.checkSearchProductsByName(resultSearchItems,1,yandexShopPage);
    }
}
