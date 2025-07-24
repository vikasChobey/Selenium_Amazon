package com.ama.qa.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class ConfigReader {
	public String fullName;
	public String mobile;
	private String username = "";
	private String password = "";
	private String browser ="";

	public ConfigReader() {
		FileInputStream fis;
		try {
			String path = System.getProperty("user.dir") + "/src/main/java/com/ama/qa/config/config.properties";
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Cant't read config.properties file!");
			return;
		}
		Properties p = new Properties();
		try {
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Cant't read config.properties file!");
			return;
		}
		username = p.getProperty("USERNAME");
		password = p.getProperty("PASSWORD");
		browser = p.getProperty("BROWSER");
		fullName = p.getProperty("FULL_NAME");
		mobile = p.getProperty("MOBILE");
		
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}
	
	public String getBrowser() {
		return browser;
	}
}