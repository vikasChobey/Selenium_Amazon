package testcases;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ama.qa.base.TestBase;
import com.ama.qa.pages.ShoppingCartPage;


public class ShoppingCartTest extends TestBase {

	ShoppingCartPage shoppingCartPage;

	@BeforeTest
	public void setup() throws InterruptedException {

		shoppingCartPage = new ShoppingCartPage();

	}

	@Test(priority=3)
	public void SearchForAProduct() throws InterruptedException {
		SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();

		System.out.println("Browser session ID: " + sessionId.toString());
		shoppingCartPage.clickProceedToBuy();
		
		
	}}
