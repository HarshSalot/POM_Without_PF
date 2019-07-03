package com.hotspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.hubspot.listeners.TestAllureListener;
import com.hubspot.pages.BasePage;
import com.hubspot.pages.HomePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;
import com.hubspot.util.TimeUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners({TestAllureListener.class})
public class LoginPageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage; 
	
	@BeforeMethod
	public void setup(){
		basePage = new BasePage();
		prop = basePage.init_prop();
		driver = basePage.init_driver(prop);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		TimeUtil.mediumWait();
	}
	
	@Test(priority=1, description="verifying login page title", enabled=false)
	@Severity(SeverityLevel.NORMAL)
	@Description("Check login page title is correct or not")
	public void verifyLoginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2, description="Verify Login page title",enabled=false) 
	@Severity(SeverityLevel.CRITICAL)
	@Description("Check login page title is correct or not")
	public void verifySignUpLinkTest()
	{
		AssertJUnit.assertTrue(loginPage.verifySignUpLink());
	}
	
	@Test(priority=3, description="Verify Login page title",enabled=false)
	public void hubSpotLoginTest(){
		loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		AssertJUnit.assertEquals(homePage.getHomePageTitle(), Constants.HOME_PAGE_TITLE);
		//Assert.assertEquals(homePage.getHomePageHeader(), Constants.HOME_PAGE_HEADER);
	}
	
	@Test
	public void getAllLinkTest(){
		loginPage.getTotalLinks();
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}

}
