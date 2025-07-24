package testcases;

import com.ama.qa.pages.AmazonHomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ama.qa.base.TestBase;
import com.ama.qa.pages.AmazonLoginPage;
import com.ama.qa.util.ConfigReader;

public class AmazonLoginTest extends TestBase {

	ConfigReader configReader = new ConfigReader();
	AmazonLoginPage amazonLoginPage;
	AmazonHomePage amazonHomePage;

	@BeforeClass
	public void setup() {
		amazonLoginPage = PageFactory.initElements(driver, AmazonLoginPage.class);
		amazonHomePage = PageFactory.initElements(driver, AmazonHomePage.class);
	}

	@Test(priority = 4)
	public void amazonLogin() throws InterruptedException {
		amazonLoginPage.enterEmailOrPhone(configReader.getUsername());
		amazonLoginPage.clickContinue();
		amazonLoginPage.enterPassword(configReader.getPassword());
		amazonLoginPage.clickSignIn();
		amazonLoginPage.verifyNoOopsPage();
		amazonLoginPage.clickCartButton();
	}
}