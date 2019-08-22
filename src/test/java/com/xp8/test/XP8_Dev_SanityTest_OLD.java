package com.xp8.test;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_Sanity_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Dev_SanityTest_OLD extends XP8_Sanity_Util {

	public static ExtentTestInterruptedException testexception;
	public ExcelReader excel;
	Properties properties;
	public Timer timer;	

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException{

		extent = new ExtentReports("src/test/resources/extentreport/XP8_Dev_SanityTest.html",true);
		//extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		
	}

	@BeforeTest
	public void setUpSystem() throws FileNotFoundException, MalformedURLException, InterruptedException, AWTException {

		properties=loadDriverAndProperties();
		Locators_XP8_Sanity loc=new Locators_XP8_Sanity(aDriver);
		Locators_BaseUtil loc1=new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);

	}

	@BeforeTest
	public void timer() {

		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if(isElementExist(Locators_XP8_Sanity.batteryFullorAppKey)) {
					clickBtn(Locators_XP8_Sanity.OK);
				}				
			}
		},  0, 10*(10*1));
	}

	@BeforeMethod
	public void beforeMethod(Method m) { 

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  m.getName()),m.getName());
	}

	@AfterMethod
	public void afterMethod(ITestResult result,Method m) throws IOException {


		if(result.getStatus()==ITestResult.FAILURE)
		{	
			String screenshot_path=captureScreenshot(m.getName());
			test.addScreenCapture(screenshot_path);		
			test.log(LogStatus.FAIL,result.getThrowable());			
		}
		extent.endTest(test);
		extent.flush();	
	}

	//====================================================================================================
	//============================================= Test Scripts =========================================
	//====================================================================================================

	@Test(priority=1, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_01_Launch_App_Menu_from_Launcher(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_01 ======================");
//		aceeptPlayprotect();
		clickBtn(Locators_XP8_Sanity.close);
		clearRecentApps();
		clickOnAppList();
		validate_Locators1(Locators_XP8_Sanity.searchApps);
	}

	@Test(priority=2, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_02_Enable_Disable_Wifi_form_DeviceSettings(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_02 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		disable_MobileData();
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnWifi();
		setUp_And_Enable_WiFi();
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnWifi();
		disable_Switch();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_MobileData_Disable();
	}

	@Test(priority=3, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_03_Enable_Disable_Wifi_form_Notification(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_03 ======================");
		clickOnAppList();
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.wifi_OffState_QuickPanel);
		customWait(2000);
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		clickOnAppList();
		swipe_NotificationBar();
		customWait(2000);
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.wifi_OnState_QuickPanel);
		customWait(3000);
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_MobileData_Disable();
	}

	@Test(priority=4, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_04_Enable_Disable_And_Scan_BT_Devices(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_04 ======================");
		enable_BT_RefDevice();
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_BT();
		disable_Switch();
		enable_Switch();
		customWait(4000);
		validate_BT_Devices(); 
		disable_Switch();
		minWait();
		validate_Locators1(Locators_XP8_Sanity.BT_Disable_Text);
		enable_BT_RefDevice(); // With the same Method user Disable the BT.
	}

	@Test(priority=5,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_05_Device_is_Charging_if_USB_connected(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_05 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Battery();
		validate_BatteryCharging_Or_Full();
	}

	@Test(priority=6,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_06_Check_if_SDCard_get_Detected(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_06 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Storage();
		validate_Locators1(Locators_XP8_Sanity.portable_Storage);
	}

	@Test(priority=7,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_07_Verify_Able_to_make_GSM_Calls(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_07 =======================");
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(3000);
		end_Call();
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}

	@Test(priority=8,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_08_Verify_Able_to_Send_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_08 =======================");
		endCall_RefDevice();
		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			clearSMSPermissions();
			create_NewSMS(refNum, dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){
			launch_APP(Locators_XP8_Sanity.messages);
			create_NewSMS1(refNum, dt.get("message"));
			clickOn_Send1();
			validate_SentMessage();
			delete_SMS1();
		}
	}

	@Test(priority=9,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_09_IMEI_Displsy_By_Dialing_USSD_code(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_09 =======================");
		launch_APP(Locators_XP8_Sanity.phone);
		dailNumber(dt.get("ussd"));
		validate_Locators1(Locators_XP8_Sanity.imei_Popup);
		clickBtn(Locators_XP8_Sanity.OK);
	}

	@Test(priority=10,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_10_Ensure_User_can_BrowseInternet(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_10 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnWifi();
		setUp_And_Enable_WiFi();
		clickOn_BackBtn();
		enable_MobileData();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
	}

	@Test(priority=11,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_11_Check_user_can_Add_contact_in_Phonebook(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_11 =======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		setDefaultAccount_Contact();
		deleteContact_IfPresent(dt.get("contactName"));
		//For Below Method user should provide 1 to save for phone and 2 for SIM in first argument.
		add_NewContact(1,dt.get("contactName"),refNum,dt.get("contactEmail"),dt.get("address"));
		validate_Added_Contact(dt.get("contactName"));
	}

	@Test(priority=12,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_12_Check_Can_create_Group_in_Contacts(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_12 =======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		clickOn_AddGroup();
		create_GroupDetails(dt.get("groupName"), dt.get("contactName"));
		validate_GroupName(dt.get("groupName"));
		delete_Group();
		delete_Contact(dt.get("contactName"));
	}

	@Test(priority=13,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_13_Check_Sound_Recording_is_Working(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_13=======================");
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		clearAllow();
		clickOn_Record();
		clearAllow();
		customWait(20000);		
		clickOn_StopRecord();
		clickOn_Save(data.get("fileName"));
		validate_SoundRecList(data.get("fileName"));
		delete_SoundRecList();
	}

	@Test(priority=14,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_14_Verify_EnableAndDisable_FlightMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_14=======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_More();
		enable_AirplaneMode();
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Enable(refNum);
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_More();
		disable_AirplaneMode();
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Disable(refNum);
		end_Call();
	}

	@Test(priority=15,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_15_Check_User_can_Add_Google_Account(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_15=======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_More();
		disable_AirplaneMode();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_AddGoogleAccount();
		add_GoogleAccount(dt.get("emailId"), dt.get("password"));
		validate_GoogleAcccount(dt.get("emailId"));
	}

	@Test(priority=16,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_16_Check_able_to_Download_APK_from_Playstore(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_16=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.PlayStore);
		customWait(3000);
		install_App(dt.get("appName"));
		validate_Installed_App(dt.get("appName"));
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.PlayStore);
		unInstall_App(dt.get("appName"));
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnAccounts();
		remove_GoogleAcccount();	
	}

	@Test(priority=17,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_17_Check_Able_Receive_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_17 =======================");
		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));
			customWait(6000);
			validate_RecievedMessage();
			delete_SMS();
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){
			launch_APP(Locators_XP8_Sanity.messages);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));
			customWait(6000);
			validate_RecievedMessage();
			delete_SMS1();
		}		
	}

	@Test(priority=18,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_18_Check_for_Back_Camera_Working(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_18 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		clickOnCapture();
		customWait(10000);
		validate_ADB_Logs(fN, "android.hardware.action.NEW_PICTURE");

	}

	@Test(priority=19,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_19_Check_for_Front_Camera_Working(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_19 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.camera);
		changeTo_FrontCam();
		clickOnCapture();
		customWait(10000);
		validate_ADB_Logs(fN, "android.hardware.action.NEW_PICTURE");	
	}

	@Test(priority=20,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_20_Validate_Turn_On_Off_FM(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_20=======================");
		String fN=startAdbLog();
		launch_APP(Locators_XP8_Sanity.fmRadio);
		clickOnFm_ON_OFF();
		customWait(2500);
		clickOnFm_ON_OFF();
		customWait(2500);
		clickOnFm_ON_OFF();
		customWait(8000);
		validate_ADB_Logs(fN, "FMRxStarting ---> NEW-STATE : FMRxOn", "FMTurningOff ---> NEW-STATE : FMOff");		
	}

	@Test(priority=21,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_21_Validate_Tune_FMStation(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_21=======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.fmRadio);
		clickOnFm_ON_OFF();
		customWait(3000);
		scan_AllStations();
		clickOnFm_ON_OFF();
		customWait(10000);
		validate_ADB_Logs(fN, "searchStations: CURRENT-STATE : FMRxOn ---> NEW-STATE : SearchInProg");
		clickOnHomeBtn();
	}
	
	
/*
 
 
 @sashi.p
 
 */


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
}

