package com.tutorialninja.qa.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver diver;
	
	@FindBy(name = "search")
	private WebElement Searchfield;
	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement Searchbutton;
	@FindBy (linkText = "HP LP3065")
	private WebElement Product1;
	@FindBy(xpath = "//div[@id='content']/h2/following::p")
	private WebElement Searchdisplaymessage;
	
	public SearchPage(WebDriver driver) {
		this.diver =driver;
		PageFactory.initElements(driver, this);
	}
	
	public void searchfield(String enterdata) {
		Searchfield.sendKeys(enterdata);
	}
	
	public SearchPage searchbutton() {
		Searchbutton.click();
		return new SearchPage(diver);
		
		
	}
	public boolean product1() {
		boolean product1display = Product1.isDisplayed();
		return product1display;
	}
	public String searchproductdisplaymessage() {
		String searchmessage = Searchdisplaymessage.getText();
		return searchmessage;
	}

}
