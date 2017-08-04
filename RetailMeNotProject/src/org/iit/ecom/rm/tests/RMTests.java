package org.iit.ecom.rm.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.iit.ecom.rm.pages.LandingPages;
import org.iit.ecom.rm.pages.ProductDealsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RMTests {
	WebDriver driver;
	Logger log;
	public static Properties prop;
	@BeforeClass
	public void invokeBrowser() throws IOException
	{
		//Fetching the  object for logger
		log = Logger.getLogger("RMTests.class");
		PropertyConfigurator.configure("Log4j.properties");
		File obj1 = new File("RM.properties");
		FileInputStream obj2 = new FileInputStream(obj1);
		prop = new Properties();
		prop.load(obj2);
		
//
//		if(BrowserType.equals("FF") && location.eqau)
//		{
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			//write logic to load property file for firefox
	//	}
			
		driver.get("http://www.retailmenot.com");
		log.info("Retailmenot Not PAge is opened successfully");
	}
	@Test
	public void verifyCategoryCount()
	{
		try{
			LandingPages lPage = new LandingPages(driver);
			log.info("Creating an object for landing Pages");
			//lPage.handlePopUp();
			log.info("Handling Popup Successfully");
			lPage.browseDeals("Product Deals");
			log.info("Browse Product Deals");
			ProductDealsPage dealsPage = new ProductDealsPage(driver);
			dealsPage.clickCategoryLink("Electronics");
			log.info("Clickin on Electronics link");
			dealsPage.getCategoryCount("Electronics");
			log.info("Get Count in Electronics");
		}
		catch(Exception e)
		{
			log.error("Exception occured::" +e.getMessage());
		}
	}

}
