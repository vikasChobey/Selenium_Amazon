package com.ama.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.ama.qa.base.TestBase;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import java.time.Duration;

public class AmazonLoginPage extends TestBase {

	@FindBy(xpath = "//span[text()='Log In']") WebElement logInButton;
	@FindBy(xpath = "//span[@id='continue']//input[@class='a-button-input']") private WebElement continueButton;
	@FindBy(id = "ap_password") @CacheLookup WebElement password;
	@FindBy(id = "signInSubmit") WebElement signInSubmit;
	@FindBy(id = "ap_email_login") WebElement emailOrPhoneField;
	@FindBy(xpath = "//p//b[contains(text(),'Oops!')]") WebElement oopsErrorElement;

	public AmazonLoginPage() {
		PageFactory.initElements(getdriver(), this);
	}

	public String gettitle() {
		return getdriver().getTitle();
	}

	public void enterEmailOrPhone(String emailOrPhone) throws InterruptedException {
		Thread.sleep(2000); // Wait for the page to load
		SessionId sessionId = ((RemoteWebDriver) getdriver()).getSessionId();
		System.out.println("Browser session ID: " + sessionId.toString());
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(30));
		WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("ap_email_login")));
		emailField.sendKeys(emailOrPhone);
	}

	public void clickContinue() {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
		By continueButtonLocator = By.xpath("//span[@id='continue']//input[@class='a-button-input']");
		WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButtonLocator));
		((JavascriptExecutor) getdriver()).executeScript("arguments[0].scrollIntoView(true);", button);
		((JavascriptExecutor) getdriver()).executeScript("arguments[0].click();", button);
	}

	public void enterPassword(String passwordValue) {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
		WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("ap_password")));
		passwordField.sendKeys(passwordValue);
		System.out.println("Password entered successfully!");
	}

	public void clickSignIn() {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
		WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("signInSubmit")));
		((JavascriptExecutor) getdriver()).executeScript("arguments[0].click();", signInButton);
		System.out.println("Clicked on Sign In button successfully!");
	}

	public void clickCartButton() {
		WebDriverWait wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
		WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-cart")));
		((JavascriptExecutor) getdriver()).executeScript("arguments[0].scrollIntoView(true);", cartButton);
		((JavascriptExecutor) getdriver()).executeScript("arguments[0].click();", cartButton);
		System.out.println("Clicked on Cart button successfully!");
	}

	public void verifyNoOopsPage() {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(oopsErrorElement));

			System.out.println("❌ Oops page detected! Stopping test.");
			throw new SkipException("Test stopped: Amazon returned an Oops page (503 Service Unavailable).");

			// Alternatively:
			// Assert.fail("Amazon returned an Oops page (503 Service Unavailable).");

		} catch (TimeoutException e) {
			// Not found within timeout, continue normally
			System.out.println("✅ No Oops page detected, continuing execution.");
		}
	}



}