package com.tutorials.qa.Baseclass;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base_Class {

	WebDriver driver;
	public Properties prop;
	public Properties dataprop;
	
	public Base_Class() {
	
		prop=new Properties();
		File file=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\config\\config.properties");
		try {
		FileInputStream fis=new FileInputStream(file);
		prop.load(fis);
	}catch(Throwable e) {
		e.printStackTrace();
		
	}
		dataprop =new Properties();
		File file1=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\testdata.properties");
		try {
		FileInputStream fis1=new FileInputStream(file1);
		dataprop.load(fis1);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		}
	
	
	
	public WebDriver InitializeBrowserAndOpenUrl(String browserName) {
		
	
		if(browserName.equalsIgnoreCase("Chrome")){
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("Firefox")) {
			driver=new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get(prop.getProperty("url"));
	
		return driver;
	
	}

}
