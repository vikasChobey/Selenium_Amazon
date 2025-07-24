package com.ama.qa.pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ama.qa.base.TestBase;


public class AmazonProductCheckoutPage extends TestBase{
	@FindBy(xpath = "//span[@id='shipToThisAddressButton']")WebElement shipToAddressButton;
	@FindBy(xpath = "//span[@id='add-new-address-desktop-sasp-tango-link']//a") WebElement addNewAddressButton;


	public AmazonProductCheckoutPage(){
		PageFactory.initElements(getdriver(), this);
	}
 
	public void clickShipToAddressButton()
	{
		shipToAddressButton.click();
	}

	public void clickAddNewAddressButton() {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
		try {
			wait.until(driver -> addNewAddressButton.isDisplayed() && addNewAddressButton.isEnabled());
			((org.openqa.selenium.JavascriptExecutor) getdriver()).executeScript("arguments[0].click();", addNewAddressButton);
			System.out.println("Clicked on Add New Address button (via JS).");
		} catch (org.openqa.selenium.TimeoutException | org.openqa.selenium.NoSuchElementException e) {
			System.err.println("❌ Add New Address button not found or not clickable. Check page state and locator.");
		}
	}
}
