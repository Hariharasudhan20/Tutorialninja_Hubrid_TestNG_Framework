package com.tutorialninja.qa.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputFilter.Config;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Extentreporter {
	
	public static ExtentReports generateExtentReport() {
		
	ExtentReports extentreport=new ExtentReports();
	
	File extentreportfile= new File(System.getProperty("user.dir")+"\\test-output\\Extentreport\\extentreport.html");
	ExtentSparkReporter sparkreport= new ExtentSparkReporter(extentreportfile);
	
	sparkreport.config().setTheme(Theme.DARK);
	sparkreport.config().setReportName("Tutorials ninja test automation result");
	sparkreport.config().setDocumentTitle("Tutorials Automation Report");
	sparkreport.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");
	
	extentreport.attachReporter(sparkreport);
	
	
	Properties Configprop =new Properties();
	File file1=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
	try {
	FileInputStream fis1=new FileInputStream(file1);
	Configprop.load(fis1);
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	extentreport.setSystemInfo("Application url", Configprop.getProperty("url"));
	extentreport.setSystemInfo("Browser", Configprop.getProperty("browserName"));
	extentreport.setSystemInfo("Email", Configprop.getProperty("Email"));
	extentreport.setSystemInfo("Password", Configprop.getProperty("Password"));
	extentreport.setSystemInfo("Operating System", System.getProperty("os.name"));
	extentreport.setSystemInfo("User Name", System.getProperty("user.name"));
	extentreport.setSystemInfo("Java Version", System.getProperty("java.version"));
	return extentreport;
	
	}
}
