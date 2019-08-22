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
import com.xp8.util.Locators_ProgrammableKey;
import com.xp8.util.Locators_SoundRec;
import com.xp8.util.XP8_ProgrammableKey_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_ProgrammableKey_Test extends XP8_ProgrammableKey_Util {
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_ProgrammableKey.html", true); //Provide Desired Report Directory Location and Name
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
		Locators_ProgrammableKey loc=new Locators_ProgrammableKey(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}

	@Test(priority=1,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_01_Validate_ProgrammableKey_in_NativeSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ProgrammableKey_01============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_01");
		recordVideo("XP8_ProgrammableKey_01");
		validate_ProgrammableKey_in_NativeSettings();
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=2,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_02_Validate_PTTKeySummaryText(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ProgrammableKey_02============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_02");
		recordVideo("XP8_ProgrammableKey_02");
		scroll_to_ProgrammableKey();
		validate_ProgrammableKeySummaryText(Locators_ProgrammableKey.PTTsummaryText);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}

	@Test(priority=30,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_03_Validate_successful_LaunchOf_YellowKeyCalculator(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ProgrammableKey_03============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_03");
		recordVideo("XP8_ProgrammableKey_03");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.calculator_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.calculator_YellwApp, Locators_ProgrammableKey.clock_Page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=4,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_04_Validate_SummaryText_Of_particularPTTKyApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ProgrammableKey_04============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_04");
		recordVideo("XP8_ProgrammableKey_04");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.camera_app);
		validate_ProgrammableKeySummaryText_of_ParticularApp("Camera");
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}


	/*@Test(priority=5,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_05_Validate_successful_LaunchOf_PTTKyAppUpdater(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ProgrammableKey_05============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_05");
		recordVideo("XP8_ProgrammableKey_05");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.app_Updater_app);
		validate_launchedApplication(Locators_ProgrammableKey.appUpdates_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}*/

	@Test(priority=6,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_06_Validate_successful_LaunchOf_PTTKeyCalculator(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_06============");
		startAdbLog("ProgrammableKey_06");
		recordVideo("ProgrammableKey_06");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.calculator_app);
		validate_launchedApplication(Locators_ProgrammableKey.calc_Page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=7,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_07_Validate_successful_LaunchOf_PTTKeyCalendar(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_07============");
		startAdbLog("ProgrammableKey_07");
		recordVideo("ProgrammableKey_07");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.calendar_app);
		validate_launchedApplication(Locators_ProgrammableKey.calendar_Page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}  


	@Test(priority=8,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_08_Validate_successful_LaunchOf_PTTKeyCamera(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_08============");
		startAdbLog("ProgrammableKey_08");
		recordVideo("ProgrammableKey_08");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.camera_app);
		validate_launchedApplication(Locators_ProgrammableKey.camera_Page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=9,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_09_Validate_successful_LaunchOf_PTTKeyClock(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_09============");
		startAdbLog("ProgrammableKey_09");
		recordVideo("ProgrammableKey_09");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.clock_app);
		validate_launchedApplication(Locators_ProgrammableKey.clock_Page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	@Test(priority=10,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_10_Validate_successful_LaunchOf_PTTKeyContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_10============");
		startAdbLog("ProgrammableKey_10");
		recordVideo("ProgrammableKey_10");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.contactTransfer_app);
		validate_launchedApplication(Locators_ProgrammableKey.contact_transfer_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=11,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_11_Validate_successful_LaunchOf_PTTKeyContacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_11============");
		startAdbLog("ProgrammableKey_11");
		recordVideo("ProgrammableKey_11");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.contacts_app);
		validate_launchedApplication(Locators_ProgrammableKey.contacts_native_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=12,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_12_Validate_successful_LaunchOf_PTTKeyDownloads(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_12============");
		startAdbLog("ProgrammableKey_12");
		recordVideo("ProgrammableKey_12");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.downloads_app);
		validate_launchedApplication(Locators_ProgrammableKey.downloads_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=13,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_13_Validate_successful_LaunchOf_PTTKeyFileManager(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_13============");
		startAdbLog("ProgrammableKey_13");
		recordVideo("ProgrammableKey_13");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.fileManager_app);
		validate_launchedApplication(Locators_ProgrammableKey.fileManager_page, data.get("summaryText"));
		logout();
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=14,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_14_Validate_successful_LaunchOf_PTTKeyFMRadio(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_14============");
		startAdbLog("ProgrammableKey_14");
		recordVideo("ProgrammableKey_14");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.fmRadio_app);
		validate_launchedApplication(Locators_ProgrammableKey.fmRadio_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=15,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_15_Validate_successful_LaunchOf_PTTKeyPhotos(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_15============");
		startAdbLog("ProgrammableKey_15");
		recordVideo("ProgrammableKey_15");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.photos_app);
		validate_launchedApplication(Locators_ProgrammableKey.photos_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=16,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_16_Validate_successful_LaunchOf_PTTKeyMessagingApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_16============");
		startAdbLog("ProgrammableKey_16");
		recordVideo("ProgrammableKey_16");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.messaging_app);
		validate_launchedApplication(Locators_ProgrammableKey.messaging_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}



	@Test(priority=17,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_17_Validate_successful_LaunchOf_PTTKeyPhone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_17============");
		startAdbLog("ProgrammableKey_17");
		recordVideo("ProgrammableKey_17");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.phone_app);
		validate_launchedApplication(Locators_ProgrammableKey.phone_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=18,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_18_Validate_successful_LaunchOf_PTTKeyPlayMusicApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_18============");
		startAdbLog("ProgrammableKey_18");
		recordVideo("ProgrammableKey_18");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.music_app);
		validate_launchedApplication(Locators_ProgrammableKey.music_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	@Test(priority=19,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_19_Validate_successful_LaunchOf_PTTKeySettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_19============");
		startAdbLog("ProgrammableKey_19");
		recordVideo("ProgrammableKey_19");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.settings_app);
		validate_launchedApplication(Locators_ProgrammableKey.setting_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	/*@Test(priority=20,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_20_Validate_successful_LaunchOf_PTTKeySonimBLE(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_20============");
		startAdbLog("ProgrammableKey_20");
		recordVideo("ProgrammableKey_20");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimBLE_app);
		validate_launchedApplication(Locators_ProgrammableKey.sonimBLE_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=21,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_21_Validate_successful_LaunchOf_PTTKeySonimScout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_21============");
		startAdbLog("ProgrammableKey_21");
		recordVideo("ProgrammableKey_21");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimScout_app);
		validate_launchedApplication(Locators_ProgrammableKey.sonimScout_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}


	@Test(priority=22,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_22_Validate_successful_LaunchOf_PTTKeySonimWarranty(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_22============");
		startAdbLog("ProgrammableKey_22");
		recordVideo("ProgrammableKey_22");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimScoutWarranty_app);
		validate_launchedApplication(Locators_ProgrammableKey.warrantyReg_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=23,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_23_Validate_successful_LaunchOf_PTTKeySonimCare(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_23============");
		startAdbLog("ProgrammableKey_23");
		recordVideo("ProgrammableKey_23");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimCare_app);
		validate_launchedApplication(Locators_ProgrammableKey.sonimCare_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=24,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_24_Validate_successful_LaunchOf_PTTKeySonimSetupWizard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_24============");
		startAdbLog("ProgrammableKey_24");
		recordVideo("ProgrammableKey_24");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.setUpWizard_app);
		validate_launchedApplication(Locators_ProgrammableKey.setUpWizard_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}*/

	@Test(priority=25,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_25_Validate_successful_LaunchOf_PTTKeySoundRecord(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_25============");
		startAdbLog("ProgrammableKey_25");
		recordVideo("ProgrammableKey_25");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.soundRecorder_app);
		validate_launchedApplication(Locators_ProgrammableKey.soundRecorder_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=26,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_26_Validate_successful_LaunchOf_PTTKeyChrome(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_26============");
		startAdbLog("ProgrammableKey_26");
		recordVideo("ProgrammableKey_26");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.chrome_app);
		validate_launchedApplication(Locators_ProgrammableKey.chrome_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=27,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_27_Validate_successful_LaunchOf_PTTKeyGmail(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_27============");
		startAdbLog("ProgrammableKey_27");
		recordVideo("ProgrammableKey_27");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.gmail_app);
		validate_launchedApplication(Locators_ProgrammableKey.gmail_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=28,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_28_Validate_successful_LaunchOf_PTTKeyMaps(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_28============");
		startAdbLog("ProgrammableKey_28");
		recordVideo("ProgrammableKey_28");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.maps_app);
		validate_launchedApplication(Locators_ProgrammableKey.maps_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	//=========================================================================================================================//
			
/*	@Test(priority=29,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_29_Validate_successful_LaunchOf_YellowKeyAppUpdater(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_Yellow_Key============");
		APP_LOGS.info("===========ProgrammableKey_29============");
		startAdbLog("ProgrammableKey_29");
		recordVideo("ProgrammableKey_29");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.app_Updater_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.AppUpdator_YellwApp, Locators_ProgrammableKey.appUpdates_page, data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	*/
		
	@Test(priority=31,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_30_Validate_successful_LaunchOf_YellowKeyCalendar(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ProgrammableKey_30============");
		// clearRecentApps();
		startAdbLog("ProgrammableKey_30");
		recordVideo("ProgrammableKey_30");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.calendar_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.calculator_YellwApp, Locators_ProgrammableKey.calendar_Page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=32,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_31_Validate_successful_LaunchOf_YellowKeyCamera(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_31============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_31");
		recordVideo("XP8_ProgrammableKey_31");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.camera_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.calculator_YellwApp, Locators_ProgrammableKey.camera_Page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=33,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_32_Validate_successful_LaunchOf_YellowKeyClock(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ProgrammableKey_32============");
		// clearRecentApps();
		startAdbLog("ProgrammableKey_32");
		recordVideo("ProgrammableKey_32");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.clock_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Clock_YellwApp, Locators_ProgrammableKey.clock_Page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=34,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_33_Validate_successful_LaunchOf_YellowKeyContactsTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ProgrammableKey_33============");
		// clearRecentApps();
		startAdbLog("ProgrammableKey_33");
		recordVideo("ProgrammableKey_33");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.contactTransfer_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.ContactTransfer_YellwApp, Locators_ProgrammableKey.contact_transfer_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=35,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_34_Validate_successful_LaunchOf_YellowKeyContacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_34============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_34");
		recordVideo("XP8_ProgrammableKey_34");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.contacts_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Contacts_YellwApp, Locators_ProgrammableKey.contacts_native_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=36,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_35_Validate_successful_LaunchOf_YellowKeyDownloads(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_35============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_35");
		recordVideo("XP8_ProgrammableKey_35");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.downloads_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Downloads_YellwApp, Locators_ProgrammableKey.downloads_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=37,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_36_Validate_successful_LaunchOf_YellowKeyFileManager(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_36============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_36");
		recordVideo("XP8_ProgrammableKey_36");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.fileManager_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.FileManager_YellwApp, Locators_ProgrammableKey.fileManager_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}
	
	@Test(priority=38,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_37_Validate_successful_LaunchOf_YellowKeyPhotos(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_37============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_37");
		recordVideo("XP8_ProgrammableKey_37");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.photos_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Photos_YellwApp, Locators_ProgrammableKey.photos_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}		
	
	@Test(priority=39,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_38_Validate_successful_LaunchOf_YellowKeyMessagings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_38============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_38");
		recordVideo("XP8_ProgrammableKey_38");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.messaging_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Messaging_YellwApp, Locators_ProgrammableKey.messaging_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}			
	
	
	@Test(priority=40,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_39_Validate_successful_LaunchOf_YellowKeyPhone(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_39============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_39");
		recordVideo("XP8_ProgrammableKey_39");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.phone_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Phone_YellwApp, Locators_ProgrammableKey.phone_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}		
	
	@Test(priority=41,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_40_Validate_successful_LaunchOf_YellowKeyPlayMusicApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_40============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_40");
		recordVideo("XP8_ProgrammableKey_40");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.music_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.PlayMusic_YellwApp, Locators_ProgrammableKey.music_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}	
	
	@Test(priority=42,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_41_Validate_successful_LaunchOf_YellowKeySettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_41============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_41");
		recordVideo("XP8_ProgrammableKey_41");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.settings_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Settings_YellwApp, Locators_ProgrammableKey.setting_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}	
	
	/*@Test(priority=43,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_42_Validate_successful_LaunchOf_YellowKeySonimBLEConnect(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_42============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_42");
		recordVideo("XP8_ProgrammableKey_42");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimBLE_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.SonimBLEConnect_YellwApp, Locators_ProgrammableKey.sonimBLE_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}	
	
	@Test(priority=44,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_43_Validate_successful_LaunchOf_YellowKeySonimScout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_43============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_43");
		recordVideo("XP8_ProgrammableKey_43");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimScout_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.SonimScout_YellwApp, Locators_ProgrammableKey.sonimScout_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}	
	
	@Test(priority=45,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_44_Validate_successful_LaunchOf_YellowKeySonimWarranty(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_44============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_44");
		recordVideo("XP8_ProgrammableKey_44");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimScoutWarranty_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Warranty_YellwApp, Locators_ProgrammableKey.warrantyReg_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}	
	
	@Test(priority=46,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_45_Validate_successful_LaunchOf_YellowKeySonimCare(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_45============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_45");
		recordVideo("XP8_ProgrammableKey_45");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.sonimCare_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.SonimCare_YellwApp, Locators_ProgrammableKey.sonimCare_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}	
	
	@Test(priority=47,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_46_Validate_successful_LaunchOf_YellowKeySonimSetupWizard(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_46============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_46");
		recordVideo("XP8_ProgrammableKey_46");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.setUpWizard_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.SonimSetupWizard_YellwApp, Locators_ProgrammableKey.setUpWizard_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}	*/
	
	@Test(priority=48,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void XP8_ProgrammableKey_47_Validate_successful_LaunchOf_YellowKeySoundRecorder(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_ProgrammableKey_47============");
		// clearRecentApps();
		startAdbLog("XP8_ProgrammableKey_47");
		recordVideo("XP8_ProgrammableKey_47");
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectYellwKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.soundRecorder_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.SoundRecorder_YellwApp, Locators_ProgrammableKey.soundRecorder_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		stopAdb();
		test.log(LogStatus.PASS, "Test case status is Passed");	
	}	
	
	@Test(priority=49,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_48_Validate_successful_LaunchOf_YellowKeyChrome(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_48============");
		startAdbLog("ProgrammableKey_48");
		recordVideo("ProgrammableKey_48");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.chrome_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Chrome_YellwApp, Locators_ProgrammableKey.chrome_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=50,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_49_Validate_successful_LaunchOf_YellowKeyGmail(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_49============");
		startAdbLog("ProgrammableKey_49");
		recordVideo("ProgrammableKey_49");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.gmail_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Gmail_YellwApp, Locators_ProgrammableKey.gmail_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=51,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_50_Validate_successful_LaunchOf_YellowKeyMaps(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_50============");
		startAdbLog("ProgrammableKey_50");
		recordVideo("ProgrammableKey_50");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.maps_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.Maps_YellwApp, Locators_ProgrammableKey.maps_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=52,dataProvider="ProgrammableKeyTest", dataProviderClass=DataProviders.class)
	public void ProgrammableKey_51_Validate_successful_LaunchOf_YellowKeyFMRadio(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException {

		APP_LOGS.info("===========ProgrammableKey_51============");
		startAdbLog("ProgrammableKey_51");
		recordVideo("ProgrammableKey_51");
		//clearRecentApps();
		scroll_to_ProgrammableKey();
		scrollToNoApplication(Locators_ProgrammableKey.SelectPTTKey);
		assign_app_to_ProgrammableKey(Locators_ProgrammableKey.fmRadio_app);
		validate_launchedApplicationYellowKey(Locators_ProgrammableKey.FMRadio_YellwApp, Locators_ProgrammableKey.fmRadio_page,data.get("summaryText"));
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
}
