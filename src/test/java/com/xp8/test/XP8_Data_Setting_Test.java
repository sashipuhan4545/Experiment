package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

//import org.apache.poi.ss.formula.PlainCellCache.Loc;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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
//import com.xp8.util.GetMethods;
import com.xp8.util.JsonFileReaderAndWriter;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_Data_Setting;
import com.xp8.util.XP8_Data_Setting_Util;
import com.xp8.util.appiumService;

import OCR.Read_File;
import OCR.my_main;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Data_Setting_Test extends XP8_Data_Setting_Util {

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException,
			ParseException, ParseException, ParseException, ParseException, ParseException {
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Data_Setting_Test.html", true); // Provide
																										// Desired
																										// Report
																										// Directory
																										// Location and
																										// Name
		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
		extent.addSystemInfo("Build #", JsonFileReaderAndWriter.primaryDevFirmwareReader())
				.addSystemInfo("Product", JsonFileReaderAndWriter.primaryDevModelReader())
				.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());
		fetch_Devices_Details();

	}
//
//	@BeforeSuite
//	public void numofTestCases() throws ClassNotFoundException {
//
//		appiumService.TOTAL_NUM_OF_TESTCASES = GetMethods.TotalTestcase("XP8_TC", this.getClass());
//
//	}

	@BeforeMethod()
	public void beforeMethod(Method method) {
		test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName()); // Test
																													// Case
																													// Start
																													// Here
	}

	@BeforeClass
	public void copyFilesToDevice() throws IOException, ParseException, ParseException, ParseException, ParseException,
			ParseException, ParseException {
		System.out.println("Executing clear log screen");
		File dir = new File("src/test/resources/adbLogs");
		if (dir.isDirectory() == false) {
			System.out.println("Not a directory. Do nothing");
			return;
		}

		File[] listFiles = dir.listFiles();
		for (File file : listFiles) {
			System.out.println("Deleting " + file.getName());
			file.delete();

		}

	}

	@AfterMethod()
	public void tearDown(ITestResult result, Method method) throws IOException, ParseException, ParseException,
			ParseException, ParseException, ParseException, ParseException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {

			String screenshot_path = captureScreenshot(method.getName());
			String image = test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL, result.getThrowable());
			clearRecentApps();
		}
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException {
		properties = loadDriverAndProperties();
		Locators_Data_Setting loc = new Locators_Data_Setting(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}
//----------------------test-----------------------------
  
	@Test(priority = 1, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_01_Data_Setting_Navigate_APN_Screen(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
	SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_01_Data_Setting============");
	      clearRecentApps();
		  launch_an_app("settings"); 
		  // navigate to APN screen
		  
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		  navigate_to_AccessPointName(); 
		  // Validate APN Screen
		 validate_navigatetoAPNscreen(sa);
		 //verify all the field 
		 click_ON_APNSetting();
		 validate_APNSettingopt(sa);
		 add_APN_Field();
		 Validate_Add_APNfield(sa);
		 discard_APN();
		  sa.assertAll();
		 
	}
	
	@Test(priority = 2, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_02_Data_Setting_ADD_Edit_Delete_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();

		APP_LOGS.info("===========XP8_TC_02_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		 disable_Airplane_Mode();
		 boolean enabled=enable_data();
	//navigate to APN SCreen
		navigate_to_APN();
	//add APN to the APNs Screen
        add_APNName(data.get("name1"));
        add_APNNo(data.get("APN1"));
        save_APN();
		webwait(Locators_Data_Setting.apnsframe, 30);
	//verify APN is  Added to the APN Screen
        validate_Added_APN(sa);
    //edit added APN in APNs Screen 
		editAPNnumber();
		add_APNName(data.get("name"));
		add_APNNo(data.get("APN"));
		save_APN();
		webwait(Locators_Data_Setting.apnsframe, 30);
		validate_edited_APN(sa);
		
		deleteAPNnumber();
		validate_deleted_APN(sa);
		sa.assertAll();
	}

	@Test(priority = 3, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_03_Data_Setting_Add_Maximum_APNs(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_03_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		 boolean enabled=enable_data();
		navigate_to_APN();
		add_APNName(data.get("name1"));
		add_APNNo(data.get("APN1"));
		save_APN();

		clickBtn(Locators_Data_Setting.NewAPN);
		add_APNName(data.get("name2"));
		add_APNNo(data.get("APN2"));
		save_APN();

		clickBtn(Locators_Data_Setting.NewAPN);
		add_APNName(data.get("name3"));
		add_APNNo(data.get("APN3"));
		save_APN();

		clickBtn(Locators_Data_Setting.NewAPN);
		add_APNName(data.get("name4"));
		add_APNNo(data.get("APN4"));
		save_APN();

		validate_Added_Max_APN(sa);
		sa.assertAll();
	}

	@Test(priority = 4, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_04_Data_Setting_select_diff_Field_and_Authentication_Type_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_04_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_AccessPointName();
		// Add different APN fields
		select_APNFields(data.get("name"), data.get("APN"), data.get("proxy"), data.get("port"),
				data.get("Username"), data.get("Password"), data.get("Server"), data.get("MMSC"));
		validate_differentField_In_APN(sa);
		// select authentication Type(pap).
		
		select_Authenticationtype(data.get("pap"));
		maxWait();
		// validate authentication type
		validate_Authenticationtype(sa);
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 5, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_05_Data_Setting_Resettodefault(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_05_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_AccessPointName();
		// reset the APN 
		reset_To_Default();
		webwait(Locators_Data_Setting.apnsframe, 30);
		// Verify APN is reseted or not
		validate_Reset_to_Default(sa);
		sa.assertAll();
	}

	@Test(priority = 6, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_06_Data_Setting_save_APN_without_Entering_APNNo(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_06_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_APN();
		add_APNName(data.get("name"));
		save_APN();
		webwait(Locators_Data_Setting.errormsginapn, 30);
		//verify error msg is displayed
		save_APN_without_Entering_APN(data.get("errormsg"), sa);
		save_APN();
		sa.assertAll();

	}

	@Test(priority = 7, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_07_Data_Setting_addapn_without_name_and_apnno(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_07_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_APN();
		save_APN();
		// save and validate the apn without entering data
		//verify error msg displayed
		webwait(Locators_Data_Setting.errormsginname, 30);
		validate_error_Msg(data.get("errormsg"), sa);
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 8, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_08_Data_Setting_lock_Unlock_Screen(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_APN();
		add_APNName(data.get("name1"));

		// lock and unlock the Screen
		lock_Unlock_Screen();
		validate_Lock_Unlock_Screen(sa);
		add_APNNo(data.get("APN1"));
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 9, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_09_Data_Setting_add_APN_Click_Backbutton(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_APN();
		// while saving APN click back button
		add_APNName(data.get("name1"));
		add_APNNo(data.get("APN"));
		add_APN_Click_Backbutton();
		webwait(Locators_Data_Setting.apnsframe, 30);
		Validate_add_APN_Then_Click_Back(sa, data.get("expectedtext"));
		sa.assertAll();
	}

	@Test(priority = 10, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_10_Data_Setting_enable_Disable_Airplane_Mode(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// enable and disable the airplane mode
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		
		  disable_Airplane_Mode();
		enable_Disable_Airplane_Mode();
		webwait(Locators_Data_Setting.airplanemodeswitchonoff, 30);
		Turn_On_Off_Airplane_Mode(4, sa, data.get("networkon"));
		launch_an_app("gmail");
		sa.assertAll();
	}

	@Test(priority = 11, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_11_Data_Setting_APN_protocol_And_Bearer(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while adding apn select apn protocol type
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_APN();
		add_APNName(data.get("name1"));
		add_APNNo(data.get("APN1"));
		select_APN_protocol("IPv6", sa);
		select_Bearer();
		validate_Bearer(sa);
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 12, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_12_Data_Setting_APN_protocol_IPv4_Or_IPv6(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_APN();
		add_APNName(data.get("name1"));
		add_APNNo(data.get("APN1"));
		select_APN_protocol(data.get("ipv4oripv6"), sa);
		save_APN();
		sa.assertAll();
	}
//
//	@Test(priority = 13, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
//	public void XP8_TC_13_Data_Setting_Bearer(Hashtable<String, String> data)
//			throws InterruptedException, AWTException, IOException {
//		SoftAssert sa = new SoftAssert();
//		// while adding APN Select bearer
//		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
//		clearRecentApps();
//		launch_an_app("settings");
//		navigate_to_APN();
//		add_APNName(data.get("name1"));
//		add_APNNo(data.get("APN1"));
//		
//		save_APN();
//		sa.assertAll();
//	}

	@Test(priority = 14, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_14_Data_Setting_Enable_Airplane(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// enable the data
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		enable_Airplane_Mode();
		validate_Mobile_network(sa);
		// launch the browser
		Launch_The_Browser(sa);
		sa.assertAll();
	}

	@Test(priority = 15, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_15_Data_Setting_Enable_Data(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// disable airplane mode
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  
		disable_Airplane_Mode();
		boolean enabled=enable_data();
		validate_enablemobile_data(enabled,sa);
		Launch_The_Browser(sa);
		sa.assertAll();
	}

	@Test(priority = 16, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_16_Data_Setting_enable_Data(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// disable airplane mode
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  
		disable_Airplane_Mode();
		boolean enabled=enable_data();
		validate_enablemobile_data(enabled,sa);
		make_Call_from_RefDev();
		customWait(5000);
		receiveCallInpriDevice();
		customWait(5000);
		endCall_RefDevice();
		customWait(5000);
		//validate_enablemobile_data(sa);
		Launch_The_Browser(sa);
		launch_an_app("gmail");
		sa.assertAll();
	}

	@Test(priority = 17, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_17_Data_Setting_validate_LTEservice(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// navigate and validate the LTEServices opt
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		network_Settingsopt();
		Validate_LTEservice(sa);
		sa.assertAll();
	}

	@Test(priority = 18, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_18_Data_Setting_Data_Setting_Edit_SaveorCancel_APN(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while editing apn click on cancel button
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_AccessPointName();
		AddAPNnumber(data.get("name1"), data.get("APN"));
		validate_Added_APN(sa);
		editAPNnumber();
		add_APNName(data.get("name"));
		add_APNNo(data.get("APN"));
		save_APN();
		validate_edited_APN(sa);
		CancelAPN(data.get("name"), data.get("APN"));
		validate_CancelAPN(sa);
		sa.assertAll();
	}

	@Test(priority = 19, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_19_Data_Setting_APN_Roaming_Protocol(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// while adding apn select APN Protocol
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_APN();
		add_APNName(data.get("name1"));
		add_APNNo(data.get("APN1"));
		select_APN_protocol(data.get("ipv4oripv6"), sa);
        customWait(5000);
		APN_Roaming_Protocol(data.get("ipv4oripv6"), sa);
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 20, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_20_Data_Setting_view_And_Selected_APNs(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// view and select the apn in APNs Screen
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_APN();
		add_APNName(data.get("name1"));
		add_APNNo(data.get("APN1"));

		view_And_Select_APNs(data.get("name1"));
		validate_View_And_Select_APNs(sa);

		sa.assertAll();
	}

	@Test(priority = 21, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_21_Data_Setting_APN_Added_To_The_List(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// add apn to the APNs List
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_AccessPointName();
		AddAPNnumber(data.get("name1"), data.get("APN1"));
		validate_Added_APN_To_The_List(sa);
        apn_Clk_Backbtn();
        validate_Click_Cackbtn(sa);
		sa.assertAll();
	}

	@Test(priority = 22, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_22_Data_Setting_discard_APNs(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// discard the apn from the list
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_AccessPointName();
		reset_To_Default();
		webwait(Locators_Data_Setting.apnsframe, 30);
		discard_APNs(data.get("name1"));
		Validate_Discard_APNs(sa);
		sa.assertAll();
	}

	@Test(priority = 23, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_23_Data_Setting_APN_Received_Call(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// discard the apn from the list
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		navigate_to_APN();
		add_APNName(data.get("name1"));
		make_Call_from_RefDev();
		customWait(5000);
		receiveCallInpriDevice();
		customWait(5000);
		endCall_RefDevice();
		webwait(Locators_Data_Setting.APNno, 30);
		validate_Receive_Call_In_PriDev(sa);
		add_APNNo(data.get("APN1"));
		save_APN();
		sa.assertAll();
	}

	@Test(priority = 24, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_24_Data_Setting_enable_disable_LTEservices(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// discard the apn from the list
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		launch_an_app("settings");
		  disable_Airplane_Mode();
		  boolean enabled=enable_data();
		enable_Disable_LTEservices();
		validate_Enable_Disable_LTEservices(sa);
		sa.assertAll();
	}
//	
	@Test(priority = 25, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_25_Data_Setting_EditAPN_Inlandscape(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();
		// discard the apn from the list
		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		// aDriver.rotate(ScreenOrientation.LANDSCAPE);
		scrollToText("CLEAR ALL");
	//	clickBtn(Locators_Data_)
		launch_an_app("settings");
		//navigate_to_APN_Landscape();
		//add_APNName1(data.get("name1"));
		add_APNNo(data.get("APN1"));
		save_APN();
		webwait(Locators_Data_Setting.apnsframe, 30);
		validate_Added_APN(sa);
		aDriver.rotate(ScreenOrientation.LANDSCAPE);
		editAPNnumber();
		add_APNName(data.get("name"));
		add_APNNo(data.get("APN"));
		save_APN();
		webwait(Locators_Data_Setting.apnsframe, 30);
		validate_edited_APN(sa);
		sa.assertAll();
	}

	@Test(priority = 111, dataProvider = "XP8_Data_Setting", dataProviderClass = DataProviders.class)
	public void XP8_TC_24_Data_Setting_APN_Setalarm(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException {
		SoftAssert sa = new SoftAssert();

		APP_LOGS.info("===========XP8_TC_08_Data_Setting============");
		clearRecentApps();
		set_Alram();
		takeScreenShot();
		Read_File.takeScreenShotForOcr("Alarm");
		my_main.validate_Using_OCR("Alarm.png");
		launch_an_app("settings");
		boolean enabled=enable_data();

		customWait(20000);
		Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 547 182");
		minWait();
		// adb -s " + p_Id + " shell service call telecom 29
		Runtime.getRuntime().exec("adb shell input touchscreen swipe 536 961 987 967");
		validate_enablemobile_data(enabled,sa);
			
			sa.assertAll();
	}


	

}
