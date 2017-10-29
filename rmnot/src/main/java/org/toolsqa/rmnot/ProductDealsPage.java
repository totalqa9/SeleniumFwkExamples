package org.toolsqa.rmnot;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDealsPage {

	WebDriver driver;
	Logger logger;
	public ProductDealsPage(WebDriver driver) {
		this.driver = driver;
		logger=Logger.getLogger("ProductDealsPage");
        PropertyConfigurator.configure("log4j.properties");
	}
	public void navigatetoCategoryName(String categoryName)
	{	 
		logger.info("Category Name :: "+ categoryName);
		//Inserting 30 seconds
	    Utility.fluentWaitFindElement(driver, 30, By.xpath("(//a[@href='#"+categoryName+"'])[2]"));
				
		driver.findElement(By.xpath("(//a[@href='#"+categoryName+"'])[2]")).click();
	}
	public int getCategoryCount(String categoryName)
	{
		 
		List<WebElement> itemsList = driver.findElements(By.xpath(".//*[@id='"+categoryName+"']/div/div"));
		logger.info("Items List :: "+  itemsList.size());
		return itemsList.size();
	}
}
