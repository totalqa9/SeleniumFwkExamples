package org.toolsqa.rmnot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy(how =How.XPATH,using=".//*[@id='top']//span[text()='Gift Card Deals']")
	public WebElement giftWE;
	
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	public GiftCardDealsPage clickGiftCardDeals()
	{

		//WebElement e = driver.findElement(By.xpath(".//*[@id='top']//span[text()='Gift Card Deals']"));
		giftWE.click();
		GiftCardDealsPage g1 = new GiftCardDealsPage(driver);
		return g1;

	}
}
