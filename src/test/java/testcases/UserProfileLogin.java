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

public class UserProfileLogin {

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

    @Test(priority = 2)
    public void testCompleteOrderFlow() throws InterruptedException {
//        TestUtil.runOnlyBetween(LocalTime.of(12, 0), LocalTime.of(15, 0)); // Time check
        amazonHomePage.clickSignIn();
        amazonLoginTest.amazonLogin();
        amazonHomePage.validateProfileName();
    }
    @AfterMethod
    public void tearDown() {
        TestBase.getdriver().quit();
    }
}