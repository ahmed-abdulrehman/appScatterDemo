package PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import Config.Utility;

public class GmailPage {

	public WebDriver driver;
	Utility utility;
	String winHandleBefore;
	
	// Sign In button web element
	@FindBy(css = ".buttonLargeBlue")
	private WebElement signInButton;

	// Email field web element
	@FindBy(name = "loginfmt")
	private WebElement emailField;

	// Next button web element
	@FindBy(id = "idSIButton9")
	private WebElement emailNextButton;

	// Password field web element
	@FindBy(name = "passwd")
	private WebElement passwordField;

	// Next button web element
	@FindBy(id = "idSIButton9")
	private WebElement passwordNextButton;

	// appScatter email web element
	@FindBy(xpath = "//*[@id='app']/div/div[2]/div/div[1]/div[3]/div/div[2]/div[1]/div[2]/div/div/div/div/div/div/div/div")
	private WebElement appScatterEmail;

	// Verify email web element
	@FindBy(xpath = "//*[@id='app']/div/div[2]/div/div[1]/div[3]/div/div[2]/div[3]/div/div/div/div/div/div[2]/div/div[1]/div/div/div/div/div/div/div/div[2]/div[1]/div/div/div/div/div[2]/a")
	private WebElement verifyEmail;

	// email verified title web element
	@FindBy(xpath = "//*[@id='sign-layout']/div/main/as4-activate-user/div/div[1]/h1")
	private WebElement emailVerifiedTitle;

	public GmailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Set input for a text field.
	// The value is retrieved from a property file in the below path.
	private void setInput(String input, WebElement element) throws IOException {
		utility = new Utility(driver);

		Properties inputProperty = new Properties();
		FileInputStream fileInput = new FileInputStream(
				"..//appScatterDemo//Config//Credentials//Gmail//LoginDetails.properties");
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

	// Switch to new window opened
	public void switchToNewWindow() {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
	}
	

	// This method is used to verify the email account used to register
	public void verifyEmail(String theEmail, String thePassword) throws IOException, InterruptedException {
		driver.get("https://outlook.live.com/owa/");

		clicking(signInButton, "Sign In Button");

		setInput(theEmail, emailField);
		clicking(emailNextButton, "Next Button");
		Thread.sleep(1000);
		setInput(thePassword, passwordField);
		Thread.sleep(1000);
		clicking(passwordNextButton, "Next Button");
		clicking(appScatterEmail, "appScatter Email");
		Thread.sleep(2000);
		clicking(verifyEmail, "Verified Email");
		Thread.sleep(2000);
		
		switchToNewWindow();
		assertText(emailVerifiedTitle, "Email Verified");
		
		

	}

}
