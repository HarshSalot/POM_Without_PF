package com.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.hubspot.pages.BasePage;
import com.hubspot.pages.HomePage;
import com.hubspot.pages.LoginPage;
import com.hubspot.util.Constants;
import com.hubspot.util.TimeUtil;

public class HomePageTest{
	
WebDriver driver;
BasePage basePage;
Properties prop;
LoginPage loginPage;
HomePage homePage;

@BeforeMethod
public void setUp(){
	basePage = new BasePage();
	prop = basePage.init_prop();
	driver = basePage.init_driver(prop);
	driver.get(prop.getProperty("url"));
	loginPage = new LoginPage(driver);
	TimeUtil.mediumWait();
	homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	TimeUtil.mediumWait();
}

@Test(priority=1, enabled=false)
public void verifyHomePageTitle(){
	String title = homePage.getHomePageTitle();
	System.out.println("home page title is: "+ title);
	Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
}


@Test(priority=3, enabled=false)
public void verifyLoggedInUserAccountNameTest(){
	String accountName = homePage.getLoggedInAccountValue();
	System.out.println("Logged in user account name is: "+ accountName);
	Assert.assertEquals(accountName, prop.getProperty("accountName"));
}

@Test
public void getBrokenLinkTest(){
	homePage.getTotalLinks();
}


@AfterMethod
public void tearDown(){
	driver.quit();
}


}
