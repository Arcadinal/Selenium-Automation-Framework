package com.mk.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mk.driver.DriverManager;

public class BaseTest {
	
	WebDriver driver;
	@BeforeMethod
	public void setup() {
		driver = DriverManager.getDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
	}
	
	@AfterMethod
	public void flush() {
		DriverManager.quitDriver();
	}

}
