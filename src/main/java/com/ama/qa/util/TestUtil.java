package com.ama.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class TestUtil {
    public static void runOnlyBetween(LocalTime start, LocalTime end) {
        LocalTime now = LocalTime.now();
        if (now.isBefore(start) || now.isAfter(end)) {
            throw new SkipException("Test runs only between " + start + " and " + end);
        }
    }
    public static void clickCheckBoxIfNotSelected(WebDriver driver, By checkboxLocator) {
        WebElement checkbox = driver.findElement(checkboxLocator);
        if (!checkbox.isSelected()) {
            // Scroll into view for safety
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
            checkbox.click();
            System.out.println("✅ Checkbox clicked.");
        } else {
            System.out.println("ℹ️ Checkbox is already selected.");
        }
    }
    public void handleContinueShoppingIfPresent(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            // Look for the "Continue shopping" button
            List<WebElement> buttons = driver.findElements(By.xpath("//button[@type='submit' and text()='Continue shopping']"));
            if (!buttons.isEmpty()) {
                // Button is present, click it
                WebElement continueBtn = buttons.get(0);
                wait.until(ExpectedConditions.elementToBeClickable(continueBtn));
                continueBtn.click();
                System.out.println("✅ Clicked on 'Continue shopping' button.");
            } else {
                System.out.println("ℹ️ 'Continue shopping' button not present, continuing normally.");
            }
        } catch (Exception e) {
            System.out.println("ℹ️ No 'Continue shopping' popup appeared: " + e.getMessage());
        }
    }

}