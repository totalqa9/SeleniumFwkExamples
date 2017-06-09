package com.macys.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PatientHomePage {

	WebDriver driver;
	public boolean clickViewHistoryButton() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver,60);

		//using explicitly wait command for given webElement
		WebElement viewHistoryButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='View History']")));
		//Clicking on viewhiStorybutton
		viewHistoryButtonElement.click();

		System.out.println("GOING INTO SLEEP...");
		Thread.sleep(20000);
		System.out.println("OUT OF SLEEP..");

		//here we are using wait command
		WebElement pastAppointmentButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Past Appointments']")));
		pastAppointmentButton.sendKeys(Keys.ENTER);
		WebElement patientPortal = wait .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".panel-title")));
		return patientPortal.isDisplayed();
	}	
	public PatientHomePage(WebDriver driver) {
		this.driver = driver;
	}
	public String getFirstName(){
		String fName = driver.findElement(By.id("fname")).getAttribute("value");
		//System.out.println("\nfName: "+ fName);
		return fName;
	}
	public String getLastName(){
		String lName = driver.findElement(By.id("lname")).getAttribute("value");
		//System.out.println("lName: " + lName);
		return lName;
	}
	public boolean verifyViewHistoryOption(String option){
		  boolean result = driver.findElement(By.cssSelector("input[value='" + option + "']")).isDisplayed();
		  return result;
	}
	public String getLicense(){
		String license = driver.findElement(By.id("licn")).getAttribute("value");
		//System.out.println("license: " +license);
		return license;
	}
	public String getSSN(){
		String SSN = driver.findElement(By.id("ssn")).getAttribute("value");
		//System.out.println("SSN: " + SSN);
		return SSN;
	}
	public String getZipCode(){
		String zipCode = driver.findElement(By.id("zip")).getAttribute("value");
		//System.out.println("zipCode: " + zipCode+ "\n");
		return zipCode;
	}

	public void clickCreateNewAppointmentButton(){
		driver.findElement(By.xpath("//input[@type='submit']")).sendKeys(Keys.ENTER); // Create New Appointment Button
	}
	public void selectDoctor(String doctor){
		//driver.findElement(By.cssSelector(".table>tbody>tr>td:nth-of-type(4)>button#opener")).click(); // Selecting 4th dr to book Appointment 
		// Xpath to click on <appointment> button as per the given Dr: "//h4[contains(text(),'Dr.Charlie')]/ancestor::ul/following-sibling::button"
		driver.findElement(By.xpath("//h4[contains(text(),'"+ doctor +"')]/ancestor::ul/following-sibling::button")).click();
	}
	public void enterAppointmentDate(String date){
		driver.switchTo().frame("myframe");
		driver.findElement(By.cssSelector("#datepicker")).sendKeys(date);
		driver.findElement(By.cssSelector("#datepicker")).sendKeys(Keys.ENTER);
	}
	public void selectAppointmentTime(String tim){
		WebElement dropdown = driver.findElement(By.id("time"));
		Select select = new Select(dropdown);
		String st="11Am";
		List<WebElement> list=select.getAllSelectedOptions();
		for(WebElement li : list)
		{
			if(li.getText().equalsIgnoreCase(st))
				select.selectByValue(tim);
			break;
		}
	}
	public void clickContinueButton(){
		driver.findElement(By.cssSelector("#ChangeHeatName")).click();//Click on <Continue> button
	}
	public void enterSymptom(String symptom){
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("#sym")).sendKeys(symptom);
	}
	public void clickSubmitButton(){
		driver.findElement(By.cssSelector(".panel.panel-cascade>div>input")).click(); //Submit button
	}
	public boolean verifySelectedTime(String tim){
		boolean result = driver.findElement(By.id("time")).isDisplayed();
		//System.out.println(result); 
		return result;
	}
	public boolean verifyEnteredSymptom(String symptom){
		boolean result = driver.findElement(By.xpath("//table/tbody/tr/td[3][contains(text(),'"+ symptom +"')]")).isDisplayed();
		//System.out.println(result); 
		return result; 
	}
	public boolean verifySelectedDoctor(String doctor){
		String dr[] = doctor.split("\\."); // Dr.Richard
		String drName = dr[1];
		boolean result = driver.findElement(By.xpath("//table/tbody/tr/td[4][contains(text(),'"+ drName +"')]")).isDisplayed();
		//System.out.println(result); 
		return result; 
	}
}
