package com.hubspot.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.hubspot.pages.BasePage;

public class DriverUtil extends BasePage {
	WebDriver driver;
	
	public DriverUtil (WebDriver driver){
		this.driver = driver;
	}
	
	public String getPageTitle(){
		String title = null;
		try {
			title = driver.getTitle(); 
		} catch (Exception e) {
			System.out.println("Exception occured with title");
		}
		return title;
	}
	
			//check all links
			public void getLinks(){
				List<WebElement> linkList = driver.findElements(By.tagName("a"));
				linkList.addAll(driver.findElements(By.tagName("img")));
				int linkCount = linkList.size();
				
				System.out.println(linkCount);
				
				List<WebElement> activeLinks = new ArrayList<WebElement>();
				for (int i = 0; i < linkCount; i++) {
					String linkText = linkList.get(i).getText();
					System.out.println(linkText);
					
					if (linkList.get(i).getAttribute("href")!=null) {
						activeLinks.add(linkList.get(i));
						//System.out.println(activeLinks.get(3).getText());	
					}
					
					
				}
				System.out.println(activeLinks.size());
				
				/*for (int i = 0; i < activeLinks.size(); i++) {
					(HttpsURLConnection)new URL(activeLinks.get(i).getAttribute("href")).openConnection();
				}*/	
			}
			
			
}
