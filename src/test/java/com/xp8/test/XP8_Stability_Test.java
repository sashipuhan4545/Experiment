package com.xp8.test;

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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.BaseUtil;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.HomeController;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Dailer;
import com.xp8.util.Locators_SoundRec;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.XP8_Stability_Util;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Stability_Test extends XP8_Stability_Util{


	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =3;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_DeviceStability_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();	
		/*String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
		test.assignCategory("Build #:"+BuildNumber);*/

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
	public void XP8_TC_01_Stability_Validate_Contact_Creation(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
   {
	   APP_LOGS.info("===========XP8_Stability_01============");
	   startAdbLog("XP8_Stability_01");
	   recordVideo("XP8_Stability_01");
	   launch_an_app("contacts");
	   setDefaultAccount_Contact();
	   deleteContacts();
	   for(int i=1; i<=itr;i++) {
		   createContact("Test"+i, HomeController.REFERENCEDEVMDN);
		   validateContactCreation(i);
	   }	   
	   test.log(LogStatus.PASS, "Test case status is Passed");	
   }


	@Test(priority=2,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Stability_Validate_Send_MO_IMS_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_02============");
		startLog("XP8_Stability_02");
		recordVideo("XP8_Stability_02");

		String fN = startRIL_Log();
		performAction();

	    value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
		System.out.println(value);
		if (value) {

			for(int i=1; i<=itr;i++) {

				if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
					launch_an_app("messaging");
					clearSMSPermissions();
					delete_All_Threads();
					navigateTo_NewSMS();
//					create_NewSMS(refNum, data.get("typeMessage"));
					type_New_Message(HomeController.REFERENCEDEVMDN, data.get("typeMessage"));
					clickOn_Send();
					validate_SentMessage(i) ;
					delete_SMS();
				} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-15.")){
					launch_an_app("messaging");
					clearSMSPermissions();
					delete_All_Threads();
					create_NewSMS1(refNum, data.get("typeMessage"));
					clickOn_Send1();
					validate_SentMessage(i) ;
					delete_SMS1();
				}

		   }
			test.log(LogStatus.PASS, "Test case status is Passed");	
		}
			else {
				test.log(LogStatus.SKIP, "NT: IMS is not Enabled");
			}

	}


	@Test(priority=3,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Stability_Validate_Send_MO_Non_IMS_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_03============");
		startLog("XP8_Stability_03");
		recordVideo("XP8_Stability_03");

			System.out.println(value);
			if (!value) {
				for(int i=1; i<=itr;i++) {
				if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
					launch_an_app("messaging");
					clearSMSPermissions();
					delete_All_Threads();
					navigateTo_NewSMS();
//					create_NewSMS(refNum, data.get("typeMessage"));
					type_New_Message(HomeController.REFERENCEDEVMDN, data.get("typeMessage"));
					clickOn_Send();
					validate_SentMessage(i) ;
					delete_SMS();
				} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-15.")){
					launch_an_app("messaging");
					clearSMSPermissions();
					delete_All_Threads();
					create_NewSMS1(refNum, data.get("typeMessage"));
					clickOn_Send1();
					validate_SentMessage(i) ;
					delete_SMS1();
				}

		   }
			test.log(LogStatus.PASS, "Test case status is Passed");	
			   } else {
				   test.log(LogStatus.SKIP, "IMS is enabled");
			   }   
	}


	@Test(priority=4,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Stability_Validate_MO_SMS_Maximum_NumberOfCharacters(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_04============");
		startAdbLog("XP8_Stability_04");
		recordVideo("XP8_Stability_04");
			for(int i=1; i<=itr;i++) {
				launch_an_app("messaging");				
				delete_All_Threads();
				navigateTo_NewMessage();
				type_New_Message(HomeController.REFERENCEDEVMDN, data.get("typeMessage3"));
				validate_CharacterAndPageNumber(Locators_DeviceStability.zero_Characters_FirstPage,data.get("expectedChar&PageNum3"));
				clickOn_Send();	
				validate_SentMessage(i) ;
		}
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}


	@Test(priority=5,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_05_Stability_Validate_MT_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_05============");
		startAdbLog("XP8_Stability_05");
		recordVideo("XP8_Stability_05");
			for(int i=1; i<=itr;i++) {
				if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
					launch_an_app("messaging");
					minWait();
					delete_All_Threads();
					sendSMS_fromRefDevice(data.get("typeMessage1"));
					validate_RecievedMessage(i);
//					delete_All_Threads();
				} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-15.")){
					launch_an_app("messaging");
					minWait();
					delete_All_Threads();
					sendSMS_fromRefDevice(data.get("typeMessage1"));
					validate_RecievedMessage(i);
//					delete_All_Threads();
				}		
//				launch_an_app("messaging");
//				delete_All_Threads();
//				navigateTo_NewMessage();
//				type_New_Message(HomeController.REFERENCEDEVMDN, data.get("typeMessage1"));
//				validate_CharacterAndPageNumber(Locators_DeviceStability.zero_Characters_FirstPage,data.get("expectedChar&PageNum1"));
//				clickOn_Send();	
//				validate_SentMessage(i) ;
//				customWait(8000);	   
		}
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}



	@Test(priority=6,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_06_Stability_Validate_MO_MMS_With_Picture(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_06============");
		startAdbLog("XP8_Stability_06");
		recordVideo("XP8_Stability_06");
			for(int i=1; i<=itr;i++) {
				launch_an_app("messaging");
				delete_All_Threads();
				navigateTo_NewMessage();
				navigateAttachOthers();
				clickOnAttach_Options(Locators_DeviceStability.capturePicture);
				capturePic_ADD();
//				validate_AnyAdded_Attachment(Locators_Stability.image_Attachment);
				type_Message(data.get("typeMessage"));
				enter_ToField(HomeController.REFERENCEDEVMDN);
				clickOn_Send();	
				validate_SentMessage(i) ;
//				press_BackKey();
				customWait(8000);	   
		}
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=7,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_07_Stability_Validate_MO_MMS_With_Video(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_07============");
		startAdbLog("XP8_Stability_07");
		recordVideo("XP8_Stability_07");
			for(int i=1; i<=itr;i++) {
				launch_an_app("messaging");
				delete_All_Threads();
				navigateTo_NewMessage();
				navigateAttachOthers();
				clickOnAttach_Options(Locators_DeviceStability.captureVideo);
				captureVideo_ADD();
//				validate_AnyAdded_Attachment(Locators_Stability.video_Attachment);
				launch_an_app("messaging");
				type_Message(data.get("typeMessage"));
				enter_ToField(HomeController.REFERENCEDEVMDN);
				clickOn_Send();	
				validate_SentMessage(i) ;
//				press_BackKey();
				customWait(8000);	   
		}
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}	


	  @Test(priority=8,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_08_Stability_Validate_MOVoiceCall_CSFB_from_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
   {
	   APP_LOGS.info("===========XP8_Stability_08============");
	   startLog("XP8_Stability_08");
	   recordVideo("XP8_Stability_08");

		System.out.println(value);
		if (!value) {
	   for(int i=1; i<=itr;i++) {
		   launch_an_app("contacts");
		   searchContact("Test"+i);
		   initiateCall();		
		   validateCallLog("called", i,"contacts");
		} 
	   test.log(LogStatus.PASS, "Test case status is Passed");	
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is Enabled");
		}   

   }

   @Test(priority=9,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
  	public void XP8_TC_09_Stability_Validate_MOVoiceCall_CSFB_from_CallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
   {
	   APP_LOGS.info("===========XP8_Stability_09============");
	   startLog("XP8_Stability_09");
	   recordVideo("XP8_Stability_09");
		System.out.println(value);
	   if (!value) {
		   for(int i=1; i<=itr;i++) {	
			   launch_an_app("phone");
			   selectPage(Locators_DeviceStability.callHistry_notification);
			   callHistory() ;
			   validateCallLog("called", i,"Call History");
//			   endcall();
		   } 
		   test.log(LogStatus.PASS, "Test case status is Passed");	
	   } else {
		   test.log(LogStatus.SKIP, "NT: IMS is enabled");
	   }   
   }


	@Test(priority=10,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_10_Stability_Validate_MO_IMSVoiceCall_from_Contacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_10============");
		startLog("XP8_Stability_10");
		recordVideo("XP8_Stability_10");
		System.out.println(value);
		if (value) {
			for(int i=1; i<=itr;i++) {
				launch_an_app("contacts");	  			
				searchContact("Test"+i);
				initiateCall();
				validateCallLog("called" ,i,"Phonebook");
			}
			test.log(LogStatus.PASS, "Test case status is Passed");	
		}
		else {
			test.log(LogStatus.SKIP, "NT: IMS is not enabled");
		}	   

	}

	@Test(priority=11,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_11_Stability_Validate_MO_IMSVoiceCall_from_CallHistory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_11============");
		startLog("XP8_Stability_11");
		recordVideo("XP8_Stability_11");  

		System.out.println(value);
		if (value) {
			for(int i=1; i<=itr;i++) {	
				launch_an_app("phone");
				selectPage(Locators_DeviceStability.callHistry_notification);
				callHistory() ;			   
				validateCallLog("called", i,"Call History");
			}
			test.log(LogStatus.PASS, "Test case status is Passed");	
		}
		else {
			test.log(LogStatus.SKIP, "NT: IMS is not enabled");
		}	    

	}


	@Test(priority=12,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_12_Stability_Validate_WiFi_ConnectDisconnect_SSID(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_12============");
		startAdbLog("XP8_Stability_12");
		recordVideo("XP8_Stability_12");
		launch_an_app("settings");
		selectWIFIOptn();
		enableFeature(Locators_DeviceStability.enabled,Locators_DeviceStability.disabled,Locators_DeviceStability.switch_Title);
		minWait();
		disconnectSSIDifConnected();
		for(int i=1; i<=itr;i++) {	
			launch_an_app("settings");
			selectWIFIOptn();
			selectRefresh();
			selectSSIDwifi();	
			enterPassword();
			validateWifiConnect(i);
			selectWIFIOptn();
			disconnectSSIDifConnected();
		}	   
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=13,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_13_Stability_Validate_WiFi_Turn_ON_OFF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_13============");
		startAdbLog("XP8_Stability_13");
		recordVideo("XP8_Stability_13");
		launch_an_app("settings");	  
		for (int i = 1; i <=itr; i++) {
			selectWIFIOptn();
			validate_ON_OFF_WiFiFeature(Locators_DeviceStability.WiFi_feature,i);
			customWait(3000);
		}			   
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=14,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_14_Stability_Validate_Browse_Visit_WebPage(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_14============");
		startAdbLog("XP8_Stability_14");
		recordVideo("XP8_Stability_14");
		launch_an_app("settings");	
		enable_MobileData();
		launch_an_app("browser");	
		 acceptDefault();
//		for(int i=0; i<=itr;i++) {
			for (int j = 1; j <=11; j++) {
				enterUrl(data.get("WebSite"+j));
				validateUrlEntered(j);
				customWait(5000);
			}		
//		}
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}


}
