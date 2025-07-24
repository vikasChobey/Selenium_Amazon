package testcases;

import com.ama.qa.base.TestBase;
import com.ama.qa.pages.*;
import com.ama.qa.util.TestUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static com.ama.qa.base.TestBase.initialization;

public class AddMultipleProductAndVerify {

    AmazonHomePage amazonHomePage;
    AmazonSearchProductsPage searchPage;
    AmazonProductDetailsPage productDetailsPage;
    ShoppingCartPage cartPage;
    AmazonProductCheckoutPage checkoutPage;
    AddressPage addressPage;
    AmazonLoginTest amazonLoginTest;
    AmazonProductCheckoutPage amazonProductCheckoutPage;

    @BeforeMethod
    public void setup() {
        new TestBase();
        initialization();
        new TestUtil().handleContinueShoppingIfPresent(TestBase.driver);// Ensure this is called only once per suite
        amazonHomePage = new AmazonHomePage();
        searchPage = new AmazonSearchProductsPage();
        productDetailsPage = new AmazonProductDetailsPage();
        cartPage = new ShoppingCartPage();
        checkoutPage = new AmazonProductCheckoutPage();
        addressPage = new AddressPage();
        amazonLoginTest = new AmazonLoginTest();
        amazonLoginTest.setup();
        amazonProductCheckoutPage = new AmazonProductCheckoutPage();

    }

    @Test(priority = 3)
    public void testAddMultipleProductsAndVerifyCart() throws InterruptedException {

//        TestUtil.runOnlyBetween(LocalTime.of(18, 0), LocalTime.of(19, 0));
        amazonHomePage.selectCategory("Electronics");
        amazonHomePage.searchForProduct("laptop Dell Inspiron 3530, Intel Core i5-1334U");
        amazonHomePage.clickOnSearchButton();
        searchPage.clickOnProduct("Dell Inspiron 3530, Intel Core i5-1334U, 13th Gen, 16GB RAM");
        productDetailsPage.clickOnAddToCart();
        amazonHomePage.searchForProduct("headphones Sony WH-1000XM4");
        amazonHomePage.clickOnSearchButton();
        searchPage.clickOnProduct("Sony ULT Wear WH-ULT900N Noise Cancellation Wireless Bluetooth Over Ear Headphones with Massive Bass");
        productDetailsPage.clickOnAddToCart();
        cartPage.clickProceedToBuy();
        amazonLoginTest.amazonLogin();
    }
    @AfterMethod
    public void tearDown() {
        TestBase.getdriver().quit();
    }
}