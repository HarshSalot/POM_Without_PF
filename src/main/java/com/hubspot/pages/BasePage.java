package com.hubspot.pages;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePage {
	public WebDriver driver;
	public Properties prop;
	public static ThreadLocal<WebDriver>tldriver = new ThreadLocal<WebDriver>();


/**
 * 
 * @param browser
 * @return driver onthe basis of browser
 */

public WebDriver init_driver(Properties prop){
	
	String browser = prop.getProperty("browser");
	
	if(browser.equals("chrome"))
	{ //Launch the Browser:
		//1. Chrome
	     System.setProperty("webdriver.chrome.driver", "E:/CSAT/QA Automation/Software/chromedriver.exe");
	     tldriver.set(new ChromeDriver());
	}
	else if(browser.equals("firefox"))
	{
		 //Firefox
		System.setProperty("webdriver.gecko.driver","E:/CSAT/QA Automation/Software/geckodriver.exe");
		//driver = new FirefoxDriver();
		tldriver.set(new FirefoxDriver());
	}
	/* else if (browser.equals("IE")) 
	{
		// IE
		System.setProperty("webdriver.ie.driver","E:/CSAT/QA Automation/Software/IEDriverServer.exe");
		driver = new InternetExplorerDriver();
	}
	*/
	else
	{
		System.out.println(browser+" -->not defined");
	}
	
	//getDriver().manage().window().fullscreen();
	getDriver().manage().deleteAllCookies();
	getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageLoadtimeout")), TimeUnit.SECONDS);
	
	return getDriver();
}

public static synchronized WebDriver getDriver(){
	return tldriver.get();
}
/**
 * will load the config.properties
 * @return prop object 
 */

public Properties init_prop(){
	prop = new Properties();
	try {
		FileInputStream ip = new FileInputStream("E:/CSAT/QA Automation/workspace/POM_Without_PF/src/main/java/com/hubspot/config/config.properties");
		prop.load(ip);  
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return prop;
}

public String getScreenshot() {
	File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
	File destination = new File(path);
	try {
		FileUtils.copyFile(src, destination);
	} catch (IOException e) {
		System.out.println("Capture Failed " + e.getMessage());
	}
	return path;
}


}
