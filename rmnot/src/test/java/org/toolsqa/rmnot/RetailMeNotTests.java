package org.toolsqa.rmnot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import jxl.read.biff.BiffException;

public class RetailMeNotTests {
	WebDriver driver;
	HomePage h1  ;
	GiftCardDealsPage g1 ;
	ProductDealsPage p1 ;
	String url;
	 Logger logger;
	static Properties pro;
	@Parameters({"url","browserType"})
	@BeforeClass
	public void invokeBrowser(String url,String browserType) throws IOException
	{
        
		logger=Logger.getLogger("RetailMeNotTests");
        PropertyConfigurator.configure("log4j.properties");
        
        File src=new File("rm.properties");

        // Create  FileInputStream object 
        FileInputStream fis=new FileInputStream(src);

        // Create Properties class object to read properties file
          pro=new Properties();

        // Load file so we can use into our script 
        pro.load(fis);

		if(browserType.contentEquals("FF"))
		{
			logger.info("Instantiating FF Browser");
			System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
			logger.info("Browser Opened Successfully");
		 
		}
		else if(browserType.equals("CH"))
		{
			logger.info("Instantiating FF Browser");
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
			logger.info("Browser Opened Successfully");
			 
		}
		else
		{
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			 
		}
		h1 = new HomePage(driver);
		this.url=url;

	}
	@DataProvider(name="DP")
	public String[][] feedDP() throws BiffException, IOException
	{
		String data[][] = Utility.readXlsFile("deals.xls", "sanity");
		return data;
	}

	@Test(description="Verify count for Electronics",priority=2)
	public void verifyCategoryCount() throws InterruptedException
	{
		driver.get(url);
		g1= h1.clickGiftCardDeals();
		p1= g1.browseProductDeals();
		p1.navigatetoCategoryName("Electronics");
		int actual = p1.getCategoryCount("Electronics");
		int expected = 18;
		Assert.assertEquals(actual, expected);

	}
	@Parameters({"title"})
	@Test(priority=1)
	public void verifyTitle(String title)
	{
		driver.get(url);
		String expected = title;
		String actual = driver.getTitle();
		System.out.println("Expected Title::" +  title);
		System.out.println("Actual Title :::" + actual);
		Assert.assertTrue(expected.contains(actual),"Expected Title not matching with Actual Title");

	}

	@Test(priority=3,dataProvider="DP")
	public void verifyDealsCount(String categoryLink,String categoryName,String categoryCount) throws InterruptedException, IOException
	{
		driver.get(url);
		g1= h1.clickGiftCardDeals();
		p1= g1.browseProductDeals();
		p1.navigatetoCategoryName(categoryLink);
		int actual = p1.getCategoryCount(categoryName);
		int expected = Integer.parseInt(categoryCount);
		Utility.takeScreenshot(driver);
		Assert.assertEquals(actual, expected);

	}

	@AfterClass
	public void closeBrowser()
	{
		//driver.close();
	}

}