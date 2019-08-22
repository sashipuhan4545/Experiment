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
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.XP8_Stability_Util_orio;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_Contact_StabilityTest_Orio extends  XP8_Stability_Util_orio{

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int  itr =2;
	boolean value = false;

	@BeforeSuite
	public void beforeSuite() throws FileNotFoundException, InterruptedException, IOException, ParseException  
	{
		extent = new ExtentReports("src/test/resources/extentreport/XP8_Contact_Stability_Orio_TestReport.html", true); //Provide Desired Report Directory Location and Name
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
		Locators_DeviceStability loc=new Locators_DeviceStability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);	
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver),loc1);
		excel=new ExcelReader(ExcelConstants.XP5S_XL_PATH);	
	}

	
	@Test(priority=1,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_001_Stability_Create_Contact_with_all_Fields_In_PhoneMemory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_01_Contact============");
		clearRecentApps();
		launch_an_app("contacts");
		
		deleteContacts();
		for(int i=1; i<=itr;i++) {
			//For Below Method user should enter 1 to save for phone memory and 2 to save for SIM memory in First Argument.
			add_NewContact_withAllFields_O("Test"+i,data.get("lastName"),data.get("phoneticlastName"),data.get("phoneticmiddleName"),
					data.get("phoneticName"), data.get("nickName"),data.get("company"), data.get("title"),refNum,data.get("SIP"),data.get("email"),
					data.get("address"),data.get("IM"),data.get("webSite"),data.get("relationship"),data.get("notes"));
	SoftAssert sa=new SoftAssert();
			validateContactCreation(i, sa);
		}	   
	}
	
	@Test(priority=2,dataProvider="XP8_Stability", dataProviderClass=DataProviders.class)
	public void XP8_TC_002_Stability_Create_Contact_With_Name_ContactNumber_SIM_Memory(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException
	{
		APP_LOGS.info("===========ATT_XP8_Stability_02_Contact============");
		clearRecentApps();
		launch_an_app("contacts");
		deleteContacts();
		for(int i=1; i<=itr;i++) {
			//For Below Method user should enter 1 to save for phone memory and 2 to save for SIM memory in First Argument.
			add_NewContact(2,"Test"+i,refNum);
			validateContactCreation_SIM(i);
		}	   
	}


}
