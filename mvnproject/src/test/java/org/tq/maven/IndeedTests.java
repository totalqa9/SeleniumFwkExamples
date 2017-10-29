package org.tq.maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
public class IndeedTests {

	//	Parameterization of TC's
	//	Execution of the TC's against multiple browsers
	//	Execution of TC parallely..

	WebDriver driver;
	Logger logger;

	@Parameters({"url","location","jobType","browserType"})
	@Test(description="Verifying the Search Functionality")
	public void verifyJobSearchFunc(String url,String location,
			String jobType,String browserType) throws IOException
	{

		logger=Logger.getLogger(IndeedTests.class);

		PropertyConfigurator.configure("Log4j.properties");

		logger.info("Executing the Tests against ::"+ browserType);



		switch(browserType)
		{
		case "FF":
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");

			// Create  FileInputStream object 
			FileInputStream fis=new FileInputStream(new File("OR_FF.properties"));

			// Create Properties class object to read properties file
			Properties pro=new Properties();

			// Load file so we can use into our script 
			pro.load(fis);
			driver = new FirefoxDriver();
			logger.info("Browser Opened successfully");
			break;
		case "IE":
			/*
			 * 1. zoom should be 100%
			 * 2. Protected mode should be enabled for all the security zones.
			 */
			System.setProperty("webdriver.ie.driver","IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		case "CH": 
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();

			break;
		default:
			System.out.println("Please provide valid values :: IE,FF,CH");
			break;

		}



		driver.get(url);
		logger.info("Opening the browser with the url::"+ url);
		driver.findElement(By.id(pro.getProperty("indeed.txtbox.what.id"))).clear();
		driver.findElement(By.id(pro.getProperty("indeed.txtbox.what.id"))).sendKeys(jobType);
		driver.findElement(By.id(pro.getProperty("indeed.txtbox.where.id"))).clear();
		driver.findElement(By.id(pro.getProperty("indeed.txtbox.where.id"))).sendKeys(location);
		driver.findElement(By.id(pro.getProperty("indeed.button.fj.id"))).click();

		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fj")));
		int actual=0;
		List<WebElement> summaryList = driver.findElements(By.xpath("//span[@class='summary']"));
		for(int i=0;i<summaryList.size();i++)
		{
			System.out.println("**********"+ summaryList.get(i).getText()+"**********");

			if(summaryList.get(i).getText().contains("Selenium"))  
			{
				actual++;
			}
		}
		int expected = 10;
		Assert.assertEquals(actual, expected);	

	}


}
