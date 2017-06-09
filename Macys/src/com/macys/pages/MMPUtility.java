package com.macys.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MMPUtility {

	public static void navigateToTab(String tabName,WebDriver driver) throws InterruptedException{

		System.out.println("in navigation methods:" + tabName);
		List<WebElement> list = driver.findElements(By.xpath("//span[contains(text(),'"+ tabName +"')]"));
		Thread.sleep(10000);
		try {
			for(int i=0;i<list.size();i++){
				
				if(list.get(i).getText().trim().equals(tabName)){
					
					list.get(i).click();
					Thread.sleep(2000);
					//System.out.println("Selected: " + tabName);						
				}
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
