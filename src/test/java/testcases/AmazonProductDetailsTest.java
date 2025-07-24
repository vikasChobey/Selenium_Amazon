package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ama.qa.base.TestBase;
import com.ama.qa.pages.AmazonHomePage;
import com.ama.qa.pages.AmazonLoginPage;
import com.ama.qa.pages.AmazonProductDetailsPage;
import com.ama.qa.pages.ShoppingCartPage;

import java.time.Duration;


public class AmazonProductDetailsTest extends TestBase {

    AmazonProductDetailsPage amazonProductDetailsPage;
    AmazonHomePage amazonHomePage;
    ShoppingCartPage shoppingCartPage;
    AmazonLoginPage amazonLoginPage;

    @BeforeClass
    public void setup() {
        //initialization();
        amazonProductDetailsPage = new AmazonProductDetailsPage();
        amazonHomePage = new AmazonHomePage();
        shoppingCartPage = new ShoppingCartPage();
        amazonLoginPage = new AmazonLoginPage();
    }

    @Test
    public void verifyProductDetailsAndAddToCartFlow() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("sc-active-items-header"),
                "Shopping Cart"
        ));
        String expectedTitle = "Noise Twist Go Round dial Smartwatch";
        String expectedPrice = "₹1,399.00";

        String actualTitle = shoppingCartPage.getCartItemTitle();
        String actualPrice = shoppingCartPage.getCartItemPrice();

        Assert.assertTrue(actualTitle.toLowerCase().contains(expectedTitle.toLowerCase()), "Product title mismatch.");
        Assert.assertEquals(actualPrice, expectedPrice, "Product price mismatch.");

//        amazonProductDetailsPage.clickOnAddToCart();
//        amazonProductDetailsPage.clickProceedToCheckoutFromSheet();
//        amazonHomePage.clickOnCart();
        shoppingCartPage.clickProceedToBuy();
        System.out.println("Test completed successfully: Product added to cart and proceeded to buy.");
    }

}
