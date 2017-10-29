package org.tq.maven;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

 
/**
 * Unit test for simple App.
 */
public class AppTest 
{
	Logger logger;
	@BeforeClass
	public void display()
	{
		logger=Logger.getLogger(AppTest.class);

		PropertyConfigurator.configure("Log4j.properties");

	}
	@Test
	public void verifyTitle()
	{
		logger.info("Executing the test for verify Title");
	}
    
}
