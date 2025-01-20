package com.mk.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.mk.reports.ExtentLogger;
import com.mk.reports.ExtentReport;

public class ListenerClass implements ITestListener,ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		ExtentReport.initReports();
	}
	
	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushReports();
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());
	}
	
	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass("Test Passed"+result.getMethod().getMethodName());
	}
	
	public void onTestFailure(ITestResult result) {
		ExtentLogger.failWithScreenshot();
		ExtentLogger.fail("Test Failed"+result.getMethod().getMethodName()+" "+result.getThrowable().toString());
	}
}
