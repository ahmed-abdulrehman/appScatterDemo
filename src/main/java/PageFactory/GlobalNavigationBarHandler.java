package PageFactory;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Config.Utility;

public class GlobalNavigationBarHandler {

	public WebDriver driver;
	Utility utility;

	// Home page accept cookies button web element
	@FindBy(xpath = "//*[@id='cookie-policy']/div/div/div/div[3]/button")
	private WebElement homePageAcceptCookiesButton;

	// Accept cookie policy button web element
	@FindBy(xpath = "//*[@id='cookie-policy']/div/div[3]/button")
	private WebElement acceptCookiesPolicyButton;
	
	// Sign up web element
	@FindBy(xpath = "/html/body/div[3]/div/nav/button[3]")
	private WebElement signUpFreeButton;
	
	// Login button web element
	@FindBy(xpath = "/html/body/div[3]/div/nav/button[2]")
	private WebElement loginButton;
	
	
	public GlobalNavigationBarHandler(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// This method is used for clicking
	private void clicking(WebElement element, String logInfo) throws IOException, InterruptedException {
		utility = new Utility(driver);
		element.click();
		utility.logInfoStatus("Clicked " + logInfo);
	}
	
	// Go to sign up page
	public void goToSignUpPage() throws IOException, InterruptedException {
		clicking(signUpFreeButton, "Sign Up Free button");
	}
	
	// Go to login page
	public void goToLoginPage() throws IOException, InterruptedException {	
		clicking(loginButton, "Login button");
		clicking(acceptCookiesPolicyButton, "Accept cookie policy button");
	}
	


}
