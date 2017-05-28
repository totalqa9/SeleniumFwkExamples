package org.toolsqa;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Gmail {
 

		public static void main(String[] args) {
		FirefoxDriver driver=new FirefoxDriver();
		driver.get("http://www.gmail.com");
	 	driver.findElement(By.id("Email")).sendKeys("thailandpics20@gmail.com");
		driver.findElement(By.id("next")).click();
			
		driver.findElement(By.id("Passwd")).sendKeys("");
		driver.findElement(By.id("signIn")).click();
		
		Options opt = driver.manage();
		Timeouts tOut=		  opt.timeouts();
		tOut.implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			
		}

 
}

 
 