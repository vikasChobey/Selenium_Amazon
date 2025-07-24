package com.ama.qa.pages;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ama.qa.base.TestBase;


public class AmazonSearchProductsPage extends TestBase {

    @FindBy(xpath = "//div[@class='a-section a-spacing-small a-spacing-top-small']//span[@class='a-color-state a-text-bold']")
    WebElement searchedProductsPageLabel;
    
    WebElement serachProductPanel;

    WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
    Set<String> newWindows;
    String oldWindow;

    public AmazonSearchProductsPage() {
        PageFactory.initElements(getdriver(), this);
    }

    public String getPageLabelForSearchedProduct() {
        return searchedProductsPageLabel.getText();
    }

    public void clickOnProduct(String productNamePartialText) {
        
        WebElement product = wait.until(ExpectedConditions
            .visibilityOfElementLocated(By.xpath("//h2[@aria-label[contains(., '" + productNamePartialText + "')]]")));

     
        //oldWindow = getdriver().getWindowHandle();


        ((org.openqa.selenium.JavascriptExecutor) getdriver())
            .executeScript("arguments[0].scrollIntoView(true);", product);


        product.click();

 
        //wait.until(driver -> getdriver().getWindowHandles().size() > 1);
        //newWindows = getdriver().getWindowHandles();

//        for (String window : newWindows) {
//            if (!oldWindow.equals(window)) {
//                getdriver().switchTo().window(window);
//                break;
//            }
//        }


    }

}
