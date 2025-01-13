package com.mk.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.mk.constants.FrameworkConstants;

public final class ExtentReport {

	private ExtentReport() {
	}

	private static ExtentReports extentReport;

	public static void initReports() {
		if (Objects.isNull(extentReport)) {
			extentReport = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.getExtentreportfilepath());
			spark.config().setDocumentTitle("Automation Report");
			spark.config().setReportName("SauceLabs Demo Report");
			extentReport.attachReporter(spark);
		}
	}
	
	public static void createTest(String testCaseName) {
		ExtentManagersClass.setExtentTest(extentReport.createTest(testCaseName));
	}

	public static void flushReports() {
		if (Objects.nonNull(extentReport)) {
			extentReport.flush();
		}
		ExtentManagersClass.unload();
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentreportfilepath()).toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
