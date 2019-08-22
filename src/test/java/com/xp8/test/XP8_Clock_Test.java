package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
/*import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;*/
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_Clock;
import com.xp8.util.XP8_Clock_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Clock_Test extends XP8_Clock_Util {	
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	public AndroidDriver<AndroidElement> aDriver;
	
	@BeforeSuite
	public void beforeSuite() 
	{   Date d= new Date();
		extent = new ExtentReports("src/test/resources/extentreport/extentreport.html", true, DisplayOrder.NEWEST_FIRST); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Selenium Version", "2.53").addSystemInfo(
				"Environment", "PROD");
		
	}
	
	@BeforeMethod()
	public  void beforeMethod(Method method) throws IOException 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Suhas K S"); //Test Script Author Name
		//Runtime.getRuntime().exec("cmd /c start cmd.exe /K \"adb logcat -v time>sashi.txt \"");
	}
	
	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{

			//String screenshot_path=captureScreenshot(method.getName());
			//String image= test.addScreenCapture(screenshot_path);
			
			test.log(LogStatus.FAIL,result.getThrowable());
			
			//test.log(LogStatus.FAIL, "screenshot for failure: "+test.addScreenCapture(image));
		}

		extent.endTest(test);
		extent.flush();
	}
	
	
	
	@BeforeTest
	public void setupSys() throws InterruptedException, FileNotFoundException, MalformedURLException, AWTException{
		
		properties=loadDriverAndProperties();
		aDriver = (AndroidDriver<AndroidElement>)aDriver;
		Locators_Clock loc=new Locators_Clock(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		
	}
	
	
    @Test(priority=1,dataProvider="LaunchSettingsApp", dataProviderClass=DataProviders.class)
	public void menu(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		
    	
    	startAdbLog("01_sanity");
    	
    	
    	
    	// Runtime.getRuntime().exec("taskkill /f /im cmd.exe") ;
    	/*if(searchString("ContactEditorAccountsChangedActivity")) {
    		test.log(LogStatus.PASS, "TC passed");
    		
    	}
    	
    	else {
    		test.log(LogStatus.FAIL, "TC failes");
    		
    	}*/
    	
			
    	stopAdbLog("01_sanity");
		
		
	}

	
	

}
