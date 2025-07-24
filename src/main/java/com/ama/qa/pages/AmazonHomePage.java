package com.ama.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;


import com.ama.qa.base.TestBase;

import java.time.Duration;

public class AmazonHomePage extends TestBase{
	
	
	Select category;
	private WebDriverWait wait;

	 @FindBy(xpath = "//input[@id='twotabsearchtextbox']") WebElement searchBoxInput;
	 @FindBy(xpath = "//select[@id='searchDropdownBox']")WebElement categoryDropDown;
	 @FindBy(xpath = "//input[@id='nav-search-submit-button']")@CacheLookup WebElement searchButton;
	 @FindBy(id = "nav-link-accountList-nav-line-1") WebElement signInSpan;
	 @FindBy(xpath = "//div[@class='s-suggestion-container']//div[1]/span") WebElement searchSuggestion;
	 @FindBy(xpath = "//div[@id='nav-cart-count-container']") WebElement cartButton;
	 @FindBy(xpath = "//li[.//span[normalize-space(text())='Eligible for Pay On Delivery']]//input[@type='checkbox']") WebElement payOnDeliveryCheckbox;
	 @FindBy(xpath = "//div[@id='reviewsRefinements']//li[.//span[contains(normalize-space(.), 'Up')]]") WebElement fourStarsAndUp;
	 @FindBy(id = "nav-hamburger-menu") WebElement hamburgerMenu;
	 @FindBy(xpath = "//div[@id='nav-link-accountList']//span[@id='nav-link-accountList-nav-line-1']") WebElement homeProfileElement;

	public AmazonHomePage(){
			PageFactory.initElements(getdriver(), this);
			this.wait = new WebDriverWait(getdriver(), Duration.ofSeconds(20));
		}

	
	 public void selectCategory(String categoryValue) {
		    try {
		        Select category = new Select(categoryDropDown);
		        category.selectByVisibleText(categoryValue);
		    } catch (Exception e) {
		        System.out.println("Standard Select failed, trying JS");

		        String script =
		            "var select = arguments[0]; " +
		            "for (var i = 0; i < select.options.length; i++) { " +
		            "  if (select.options[i].text.trim() === arguments[1].trim()) { " +
		            "    select.selectedIndex = i; " +
		            "    select.dispatchEvent(new Event('change')); " +
		            "    break; " +
		            "  } " +
		            "}";

		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript(script, categoryDropDown, categoryValue);
		    }
		}

	
	 public String getSelectedCategory() {
		    // recreate Select to reflect updated DOM
		    Select category = new Select(categoryDropDown);
		    String selectedCategory = category.getFirstSelectedOption().getText().trim();
		    System.out.println("Selected category: >" + selectedCategory + "<");
		    return selectedCategory;
		}
	
	public void searchForProduct(String product){
		searchBoxInput.sendKeys(product);	
		//searchSuggestion.click();
	}

