package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
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
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.XP8_Calculater_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import com.xp8.util.Locators_Calculator;
import com.xp8.util.Locators_Clock;



public class XP8_Calculater_Test extends XP8_Calculater_Util {


	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;
	
	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_CalcTest.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.1").addSystemInfo(
				"Environment", "TEST");
		
	}
	
	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Farheen Taj"); //Test Script Author Name
	}
	
	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{	String screenshot_path=captureScreenshot(method.getName());
		   String image= test.addScreenCapture(screenshot_path);		
			test.log(LogStatus.FAIL,result.getThrowable());			
			
		}
		extent.endTest(test);
		extent.flush();
	}
	
	
	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		//aDriver = (AndroidDriver<AndroidElement>)driver;
		Locators_Calculator loc=new Locators_Calculator(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		
	}
	
	
	@Test(priority=1,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0001_Launch_and_Exit(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_001============");
		 //clearRecentApps();
		 startAdbLog("XP8_Calc_001");
		 launchCalc();
		 validateCalcLaunch();
		 longpress(4);
		 stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	
	@Test(priority=2,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0002_Basic_functions_with_decimalpt(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_002============");
		//clearRecentApps();
		startAdbLog("XP8_Calc_002");
		recordVideo("XP8_Calc_002");
		launchCalc();
		basicOperationwithdecimalpt();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=3,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0003_Basic_functions_without_decimalpt(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_003============");
		//clearRecentApps();
		startAdbLog("XP8_Calc_003");
		recordVideo("XP8_Calc_003");
		launchCalc();
		basicOperationwithoutdecimalpt();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=4,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0004_Functionality_Allkeys_shortpress(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_004============");
		//clearRecentApps();
		startAdbLog("XP8_Calc_004");
		recordVideo("XP8_Calc_004");
		launchCalc();
		shortpress();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}	
	
	@Test(priority=5,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0005_Functionality_Allkeys_longpress(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_005============");
		//clearRecentApps();
		startAdbLog("XP8_Calc_005");
		recordVideo("XP8_Calc_005");
		launchCalc();
		longPress();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=6,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0006_Operation_with_Zero(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_006============");
		//clearRecentApps();
		startAdbLog("XP8_Calc_006");
		recordVideo("XP8_Calc_006");
		launchCalc();
		basicOperationWithZero();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
   
	
	@Test(priority=7,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0007_Digitpresent_Relaunch_ScreenOrientation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Calc_007============");
		//clearRecentApps();
		startAdbLog("XP8_Calc_007");
		recordVideo("XP8_Calc_007");
		launchCalc();
		relaunch_ScreenOrientation();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
	
	@Test(priority=8,dataProvider="LaunchCalculatorApp", dataProviderClass=DataProviders.class)
	public void XP8_CALC_TC_0008_Voicecall_interaction(Hashtable<String, String> data) throws InterruptedException, MalformedURLException, IOException, AWTException
	{
		APP_LOGS.info("===========XP8_Calc_008============");
		//clearRecentApps();
		startAdbLog("XP8_Calc_008");
		recordVideo("XP8_Calc_008");
		launchCalc();
		//Call interaction with Toll Free Num
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
		Runtime.getRuntime().exec("adb -s c1edac9c shell am start -a android.intent.action.CALL -d tel:555-5555");
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");		
	}
     

}
