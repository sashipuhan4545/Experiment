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
import com.xp8.ATTUtil.Stability_Email_Util;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Stability_Email_Test_Orio extends Stability_Email_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public Timer timer1 ;

	boolean value= true;
	public int itr = 1;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, org.json.simple.parser.ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_ATT_Stability_Email_TestReport.html", true); //Provide Desired Report Directory Location and Name
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

		//		Runtime.getRuntime().exec("adb push src/test/resources/StorageFile/AttachFile /storage/emulated/0");
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
	public void XP8_TC_01_Stability_Setting_Up_Preconditions_Email(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_01_Preconditions============");
		recordVideo("ATT_Stability_Email_01");

		String fN = startRIL_Log();
		deleteDirectory();
		clearRecentApps();
		launch_an_app("settings");
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		ON_Switch("data");
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		scrollToTextContains("Wi");
		selectSSIDwifi();	
		enterPassword();
		checkSimCardAvailability();
		performAction_O();	
		//		value =   validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
		System.out.println(value);


		if(value) {
			launch_an_app("settings");
			remove_GoogleAcccount_Orio();
			navigateTo_AddGoogleAccount_Orio();

			add_GoogleAccount(data.get("emailId"), data.get("password"));			
			test.log(LogStatus.PASS, "Precondition all set\n"
					+ "1. Memory filled upto 92%\n"
					+ "2. Google Account is added\n");
			customWait(2000);
		}	
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}	
	}

	@Test(priority=2,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Stability_Send_Email_Without_Attachment(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_02_Send_Email============");
		recordVideo("ATT_Stability_Email_02");
		value = true;
		if(value) {
			launch_an_app("gmail");
			clear_GmailPermission();
			navigate_MailOptns() ;
			deleteAllmails();
			click_NewMail();
			compose_NewMail(data.get("TO"), data.get("subject"), data.get("mailContent"));
			send_Mail();	
			navigate_MailOptns() ;
			validate_Mail(data.get("subject"),1);
			for(int i=2; i<=itr;i++) {
				System.out.println("Im in");
				forwardMail(data.get("TO"),data.get("subject"), data.get("forwardContent"),Locators_Stability.clickwithout_Mail);
				send_Mail();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				validate_Mail(data.get("subject"), i);			
			}	
			deleteAllmails();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		}	
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
	}


	@Test(priority=3,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Stability_Send_Email_With_Attachment(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_03_Send_Email============");
		recordVideo("ATT_Stability_Email_03");
		value = true;
		if(value) {	
			attachFile(data.get("fileName"));	
			compose_NewMail(data.get("TO"), data.get("subject"), data.get("mailContent"));		
			send_Mail();	
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			launch_an_app("gmail");
			navigate_MailOptns() ;
			validate_Mail(data.get("subject"),1);
			for(int i=2; i<=itr;i++) {
				System.out.println("Im in");
				forwardMail(data.get("TO"),data.get("subject"), data.get("forwardContent"),Locators_Stability.click_Mail);			
				send_Mail();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(3000);
				validate_Mail(data.get("subject"), i);			
			}	
			deleteAllmails();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		}	
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}	
	}


	@Test(priority=4,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Stability_Validate_Open_Email(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_04_Send_Email============");
		recordVideo("ATT_Stability_Email_04");

		if(value) {	

			launch_an_app("gmail");
			deleteAllmails();
			click_NewMail();
			compose_NewMail(data.get("TO"), data.get("subject"), data.get("mailContent"));
			send_Mail();	
			navigate_MailOptns() ;
			for(int i=1; i<=itr;i++) {
				minWait();
				clickBtn(Locators_Stability.receive_Mail);
				minWait();
				validate_OpenMail(data.get("subject"), i);	
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			}	
			deleteAllmails();
		}	
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
		launch_an_app("settings");
		remove_GoogleAcccount_Orio();
	}
}
