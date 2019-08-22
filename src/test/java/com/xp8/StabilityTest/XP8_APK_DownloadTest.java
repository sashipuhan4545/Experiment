package com.xp8.StabilityTest;

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
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.ATTUtil.Locators_Stability;
import com.xp8.ATTUtil.Stability_Browser_Util;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.Locators_XP8_Sanity;
import com.xp8.util.XP8_Stability_Util_orio;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_APK_DownloadTest  extends  XP8_Stability_Util_orio{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =1;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		fetch_Devices_Details();	
		String opName=fetchOperatorName();
		String getTime=getCurrentTime();
		System.out.println(getTime);
		String getDate =getCurrentDate();
		System.out.println(opName);
		
		extent = new ExtentReports("src/test/resources/extentreport/XP8_"+opName+"_APK_Download_Stability_Orio_TestReport.html",true); //Provide Desired Report Directory Location and Name
		extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
		extent.addSystemInfo("Appium", "1.2.7").addSystemInfo("Environment", "TEST");
		
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
		clearRecentApps();
	}


	@BeforeTest
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException{
		properties=loadDriverAndProperties();
		Locators_DeviceStability loc=new Locators_DeviceStability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}


/*	@Test(priority=1,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_001_Stability_Download_APK_Amazon(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_01_Browser============");
		SoftAssert sa1 = new SoftAssert();
		clearRecentApps();
		launch_an_app("settings");
		clickOn_Networks_and_Internet();
		scrollToText("Mobile network");
		ON_Switch("data");
		clickBtn(Locators_DeviceStability.OK);
		launch_an_app("settings");
		clickOn_Networks_and_Internet();
		scrollToTextContains("Wi");
		ON_Switch("Off");
		checkWifiConnected1();

		launch_an_app("settings");
		remove_GoogleAcccount_Orio();
		navigateTo_AddGoogleAccount_Orio();
		add_GoogleAccount(data.get("emailId"), data.get("password"));	
		test.log(LogStatus.PASS, "Preconditions are Set");
		
		for(int j=1; j<=1; j++) {

		
			for(int i=1; i<=itr;i++) {	  
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa1);
			
			}
		}
		sa1.assertAll();
	}
	

		@Test(priority=2,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_002_Stability_Download_APK_Ebay(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_02_Browser============");
		SoftAssert sa2 = new SoftAssert();
		for(int j=2; j<=2; j++) {		
			for(int i=1; i<=itr;i++) {	  
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa2);
			}
		}
		sa2.assertAll();
	}
	

	@Test(priority=3,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_003_Stability_Download_APK_Walmart(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_03_Browser============");
		SoftAssert sa3 = new SoftAssert();
		for(int j=3; j<=3; j++) {
		for(int i=1; i<=itr;i++) {	  
				CheckInstalled_App("BestPrice");
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App("BestPrice",i,sa3);
			}
		}
		sa3.assertAll();
	}
	

	@Test(priority=4,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_004_Stability_Download_APK_Bestbuy(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_04_Browser============");
		SoftAssert sa4 = new SoftAssert();
		for(int j=4; j<=4; j++) {

		for(int i=1; i<=itr;i++) {	  
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa4);
			}	
		}
		sa4.assertAll();
	}
	

	@Test(priority=5,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_005_Stability_Download_APK_OfficeDepot(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("==========XP8_Stability_03_Browser============");
		SoftAssert sa5 = new SoftAssert();
		for(int j=5; j<=5; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa5);
			}
			minWait();		
		}
		sa5.assertAll();
	}
	

	@Test(priority=6,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_006_Stability_Download_APK_Staples(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_06_Browser============");
		SoftAssert sa6 = new SoftAssert();
		for(int j=6; j<=6; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App("Tech Support Staples");
				validate_Installed_App(data.get("appName"+j),i,sa6);

			}	
		}
		sa6.assertAll();
	}
	

	@Test(priority=7,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_007_Stability_Download_APK_CraigslistApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_07_Browser============");
		SoftAssert sa7 = new SoftAssert();
		for(int j=7; j<=7; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa7);
			}
		}
		sa7.assertAll();
	}


	@Test(priority=8,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_008_Stability_Download_APK_KohlsApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_08_Browser============");
		SoftAssert sa8 = new SoftAssert();
		for(int j=8; j<=8; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa8);

			}
		}
		sa8.assertAll();
	}


	@Test(priority=9,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_009_Stability_Download_APK_MacysApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_09_Browser============");

		SoftAssert sa9 = new SoftAssert();
		for(int j=9; j<=9; j++) {
		for(int i=1; i<=itr;i++) {	  			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App("Macys");
				validate_Installed_App(data.get("appName"+j),i,sa9);

			}
		}
		sa9.assertAll();
	}

	@Test(priority=10,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_010_Stability_Download_APK_DealstoBuyApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_10_Browser============");
		SoftAssert sa10 = new SoftAssert();
		for(int j=10; j<=10; j++) {
		for(int i=1; i<=itr;i++) {	  
			

				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa10);
			}	
		}
		sa10.assertAll();
	}

	@Test(priority=11,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_011_Stability_Download_APK_BankofAmerica(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_11_Browser============");
		SoftAssert sa11 = new SoftAssert();
		for(int j=11; j<=11; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa11);

			}	
		}
		sa11.assertAll();
	}

	@Test(priority=12,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_012_Stability_Download_APK_ChaseBankApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_12_Browser============");
		SoftAssert sa12 = new SoftAssert();
		for(int j=12; j<=12; j++) {
		for(int i=1; i<=itr;i++) {	  
			
			CheckInstalled_App(data.get("appName"+j));
			customWait(4000);
			launch_APP(Locators_DeviceStability.PlayStore);
			customWait(4000);
			install_App(data.get("appName"+j));
			validate_Installed_App(data.get("appName"+j),i,sa12);

			}	
		}
		sa12.assertAll();
	}

	@Test(priority=13,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_013_Stability_Download_APK_CitiBankApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_13_Browser============");
		SoftAssert sa13 = new SoftAssert();
		for(int j=13; j<=13; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa13);

			}	
		}
		sa13.assertAll();
	}

	@Test(priority=14,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_014_Stability_Download_APK_wellsfargoApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_14_Browser============");
		SoftAssert sa14 = new SoftAssert();
		for(int j=14; j<=14; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa14);

			}	
		}
		sa14.assertAll();
	}

	@Test(priority=15,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_015_Stability_Download_APK_YahooApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_15_Browser============");
		SoftAssert sa15 = new SoftAssert();
		for(int j=15; j<=15; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa15);

			}	
		}
		sa15.assertAll();
	}


	@Test(priority=16,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_016_Stability_Download_APK_BingoApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_16_Browser============");
		SoftAssert sa16 = new SoftAssert();
		for(int j=16; j<=16; j++) {
		for(int i=1; i<=itr;i++) {	  	

				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa16);

			}
		}
		sa16.assertAll();
	}

	@Test(priority=17,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_017_Stability_Download_APK_KFCApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_17_Browser============");
		SoftAssert sa17 = new SoftAssert();
		for(int j=17; j<=17; j++) {
		for(int i=1; i<=itr;i++) {	  
	
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa17);

			}		
		}
		sa17.assertAll();
	}

	@Test(priority=18,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_018_Stability_Download_APK_McDonaldsApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_18_Browser============");
		SoftAssert sa18 = new SoftAssert();
		for(int j=18; j<=18; j++) {
		for(int i=1; i<=itr;i++) {	  
	
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App("McDonald's",i,sa18);

			}	
		}
		sa18.assertAll();
	}


	@Test(priority=19,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_019_Stability_Download_APK_BurgerKingApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_19_Browser============");
		SoftAssert sa19 = new SoftAssert();
		for(int j=19; j<=19; j++) {
		for(int i=1; i<=itr;i++) {	  
		
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa19);

			}
		}
		sa19.assertAll();
	}


	@Test(priority=20,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_020_Stability_Download_APK_FacebookApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_20_Browser============");
		SoftAssert sa20 = new SoftAssert();
		for(int j=20; j<=20; j++) {
		for(int i=1; i<=itr;i++) {	  
		

				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa20);

			}	
		}
		sa20.assertAll();
	}

	@Test(priority=21,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_021_Stability_Download_APK_TwitterApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_21_Browser============");
		SoftAssert sa21 = new SoftAssert();
		for(int j=21; j<=21; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa21);

			}	
		}
		sa21.assertAll();
	}

	@Test(priority=22,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_022_Stability_Download_APK_InstagramApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_22_Browser============");
		SoftAssert sa22 = new SoftAssert();
		for(int j=22; j<=22; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa22);

			}	
		}
		sa22.assertAll();
	}

	@Test(priority=23,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_023_Stability_Download_APK_MeetupApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_23_Browser============");
		SoftAssert sa23 = new SoftAssert();
		for(int j=23; j<=23; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa23);

			}	
		}
		sa23.assertAll();
	}

	@Test(priority=24,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_024_Stability_Download_APK_FedexApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_24_Browser============");
		SoftAssert sa24 = new SoftAssert();
		for(int j=24; j<=24; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa24);

			}	
		}
		sa24.assertAll();
	}

	@Test(priority=25,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_025_Stability_Download_APK_UPSApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_25_Browser============");
		SoftAssert sa25 = new SoftAssert();
		for(int j=25; j<=25; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa25);
			}
		}
		sa25.assertAll();
	}


	@Test(priority=26,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_026_Stability_Download_APK_UPSCApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_26_Browser============");
		SoftAssert sa26 = new SoftAssert();
		for(int j=26; j<=26; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa26);
			}	
		}
		sa26.assertAll();
	}


	@Test(priority=27,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_027_Stability_Download_APK_DHLApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_27_Browser============");
		SoftAssert sa27 = new SoftAssert();
		for(int j=27; j<=27; j++) {
		for(int i=1; i<=itr;i++) {	  

				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa27);

			}
		}
		sa27.assertAll();
	}


	@Test(priority=28,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_028_Stability_Download_APK_UberApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_28_Browser============");
		SoftAssert sa28 = new SoftAssert();
		for(int j=28; j<=28; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa28);

			}
		}
		sa28.assertAll();
	}

	@Test(priority=29,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_029_Stability_Download_APK_YellowcabsApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_29_Browser============");
		SoftAssert sa29 = new SoftAssert();
		for(int j=29; j<=29; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa29);

			}
		}
		sa29.assertAll();
	}

	@Test(priority=30,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_030_Stability_Download_APK_SupershuttleApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_30_Browser============");
		SoftAssert sa30 = new SoftAssert();
		for(int j=30; j<=30; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa30);
			}
		}
		sa30.assertAll();
	}

	@Test(priority=31,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_031_Stability_Download_APK_BudgetApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_31_Browser============");
		SoftAssert sa31 = new SoftAssert();
		for(int j=31; j<=31; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa31);

			}
		}
		sa31.assertAll();
	}

	@Test(priority=32,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_032_Stability_Download_APK_AvisApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_32_Browser============");
		SoftAssert sa32 = new SoftAssert();
		for(int j=32; j<=32; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa32);

			}
		}
		sa32.assertAll();
	}


	@Test(priority=33,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_033_Stability_Download_APK_HertzApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_33_Browser============");
		SoftAssert sa33 = new SoftAssert();
		for(int j=33; j<=33; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa33);
			}
		}
		sa33.assertAll();
	}

	@Test(priority=34,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_034_Stability_Download_APK_AlmoApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_34_Browser============");
		SoftAssert sa34 = new SoftAssert();
		for(int j=34; j<=34; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa34);
			}	
		}
		sa34.assertAll();
	}

	@Test(priority=35,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_035_Stability_Download_APK_AirportshuttleApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_35_APKDownload============");
		SoftAssert sa35 = new SoftAssert();
		for(int j=35; j<=35; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa35);
			}
			minWait();		
		}
		sa35.assertAll();
	}

	@Test(priority=36,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_036_Stability_Download_APK_DartApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_36_APKDownload============");
		SoftAssert sa36 = new SoftAssert();
		for(int i=1; i<=itr;i++) {	  
			for(int j=36; j<=36; j++) {
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa36);
			}
			minWait();		
		}
		sa36.assertAll();
	}

	@Test(priority=37,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_037_Stability_Download_APK_CNNApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_37_Browser============");
		SoftAssert sa37 = new SoftAssert();
		for(int j=37; j<=37; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa37);
			}
			minWait();		
		}
		sa37.assertAll();
	}

	@Test(priority=38,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_038_Stability_Download_APK_FoxApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_38_Browser============");
		SoftAssert sa38 = new SoftAssert();
		for(int j=38; j<=38; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa38);
			}
			minWait();		
		}
		sa38.assertAll();
	}

	@Test(priority=39,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_039_Stability_Download_APK_NYTimesApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_39_Browser============");
		SoftAssert sa39 = new SoftAssert();
		for(int j=39; j<=39; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa39);
			}
			minWait();		
		}
		sa39.assertAll();
	}

	@Test(priority=40,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_040_Stability_Download_APK_LAtimesApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_40_Browser============");
		SoftAssert sa40 = new SoftAssert();
		for(int j=40; j<=40; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa40);
			}
		}
		sa40.assertAll();
	}

	@Test(priority=41,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_041_Stability_Download_APK_BBCApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_41_Browser============");
		SoftAssert sa41 = new SoftAssert();
		for(int j=41; j<=41; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa41);
			}
		}
		sa41.assertAll();
	}

	@Test(priority=42,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_042_Stability_Download_APK_EspnApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_42_Browser============");
		SoftAssert sa42 = new SoftAssert();
		for(int j=42; j<=42; j++) {
		for(int i=1; i<=itr;i++) {	  
		
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa42);
			}
			minWait();		
		}
		sa42.assertAll();
	}

	@Test(priority=43,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_043_Stability_Download_APK_NbaApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_43_Browser============");
		SoftAssert sa43 = new SoftAssert();
		for(int j=43; j<=43; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa43);
			}	
		}
		sa43.assertAll();
	}

	@Test(priority=44,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_044_Stability_Download_APK_NFLApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_44_Browser============");
		SoftAssert sa44 = new SoftAssert();
		for(int j=44; j<=44; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa44);
			}	
		}
		sa44.assertAll();
	}

	@Test(priority=45,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_045_Stability_Download_APK_IndeedApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_45_Browser============");
		SoftAssert sa45 = new SoftAssert();
		for(int j=45; j<=45; j++) {
		for(int i=1; i<=itr;i++) {	  
			
				CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa45);
			}	
		}
		sa45.assertAll();
	}

	@Test(priority=46,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_046_Stability_Download_APK_MonsterApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_46_Browser============");
		SoftAssert sa46 = new SoftAssert();
		for(int j=46; j<=46; j++) {
		for(int i=1; i<=itr;i++) {	  
			
	           CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa46);
			}		
		}
		sa46.assertAll();
	}

	@Test(priority=47,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_047_Stability_Download_APK_AmazonPrimeApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_47_Browser============");
		SoftAssert sa47 = new SoftAssert();
		for(int j=47; j<=47; j++) {
		for(int i=1; i<=itr;i++) {	  
			
	            CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa47);
			}
		}
		sa47.assertAll();
	}

	@Test(priority=48,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_048_Stability_Download_APK_HBOApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_48_Browser============");
		SoftAssert sa48 = new SoftAssert();
		for(int j=48; j<=48; j++) {
		for(int i=1; i<=itr;i++) {	  
			
                CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa48);
			}	
		}
		sa48.assertAll();
	}

	@Test(priority=49,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_049_Stability_Download_APK_NetflixApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_49_Browser============");
		SoftAssert sa49 = new SoftAssert();
		for(int j=49; j<=49; j++) {
		for(int i=1; i<=itr;i++) {	  
			
                CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa49);
			}
		}
		sa49.assertAll();
	}

	@Test(priority=50,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_050_Stability_Download_APK_HuluApp(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_50_Browser============");
		SoftAssert sa50 = new SoftAssert();
		for(int j=50; j<=50; j++) {
		for(int i=1; i<=itr;i++) {	  
			
                CheckInstalled_App(data.get("appName"+j));
				customWait(4000);
				launch_APP(Locators_DeviceStability.PlayStore);
				customWait(4000);
				install_App(data.get("appName"+j));
				validate_Installed_App(data.get("appName"+j),i,sa50);
			}
		}
		sa50.assertAll();
	}*/
	
	@Test(priority=51,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_051_Stability_Uninstall_AllApps(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========XP8_Stability_51_Browser============");
		SoftAssert sa51 = new SoftAssert();
	
		for(int i=1; i<=1;i++) {	  
			for(int j=48; j<=50; j++) {
				clearRecentApps();
			    CheckInstalled_App(data.get("appName"+j));
			    validate_Uninstalled_App(data.get("appName"+j),i,sa51);
			}
		}
		sa51.assertAll();
	}
}
