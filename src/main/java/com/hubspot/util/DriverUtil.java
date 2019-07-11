package com.hubspot.util;

import java.io.IOException;
import java.net.MalformedURLException;
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
	List<WebElement> linkList;

	public DriverUtil(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		String title = null;
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			System.out.println("Exception occured with title");
		}
		return title;
	}

	// check all links
	public void getLinks() {
		linkList = driver.findElements(By.tagName("a"));
		linkList.addAll(driver.findElements(By.tagName("img")));
		int linkCount = linkList.size();
		System.out.println(linkCount);

		for (int i = 0; i < linkCount; i++) {
			//String linkText = linkList.get(i).getText();
			//System.out.println(linkText);
			System.out.println("Java Script <---"+linkList.get(i).getAttribute("href"));
			}
         
	}

	public void brokenLinks() {
		
		getLinks();

		List<WebElement> activeLinks = new ArrayList<WebElement>();

		for (int i = 0; i < linkList.size(); i++) {
			if (linkList.get(i).getAttribute("href") != null) {
				activeLinks.add(linkList.get(i));
				// System.out.println(activeLinks.get(3).getText());
				
			}
		}

		for (int j = 0; j < activeLinks.size(); j++) {
			try {
				
				HttpsURLConnection connection = (HttpsURLConnection) new URL(activeLinks.get(j).getAttribute("href"))
						.openConnection();
				connection.connect();
				String response = connection.getResponseMessage();
				if (response.equals(null)|| (response.equals("Not Found")))
				{
					System.out.println("Broken Links <---"+activeLinks.get(j).getAttribute("href") + "--->" + response);
				}
				else if ((activeLinks.get(j).getAttribute("href").contains("javascript"))){
					System.out.println("Java Script <---"+activeLinks.get(j).getAttribute("href").contains("javascript") + "--->");
				}
				connection.disconnect();
				//System.out.println(activeLinks.get(j).getAttribute("href") + "--->" + response);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
