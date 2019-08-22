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
import com.xp8.util.Locators_Dailer;
import com.xp8.util.Locators_Warranty_Reg;
import com.xp8.util.XP8_SonimWarranty_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_SonimWarranty_Test extends XP8_SonimWarranty_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_SonimWarrantyTest.html", true); //Provide Desired Report Directory Location and Name
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
		//aDriver = (AndroidDriver<AndroidElement>)driver;
		Locators_Warranty_Reg loc=new Locators_Warranty_Reg(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	
	@Test(priority=1,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_001_Presence_Of_SWR_In_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_001============");
//		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_001");
		presence_Of_SonimWarrantyReg_In_ScoutApp();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	

	@Test(priority=2,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_002_Presence_Of_Register_Option_In_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_002============");
//		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_002");
		presence_Of_Register_Option();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=3,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_003_skip_AllFields_and_Validate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_003============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_003");
		skip_AllFields_Press_SubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=4,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_004_Validate_CountryName_In_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_004============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_004");
		enter_Country_Name_Press_SubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=5,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_005_Validate_FirstName_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_005============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_005");
		enter_Fname_Press_SubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=6,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_006_Validate_SecondtName_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_006============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_006");
		enter_Sname_Press_SubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=7,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_007_Validate_CompanyName_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_007============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_007");
		enter_CompanyName_Press_SubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=8,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_008_Validate_AddressField_1_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_008============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_008");
		enter_Address1_Press_SubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=9,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_009_Validate_City_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_009============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_009");
		enter_City_Press_SubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=10,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_010_Validate_StateName_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_010============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_010");
		enter_StateName_PressSubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=11,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_011_Validate_ZipCode_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_011============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_011");
		enter_ZipCode_PressSubmitbtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=12,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_012_Validate_Email_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_012============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_012");
		enter_EmailId_Press_SubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=13,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_013_Validate_PhoneNumber_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_013============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_013");
		enter_PhoneNum_Press_SubmitBtn();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=14,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_014_Validate_All_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_014============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_014");
//		enter_PurchaseDate_Press_SubmitBtn();
	    enter_DealerName_Press_SubmitBtn();
	    clearRecentApps();
		addInvoice();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=15,dataProvider="SonimWarrantyReg", dataProviderClass=DataProviders.class)
	public void XP8_SonimWarrantyReg_TC_015_Validate_All_Field(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SonimWarrantyReg_015============");
		clearRecentApps();
		startAdbLog("XP8_SonimWarrantyReg_015");
//		enter_PurchaseDate_Press_SubmitBtn();
	    enter_DealerName_Press_SubmitBtn();
	    clearRecentApps();
		addInvoice();
		longpress(4);
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
}
