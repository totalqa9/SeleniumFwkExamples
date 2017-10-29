package org.toolsqa.rmnot;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class GiftCardDealsPage {
	 Logger logger;
	WebDriver driver;
	public GiftCardDealsPage(WebDriver driver) {
		this.driver=driver;
		logger=Logger.getLogger("GiftCardDealsPage");
        PropertyConfigurator.configure("log4j.properties");
	}
	public ProductDealsPage browseProductDeals() throws InterruptedException
	{
		//Inserting 30 seconds
		Utility.fluentWaitFindElement(driver, 30, By.xpath(".//*[@id='top']/div/nav/ul/li[1]/div/div/a"));
		logger.info("Browsing Product Deals");
		Actions action = new Actions(driver);
		logger.info("Property value fetched:: " + RetailMeNotTests.pro.getProperty("rm.browsercoupons.xpath"));
		action.moveToElement(driver.findElement(By.xpath(RetailMeNotTests.pro.getProperty("rm.browsercoupons.xpath"))));
		action.moveToElement(driver.findElement(By.xpath(RetailMeNotTests.pro.getProperty("rm.productdeals.xpath"))));
		action.click();
		action.perform();
		 
		ProductDealsPage p1 = new ProductDealsPage(driver);
		return p1;
	}
}
