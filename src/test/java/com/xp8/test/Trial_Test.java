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
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_Sanity_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Trial_Test extends XP8_Sanity_Util {
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;

	@BeforeSuite
	public void beforeSuite() 
	{
		//Provide Desired Report Directory Location and Name
		extent = new ExtentReports("src/test/resources/extentreport/Trial_Test.html", true); 
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.1").addSystemInfo("Environment", "TEST");
	} 

	@BeforeMethod()
	public  void beforeMethod(Method method)  
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Chandan. A"); //Test Script Author Name
	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{	
			String screenshot_path=captureScreenshot(method.getName());
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
		Locators_XP8_Sanity loc=new Locators_XP8_Sanity(aDriver);
		Locators_BaseUtil loc1=new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}
	
	// =====================================================================================================
	// =============================================  Test Scripts =========================================
	// =====================================================================================================
		/*
		
	@Test(priority=37,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_037(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, TesseractException {
			
			APP_LOGS.info("======================XP8_Sanity_037=======================");
//			startAdbLog("XP8_Sanity_037");
//			recordVideo("XP8_Sanity_037");
			remove_BatteryIsFull_PopUp();
//			clearRecentApps();		
			launch_SMS();
			
			String text=null;
			
			text=OCR(image(aDriver));
//			text=Verify(aDriver);
//			text =readToastMessage();
			
			System.out.println(text);
			
//			test.log(LogStatus.PASS, "TestCase status is Pass");
 
	}
	*/
	
	@Test(priority=21,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_021_Verify_USBtethering_BTtethering_And_WiFihotspot_EntitlementError(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {
		
		APP_LOGS.info("======================XP8_Sanity_021=======================");
		startAdbLog("XP8_Sanity_021");
		recordVideo("XP8_Sanity_021");
		clearRecentApps();
		enable_MobileData();
		navigateTo_TetheringAndPortableHotspot();
		enable_USBTethering();
		validate_EntitlementError();
		enable_BT_Tethering();
		validate_EntitlementError();
		enable_portableWiFi_hotspot();
		validate_EntitlementError();
		test.log(LogStatus.PASS, "TestCase Status is Pass");		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
