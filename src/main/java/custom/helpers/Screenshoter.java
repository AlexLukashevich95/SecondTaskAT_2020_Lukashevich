package custom.helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Screenshoter {

    /**
     * Метод создания скриншотов каждого продукта
     */
    @Attachment
    public static void getScreenOfProducts(WebDriver driver, List<WebElement> products, String procedureName){
        Actions actions = new Actions(driver);
        for(WebElement product : products){
        actions.moveToElement(product).build().perform();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenshot,new File("src/main/resources/"+procedureName+"/screen"+product.getLocation()+".png"));
                Allure.addAttachment("screen"+product.getLocation()+".png", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод создания скриншота
     */
    @Attachment
    public static byte[] getScreen(WebDriver driver, String procedureName){
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot,new File("src/main/resources/"+procedureName+"/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources/"+procedureName+"/","screen.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * Метод создания скриншота искомого продукта
     */
    @Attachment
    public static byte[] getScreenOfSearchedProduct(WebDriver driver,List<WebElement> products,String nameProduct, String procedureName){
        Actions actions = new Actions(driver);
        for(WebElement product : products){
            if(product.getText().contains(nameProduct)){
                actions.moveToElement(product).build().perform();
                File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                try {
                 FileUtils.copyFile(screenshot,new File("src/main/resources/"+procedureName+"/screen"+product.getLocation()+".png"));
                    return Files.readAllBytes(Paths.get("src/main/resources/"+procedureName+"/","screen"+product.getLocation()+".png"));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return new byte[0];
    }
}
