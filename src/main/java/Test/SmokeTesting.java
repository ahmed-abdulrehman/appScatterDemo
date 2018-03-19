package Test;

import java.io.IOException;

import org.testng.annotations.Test;

import Config.Utility;
import PageFactory.GlobalNavigationBarHandler;
import PageFactory.RegisterPage;
import PageFactory.GmailPage;
import PageFactory.LoginPage;
import PageFactory.DashboardPage;

public class SmokeTesting extends Setup {
	
	Utility utility;
	GlobalNavigationBarHandler theGlobalNavigationBarHandler;
	RegisterPage theRegisterPage;
	GmailPage theGmailPage;
	LoginPage theLoginPage;
	DashboardPage theDashboardPage;
	
	@Test
	public void createAccount() throws InterruptedException, IOException {
		utility = new Utility(driver);
		theGlobalNavigationBarHandler = new GlobalNavigationBarHandler(driver);
		theRegisterPage = new RegisterPage(driver);
		
		utility.testName("Creating an account");
		utility.logInfoStatus("Browser Started");
		
		theGlobalNavigationBarHandler.goToSignUpPage();
		theRegisterPage.createAnAccount("firstName", "lastName", "emailName", "incrementEmail", "emailClient", "password","confirmPassword");
		
		utility.logPassStatus("Successfully created an account");
	}
	
	@Test
	public void activateAccount() throws InterruptedException, IOException {
		utility = new Utility(driver);
		theGmailPage = new GmailPage(driver);
		
		utility.testName("Account activation");
		utility.logInfoStatus("Browser Started");
		
		theGmailPage.verifyEmail("email", "password");
		
		utility.logPassStatus("Successfully activated account");
	}
	
	@Test
	public void logInToAnAccount() throws InterruptedException, IOException {
		utility = new Utility(driver);
		theGlobalNavigationBarHandler = new GlobalNavigationBarHandler(driver);
		theLoginPage = new LoginPage(driver);
		theDashboardPage = new DashboardPage(driver);
		
		utility.testName("Log into an account");
		utility.logInfoStatus("Browser Started");

		theGlobalNavigationBarHandler.goToLoginPage();
		theLoginPage.accountLogin("emailName", "incrementEmail", "emailClient", "password");
		theDashboardPage.dashboardPageTitle();
		theDashboardPage.appSearching("searching");
		
		utility.logPassStatus("Successfully logged into an account");
	}
	
}