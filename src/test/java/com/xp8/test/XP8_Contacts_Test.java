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
import com.xp8.util.Locators_Calculator;
import com.xp8.util.Locators_Contacts;
import com.xp8.util.XP8_Contacts_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Contacts_Test extends XP8_Contacts_Util{
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
//	public AndroidDriver<AndroidElement> aDriver;
	
	
	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Contacts.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.1").addSystemInfo("Environment", "TEST");
		
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
//		aDriver = (AndroidDriver<AndroidElement>)driver;
		Locators_Contacts loc=new Locators_Contacts(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		
	}
	
	
	/*@Test(priority=1,dataProvider="LaunchContactsApp", dataProviderClass=DataProviders.class)
	public void XP8_Contacts_001_Launch_and_Exit_Contacts(Hashtable<String, String> data) throws InterruptedException, MalformedURLException
	{
		
		 longpress(4);
		
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}*/
	
	@Test(priority=2,dataProvider="LaunchContactsApp", dataProviderClass=DataProviders.class)
	public void XP8_Contacts_002_Add_and_Delete_Contact(Hashtable<String, String> data) throws InterruptedException, MalformedURLException
	{
//	   launchApp("CONTACTS_PACKAGE","CONTACTS_ACTIVITY");
		addContact();
		
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	

}
