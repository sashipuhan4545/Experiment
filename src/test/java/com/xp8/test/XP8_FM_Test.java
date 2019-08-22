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
import com.xp8.util.Locators_FM;
import com.xp8.util.Locators_SoundRec;
import com.xp8.util.XP8_FM_Util;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_FM_Test extends XP8_FM_Util {
	
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_FM_Test.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.1").addSystemInfo(
				"Environment", "TEST");
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
	//	aDriver = (AndroidDriver<AndroidElement>)driver;
		Locators_FM loc=new Locators_FM(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
		
	}


	@Test(priority=1,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_001_Launch_and_Exit_FM(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_001============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_001");
		 recordVideo("XP8_FM_001");
		 launchFM();
		 validateFMLaunch();
		 logout();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	 
	 @Test(priority=2,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_002_ON_OFF_FM(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_002============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_002");
		 recordVideo("XP8_FM_002");
		 launchFM();
		 turnOFF_FM();
		 validateOFF_FM();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	

	 @Test(priority=3,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_003_Start_Stop_Save_Recording(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_003============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_003");
		 recordVideo("XP8_FM_003");
		 launchFM();
		 StartStopRecording();
		 validateRecording();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	

	 @Test(priority=4,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_004_FM_ChangeRecord_Duration(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_004============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_004");
		 recordVideo("XP8_FM_004");
		 launchFM();
		 selectSetting();
		 changeandValidateRecordDurtn();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	 
	 @Test(priority=5,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_005_FM_SleepTime_Set_and_Cancel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_005============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_005");
		 recordVideo("XP8_FM_005");
		 launchFM();
		 sleepTimeSet();
		 validateSleepSetNCancelSleep();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	
	@Test(priority=6,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_FM_006_Validate_Default_Regional_Band_Country_Target(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_FM_006============");
		clearRecentApps();
		startAdbLog("XP8_FM_006");
		recordVideo("XP8_FM_006");
		launchFM();
		selectSetting();
		validatedefaultChannel();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	
	 @Test(priority=7,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_007_FM_Restored_to_DefaultSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_007============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_007");
		 recordVideo("XP8_FM_007");
		 launchFM();
		 selectSetting();
		 changeSettingOptn();
		 validateResortDefaultSettg();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }

	 @Test(priority=8,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_008_FM_Scan_and_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_008============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_008");
		 recordVideo("XP8_FM_008");
		 launchFM();
		 validatescanAllStations();
		 validateDeleteChannel();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	 
	 @Test(priority=9,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_009_FM_Replace_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_009============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_009");
		 recordVideo("XP8_FM_009");
		 launchFM();
		 saveScanChannel();
		 validatereplaceChannel();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	 
	 @Test(priority=10,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_010_FM_Rename_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_010============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_010");
		 recordVideo("XP8_FM_010");
		 launchFM();
		 saveScanChannel();
		 validateRenameChannel();
		 validateDeleteChannel();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	 
	 @Test(priority=11,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_011_FM_ExistingChannel_ADD_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_011============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_011");
		 recordVideo("XP8_FM_011");
		 launchFM();
		 saveScanChannel();
		 addExistChannel();
		 validateExistingChannel();
		 validateDeleteChannel();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	 
	 @Test(priority=12,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_012_FM_ChangeChannel_Forward_And_Back_Button(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_012============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_012");
		 recordVideo("XP8_FM_012");
		 launchFM();
		 validateForwardAndBackBtn();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }

	  @Test(priority=13,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_013_FM_ChangeStations_Forward_And_Back_Button(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		  APP_LOGS.info("===========XP8_FM_013============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_013");
		 recordVideo("XP8_FM_013");
		 launchFM();
		 validateForwardBackwrdStations();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	
	
	 @Test(priority=14,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_014_FM_VoiceCall_interaction(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_014============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_014");
		 recordVideo("XP8_FM_014");
		 launchFM();
		 initialCall();
		 validateCallInteraction();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	
	 @Test(priority=15,dataProvider="LaunchFMRadioApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_015_Push_FM_Background(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_015============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_015");
		 recordVideo("XP8_FM_015");
		 launchFM();
		 pushBackgrdFM();
		 performOperatn();
		 validateFMOn();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	 
	 //Covered Open Fm from Notification 
	 @Test(priority=16,dataProvider="LaunchFMRadioApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_016_FM_OpenFM_from_Notification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_016============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_016");
		 recordVideo("XP8_FM_016");
		 launchFM();
		 RecordingStartStop();
		 validateRecording();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	 
	 @Test(priority=17,dataProvider="LaunchFMRadioApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_017_Mute_Speaker_Enable(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_017============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_017");
		 recordVideo("XP8_FM_017");
		 launchFM();
		 switchToMute();
		 validateMuteMode();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }

	 @Test(priority=18,dataProvider="LaunchFMRadioApp", dataProviderClass=DataProviders.class)
		public void XP8_FM_018_VolumeUp_Down(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_018============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_018");
		 recordVideo("XP8_FM_018");
		 launchFM();
		 volumeUpandDown();
		 validateVolumeUpDow();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed");	
	 }
	 
	 @Test(priority=19,dataProvider="LaunchFMRadioApp", dataProviderClass=DataProviders.class)
	  public void XP8_FM_019_FM_Tune_SaveChannel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	 {
		 APP_LOGS.info("===========XP8_FM_019============");
		 clearRecentApps();
		 startAdbLog("XP8_FM_019");
		 recordVideo("XP8_FM_019");
		 launchFM();
		 validateTune();
		 stopAdb();
		 test.log(LogStatus.PASS, "Test case status is Passed"); 
	 }
	 
}


