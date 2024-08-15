package com.tutorialsninja.qa.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialninja.qa.Pages.SearchPage;
import com.tutorials.qa.Baseclass.Base_Class;

public class SearchTest extends Base_Class {
	public SearchTest() {
		super();
	}
	
	public WebDriver driver;
	SearchPage searcfield;
	@BeforeMethod
	public void Setup() {
		
		driver=InitializeBrowserAndOpenUrl(prop.getProperty("browserName"));
		
	}
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	@Test(priority=1)
	public void VerifySearchWithValidProduct() {
		
		SearchPage searchpage=new SearchPage(driver);
		searchpage.searchfield(dataprop.getProperty("Validproduct"));
		searchpage.searchbutton();
				
		Assert.assertTrue(searchpage.product1());
		
	}
	@Test(priority=2)
	public void VerifySearchWithInValidProduct() {
		
		SearchPage searchpage=new SearchPage(driver);
		searchpage.searchfield(dataprop.getProperty("Invalidproduct"));
		searchpage.searchbutton();
		
		String Actualdisplaymessage= searchpage.searchproductdisplaymessage();
//		Assert.assertEquals(Actualdisplaymessage, dataprop.getProperty("Noproducttext"),"Default message has not showing");
		Assert.assertEquals(Actualdisplaymessage, "abc","Default message has not showing");

	}
	
	
	@Test(priority=3, dependsOnMethods = {"VerifySearchWithInValidProduct", "VerifySearchWithValidProduct" })
	public void VerifySearchWithoutAnyProduct() {
		
		SearchPage searchpage=new SearchPage(driver);

		searchpage.searchfield("");
		searchpage.searchbutton();
		String Actualdisplaymessage= searchpage.searchproductdisplaymessage();
		Assert.assertEquals(Actualdisplaymessage, dataprop.getProperty("Noproducttext"),"Default message has not showing");
	}

}
