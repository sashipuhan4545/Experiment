package com.xp8.util;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import bsh.Capabilities;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;


public class CommonConfig  {
	
	//public static AndroidDriver driver;
	public FileInputStream inputStream;
	public Properties properties;
	public static AndroidDriver<AndroidElement> aDriver;
	public static AndroidDriver<AndroidElement> aDriver1;


	private static final String LOG_PATH = "src/test/resources/properties/log4j.properties";
	public static Logger APP_LOGS = null;
	public static DesiredCapabilities Capabilities;
	
	
	 @Parameters({ "deviceName_","UDID_","platformVersion_", "URL_","App_Package_Name_","App_Activity_Name_"})
	 @BeforeTest(alwaysRun=true)
	 public void setUp(String deviceName_,String UDID_,String platformVersion_,String URL_,String App_Package_Name_,String App_Activity_Name_) throws MalformedURLException, InterruptedException, FileNotFoundException{
		 
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		/*
		String path = System.getProperty("user.dir")+"//apk//ContactManager.apk";
		File file = new File(path);
		*/
		JsonFileReaderAndWriter.primaryDevIdReader();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName_);
	    capabilities.setCapability(MobileCapabilityType.UDID, UDID_);
	//	capabilities.setCapability("automationName", "UiAutomator2");

		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion_);
		
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("autoGrantPermissions", "true");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
		
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, App_Package_Name_);
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, App_Activity_Name_);
		
		//capabilities.setCapability(MobileCapabilityType.APP, file);		
		 
		aDriver = new AndroidDriver<AndroidElement>(new URL("http://"+URL_+""), capabilities);
		
		aDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
		
	}	
	 
	 
	 public void setUp1() throws InterruptedException, IOException, ParseException{
		 
			
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			/*
			String path = System.getProperty("user.dir")+"//apk//ContactManager.apk";
			File file = new File(path);
			*/
			String refid = JsonFileReaderAndWriter.ReadRefDeviceId();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "XP8800");
		    capabilities.setCapability(MobileCapabilityType.UDID, refid);
			//capabilities.setCapability("automationName", "UiAutomator2");

			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
			
			capabilities.setCapability("autoAcceptAlerts", true);
			capabilities.setCapability("autoGrantPermissions", "true");
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
			
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,"com.android.dialer" );
			capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.android.dialer.app.DialtactsActivity");
			
			//capabilities.setCapability(MobileCapabilityType.APP, file);		
			 
			//aDriver1 = new AndroidDriver<AndroidElement>(appiumServerURL, capabilities);
			
			aDriver1.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Thread.sleep(5000);
	 }
	public void loggerProperties() {
		APP_LOGS=Logger.getLogger(this.getClass().getName());
		PropertyConfigurator.configure(LOG_PATH);
	}


	public Properties loadDriverAndProperties() throws FileNotFoundException, MalformedURLException, InterruptedException, AWTException {
		loggerProperties();
		
		return properties;
	}

	public static void cleanUp() throws InterruptedException, IOException {

		if (getDriver() != null) {
			
			//BaseUtil.stopAdbLog();
			getDriver().close();
			getDriver().quit();
		}
	}

	public static AndroidDriver getDriver() {
		return aDriver;
	}


}
