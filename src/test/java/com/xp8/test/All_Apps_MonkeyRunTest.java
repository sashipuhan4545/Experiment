package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.All_AppsMonkeyRun_Util;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_XP8_Sanity;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class All_Apps_MonkeyRunTest extends All_AppsMonkeyRun_Util{
	
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	public AndroidDriver<AndroidElement> aDriver;
	
	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/All_AppsMonkeyRunReport.html", true);
	}
	
	@BeforeMethod()
	public  void beforeMethod(Method method) throws InterruptedException 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+method.getName()),method.getName());
		test.assignAuthor("Chandan. A"); //Test Script Author Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium Version", "1.2.7").addSystemInfo("Environment", "TEST");
		
	}
	
	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String screenshot_path=captureScreenshot(method.getName());
			String image= test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL,result.getThrowable());
			test.log(LogStatus.FAIL, "screenshot for failure: "+test.addScreenCapture(image));
		}
		extent.endTest(test);
		extent.flush();
	}	
	
	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException
	{
		properties=loadDriverAndProperties();
		Locators_XP8_Sanity loc=new Locators_XP8_Sanity(aDriver);
		Locators_BaseUtil loc1=new Locators_BaseUtil(aDriver);
		//public AndroidDriver<AndroidElement> aDriver;
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);		
	}	
	
	@Test(priority=1,dataProvider="AllAppsMonkeyTest", dataProviderClass=DataProviders.class)
	public void XP5S_ALL_AppsMonkeyRun_001(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		//ToolFrame.logwindow.setText("Current TC :" +"\n"+m.getName());
		
		APP_LOGS.info("===========ALL_AppsMonkeyRun_01============");
		startAdbLog("ALL_AppsMonkeyRun_01");
		recordVideo("ALL_AppsMonkeyRun_01");
		clearRecentApps();
		monkeyRunAllApps();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
}
