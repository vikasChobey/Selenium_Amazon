package com.ama.qa.pages;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ama.qa.base.TestBase;


public class AmazonProductDetailsPage extends TestBase {

    @FindBy(xpath = "//div[@id='titleSection']")
    WebElement searchedProductTitle;

    @FindBy(xpath = "//div[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']")
    WebElement searchedProductPrice;

    @FindBy(xpath = "//div[@id='desktop_qualifiedBuyBox']//span[@id='submit.add-to-cart' and contains(@class,'a-button-primary')]//input[@id='add-to-cart-button' and @type='submit']")
    WebElement addToCartButton;

    // Side sheet's Proceed to Checkout button
    @FindBy(id = "attach-sidesheet-checkout-button")
    WebElement proceedToCheckoutFromSheet;


    @FindBy(id = "attach-close_sideSheet-link")
    WebElement closeCartSideSheet;

    WebDriverWait wait;

    public AmazonProductDetailsPage() {
        PageFactory.initElements(getdriver(), this);
        wait = new WebDriverWait(getdriver(), Duration.ofSeconds(5));
    }

    public String getSearchedProductTitle() {
        return searchedProductTitle.getText().trim();
    }

    public String getSearchedProductPrice() {
        return searchedProductPrice.getText().trim();
    }

    public void clickOnAddToCart() {
        try {
            wait.until(ExpectedConditions.visibilityOf(addToCartButton));
            wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));

            ((JavascriptExecutor) getdriver()).executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
            Thread.sleep(500); // optional but useful in some cases

            ((JavascriptExecutor) getdriver()).executeScript("arguments[0].click();", addToCartButton);

            System.out.println("Add to Cart clicked.");
        } catch (Exception e) {
            System.out.println("Failed to click Add to Cart: " + e.getMessage());
            // Add screenshot or throw exception if needed
        }
    }


    public void clickProceedToCheckoutFromSheet() {
        try {
            WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutFromSheet));
            ((JavascriptExecutor) getdriver()).executeScript("arguments[0].click();", checkoutBtn);
            System.out.println("Proceed to Checkout clicked from side sheet.");
        } catch (Exception e) {
            System.out.println("Proceed to Checkout not visible. Trying to close side sheet...");
            try {
                WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(closeCartSideSheet));
                ((JavascriptExecutor) getdriver()).executeScript("arguments[0].click();", closeBtn);
                System.out.println("Side sheet closed.");
            } catch (Exception ex) {
                System.out.println("Side sheet was not present.");
            }
        }
    }
}
