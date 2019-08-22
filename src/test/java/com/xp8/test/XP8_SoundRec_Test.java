package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
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
import com.xp8.util.Locators_Calculator;
import com.xp8.util.Locators_Contacts;
import com.xp8.util.Locators_SoundRec;
import com.xp8.util.XP8_Contacts_Util;
import com.xp8.util.XP8_SoundRec_Util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_SoundRec_Test  extends XP8_SoundRec_Util{

	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;
	
	@BeforeSuite
	public void beforeSuite()  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_SoundRecTest.html", true); //Provide Desired Report Directory Location and Name
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
		Locators_SoundRec loc=new Locators_SoundRec(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	

	
   @Test(priority=1,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_001_Launch_and_Exit_SoundRecorder(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
   {
	   APP_LOGS.info("===========XP8_SR_001============");
	   clearRecentApps();
	   startAdbLog("XP8_SR_001");
	   recordVideo("XP8_SR_001");
	   launchSounchRec();
	   validateSoundRecorderLaunch();
	   logout();
	   stopAdb();
	   test.log(LogStatus.PASS, "Test case status is Passed");	
   }
   

	@Test(priority=2,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_002_Launch_and_Select_ListOpt_Sounds(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SR_002============");
		clearRecentApps();
		startAdbLog("XP8_SR_002");
		recordVideo("XP8_SR_002");
		launchSounchRec();
		selectListOptn();
		validateListPage();
		
		test.log(LogStatus.PASS, "Test case status is Passed");
		logout();
		stopAdb();
	}
	
	@Test(priority=3,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_003_Record_and_Save_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SR_003============");
		clearRecentApps();
		startAdbLog("XP8_SR_003");
		recordVideo("XP8_SR_003");
		launchSounchRec();
		recordSound();
		validateRecordedSoundSaved();
		test.log(LogStatus.PASS, "Test case status is Passed");	
		deleteSavedRecorder();
		logout();
		stopAdb();
	}
	
	@Test(priority=4,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_004_Record_and_Discard_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SR_004============");
		clearRecentApps();
		startAdbLog("XP8_SR_004");
		recordVideo("XP8_SR_004");
		launchSounchRec();
		recordSound();
		DiscardSound();
		validateRecordedSoundDiscarded();
		test.log(LogStatus.PASS, "Test case status is Passed");	
		stopAdb();

	}
	
	@Test(priority=5,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_005_Record_and_Delete_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SR_005============");
		clearRecentApps();
		startAdbLog("XP8_SR_005");
		recordVideo("XP8_SR_005");
		launchSounchRec();
		recordSound();
		SaveSoundRecord();
		deleteSavedRecorder();
		validateSoundRecordDeletion();
		test.log(LogStatus.PASS, "Test case status is Passed");	
		stopAdb();
	}
	

	@Test(priority=6,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_006_Record_and_Edit_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SR_006============");
		clearRecentApps();
		startAdbLog("XP8_SR_006");
		recordVideo("XP8_SR_006");
		launchSounchRec();
		recordSound();
		SaveSoundRecord();
		editSoundRecordName();
		validateSoundRecordEdit();
		test.log(LogStatus.PASS, "Test case status is Passed");	
		deleteSavedRecorder();
		logout();
		stopAdb();
	}
	

	@Test(priority=13,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_007_Details_SoundRecord(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		clearRecentApps();
		startAdbLog("XP8_SR_007");
		recordVideo("XP8_SR_007");
		launchSounchRec();
		recordSound();
		SaveSoundRecord();
		logout();
		launchFilemanager();
//		validateDetailsSoundRec();
//		launchSounchRec();
//		selectListOptn();
		test.log(LogStatus.PASS, "Test case status is Passed");
		deleteSavedRecorder();
		logout();
		stopAdb();
	}

	@Test(priority=7,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_008_DeleteMutiple_Sound(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SR_008============");
		clearRecentApps();
		startAdbLog("XP8_SR_008");
		recordVideo("XP8_SR_008");
		launchSounchRec();
		addMultipleSoundRec();
		deleteMulSoundRec(); 
		validateMulDeleteSoundRec();
		test.log(LogStatus.PASS, "Test case status is Passed");	
		logout();
		stopAdb();

	}
	
	@Test(priority=8,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_009_Pause_and_Resume_SoundRecording(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SR_009============");
		clearRecentApps();
		startAdbLog("XP8_SR_009");
		recordVideo("XP8_SR_009");
		launchSounchRec();
		ValidatePauseAndResume();
		test.log(LogStatus.PASS, "Test case status is Passed");
		logout();
		stopAdb();
	}
	
	@Test(priority=9,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_010_Selectall_and_DeselectAll_Sounds(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SR_010============");
		clearRecentApps();
		startAdbLog("XP8_SR_010");
		recordVideo("XP8_SR_010");
		launchSounchRec();
		addMultipleSoundRec();
		selectAllSoundRec();
		validateSelectAll();
		deslectAllSoundRec();
		validateDeselectAll();
		test.log(LogStatus.PASS, "Test case status is Passed");
		deleteMulSoundRec();
		logout();
		stopAdb();
	}


	@Test(priority=10,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_011_Select_and_Play_SoundRecordingList(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_SR_011============");
		clearRecentApps();
		startAdbLog("XP8_SR_011");
		recordVideo("XP8_SR_011");
		launchSounchRec();
		recordSound();
		playSoundRecord();
		validateRecSoundPlay();
		test.log(LogStatus.PASS, "Test case status is Passed");
		deleteSavedRecorder();
		logout();
		stopAdb();
	}

	@Test(priority=11,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_012_VoiceCall_interaction(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException
	{
		APP_LOGS.info("===========XP8_SR_012============");
		clearRecentApps();
		startAdbLog("XP8_SR_012");
		recordVideo("XP8_SR_012");
		launchSounchRec();
		startRecord();
		selectRecordBtn();
		initialCall();
		stopAdb();
		validateCallInteraction();
		test.log(LogStatus.PASS, "Test case status is Passed");
		deleteSavedRecorder();
		logout();
	}

	@Test(priority=12,dataProvider="LaunchSoundRecorderApp", dataProviderClass=DataProviders.class)
	public void XP8_SR_013_SoundRec_In_SilentMode(Hashtable<String, String> data) throws InterruptedException, IOException, AWTException
	{
		APP_LOGS.info("===========XP8_SR_013============");
		clearRecentApps();
		startAdbLog("XP8_SR_013");
		recordVideo("XP8_SR_013");
		launchSounchRec();
		deviceSilentMode();
		selectRecordBtn();
		validateSoundRecording();
		test.log(LogStatus.PASS, "Test case status is Passed");
		logout();
		stopAdb();
	}
	
}
