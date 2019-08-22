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
import com.xp8.ATTUtil.Stability_Browser_Util;
import com.xp8.ATTUtil.Stability_Messaging_Util;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.HomeController;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ATT_Stability_Messaging_Test extends Stability_Messaging_Util{


	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public Timer timer1 ;

	boolean value = true;
	public int itr = 2;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, org.json.simple.parser.ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_ATT_Stability_Messaging_TestReport.html", true); //Provide Desired Report Directory Location and Name
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

/*	@Test(priority=1,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_01_Stability_Setting_Up_Preconditions_Messaging(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_01_Preconditions============");

		String fN = startRIL_Log();
		deleteDirectory();
		clearRecentApps();
		checkSimCardAvailability();
		installAPK();
		performAction();		
		value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
		System.out.println(value);


		if(value) {
//			memoryFill();
			launch_an_app("gallery");
			cleargalleryPermission();
			delete_All_Photos();
			launch_an_app("settings");
			remove_GoogleAcccount();
			navigateTo_AddGoogleAccount();
			add_GoogleAccount(data.get("emailId"), data.get("password"));	
			launch_an_app("gmail");
			clear_GmailPermission();
			navigate_MailOptns(Locators_Stability.drafts_Image) ;
			navigate_MailOptns(Locators_Stability.drafts_Video) ;
			attachFile(data.get("fileName"));	
			}
			
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
	}
	
	
	@Test(priority=2,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Stability_Send_SMS_To_Device_under_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_02_Send_SMS============");
	
	
        itr=5;
		if(value) {
			for(int i=1; i<=itr ; i++) {
				launch_an_app("messaging");		
				minWait();
				delete_All_Threads();
				sendSMS_fromRefDevice(data.get("typeMessage1"));
				customWait(4000);
				validate_RecievedMessage(i);
			    delete_SMS();
			}
			}
			
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
	}
	
	

	
	@Test(priority=3,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Stability_Send_SMS_with_Maximum_NumberOfCharacters_Device_under_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_03_SMS_with_Maximum_NumberOfCharacters============");
	
        itr=2;
		if(value) {
			for(int i=1; i<=itr ; i++) {
				launch_an_app("messaging");				
				delete_All_Threads();
				navigateTo_NewMessage();
				type_New_Message(HomeController.REFERENCEDEVMDN, data.get("typeMessage3"));
				validate_CharacterAndPageNumber(Locators_Stability.zero_Characters_FirstPage,data.get("expectedChar&PageNum3"));
				clickOn_Send();	
				validate_SentMessage(i,"Maximum Character Send SMS") ;
			}
			}
			
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
	}
	
	@Test(priority=4,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Stability_Send_MMS_with_Picture_Attachment_Device_under_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_04_MMS_with_Picture_Attachment============");
	
	
        itr=5;
		if(value) {
			for(int i=1; i<=itr ; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			navigateAttachOthers();
			clickOnAttach_Options(Locators_Stability.Pictures);
			clickBtn(Locators_Stability.pictureattach);
			customWait(1000);
			type_Message(data.get("typeMessage"));
			enter_ToField(HomeController.REFERENCEDEVMDN);
			clickOn_Send();	
			validate_SentMessage(i,"MMS") ;
			customWait(8000);	   
			}
			}
			
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
	}
	
	
	@Test(priority=5,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_05_Stability_Send_MMS_with_Audio_Attachment_Device_under_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_05_MMS_with_Audio_Attachment============");
	
	
        itr=2;
		if(value) {
			for(int i=1; i<=itr ; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			navigateAttachOthers();
			clickOnAttach_Options(Locators_Stability.Audio);
			selectAudio();
			customWait(1000);
			type_Message(data.get("typeMessage"));
			enter_ToField(HomeController.REFERENCEDEVMDN);
			clickOn_Send();	
			validate_SentMessage(i,"Send with AUdio Attach SMS") ;
			customWait(8000);	   
			}
			}			
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
	}
	

	@Test(priority=6,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_06_Stability_Send_MMS_with_Video_Attachment_Device_under_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_06_MMS_with_Video_Attachment============");

        itr=2;
		if(value) {
			for(int i=1; i<=itr ; i++) {
			launch_an_app("messaging");
			delete_All_Threads();
			navigateTo_NewMessage();
			navigateAttachOthers();
			clickOnAttach_Options(Locators_Stability.videos);
			clickBtn(Locators_Stability.pictureattach);
			customWait(1000);
			type_Message(data.get("typeMessage"));
			enter_ToField(HomeController.REFERENCEDEVMDN);
			clickOn_Send();	
			validate_SentMessage(i,"Send with AUdio Attach SMS") ;
			customWait(8000);	   
			}
			}		
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
	}*/
	
	
	@Test(priority=7,dataProvider="XP8_ATTStability", dataProviderClass=DataProviders.class)
	public void XP8_TC_07_Stability_Open_MMS_with_Audio_Attachment_Device_under_Test(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_07_MMS_with_Audio_Attachment============");

        itr=2;
		if(value) {
			for(int i=1; i<=itr ; i++) {
				launch_an_app("messaging");
				delete_All_Threads();
				navigateTo_NewMessage();
				navigateAttachOthers();
				clickOnAttach_Options(Locators_Stability.Audio);
				selectAudio();			
			}
			}		
		else {
			test.log(LogStatus.SKIP, "VoLTE SIM is not Enabled");
		}
	}
	
	
	
}
