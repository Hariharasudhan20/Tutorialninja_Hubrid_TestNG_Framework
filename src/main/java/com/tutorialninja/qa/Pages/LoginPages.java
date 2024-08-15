package com.tutorialninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPages {
	
	WebDriver driver;
		
	@FindBy(id="input-email")
	private WebElement Email;
	@FindBy(id="input-password")
	private WebElement Pwd;
	@FindBy(xpath="//input[@value='Login']")
	private WebElement Logbtn;
	@FindBy(xpath="//div[contains(@class, 'alert-dismissible')]")
	WebElement AccWarningmessage;
	
	public LoginPages(WebDriver driver) {
	
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void Enteremailaddress(String Emailadress) {
		Email.sendKeys(Emailadress);
	}
	public void Enterpassword(String Password) {

	    Pwd.sendKeys(Password);
	
	}
	public void Clickonloginbutton() {
		
		Logbtn.click();
		
	}
	public String retriveEmailPasswordnotmatchingwarningmessage() {
		String actualwarningmessage= AccWarningmessage.getText();
		return actualwarningmessage;
	}
	
	
	
}
