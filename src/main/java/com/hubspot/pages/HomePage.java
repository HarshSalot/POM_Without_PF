package com.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hubspot.pages.BasePage;
import com.hubspot.util.Constants;


public class HomePage extends BasePage{

	WebDriver driver;
	
	//By Locators
	By homePageHeader = By.xpath("//h1[@class='private-page__title']");
	By accountName = By.xpath("//span[@class='account-name ']");
	By ContactsHoverMenu = By.id("nav-primary-contacts-branch");
	By ContactsPage = By.id("nav-secondary-contacts");
		
		
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public String getHomePageTitle(){
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.titleContains(Constants.HOME_PAGE_TITLE));
		return driver.getTitle();
	}
	
	public String getHomePageHeader(){
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(homePageHeader));
		return driver.findElement(homePageHeader).getText();
	}
	
	public String getLoggedInAccountValue(){
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(accountName));
		return driver.findElement(accountName).getText();
	}
	

}

