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

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
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
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_CallSetting;
import com.xp8.util.XP8_Call_Settings_Util;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class cal extends XP8_Call_Settings_Util {
	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;


	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Call_Settings_TestReport.html", true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		fetch_Devices_Details();	
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
		Locators_CallSetting loc=new Locators_CallSetting(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	//============================================================Test Case=======================================================================================
	@Test(priority=1,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_01_Call_Settings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{  
		SoftAssert sa=new SoftAssert();
		//Navigate to call settings
		APP_LOGS.info("===========XP8_TC_01_Call_Settings============");
		clearRecentApps();
		launch_an_app("phone");
		navigate_to_call_settings();
		clickBackButton(4);
		//Validating call settings are navigated
		validate_navigatetocallsettings(sa);
		sa.assertAll();
	}

	@Test(priority=2,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_02_Call_Settings_ValidateSettings_and_Displayoption(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		//Verify the behaviour of Call settings dispaly options
		SoftAssert sa=new SoftAssert();
		
		APP_LOGS.info("===========XP8_TC_02_Call_Settings_ValidateSettings_and_Displayoption============");
		launch_an_app("contacts");
		//Delete the contact in contact app
		deleteIfContactsPresent(sa);
		minWait();
		launch_an_app("contacts");
		minWait();
		//Set default saving account in contact app
		setDefaultSavingAccount();
		launch_an_app("phone");
		//Add contact from contact pages
		AddContactFromContactsPage(data.get("name"),data.get("lastname"), data.get("phone"));
		//Validate the saved contact
		validateSavedContact(data.get("name"), "from Contacts page",sa);
		add_Contact(data.get("Newname"),data.get("Newlastname"), data.get("Newphone"));
		validateSavedContact2(data.get("Newname"), "from Contacts page",sa);
		add_Contact(data.get("Thirdname"),data.get("Thirdlastname"), data.get("Thirdphone"));
		validateSavedContact2(data.get("Thirdname"), "from Contacts page",sa);
		//Validate settings and display options in call screening
		
		validateSettingsAndDisplayOptions(sa);
		sa.assertAll();

	}


	@Test(priority=3,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_03_Call_Settings_ValidateSounds_and_VibrationSuboption(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("=========== XP8_TC_03_Call_Settings_ValidateSounds_and_VibrationSuboption============");
		launch_an_app("phone");
		//Navigate to sounds and vibration option in call settings
		navigateToSettingsAndElement(Locators_CallSetting.soundsAndVibration);
		minWait();
		//Validate phone ringtone
		validatePhoneRingtone(sa);
		minWait();
		//Validate sounds and vibration SubOptions
		validateSoundsAndVibrationSubOptions(sa);
		minWait();
		clickBackButton(3);
		sa.assertAll();
	}
	@Test(priority=4,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void XP8_TC_04_Call_Settings_Validate_Quickresponses(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa=new SoftAssert();
		APP_LOGS.info("===========XP8_TC_04_Call_Settings_Validate_Quickresponses===========");
		//("XP8_PhoneDialer_25");
		launch_an_app("phone");
		//Navigate to Quickresponse option in call settings
		navigateToSettingsAndElement(Locators_CallSetting.quickResponseOpt);
		minWait();
		//Default quickresponses are restored
		Restore_default_QuickResponses();
		//Validate quick response list
		getAndValidateQuickResponsesList(sa);
		minWait();
		//Edit and validate quick response list
		editAndValidateQuickResponse(data.get("textMessage"),sa);
		minWait();
		clickBackButton(4);
		sa.assertAll();
	}
	@Test(priority=5,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_05_Call_Settings_Validate_Speeddialsettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{

		APP_LOGS.info("===========XP8_Call_Settings_05===========");
		SoftAssert sa=new SoftAssert();
		launch_an_app("phone");
		//Navigate to speed dial setting option in call setting
		navigateToSettingsAndElement(Locators_CallSetting.speedDialSettingsOpt);
		//Validating presence of voice mail in speed dial setting
		presenceOfVoicemailInSpeedDailSettings(sa);
		//Add contact in speed dial setting
		addContactInSpeedDailSettings(data.get("phone"),sa);
		//Replace contact in speed dial setting
		replaceContactInSpeedDailSettings(data.get("name"),sa);
		//Delete contact in speed dial setting
		deleteContactInSpeedDailSettings(sa);
		addContactInSpeedDailSettings(data.get("phone"),sa);
		Runtime.getRuntime().exec("adb -s "+ p_Id +" shell input keyevent 4");
		//Validating speed dial setting no is working properly
		launch_an_app("phone");
		minWait();
		clickBtn(Locators_CallSetting.callericon);
		minWait();
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_CallSetting.twoindialpad).release().perform();
		minWait();
		clickBtn(Locators_CallSetting.endcallicon);
		minWait();clickBackButton(3);
		sa.assertAll();


	}
	@Test(priority=6,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_06_Call_Settings_Call_Screening_black_white_list(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa = new SoftAssert();{
			APP_LOGS.info("===========XP8_Call_Settings_06===========");
			//("XP8_PhoneDialer_26");
			launch_an_app("phone");
			//Navigate to call screening in call settings
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			/*deleting_no_in_blacklist();
			minWait();*/
			//Verify adding numbers to Black list in CallScreening
			BlacklistincallScreening(data.get("phoneno1"));
			minWait();
			//Verify unblockingnumbers  from Black list(CallScreening)
			UnblockincallScreening();
			minWait();
			clickBackButton(4);
			//Adding and validating new number with + prefix to block list
			validate_blocknumber_with_values(data.get("phoneno1"),sa);
			minWait();
			UnblockincallScreening();
			minWait();
			//adding new number with +91 prefix to block list.
			validate_blocknumber_with_values(data.get("phoneno2"),sa);	
			minWait();
			UnblockincallScreening();
			minWait();
			//adding international no to blacklist
			validate_blocknumber_with_values(data.get("phoneno11"),sa);	
			minWait();
			UnblockincallScreening();
			minWait();
		    // adding new number with 0 prefix to block list
			validate_blocknumber_with_values(data.get("phoneno3"),sa);
			minWait();
			UnblockincallScreening();
			minWait();
			//Deleting unblocking numbers
			clickBackButton(1);
			sa.assertAll();
		}
	}
	@Test(priority=-7,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_07_Call_Settings_Call_Screening_black_white_list_2(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa = new SoftAssert();{
			APP_LOGS.info("===========XP8_Call_Settings_06===========");
			//("XP8_PhoneDialer_26");
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();	
			//Adding maximum numbers to Black list
			for(int k=1;k<=10; k++){
				addingno_in_blacklist_callscreening(data.get("phoneno"+k));
				minWait();
			}
			minWait();
			//Validate scrolling through blocked numbers
			for(int K=1;K<=12; K++){
				validatescrollingno_in_blacklist(data.get("phoneno"+K),sa);
				minWait();
			}
			//Deleting all no in blacklist
			/*for(int j=1; j<=10; j++){
				deleting_no_in_blacklist("phoneno"+j);
			}
			minWait();
			*/
			//Verify Cancel  options while adding number to Black list

			validate_cancel_option_blacklist(data.get("phoneno1"),sa);
			minWait();

			//Verify Cancel  options while adding number to white list
			validate_cancel_option_whitelist(data.get("phoneno1"),sa);
			minWait();
			// number field is displayed on selecting "ADD A NUMBER" option under Black List
			validate_addno_option_under_callsettings_blacklist(data.get("phoneno1"),sa);
			minWait();
			// number field is displayed on selecting "ADD A NUMBER" option under White List
			validate_addno_option_under_callsettings_Whitelist(data.get("phoneno1"),sa);
			minWait();
			//Verify removing numbers from white list
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			//Deleting no in whitelist
			for(int j=1; j<=10; j++){
				deleting_no_in_whitelist("phoneno"+j);
			}
			minWait();
			clickBackButton(4);
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			//Verify adding maximum numbers to White list
			for(int k=1;k<=10; k++){
				addContactNumWhitelist(data.get("phoneno"+k));
				minWait();
			}
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			for(int j=1; j<=10; j++){
				deleting_no_in_whitelist("phoneno"+j);
			}
			minWait();
			//Verify scrolling through white list numbers

			for(int K=1;K<=12; K++){
				validatescrollingno_in_whitelist(data.get("phoneno"+K),sa);
				minWait();
			}
		}
		sa.assertAll();

	}
	




	


	@Test(priority=8,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_08_Call_Settings_Call_Screening(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException
	{
		SoftAssert sa = new SoftAssert();{
			APP_LOGS.info("===========XP8_Call_Settings_07===========");
			//("XP8_PhoneDialer_26");
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			//the behavior  by pressing Back key once while adding number to the blocked list.
			validate_pressing_backkey_once(data.get("phoneno1"),sa);
			minWait();
			////the behavior  by pressing Back key twice while adding number to the blocked list.
			validate_pressing_backkey_twice(data.get("phoneno1"),sa);
			minWait();
			//the behavior  by pressing Home key (Short press) while adding number to the blocked list.
			validate_pressing_Shortpress_HomeKey(data.get("phoneno1"),sa);
			minWait();
			//the behavior  by pressing Home key (LONG PRESS)while adding number to the blocked list.
			validate_pressing_Longpress_HomeKey(data.get("phoneno1"),sa);
			minWait();
			//the behavior  by pressing Recent apps key while adding number to the blocked list
			validate_pressing_Recentsapp_Key(data.get("phoneno1"),sa);
			minWait();
			//"Screening incoming call Setting" option is highlighted (not greyed out)
			validate_screening_incoming_call_highlighted(sa);
			minWait();
			// Call Screening option is present under call settings
			validate_Callscreening_under_Callsettings(sa);
			minWait();

		}
		sa.assertAll();

	}

	@Test(priority=9,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_09_Call_Settings_block_contactapp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	// blocking  numbers from Contact app

	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_09===========");
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			clickBackButton(4);
			minWait();
			delete_no_blacklist();
			minWait();
			launch_an_app("contacts");
			minWait();
			//Block no in contact app
			blockno_in_contactapp(refNum);
			minWait();
			//Validating Blockingno from contacts app is reflected in Black lsit under Call screening
			validate_blockno_in_blacklist(sa);
		}
		sa.assertAll();
	}

	@Test(priority=10,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_10_Call_Settings_Unblock_contactapp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	// unblocking  numbers from Contact app

	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_10===========");
			clearRecentApps();
			launch_an_app("contacts");
			minWait();
			//Unblock in contact app
			unblockno_in_contactapp(refNum);
			minWait();
			clickBackButton(4);
			launch_an_app("phone");
			minWait();
			//Validating UnBlockingno from contacts app is reflected in Black lsit under Call screening
			validate_Unblockno_in_blacklist(sa);
			minWait();
		}
		sa.assertAll();
	}
	@Test(priority=11,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_11_Call_Settings_block_Unblock_Calldetails(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	// Verify blocking and unblocking numbers from Call details
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_10===========");
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			//Blocking no in call details
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			delete_no_blacklist();
			minWait();
			clickBackButton(3);
			minWait();
			clearcallhistory(sa);
			minWait();
			make_Call_from_PrmyDev();
			customWait(3000);
			endCall_PrmyDevice();
			minWait();
			blockno_in_Calldetails(sa);
			minWait();
			//Validating Blockingno from contacts app is reflected in Black lsit under Call screening
			validate_blockno_in_blacklist(sa);
			minWait();
			//Unblock in calldetails
			Unblockno_in_calldetails();
			minWait();
			//Validating UnBlockingno from contacts app is reflected in Black lsit under Call screening
			validate_Unblockno_in_blacklist(sa);
			minWait();
			//Validate the no is blocked or unblocked from callhistory
			clearcallhistory(sa);
			minWait();
			make_Call_from_PrmyDev();
			customWait(3000);
			endCall_PrmyDevice();
			clickBackButton(2);
			minWait();
			launch_an_app("phone");
			validateBlockAndUnblockNumber(data.get("status"),("refNum"),sa);
			minWait(); 
			//Validate cancel option in call details block no
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dialerfirstno);
			validate_canceloptionin_calldetailsblockno(sa);
			minWait();
			clickBackButton(4);
			//Validate cancel options in call details Unblock no
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			clickBtn(Locators_CallSetting.Callhistorytab);
			minWait();
			clickBtn(Locators_CallSetting.dialerfirstno);
			validate_canceloptionin_calldetailsUnblockno(sa);
			minWait();
		}
		sa.assertAll();


	}
	@Test(priority=12,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_12_Call_Settings_Call_Screening_Screeningincomingcallsetting_option1(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//calls  is received from blacklist no only on disabling screening incoming call setting option
	//Block black list is selected in screening incoming call setting option
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_08===========");
			//
			launch_an_app("phone");
			minWait();
			//Disabling Screening incoming call
			disabling_Screeningincomingcalls_callscreening();
			minWait();
			//Adding no in blacklist
			addingno_in_blacklist_callscreening(refNum);
			minWait();
			clickBackButton(4);
			minWait();
			//Validating call is received in primary device
			clearcallhistory(sa);
			minWait();
     		clickBackButton(4);
			make_Call_from_RefDev();
			customWait(6000);
			recieve_Call_PrimaryDev_O();
			customWait(6000);
			endCall_RefDevice();
			customWait(3000);
			validate_calllog_callreceived(sa);
			minWait();
}

		sa.assertAll();
	}

	@Test(priority=13,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_13_Call_Settings_Call_Screening_Screeningincomingcallsettingoption_blockblacklist(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify calls and messages are not received from black list on selecting 'Block black list"

	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_08===========");
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			//Enable and selecting block black list in Screening incoming call settings
			enabling_Screeningincomingcalls_callscreening("phoneno");
			minWait();
			//Deleting no in whitelist
			deleting_no_whitelist();
			minWait();
			//Add contact no in whitelist
			addContactNumWhitelist(refNum);
			minWait();
			//Validating calls and messages are not received from black list on selecting 'Block black list"
			clickBackButton(4);
			clearcallhistory(sa);
			minWait();
			make_Call_from_RefDev();
			customWait(6000);
			endCall_RefDevice();
			customWait(3000);
			delete_SMS(sa);
			minWait();
			sendSMS_fromRefDevice("Hi");
			minWait();
			validate_calllog_callnotreceived(sa);
			minWait();
			validate_NotSentMessage(sa);
			minWait();
		}
		sa.assertAll();
	}
	@Test(priority=14,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_14_Call_Settings_Call_Screening_Screeningincomingcallsettingoption_Allowonlycontacts(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify calls and messages are  received from contacts on selecting "Allow only contacts"
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_08===========");
			launch_an_app("phone");
			minWait();
			//Enable and selecting allow only contacts in Screening incoming call settings
			enabling_Screeningincomingcalls_allowonlycontacts("phoneno");
			minWait();
			//Deleting no in whitelist
			deleting_no_whitelist();
			minWait();
			clickBackButton(1);
			minWait();
			//Adding contact no in whitelist
			addContactNumWhitelist(refNum);
			minWait();
			clickBackButton(4);
			clearcallhistory(sa);
			minWait();
			//Adding contact for call screening
			add_contact_for_callscreening(data.get("name"),data.get("lastname"), refNum,sa);
			minWait();
			make_Call_from_RefDev();
			customWait(6000);
			recieve_Call_PrimaryDev_O();
			customWait(6000);
			endCall_RefDevice();
			customWait(3000);
			//Validating calls and messages are  received from contacts on selecting "Allow only contacts"
			validate_calllog_contactcallreceived(sa);
			minWait();

		}
		sa.assertAll();
	}

	@Test(priority=15,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_15_Call_Settings_Call_Screening_Screeningincomingcallsettingoption_Allowwhitelist(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify calls are  received from contacts on selecting "Allow white list"
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_08===========");
			launch_an_app("phone");
			minWait();
			//Enable and selecting allow white list in Screening incoming call settings
			enabling_Screeningincomingcalls_allowwhitelist();
			minWait();
			//Add contact for call screening
			clearcallhistory(sa);
			minWait();
			add_contact_for_callscreening(data.get("name"),data.get("lastname"), refNum,sa);
			minWait();
			make_Call_from_RefDev();
			customWait(6000);
			recieve_Call_PrimaryDev_O();
			customWait(6000);
			endCall_RefDevice();
			customWait(10000);
			//Validating calls are  received from contacts on selecting "Allow white list"
			validate_calllog_contactcallreceived(sa);
			minWait();

		}
		sa.assertAll();
	}

	@Test(priority=16,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_16_Call_Settings_Call_Screening_Screeningoutgoingcalls(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify enabling/disabling Screening outgoing calls
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_08===========");
			clearRecentApps();
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			//Validating  on enabling Screening outgoing calls
			validation_Screeningoutgoingcall_enabled(sa);
			minWait();
			clickBackButton(4);
			minWait();
			clearcallhistory(sa);
			minWait();
			make_Call_from_PrmyDev();
			customWait(10000);
			endCall_PrmyDevice();
			minWait();
			//validating receiving calls from contact saved no
			validate_calllog_contactcallreceived(sa);
			minWait();
			//Validating screening outgoing call disabled
			launch_an_app("phone");
			navigateToSettingsAndElement(Locators_CallSetting.Callscreening);
			minWait();
			validation_Screeningoutgoingcall_disabled(sa);
			minWait();
		}
	}

	@Test(priority=17,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_17_Call_Settings_Call_Screening_contactsScreeningoutgoingcalls(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//Verify enabling/disabling Screening outgoing calls
	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_08===========");
			clearRecentApps();

			//Verify initiating calls to saved contacts on enabling Screening outgoing calls
			validation_initiatingcall_Screeningoutgoingcall_enabled(data.get("name"),data.get("lastname"), refNum,sa);
			minWait();
			make_Call_from_PrmyDev();
			customWait(6000);
			endCall_PrmyDevice();
			minWait();
			validate_calllog_contactcallreceived(sa);
			minWait();


		}
		sa.assertAll();
	}
	@Test(priority=18,dataProvider="XP8_Call_Settings", dataProviderClass=DataProviders.class)
	public void  XP8_TC_18_validate_Editedquickresponse_displayproperly_duringcallscreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException

	//To verify user edited quick response should display properly on the screen while sending during a call screen

	{
		SoftAssert sa = new SoftAssert();
		{
			APP_LOGS.info("===========XP8_Call_Settings_08===========");
			clearRecentApps();
			launch_an_app("phone");
			minWait();
			delete_SMS(sa);
			navigateToSettingsAndElement(Locators_CallSetting.quickResponseOpt);
			minWait();
			//Restore default quickresponse
			Restore_default_QuickResponses();
			minWait();
			//Edit and validate quick response
			editAndValidateQuickResponse(data.get("textMessage"),sa);
			minWait();
			//editing quick responses are display properly in call screen
			Editedquickresponse_displayproperly_duringcallscreen();
			//Validating editing quick responses are display properly in call screen
			validate_Editedquickresponse_displayproperly_duringcallscreen(sa);
			minWait();
			clickBackButton(4);

		}
		sa.assertAll();
	}
}