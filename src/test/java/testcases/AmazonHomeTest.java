package testcases;



import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ama.qa.base.TestBase;
import com.ama.qa.pages.AmazonHomePage;


public class AmazonHomeTest extends TestBase {

	AmazonHomePage amazonHomePage;

	@BeforeMethod
	public void setup() throws InterruptedException {
		//initialization();
		amazonHomePage = new AmazonHomePage();

	}

	 }
