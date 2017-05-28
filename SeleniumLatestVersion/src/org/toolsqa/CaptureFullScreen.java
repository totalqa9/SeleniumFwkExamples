package org.toolsqa;

public class CaptureFullScreen {

	 @Test
	public  void main1() throws AWTException   {
		try 
		{
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			FirefoxDriver driver = new FirefoxDriver();
			driver.get("http://www.google.com");
			
			//maximize the browser
			driver.manage().window().maximize();

			System.out.println(driver.getTitle());
			//Implementing the Robot Class
			Robot robot = new Robot();
			String format = "jpg";    
			String imgFileName= new SimpleDateFormat("mm-dd-yyyy_HH-ss").
					format( new GregorianCalendar().getTime());
			System.out.println(new GregorianCalendar().getTime());

			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File("d:\\"+imgFileName+".jpg"));

			System.out.println("Screenshot Saved in today's date format in d:\\");
		} 
		catch (  IOException ex)
		{
			System.err.println(ex);
		}
	}
}
