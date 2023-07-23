package Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

	public Properties prop;
	public final String propertyfilepath = "C:\\Users\\deepa\\eclipse-workspace\\CalculatorTestAppium\\src\\main\\resources\\config.properties";

	public ConfigReader() {

		try {

			prop = new Properties();
			InputStream input = new FileInputStream(propertyfilepath);
			prop.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getDevicename() {

		String devicename = prop.getProperty("deviceName");
		if (devicename != null)

		{
			return devicename;

		} else
			throw new RuntimeException("device name is not specified in the config file");

	}

	public String getUDID() {

		String udid = prop.getProperty("udid");
		if (udid != null)

		{
			return udid;

		} else
			throw new RuntimeException("udid is not specified in the config file");

	}

	public String getplatformName() {

		String platformName = prop.getProperty("platformName");
		if (platformName != null)

		{
			return platformName;

		} else
			throw new RuntimeException("platformName is not specified in the config file");

	}

	public String getplatformVersion() {

		String platformVersion = prop.getProperty("platformVersion");
		if (platformVersion != null)

		{
			return platformVersion;

		} else
			throw new RuntimeException("platformVersion is not specified in the config file");

	}

	public String getappPackage() {

		String appPackage = prop.getProperty("appPackage");
		if (appPackage != null)

		{
			return appPackage;

		} else
			throw new RuntimeException("appPackage is not specified in the config file");

	}

	public String getappActivity() {

		String appActivity = prop.getProperty("appActivity");
		if (appActivity != null)

		{
			return appActivity;

		} else
			throw new RuntimeException("appActivity is not specified in the config file");

	}

	public String getautomationName() {

		String automationName = prop.getProperty("automationName");
		if (automationName != null)

		{
			return automationName;

		} else
			throw new RuntimeException("automationName is not specified in the config file");

	}

}
