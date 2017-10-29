package org.toolsqa.rmnot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Utility {

	public static WebElement fluentWaitFindElement(WebDriver driver,long time,By locator)
	{

        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(time, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(Exception.class);
        WebElement e =  wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return e;
	}

	public static String[][] readXlsFile(String fileName,String sheetName) throws BiffException, IOException {
		//IOException
		//IOException io = new FileNotFoundException();
		File f = new File(fileName);
		//fetch the workbook
		Workbook wb = Workbook.getWorkbook(f);
		//fetch the sheet
		Sheet sheet = wb.getSheet(0);//index as zero
	  //Sheet sheet = wb.getSheet(sheetName);
		//fetch the rows and cols
		int rows = sheet.getRows();
		int cols = sheet.getColumns();
		String data[][] = new String[rows-1][cols];
		for(int i =1 ;i<rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				Cell cell = sheet.getCell(j, i);
				String str = cell.getContents();
				System.out.println("Contents::::  " + str);
				data[i-1][j]=str;
			}
		}
		return data;
	}
	public static void takeScreenshot(WebDriver driver) throws IOException
	{
		SimpleDateFormat sdf =new  SimpleDateFormat("YYYY-MM-dd-HH-MM-SS");
		String date = sdf.format(new Date());
		System.out.println(date);
		
//		Calendar cal = Calendar.getInstance()
//		long time = cal.getTimeInMillis();
		
		//TakesScreenshot Logic Starts
		//TakesScreenshot is an interface implemented by FirefoxDriver
		TakesScreenshot tsh = (TakesScreenshot) driver;
		File srcFile = tsh.getScreenshotAs(OutputType.FILE);
		System.out.println("Path to the File:: "+ srcFile.getAbsolutePath());
	
		//Copy the file from one location to another location
		File destFile = new File("C:\\tq\\ScreenshotEx"+date+".jpg");
		FileUtils.copyFile(srcFile, destFile);
	}
}
