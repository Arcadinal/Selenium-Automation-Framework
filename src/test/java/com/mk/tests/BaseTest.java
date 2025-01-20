package com.mk.tests;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.mk.driver.DriverManager;

public class BaseTest {

	WebDriver driver;

	@BeforeMethod()
	public void setup(Object[] data) {
		Map<String,String> map = (Map<String,String>) data[0];
		System.out.println(map.get("browser"));
		DriverManager.setDriver(map.get("browser"));
		driver = DriverManager.getDriver();
		DriverManager.getDriver().get("https://www.saucedemo.com/");
		driver.get("https://www.saucedemo.com/");
		
	}

	@AfterMethod
	public void flush() {
		DriverManager.quitDriver();
	}

}
