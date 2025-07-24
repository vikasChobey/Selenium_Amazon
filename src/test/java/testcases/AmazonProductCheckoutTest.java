package testcases;

import com.ama.qa.pages.AddressPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.ama.qa.pages.AmazonProductCheckoutPage;


public class AmazonProductCheckoutTest {
	
	AmazonProductCheckoutPage amazonProductCheckoutPage;
	AddressPage addressPage;
	@BeforeClass
	public void setup()
	{
		//initialization();
		amazonProductCheckoutPage=new AmazonProductCheckoutPage();
		addressPage= new AddressPage();
	}
	
	@Test(priority = 5)
	public void useAddress()
	{
		amazonProductCheckoutPage.clickAddNewAddressButton();
		addressPage.fillAddressForm();

	}
}
