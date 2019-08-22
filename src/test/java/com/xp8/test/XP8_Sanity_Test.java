package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
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
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_Sanity_Util;

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Sanity_Test extends XP8_Sanity_Util {

	public static ExtentTestInterruptedException testexception;
	public ExcelReader excel;
	Properties properties;
	public Timer timer1;

	@BeforeSuite
	public void beforeSuite() throws InterruptedException, FileNotFoundException, IOException, ParseException  {

		//Provide Desired Report Directory Location and Name.
		extent = new ExtentReports("src/test/resources/extentreport/XP8_SanityTest.html", true); 
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.1").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();
	} 

	@BeforeTest
	public void setupSys() throws InterruptedException, AWTException, IOException {

		properties=loadDriverAndProperties();
		Locators_XP8_Sanity loc=new Locators_XP8_Sanity(aDriver);
		Locators_BaseUtil loc1=new Locators_BaseUtil(aDriver);
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
				if(isElementExist(Locators_XP8_Sanity.batteryFullorAppKey)) { 
					clickBtn(Locators_XP8_Sanity.OK);
				}
			}
		}, 0, 10*(100*1));
	}

	@BeforeMethod()
	public  void beforeMethod(Method method) {

		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
		test.assignAuthor("Chandan. A"); //Test Script Author Name.
	}

	@AfterMethod()
	public void tearDown(ITestResult result,Method method) throws IOException, InterruptedException	{

		if(result.getStatus()==ITestResult.FAILURE)
		{	
			String screenshot_path=captureScreenshot(method.getName());
			test.addScreenCapture(screenshot_path);		
			test.log(LogStatus.FAIL,result.getThrowable());			
		}
		extent.endTest(test);
		extent.flush();
	}


	// ===================================================================================================================================
	// ============================================== XP8 Sanity Test Scripts ============================================================
	// ===================================================================================================================================

	
	@Test(priority=1,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_001_Verify_Date_TimeAndTimeZone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_001=======================");
//		aceeptPlayprotect();
		clickBtn(Locators_XP8_Sanity.close);
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_DateAndTime();
		enableAuto_DateTime_Timezone();
		clickOn_BackBtn();
		validate_DateTime_And_Timezone();
	}

	@Test(priority=2,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_002_Verify_ChargingStatusOfBatteryIcon(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("=====================XP8_Sanity_002=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Battery();
		validate_BatteryCharging_Or_Full();
	}

	@Test(priority=3,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_003_Verify_EnableAndDisable_FlightMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_003=======================");
		clearRecentApps();
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

	@Test(priority=4,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_004_Verify_OperatorName_LockScreenAndHomeScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("======================XP8_Sanity_004=======================");
		clearRecentApps();
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_More();
		disable_AirplaneMode();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_CellularNetworks();
		clickOnAPNs();
		validate_APN_Diaplay();
		validate_OperatorName_Lockscreen();
		unlock_Phone();
	}

	@Test(priority=5,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_005_Verify_ON_And_OFF_WiFi_Quick_Panel(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_005=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		disable_MobileData();
		swipe_NotificationBar();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.wifi_OnState_QuickPanel);
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		validate_MobileData_Disable();
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.wifi_OffState_QuickPanel);
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(data.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
	}

	@Test(priority=6,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_006_Verify_ON_And_OFF_Bluetooth_Quick_Panel(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_006=======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		disable_Bluetooth();
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.BT_OffState_QuickPanel); 
		minWait();
		disable_Switch_QuickPanel(Locators_XP8_Sanity.switch_On_State);
		customWait(4000);
		validate_ADB_Logs(fN, "BT_VND_OP_POWER_CTRL: On","BT_VND_OP_POWER_CTRL: Off");
	}

	@Test(priority=7,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_007_Verify_ON_And_OFF_DataService_Quick_Panel(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_007=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		disable_MobileData();
		swipe_NotificationBar();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.wifi_OnState_QuickPanel);
		clickOn_MobileData_QuickPanel();
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		validate_And_BrowseIn_Chrome(data.get("url"), Locators_XP8_Sanity.googleCoIn_Text,Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		swipe_NotificationBar();
		clickOn_MobileData_QuickPanel();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_MobileData_Disable();
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.wifi_OffState_QuickPanel);
		customWait(4000);
		clickOnHomeBtn();
	}

	@Test(priority=8,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_008_Verify_ON_And_OFF_AirplaneMode_Quick_Panel(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_008=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.phone);
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.airplane_OffState_QuickPanel);
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Enable(refNum);
		swipe_NotificationBar();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.airplane_OnState_QuickPanel);
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Disable(refNum);
		end_Call();
	}

	@Test(priority=9,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_009_Validate_Volume_Up_And_Down_MOCall(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_009=======================");
		String fN = startAdbLog();
		clearRecentApps();
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_More();
		disable_AirplaneMode();
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(6000);
		for (int i = 0; i <=9; i++) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_UP);
			minWait();			
		}
		for (int i = 0; i <=9; i++) {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_VOLUME_DOWN);
		}
		end_Call();
		customWait(3000);
		validate_ADB_Logs(fN,"level_changed STREAM_VOICE_CALL 1","level_changed STREAM_VOICE_CALL 5", "level_changed STREAM_VOICE_CALL 10");
	}

	@Test(priority=10,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_010_Verify_ON_And_OFF_AutoRotate_Quick_Panel(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("====================== XP8_Sanity_010 =======================");
		clearRecentApps();
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.settings);
		disable_AutoRotate();
		swipe_NotificationBar();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.autoRotate_On);
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.autoRotate_Off);
		validate_AutoRotate();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.autoRotate_On);
		minWait();
		clickOnHomeBtn();
	}

	@Test(priority=11,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_011_Verify_Launch_SettingsFromQuickPanel(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_011=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.phone);
		swipe_NotificationBar();
		click_SettingIcon_QuickPanel();
		validate_SettingsLaunch();
	}	

	@Test(priority=12,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_012_Verify_the_CallLogs(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_012=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.phone);
		clear_Call_History();
		dailCallUsingDailPad(refNum);
		customWait(12000);
		end_Call();
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}

	@Test(priority=13,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_013_Verify_Able_Initiate_MO_Calls_From_CallLog(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_013=======================");
		clearRecentApps();		
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		clear_Call_History();
		dailCallUsingDailPad(refNum);
		end_Call();
		launch_APP(Locators_XP8_Sanity.phone);
		clickOn_CallBtn();
		customWait(5000);
		end_Call();
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}

	@Test(priority=14,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_014_Verify_Able_To_Add_NewContact(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_014=======================");
		clearRecentApps();
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.contacts);
		setDefaultAccount_Contact();
		deleteContact_IfPresent(data.get("contactName"));
		//For Below Method user should provide 1 to save for phone and 2 for SIM in first argument.
		add_NewContact(1,data.get("contactName"),refNum,data.get("contactEmail"),data.get("address"));
		validate_Added_Contact(data.get("contactName"));
	}

	@Test(priority=15,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_015_Verify_Able_Add_NewContact_SIMcard(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_015=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.contacts);
		deleteContact_IfPresent(data.get("contactName"));
		//For Below Method user should enter 1 to save for phone memory and 2 to save for SIM memory in First Argument.
		add_NewContact(2,data.get("contactName"),refNum,data.get("contactEmail"),data.get("address"));
		validate_Added_Contact(data.get("contactName"));
	}

	@Test(priority=16,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_016_Verify_User_CanMake_Call_AnyContact(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_016=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.contacts);
		call_FromContact(data.get("contactName"));
		clearAllow();
		customWait(5000);
		end_Call();
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Num_In_CallLog(data.get("expectedNum"));
	}

	@Test(priority=17,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_017_Verify_UserCan_Delete_DesiredContact(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_017=======================");
		clearRecentApps();
		endCall_RefDevice();
		// Deleting Contact from Phone Memory.
		launch_APP(Locators_XP8_Sanity.contacts);
		delete_Contact(data.get("contactName"));
		validate_ContactDelete(data.get("contactName"));
	}

	@Test(priority=18,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_018_VerifyUserCan_Delete_DesiredContact_SIMcard(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_018=======================");
		clearRecentApps();
		//Deleting Contact from SIM Memory.
		launch_APP(Locators_XP8_Sanity.contacts);
		delete_Contact(data.get("contactName"));
		validate_ContactDelete(data.get("contactName"));
		launch_APP(Locators_XP8_Sanity.phone);
		clear_Call_History();
	}

	@Test(priority=19,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_019_Verify_AbleTo_Send_SinglePage_SMS(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("======================XP8_Sanity_019=======================");
		clearRecentApps();
		if(p_b_No.contains("-10.")||p_b_No.contains("-30.")){

			launch_APP(Locators_XP8_Sanity.messaging);
			clearSMSPermissions();
			create_NewSMS(refNum,data.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
			launch_APP(Locators_XP8_Sanity.contacts);
			deleteContact_IfPresent(data.get("contactName"));
			//For Below Method user should provide 1 to save for phone and 2 for SIM in first Argument.
			add_NewContact(1, data.get("contactName"),refNum, data.get("contactEmail"),data.get("address") );
			launch_APP(Locators_XP8_Sanity.messaging);
			navigateTo_NewSMS();	
			enterText_MessageField(data.get("message"));
			addContactToMsg_FromContacts(data.get("contactName"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
			launch_APP(Locators_XP8_Sanity.contacts);
			deleteContact_IfPresent(data.get("contactName"));

		} else if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {

			launch_APP(Locators_XP8_Sanity.messages);
			create_NewSMS1(refNum,data.get("message"));
			clickOn_Send1();
			validate_SentMessage();
			delete_SMS1();
			launch_APP(Locators_XP8_Sanity.contacts);
			deleteContact_IfPresent(data.get("contactName"));
			// For Below Method user should provide 1 to save for phone and 2 for SIM in First Argument.
			add_NewContact(1, data.get("contactName"),refNum, data.get("contactEmail"),data.get("address") );
			launch_APP(Locators_XP8_Sanity.messages);
			navigateTo_NewSMS1();
			addContactToMsg_FromContacts1(data.get("contactName"));
			Locators_XP8_Sanity.messageField1.click();
			Locators_XP8_Sanity.messageField1.sendKeys(data.get("message"));		
			clickOn_Send1();
			validate_SentMessage();
			delete_SMS1();
			launch_APP(Locators_XP8_Sanity.contacts);
			deleteContact_IfPresent(data.get("contactName"));
		}
	}

	@Test(priority=20,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_020_Verify_AbleTo_Enable_Disable_Data_Service(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_020=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		enable_MobileData();
		swipe_NotificationBar();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.wifi_OnState_QuickPanel);
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		validate_And_BrowseIn_Chrome(data.get("url"), Locators_XP8_Sanity.googleCoIn_Text,Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		launch_APP(Locators_XP8_Sanity.settings);
		disable_MobileData();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_MobileData_Disable();
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.wifi_OffState_QuickPanel);
		customWait(4000);
		clickOnHomeBtn();
	}

	@Test(priority=21,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_021_Verify_USBtethering_BTtethering_And_WiFihotspot(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_021=======================");
		String fN = startAdbLog();
		clearRecentApps();		
		if (p_b_No.contains("-10.")) {
			launch_APP(Locators_XP8_Sanity.settings);
			enable_MobileData();
			launch_APP(Locators_XP8_Sanity.settings);
			navigateTo_TetheringAndPortableHotspot();
			enable_USBTethering();
			validate_EntitlementError();
			enable_BT_Tethering();
			validate_EntitlementError();
			enable_portableWiFi_hotspot();
			validate_EntitlementError();

		}else if (p_b_No.contains("-26.")) {

			test.log(LogStatus.PASS, "TestCase NOT Applicable for SL Operator.");

		}else if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-29.")||p_b_No.contains("-30.")) {			
			launch_APP(Locators_XP8_Sanity.settings);
			enable_MobileData();
			launch_APP(Locators_XP8_Sanity.settings);
			navigateTo_TetheringAndPortableHotspot();
			enable_BT_Tethering();
			customWait(3000);
			disable_BT_Tethering();
			customWait(3000);
			validate_ADB_Logs(fN, "setBluetoothTethering(true)","setBluetoothTethering(false)");
			enable_portableWiFi_hotspot();
			customWait(3000);
			disable_portableWiFi_hotspot();
			customWait(3000);
			validate_ADB_Logs(fN, "Tethering services running","Tethering services stopped");
			test.log(LogStatus.INFO, "Please Check manually for USB tethering.");
		}
	}

	@Test(priority=22,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_022_Verify_AbleTo_SendSMS_OnActiveVoiceCall(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_022=======================");
		clearRecentApps();
		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
			launch_APP(Locators_XP8_Sanity.phone);
			dailCallUsingDailPad(refNum);
			launch_APP(Locators_XP8_Sanity.messaging);
			navigateTo_NewSMS();
			enterText_MessageField(data.get("message"));
			enter_Num_ToField(refNum);
			clickOn_Send();
			clickOn_BackBtn();clickOn_BackBtn();
			validate_SentMessage();
			delete_SMS();
			hangUp_Call();
		} else if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
			launch_APP(Locators_XP8_Sanity.phone);
			dailCallUsingDailPad(refNum);
			launch_APP(Locators_XP8_Sanity.messages);
			navigateTo_NewSMS1();
			enter_Num_ToField1(refNum);
			enterText_MessageField1(data.get("message"));
			clickOn_Send1();
			clickOn_BackBtn();clickOn_BackBtn();
			validate_SentMessage();
			delete_SMS1();
			hangUp_Call();
		} 
	}

	@Test(priority=23,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_023_Verify_Able_Perform_MultiRadioAccessBearer(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_023=======================");
		clearRecentApps();
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.settings);
		enable_MobileData();
		swipe_NotificationBar();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.wifi_OnState_QuickPanel);
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		clickOnHomeBtn();
		validate_Call_In_NotificationBar();
		clickOnHomeBtn();
		launchChrome();
		validate_And_BrowseIn_Chrome(data.get("url"), Locators_XP8_Sanity.googleCoIn_Text,Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.wifi_OffState_QuickPanel);
		customWait(3000);
		hangUp_Call();		
	}

	@Test(priority=24,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_024_Verify_UserAble_Search_URLbar_from_Browser(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_024=======================");
		clearRecentApps();
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.settings);
		enable_MobileData();
		swipe_NotificationBar();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.wifi_OnState_QuickPanel);
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(data.get("url2"), Locators_XP8_Sanity.ebayCom_Text,Locators_XP8_Sanity.eBay_Logo,Locators_XP8_Sanity.eBay_Logo);
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(data.get("url3"), Locators_XP8_Sanity.yahooCom_Text,Locators_XP8_Sanity.yahoo_Logo,Locators_XP8_Sanity.yahoo_Logo);
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(data.get("url4"), Locators_XP8_Sanity.facebookCom_Text,Locators_XP8_Sanity.facebook_Logo,Locators_XP8_Sanity.facebook_Logo);
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(data.get("url1"), Locators_XP8_Sanity.amazonCom_Text,Locators_XP8_Sanity.amazon_Logo,Locators_XP8_Sanity.amazon_CartLogo);
		//swipe_NotificationBar();
		//enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.wifi_OffState_QuickPanel);
		clickOnHomeBtn();
	}

	@Test(priority=25,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_025_Verify_OperatorSpecified_PhoneLanguages_in_DUT(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_025=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_LanguagesAndInput();
		clickOnLanguages();
		validate_LanguageDiplay(data.get("language1"), Locators_XP8_Sanity.english_Language);
		validate_LanguageDiplay(data.get("language2"), Locators_XP8_Sanity.french_Language);
		validate_LanguageDiplay(data.get("language3"), Locators_XP8_Sanity.spanish_Language);
	}

	@Test(priority=26,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_026_Verify_Operator_Homepage_in_NativeBrowser(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_026=======================");
		clearRecentApps();
		if (p_b_No.contains("-10.")) {
			launch_APP(Locators_XP8_Sanity.chrome);
			clearChromePermission();
			customWait(8000);
			SoftAssert sf = new SoftAssert();
			if (isElementExist(Locators_XP8_Sanity.attNet_Logo)) {
				APP_LOGS.info("Navigated to ATT.NET page");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "ATT Home page displayed successfully.");
			} else {
				APP_LOGS.info("ATT Home Page didn't displayed");
				sf.fail();
				test.log(LogStatus.FAIL, "Test Case Status is Fail.");
				test.log(LogStatus.INFO, "ATT Home page didn't displayed.");
			}
			clickBtn(Locators_XP8_Sanity.switcherButton_Chrome);
			minWait();
			clickBtn(Locators_XP8_Sanity.menuButton_Chrome);
			minWait();
			clickBtn(Locators_XP8_Sanity.closeAllTabs_Chrome);
			minWait();
		}else {
			test.log(LogStatus.SKIP, "This Test Case is Applicable ONLY for ATT Operator.");
		}
	}

	@Test(priority=27,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_027_Verify_APN_Correctness_Before_after_RestoringDefault_APNs(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_027=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_CellularNetworks();
		clickOnAPNs();
		validate_APN_Diaplay();
		restoreAPNs();
		validate_APN_Diaplay();
	}

	@Test(priority=28,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_028_Verify_Able_Switch_ON_OFF_Flashlight_Quickpanel(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_028=======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		swipe_NotificationBar();
		enable_Torch();
		validate_TorchEnabled_Notification();
		disable_Torch();
		clickOnHomeBtn();
		customWait(2000);
		validate_ADB_Logs(fN, "Received torch change true","Torch Notificaion shown","Received torch change false");
	}

	@Test(priority=29,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_029_Verify_Able_Launch_Chrome_Browse_URL(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_029=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(data.get("url1"), Locators_XP8_Sanity.ndtvCom_Text,Locators_XP8_Sanity.ndtv_logo_1,Locators_XP8_Sanity.ndtv_logo_2);
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(data.get("url2"), Locators_XP8_Sanity.bbcCom_Text,Locators_XP8_Sanity.bbc_logo_1,Locators_XP8_Sanity.bbc_logo_2);
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(data.get("url3"), Locators_XP8_Sanity.cnbcCom_Text,Locators_XP8_Sanity.cnbc_logo_1,Locators_XP8_Sanity.cnbc_logo_2);
	}

	@Test(priority=30,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_030_Verify_Able_to_Launch_And_Check_CalculatorApp(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_030=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.calculator);
		addition_Calculator(Locators_XP8_Sanity.two, Locators_XP8_Sanity.two);
		validate_Calci_Result("4");
		clear_Calculator();	
		subtraction_Calculator(Locators_XP8_Sanity.five, Locators_XP8_Sanity.two);
		validate_Calci_Result("3");
		clear_Calculator();	
		multiplication_Calculator( Locators_XP8_Sanity.two, Locators_XP8_Sanity.two);
		validate_Calci_Result("4");
		clear_Calculator();	
		division_Calculator( Locators_XP8_Sanity.nine, Locators_XP8_Sanity.three);
		validate_Calci_Result("3");
		clear_Calculator();
	}

	@Test(priority=31,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_031_Verify_Launch_Sound_Recorder_And_Check_Recording(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_031=======================");
		clearRecentApps();
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

	@Test(priority=32,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_032_Verify_User_Able_Lauch_Clock_Menus(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_032=======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_DateAndTime();
		enableAuto_DateTime_Timezone();
		launch_APP(Locators_XP8_Sanity.clock);
		validate_TimeAndDate_InClock();		
		validate_TimerDisplay();
		validate_StopWatch();
		addAndDelete_Alarm();
		customWait(2000);
		validate_ADB_Logs(fN, "Created new alarm instance: AlarmInstance");
	}

	@Test(priority=33,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_033_Verify_Internal_Storage_Space_Capacity(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_033=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Storage();
		validate_StorageSpace(data.get("expectedStorage"));
	}

	@Test(priority=34,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_034_Verify_SIM_PIN_Lock_Enabled_From_Settings(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_034=======================");
		clearRecentApps();		
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Security();
		setUpSIMcardLock(data.get("PIN"));
		validate_SIMcardLock(data.get("PIN"));
		unlock_SIMCardPin(data.get("PIN"));
	}

	@Test(priority=35,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_035_Verify_Sending_Receive_SMS_While_Camped_To_LTE_Network(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_035=======================");
		clearRecentApps();		
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_AboutStatus();
		clickOn_Status();
		clickOn_SIM_Status();
		boolean value = validate_CellularNetworkType();
		if (value) {
			if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
				launch_APP(Locators_XP8_Sanity.messaging);
				create_NewSMS(refNum, data.get("message"));
				clickOn_Send();
				validate_SentMessage();
				delete_SMS();
			} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
				launch_APP(Locators_XP8_Sanity.messages);
				create_NewSMS1(refNum, data.get("message"));
				clickOn_Send1();
				validate_SentMessage();
				delete_SMS1();
			}			
		}else {
			test.log(LogStatus.FAIL, "Cellular Network Type is NOT LTE");
		}
	}

	@Test(priority=36,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_036_Verify_USB_Power_Saving_Option_Present_SystemSettings(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_036=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_USBPowerSaving();
		validate_USBPowerSaving();
	}

	@Test(priority=37,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_037_Validate_HomeKey_BackKey_AppSwitchKey(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_037=======================");
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		validate_HomeScreen();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
		minWait();
		validate_RecentAppWindow();
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.BACK);
		minWait();
		validate_HomeScreen();
		minWait();
	}

	@Test(priority=38,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_038_Validate_SoftwareVersion(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_038=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);			
		navigateTo_AboutStatus();
		validate_BuildNumber();
	}

	@Test(priority=39,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_039_Capture_Picture(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_039=======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		clickOnCapture();
		customWait(10000);
		validate_ADB_Logs(fN, "android.hardware.action.NEW_PICTURE");
	}

	@Test(priority=40,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_040_Capture_Video(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_040=======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		clickVideoButton();
		customWait(8000);
		clickVideoButton();
		customWait(8000);
		validate_ADB_Logs(fN, "android.hardware.action.NEW_VIDEO");
	}

	@Test(priority=41,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_041_Add_APNs(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_041=======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_CellularNetworks();
		clickOnAPNs();
		validate_APN_Diaplay();
		clickOnAddAPN();
		add_Or_Edit_APN(data.get("apnName"), data.get("apn"));
		customWait(2000);
		validate_ADB_Logs(fN, "Apn="+data.get("apn"));
	}
	// Methods 41 and 42 are inter linked.
	@Test(priority=42,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_042_Edit_And_Delete_APN(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_042=======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_CellularNetworks();
		clickOnAPNs();
		click_APN(data.get("apnName"));
		add_Or_Edit_APN(data.get("newApnName"), data.get("newApn"));
		customWait(2000);
		validate_ADB_Logs(fN, "Apn="+data.get("newApn"));
		click_APN(data.get("newApnName"));
		delete_APN();	
	}

	@Test(priority=43,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_043_Validate_Default_ringtones(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_043=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Sound();
		if (p_b_No.contains("-10.")) {
			validate_DefaultSounds();
		} else if(p_b_No.contains("-26.")){
			validate_DefaultSounds1();
		}else if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-29.")||p_b_No.contains("-30.")) {
			validate_DefaultSounds2();
		}
	}

	@Test(priority=44,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_044_Validate_Preffered_NetworkMode(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_044=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_CellularNetworks();
		validate_Preffered_NetworkMode_NotSupported();
	}

	@Test(priority=45,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_045_Validate_enable_disable_DataRoamingService(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_045=======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_CellularNetworks();
		disable_DataRoaming();
		enable_DataRoaming();
		customWait(3000);
		disable_DataRoaming();
		customWait(5000);
		if (p_b_No.contains("-10.")) {
			validate_ADB_Logs(fN, "mButtonRoamingCategory enabling roaming data", "On Preference Change mButtonRoamingCategory end");
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-30.")) {
			validate_ADB_Logs(fN, "QCRIL send data roaming enable status DONE", "preferenceTreeClick: return false");

		}else if(p_b_No.contains("-29.")) {
			test.log(LogStatus.SKIP, "NOT Applicable for SPRINT Operator.");
		}
	}

	@Test(priority=46,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_046_Validate_Turn_On_Off_FM(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_046=======================");
		String fN=startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.fmRadio);
		clickOnFm_ON_OFF();
		customWait(2000);
		clickOnFm_ON_OFF();
		customWait(2000);
		clickOnFm_ON_OFF();
		customWait(8000);
		validate_ADB_Logs(fN, "FMRxStarting ---> NEW-STATE : FMRxOn", "FMTurningOff ---> NEW-STATE : FMOff");		
	}

	@Test(priority=47,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_047_Validate_Tune_FMStation(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_047=======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.fmRadio);
		scan_AllStations();
		clickOnFm_ON_OFF();
		customWait(10000);
		validate_ADB_Logs(fN, "searchStations: CURRENT-STATE : FMRxOn ---> NEW-STATE : SearchInProg");
	}

	@Test(priority=48,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_048_Validate_Software_Updates(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_048=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings); 
		navigateTo_AboutStatus();
		checkFor_SoftwareUpdate();
		validate_softwareUpdate();
	}
}
