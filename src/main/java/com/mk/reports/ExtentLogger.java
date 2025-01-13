package com.mk.reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.mk.utils.ScreenshotUtils;

public class ExtentLogger {
	
	private ExtentLogger() {}
	
	public static void pass(String message) {
		ExtentManagersClass.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}
	
	public static void fail(String message) {
		ExtentManagersClass.getExtentTest().fail(MarkupHelper.createLabel(message,ExtentColor.RED));
		ExtentManagersClass.getExtentTest()
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getScreenShot()).build());
	}
	
	public static void skip(String message) {
		ExtentManagersClass.getExtentTest().skip(MarkupHelper.createLabel(message, ExtentColor.ORANGE));
	}

}
