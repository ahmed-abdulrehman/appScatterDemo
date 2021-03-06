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

public class LoginPage {

	public WebDriver driver;
	Utility utility;

	private static int nextEmailNumberInInteger;
	
	// Login page title web element
	@FindBy(xpath = "//*[@id='sign-layout']/div/main/as2-login/div/div[1]/h1")
	private WebElement pageTitle;
	
	// Email field web element
	@FindBy(name = "username")
	private WebElement emailField;

	// Password field web element
	@FindBy(name = "password")
	private WebElement passwordField;

	// Sign in button web element
	@FindBy(xpath = "//*[@id='sign-layout']/div/main/as2-login/div/div[2]/form/button")
	private WebElement signInButton;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Set input for a text field.
	// The value is retrieved from a property file in the below path.
	private void setInput(String input, WebElement element) throws IOException {
		utility = new Utility(driver);

		Properties inputProperty = new Properties();
		FileInputStream fileInput = new FileInputStream(
				"..//appScatterDemo//Config//Credentials//Register//CreateAccount.properties");
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

	// Set email input for a text field.
	// This method is used to look at the current email in the property file in the below path and
	// then it will decrement the email address because the email will exist already.
	private void setEmailDecrement(String input, WebElement element) throws IOException {
		utility = new Utility(driver);

		Properties theProperties = new Properties();
		FileInputStream fileInput = new FileInputStream(
				"..//appScatterDemo//Config//Credentials//Register//CreateAccount.properties");
		theProperties.load(fileInput);

		String nextEmailNumberInString = theProperties.getProperty(input);

		nextEmailNumberInInteger= Integer.parseInt(nextEmailNumberInString);
		nextEmailNumberInInteger--;
		String str = Integer.toString(nextEmailNumberInInteger);
		
		element.sendKeys(str);
		fileInput.close();
	}
	
	// Assert text expected copy against actual copy
	private void assertText(WebElement element, String actualMessage) throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals(actualMessage, element.getText());
	}
	
	
	// This method is used to log into user's account
	public void accountLogin(String theEmailName, String theEmailDecrement, String theEmailClient, String thePassword)
			throws IOException, InterruptedException {
		
		// Asserting registration page title
		assertText(pageTitle,"Sign in");
		
		setInput(theEmailName, emailField);
		setEmailDecrement(theEmailDecrement, emailField);
		setInput(theEmailClient, emailField);
		setInput(thePassword, passwordField);
		clicking(signInButton, "Sign In Button");

	}
}