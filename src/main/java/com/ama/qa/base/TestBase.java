package com.ama.qa.base;

import java.io.FileInputStream;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 public class TestBase {

	public static WebDriver driver = null;
	public static Properties prop;
	// public static EventFiringWebDriver e_driver;
	// public static WebEventListener eventListener;

	public TestBase() {
		try {
			prop = new Properties();
			String path = System.getProperty("user.dir") + "/src/main/java/com/ama/qa/config/config.properties";
			FileInputStream ip = new FileInputStream(path);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//String browsername = prop.getProperty("browser");

	}

	public static void initialization() {
	    String browsername = prop.getProperty("BROWSER").toLowerCase();
	    String url = prop.getProperty("URL");

	    if (browsername.equals("chrome")) {
	        WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();

	    } else if (browsername.equals("edge")) {
	        WebDriverManager.edgedriver().setup();
	        driver = new EdgeDriver();

	    } else if (browsername.equals("firefox")) {
	        WebDriverManager.firefoxdriver().setup();
	        driver = new FirefoxDriver();

	    } else {
	        throw new RuntimeException("Browser not supported: " + browsername);
	    }
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    driver.get(url);
	}



	/*public static void myWait()
	{
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(2000));
		//wait.until(ExpectedConditions.element))
	}
	*/
	public static WebDriver getdriver() {
	    if (driver == null) {
	        String browserName = prop.getProperty("BROWSER").toLowerCase();

	        switch (browserName) {
	            case "chrome":
	                driver = new ChromeDriver();
	                break;
	            case "firefox":
	                driver = new FirefoxDriver();
	                break;
	            case "edge":
	                driver = new EdgeDriver();
	                break;
	            default:
	                throw new RuntimeException("Browser not supported: " + browserName);
	        }
	    }
	    return driver;
	}



}

