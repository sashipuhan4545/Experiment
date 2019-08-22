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
import com.xp8.util.Locators_Safeguard;
//import com.xp8.util.Locators_SonimCare;
import com.xp8.util.XP8_Safeguard_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Safeguard_Test extends XP8_Safeguard_Util{

	
	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/SafeguardTestReprt.html", true); //Provide Desired Report Directory Location and Name
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
		Locators_Safeguard loc=new Locators_Safeguard(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}
	

	@Test(priority=1,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_001_Presence_Of_SG_In_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_01============");
		startAdbLog("Safegaurd_01");
		recordVideo("Safegaurd_01");
		clearRecentApps();
		launchScoutApp();
		sf_app_Presence_Validation();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
    }
	
	@Test(priority=2,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_002_Default_State_SG_In_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_02============");
		startAdbLog("Safegaurd_02");
		recordVideo("Safegaurd_02");
//		clearRecentApps();
		launchScoutApp();
		sf_app_Verify_Defualt_state();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	@Test(priority=3,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_003_Verify_Ver_Help_Default_State_SG_Scout(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_03============");
		startAdbLog("Safegaurd_03");
		recordVideo("Safegaurd_03");
//		clearRecentApps();
		launchScoutApp();
		verify_Version_Help_In_Default_State();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}

	@Test(priority=4,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_004_Navigate_To_Help_Fetch_Pin(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_04============");
		startAdbLog("Safegaurd_04");
		recordVideo("Safegaurd_04");
//		clearRecentApps();
		launchScoutApp();
		navigate_To_Help_Fetch_Pin_Check_Version();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
		
	@Test(priority=5,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_005_verify_Default_States_Of_All_Options_SG(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_05============");
		startAdbLog("Safegaurd_05");
		recordVideo("Safegaurd_05");
//		clearRecentApps();
		launchScoutApp();
		verify_Default_States_Of_All_Options_SG();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}

/*@Test(priority=6,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_006_verify_Default_State_Of_All_Apps_In_App_Option(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_05============");

		startAdbLog("Safegaurd_05");
		recordVideo("Safegaurd_05");
//		clearRecentApps();
//		launchScoutApp();
//		verify_Default_States_Of_All_Options_SG();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
*/
	@Test(priority=7,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_007_verify_Default_State_Of_All_Apps_In_App_Option(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_07============");
//		startAdbLog("Safegaurd_07");
//		recordVideo("Safegaurd_07");
//		clearRecentApps();
		launchScoutApp();
		select_All_Apps_And_Check();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}

	
	@Test(priority=8,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_008_verify_Unselect_All_Apps_And_Check(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_08============");
//		startAdbLog("Safegaurd_08");
//		recordVideo("Safegaurd_08");
//		clearRecentApps();
//		launchScoutApp();
		launchScoutApp();
		Unselect_All_Apps_And_Check();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	@Test(priority=9,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_009_select_few_And_Check(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_09============");
//		startAdbLog("Safegaurd_09");
//		recordVideo("Safegaurd_09");
//		clearRecentApps();
		launchScoutApp();
		select_few_And_Check();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	

	@Test(priority=10,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_010_emergency_Call_Check(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_10============");
//		startAdbLog("Safegaurd_10");
//		recordVideo("Safegaurd_10");
//		clearRecentApps();
//		launchScoutApp();
		emergency_Call();
		launchScoutApp();
		pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Un_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
@Test(priority=11,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_011_select_All_In_Feature_Check(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_11============");
//		startAdbLog("Safegaurd_11");
//		recordVideo("Safegaurd_11");
//		clearRecentApps();
		launchScoutApp();
		check_Features_Tab();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	@Test(priority=12,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_012_Unselect_All_Features_And_Check(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_12============");
//		startAdbLog("Safegaurd_12");
//		recordVideo("Safegaurd_12");
//		clearRecentApps();
		launchScoutApp();
		Unselect_All_Features_And_Check();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}

	
	@Test(priority=13,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_013_Verify_application_Pin_TimeOut(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_13============");
//		startAdbLog("Safegaurd_13");
//		recordVideo("Safegaurd_13");
//		clearRecentApps();
		launchScoutApp();
		special_Case_Select();
		application_Pin_30Sec_TimeOut();
		customWait(4000);
//	    Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	
	@Test(priority=14,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_014_Verify_App_Pin_Time_Out_One_Min(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_14============");
//		startAdbLog("Safegaurd_14");
//		recordVideo("Safegaurd_14");
//		clearRecentApps();
		launchScoutApp();
		pin_Time_Out_Set_To_1_Min();
		customWait(4000);
//	    Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	

	@Test(priority=15,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_015_min_Code_Verification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_15============");
//		startAdbLog("Safegaurd_15");
//		recordVideo("Safegaurd_15");
//		clearRecentApps();
		launchScoutApp();
		min_Code_Verification();
		customWait(4000);
//	    Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
		
	@Test(priority=16,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_016_max_Limit_Code_Verification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_16============");
//		startAdbLog("Safegaurd_16");
//		recordVideo("Safegaurd_16");
//		clearRecentApps();
		launchScoutApp();
		max_Limit();
		customWait(4000);
//	    Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	
	@Test(priority=17,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_017_Change_Pin_Verification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_17============");
//		startAdbLog("Safegaurd_17");
//		recordVideo("Safegaurd_17");
		clearRecentApps();
		launchScoutApp();
		change_Pin( data.get("NewPin1"),data.get("OldPin1"));
		customWait(4000);
		changePinAfter();
		change_Pin( data.get("NewPin2"),data.get("OldPin2"));
//	    Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	@Test(priority=18,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_018_Check_Invalid_Pin_Verification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_18============");
//		startAdbLog("Safegaurd_18");
//		recordVideo("Safegaurd_18");
		clearRecentApps();
		launchScoutApp();
	    Check_Invalid_Pin();
		customWait(4000);
//	    Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
	
	@Test(priority=19,dataProvider="SafeguardTest", dataProviderClass=DataProviders.class)
	public void Safeguard_019_Pin_Acccepts_Only_Num_Verification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========Safegaurd_19============");
//		startAdbLog("Safegaurd_19");
//		recordVideo("Safegaurd_19");
		clearRecentApps();
		launchScoutApp();
		Pin_Acccepts_Only_Num();
		customWait(4000);
//	    Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		customWait(2000);
		test.log(LogStatus.PASS, "TestCase Passed");
	}
}
