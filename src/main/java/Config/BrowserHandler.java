package Config;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import Config.UrlHandler;
import Test.Setup;

public class BrowserHandler extends Setup {

	static UrlHandler theUrlHandler;

	public BrowserHandler(WebDriver driver) {
		Setup.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This method is used to run on the Chrome browser
	public void startChromeBrowser() throws Exception {

		// WebDriver will search the specified below path to start Chrome driver
		System.setProperty("webdriver.chrome.driver", "..\\appScatterDemo\\Config\\Browsers\\Chrome\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Browser goes into full screen
		driver.manage().window().maximize();

		// Navigates to a page which is defined in the UrlHandler class
		theUrlHandler = new UrlHandler(driver);
		theUrlHandler.navigate();
	}

}