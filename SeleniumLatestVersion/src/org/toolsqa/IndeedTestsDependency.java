package org.toolsqa;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IndeedTestsDependency {
	
	WebDriver driver;
	@Parameters({"url","location","jobType","browserType"})
	@Test(description="Performing job search")
	public void performJS(String url,String location,String jobType,String browserType)
	{
		if(browserType.equals("FF"))
		{
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserType.equals("IE"))
		{
			/**
			 * Pre-conditions
			 * 1. zoom should be set to 100%
			 * 2. All the 4 security zones should be in protected mode
			 */
			System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		else
		{		 
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver();
		}
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Enter the url
		driver.get(url);
		
		 WebElement e = driver.findElement(By.id("what"));
		 driver.findElement(By.id("what")).sendKeys(jobType);
		
		driver.findElement(By.id("where")).clear();
		driver.findElement(By.id("where")).sendKeys(location);
		
		driver.findElement(By.id("fj")).click();
		//Verify the title of the next page
		 
		String actual = driver.getTitle();
		Assert.assertTrue(actual.contains(location));
		
	}
	@Parameters({"location"})
	@Test(dependsOnMethods={"performJS"},alwaysRun=true)
	public void averifyLocation(String location)
	{
		String loc  = driver.findElement(By.className("location")).getText();
		boolean result = loc.contains(location);
		Assert.assertTrue(result);
	}
	@Parameters({"jobType"})
	@Test(dependsOnMethods={"performJS"},alwaysRun=true)
	public void verifySummary(String jobType)
	{
		String summary = driver.findElement(By.className("summary")).getText();
	   boolean result = summary.contains(jobType);
	  Assert.assertTrue(result);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
