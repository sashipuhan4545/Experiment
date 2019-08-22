package com.xp8.ATTStability;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.ParseException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.ATTUtil.Locators_Stability;
import com.xp8.ATTUtil.Stability_Telephony_Util;
import com.xp8.util.BaseUtil;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;




import android.os.Environment;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ATT_Stability_Telephony_Test extends Stability_Telephony_Util{


	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public Timer timer1 ;
	boolean value= true;
	public int itr = 10;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, org.json.simple.parser.ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_ATT_Stability_Telephony_TestReport.html", true); //Provide Desired Report Directory Location and Name
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
		Locators_Stability loc=new Locators_Stability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}



	@BeforeTest
	public void timer() {

		timer1 = new Timer(); 
		timer1.schedule( new TimerTask()
		{ 
			public void run() {
				if(isElementExist(Locators_Stability.batteryFullorAppKey)) {
					clickBtn(Locators_Stability.OK);			
				}

			}
		}, 0, 10*(100*1));
	}


	@Test(priority=1,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_01_Stability_Setting_Up_Preconditions_Telephony(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_01============");
		String fN = startRIL_Log();
		deleteDirectory();
		clearRecentApps();
		checkSimCardAvailability();
		installAPK();
		performAction();	

		value =   validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
		System.out.println(value);

		if(value) {
			memoryFill();
			launch_an_app("contacts");
			deleteContacts();		
			for(int i=1; i<=itr;i++) {
				customWait(1000);
				contactCreation("ContactName"+i);
				pressBack();					
			}		
			test.log(LogStatus.PASS, "Precondition all set");
		}	
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}

	}


	@Test(priority=2,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Stability_Validate_MO_VoLTE_voicecall_from_phonebook(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_02============");
		startLog("ATT_Stability_02");
		recordVideo("ATT_Stability_02");
		value = true;
		if(value) {
			for(int i=1; i<=itr; i++) {
				launch_an_app("contacts");	  
				searchContact("ContactName"+i);
				initiateCall();
				customWait(7000);
				validateCallLog("called" ,i,"Phonebook");
				pressBack();	
				customWait(3000);	      
			}
			test.log(LogStatus.PASS, "Test case status is Passed");	
		}
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}

	}


	@Test(priority=3,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Stability_Validate_MO_VoLTE_voicecall_from_CallHistoryList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_03============");
		startLog("ATT_Stability_03");
		recordVideo("ATT_Stability_03");
		if(value) {
			for(int i=1; i<=itr; i++) {
				launch_an_app("phone");
				selectPage(Locators_Stability.speedDail);
				phonebookCall("ContactName"+i);
				validateCallLog("called", i ,"Call History");
				pressBack();	
				customWait(3000);

			}
			test.log(LogStatus.PASS, "Test case status is Passed");	
		}
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}

	}


	@Test(priority=4,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Stability_Validate_MT_VoLTE_voicecall(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_04============");
		startLog("ATT_Stability_04");
		recordVideo("ATT_Stability_04");
		if(value) {
			for(int i=1; i<=itr; i++) {
//				launch_an_app("phone");
				make_Call_from_RefDev();
				customWait(10000);
				recieve_Call_PrimaryDev("called","ATT_Stability_03", i ,"Call History");
				customWait(4000);
				endCall_RefDevice();
			}
		}
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
		deleteDirectory();
	}






}
