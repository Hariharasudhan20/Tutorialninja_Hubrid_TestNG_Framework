package com.tutorialsninja.qa.testcase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.Pages.AccountPage;
import com.tutorialninja.qa.Pages.Home;
import com.tutorialninja.qa.Pages.RegisterPage;
import com.tutorialninja.qa.Utilities.Utilities;
import com.tutorials.qa.Baseclass.Base_Class;

public class RegisterTest extends Base_Class {
	
	public RegisterTest() {
		super();
	}
	
	public WebDriver driver;
	RegisterPage registerpage;
	@BeforeMethod
	public void Setup() {
		
		
		driver=InitializeBrowserAndOpenUrl(prop.getProperty("browserName"));
		Home home=new Home(driver);
		home.clickonmyaccount();
		registerpage =home.Registerclick();
	
	
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	@Test(priority=1)
	public void RegisterAnAccountwithMandatoryfields() {
		
		
		registerpage.enterfirstname(dataprop.getProperty("firstname"));
		registerpage.enterlastname(dataprop.getProperty("lastname"));
		registerpage.enteremail(Utilities.Timestamp());
		registerpage.entertelephone(dataprop.getProperty("telephone"));
		registerpage.enterpassword(dataprop.getProperty("Password"));
		registerpage.enterconfirmpassword(dataprop.getProperty("Password"));
		registerpage.privacypolicyclick();
		registerpage.continueclick();
		
		AccountPage accpage=new AccountPage(driver);
		String actualsucessheading= accpage.accountcreatedsuccessmessage();
		System.out.println(actualsucessheading);
		Assert.assertEquals(actualsucessheading, "Your Account Has Been Created!", "Sucess Not displayed");
		
	}
	@Test (priority=2)
	public void VerifyRegisteringAccountByProvidingAllFields() {
		
		registerpage.enterfirstname(dataprop.getProperty("firstname"));
		registerpage.enterlastname(dataprop.getProperty("lastname"));
		registerpage.enteremail(Utilities.Timestamp());
		registerpage.entertelephone(dataprop.getProperty("telephone"));
		registerpage.enterpassword(dataprop.getProperty("Password"));
		registerpage.enterconfirmpassword(dataprop.getProperty("Password"));
		registerpage.newsletterclick();
		registerpage.privacypolicyclick();
		registerpage.continueclick();
		
		AccountPage accpage=new AccountPage(driver);
		String actualsucessheading= accpage.accountcreatedsuccessmessage();
		System.out.println(actualsucessheading);
		Assert.assertEquals(actualsucessheading, "Your Account Has Been Created!", "Sucess Not displayed");
	}
	
	@Test (priority=3)
	public void VerifyRegisteringAccountByProvidingExitEmailAddress() {
		
		registerpage.enterfirstname(dataprop.getProperty("firstname"));
		registerpage.enterlastname(dataprop.getProperty("lastname"));
		registerpage.enteremail(prop.getProperty("Email"));
		registerpage.entertelephone(dataprop.getProperty("telephone"));
		registerpage.enterpassword(dataprop.getProperty("Password"));
		registerpage.enterconfirmpassword(dataprop.getProperty("Password"));
		registerpage.newsletterclick();
		registerpage.privacypolicyclick();
		registerpage.continueclick();
		
		
		String Warningmessage= registerpage.emailwarningmessageshowing();
		System.out.println(Warningmessage);
		Assert.assertTrue(Warningmessage.contains(dataprop.getProperty("EmailWarningmessage")), "Warning message not displayed");
	
	}
	
	@Test (priority=4)
	public void VerifyRegisteringAccountWithoutfillingAnyDetails() {
		
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String FirstnameWarningmessage=driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		System.out.println(FirstnameWarningmessage);
		Assert.assertTrue(FirstnameWarningmessage.contains("First Name must be between 1 and 32 characters!"), "First Name Warning message not displayed");
		
		String LastnameWarningmessage=driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(LastnameWarningmessage, "Last Name must be between 1 and 32 characters!", "Last Name Warning message not displayed");
		
		String EmailWarningmessage=driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertTrue(EmailWarningmessage.contains("E-Mail Address does not appear to be valid!"), "Email Warning message not displayed");
		
		String TelephoneWarningmessage=driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(TelephoneWarningmessage, "Telephone must be between 3 and 32 characters!", "Telephone Warning message not displayed");
		
		String PasswordWarningmessage=driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertTrue(PasswordWarningmessage.contains("Password must be between 4 and 20 characters!"), "Password Warning message not displayed");
	}
	
	
}
