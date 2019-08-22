package com.xp8.test;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Hashtable;
import java.util.Properties;

import org.openqa.selenium.WebElement;
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
import com.xp8.util.Locators_ContactTransfer;
import com.xp8.util.Locators_SonimCare;
import com.xp8.util.Locators_Warranty_Reg;
import com.xp8.util.XP8_ContactTransfer_Util;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_ContactTransfer_Test extends XP8_ContactTransfer_Util {

	public ExcelReader excel;
	Properties properties;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTestInterruptedException testexception;
	//public AndroidDriver<AndroidElement> aDriver;

	@BeforeSuite
	public void beforeSuite() 
	{
		extent = new ExtentReports("src/test/resources/extentreport/ContactTransferReport.html", true); //Provide Desired Report Directory Location and Name
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
		{	
			String screenshot_path=captureScreenshot(method.getName());
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
		Locators_ContactTransfer loc=new Locators_ContactTransfer(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}

 @Test(priority=1,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_01_Validate_ContactTransfer_HomeScreen(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_01============");
		startAdbLog("ContactTransfer_01");
		recordVideo("ContactTransfer_01");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		validate_Current_Screen(Locators_ContactTransfer.ContactTransferTitle,data.get("screenTitle"));
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=2,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_02_Validate_Default_TransferMode(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_02============");
		startAdbLog("ContactTransfer_02");
		recordVideo("ContactTransfer_02");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		validate_Default_TransferMode();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=3,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_03_Validate_About_ContactTransfer(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_03============");
		startAdbLog("ContactTransfer_03");
		recordVideo("ContactTransfer_03");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		selectMoreOptions();
		validateAboutText();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	
	@Test(priority=4,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_04_Validate_All_TransferMode_Present(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_04============");
		startAdbLog("ContactTransfer_04");
		recordVideo("ContactTransfer_04");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		validateTransferModesPresent();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

	@Test(priority=5,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_05_ImportContact_Via_MDB(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_05============");
		startAdbLog("ContactTransfer_05");
		recordVideo("ContactTransfer_05");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("mdb");
		selectContactfileToImport(7);
		validateAllContactImport();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=6,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_06_ImportContact_Via_VCF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_06============");
		startAdbLog("ContactTransfer_06");
		recordVideo("ContactTransfer_06");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("vcf");
		selectContactfileToImport(7);
		validateAllContactImport();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
		
	@Test(priority=7,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_07_Validate__ImportContact_Via_CSV(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_07============");
		startAdbLog("ContactTransfer_07");
		recordVideo("ContactTransfer_07");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("csv");
		selectContactfileToImport(45);
		validateNotificationBar();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=8,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_08_Validate_ImportContact_Notification(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_08============");
		startAdbLog("ContactTransfer_08");
		recordVideo("ContactTransfer_08");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("mdb");
		selectContactfileToImport(7);
		validateNotificationBar();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=9,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_09_Validate_ImportContact_Cancel(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_09============");
		startAdbLog("ContactTransfer_09");
		recordVideo("ContactTransfer_09");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("csv");
//		selectContactfileToImport();
		validateCancelImport();
//		validateNotificationBar();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	 
	
	@Test(priority=10,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_10_Disable_MBD_While_Impoting_MDBfile(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_10============");
		startAdbLog("ContactTransfer_10");
		recordVideo("ContactTransfer_10");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		DisableFile("mdb", Locators_ContactTransfer.ImportMBDOptn,1);
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=11,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_11_Disable_VCF_While_Impoting_VCFfile(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_11============");
		startAdbLog("ContactTransfer_11");
		recordVideo("ContactTransfer_11");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		DisableFile("vcf", Locators_ContactTransfer.ImportVCFOptn,2);
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=12,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_12_Disable_CSV_While_Impoting_CSVfile(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_12============");
		startAdbLog("ContactTransfer_12");
		recordVideo("ContactTransfer_12");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		DisableFile("csv", Locators_ContactTransfer.ImportCSVOptn,3);
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=13,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_13_Disable_BT_While_Impoting_BTfile(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_13============");
		startAdbLog("ContactTransfer_13");
		recordVideo("ContactTransfer_13");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		DisableFile("Bluetooth", Locators_ContactTransfer.ImportBTOptn,0);
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	

	@Test(priority=14,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_14_Import_Few_Number_After_ImportingContact(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_14============");
		startAdbLog("ContactTransfer_14");
		recordVideo("ContactTransfer_14");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("vcf");
//	    enterSearchField();
	    validateFewImportContact();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}

/*
	@Test(priority=15,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_15_Search_Particular_Number_while_Importing(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_15============");
		startAdbLog("ContactTransfer_15");
		recordVideo("ContactTransfer_15");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("vcf");
	    enterSearchField();
	    Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input text \"Automation2\"");
		validateSearch();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}*/
	
	
	@Test(priority=16,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_16_Search_Import_UFMI_Format(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_16============");
		startAdbLog("ContactTransfer_16");
		recordVideo("ContactTransfer_16");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("csv");
	    enterSearchField();
	    validateUFMIFormat();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=17,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_17_Validate_Title_After_Selecting_ViaBluetooth(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_17============");
		startAdbLog("ContactTransfer_17");
		recordVideo("ContactTransfer_17");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("Bluetooth");
		validateBrowse_Title(Locators_ContactTransfer.Title_BTOptn,data.get("BrowseTitle"));
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=18,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_18_Validate_Title_After_Selecting_ViaMDB(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_18============");
		startAdbLog("ContactTransfer_18");
		recordVideo("ContactTransfer_18");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("mdb");
		validateBrowse_Title(Locators_ContactTransfer.Title_MBDOptn,data.get("BrowseTitle"));
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=19,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_19_Validate_Title_After_Selecting_ViaVCF(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_19============");
		startAdbLog("ContactTransfer_19");
		recordVideo("ContactTransfer_19");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("vcf");
		validateBrowse_Title(Locators_ContactTransfer.Title_VCFOptn,data.get("BrowseTitle"));
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=20,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_20_Validate_Title_After_Selecting_ViaCSV(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_20============");
		startAdbLog("ContactTransfer_20");
		recordVideo("ContactTransfer_20");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("csv");
		validateBrowse_Title(Locators_ContactTransfer.Title_CSVOptn, data.get("BrowseTitle"));
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	
	@Test(priority=21,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_21_Validate_ReOpen_ContactTransferApp_NotificationBar(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_21============");
		startAdbLog("ContactTransfer_21");
		recordVideo("ContactTransfer_21");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		select_Transfer_Mode("csv");
		selectContactfileToImport(45);
		validateImportSummaryPageAfterReopen();
		customWait(4000);
//		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
//		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
	
	@Test(priority=22,dataProvider="ContactTransfer",dataProviderClass=DataProviders.class)
	public void XP8_CT_022_Validate_Default_Mode_MoreOptions(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException 
	{
		APP_LOGS.info("===========ContactTransfer_22============");
		startAdbLog("ContactTransfer_22");
		recordVideo("ContactTransfer_22");
		clearRecentApps();
		launch_application(Locators_ContactTransfer.sonim_scout_AppLauncher);
		validateDeafaultModeMoreOptions();
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		logout();
		customWait(2000);
		test.log(LogStatus.PASS, "Test case status is Passed");
	}
}
