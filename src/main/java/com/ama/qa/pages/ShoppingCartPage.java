package com.ama.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.ama.qa.base.TestBase;


public class ShoppingCartPage extends TestBase {

	@FindBy(xpath = "//input[@id='buy-now-button']")WebElement proceedToBuy;
	@FindBy(xpath = "//span[contains(@class,'sc-grid-item-product-title')]//span[contains(@class,'a-truncate-full')]")
	private WebElement cartItemFullTitle;
	@FindBy(xpath = "//span[contains(@class,'a-price apex-price-to-pay-value')]//span[@class='a-offscreen']")
	private WebElement cartItemPrice;
	@FindBy(xpath = "//span[@id='sc-buy-box-ptc-button']//input[@class='a-button-input']")
	private WebElement proceedToBuyAfterCart;
	@FindBy(xpath = "//div[@id='nav-cart-count-container']") WebElement cartButton;

	public ShoppingCartPage(){
			PageFactory.initElements(getdriver(), this);
		}
	 
	@Test(priority = 3)
	public void clickProceedToBuy() {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));

		try {
			WebElement button = wait.until(ExpectedConditions.elementToBeClickable(proceedToBuy));
			((JavascriptExecutor)getdriver()).executeScript("arguments[0].scrollIntoView(true);", button);
			((JavascriptExecutor)getdriver()).executeScript("arguments[0].click();", button);
			System.out.println("Clicked primary Proceed to Buy button.");
		} catch (Exception e) {
			System.out.println("⚠ Primary Proceed to Buy not found or not clickable, trying alternate...");

			try {
				WebElement altButton = wait.until(ExpectedConditions.elementToBeClickable(proceedToBuyAfterCart));
				((JavascriptExecutor)getdriver()).executeScript("arguments[0].click();", altButton);
				System.out.println("Clicked alternate Proceed to Buy (buy-now-button).");
			} catch (Exception ex) {
				System.out.println("Neither Proceed to Buy button was clickable.");
				throw ex; // Rethrow so test fails visibly
			}
		}
	}


	public String getCartItemTitle() {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(
				org.openqa.selenium.By.xpath("//span[contains(@class,'sc-grid-item-product-title')]//span[contains(@class,'a-truncate-full')]")
		));
		String title = cartItemFullTitle.getAttribute("textContent").trim();
		System.out.println("🛒 Cart Item Title Found: " + title);
		return title;
	}


	public String getCartItemPrice() {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(
				By.xpath("//span[contains(@class,'a-price apex-price-to-pay-value')]//span[@class='a-offscreen']")
		));
		String price = cartItemPrice.getAttribute("textContent").trim();
		System.out.println(" Cart Item Price Found: " + price);
		return price;
	}

	public void clickOnCart() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);
		System.out.println("✅ Clicked on Cart button (via JS).");
	}


	
	
}
