package totalqa;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverTests {
	public static void main(String[] args) 
	{
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://total-qa.com");
		System.out.println("The Title is " + driver.getTitle());
	}
}
