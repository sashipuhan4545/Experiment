package com.xp8.test;

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
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.GetMethods;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_Sanity_Util;
import com.xp8.util.appiumService;

import OCR.Read_File;
import OCR.my_main;
import android.provider.ContactsContract.Contacts.Data;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Bell_sanity extends XP8_Sanity_Util {

	public static ExtentTestInterruptedException testexception;
	public ExcelReader excel;
	Properties properties;
	public Timer timer1;
	
	public static String ssid = "IDCSONWAP";
	public static String pswd = "1dcS0n1md0tc0MbLr";
//test
	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException {

		extent = new ExtentReports("src/test/resources/extentreport/XP8_Device_QA_Sanity_Test_O.html", true); 
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.1").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();
	}

	@BeforeTest
	public void setUpSys() throws FileNotFoundException, MalformedURLException, InterruptedException, AWTException {
		properties = loadDriverAndProperties();
		Locators_XP8_Sanity loc1 = new Locators_XP8_Sanity(aDriver);
		Locators_BaseUtil loc2 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc2);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}

	@BeforeTest
	public void timer() {
		timer1 = new Timer();
		timer1.schedule(new TimerTask() {

			@Override
			public void run() {
				if(isElementExist(Locators_XP8_Sanity.batteryFullorAppKey)) {
					Locators_XP8_Sanity.OK.click();
				}
			}
		}, 0, 10*(100*1));
	}

	@BeforeClass
	public void deleteADBLogs() {

		File dir = new File("");		
		if (dir.isDirectory() == false) {
			return;
		}
		File[] files = dir.listFiles();
		for (File file : files) {
			try {
				file.delete();
			} catch (Exception e) {
			}
		}
	}


	@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {


		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_DeviceSanity", this.getClass());


	}


	@BeforeMethod
	public void beforeMethod(Method method) throws InterruptedException {
		test = extent.startTest( (/*this.getClass().getSimpleName() +" :: "+  */method.getName()),method.getName()); //Test Case Start Here
		//test.assignAuthor("Chandan. A"); //Test Script Author Name.
		
		clearRecentApps();
	}

	@AfterMethod
	public void afterMethod(Method method,ITestResult result) throws IOException, InterruptedException {
		if(result.getStatus()== ITestResult.FAILURE) {
			String screenshotPath = captureScreenshot(method.getName());
			test.addScreenCapture(screenshotPath);
			test.log(LogStatus.FAIL,result.getThrowable());
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}

	// ========================================= Test Method ============================================

	
	@Test(priority=1,dataProvider="XP8SanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_001_Launch_All_Applications_from_AppMenu(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_001 ======================");
		acceptPlayprotect();
		unlock_Phone();
		clearRecentApps_O();
		launch_APP(Locators_XP8_Sanity.settings);
		precondition_O();
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToTextContains("Wi");
		ON_Switch("Off");
		setUp_And_Enable_WiFi();

		WebDriverWait wt = new WebDriverWait(aDriver, 30);

		launch_APP(Locators_XP8_Sanity.calculator);
		validate_Locators(Locators_XP8_Sanity.equal);
		launch_APP(Locators_XP8_Sanity.Calendar);
		customWait(4000);
		if(isElementExist(Locators_XP8_Sanity.Google_SignIn_page)) {
			validate_Locators(Locators_XP8_Sanity.Google_SignIn_page);
		}else {
			validate_Locators(Locators_XP8_Sanity.google_Calendar);
		}
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		validate_Locators(Locators_XP8_Sanity.capturePicture);
		launch_APP(Locators_XP8_Sanity.chrome);
		customWait(4000); 
		if(isElementExist(Locators_XP8_Sanity.Google_Search_chrome)) {
			validate_Locators(Locators_XP8_Sanity.Google_Search_chrome);
		}else {
			validate_Locators(Locators_XP8_Sanity.ACCEPTCONTINUE);
		}
		launch_APP(Locators_XP8_Sanity.clock);

		validate_Locators(Locators_XP8_Sanity.clock);
		launch_APP(Locators_XP8_Sanity.contacts);
		validate_Locators(Locators_XP8_Sanity.add_NewContact);
		launch_APP(Locators_XP8_Sanity.downloads);
		validate_Locators(Locators_XP8_Sanity.downloads);
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
		if(isElementExist(Locators_XP8_Sanity.NO_THANKS1)) {
			clickBtn(Locators_XP8_Sanity.NO_THANKS1);
		}
		validate_Locators(Locators_XP8_Sanity.Google_search_field);		
		launch_APP(Locators_XP8_Sanity.keep); customWait(3000);
		try { wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]"))); } catch (Exception e) {}
		customWait(2000);
		validate_Locators(Locators_XP8_Sanity.headingText_Google);
		launch_APP(Locators_XP8_Sanity.maps);
		validate_Locators(Locators_XP8_Sanity.Make_it_yourMap);
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Locators(Locators_XP8_Sanity.dailerPad);
		launch_APP(Locators_XP8_Sanity.photos);
		validate_Locators(Locators_XP8_Sanity.googlePhotos_uses_FaceGrouping);
		launch_APP(Locators_XP8_Sanity.PlayMovies_TV);
		customWait(4000);
		if(isElementExist(Locators_XP8_Sanity.Google_SignIn_page)) {
			validate_Locators(Locators_XP8_Sanity.Google_SignIn_page);
		}else if (isElementExist(Locators_XP8_Sanity.Google_playmovies_Tv)) {
			validate_Locators(Locators_XP8_Sanity.Google_playmovies_Tv);
		}else {
			try {wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='GET STARTED']")));	} catch (Exception e) {	}
			validate_Locators(Locators_XP8_Sanity.welcome_title_PlayMoviesTV);
		}
		launch_APP(Locators_XP8_Sanity.PlayMusic);
		customWait(4000);
		if(isElementExist(Locators_XP8_Sanity.play_music_page)) {
			validate_Locators(Locators_XP8_Sanity.play_music_page);
		}else if (check) {
			validate_Locators(Locators_XP8_Sanity.play_now_playMusic);
		}else {
			validate_Locators(Locators_XP8_Sanity.ToUse_GooglePlayMusic);
		}
		//launch_APP(Locators_XP8_Sanity.PlayStore);
		//wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
		//validate_Locators(Locators_XP8_Sanity.Use_your_Google_account);
		launch_APP(Locators_XP8_Sanity.Security);
		validate_Locators(Locators_XP8_Sanity.Please_choose_follow);
		launch_APP(Locators_XP8_Sanity.settings);
		validate_Locators(Locators_XP8_Sanity.search_Settings);
		launch_APP(Locators_XP8_Sanity.sonimScout);
		validate_Locators(Locators_XP8_Sanity.scout_Heading);
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		validate_Locators(Locators_XP8_Sanity.recordButton);
		launch_APP(Locators_XP8_Sanity.Voice_Activation);
		clearAllow();
		validate_Locators(Locators_XP8_Sanity.when_you_turn_On_Voice_Activation);
		launch_APP(Locators_XP8_Sanity.WiPowerAgent);
		validate_Locators(Locators_XP8_Sanity.Hello_from_WiPower);
		launch_APP(Locators_XP8_Sanity.youTube);
		clickBtn(Locators_XP8_Sanity.NOT_NOW);
		validate_Locators(Locators_XP8_Sanity.youtube_Logo);
	    //	if (p_b_No.contains("-10.")) {
		//	launch_APP(Locators_XP8_Sanity.drive);
		//	validate_Locators(Locators_XP8_Sanity.with_your_GoogleAccount);
		//	launch_APP(Locators_XP8_Sanity.messaging);
		//	clearSMSPermissions();
		//	validate_Locators(Locators_XP8_Sanity.add_NewSMS);
		//	launch_APP(Locators_XP8_Sanity.Device_Help);
		//	validate_Locators(Locators_XP8_Sanity.add_NewSMS);
		//	launch_APP(Locators_XP8_Sanity.Visual_Voicemail);
		//	validate_Locators(Locators_XP8_Sanity.Play_messages_in_any_order);
	//	} else if(p_b_No.contains("-00.")) {
	//		launch_APP(Locators_XP8_Sanity.drive);
		//	validate_Locators(Locators_XP8_Sanity.Use_your_Google_account);
		//	launch_APP(Locators_XP8_Sanity.messaging);
		//	clearSMSPermissions();
		//	validate_Locators(Locators_XP8_Sanity.add_NewSMS);
//			launch_APP(Locators_XP8_Sanity.QTI_Logkit);
//			customWait(5000);   clickBtn(Locators_XP8_Sanity.CONTINUE);
//			validate_Locators(Locators_XP8_Sanity.Recent_captures_and_events);
	//	} else if(p_b_No.contains("-15.")) {
		//	launch_APP(Locators_XP8_Sanity.drive);
		//	validate_Locators(Locators_XP8_Sanity.a_SafePlace_for_all_your_files);
		//	launch_APP(Locators_XP8_Sanity.MessagePlus);
		//	customWait(3000);
		//	validate_Locators(Locators_XP8_Sanity.StartMessaging);
		//	launch_APP(Locators_XP8_Sanity.Hum);
		//	validate_Locators(Locators_XP8_Sanity.welcome_To_Hum);
		//	launch_APP(Locators_XP8_Sanity.Music);
		//	clearAllow();
		//	validate_Locators(Locators_XP8_Sanity.Artists);
		//	launch_APP(Locators_XP8_Sanity.MyVerizon);
		//	clearAllow();  clickBtn(Locators_XP8_Sanity.ADVANCE);
		//	clearAllow(); 
		//	try { wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@text,'Get Started')]"))); } catch (Exception e) {}
		//	validate_Locators(Locators_XP8_Sanity.Get_Started);
		//	launch_APP(Locators_XP8_Sanity.OmaDMTest);
		//	validate_Locators(Locators_XP8_Sanity.SDMFOTA);
		//	launch_APP(Locators_XP8_Sanity.SecurityAndPrivacy);
		//	validate_Locators(Locators_XP8_Sanity.Continue);
			//launch_APP(Locators_XP8_Sanity.Snapdragon_Gallery);
			//clearAllow();
			//validate_Locators(Locators_XP8_Sanity.Timeline);
	//	}

		launch_APP(Locators_XP8_Sanity.drive);
		customWait(2000);
		if(isElementExist(Locators_XP8_Sanity.Google_SignIn_page)) {
			validate_Locators(Locators_XP8_Sanity.Google_SignIn_page);
		}else {
			validate_Locators(Locators_XP8_Sanity.Google_drive_add);
		}
		sf1.assertAll();
	}

	
	@Test(priority=2,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_002_AboutPhone_Build_Baseband_Versions(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_002 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("System");
		scrollToText("About phone");
		scrollTo("Build number");
		validate_BuildNumber();
		validate_BasebandVersion();
	}

	
	@Test(priority=3,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_003_Check_NetworkType(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_003 =======================");
		if (!p_b_No.contains("-10")) {
			launch_APP(Locators_XP8_Sanity.settings);
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			minWait();
			clickBtn(Locators_XP8_Sanity.down_Triangle);
			minWait();
			scrollToText("Preferred network type");
			minWait();
			scrollToTextContains("LTE");
			minWait();
			validate_Locators1(Locators_XP8_Sanity.PreferrednetworkType_LTE);
			scrollToText("Preferred network type");
			minWait();
			scrollToTextContains("3G");
			minWait();
			validate_Locators1(Locators_XP8_Sanity.PreferrednetworkType_3G);
			scrollToText("Preferred network type");
			minWait();
			scrollToTextContains("2G");
			minWait();
			validate_Locators1(Locators_XP8_Sanity.PreferrednetworkType_2G);
		}	else {
			test.log(LogStatus.FAIL, "NOT Applicable for AT&T");
		}
	}

	
	@Test(priority=4,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_004_Verify_OperatorName_LockScreen_And_HomeScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_004 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Security & location");
		scrollToText("Screen lock");minWait();
		clickBtn(Locators_XP8_Sanity.swipeLock);
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		minWait();
		clickBtn(Locators_XP8_Sanity.down_Triangle);
		minWait();
		scrollToText("Preferred network type");
		minWait();
		scrollToTextContains("LTE");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		minWait();
		clickBtn(Locators_XP8_Sanity.down_Triangle);
		customWait(2000);
		aDriver.swipe(540, 1700, 540, 1300, 1000);
		scrollToText("Access Point Names");
		validate_APN_Diaplay();
		validate_OperatorName_Lockscreen();	
		unlock_Phone();
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Security & location");
		scrollToText("Screen lock");minWait();
		clickBtn(Locators_XP8_Sanity.noneLock);
	}

	
	@Test(priority=5,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_005_Make_MO_Voice_Call(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_005 =======================");
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(6000);
		end_Call();
		customWait(2000);
		validate_Num_In_CallLog(refNum);
		clear_Call_History_O();
	}
	
	
	@Test(priority=6,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_006_Send_SMS_During_Ongoing_Call(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_006 =======================");
		
		if(p_b_No.contains("-10.")||p_b_No.contains("-11.")){
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(3000);
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum,dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		}else if (p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			clearSMSPermissions_O();
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
		endCall_RefDevice();
	}

	
	@Test(priority=7,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_007_Check_Able_to_do_InCall_functionality(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_007 =======================");
		endCall_RefDevice();
		//	String XP8_Device_QA_Sanity_007=startAdbLog();
		startAdbLog("XP8_Device_QA_Sanity_007");

		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		customWait(6000);
		clickBtn(Locators_XP8_Sanity.speaker_call);	minWait();
		clickBtn(Locators_XP8_Sanity.speaker_call);	minWait();
		clickBtn(Locators_XP8_Sanity.mute_call);	minWait();
		clickBtn(Locators_XP8_Sanity.mute_call);	minWait();
		end_Call();
		customWait(9000);
		validate_ADB_Logs("XP8_Device_QA_Sanity_007","AUDIO_ROUTE, Entering state ActiveSpeakerRoute: ICA.sAR->CARSM.pM_USER_SWITCH_SPEAKER","AUDIO_ROUTE, Leaving state ActiveSpeakerRoute: ICA.sAR->CARSM.pM_USER_SWITCH_BASELINE_ROUTE->CARSM.pM_USER_SWITCH_HEADSET");
		validate_ADB_Logs("XP8_Device_QA_Sanity_007","turning on mute: true","turning on mute: false");
	}

	
	@Test(priority=8,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_008_MT_Voice_call(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_008 =======================");
		endCall_RefDevice();
		make_Call_from_RefDev();
		customWait(10000);
		recieve_Call_PrimaryDev_O();
		customWait(5000);
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}

	
	@Test(priority=9,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_009_Reject_MT_Voice_Call(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_009 =======================");
		launch_APP(Locators_XP8_Sanity.phone);
		clear_Call_History();
		endCall_RefDevice();
		make_Call_from_RefDev();
		customWait(20000);
	//	aDriver.pressKeyCode(6);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 30");
		customWait(5000);
		//endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		validate_Num_In_CallLog(refNum);
		clear_Call_History();
	}	

	
	@Test(priority=10,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_010_MT_Call_Reject_with_SMS_Notification(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_010 =======================");
		WebDriverWait wait = new WebDriverWait(aDriver, 40);
		endCall_RefDevice();
		if(p_b_No.contains("-10.")) {       				// AT & T
			launch_APP(Locators_XP8_Sanity.messaging);
			clearSMSPermissions();
			delete_All_SMS();
			make_Call_from_RefDev();
			customWait(10000);
			reject_Call_With_SMS_O(dt.get("message"));
			customWait(4000);
			validate_SentMessage();
			delete_SMS();
		}
		else if(p_b_No.contains("-11.")){					// Bell Device
			launch_MessageApp();
			clearSMSPermissions();
			delete_All_SMS_Bell();
			make_Call_from_RefDev();
			customWait(10000);
			reject_Call_With_SMS_O(dt.get("message"));
			customWait(4000);
			validate_SentMessage();
		}
		else if (p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			//delete_All_SMS1();
			delete_SMS_O();
			customWait(3000);
			make_Call_from_RefDev();
			customWait(6000);
			reject_Call_With_SMS_O(dt.get("message"));
			wait.until(ExpectedConditions.visibilityOf(Locators_XP8_Sanity.firstSMS_InList_O));	
			if(isElementExist(Locators_XP8_Sanity.firstSMS_InList_O))
				clickBtn(Locators_XP8_Sanity.firstSMS_InList_O);
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	
	@Test(priority=11,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_011_MO_VoiceCall_from_CallHistory(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException, ParseException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_011 =======================");
		if(p_b_No.contains("-10.")||p_b_No.contains("-11.")) {   //AT&T , BELL
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.phone);
		clear_Call_History();
		dailCallUsingDailPad(refNum);
		end_Call();
		launch_APP(Locators_XP8_Sanity.phone);
		clickOn_CallBtn_O();
		customWait(5000);
		validate_Locators1(Locators_XP8_Sanity.speaker_call);
		end_Call();
		customWait(4000);
		validate_Num_In_CallLog(refNum);
		clear_Call_History_O();
		}
	}

	
	@Test(priority=12,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_012_Check_the_Call_history_for_details(Hashtable<String, String> dt) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_012 =======================");
		endCall_RefDevice();
		clearRecentApps();
		if (p_b_No.contains("-10.")||p_b_No.contains("-11.")) {   	//ATT ,BELL
		launch_APP(Locators_XP8_Sanity.phone);
		clear_Call_History();
		dailCallUsingDailPad(refNum);
		customWait(6000);
		end_Call();
		customWait(3000);
		validate_Call_Details_and_Delete(refNum);
		} else if(p_b_No.contains("-15.")){
			validate_Call_Details_and_Delete_O(refNum);
		}		
	}

	
	@Test(priority=13,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_013_Send_SMS_To_New_Number_and_Delete(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_013 =======================");
		clearRecentApps();
		endCall_RefDevice();
		if (p_b_No.contains("-10.")) {						//ATT
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum, dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		}else if(p_b_No.contains("-11.")) {					//Bell
			launch_MessageApp();
			create_NewSMS_bell(refNum, dt.get("message"));
			clickOn_Send_bell();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_SentMessage();
			delete_All_SMS_Bell();
		}
		else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	
	@Test(priority=14,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_014_Send_SMS_SavedContact(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_014 =======================");
		if (p_b_No.contains("-10.")) {
		launch_APP(Locators_XP8_Sanity.contacts);
		setDefaultAccount_Contact_O();
		add_Contact_ADB_Command_O(dt.get("contactName"), refNum);
		
			launch_APP(Locators_XP8_Sanity.messaging);
			navigateTo_NewSMS();
			enterText_MessageField(dt.get("message"));
			customWait(2000);
			addContactToMsg_FromContacts(dt.get("contactName"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
			launch_APP(Locators_XP8_Sanity.contacts);
			deleteContact_O(dt.get("contactName"));
			
		}else if (p_b_No.contains("-11.")) {
			launch_APP(Locators_XP8_Sanity.contacts);
			setDefaultAccount_Contact_O();
			add_Contact_ADB_Command_O(dt.get("contactName"), refNum);
			launch_MessageApp();
			click_NewSMS_bell();
			enterTextToInputField(Locators_XP8_Sanity.sms_ToField, dt.get("contactName"));
			clickOn_contact();
			enterTextToInputField(Locators_XP8_Sanity.sms_TextField_bell, dt.get("message"));
			clickOn_Send_bell();
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_SentMessage();
			delete_All_SMS_Bell();
			launch_APP(Locators_XP8_Sanity.contacts);
			deleteContact_O(dt.get("contactName"));	
		}
		
		else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			navigateTo_NewSMS_O();
			addContactToMsg_FromContacts_O(dt.get("contactName"));
			enterText_MessageField_O(dt.get("message"));			
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
		launch_APP(Locators_XP8_Sanity.contacts);
		deleteContact_O(dt.get("contactName"));
	}

	
	@Test(priority=15,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_015_Send_SMS_With_AlphaNumeric_SpecialCharacters(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_015 =======================");
		endCall_RefDevice();
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum, dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if(p_b_No.contains("-15.")){
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	
	@Test(priority=16,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_016_Check_able_to_send_multi_page_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_016 =======================");

		if(p_b_No.contains("-10.")||p_b_No.contains("-00.")) {

			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum,dt.get("message"));
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if (p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	
	@Test(priority=17,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_017_Check_Able_Receive_SMS(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_017 =======================");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			delete_SMS();
			clickBackButton(1);
			minWait();
			unlock_RefPhone();
			sendSMS_fromRefDevice(dt.get("message"));
			validate_RecievedMessage();
			delete_SMS();
		} else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);
			minWait();
			sendSMS_fromRefDevice(dt.get("message"));
			validate_RecievedMessage_O();
			delete_SMS_O();
		}		
	}

	
	@Test(priority=18,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_018_Save_SMS_as_Draft(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_018 ======================="); 

		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			create_NewSMS(refNum, dt.get("message"));
			clickBackButton(2);
			validate_Locators1(Locators_XP8_Sanity.draft);
			clickBtn(Locators_XP8_Sanity.draft);
			clickOn_Send();
			validate_SentMessage();
			delete_SMS();
		} else if(p_b_No.contains("-15.")) {

			launch_APP(Locators_XP8_Sanity.MessagePlus);
			create_NewSMS_O(refNum,dt.get("message"));
			clickOn_BackBtn();
			clickOn_BackBtn();
			validate_Locators1(Locators_XP8_Sanity.draft);
			clickBtn(Locators_XP8_Sanity.draft);
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	
	@Test(priority=19,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_019_Check_able_to_send_MMS_and_Delete(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_019 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		ON_Switch("data");
		if (p_b_No.contains("-10.")||p_b_No.contains("-00.")) {
			launch_APP(Locators_XP8_Sanity.messaging);
			delete_All_SMS();
			navigateTo_NewSMS();
			enter_Num_ToField(refNum);
			add_Picture_O();
			enterText_MessageField(dt.get("message"));
			clickOn_Send();
			clickBackButton(2);
			validate_SentMessage();
			delete_SMS();
		} else if(p_b_No.contains("-15.")) {
			launch_APP(Locators_XP8_Sanity.MessagePlus);		
			create_NewSMS_O(refNum, dt.get("message"));
			capturePic_MMS_O();
			clickOn_Send_O();
			validate_SentMessage_O();
			delete_SMS_O();
		}
	}

	
	@Test(priority=20,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_020_Create_Contact_with_all_Fields_In_PhoneMemory(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_020 =======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		deleteContact_O(dt.get("contactName"));
		add_NewContact_withAllFields_O(dt.get("contactName"),dt.get("lastName"),dt.get("phoneticlastName"),dt.get("phoneticmiddleName"),
				dt.get("phoneticName"), dt.get("nickName"),dt.get("company"), dt.get("title"),refNum,dt.get("SIP"),dt.get("email"),
				dt.get("address"),dt.get("IM"),dt.get("webSite"),dt.get("relationship"),dt.get("notes"));
		validate_Added_Contact_O(dt.get("contactName"));
		
	}	 

	
	@Test(priority=21,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_021_Create_Contact_With_Name_ContactNumber_SIM_Memory(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_021 =======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		deleteContact_O(data.get("contactName"));
		//For Below Method user should enter 1 to save for phone memory and 2 to save for SIM memory in First Argument.
		add_NewContact(2,data.get("contactName"),refNum,data.get("contactEmail"),data.get("address"));
		validate_Added_Contact_O(data.get("contactName"));
		
	}

	// Test case 22 is interlinked with test case 21 without running that it will fail
	@Test(priority=22,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_022_Delete_Saved_Contacts(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_022 =======================");
		launch_APP(Locators_XP8_Sanity.contacts);
		deleteContact_O(dt.get("contactName1"));
		validate_deleted_Contact(dt.get("contactName1"));
		
	}

	
	@Test(priority=23,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_023_Enable_Disable_MobileData_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_DeviceSanity_023=======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		wiFi_OFF();// Used Contains Text.
		scrollToText("Mobile network");
		ON_Switch("data");
		clearChromeCache();
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		clear_SearchBox();
		validate_And_BrowseIn_Chrome(data.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		OFF_Switch("Mobile data");
		clickBtn(Locators_XP8_Sanity.OK);
		launch_APP(Locators_XP8_Sanity.chrome);
		clear_SearchBox();
		validate_MobileData_Disable();
		/*swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.wifi_OffState_QuickPanel);
		customWait(4000);*/
		clickOnHomeBtn();
	}

	
	@Test(priority=24,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_024_Connection_Secured_Wifi(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_DeviceSanity_024 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		OFF_Switch("data");
		clickBtn(Locators_XP8_Sanity.OK);
		clickOn_BackBtn();
		scrollToTextContains("Wi");
		ON_Switch("Off");
		customWait(3000);
		remove_connectedNtwrk();
		customWait(3000);
		select_SSIDwifi(ssid);
		minWait();
		enter_Password(pswd);
		clickOn_Connect();
		customWait(2000);
		clearChromeCache();
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		clear_SearchBox();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
	}
	
	
	@Test(priority=25, dataProvider="XP8SanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_025_Enable_Disable_WiFi_DeviceSettings(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_DeviceSanity_025 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToTextContains("Wi");
		ON_Switch("Off");
		customWait(3000);
		remove_connectedNtwrk();
		customWait(3000);
		select_SSIDwifi(ssid);
		minWait();
		enter_Password(pswd);
		clickOn_Connect();
		customWait(2000);
		clearChromeCache();
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		clear_SearchBox();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		turnOff_wifi_adb();
		/*launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		wiFi_OFF();*/
		launch_APP(Locators_XP8_Sanity.chrome);
		clear_SearchBox();
		validate_MobileData_Disable();
	}
	
	
	@Test(priority=26,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_026_Enable_Disable_Airplane_Devicesettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_026 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollTo("Airplane mode");
		ON_Switch("Airplane mode");
		clickBtn(Locators_XP8_Sanity.OK);
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Enable(refNum);
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollTo("Airplane mode");
		OFF_Switch("Airplane mode");
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Disable(refNum);
		end_Call();
	}
	
	
	@Test(priority=27, dataProvider="XP8SanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_027_Enable_Disable_BT_And_Scan_BT_Devices(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_027 ======================");
		Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); // To Clear Battery full Popup if present.
		enable_BT_RefDevice();
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Connected devices");
		scrollToText("Bluetooth");
		OFF_Switch("On");
		ON_Switch("Off");
		customWait(4000);
		clickOnText("Pair new device");
		validate_BT_Devices();
		customWait(2000);
		clickOn_BackBtn();
		OFF_Switch("On");
		customWait(2000);
		validate_Locators1(Locators_XP8_Sanity.BT_Disable_Text);
		enable_BT_RefDevice(); // With the same Method we can Disable the BT.
	}
	
	
	@Test(priority=28,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_028_Enable_Disable_Location_Devicesettings(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_Device_QA_Sanity_028 =======================");
		String fN=startAdbLog();
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Security & location");
		scrollToText("Location");
		OFF_Switch("On");
		minWait();
		clickBtn(Locators_XP8_Sanity.close);
		minWait();
		ON_Switch("Off");customWait(5000);
		OFF_Switch("On");customWait(12000);

		SoftAssert sf = new SoftAssert();
		String string1 = "Network location enabled.";
		boolean check1 = searchString(string1,fN);
		String string2 = "Network location disabled.";
		boolean check2 = searchString(string2,fN);	
		if (check1&&check2) {
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Validated from ADB Logs : "+string1+", "+string2);
		} else {
			String string3 = "location enabled :false";
			boolean check3 = searchString(string3,fN);
			String string4 = "location enabled :true";
			boolean check4 = searchString(string4,fN);
			if (check3&&check4) {
				check=true;
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Validated from ADB Logs : "+string1+", "+string2);
			} else {
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO, "Validation failed from ADB Logs.");
			}
		}
		sf.assertAll();
	}
	
	
	@Test(priority=29,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_029_Enable_Disable_WiFi_QuickSettings(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("====================== XP8_Device_QA_Sanity_029 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		//wiFi_OFF();
		scrollToText("Mobile network");
		OFF_Switch("data");
		clickBackButton(1);
		scrollToTextContains("Wi");
		ON_Switch("Off");
		customWait(3000);
		remove_connectedNtwrk();
		customWait(3000);
		select_SSIDwifi(ssid);
		minWait();
		enter_Password(pswd);
		clickOn_Connect();
		customWait(2000);
		turnOff_wifi_adb();
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.wifi_OffState_QuickPanel_O);
		customWait(5000);
		clearChromeCache();
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		clear_SearchBox();
		validate_And_BrowseIn_Chrome_O(data.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
		swipe_NotificationBar();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.wifi_OnState_QuickPanel_O);
		customWait(3000);
		launch_APP(Locators_XP8_Sanity.chrome);
		customWait(2000);
		clear_SearchBox();
		validate_MobileData_Disable_O();
	}
	
	
	@Test(priority=30,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_030_Enable_Disable_Bluetooth_QuickSettings(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("======================XP8_Device_QA_Sanity_030=======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Connected devices");
		BT_OFF();
		swipe_NotificationBar();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.BT_OffState_QuickPanel_O); 
		customWait(5000);
		disable_Switch_QuickPanel(Locators_XP8_Sanity.switch_On_State);
		customWait(10000);
		validate_ADB_Logs(fN, "MESSAGE_BLUETOOTH_STATE_CHANGE: BLE_ON > TURNING_ON", "MESSAGE_BLUETOOTH_STATE_CHANGE: BLE_ON > BLE_TURNING_OFF");
	}
	
	
	@Test(priority=31,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_031_Enable_Disable_AirplaneMode_QuickSettings(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("======================XP8_Device_QA_Sanity_031=======================");
		launch_APP(Locators_XP8_Sanity.phone);
		swipe_NotificationBar();
		swipe_QuickPanel_SecondPage();
		enable_Shortcuts_In_QuickPanel(Locators_XP8_Sanity.airplane_OffState_QuickPanel_O);
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Enable(refNum);
		swipe_NotificationBar();
		swipe_QuickPanel_SecondPage();
		disable_Shortcuts_QuickPanel(Locators_XP8_Sanity.airplane_OnState_QuickPanel_O);
		launch_APP(Locators_XP8_Sanity.phone);
		dailCallUsingDailPad(refNum);
		validate_Airplane_Disable(refNum);
		end_Call();
	}
	
	
	@Test(priority=32,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_032_Charging_Over_USB_And_BatteryLevel_Display(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("=====================XP8_Device_QA_Sanity_032=======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Battery();
		validate_BatteryCharging_Or_Full_O();
	}

	
	@Test(priority=33,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_033_Verify_Date_Time_And_TimeZone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_DeviceSanity_033 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("System");
		scrollToText("Date & time");
		enableAuto_DateTime_Timezone();
		validate_DateTime_And_Timezone_O();
	}
	
	
	@Test(priority=34,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_034_Add_APNs(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_DeviceSanity_034 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_APN_O();
		validate_APN_Diaplay();
		clickOnAddAPN();
		add_Or_Edit_APN(data.get("apnName"), data.get("apn"));
		customWait(8000);
		validate_ADB_Logs(fN, "Apn="+data.get("apn"));
	}
	
	// Methods 34 and 35 are inter linked.
	@Test(priority=35,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_035_Edit_And_Delete_APN(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_DeviceSanity_035 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_APN_O();
		click_APN(data.get("apnName"));
		add_Or_Edit_APN(data.get("newApnName"), data.get("newApn"));
		customWait(5000);
		validate_ADB_Logs(fN, "Apn="+data.get("newApn"));
		click_APN(data.get("newApnName"));
		delete_APN();
	}
	
	@Test(priority=36,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_036_Verify_Internal_Storage_Space_Capacity(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_DeviceSanity_036=======================");
		launch_APP(Locators_XP8_Sanity.settings);
		navigateTo_Storage();
		validate_StorageSpace("64");
	}
	
	
	@Test(priority=37,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_037_Validate_Volume_Up_And_Down_MOCall(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_DeviceSanity_037 =======================");
		String fN = startAdbLog();
		endCall_RefDevice();
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollTo("Airplane mode");
		OFF_Switch("Airplane mode");
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
	
	
	@Test(priority=38,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_038_Launch_Application_With_PTT_Key(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_DeviceSanity_038 =======================");
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
	
	
	@Test(priority=39,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_039_Browse_Using_MobileData(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_DeviceSanity_039 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		wiFi_OFF();
		scrollToText("Mobile network");
		ON_Switch("data");
		launch_APP(Locators_XP8_Sanity.chrome);
		clear_SearchBox();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
	}
	
	
	@Test(priority=40,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_040_LoadHomePage_Browser_MobileData(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_DeviceSanity_040 =======================");
		if (p_b_No.contains("-10.")) {
			launch_APP(Locators_XP8_Sanity.chrome);
			clear_SearchBox();
			customWait(12000);
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
			test.log(LogStatus.INFO, "This Test Case is Applicable ONLY for ATT Operator.");
		}
	}	
	
	@Test(priority=41,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_041_Browse_Using_WiFi_Data(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_DeviceSanity_041 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		OFF_Switch("data");
		clickBackButton(1);
		scrollToTextContains("Wi");
		ON_Switch("Off");
		customWait(3000);
		remove_connectedNtwrk();
		customWait(3000);
		select_SSIDwifi(ssid);
		minWait();
		enter_Password(pswd);
		clickOn_Connect();
		customWait(2000);
		clearChromeCache();
		launch_APP(Locators_XP8_Sanity.chrome);
		clearChromePermission();
		clear_SearchBox();
		validate_And_BrowseIn_Chrome(dt.get("url"), Locators_XP8_Sanity.googleCoIn_Text, Locators_XP8_Sanity.google_Logo, Locators_XP8_Sanity.google_Logo);
	}
	
	
	@Test(priority=42,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_042_LoadHomePage_Browser_WiFiData(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_DeviceSanity_042 =======================");
		if (p_b_No.contains("-10.")) {
			launch_APP(Locators_XP8_Sanity.chrome);
			clear_SearchBox();
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
			test.log(LogStatus.INFO, "This Test Case is Applicable ONLY for ATT Operator.");
		}
	}
	
	
	@Test(priority=43,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_043_Record_Media_Using_VoiceRecorder(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_DeviceSanity_043=======================");
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Users & accounts");
		if(isElementExist(Locators_XP8_Sanity.google_Account)) {
			remove_GoogleAcccount_O();
		}
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		delete_presentAudioFiles();
		clearAllow();
		clickOn_Record();
		clearAllow();
		customWait(10000);		
		clickOn_StopRecord();
		clickOn_Save(data.get("fileName"));
		customWait(2000);
		validate_SoundRecList(data.get("fileName"));
	}
	
	@Test(priority=44,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_044_PlayMusic_from_MusicPlayer(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("======================XP8_DeviceSanity_044=======================");
		launch_APP(Locators_XP8_Sanity.PlayMusic);
		customWait(4000);
		clickBtn(Locators_XP8_Sanity.SKIP);minWait();
		clearAllow();   customWait(3000);
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 380 486");
		minWait();
		clickBtn(Locators_XP8_Sanity.audioRecords_Music_1); 
		customWait(3000);
		validate_Locators1(Locators_XP8_Sanity.autoFile_Recorded_File);
		clickBtn(Locators_XP8_Sanity.autoFile_Recorded_File);
		customWait(5000);
		validate_Locators1(Locators_XP8_Sanity.play_pause_header);
		launch_APP(Locators_XP8_Sanity.soundRecorder);
		clickOn_RecordList();
		delete_SoundRecList();
	}
	
	
	@Test(priority=45,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_045_TakePicture_With_Back_Camera(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_DeviceSanity_045 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		clickOnCapture();
		customWait(10000);
		validate_ADB_Logs(fN, "com.android.camera.NEW_PICTURE");
	}
	
	
	@Test(priority=46,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_046_TakePicture_With_Front_Camera(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===================== XP8_DeviceSanity_046 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.camera);
		changeTo_Front_Back_Cam_O();
		clickOnCapture();
		changeTo_Front_Back_Cam_O();
		customWait(10000);
		validate_ADB_Logs(fN, "com.android.camera.NEW_PICTURE");
	}
	
	@Test(priority=47,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_047_TakeVideo_With_Front_Camera(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_DeviceSanity_047 =======================");
		String fN = startAdbLog();
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.camera);
		clearCameraPermission();
		clickVideoButton();
		customWait(8000);
		clickVideoButton();
		customWait(8000);
		validate_ADB_Logs(fN, "encoder (video) stopped");
	}

	@Test(priority=48,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_048_TakeVideo_With_Back_Camera(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_DeviceSanity_048 =======================");
		String fN = startAdbLog(); 
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.camera);
		changeTo_FrontCam();
		clickVideoButton();
		customWait(8000);
		clickVideoButton();
		customWait(8000);
		validate_ADB_Logs(fN, "encoder (video) stopped");//com.android.camera.NEW_VIDEO
	}	
	
	@Test(priority=49,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_049_Change_SleepMode_Duration(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_DeviceSanity_049 =======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Dispaly();
		clickBtn(Locators_XP8_Sanity.down_Triangle);
		clickOn_Sleep();
		clickBtn(Locators_XP8_Sanity.tenMin_Sleep);minWait();
		validate_Locators1(Locators_XP8_Sanity.tenMinutes_Inactivity);
		clickOn_Sleep();
		clickBtn(Locators_XP8_Sanity.thirtyMin_Sleep);minWait();
		validate_Locators1(Locators_XP8_Sanity.thirtyMinutes_Inactivity);
	}
	
	@Test(priority=50,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_050_Add_Google_Account(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_DeviceSanity_050 =======================");
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollTo("Airplane mode");
		OFF_Switch("Airplane mode");
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Users & accounts");
		remove_GoogleAcccount_Orio();
		navigateTo_AddGoogleAccount();
		add_GoogleAccount(dt.get("emailId"), dt.get("password")); 
		validate_GoogleAcccount(dt.get("emailId"));
	}
	
	@Test(priority=51,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_051_Download_APK_from_Playstore(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("====================== XP8_DeviceSanity_051 =======================");
		clearRecentApps();
		System.out.println(dt.get("appName"));
		launch_APP(Locators_XP8_Sanity.PlayStore);
		customWait(3000);
		install_App(dt.get("appName"));
		validate_Installed_App(dt.get("appName"));
		clearRecentApps();
		launch_APP(Locators_XP8_Sanity.PlayStore);
		unInstall_App(dt.get("appName"));
	}
	
	@Test(priority=52,dataProvider="XP8SanityTest", dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_052_Launch_Calendar_Add_an_Event(Hashtable<String, String> dt) throws InterruptedException, AWTException, IOException, ParseException {

		APP_LOGS.info("===================== XP8_DeviceSanity_052 =======================");
		launch_APP(Locators_XP8_Sanity.Calendar);
		clear_Calender_Permission();
		add_CalenderEvent(dt.get("eventName"));
		customWait(5000);
		validate_CalenderEvent(dt.get("eventName"));
		customWait(8000);
		delete_calenderEvent(dt.get("eventName"));
		launch_APP(Locators_XP8_Sanity.settings);
		scrollToText("Users & accounts");
		remove_GoogleAcccount_O();
	}
	
	@Test(priority=53,dataProvider="XP8SanityTest", dataProviderClass= DataProviders.class)
	public void XP8_DeviceSanity_053_StreamVideo_via_YouTube(Hashtable<String, String> dt) throws InterruptedException, IOException {

		APP_LOGS.info("===================== XP8_DeviceSanity_053 ======================");
		launch_APP(Locators_XP8_Sanity.settings);
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		ON_Switch("Mobile data");
		launch_APP(Locators_XP8_Sanity.youTube);
		customWait(8000);
		clickBtn(Locators_XP8_Sanity.firstVideo_YT);		
		customWait(6000);
		validate_Locators1(Locators_XP8_Sanity.playerView_YT);
	}
	
	@Test(priority=54,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_054_Validate_Turn_On_Off_FM(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("====================== XP8_DeviceSanity_054 =======================");
		String fN=startAdbLog();
		launch_APP(Locators_XP8_Sanity.fmRadio);
		FM_ON();
		customWait(3000);
		FM_OFF();
		customWait(2000);
		FM_ON();
		customWait(3000);
		FM_OFF();
		customWait(10000); 
		//validate_ADB_Logs(fN, "FMRxStarting ---> NEW-STATE 1: FMRxOn", "FMTurningOff ---> NEW-STATE : FMOff");
		validate_ADB_Logs(fN, "UART Flow On", "UART Flow Off");
	}
	
	@Test(priority=55,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_055_Validate_Tune_FMStation(Hashtable<String, String> data) throws AWTException, InterruptedException, IOException {

		APP_LOGS.info("====================== XP8_DeviceSanity_055 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.fmRadio);
		FM_ON();
		customWait(3000);
		scan_AllStations();
		FM_OFF();
		customWait(10000);
		validate_ADB_Logs(fN, "searchStations: CURRENT-STATE : FMRxOn ---> NEW-STATE : SearchInProg");
		clickOnHomeBtn();
	}
	
	@Test(priority=56,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_056_Add_AlarmEvent_Clock(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_DeviceSanity_056 =======================");
		String fN = startAdbLog();
		launch_APP(Locators_XP8_Sanity.clock);
		addAndDelete_Alarm();
		customWait(8000);
		validate_ADB_Logs(fN, "Created new alarm instance: AlarmInstance");

	}
	
	@Test(priority=57,dataProvider="XP8SanityTest",dataProviderClass=DataProviders.class)
	public void XP8_DeviceSanity_057_Snooze_Alarm(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException {

		APP_LOGS.info("====================== XP8_DeviceSanity_057 =======================");
		google_Search("set alarm for 2 minute from now");
		customWait(5000);
		clickBtn(Locators_XP8_Sanity.alarm_Execute_button);
		minWait();
		long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(2L, TimeUnit.MINUTES);
		boolean chk=false;
		while ( System.nanoTime() < endTime ) {
			Read_File.takeScreenShotForOcr("2");
			customWait(2000);
			my_main.validate_Using_OCR("2.jpeg");
			//chk = my_main.searchOCRString("Clock");
			if(chk) {
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 260 380");
				minWait();
				break;
			} 
		}	
		if(chk) {
			test.log(LogStatus.PASS, "SNOOZE popup displayed successfully.");
		} else {
			test.log(LogStatus.FAIL, "SNOOZE popup didn't displayed.");
		}
		launch_APP(Locators_XP8_Sanity.clock);minWait();
		clickBtn(Locators_XP8_Sanity.alarm);
		validate_Locators1(Locators_XP8_Sanity.snoozing_Until);
		clickBtn(Locators_XP8_Sanity.switch_On_State);
	}





















}
