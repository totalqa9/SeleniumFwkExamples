package seleniumexamples;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ListboxExample {
    WebDriver driver;
	@Test
	public void listboxExample()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("file:///C:/SeleniumDocs/HTML/ListboxEx.html");
		driver.manage().window().maximize();
		
		WebElement countryWE = driver.findElement(By.id("country"));
		Select countrySelect = new Select(countryWE);
		
		//Index
		countrySelect.selectByIndex(2);
		
		//Value
		countrySelect.selectByValue("india");
		
		//VisibleText
		countrySelect.selectByVisibleText("INDIA");
		
		List<WebElement> optionList = countrySelect.getOptions();
		for(int i=0;i<optionList.size();i++)
		{
			System.out.println(optionList.get(i).getText());
			if(optionList.get(i).getText().equals("GERMANY"))
			{
				optionList.get(i).click();
				break;
			}
		}
		
		boolean result = countrySelect.isMultiple();
		Assert.assertTrue(result);
		
		
		
	}

}
