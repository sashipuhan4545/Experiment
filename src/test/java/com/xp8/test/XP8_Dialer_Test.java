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
import com.xp8.util.Locators_Browser;
import com.xp8.util.Locators_Dailer;
import com.xp8.util.XP8_Dailer_Util;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Dialer_Test extends XP8_Dailer_Util{


	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_DailerTest.html", true); //Provide Desired Report Directory Location and Name
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
		Locators_Dailer loc=new Locators_Dailer(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}

	@Test(priority=1,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_001_Validate_Launch_and_Exit_PhoneDailer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_001============");
		startAdbLog("XP8_Dailer_001");
		recordVideo("XP8_Dailer_001");
		launchDailer();
		validateDailerLaunch();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=2,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_002_Validate_SpeedPhoneDailer_Page(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_002============");
		startAdbLog("XP8_Dailer_002");
		recordVideo("XP8_Dailer_002");
		launchDailer();
		selectPage(Locators_Dailer.speedDail);
		validateSelectedPage(Locators_Dailer.addFavorite);
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}


	@Test(priority=3,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_003_Validate_History_Page(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_003============");
		startAdbLog("XP8_Dailer_003");
		recordVideo("XP8_Dailer_003");
		launchDailer();
		selectPage(Locators_Dailer.callHistory);
		validateSelectedPage(Locators_Dailer.makeCall);
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=4,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_004_Validate_Contacts_Page(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_004============");
		startAdbLog("XP8_Dailer_004");
		recordVideo("XP8_Dailer_004");
		launchDailer();
		selectPage(Locators_Dailer.contactsIcon);
		validateSelectedPage(Locators_Dailer.UidaiContact);
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}


	@Test(priority=5,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_005_Validate_Dail_Number(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_005============");
		startAdbLog("XP8_Dailer_005");
		recordVideo("XP8_Dailer_005");
		launchDailer();
		selectPage(Locators_Dailer.speedDail);
		dailCallUsingDailPad(data.get("DailNumber"));
		dailcall();
		validateUI_DailCall(Locators_Dailer.dailedNum, data.get("DailNumber"));
		validateDailCall();
		endCall();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=6,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_006_Validate_Call_LogsUpdate(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_006============");
		startAdbLog("XP8_Dailer_006");
		recordVideo("XP8_Dailer_006");
		launchDailer();
		selectPage(Locators_Dailer.speedDail);
		dailCallUsingDailPad(data.get("DailNumber"));
		dailcall();
		endCall();
		selectPage(Locators_Dailer.callHistory);
		validateCallLogsUpdate(Locators_Dailer.dailedNum,Locators_Dailer.saveContactName);
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=7,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_007_Validate_Add_Contact_ContactList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_007============");
		startAdbLog("XP8_Dailer_007");
		recordVideo("XP8_Dailer_007");
		launchDailer();
		selectPage(Locators_Dailer.contactsIcon);
		validateCallLogsUpdate(Locators_Dailer.contactTitle,Locators_Dailer.saveContactName);
		validateLogSavedContact(data.get("DailNumber"));
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=8,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_008_Validate_SavedConatct_Call_HistoryList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_008============");
		startAdbLog("XP8_Dailer_008");
		recordVideo("XP8_Dailer_008");
		launchDailer();
		selectPage(Locators_Dailer.callHistory);
		validateCallLogsUpdate(Locators_Dailer.contactTitle,Locators_Dailer.contactNameTitle);
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=8,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_009_Validate_SavedConatct_Call_FavoriteList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_009============");
		startAdbLog("XP8_Dailer_009");
		recordVideo("XP8_Dailer_009");
		launchDailer();
		selectPage(Locators_Dailer.speedDail);
		validateCallLogsUpdate(Locators_Dailer.contactTitle,Locators_Dailer.saveContactName);
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=10,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_010_Validate_Edited_Contact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_010============");
		startAdbLog("XP8_Dailer_010");
		recordVideo("XP8_Dailer_010");
		launchDailer();
		selectPage(Locators_Dailer.contactsIcon);
		editSaveContact(Locators_Dailer.Work);
		validateEditContact( data.get("summaryText"));
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=11,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_011_Validate_DisplayAllOptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_011============");
		startAdbLog("XP8_Dailer_011");
		recordVideo("XP8_Dailer_011");
		launchDailer();
		validateAllOptions();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=12,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_012_Validate_ClearFrequentsContacted(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_012============");
		startAdbLog("XP8_Dailer_012");
		recordVideo("XP8_Dailer_012");
		launchDailer();
		selectClearFrequentContacted() ;
		launchDailer();
		selectPage(Locators_Dailer.speedDail);
		validateSelectedPage(Locators_Dailer.addFavorite);
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=13,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_013_Validate_OutgoingCallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_013============");
		startAdbLog("XP8_Dailer_013");
		recordVideo("XP8_Dailer_013");
		launchDailer();
		selectClearHistory() ;
		validateCallTypeHistory(Locators_Dailer.outgoingCalls,data.get("CallType"));
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=14,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_014_Validate_IncomingCallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_014============");
		startAdbLog("XP8_Dailer_014");
		recordVideo("XP8_Dailer_014");
		launchDailer();
		selectClearHistory() ;
		validateCallTypeHistory(Locators_Dailer.incomingCalls,data.get("CallType"));
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=15,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_015_Validate_MissedCallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_015============");
		startAdbLog("XP8_Dailer_015");
		recordVideo("XP8_Dailer_015");
		launchDailer();
		selectClearHistory() ;
		validateCallTypeHistory(Locators_Dailer.missedCalls,data.get("CallType"));
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=16,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_016_Validate_SearchCallLogHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_016============");
		startAdbLog("XP8_Dailer_016");
		recordVideo("XP8_Dailer_016");
		launchDailer();
		selectClearHistory() ;
		searchCallLog(data.get("SearchName"));
		validateSearchLogs(data.get("SearchName"));
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}


	@Test(priority=17,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_017_Validate_ClearCallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_017============");
		startAdbLog("XP8_Dailer_017");
		recordVideo("XP8_Dailer_017");
		launchDailer();
		dailCallUsingDailPad(data.get("DailNumber"));
		dailcall();
		endCall();
		selectClearHistory() ;
		selectclearCallHistory();
		validateClearCallHistory();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=18,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_018_Validate_SearchContacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_018============");
		startAdbLog("XP8_Dailer_018");
		recordVideo("XP8_Dailer_018");
		launchDailer();
		enterSearchContact(data.get("SearchName"));
		validateSearchLogs(data.get("SearchName"));
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=19,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_019_Validate_PerformCall_ContactList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_019============");
		startAdbLog("XP8_Dailer_019");
		recordVideo("XP8_Dailer_019");
		launchDailer();
		selectPage(Locators_Dailer.contactsIcon);
		enterSearchContact(data.get("SearchName"));
		callSearchName(Locators_Dailer.saveContactName );
		validateDailCall();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=20,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_020_Validate_PerformCall_RecentList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_020============");
		startAdbLog("XP8_Dailer_020");
		recordVideo("XP8_Dailer_020");
		launchDailer();
		selectPage(Locators_Dailer.speedDail);
		callSearchName(Locators_Dailer.contactTitle);
		validateDailCall();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=21,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_021_Validate_PerformCall_HistoryList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_021============");
		startAdbLog("XP8_Dailer_021");
		recordVideo("XP8_Dailer_021");
		launchDailer();
		selectPage(Locators_Dailer.callHistory);
		callSearchName(Locators_Dailer.CallIcon);
		validateDailCall();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=22,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_022_Validate_CallDetails(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_022============");
		startAdbLog("XP8_Dailer_022");
		recordVideo("XP8_Dailer_022");
		launchDailer();
		dailCallUsingDailPad(data.get("DailNumber"));
		dailcall();
		endCall();
		selectPage(Locators_Dailer.callHistory);
		selectCallDetails();
		validateCallDetails();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=24,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_026_Validate_Export_To_VCF_FileStorage(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_026============");
		startAdbLog("XP8_Dailer_026");
		recordVideo("XP8_Dailer_026");
		launchDailer();
		exportVCFContactsStorage();
		validateExportedVCF();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=25,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_027_Validate_Import_From_VCF_FileStorage(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_027============");
		startAdbLog("XP8_Dailer_027");
		recordVideo("XP8_Dailer_027");
		launchDailer();
		importVCFContact();
		launchDailer();
		selectPage(Locators_Dailer.contactsIcon);
		importVCFContact();
		validateImportedVcfContact();
		deleteimportedContacts();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=26,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_035_Validate_DailerPad_Faunctionality(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_035============");
		startAdbLog("XP8_Dailer_035");
		recordVideo("XP8_Dailer_035");
		launchDailer();
		dailCallUsingDailPad(data.get("DailNumber"));
		validateDailPadAfterUnlock(data.get("DailNumber")) ;
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=27,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_036_Validate_DailerNumber_In_Landscape(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_036============");
		startAdbLog("XP8_Dailer_036");
		recordVideo("XP8_Dailer_036");
		launchDailer();
		DailLandscapemode();
		dailCallUsingDailPad(data.get("DailNumber"));
		validateDailPadAfterUnlock(data.get("DailNumber")) ;
		BackPotraitmode();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=28,dataProvider="DailerTest", dataProviderClass=DataProviders.class)
	public void XP8_Dailer_037_Validate_Call_Inbackground_Check_DailerAccess(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Dailer_037============");
		startAdbLog("XP8_Dailer_037");
		recordVideo("XP8_Dailer_037");
		launchDailer();
		dailCallUsingDailPad(data.get("DailNumber"));
		callinBackground();
		validateSearchLogs(data.get("Text")) ;
		minWait();
		 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
}
