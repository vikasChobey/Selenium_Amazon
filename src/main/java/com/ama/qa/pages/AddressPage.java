package com.ama.qa.pages;

import com.ama.qa.base.TestBase;
import com.ama.qa.util.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;


public class AddressPage extends TestBase {

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName']")
	private WebElement fullNameTxt;
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPhoneNumber']")
	private WebElement mobileNumberTxt;
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPostalCode']")
	private WebElement pincodeTxt;
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine1']")
	private WebElement addressLine1Txt;
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine2']")
	private WebElement addressLine2Txt;
	@FindBy(xpath = "//input[@id='address-ui-widgets-landmark']")
	private WebElement landmarkTxt;
	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressCity']")
	private WebElement cityTxt;
	@FindBy(xpath = "//select[@id='address-ui-widgets-enterAddressStateOrRegion-dropdown-nativeId']")
	private WebElement stateDropdown;
	@FindBy(xpath = "//span[@id='checkout-primary-continue-button-id-announce']/preceding-sibling::input[@type='submit']")
	private WebElement saveAddressBtn;


	ConfigReader configReader=new ConfigReader();

	public AddressPage(){
			PageFactory.initElements(getdriver(), this);
		}
	 
	@Test(priority = 4)
	public void fillAddressForm() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(fullNameTxt)).sendKeys(configReader.fullName);
		mobileNumberTxt.sendKeys(configReader.mobile);
		pincodeTxt.sendKeys("201001");
		addressLine1Txt.sendKeys("R-275");
		addressLine2Txt.sendKeys("Nandgram");
		landmarkTxt.sendKeys("Ghaziabad");
		cityTxt.sendKeys("ghaziabad");

		Select stateSelect = new Select(stateDropdown);
		stateSelect.selectByVisibleText("UTTAR PRADESH");
		wait.until(ExpectedConditions.elementToBeClickable(saveAddressBtn)).click();
		System.out.println("✅ Address form filled successfully.");
	}

}
