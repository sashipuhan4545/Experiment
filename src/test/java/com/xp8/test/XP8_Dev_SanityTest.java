package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Dev_SanityTest extends XP8_Sanity_Util {

	public static ExtentTestInterruptedException testexception;
	public ExcelReader excel;
	Properties properties;
	public Timer timer;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException{

		extent = new ExtentReports("src/test/resources/extentreport/XP8_Dev_SanityTest.html",true);
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.8.1").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();	
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

	//============================================= Test Scripts =========================================

	@Test(priority=1,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_01_Check_Able_to_Change_NetworkType(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Sanity_01 =======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		precondition();
		if (!p_b_No.contains("-10.")) {
			launch_APP(Locators_XP8_Sanity.settings);
			navigateTo_CellularNetworks();
			clickBtn(Locators_XP8_Sanity.preferred_NetworkType);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().textContains(\"LTE\"))").click();
			validate_Locators1(Locators_XP8_Sanity.Preferrednetworkmode_LTE);
			if (!p_b_No.contains("-26.")) {
				clickBtn(Locators_XP8_Sanity.preferred_NetworkType);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().textContains(\"GSM only\"))").click();
				validate_Locators1(Locators_XP8_Sanity.Preferrednetworkmode_GSM);
			}
		}else if (p_b_No.contains("-10.")) {
			test.log(LogStatus.INFO, "NetworkType Change NOT applicable for AT&T.");
		}
	}

	@Test(priority=2,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_02_IMS_Registration_Status(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Sanity_02 =======================");
		try {
			launch_APP(Locators_XP8_Sanity.settings);
			navigateTo_CellularNetworks();
			clickBtn(Locators_XP8_Sanity.preferred_NetworkType);
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().textContains(\"LTE\"))").click();
		} catch (Exception e) {
		}
		String fN = startRIL_Log();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_More();
		enable_AirplaneMode();
		customWait(4000);
		disable_AirplaneMode();
		customWait(8000);
		validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
	}

	@Test(priority=3,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_03_Mobile_Data_EnableDisable_from_Quick_Setting(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_03=======================");
		
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnWifi();
		disable_Switch();clickOn_BackBtn();
		disable_MobileData();
		swipe_NotificationBar();
		clickOn_MobileData_QuickPanel();
		launch_APP(Locators_XP8_Sanity.chrome);		
		clearChromePermission();
		setDataSaver_On();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text,Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		swipe_NotificationBar();
		clickOn_MobileData_QuickPanel();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_MobileData_Disable();
		customWait(2000);
		clickOnHomeBtn();
	}

	@Test(priority=4,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_04_Verify_OperatorName_LockScreen_And_HomeScreen(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("======================XP8_Sanity_04=======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_CellularNetworks();
		clickOnAPNs();
		validate_APN_Diaplay();
		validate_OperatorName_Lockscreen();
		unlock_Phone();
	}

	@Test(priority=5,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_05_Verify_Enable_And_Disable_FlightMode(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_05 =======================");
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

	@Test(priority=6,dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_06_Launch_all_Apps_Present_in_MenuTray(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_06 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_More();
		disable_AirplaneMode();
		clickOn_BackBtn();
		clickOnWifi();
		setUp_And_Enable_WiFi();
		
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		launch_APP(Locators_XP8_Sanity.calculator);
		validate_Locators(Locators_XP8_Sanity.equal); 
		launch_APP(Locators_XP8_Sanity.Calendar);
		validate_Locators(Locators_XP8_Sanity.google_Calendar);
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		validate_Locators(Locators_XP8_Sanity.capturePicture);
		launch_APP(Locators_XP8_Sanity.chrome);customWait(4000); 
		validate_Locators(Locators_XP8_Sanity.urlBar_Chrome);
		launch_APP(Locators_XP8_Sanity.clock);
		validate_Locators(Locators_XP8_Sanity.CLOCK);
		launch_APP(Locators_XP8_Sanity.contacts);
		validate_Locators(Locators_XP8_Sanity.add_NewContact); 
		launch_APP(Locators_XP8_Sanity.docs);
		validate_Locators(Locators_XP8_Sanity.WriteOntheGo_Docs);
		launch_APP(Locators_XP8_Sanity.downloads);
		validate_Locators(Locators_XP8_Sanity.downloads);
		launch_APP(Locators_XP8_Sanity.drive);
		validate_Locators(Locators_XP8_Sanity.a_SafePlace_for_all_your_files); 
		launch_APP(Locators_XP8_Sanity.duo);
		validate_Locators(Locators_XP8_Sanity.High_quality_video_calling);
		//launch_APP(Locators_XP8_Sanity.fileManager);
		//clearAllow();
		//validate_Locators1(Locators_XP8_Sanity.material_toolbar_FileManager);
		launch_APP(Locators_XP8_Sanity.fmRadio);
		validate_Locators(Locators_XP8_Sanity.fm_OnOff_Button);
		clickBtn(Locators_XP8_Sanity.fm_OnOff_Button);
		launch_APP(Locators_XP8_Sanity.gmail);
		validate_Locators(Locators_XP8_Sanity.Welcome_to_Gmail);
		launch_APP(Locators_XP8_Sanity.google);
		clickBtn(Locators_XP8_Sanity.NO_THANKS1);
		validate_Locators(Locators_XP8_Sanity.Search_Google);
		launch_APP(Locators_XP8_Sanity.keep); 
		try { wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]"))); } catch (Exception e) {}
		validate_Locators(Locators_XP8_Sanity.headingText_Google);
		launch_APP(Locators_XP8_Sanity.maps);
		validate_Locators(Locators_XP8_Sanity.Make_it_yourMap); 
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Locators(Locators_XP8_Sanity.dailerPad);
		launch_APP(Locators_XP8_Sanity.photos);
		validate_Locators(Locators_XP8_Sanity.googlePhotos_uses_FaceGrouping);
		launch_APP(Locators_XP8_Sanity.PlayMovies_TV);
		try {wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='GET STARTED']")));	} catch (Exception e) {	}
		validate_Locators(Locators_XP8_Sanity.welcome_title_PlayMoviesTV);
		launch_APP(Locators_XP8_Sanity.PlayMusic);
		customWait(4000);
		validate_Locators(Locators_XP8_Sanity.ToUse_GooglePlayMusic);
		//launch_APP(Locators_XP8_Sanity.PlayStore);
		//wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
		//validate_Locators(Locators_XP8_Sanity.headingText_Google);
		launch_APP(Locators_XP8_Sanity.settings);
		validate_Locators(Locators_XP8_Sanity.settings_search);
		launch_APP(Locators_XP8_Sanity.sheets);
		validate_Locators(Locators_XP8_Sanity.welcomeText_Spreadsheets);
		launch_APP(Locators_XP8_Sanity.slides);
		validate_Locators(Locators_XP8_Sanity.welcomeText_Slides);
		launch_APP(Locators_XP8_Sanity.sonimScout);
		validate_Locators(Locators_XP8_Sanity.scout_Heading);
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		validate_Locators(Locators_XP8_Sanity.recordButton);
		launch_APP(Locators_XP8_Sanity.voiceSearch);
		validate_Locators(Locators_XP8_Sanity.listening);
		launch_APP(Locators_XP8_Sanity.youTube);
		clickBtn(Locators_XP8_Sanity.NOT_NOW); 
		validate_Locators(Locators_XP8_Sanity.youtube_Logo);
		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			clearSMSPermissions();
			validate_Locators(Locators_XP8_Sanity.add_NewSMS);
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){
			launch_APP(Locators_XP8_Sanity.messages);
			validate_Locators(Locators_XP8_Sanity.add_NewSMS1);
		}
		sf1.assertAll();
	}

	@Test(priority=7,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_07_Check_User_can_Add_Google_Account(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_07=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_More();
		disable_AirplaneMode();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_AddGoogleAccount();
		add_GoogleAccount(dt.get("emailId"), dt.get("password"));
		validate_GoogleAcccount(dt.get("emailId"));
	}

	@Test(priority=8,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_08_Check_able_to_Download_APK_from_Playstore(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_08=======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.PlayStore);
		customWait(3000);
		install_App(dt.get("appName"));
		validate_Installed_App(dt.get("appName"));
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.PlayStore);
		unInstall_App(dt.get("appName"));
	}

	@Test(priority=9,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_09_Check_able_to_make_Voice_calls(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_09 =======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(3000);
		end_Call();
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}

	@Test(priority=10,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_10_Check_able_to_receive_VoiceCalls(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_10 =======================");
		endCall_RefDevice();
		make_Call_from_RefDev();
		customWait(10000);
		recieve_Call_PrimaryDev();
		customWait(5000);
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}

	@Test(priority=11,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_11_Check_able_to_Reject_VoiceCalls(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_11 =======================");
		endCall_RefDevice();
		make_Call_from_RefDev();
		customWait(10000);
		aDriver.pressKeyCode(6);
		customWait(5000);
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}

	@Test(priority=12,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_12_Check_Able_to_do_InCall_functionality(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_12 =======================");
		endCall_RefDevice();
		String fN=startAdbLog();
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(6000);
		clickBtn(Locators_XP8_Sanity.audioButton);
		minWait();
		clickBtn(Locators_XP8_Sanity.yes);
		minWait();
		clickBtn(Locators_XP8_Sanity.audioButton);
		minWait();
		clickBtn(Locators_XP8_Sanity.muteButton);minWait();		
		clickBtn(Locators_XP8_Sanity.muteButton);minWait();
		end_Call();
		customWait(6000);
		validate_ADB_Logs(fN,"AUDIO_ROUTE, Entering state ActiveSpeakerRoute: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER","AUDIO_ROUTE, Leaving state ActiveSpeakerRoute: ICA.sAR->CARSM.pM_USER_SWITCH_BASELINE_ROUTE->CARSM.pM_USER_SWITCH_HEADSET");
	}

	@Test(priority=13,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_13_MT_Call_Reject_with_SMS_Notification(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_13 =======================");
		endCall_RefDevice();
		if(p_b_No.contains("-10.")||p_b_No.contains("-30.")){
			launch_APP(Locators_XP8_Sanity.messaging);
			clearSMSPermissions();
			make_Call_from_RefDev();
			customWait(6000);
			reject_Call_With_SMS(dt.get("message"));
			validate_SentMessage();
			delete_SMS();
		}else if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
			launch_APP(Locators_XP8_Sanity.messages);
			make_Call_from_RefDev();
			customWait(6000);
			reject_Call_With_SMS(dt.get("message"));
			validate_SentMessage();
			delete_SMS1();
		}
	}

	@Test(priority=14,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_14_Send_SMS_during_ongoing_Voice_call(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_14 =======================");
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(3000);
		if(p_b_No.contains("-10.")||p_b_No.contains("-30.")){
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum,dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		}else if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
			launch_APP(Locators_XP8_Sanity.messages);
			create_NewSMS1(refNum,dt.get("message"));
			clickOn_Send1();
			validate_SentMessage();
			delete_SMS1();
		}	
		endCall_RefDevice();		
	}

	@Test(priority=15,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_15_Check_the_Call_history_for_details(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Sanity_15 =======================");
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		clear_Call_History();
		dailCallUsingDailPad(refNum);
		customWait(6000);
		end_Call();
		validate_Call_Details_and_Delete(refNum);
	}

	@Test(priority=16,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_16_Verify_Able_to_Send_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_16 =======================");
		endCall_RefDevice();
		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
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

	@Test(priority=17,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_17_Check_Able_Receive_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_17 =======================");
		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {

			launch_APP(Locators_XP8_Sanity.messaging);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));
			validate_RecievedMessage();
			delete_SMS();
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){

			launch_APP(Locators_XP8_Sanity.messages);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));
			validate_RecievedMessage();
			delete_SMS1();
		}		
	}

	@Test(priority=18,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_18_Check_able_to_send_multi_page_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("======================XP8_Sanity_18=======================");

		if(p_b_No.contains("-10.")||p_b_No.contains("-30.")){

			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum,dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {

			launch_APP(Locators_XP8_Sanity.messages);
			create_NewSMS1(refNum,dt.get("message"));
			clickOn_Send1();
			validate_SentMessage();
			delete_SMS1();
		}
	}

	@Test(priority=19,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_19_Check_able_to_Recieve_multi_page_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("======================XP8_Sanity_19 =======================");

		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {

			launch_APP(Locators_XP8_Sanity.messaging);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));			
			validate_RecievedMessage();
			delete_SMS();
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){

			launch_APP(Locators_XP8_Sanity.messages);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));
			validate_RecievedMessage();
			delete_SMS1();
		}
	}

	@Test(priority=20,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_20_Check_able_to_save_SMS_to_Draft_and_Delete(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_20======================="); 

		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {

			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum, dt.get("message"));
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_Locators1(Locators_XP8_Sanity.draft);
			clickBtn(Locators_XP8_Sanity.draft);
			clickOn_Send();
			delete_SMS();
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){

			test.log(LogStatus.INFO, "NOT applicable for canadian Operators.");
			//launch_APP(Locators_XP8_Sanity.messages);
			//create_NewSMS1(refNum, dt.get("message"));
			//clickOn_BackBtn();
			//clickOn_BackBtn();
			//validate_Locators1(Locators_XP8_Sanity.draft);
		}
	}

	@Test(priority=21,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_21_Check_able_to_send_MMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_21 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		enable_MobileData();
		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			navigateTo_NewSMS();
			enter_Num_ToField(refNum);
			add_Picture();
			enterText_MessageField(dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){
			launch_APP(Locators_XP8_Sanity.messages);
			create_NewSMS1(refNum, dt.get("message"));
			add_Picture();
			clickOn_Send1();
			validate_SentMessage();
			delete_SMS1();
		}
	}

	@Test(priority=22,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_22_Check_able_to_save_MMS_to_draft_and_Delete(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_22 =======================");
		if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			navigateTo_NewSMS();
			enter_Num_ToField(refNum);
			add_Picture();
			enterText_MessageField(dt.get("message"));
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_Locators1(Locators_XP8_Sanity.draft);
			clickBtn(Locators_XP8_Sanity.draft);
			clickOn_Send();
			delete_SMS();
		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){
			test.log(LogStatus.INFO, "NOT applicable for canadian Operators.");
			//launch_APP(Locators_XP8_Sanity.messages);
			//create_NewSMS1(refNum, dt.get("message"));
			//add_Picture();
			//clickOn_Send1();
			//validate_SentMessage();
			//delete_SMS1();
		}
	}

	@Test(priority=23,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_23_Check_able_to_Send_and_Receive_Email(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_23 =======================");
		launch_APP(Locators_XP8_Sanity.g_mail);
		clear_GmailPermission();
		deleteAllmails();
		click_NewMail();
		compose_NewMail(dt.get("TO"), dt.get("subject"), dt.get("mailContent"));
		send_Mail();
		customWait(5000);
		validate_Mail(dt.get("subject"));
		deleteAllmails();
	}

	@Test(priority=24,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_24_Ensure_User_can_BrowseInternet(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_24 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnWifi();
		disable_Switch();
		clickOn_BackBtn();
		enable_MobileData();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
	}	

	@Test(priority=25,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_25_Check_able_to_connect_to_available_SSID_Browse(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_25 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		disable_MobileData();
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnWifi();
		setUp_And_Enable_WiFi();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
	}

	@Test(priority=26, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_26_Enable_Disable_Wifi_form_DeviceSettings(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_26 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnWifi();
		setUp_And_Enable_WiFi();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnWifi();
		disable_Switch();
		launch_APP(Locators_XP8_Sanity.chrome);
		validate_MobileData_Disable();
	}

	@Test(priority=27, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_27_Enable_Disable_Wifi_form_Notification(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_27 ======================");
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

	@Test(priority=28,dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_28_Check_able_to_StreamVideo_via_YouTube(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_28 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		enable_MobileData();
		launch_APP(Locators_XP8_Sanity.youTube);
		customWait(4000);
		clickBtn(Locators_XP8_Sanity.firstVideo_YT);		
		customWait(5000);
		validate_Locators1(Locators_XP8_Sanity.playerView_YT);		
	}

	@Test(priority=29, dataProvider="XP8DevSanity", dataProviderClass= DataProviders.class)
	public void XP8_Sanity_29_Enable_Disable_And_Scan_BT_Devices(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_29 ======================");
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083");// To Clear Battery full Popup.
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
		enable_BT_RefDevice(); // With the same Method we can Disable the BT.
	}

	@Test(priority=30,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_30_Device_is_Charging_if_USB_connected(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_30 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Battery();
		validate_BatteryCharging_Or_Full();
	}

	@Test(priority=31,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_31_Check_if_SDCard_get_Detected(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_31 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Storage();
		validate_Locators2(Locators_XP8_Sanity.portable_Storage,Locators_XP8_Sanity.sizeOfSDcard);
	}


	@Test(priority=32,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_32_IMEI_Displsy_By_Dialing_USSD_code(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_32 =======================");
		launch_APP(Locators_XP8_Sanity.phone);
		dailNumber(dt.get("ussd"));
		validate_Locators1(Locators_XP8_Sanity.imei_Popup);
		clickBtn(Locators_XP8_Sanity.OK);
	}

	@Test(priority=33,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_33_Add_Contact_in_Phonebook_with_all_Field(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_33 =======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		setDefaultAccount_Contact();
		deleteContact_IfPresent(dt.get("contactName"));
		add_NewContact_withAllFields(dt.get("contactName"), dt.get("phoneticName"), dt.get("nickName"),dt.get("company"), dt.get("title"),refNum,dt.get("email"),dt.get("address"), dt.get("IM"),dt.get("webSite"),dt.get("notes"));
		validate_Added_Contact(dt.get("contactName"));
	}

	@Test(priority=34,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_34_Add_contact_into_SIM_onlyWith_Name_PhoneNumber(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_34=======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		deleteContact_IfPresent(data.get("contactName"));
		//For Below Method user should enter 1 to save for phone memory and 2 to save for SIM memory in First Argument.
		add_NewContact(2,data.get("contactName"),refNum,data.get("contactEmail"),data.get("address"));
		validate_Added_Contact(data.get("contactName"));
	}

	@Test(priority=35,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_35_Check_Can_create_Group_in_Contacts(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_35 =======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		clickOn_AddGroup();
		create_GroupDetails(dt.get("groupName"), dt.get("contactName"));
		validate_GroupName(dt.get("groupName"));
		delete_Group();
	}

	@Test(priority=36,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_36_Check_user_can_delete_contact(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_36=======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		delete_Contact(dt.get("contactName1"));
		delete_Contact(dt.get("contactName2"));
	}

	@Test(priority=37,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_37_Check_for_Back_Camera_Working(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_37 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		clickOnCapture();
		customWait(10000);
		validate_ADB_Logs(fN, "android.hardware.action.NEW_PICTURE");
	}

	@Test(priority=38,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_38_Check_for_Front_Camera_Working(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Sanity_38 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.camera);
		changeTo_FrontCam();
		clickOnCapture();
		customWait(10000);
		validate_ADB_Logs(fN, "android.hardware.action.NEW_PICTURE");	
	}

	@Test(priority=39,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_39_Check_can_record_video_using_Front_Camera(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_Sanity_39 =======================");
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

	@Test(priority=40,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_40_Check_can_record_video_using_Back_Camera(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_Sanity_40 =======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.camera);
		changeTo_FrontCam();
		clickVideoButton();
		customWait(8000);
		clickVideoButton();
		customWait(8000);
		validate_ADB_Logs(fN, "android.hardware.action.NEW_VIDEO");
	}	

	@Test(priority=41,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_41_Check_able_to_change_the_SleepTime(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_41 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Dispaly();
		clickOn_Sleep();
		clickBtn(Locators_XP8_Sanity.tenMin_Sleep);minWait();
		validate_Locators1(Locators_XP8_Sanity.tenMinutes_Inactivity);
		clickOn_Sleep();
		clickBtn(Locators_XP8_Sanity.thirtyMin_Sleep);minWait();
		validate_Locators1(Locators_XP8_Sanity.thirtyMinutes_Inactivity);
	}

	@Test(priority=42,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_42_Check_able_to_change_the_Ringtone(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_42 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Sound();
		clickOn_PhoneRingtone();
		select_Ringtone(dt.get("rintoneName"));
		validate_RingtoneChange(Locators_XP8_Sanity.bigEasy_ringtone);
	}

	@Test(priority=43,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_43_Check_able_to_Enable_Disable_Location(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_43 =======================");
		String fN=startAdbLog();
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Location();
		clickBtn(Locators_XP8_Sanity.switch_On_State);
		minWait();
		clickBtn(Locators_XP8_Sanity.close);
		minWait();
		clickBtn(Locators_XP8_Sanity.switch_Off_State);customWait(5000);
		clickBtn(Locators_XP8_Sanity.switch_On_State);customWait(5000);
		validate_ADB_Logs(fN, "Network location enabled.", "Network location disabled.");	
	}

	@Test(priority=44,dataProvider="XP8DevSanity", dataProviderClass=DataProviders.class)
	public void XP8_Sanity_44_Check_able_to_launch_Calendar_Add_an_event(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Sanity_44 =======================");
		launch_APP(Locators_XP8_Sanity.Calendar);
		clear_Calender_Permission();
		add_CalenderEvent(dt.get("eventName"));
		validate_CalenderEvent(dt.get("eventName"));
		customWait(8000);
		delete_calenderEvent(dt.get("eventName"));
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnAccounts();
		remove_GoogleAcccount();
	}	

	@Test(priority=45,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_45_Check_Sound_Recording_is_Working(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_45 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOnAccounts();
		remove_GoogleAcccount();
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		clearAllow();
		clickOn_Record();
		clearAllow();
		customWait(20000);		
		clickOn_StopRecord();
		clickOn_Save(data.get("fileName"));
		validate_SoundRecList(data.get("fileName"));
	}

	@Test(priority=46,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_46_Check_able_to_play_Music_file(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_46 =======================");
		launch_APP(Locators_XP8_Sanity.PlayMusic);
		clickBtn(Locators_XP8_Sanity.skip_Btn);minWait();
		clickBtn(Locators_XP8_Sanity.audioRecords_Music);minWait();
		validate_Locators1(Locators_XP8_Sanity.autoFile_RecordedDFile);
		clickBtn(Locators_XP8_Sanity.autoFile_RecordedDFile);
		customWait(5000);
		validate_Locators1(Locators_XP8_Sanity.play_pause_header);
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		clickOn_RecordList();
		delete_SoundRecList();
	}

	@Test(priority=47,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_47_Check_able_to_launch_Clock_Add_Alarm(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_47=======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.clock);
		addAndDelete_Alarm();
		customWait(2000);
		validate_ADB_Logs(fN, "Created new alarm instance: AlarmInstance");
	}

	@Test(priority=48,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_48_Check_able_to_launch_SCOUT_application(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_Sanity_48=======================");
		launch_APP(Locators_XP8_Sanity.sonimScout);
		minWait();
		validate_Locators1(Locators_XP8_Sanity.scout_Heading);		
	}

	@Test(priority=49,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_49_Validate_Turn_On_Off_FM(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_49=======================");
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

	@Test(priority=50,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_50_Validate_Tune_FMStation(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Sanity_50=======================");
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

	@Test(priority=51,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	public void XP8_Sanity_51_Launch_Application_With_PTT_Key(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_Sanity_51 =======================");
		if (!p_b_No.contains("-26.")) {
			launch_APP(Locators_XP8_Sanity.settings);
			navigateprogrammablekey();
			validate_ProgrammableKeySummaryText();
			press_PTT_Key();
			validate_Locators1(Locators_XP8_Sanity.dailerPad);
		}else{
			test.log(LogStatus.INFO, "NOT applicable for SL.");
		}
	}
	
	


	//	@Test(priority=6,dataProvider="XP8DevSanity",dataProviderClass=DataProviders.class)
	//	public void XP8_Sanity_06_Check_able_to_EnableDdisable_DataRoaming(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {
	//
	//		APP_LOGS.info("======================XP8_Sanity_06=======================");
	//		String fN = startAdbLog();
	//		launch_APP(Locators_XP8_Sanity.settings);
	//		navigateTo_CellularNetworks();
	//		disable_DataRoaming();
	//		enable_DataRoaming();
	//		customWait(3000);
	//		disable_DataRoaming();
	//		customWait(5000);
	//		if (p_b_No.contains("-10.")) {
	//			validate_ADB_Logs(fN, "mButtonRoamingCategory enabling roaming data", "On Preference Change mButtonRoamingCategory end");
	//		} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-30.")) {
	//			validate_ADB_Logs(fN, "QCRIL send data roaming enable status DONE", "preferenceTreeClick: return false");
	//		}else if(p_b_No.contains("-29.")) {
	//			test.log(LogStatus.SKIP, "NOT Applicable for SPRINT Operator.");
	//		}
	//		p.destroyForcibly();
	//	}


}
