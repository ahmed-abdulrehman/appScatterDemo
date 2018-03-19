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

public class RegisterPage {

	public WebDriver driver;
	Utility utility;

	// register page title web element
	@FindBy(xpath = "//*[@id='sign-layout']/div/main/as2-register/div/div[1]/h1")
	private WebElement pageTitle;
	
	// First name field web element
	@FindBy(name = "firstname")
	private WebElement firstNameField;

	// Last name field web element
	@FindBy(name = "lastname")
	private WebElement lastNameField;

	// Email field web element
	@FindBy(name = "email")
	private WebElement emailField;

	// Terms & conditions checkbox web element
	@FindBy(name = "acceptTermsAndConditions")
	private WebElement termsAndConditionsCheckBox;

	// Create account button web element
	@FindBy(xpath = "//*[@id='sign-layout']/div/main/as2-register/div/div[2]/form/button")
	private WebElement createAccountButton;

	// Password field web element
	@FindBy(name = "initial")
	private WebElement passwordField;

	// Confirm password field web element
	@FindBy(name = "confirm")
	private WebElement confirmPasswordField;

	// Complete button web element
	@FindBy(xpath = "//*[@id='sign-layout']/div/main/as2-register/div/div[2]/form/button")
	private WebElement completeButton;

	// Using this variable to increment email address. Therefore, I can use the
	// same email address as many times as I wish
	private static int nextEmailNumberInInteger;

	public RegisterPage(WebDriver driver) {
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

	// Set email input for a text field.
	// This method will be used to increment the same email address as many times as I wish.
	// The value is retrieved from a property file in the below path.
	private void setEmailIncrement(String input, WebElement element) throws IOException {
		utility = new Utility(driver);

		Properties theProperties = new Properties();
		FileInputStream fileInput = new FileInputStream(
				"..//appScatterDemo//Config//Credentials//Register//CreateAccount.properties");
		theProperties.load(fileInput);

		String nextEmailNumberInString = theProperties.getProperty(input);

		element.sendKeys(nextEmailNumberInString);
		fileInput.close();

		nextEmailNumberInInteger = Integer.parseInt(nextEmailNumberInString);

		FileOutputStream out = new FileOutputStream(
				"..//appScatterDemo//Config//Credentials//Register//CreateAccount.properties");
		nextEmailNumberInInteger++;
		String str = Integer.toString(nextEmailNumberInInteger);

		theProperties.setProperty("incrementEmail", str);
		theProperties.store(out, "Create Account");
		out.close();
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

	// This method is used to create an account
	public void createAnAccount(String theFirstName, String theLastName, String theEmailName, String theEmailIncrement,
			String theEmailClient, String thePassword, String theConfirmPassword)
			throws IOException, InterruptedException {
		
		// Asserting registration page title
		assertText(pageTitle,"Create an account");
		
		setInput(theFirstName, firstNameField);
		setInput(theLastName, lastNameField);
		setInput(theEmailName, emailField);
		setEmailIncrement(theEmailIncrement, emailField);
		setInput(theEmailClient, emailField);
		clicking(termsAndConditionsCheckBox, "Terms & Conditions Checkbox");
		clicking(createAccountButton, "Create Account Button");
		setInput(thePassword, passwordField);
		setInput(theConfirmPassword, confirmPasswordField);
		clicking(completeButton, "Complete Button");

	}

}
