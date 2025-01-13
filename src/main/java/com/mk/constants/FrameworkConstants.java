package com.mk.constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FrameworkConstants {
	
	private FrameworkConstants(){};

	private static final String RESOURCESPATH = System.getProperty("user.dir") + "/src/test/resources";
	private static final String CONFIGFILEPATH = RESOURCESPATH + "/config/config.properties";
	private static final String GRIDURL = "http://localhost:4444/wd/hub";
	private static final int EXPLICITWAIT = 10;
	private static final String EXTENTREPORTDIR = System.getProperty("user.dir")+"/test-output/extentReports/";
	private static final String TESTRUNNER = "D:\\SeleniumAutomationFramework\\SeleniumAutomation\\src\\test\\resources\\excel\\testRunner.xlsx";
	private static final String EXTENTREPORTFILEPATH;
	
	
	static {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
		String timestamp = now.format(formatter);
		EXTENTREPORTFILEPATH = EXTENTREPORTDIR + "Test-Report-" + timestamp + ".html";
	}
	
	
	public static String getResourcespath() {
		return RESOURCESPATH;
	}

	public static String getConfigfilepath() {
		return CONFIGFILEPATH;
	}

	public static String getGridurl() {
		return GRIDURL;
	}
	public static int getExplicitwait() {
		return EXPLICITWAIT;
	}

	public static String getExtentreportfilepath() {
//		LocalDateTime n = LocalDateTime.now();
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-00");
//		String currentTime = n.format(dtf);
		return EXTENTREPORTFILEPATH;
	}

	public static String getTestrunner() {
		return TESTRUNNER;
	}
}
