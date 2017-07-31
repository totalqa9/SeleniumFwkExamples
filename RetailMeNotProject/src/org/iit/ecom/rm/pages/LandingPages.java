package org.iit.ecom.rm.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.iit.ecom.rm.tests.RMTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPages {
	
 
	WebDriver driver;
	Logger log;
	public LandingPages(WebDriver driver) {//parameterized constructor
		this.driver = driver;
		log = Logger.getLogger("LandingPages.class");
		PropertyConfigurator.configure("Log4j.properties");

	}
	public void handlePopUp()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scroll(200,100)");
		jse.executeScript("window.scroll(0,400)");
		jse.executeScript("window.scroll(200,100)");
		log.info("Scrolling the window");
		try{
			//handle the popup
			driver.findElement(By.xpath(".//*[@id='top']/div/nav/ul/li/div/div/a")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Thanks')]")).click();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			log.error("Pop-up is not displayed"+ e.getMessage());
		}
	}
	public void browseDeals(String subMenuItem)
	{
		System.out.println("Key value getting from property file:::"+ RMTests.prop.getProperty("rm.landingpage.submenuitem"));
		driver.findElement(By.xpath(RMTests.prop.getProperty("rm.landingpage.submenuitem"))).click();
		//driver.findElement(By.xpath(".//*[@id='top']/div/nav/ul/li/div/div/ul/li[5]/a")).click();
		
		List<WebElement> list=driver.findElements(By.xpath(".//*[@id='top']/div/nav/ul/li/div/div/ul/li/a"));
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getText());
			if(list.get(i).getText().contains(subMenuItem))
			{
				list.get(i).click();
				System.out.println("in submenu" + subMenuItem);
				break;
			}
		}
		
		
		
		
		
		
		
		
		
		
		
	 
	}

}
