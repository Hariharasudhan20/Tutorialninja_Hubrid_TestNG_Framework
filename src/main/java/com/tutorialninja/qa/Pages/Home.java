package com.tutorialninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myaccountmenu;
	
	@FindBy(linkText="Login")
	private WebElement Loginbtn;
	
	@FindBy(linkText = "Register")
	WebElement Register;
	
	
	public Home(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	//Actions
	public void clickonmyaccount() {
		
		myaccountmenu.click();
	}
	
	public LoginPages Loginclick() {
		
		Loginbtn.click();
		return new LoginPages(driver);
	}
	
	public RegisterPage Registerclick() {
		Register.click();
		return new RegisterPage(driver);
	}
}
