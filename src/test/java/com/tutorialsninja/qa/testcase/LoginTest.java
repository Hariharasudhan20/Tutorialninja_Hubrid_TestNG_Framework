package com.tutorialsninja.qa.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialninja.qa.Pages.AccountPage;
import com.tutorialninja.qa.Pages.Home;
import com.tutorialninja.qa.Pages.LoginPages;
import com.tutorialninja.qa.Utilities.Utilities;
import com.tutorials.qa.Baseclass.Base_Class;

public class LoginTest extends Base_Class {
	
	public LoginTest() {
		super();
	}
		
	public WebDriver driver;
	LoginPages loginpage;
	@BeforeMethod
	public void setup() {
		
		driver = InitializeBrowserAndOpenUrl(prop.getProperty("browserName"));
		Home home=new Home(driver);
		home.clickonmyaccount();
		loginpage= home.Loginclick();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	
	@Test(priority=1, dataProvider ="Validcredentailssupplier")
	public void Verifyloginwithvalidcredentials(String Email, String Password) {
		
		
		loginpage.Enteremailaddress(Email);
		loginpage.Enterpassword(Password);
		loginpage.Clickonloginbutton();
		
		AccountPage accpage=new AccountPage(driver);
		Assert.assertTrue(accpage.Edityourinformation());
		
	}
	@DataProvider(name="Validcredentailssupplier")
	public Object[][] supplydata() {
		
		Object[][] data= Utilities.getTestdatafromExcel("Login");
		return data;
	}
	@Test(priority=2)
	public void VerifyLoginwithInvalidcredentials() {
	
		loginpage.Enteremailaddress(Utilities.Timestamp());
		loginpage.Enterpassword(dataprop.getProperty("InvalidPassword"));
		loginpage.Clickonloginbutton();
				
		String Actualwarningmessage= loginpage.retriveEmailPasswordnotmatchingwarningmessage();
		String Expectedwarningmessage="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(Actualwarningmessage.contains(Expectedwarningmessage), "Not Expected");
			
		}
	@Test(priority=3)
	public void VerifyLoginwithInvalidEmailandValidpassword() {
		
		loginpage.Enteremailaddress(Utilities.Timestamp());
		loginpage.Enterpassword(prop.getProperty("Password"));
		loginpage.Clickonloginbutton();
		
		String Actualwarningmessage=loginpage.retriveEmailPasswordnotmatchingwarningmessage();
		String Expectedwarningmessage=dataprop.getProperty("emailpasswordnotmatching");
		Assert.assertTrue(Actualwarningmessage.contains(Expectedwarningmessage), "Not Expected");
			
		}
	@Test(priority=4)
	public void VerifyLoginwithvalidEmailandInvalidpassword() {
		
		loginpage.Enteremailaddress(prop.getProperty("Email"));
		loginpage.Enterpassword(dataprop.getProperty("InvalidPassword"));
		loginpage.Clickonloginbutton();
		
				
		String Actualwarningmessage=loginpage.retriveEmailPasswordnotmatchingwarningmessage();
		String Expectedwarningmessage=dataprop.getProperty("emailpasswordnotmatching");
		Assert.assertTrue(Actualwarningmessage.contains(Expectedwarningmessage), "Not Expected");
			
		}
	
	@Test(priority=5)
	public void VerifyLoginwithwithoutprovidingcredentials() {
		
		loginpage.Clickonloginbutton();

			
		String Actualwarningmessage=loginpage.retriveEmailPasswordnotmatchingwarningmessage();
		String Expectedwarningmessage=dataprop.getProperty("emailpasswordnotmatching");
		Assert.assertTrue(Actualwarningmessage.contains(Expectedwarningmessage), "Not Expected");
			
		}
	
}
