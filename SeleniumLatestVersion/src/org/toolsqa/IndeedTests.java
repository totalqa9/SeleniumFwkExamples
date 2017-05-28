package org.toolsqa;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IndeedTests {
	
	FirefoxDriver driver;
	@Test(priority=1)
	public void performJS()
	{
		
		System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
		driver = new FirefoxDriver();
		//Enter the url
		driver.get("https://www.indeed.com");
		
		 WebElement e = driver.findElement(By.id("what"));
		 driver.findElement(By.id("what")).sendKeys("selenium");
		
		driver.findElement(By.id("where")).clear();
		driver.findElement(By.id("where")).sendKeys("chennai");
		
		//driver.findElement(By.id("fj")).click();
		//Verify the title of the next page
		String expected = "Selenium Jobs, Recruitment in Bangalore, Karnataka | Indeed.co.in";
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		
	}
	@Test(priority=2)
	public void averifyLocation()
	{
		String location = driver.findElement(By.className("location")).getText();
		boolean result = location.contains("Bangalore");
		Assert.assertFalse(result);
	}
	@Test(priority=3)
	public void verifySummary()
	{
		String summary = driver.findElement(By.className("summary")).getText();
	   boolean result = summary.contains("Selenium");
	  Assert.assertTrue(result);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
