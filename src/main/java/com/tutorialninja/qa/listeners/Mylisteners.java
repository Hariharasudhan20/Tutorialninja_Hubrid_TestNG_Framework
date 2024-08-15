package com.tutorialninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialninja.qa.Utilities.Extentreporter;
import com.tutorialninja.qa.Utilities.Utilities;

public class Mylisteners implements ITestListener {
	ExtentReports extentreport;
	ExtentTest extentTest;
	@Override
	public void onStart(ITestContext context) {
		extentreport = Extentreporter.generateExtentReport();
	}
	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getName();
		extentTest = extentreport.createTest(testname);
		extentTest.log(Status.INFO, testname+" Start");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getName();
		extentTest = extentreport.createTest(testname);
		extentTest.log(Status.PASS, testname+" Got Sucess");
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getName();
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationscreenshotpath = Utilities.capturescreenshot(driver, testname);
		extentTest.addScreenCaptureFromPath(destinationscreenshotpath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testname+" Got Failed");
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getName();
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testname+" Got Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
		
		String pathofextentreport =System.getProperty("user.dir")+"\\test-output\\Extentreport\\extentreport.html";
		File extentreportopen= new File(pathofextentreport);
		try {
			Desktop.getDesktop().browse(extentreportopen.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
}
