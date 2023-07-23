package Utility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseClass

{

	private static Logger log = LogManager.getLogger(BaseClass.class);

	ConfigReader configreader = new ConfigReader();
	public static AppiumDriver<MobileElement> driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeClass
	public void setUp() throws InterruptedException, MalformedURLException {
		log.info("Launching Calculator App");
		// Set the desired capabilities for the app
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", configreader.getDevicename());
		caps.setCapability("udid", configreader.getUDID());
		caps.setCapability("platformName", configreader.getplatformName());
		caps.setCapability("platformVersion", configreader.getplatformVersion());
		caps.setCapability("appPackage", configreader.getappPackage());
		caps.setCapability("appActivity", configreader.getappActivity());
		caps.setCapability("automationName", configreader.getautomationName());

		// Create an AndroidDriver object
		driver = new AppiumDriver<MobileElement>(new URL("http:/0.0.0.0:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@BeforeTest
	public void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/target/ExtentReport.html", true);
		extent.addSystemInfo("Tester Name", "Deepak mehta").addSystemInfo("Environment", "AT")
				.addSystemInfo("User Name", "Deepak Mehta");
		extent.loadConfig(new File(System.getProperty("user.dir") + "/src/main/resources/extent-config.xml"));
	}

	public static String getScreenhot(AppiumDriver<MobileElement> driver, String screenshotName) throws Exception {
		log.info("getScreenhot - Capturing the screenshot on failure");
		String dateName = new SimpleDateFormat("yyy-MM-dd-HH-mm").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, "Test case failed is " + result.getName());
			test.log(LogStatus.FAIL, "Test case failed is " + result.getThrowable());
			String screenshotPath = BaseClass.getScreenhot(driver, result.getName());
			test.log(LogStatus.FAIL, test.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.log(LogStatus.SKIP, "Test case skipped is " + result.getName());
		}
		extent.endTest(test);
	}

	@AfterTest
	public void endreport() {
		extent.flush();
	}

	@AfterClass
	public void closeDriver() {
		log.debug("closeDriver - Attempting to close the driver");
		if (driver != null) {
			try {
				driver.quit();
			} catch (Exception e) {
				log.error("closeDriver - Could not close the driver");
			}
		}

	}
}
