package com.mk.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.mk.driver.DriverManager;

public class ScreenshotUtils {
	
	private ScreenshotUtils() {}
	
	public static String getScreenShot() {
		return ((TakesScreenshot)DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}

}
