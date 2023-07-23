package Pages;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import Utility.BaseClass;
import io.appium.java_client.MobileBy;

public class CalculatorPage extends BaseClass

{
	private static Logger log = LogManager.getLogger(CalculatorPage.class);

	public By button_0 = MobileBy.AccessibilityId("0");
	public By button_1 = MobileBy.AccessibilityId("1");
	public By button_2 = MobileBy.AccessibilityId("2");
	public By button_3 = MobileBy.AccessibilityId("3");
	public By button_4 = MobileBy.AccessibilityId("4");
	public By button_5 = MobileBy.AccessibilityId("5");
	public By button_6 = MobileBy.AccessibilityId("6");

	// public By button_7 = By.xpath("//android.widget.Button[@text='7']");
	// public By button_8 = By.xpath("//android.widget.Button[@text='8']");
	// public By button_7 = MobileBy.AccessibilityId("7");
	// public By button_8 = MobileBy.AccessibilityId("8");
	public By button_9 = MobileBy.AccessibilityId("9");
	public By plusbutton = MobileBy.AccessibilityId("Plus");
	public By minusnbutton = MobileBy.AccessibilityId("Minus");
	public By divisionbutton = MobileBy.AccessibilityId("Division");
	public By decimalpointbutton = MobileBy.AccessibilityId("Decimal point");
	public By percentagebutton = MobileBy.AccessibilityId("Percentage");

	public By equalbutton = MobileBy.AccessibilityId("Equal");

	public boolean VerifyAdditonFuctionality(String number1 , String number2) throws InterruptedException 
	{
		log.info("VerifyAdditonFuctionality - User is Verifying additon fuctionaltiy");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean returnval = false;
		driver.findElement(By.xpath("//android.widget.Button[@text='" + number1 + "']")).click();
		driver.findElement(plusbutton).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='" + number2 + "']")).click();
		driver.findElement(equalbutton).click();
		returnval = true;
		return returnval;

	}
	
	public boolean VerifySubstractionFuctionality(String number1 , String number2) throws InterruptedException 
	{
		log.info("VerifySubstractionFuctionality - User is Verifying substraction fuctionaltiy");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		boolean returnval = false;
		driver.findElement(By.xpath("//android.widget.Button[@text='" + number1 + "']")).click();
		driver.findElement(minusnbutton).click();
		driver.findElement(By.xpath("//android.widget.Button[@text='" + number2 + "']")).click();
		driver.findElement(equalbutton).click();
		returnval = true;
		return returnval;

	}
}
