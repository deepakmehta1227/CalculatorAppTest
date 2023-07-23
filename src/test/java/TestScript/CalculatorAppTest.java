package TestScript;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import Pages.CalculatorPage;
import Utility.BaseClass;
import Utility.ExcelUtils;

public class CalculatorAppTest extends BaseClass

{
	String number_1_add= ExcelUtils.getExcelValue("C:\\Users\\deepa\\eclipse-workspace\\CalculatorTestAppium\\src\\main\\resources\\Test Data.xlsx", "ClaculatorAppTestData",
	"num_1_additon", "Value");
	String number_2_add =ExcelUtils.getExcelValue("C:\\Users\\deepa\\eclipse-workspace\\CalculatorTestAppium\\src\\main\\resources\\Test Data.xlsx","ClaculatorAppTestData",
	"num_2_additon", "Value");

	@Test
	public void basictest() throws InterruptedException {

		test = extent.startTest("CalculatorAppTest");
		CalculatorPage calculatorpage = new CalculatorPage();

		test.log(LogStatus.PASS, "User has Successfully opened calculator app");

		Assert.assertTrue(calculatorpage.VerifyAdditonFuctionality(number_1_add, number_2_add), "failed method");
		test.log(LogStatus.PASS, "User has added two numbers successfully");
		
		Assert.assertTrue(calculatorpage.VerifySubstractionFuctionality(number_1_add, number_2_add), "failed method");
		test.log(LogStatus.PASS, "User has substacted two numbers successfully");


	}
}