	public void clickOnSearchButton() {
		// Always re-find the element before clicking to avoid stale reference
		WebDriverWait wait = new WebDriverWait(TestBase.getdriver(), Duration.ofSeconds(10));
		WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("nav-search-submit-button")));
		searchButton.click();
	}

	public void clickOnCart() {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);
		System.out.println("✅ Clicked on Cart button (via JS).");
	}

	// In AmazonHomePage.java

	public void selectProductWithPriceHighToLow() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

			// ✅ Click on the "Featured" option (acts as the dropdown trigger)
			WebElement featuredOption = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//span[contains(text(),'Featured')]")));
			featuredOption.click();
			System.out.println("✅ Opened sort dropdown");

			// ✅ Wait for "Price: High to Low" option to appear and click it
			WebElement highToLowOption = wait.until(ExpectedConditions.elementToBeClickable(
					By.xpath("//a[contains(text(),'Price: High to Low')]")));
			highToLowOption.click();
			System.out.println("✅ Sorted results by Price: High to Low");

		} catch (Exception e) {
			System.out.println("❌ Failed to sort by Price High to Low: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void clickPayOnDeliveryCheckboxIfNotSelected() {
		if (!payOnDeliveryCheckbox.isSelected()) {
			WebElement checkbox = driver.findElement(By.xpath("//li[.//span[normalize-space(text())='Eligible for Pay On Delivery']]//input[@type='checkbox']"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			System.out.println("✅ Pay On Delivery checkbox clicked.");
		} else {
			System.out.println("ℹ️ Pay On Delivery checkbox already selected.");
		}
	}
	// Java
	public void clickFormatCheckboxByName(String formatName) throws InterruptedException {
		Thread.sleep(1000);
		String xpath = String.format(
				"//li[.//span[normalize-space(text())='%s']]//input[@type='checkbox']",
				formatName
		);
		WebElement checkbox = driver.findElement(By.xpath(xpath));

		if (!checkbox.isSelected()) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
			// Use JS click to avoid interception
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
			System.out.println("✅ Checkbox for " + formatName + " clicked (via JS).");
		} else {
			System.out.println("ℹ️ Checkbox for " + formatName + " already selected.");
		}
	}

	public void clickLanguageCheckboxByName(String languageName) {
		String xpath = String.format(
				"//li[.//span[normalize-space(text())='%s']]//input[@type='checkbox']",
				languageName
		);
		try {
			WebElement checkbox = driver.findElement(By.xpath(xpath));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);

			if (!checkbox.isSelected()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
				System.out.println("✅ Checkbox for language '" + languageName + "' clicked (via JS).");
			} else {
				System.out.println("ℹ️ Checkbox for language '" + languageName + "' already selected.");
			}
		} catch (NoSuchElementException e) {
			System.out.println("❌ Language checkbox with name '" + languageName + "' not found.");
		}
	}
	public void clickFourStarsAndUp() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", fourStarsAndUp);
		fourStarsAndUp.click();
		System.out.println("✅ Clicked on '4 Stars & Up' filter under Customer Reviews.");
	}

	public void clickSignIn() {
		if (isLoggedIn()) {
			System.out.println("✅ User already logged in. Skipping login steps.");
			return; // Skip login
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signInSpan);
		signInSpan.click();
		System.out.println("✅ Clicked on Sign In link.");
	}

	public void clickHamburgerMenu() {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", hamburgerMenu);
		hamburgerMenu.click();
		System.out.println("✅ Clicked on Hamburger Menu (All).");
	}
	public void validateProfileName() {
		// Wait until the profile element is visible
		wait.until(ExpectedConditions.visibilityOf(homeProfileElement));

		// Get full text
		String fullText = homeProfileElement.getText().trim(); // e.g. "Hello, Auto"
		System.out.println("ℹ️ Full profile text: " + fullText);

		// Extract name after "Hello,"
		String profileName = "";
		if (fullText.toLowerCase().startsWith("hello,")) {
			profileName = fullText.replaceFirst("(?i)hello,", "").trim();
		} else {
			profileName = fullText.trim();
		}
		System.out.println("✅ Extracted profile name: " + profileName);

		// Validate against forbidden characters
		String forbidden = "ACGILK";
		for (char ch : forbidden.toCharArray()) {
			if (profileName.toUpperCase().contains(String.valueOf(ch))) {
				throw new AssertionError("❌ Profile name contains forbidden character: " + ch);
			}
		}

		System.out.println("✅ Profile name is valid and does not contain forbidden characters.");
	}

	public boolean isLoggedIn() {
		try {
			// Wait for the home profile element on the top nav
			wait.until(ExpectedConditions.visibilityOf(homeProfileElement));

			// Get the text (e.g., "Hello, Auto")
			String fullText = homeProfileElement.getText().trim();
			System.out.println("ℹ️ Home profile text: " + fullText);

			// Check if it actually contains a name after "Hello,"
			if (fullText.toLowerCase().startsWith("hello,")) {
				String profileName = fullText.replaceFirst("(?i)hello,", "").trim();
				System.out.println("✅ Extracted profile name: " + profileName);

				// Validate profile name is not default "sign in"
				if (!profileName.equalsIgnoreCase("sign in") && !profileName.isEmpty()) {
					System.out.println("✅ User is logged in (from home).");
					return true;
				}
			}

			System.out.println("❌ User is NOT logged in (from home).");
			return false;

		} catch (Exception e) {
			System.out.println("❌ Could not find home profile element or user not logged in: " + e.getMessage());
			return false;
		}
	}


}
