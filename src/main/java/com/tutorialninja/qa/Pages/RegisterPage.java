package com.tutorialninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstname;
	@FindBy(id="input-lastname")
	private WebElement lastname;
	@FindBy(id = "input-email")
	private WebElement email;
	@FindBy(id = "input-telephone")
	private WebElement telephone;
	@FindBy(id = "input-password")
	private WebElement password;
	@FindBy(id = "input-confirm")
	private WebElement confirmpassword;
	@FindBy(name = "agree")
	private WebElement privacyploicy;
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continuebtn;
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement Newsletter;
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement EmailWarningmessage;
	
	public RegisterPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	public void enterfirstname(String fname) {
		firstname.sendKeys(fname);
	}
	
	public void enterlastname(String lname) {
		lastname.sendKeys(lname);
	}
	public void enteremail(String Email) {
		email.sendKeys(Email);
	}
	public void entertelephone(String telephn) {
		telephone.sendKeys(telephn);
	}
	
	public void enterpassword(String pwd) {
		password.sendKeys(pwd);
	}
	public void enterconfirmpassword(String cpwd) {
		confirmpassword.sendKeys(cpwd);
	}
	
	public void privacypolicyclick() {
		privacyploicy.click();
	}
	
	public void continueclick() {
		continuebtn.click();
		
	}
	public void newsletterclick() {
		Newsletter.click();
	}
	public String emailwarningmessageshowing() {
		String emailwarmessage=EmailWarningmessage.getText();
		return emailwarmessage;
	}
	
}
