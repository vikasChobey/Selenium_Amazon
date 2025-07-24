package testcases;
import com.ama.qa.base.TestBase;
import com.ama.qa.util.TestUtil;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.LocalTime;

import com.ama.qa.pages.*;

import static com.ama.qa.base.TestBase.initialization;

public class AmazonEndToEndTest {

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

    @Test(priority = 0)
    public void testCompleteOrderFlow() throws InterruptedException {
//        TestUtil.runOnlyBetween(LocalTime.of(18, 0), LocalTime.of(17, 0));
        amazonHomePage.selectCategory("Electronics");
        amazonHomePage.searchForProduct("laptop Dell Inspiron 3530"); // Example search
        amazonHomePage.clickOnSearchButton();
        amazonHomePage.selectProductWithPriceHighToLow();
        searchPage.clickOnProduct("Dell Vostro 5630 Laptop, Intel Core i7-1355U Processor/ 16GB LPDDR5/ 512GB SSD");
        productDetailsPage.clickOnAddToCart();
        cartPage.clickProceedToBuy();
        amazonLoginTest.amazonLogin();
        amazonProductCheckoutPage.clickAddNewAddressButton();
        addressPage.fillAddressForm();
    }

    @AfterMethod
    public void tearDown() {
        TestBase.getdriver().quit();
    }
}