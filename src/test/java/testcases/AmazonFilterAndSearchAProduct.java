package testcases;

import com.ama.qa.base.TestBase;
import com.ama.qa.pages.*;
import com.ama.qa.util.TestUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalTime;

import static com.ama.qa.base.TestBase.initialization;

public class AmazonFilterAndSearchAProduct {

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
        new com.ama.qa.base.TestBase();
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

    @Test(priority = 1)
    public void testCompleteOrderFlow() throws InterruptedException {
//        TestUtil.runOnlyBetween(LocalTime.of(15, 0), LocalTime.of(18, 0)); // Time check
        amazonHomePage.selectCategory("Books");
        amazonHomePage.searchForProduct("C");
        amazonHomePage.clickOnSearchButton();
        amazonHomePage.selectProductWithPriceHighToLow();
        amazonHomePage.clickPayOnDeliveryCheckboxIfNotSelected();
        amazonHomePage.clickFormatCheckboxByName("Paperback");
        amazonHomePage.clickFormatCheckboxByName("Hardcover");
        amazonHomePage.clickLanguageCheckboxByName("English");
        amazonHomePage.clickFourStarsAndUp();
        searchPage.clickOnProduct("Frontier Computing: Proceedings of FC 2021");
        productDetailsPage.clickOnAddToCart();
        cartPage.clickProceedToBuy();
        amazonLoginTest.amazonLogin();
        cartPage.clickOnCart();

    }
    @AfterMethod
    public void tearDown() {
        TestBase.getdriver().quit();
    }

}