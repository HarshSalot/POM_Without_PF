package com.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.hubspot.pages.BasePage;
import com.hubspot.pages.HomePage;
import com.hubspot.util.DriverUtil;
import com.hubspot.util.ElementActions;

import io.qameta.allure.Step;



public class LoginPage extends BasePage {

	ElementActions elementActions;
	DriverUtil driverUtil;
	WebDriver driver;
	
	//By Locators
	By emailID = By.id("username");
	By password = By.id("password");
	By loginButton =By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		elementActions = new ElementActions(driver);
		driverUtil = new DriverUtil(driver);
	}
		
	//define page action/methods
		@Step("getting login page title step")
		public String getLoginPageTitle(){
			return driverUtil.getPageTitle();
		}
		
		//verify signup link
		@Step("checking signup link")
		public boolean verifySignUpLink(){
		   return elementActions.elementIsDisplayed(signUpLink);
		}
		
		@Step("login to app with username:{0} and password:{1}")
		public HomePage doLogin(String username, String pass){
			{
				//driver.findElement(emailID).sendKeys(username);
				elementActions.doSendkeys(emailID, username);
				elementActions.doSendkeys(password, pass);
				elementActions.doClick((loginButton));
				return new HomePage(driver);
			}
			
		}
		
		// check all links
		public void getTotalLinks() {
			driverUtil.getLinks();
		}

		// check all links
		public void getBrokenLinks() {
			driverUtil.brokenLinks();
		}
		
		
		
		
		
}
