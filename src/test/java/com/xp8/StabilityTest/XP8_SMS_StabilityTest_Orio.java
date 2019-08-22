package com.xp8.StabilityTest;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.XP8_Stability_Util_orio;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_SMS_StabilityTest_Orio extends XP8_Stability_Util_orio{
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =1;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_SMS_Stability_Orio_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();	
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) 
	{
		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Farheen Taj"); //Test Script Author Name
	}


	@BeforeClass
	public void copyFilesToDevice() throws IOException {
		System.out.println("Executing clear log screen");
		File dir = new File("src/test/resources/adbLogs");
		if(dir.isDirectory() == false) {
			System.out.println("Not a directory. Do nothing");
			return;
		}

		File[] listFiles = dir.listFiles();
		for(File file : listFiles){
			System.out.println("Deleting "+file.getName());
			file.delete();

		}

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
		Locators_DeviceStability loc=new Locators_DeviceStability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	
	
	@Test(priority=1,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_001_Stability_Validate_Send_MO_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_001============");
		startAdbLog("XP8_Stability_001");
		SoftAssert sa1 = new SoftAssert();
			for(int i=1; i<=itr;i++) {
				if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
					launch_APP(Locators_DeviceStability.messaging);
					clearSMSPermissions();
					create_NewSMS(refNum, data.get("typeMessage"));
					clickOn_Send();
					validate_SentMessage(i,sa1) ;
					delete_SMS();
				} else if(p_b_No.contains("-15.")) {
					System.out.println("Executing...");
					launch_APP(Locators_DeviceStability.MessagePlus);
					clearSMSPermissions_O();
					navigateTo_NewSMS_O();
					create_NewSMS_O(refNum,data.get("typeMessage"));
					clickOn_Send_O();
					validate_SentMessage_O(itr,sa1) ;
					delete_SMS_O();
				}
			}
			sa1.assertAll();
			test.log(LogStatus.PASS, "Test case status is Passed");		
	}

	
	@Test(priority=2,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_002_Stability_Validate_MO_SMS_Maximum_NumberOfCharacters(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_002============");
		startAdbLog("XP8_Stability_002");
		SoftAssert sa2 = new SoftAssert();
		for(int i=1; i<=itr;i++) {
			
			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
				launch_APP(Locators_DeviceStability.messaging);
				navigateTo_NewSMS_O();
				create_NewSMS(refNum, data.get("typeMessage3"));
				validate_CharacterAndPageNumber(Locators_DeviceStability.zero_Characters_FirstPage,data.get("expectedChar&PageNum3"));
				clickOn_Send();
				validate_SentMessage(i,sa2) ;
				delete_SMS();
			} else if(p_b_No.contains("-15.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);	
				navigateTo_NewSMS_O();				
				create_NewSMS_O(refNum,data.get("typeMessage3"));
				validate_CharacterAndPageNumber_O(Locators_DeviceStability.zero_Characters_FirstPage_O,data.get("expectedChar&PageNum3"));
				clickOn_Send_O();
				validate_SentMessage_O(itr,sa2) ;
				delete_SMS_O();
			}
		}
		sa2.assertAll();
		test.log(LogStatus.PASS, "Test case status is Passed");			
	}


	@Test(priority=3,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_003_Stability_Validate_MT_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_003============");
		startAdbLog("XP8_Stability_003");
		SoftAssert sa3 = new SoftAssert();
		clearRecentApps();
		for(int i=1; i<=itr;i++) {

			if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
				launch_APP(Locators_DeviceStability.messaging);
				delete_SMS();
				sendSMS_fromRefDevice(data.get("typeMessage1"));
		
				validate_ReceivedMessage(itr, sa3);
			} else if(p_b_No.contains("-15.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);		
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				customWait(3000);
				validate_RecievedMessage_O(itr,sa3);		
			}
		}
		sa3.assertAll();
	}




}
