package Config;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import Test.Setup;

public class UrlHandler extends Setup {

	public UrlHandler(WebDriver driver) {
		Setup.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// From the url properties file the user can control which environment they
	// wish to navigate too and test
	public void navigate() throws Exception {
		Properties urlProperties = new Properties();
		FileInputStream urlFileInputStream = new FileInputStream("..\\appScatterDemo\\Config\\Url\\url.properties");
		urlProperties.load(urlFileInputStream);
		driver.get(urlProperties.getProperty("environment"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
}