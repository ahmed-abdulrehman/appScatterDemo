package PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Config.Utility;

public class DashboardPage {

	public WebDriver driver;
	Utility utility;
	
	// Search icon web element
	@FindBy(xpath = "//*[@id='main-layout']/div/div[1]/as4-responsive-header/header/div/div[2]/nav[1]/as4-tooltip")
	private WebElement searchIcon;
	
	// Quick search field web element
	@FindBy(name = "quick-search")
	private WebElement quickSearchField;
	
	// Dashboard page title web element
	@FindBy(xpath = "//*[@id='as4-content-header']/div/div[1]/h1")
	private WebElement pageTitle;
	
	// First app returned result web element
	@FindBy(xpath = "//*[@id='pagination-top']/div/section[2]/div[1]/as2-app-result2/div/div[2]/div[1]")
	private WebElement appResult;
	

	
	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Set input for a text field.
	// The value is retrieved from a property file in the below path.
	private void setInput(String input, WebElement element) throws IOException {
		utility = new Utility(driver);

		Properties inputProperty = new Properties();
		FileInputStream fileInput = new FileInputStream(
				"..//appScatterDemo//Config//Search//Search.properties");
		inputProperty.load(fileInput);

		element.sendKeys(inputProperty.getProperty(input));

		utility.logInfoStatus("Adding " + input);
	}
	
	// This method is used for clicking
	private void clicking(WebElement element, String logInfo) throws IOException, InterruptedException {
		utility = new Utility(driver);
		element.click();
		utility.logInfoStatus("Clicked " + logInfo);
	}

	// Assert text expected copy against actual copy
	private void assertText(WebElement element, String actualMessage) throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals(actualMessage, element.getText());
	}
	
	// Asserting dashboard page title
	public void dashboardPageTitle() throws IOException, InterruptedException {
		assertText(pageTitle, "Dashboard");
	}
	
	// Searching for an app
	public void appSearching(String searchInput) throws IOException, InterruptedException {
		clicking(searchIcon, "Search icon");
		setInput(searchInput,quickSearchField);
		Thread.sleep(2000);
		quickSearchField.sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		
		// Asserting search page title
		assertText(pageTitle, "Search");
		
		clicking(appResult, "First app returned result");
		
		// Asserting app detail page title
		assertText(pageTitle, "App Detail");
	}
	

}