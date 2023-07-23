package TestScript;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import Pages.CalculatorPage;
import Utility.BaseClass;

public class CalculatorAppTest extends BaseClass

{
	String num1 = "7";
	String num2 = "8";

	@Test
	public void basictest() throws InterruptedException {

		test = extent.startTest("CalculatorAppTest");
		CalculatorPage calculatorpage = new CalculatorPage();

		test.log(LogStatus.PASS, "User has Successfully opened calculator app");

		Assert.assertTrue(calculatorpage.VerifyAdditonFuctionality(num1, num2), "failed method");
		test.log(LogStatus.PASS, "User has added two numbers successfully");
		
		Assert.assertTrue(calculatorpage.VerifySubstractionFuctionality(num1, num2), "failed method");
		test.log(LogStatus.PASS, "User has substacted two numbers successfully");


	}
}
