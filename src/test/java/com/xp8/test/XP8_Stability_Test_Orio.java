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
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.mongodb.util.JSON;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.ATTUtil.Locators_Stability;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.JsonFileReaderAndWriter;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;

import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_Stability_Util_orio;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Stability_Test_Orio extends XP8_Stability_Util_orio {

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int itr = 3;
	boolean value = false;
	public String pw = "1dcS0n1md0tc0MbLr";

//	@BeforeSuite
//	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException, ParseException  
//	{
//		extent = new ExtentReports("src/test/resources/extentreport/XP8_DeviceStability_Orio_TestReport.html", true); //Provide Desired Report Directory Location and Name
//
//		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
//
//		extent.loadConfig(new File("src/test/resources/StorageFile/ReportsConfig.xml"));
//		extent.addSystemInfo("Build #",JsonFileReaderAndWriter.primaryDevFirmwareReader())
//		.addSystemInfo("Product",JsonFileReaderAndWriter.primaryDevModelReader())
//		.addSystemInfo("Operator", JsonFileReaderAndWriter.primaryDevOperatorReader());		
//
//		fetch_Devices_Details();	
//	}

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException {
		extent = new ExtentReports("src/test/resources/extentreport/XP8_WiFi_Stability_Orio_TestReport.html", true); // Provide
																														// Desired
																														// Report
																														// Directory
																														// Location
																														// and
																														// Name
		extent.loadConfig(new File(System.getProperty("user.dir") + "//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();
	}

//	@BeforeSuite
//	public void numofTestCases() throws ClassNotFoundException {
//		
//		appiumService.TOTAL_NUM_OF_TESTCASES=GetMethods.TotalTestcase("XP8_TC", this.getClass());
//	}
//
//	@BeforeMethod()
//	public  void beforeMethod(Method method) 
//	{
//		test = extent.startTest( (this.getClass().getSimpleName() +" :: "+  method.getName()),method.getName()); //Test Case Start Here
//		test.assignAuthor(""); //Test Script Author Name
//		String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
//		test.assignCategory("Build #:"+BuildNumber);
//	}

	@BeforeMethod()
	public void beforeMethod(Method method) {
		test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName()); // Test
																													// Case
																													// Start
																													// Here
		test.assignAuthor("Farheen Taj"); // Test Script Author Name
	}

	@BeforeClass
	public void copyFilesToDevice() throws IOException, ParseException {
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
	public void tearDown(ITestResult result, Method method) throws IOException, ParseException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshot_path = captureScreenshot(method.getName());
			String image = test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		clearRecentApps();
		extent.endTest(test);
		extent.flush();
	}

	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException {
		properties = loadDriverAndProperties();
		Locators_DeviceStability loc = new Locators_DeviceStability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

	}

	@BeforeTest
	public void tclearAppdata() {
		System.out.println("inside");

		try {
			if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {
				System.out.println("before test");
				launchAppThroughABD("adb shell pm clear com.android.mms");
				System.out.println("exit from Before Test");
			}
		}

		catch (Exception e) {
			// TODO: handle exception
		}

	}
	
//	@Test(priority = 1, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
//	public void XP8_TC_01_Stability_Validate_Contact_Creation(Hashtable<String, String> data)
//			throws InterruptedException, AWTException, IOException, ParseException, ParseException {
//		APP_LOGS.info("===========XP8_Stability_01============");
//		startAdbLog("XP8_Stability_01");
//		SoftAssert sa1 = new SoftAssert();
//		clearRecentApps();
//		launch_an_app("contacts");
//		deleteContacts();
//		for (int i = 1; i <= itr; i++) {
//			
//			if (p_b_No.contains("-29.")) {
//
//				createContact("Test" + i, refNum);
//				validateContactCreation(i, sa1);
//			} else if (p_b_No.contains("-15.")) {
//
//				createContact("Test" + i, refNum);
//				validateContactCreation(i, sa1);
//			}
//
//			else if (p_b_No.contains("-11.")) {
//
//				createContact("Test" + i, refNum);
//				validateContactCreation(i, sa1);
//			} else if (p_b_No.contains("-26.")) {
//
//				createContact("Test" + i, refNum);
//				validateContactCreation(i, sa1);
//			}
//
//			else if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
//
//				createContact("Test" + i, refNum);
//				validateContactCreation(i, sa1);
//
//			}
//		}
//		sa1.assertAll();
//	}
//
////
//
//	@Test(priority = 2, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
//	public void XP8_TC_02_Stability_Validate_Send_MO_IMS_SMS(Hashtable<String, String> data)
//			throws InterruptedException, AWTException, IOException, ParseException {
//		APP_LOGS.info("===========XP8_Stability_02============");
//		startAdbLog("XP8_Stability_02");
//		SoftAssert sa2 = new SoftAssert();
//		clearRecentApps();
//	 String fN = startRIL_Log();
//
//	value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");
//		
//		System.out.println(value);
//		if (true) {
//			for (int i = 1; i <= itr; i++) {
//
//				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
//					performAction();
//					launch_APP(Locators_DeviceStability.Messageing);
//					clearSMSPermissions();
//					navigateTo_NewSMS();
//					enter_Num_ToField(refNum);
//					enterText_MessageField("Automation Test");
//					clickOn_Send();
//					validate_SentMessage(i, sa2);
//					delete_SMS();
//
//				} else if (p_b_No.contains("-15.")) {
//					System.out.println("vwz");
//					performAction_O();
//					launch_APP(Locators_DeviceStability.MessagePlus);
//					clearSMSPermissions_O();
//					navigateTo_NewSMS_O();
//					enter_Num_ToField_O(refNum);
//					enterText_MessageField_O(data.get("typeMessage"));
//					clickOn_Send_O();
//					validate_SentMessage_O(i, sa2);
//					delete_SMS_O();
//				} else if (p_b_No.contains("-29.")) {
//					System.out.println("sprint");
//					performAction();
//					launch_APP(Locators_DeviceStability.messages);
//					clearSMSPermissions_O();
//					navigateTo_NewSMS_S();
//					enter_Num_ToField_B(refNum);
//					enterText_MessageField_B(data.get("typeMessage"));
//					clickOn_Send_B();
//					validate_SentMessage_B(i, sa2);
//					delete_SMS_s();
//				}
//
//				else if (p_b_No.contains("-11")) {
//					System.out.println("bell");
//					performAction();
//					launch_APP(Locators_DeviceStability.messages);
//					clearSMSPermissions_O();
//					navigateTo_NewSMS_S();
//					navigateTo_NewSMS_S();
//					enter_Num_ToField_B(refNum);
//					enterText_MessageField_B(data.get("typeMessage"));
//					clickOn_Send_B();
//					validate_SentMessage_B(i, sa2);
//					delete_SMS_s();
//				} else if (p_b_No.contains("-26")) {
//					System.out.println("SL");
//					performAction();
//					launch_APP(Locators_DeviceStability.messages);
//					clearSMSPermissions_O();
//					navigateTo_NewSMS_S();
//					navigateTo_NewSMS_S();
//					enter_Num_ToField_B(refNum);
//					enterText_MessageField_B(data.get("typeMessage"));
//					clickOn_Send_B();
//					validate_SentMessage_B(i, sa2);
//					delete_SMS_s();
//				} else {
//					System.out.println("Executing Else part");
//					launch_APP(Locators_DeviceStability.messaging);
//					clearSMSPermissions();
//					create_NewSMS(refNum, data.get("typeMessage"));
//					clickOn_Send();
//					validate_SentMessage(i, sa2);
//				}
//			}
//		} else {
//			test.log(LogStatus.SKIP, "NT: IMS is not Enabled");
//		}
//		sa2.assertAll();
//	}
//	
//	@Test(priority = 3, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
//	public void XP8_TC_03_Stability_Validate_Send_MO_Non_IMS_SMS(Hashtable<String, String> data)
//			throws InterruptedException, AWTException, IOException, ParseException {
//		APP_LOGS.info("===========XP8_Stability_03============");
//		startAdbLog("XP8_Stability_03");
//		SoftAssert sa3 = new SoftAssert();
//		clearRecentApps();
//		System.out.println(value);
//		if (!value) {
//			for (int i = 1; i <= itr; i++) {
//				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
//                    performAction();
//					launch_APP(Locators_DeviceStability.Messageing);
//					clearSMSPermissions();
//					navigateTo_NewSMS();
//					enter_Num_ToField(refNum);
//					System.out.println("Enter Text");
//					enterText_MessageField("Automation Test");
//					clickOn_Send();
//					validate_SentMessage(i, sa3);
//					delete_SMS();
//				} else if (p_b_No.contains("-15.")) {
//					performAction_O();
//					launch_APP(Locators_DeviceStability.MessagePlus);
//					clearSMSPermissions_O();
//					navigateTo_NewSMS_O();
//					enter_Num_ToField_O(refNum);
//					enterText_MessageField_O(data.get("typeMessage"));
//					clickOn_Send_O();
//					validate_SentMessage_O(i, sa3);
//					delete_SMS_O();
//				} else if (p_b_No.contains("-29.")) {
//					performAction();
//					System.out.println("sprint");
//					launch_APP(Locators_DeviceStability.messages);
//					clearSMSPermissions_O();
//					navigateTo_NewSMS_S();
//					enter_Num_ToField_B(refNum);
//					enterText_MessageField_B(data.get("typeMessage"));
//					// create_NewSMS_B(refNum,data.get("typeMessage"));
//					clickOn_Send_B();
//					validate_SentMessage_B(i, sa3);
//					delete_SMS_s();
//				} else if (p_b_No.contains("-11.")) {
//					System.out.println("bell");
//					performAction();
//					launch_APP(Locators_DeviceStability.messages);
//					clearSMSPermissions_O();
//					navigateTo_NewSMS_S();
//					enter_Num_ToField_B(refNum);
//					enterText_MessageField_B(data.get("typeMessage"));
//					clickOn_Send_B();
//					validate_SentMessage_B(i, sa3);
//					delete_SMS_s();
//				} else if (p_b_No.contains("-26.")) {
//					System.out.println("SL");
//					performAction();
//					launch_APP(Locators_DeviceStability.messages);
//					clearSMSPermissions_O();
//					navigateTo_NewSMS_S();
//					enter_Num_ToField_B(refNum);
//					enterText_MessageField_B(data.get("typeMessage"));
//					// create_NewSMS_B(refNum,data.get("typeMessage"));
//					clickOn_Send_B();
//					validate_SentMessage_B(i, sa3);
//					delete_SMS_s();
//				} else {
//					System.out.println("Executing Else part");
//					launch_APP(Locators_DeviceStability.messaging);
//					clearSMSPermissions();
//					navigateTo_NewSMS_O();
//					create_NewSMS(refNum, data.get("typeMessage"));
//					clickOn_Send();
//					validate_SentMessage(i, sa3);
//					delete_SMS();
//				}
//			}
//		} else {
//			test.log(LogStatus.SKIP, "NT: IMS is Enabled");
//		}
//		sa3.assertAll();
//	}
//
//	@Test(priority = 4, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
//	public void XP8_TC_04_Stability_Validate_MO_SMS_Maximum_NumberOfCharacters(Hashtable<String, String> data)
//			throws InterruptedException, AWTException, IOException, ParseException {
//		APP_LOGS.info("===========XP8_Stability_04============");
//		startAdbLog("XP8_Stability_04");
//		SoftAssert sa4 = new SoftAssert();
//		clearRecentApps();
//		for (int i = 1; i <= itr; i++) {
//
//			if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
//				performAction();
//				launch_APP(Locators_DeviceStability.Messageing);
//				clearSMSPermissions();
//				navigateTo_NewSMS();
//				enter_Num_ToField(refNum);
//				System.out.println("Enter Text");
//				enterText_MessageField(data.get("typeMessage1"));
//				clickOn_Send();
//				validate_SentMessage(i, sa4);
//				delete_SMS();
//			} else if (p_b_No.contains("-15.")) {
//				performAction_O();
//				launch_APP(Locators_DeviceStability.MessagePlus);
//				navigateTo_NewSMS_O();
//				create_NewSMS_O(refNum, data.get("typeMessage3"));
//				clickOn_Send_O();
//				validate_SentMessage_O(i, sa4);
//				delete_SMS_O();
//			} else if (p_b_No.contains("-29.")) {
//				System.out.println("sprint");
//				performAction();
//				launch_APP(Locators_DeviceStability.messages);
//				clearSMSPermissions_O();
//				navigateTo_NewSMS_S();
//				enter_Num_ToField_B(refNum);
//				enterText_MessageField_B(data.get("expectedChar&PageNum3"));
//				clickOn_Send_B();
//				validate_SentMessage_B(i, sa4);
//				delete_SMS_s();
//			}
//
//			else if (p_b_No.contains("-11.")) {
//				System.out.println("bell");
//				performAction();
//				launch_APP(Locators_DeviceStability.messages);
//				clearSMSPermissions_O();
//				navigateTo_NewSMS_S();
//				enter_Num_ToField_B(refNum);
//				enterText_MessageField_B(data.get("expectedChar&PageNum3"));
//				// create_NewSMS_B(refNum,data.get("expectedChar&PageNum3"));
//				clickOn_Send_B();
//				validate_SentMessage_B(i, sa4);
//				delete_SMS_s();
//			}
//
//			else if (p_b_No.contains("-26.")) {
//				System.out.println("SL");
//				performAction();
//				launch_APP(Locators_DeviceStability.messages);
//				clearSMSPermissions_O();
//				navigateTo_NewSMS_S();
//				enter_Num_ToField_B(refNum);
//				enterText_MessageField_B(data.get("expectedChar&PageNum3"));
//				// create_NewSMS_B(refNum,data.get("expectedChar&PageNum3"));
//				clickOn_Send_B();
//				validate_SentMessage_B(i, sa4);
//				delete_SMS_s();
//			} else {
//				System.out.println("Executing Else part");
//				launch_APP(Locators_DeviceStability.messaging);
//				navigateTo_NewSMS_O();
//				create_NewSMS(refNum, data.get("typeMessage3"));
//				validate_CharacterAndPageNumber(Locators_DeviceStability.zero_Characters_FirstPage,
//						data.get("expectedChar&PageNum3"), i);
//				clickOn_Send();
//				delete_SMS();
//			}
//		}
//		sa4.assertAll();
//	}
//	
//	@Test(priority = 5, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
//	public void XP8_TC_05_Stability_Validate_MT_SMS(Hashtable<String, String> data)
//			throws InterruptedException, AWTException, IOException, ParseException {
//		APP_LOGS.info("===========XP8_Stability_05============");
//		startAdbLog("XP8_Stability_05");
//		SoftAssert sa5 = new SoftAssert();
//		clearRecentApps();
//		for (int i = 1; i <= itr; i++) {
//
//			if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
//				performAction();
//				launch_APP(Locators_DeviceStability.Messageing);
//				sendSMS_fromRefDevice(data.get("typeMessage1"));
//				minWait();
//				validate_ReceivedMessage(i, sa5);
//			} else if (p_b_No.contains("-15.")) {
//				performAction_O();
//				launch_APP(Locators_DeviceStability.MessagePlus);
//				sendSMS_fromRefDevice(data.get("typeMessage1"));
//				minWait();
//				validate_RecievedMessage_O(i, sa5);
//			} else if (p_b_No.contains("-29.")) {
//				System.out.println("sprint");
//				performAction();
//				launch_APP(Locators_DeviceStability.messages);
//				sendSMS_fromRefDevice(data.get("typeMessage1"));
//				minWait();
//				validate_ReceivedMessage(i, sa5);
//				// validate_CharacterAndPageNumber_O(Locators_DeviceStability.zero_Characters_FirstPage_O,data.get("expectedChar&PageNum3"),i);
//
//			} else if (p_b_No.contains("-11.")) {
//				System.out.println("bell");
//				performAction();
//				launch_APP(Locators_DeviceStability.messages);
//				sendSMS_fromRefDevice(data.get("typeMessage1"));
//				minWait();
//				validate_ReceivedMessage(i, sa5);
//			} else if (p_b_No.contains("-26.")) {
//				System.out.println("SL");
//				performAction();
//				launch_APP(Locators_DeviceStability.messages);
//				sendSMS_fromRefDevice(data.get("typeMessage1"));
//				minWait();
//				validate_ReceivedMessage(i, sa5);
//			} else {
//				System.out.println("Executing Else part");
//				launch_APP(Locators_DeviceStability.messaging);
//				navigateTo_NewSMS_O();
//				create_NewSMS(pryNum, data.get("typeMessage1"));
//				clickOn_Send();
//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
//				validate_RecievedMessage_O(i, sa5);
//			}
//		}
//		sa5.assertAll();
//	}

	@Ignore
	@Test(priority = 6, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_06_Stability_Validate_MO_MMS_With_Picture(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_06============");
		startAdbLog("XP8_Stability_06");
		recordVideo("XP8_Stability_06");
		SoftAssert sa6 = new SoftAssert();
		clearRecentApps();
		for (int i = 1; i <= itr; i++) {
			if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
				launch_APP(Locators_DeviceStability.messaging);
				navigateTo_NewSMS();
				enter_Num_ToField(refNum);
				add_Picture_O();
				Locators_DeviceStability.type_text1.sendKeys("Test");
				clickOn_Send();
				validate_SentMessage(i, sa6);
				delete_SMS();
			} else if (p_b_No.contains("-15.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				navigateTo_NewSMS_O();
				create_NewSMS_O(refNum, data.get("typeMessage"));
				capturePic_MMS_O();
				clickOn_Send_O();
				validate_SentMessage_O(i, sa6);
				delete_SMS_O();
				customWait(8000);
			} else if (p_b_No.contains("-15.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				navigateTo_NewSMS_O();
				create_NewSMS_O(refNum, data.get("typeMessage"));
				capturePic_MMS_O();
				clickOn_Send_O();
				validate_SentMessage_O(i, sa6);
				delete_SMS_O();
				customWait(8000);
			}

			else if (p_b_No.contains("-11.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				navigateTo_NewSMS_O();
				create_NewSMS_O(refNum, data.get("typeMessage"));
				capturePic_MMS_O();
				clickOn_Send_O();
				validate_SentMessage_O(i, sa6);
				delete_SMS_O();
				customWait(8000);
			} else if (p_b_No.contains("-29.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				navigateTo_NewSMS_O();
				create_NewSMS_O(refNum, data.get("typeMessage"));
				capturePic_MMS_O();
				clickOn_Send_O();
				validate_SentMessage_O(i, sa6);
				delete_SMS_O();
				customWait(8000);
			} else {
				launch_APP(Locators_DeviceStability.messaging);
				navigateTo_NewSMS();
				enter_Num_ToField(refNum);
				add_Picture_O();
				enterText_MessageField(data.get("typeMessage"));
				clickOn_Send();
				validate_SentMessage(i, sa6);
				delete_SMS();
			}
		}
		sa6.assertAll();
	}

//
	@Ignore
	@Test(priority = 7, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_07_Stability_Validate_MO_MMS_With_Video(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_07============");
		startAdbLog("XP8_Stability_07");
		recordVideo("XP8_Stability_07");
		SoftAssert sa7 = new SoftAssert();
		clearRecentApps();
		for (int i = 1; i <= itr; i++) {
			if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
				launch_APP(Locators_DeviceStability.messaging);
				navigateTo_NewSMS();
				enter_Num_ToField(refNum);
				captureVideo_MMS_O();
				enterText_MessageField(data.get("typeMessage"));
				clickOn_Send();
				validate_SentMessage(i, sa7);
				delete_SMS();
			} else if (p_b_No.contains("-15.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				navigateTo_NewSMS_O();
				create_NewSMS_O(refNum, data.get("typeMessage"));
				captureVideo_MMS_O();
				clickOn_Send_O();
				validate_SentMessage_O(i, sa7);
				delete_SMS_O();
				customWait(8000);
			} else if (p_b_No.contains("-15.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				navigateTo_NewSMS_O();
				create_NewSMS_O(refNum, data.get("typeMessage"));
				captureVideo_MMS_O();
				clickOn_Send_O();
				validate_SentMessage_O(i, sa7);
				delete_SMS_O();
				customWait(8000);
			} else if (p_b_No.contains("-11.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				navigateTo_NewSMS_O();
				create_NewSMS_O(refNum, data.get("typeMessage"));
				captureVideo_MMS_O();
				clickOn_Send_O();
				validate_SentMessage_O(i, sa7);
				delete_SMS_O();
				customWait(8000);
			} else if (p_b_No.contains("-29.")) {
				launch_APP(Locators_DeviceStability.MessagePlus);
				navigateTo_NewSMS_O();
				create_NewSMS_O(refNum, data.get("typeMessage"));
				captureVideo_MMS_O();
				clickOn_Send_O();
				validate_SentMessage_O(i, sa7);
				delete_SMS_O();
				customWait(8000);
			} else {
				launch_APP(Locators_DeviceStability.messaging);
				navigateTo_NewSMS();
				enter_Num_ToField(refNum);
				captureVideo_MMS_O();
				enterText_MessageField(data.get("typeMessage"));
				clickOn_Send();
				validate_SentMessage(i, sa7);
				delete_SMS();
			}
		}
		sa7.assertAll();
	}
	
	@Test(priority = 8, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_08_Stability_Validate_MOVoiceCall_CSFB_from_Contacts(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_08============");
		startAdbLog("XP8_Stability_08");
		SoftAssert sa8 = new SoftAssert();
		clearRecentApps();
		String fN = startRIL_Log();

	value =  validate_RIL_Logs(fN, "RIL_REQUEST_IMS_REGISTRATION_STATE {1, 1} [SUB0]","Failure may also be because Operator is NOT IMS Registered(NOT VoLTE).");

		System.out.println(value);
		if (!value) {
			for (int i = 1; i <= itr; i++) {
				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				} else if (p_b_No.contains("-15.")) {
					performAction_O();
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				} else if (p_b_No.contains("-11.")) {
					performAction();
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				} else if (p_b_No.contains("-29.")) {
					performAction();
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				} else if (p_b_No.contains("-26.")) {
					performAction();
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				} else {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa8);
				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is Enabled");
		}
		sa8.assertAll();

	}

	@Test(priority = 9, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_09_Stability_Validate_MOVoiceCall_CSFB_from_CallHistory(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_09============");
		startAdbLog("XP8_Stability_09");
		SoftAssert sa9 = new SoftAssert();
		clearRecentApps();
		System.out.println(value);
		if (!value) {
			for (int i = 1; i <= itr; i++) {

				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
					performAction();
					launch_an_app("phone");
					// selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				} else if (p_b_No.contains("-15.")) {
					performAction_O();
					launch_an_app("phone");
					// selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				} else if (p_b_No.contains("-29.")) {
					performAction();
					launch_an_app("phone");
					// selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				} else if (p_b_No.contains("-11.")) {
					performAction();
					launch_an_app("phone");
					// selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				} else if (p_b_No.contains("-26.")) {
					performAction();
					launch_an_app("phone");
					// selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				} else {
					launch_an_app("phone");
					// selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa9);
				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is Enabled");
		}
		sa9.assertAll();

	}

	@Test(priority = 10, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_10_Stability_Validate_MO_IMSVoiceCall_from_Contacts(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_10============");
		startAdbLog("XP8_Stability_10");
		SoftAssert sa10 = new SoftAssert();
		clearRecentApps();
		System.out.println(value);
		if (value) {
			for (int i = 1; i <= itr; i++) {

				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog("called", i, "contacts", sa10);
				} else if (p_b_No.contains("-29.")) {
					performAction();
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa10);

				} else if (p_b_No.contains("-11.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa10);

				}

				else if (p_b_No.contains("-15.")) {
					performAction_O();
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa10);
				} else if (p_b_No.contains("-26.")) {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog_Orio("called", i, "contacts", sa10);
				} else {
					launch_an_app("contacts");
					searchContact("Test" + i);
					initiateCall();
					validateCallLog("called", i, "contacts", sa10);
				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is not Enabled");
		}
		sa10.assertAll();
	}

	@Test(priority = 11, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_11_Stability_Validate_MO_IMSVoiceCall_from_CallHistory(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_11============");
		startAdbLog("XP8_Stability_11");
		SoftAssert sa11 = new SoftAssert();
		clearRecentApps();
		System.out.println(value);
		if (value) {
			for (int i = 1; i <= itr; i++) {

				if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog("called", i, "contacts", sa11);
				} else if (p_b_No.contains("-15.")) {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa11);
				} else if (p_b_No.contains("-29.")) {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa11);
				} else if (p_b_No.contains("-26.")) {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa11);
				}

				else if (p_b_No.contains("-11.")) {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog_Orio("called", i, "contacts", sa11);
				} else {
					launch_an_app("phone");
					selectPage(Locators_DeviceStability.callHistry_notification);
					callHistory();
					validateCallLog("called", i, "contacts", sa11);
				}
			}
		} else {
			test.log(LogStatus.SKIP, "NT: IMS is not Enabled");
		}
		sa11.assertAll();
	}

	@Test(priority = 12, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_12_Stability_Validate_WiFi_ConnectDisconnect_SSID(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_12============");
		startAdbLog("XP8_Stability_12");
		recordVideo("XP8_Stability_12");
		SoftAssert sa12 = new SoftAssert();
		clearRecentApps();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			clickOn_BackBtn();
			scrollToTextContains("Wi");
			enable_Wifi();
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= itr; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa12);
				scrollToTextContains("Wi");
				selectWIFIOptn();
				disconnectSSIDifConnected();
			}
		} else if (p_b_No.contains("-29.")) {
			performAction();
			System.out.println("sprint");
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();

			clickOn_BackBtn();
			scrollToTextContains("Wi");
			enableFeature(Locators_DeviceStability.enabled, Locators_DeviceStability.disabled,
					Locators_DeviceStability.switch_Title);
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= itr; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa12);
				scrollToTextContains("Wi");
				selectWIFIOptn();
				disconnectSSIDifConnected();

			}
		} else if (p_b_No.contains("-11.")) {
			System.out.println("sprint");
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();

			clickOn_BackBtn();
			scrollToTextContains("Wi");
			enableFeature(Locators_DeviceStability.enabled, Locators_DeviceStability.disabled,
					Locators_DeviceStability.switch_Title);
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= itr; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa12);
				scrollToTextContains("Wi");
				selectWIFIOptn();
				selectSSIDwifi();
				disconnectSSIDifConnected();
			}
		} else if (p_b_No.contains("-26.")) {
			System.out.println("SL");
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();

			clickOn_BackBtn();
			scrollToTextContains("Wi");
			enableFeature(Locators_DeviceStability.enabled, Locators_DeviceStability.disabled,
					Locators_DeviceStability.switch_Title);
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= 1; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa12);
				scrollToTextContains("Wi");
				selectWIFIOptn();
				disconnectSSIDifConnected();
			}
		} else if (p_b_No.contains("-15.")) {
			System.out.println("vzw");
			performAction_O();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			clickOn_BackBtn();
			scrollToTextContains("Wi");
			enable_Wifi();
			minWait();
			checkWifiConnected();
			for (int j = 1; j <= itr; j++) {
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				selectSSIDwifi();
				enterPassword(pw);
				validateWifiConnect(j, sa12);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				selectWIFIOptn();
				selectSSIDwifi();
				disconnectSSIDifConnected();
			}
		}

		sa12.assertAll();

	}

	@Test(priority = 13, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_13_Stability_Validate_WiFi_Turn_ON_OFF(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_13============");
		startAdbLog("XP8_Stability_13");
		recordVideo("XP8_Stability_13");
		SoftAssert sa13 = new SoftAssert();
		clearRecentApps();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToTextContains("Wi");
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission();
				minWait();
				validate_And_BrowseIn_Chrome(1, sa13);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				wiFi_OFF();

				launch_an_app("browser");
				clearChromePermission();
				validate_And_BrowseIn_Chrome(1, sa13);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}
		} else if (p_b_No.contains("-15.")) {
			performAction_O();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToTextContains("Wi");
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission();
				minWait();
				validate_And_BrowseIn_Chrome(1, sa13);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				wiFi_OFF();

				launch_an_app("browser");
				clearChromePermission();
				validate_And_BrowseIn_Chrome(1, sa13);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}
		} else if (p_b_No.contains("-26.")) {
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToTextContains("Wi");
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission();
				minWait();
				validate_And_BrowseIn_Chrome(1, sa13);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				wiFi_OFF();

				launch_an_app("browser");
				clearChromePermission();
				validate_And_BrowseIn_Chrome(1, sa13);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}
		} else if (p_b_No.contains("-29.")) {
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToTextContains("Wi");
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission();
				minWait();
				validate_And_BrowseIn_Chrome(1, sa13);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				wiFi_OFF();

				launch_an_app("browser");
				clearChromePermission();
				validate_And_BrowseIn_Chrome(1, sa13);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}
		}

		else if (p_b_No.contains("-11.")) {
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			minWait();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToTextContains("Wi");
			enable_Wifi();
			selectSSIDwifi();
			enterPassword(pw);
			for (int j = 1; j <= itr; j++) {
				System.out.println("In Loop");
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				enable_Wifi();
				launch_an_app("browser");
				clearChromePermission();
				minWait();
				validate_And_BrowseIn_Chrome(1, sa13);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				wiFi_OFF();

				launch_an_app("browser");
				clearChromePermission();
				validate_And_BrowseIn_Chrome(1, sa13);
				customWait(3000);
				if (isElementExist(Locators_DeviceStability.alert_OKBtn)) {
					clickBtn(Locators_DeviceStability.alert_OKBtn);
					System.out.println("Im Clicking Ok 3rd ");
				}
			}
		}

		sa13.assertAll();
	}

	@Test(priority = 14, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_14_Stability_Validate_Browse_Visit_WebPage(Hashtable<String, String> data)
			throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_14============");
		startAdbLog("XP8_Stability_14");
		recordVideo("XP8_Stability_14");
		SoftAssert sa14 = new SoftAssert();
		clearRecentApps();

		if (p_b_No.contains("-10.") || p_b_No.contains("-00.")) {
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			clickOn_BackBtn();
			scrollToTextContains("Wi");
			disable_Wifi();
			launch_an_app("browser");

			clearChromePermission();
			for (int i = 1; i <= itr; i++) {
				enterUrl();
				validateUrlEntered(i, sa14);
				customWait(5000);
			}
		}

		else if (p_b_No.contains("-15.")) {
			performAction_O();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			clickOn_BackBtn();
			scrollToTextContains("Wi");
			disable_Wifi();
			launch_an_app("browser");

			clearChromePermission();
			for (int i = 1; i <= itr; i++) {
				enterUrl();
				validateUrlEntered(i, sa14);
				customWait(5000);
			}
		} else if (p_b_No.contains("-29.")) {
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			clickOn_BackBtn();
			scrollToTextContains("Wi");
			disable_Wifi();
			launch_an_app("browser");

			clearChromePermission();
			for (int i = 1; i <= itr; i++) {
				enterUrl();
				validateUrlEntered(i, sa14);
				customWait(5000);
			}
		} else if (p_b_No.contains("-26.")) {
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			clickOn_BackBtn();
			scrollToTextContains("Wi");
			disable_Wifi();
			launch_an_app("browser");

			clearChromePermission();
			for (int i = 1; i <= itr; i++) {
				enterUrl();
				validateUrlEntered(i, sa14);
				customWait(5000);
			}
		} else if (p_b_No.contains("-11.")) {
			performAction();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			disable_MobileData();
			clickOn_BackBtn();
			scrollToTextContains("Wi");
			disable_Wifi();
			launch_an_app("browser");

			clearChromePermission();
			for (int i = 1; i <= itr; i++) {
				enterUrl();
				validateUrlEntered(i, sa14);
				customWait(5000);
			}
		}
		sa14.assertAll();

	}
}
