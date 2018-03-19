package Config;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Utility {

	public WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;

	public static TakesScreenshot takeScreenshot;
	public static File destination;
	public static File source;

	public static String screenshot_path;
	public static String image;
	public static String path;
	public static String dest;

	public Utility(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// This method allows us to take a screenshot
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			takeScreenshot = (TakesScreenshot) driver;
			source = takeScreenshot.getScreenshotAs(OutputType.FILE);
			dest = "../appScatter_Report/Screenshots/" + screenshotName + ".png";
			destination = new File(dest);
			FileUtils.copyFile(source, destination);
			System.out.println("Screenshot taken");
			return dest;
		}

		catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
			return e.getMessage();
		}
	}

	// Starting the report
	public void startReport() {
		path = "../appScatter_Report/TestResults.html";
		report = new ExtentReports(path, true);
		report.loadConfig(new File("../appScatterDemo/extent-config.xml"));
	}

	// Displays the name of the test case
	public void testName(String theTestName) {
		logger = report.startTest(theTestName);
	}

	// Displaying the log info status
	public void logInfoStatus(String currentInfoStatus) {
		logger.log(LogStatus.INFO, currentInfoStatus);
	}

	// Displaying the log pass status
	public void logPassStatus(String currentPassStatus) {
		logger.log(LogStatus.PASS, currentPassStatus);
	}

	// Displaying the log fail status
	public void logFailStatus(ITestResult result, String currentFailStatus) {

		if (result.getStatus() == ITestResult.FAILURE) {
			screenshot_path = captureScreenshot(driver, result.getName());
			image = logger.addScreenCapture(screenshot_path);
			logger.log(LogStatus.FAIL, currentFailStatus, image);
		}

		report.endTest(logger);
	}

	// Ends the report
	public void endReport() {
		report.flush();
		report.close();
	}

	// Opening the report page
	public void openReport() {
		driver.get("../appScatter_Report/TestResults.html");
	}

}
