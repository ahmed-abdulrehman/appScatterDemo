package Test;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import Config.BrowserHandler;
import Config.Utility;

public class Setup {

	public static WebDriver driver;
	
	BrowserHandler theBrowserHandler;
	Utility utility;


	@BeforeTest
	public void startingReport() throws Exception {
		utility = new Utility(driver);
		utility.startReport();
	}

	@BeforeMethod
	public void browser() throws Exception {
		theBrowserHandler = new BrowserHandler(driver);		
		theBrowserHandler.startChromeBrowser();
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		utility = new Utility(driver);
		utility.logFailStatus(result, "Fail");

		driver.quit();
	}

	@AfterTest
	public void endingReport() {
		utility = new Utility(driver);
		utility.endReport();
	}
}
