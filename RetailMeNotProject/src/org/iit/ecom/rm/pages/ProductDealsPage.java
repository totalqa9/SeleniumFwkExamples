package org.iit.ecom.rm.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDealsPage {
	//By default int contains zero
	//Ref Variable for a class/interface contains null..
	WebDriver driver;//opens a new browser
	Logger log;
	public ProductDealsPage(WebDriver driver2) {
		 driver=driver2;
		 log = Logger.getLogger("ProductDealsPage.class");
		 PropertyConfigurator.configure("Log4j.properties");
	}
	public void clickCategoryLink(String categoryName)
	{	
		log.info("Click on the categoryName::"+  categoryName);
		//"+categoryName+"
		driver.findElement(By.xpath("(//a[@href='#"+categoryName+"'])[2]")).click();
	}
	public int getCategoryCount(String categoryName)
	{
		List<WebElement> list = driver.findElements(By.xpath(".//*[@id='"+categoryName+"']/div/div"));
		log.info("Number of items in "+categoryName);
		return list.size();
	}

}
