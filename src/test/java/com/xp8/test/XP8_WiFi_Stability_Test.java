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

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTestInterruptedException;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.DataProviders;
import com.xp8.util.ExcelConstants;
import com.xp8.util.ExcelReader;
import com.xp8.util.JsonFileReaderAndWriter;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.XP8_Stability_Util_orio;

import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class XP8_WiFi_Stability_Test extends XP8_Stability_Util_orio {

	public ExcelReader excel;
	Properties properties;
	public static ExtentTestInterruptedException testexception;
	public int itr = 1;
	public Timer timer1;
	public static String ssid = "IDCSONWAP";
	public static String pswd = "1dcS0n1md0tc0MbLr";

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

	@BeforeMethod()
	public void beforeMethod(Method method) {
		test = extent.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName()); // Test
																													// Case
																													// Start
																													// Here
	}

	@BeforeClass
	public void copyFilesToDevice() throws IOException {
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
	public void tearDown(ITestResult result, Method method) throws IOException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshot_path = captureScreenshot(method.getName());
			String image = test.addScreenCapture(screenshot_path);
			test.log(LogStatus.FAIL, result.getThrowable());
		}
		extent.endTest(test);
		extent.flush();
	}

	/*@BeforeSuite
	public void numofTestCases() throws ClassNotFoundException {

		appiumService.TOTAL_NUM_OF_TESTCASES = GetMethods.TotalTestcase("XP8_TC", this.getClass());
	}*/

	@BeforeTest()
	public void setupSys() throws MalformedURLException, InterruptedException, FileNotFoundException, AWTException {
		properties = loadDriverAndProperties();
		Locators_DeviceStability loc = new Locators_DeviceStability(aDriver);
		Locators_BaseUtil loc1 = new Locators_BaseUtil(aDriver);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc);
		PageFactory.initElements(new AppiumFieldDecorator(aDriver), loc1);
		excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	}

	/*@BeforeTest
	public void timer() {
		try {
			timer1 = new Timer();
			timer1.schedule(new TimerTask() {
				@Override
				public void run() {
					
					if (isElementExist(Locators_DeviceStability.setting_TexkOKBTN)) {
						Locators_DeviceStability.setting_TexkOKBTN.click();
						try {
							customWait(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					
					
					
				}
			}, 0, 10 * (10 * 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/


	@Test(priority = 1, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_001_Stability_Connection_and_Disconnection_Secured_Wifi_DeviceSettings(Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_001============");
		SoftAssert sa = new SoftAssert();
			clearRecentApps();
			
		if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {  // AT&T Device
			
			clearRecentApps();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			clickOn_MobileNetwork();
			MobileData_Switch();

			for (int i = 1; i <= itr; i++) {
				clearRecentApps();
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				turnOn_Wifi();
				customWait(3000);
				remove_connectedNtwrk();
				customWait(3000);
				select_SSIDwifi(ssid);
				enter_Password(pswd);
				clickOn_Connect();
				clearChromeCache();
				launch_an_app("browser");
				clearChromePermission();
				validate_And_BrowseIn_Chrome(i, sa);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				remove_connectedNtwrk();
				launch_an_app("browser");
				validate_And_BrowseIn_Chrome_Disconnect(i, sa);
				customWait(2000);

			}
		} else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {  // Verizon Device
			
			clearRecentApps();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			clickOn_MobileNetwork();
			MobileData_Switch();

			for (int i = 1; i <= itr; i++) {
				clearRecentApps();
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				turnOn_Wifi();
				customWait(3000);
				remove_connectedNtwrk();
				customWait(3000);
				select_SSIDwifi(ssid);
				enter_Password(pswd);
				clickOn_Connect();
				customWait(2000);
				clearChromeCache();
				launch_an_app("browser");
				clearChromePermission_SPRN();
				validate_And_BrowseIn_Chrome(i, sa);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				remove_connectedNtwrk();
				launch_an_app("browser");
				validate_And_BrowseIn_Chrome_Disconnect(i, sa);
				customWait(2000);
			}
			}
		 else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {  // Sprint Device
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_MobileNetwork();
				MobileData_Switch();

				for (int i = 1; i <= itr; i++) {
					clearRecentApps();
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					turnOn_Wifi();
					customWait(5000);
					remove_connectedNtwrk();
					customWait(3000);
					select_SSIDwifi(ssid);
					enter_Password(pswd);
					clickOn_Connect();
					clearChromeCache();
					launch_an_app("browser");
					clearChromePermission_SPRN();
					validate_And_BrowseIn_Chrome(i, sa);
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					remove_connectedNtwrk();
					launch_an_app("browser");
					validate_And_BrowseIn_Chrome_Disconnect(i, sa);
					customWait(2000);
				}
				}
		 else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {  // SL Device
				
			 	
				clearRecentApps();
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_MobileNetwork();
				MobileData_Switch();

				for (int i = 1; i <= itr; i++) {
					clearRecentApps();
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					turnOn_Wifi();
					customWait(3000);
					remove_connectedNtwrk();
					customWait(3000);
					select_SSIDwifi(ssid);
					enter_Password(pswd);
					clickOn_Connect();
					customWait(2000);
					clearChromeCache();
					launch_an_app("browser");
					clearChromePermission_SPRN();
					validate_And_BrowseIn_Chrome(i, sa);
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					remove_connectedNtwrk();
					launch_an_app("browser");
					validate_And_BrowseIn_Chrome_Disconnect(i, sa);
					customWait(2000);
				}
				}
		 else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {  // Bell Device
				clearRecentApps();
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				clickOn_MobileNetwork();
				MobileData_Switch();

				for (int i = 1; i <= itr; i++) {
					clearRecentApps();
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					turnOn_Wifi();
					customWait(3000);
					remove_connectedNtwrk();
					customWait(3000);
					select_SSIDwifi(ssid);
					enter_Password(pswd);
					clickOn_Connect();
					customWait(2000);
					clearChromeCache();
					launch_an_app("browser");
					clearChromePermission();
					validate_And_BrowseIn_Chrome(i, sa);
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					remove_connectedNtwrk();
					launch_an_app("browser");
					validate_And_BrowseIn_Chrome_Disconnect(i, sa);
					customWait(2000);
				}
				}

		sa.assertAll();
	}

	
	@Ignore
	@Test(priority = 2, dataProvider = "XP8_Stability", dataProviderClass = DataProviders.class)
	public void XP8_TC_002_Stability_Enable_Disable_Connection_Secured_Wifi_DeviceSettings(
			Hashtable<String, String> data) throws InterruptedException, AWTException, IOException, ParseException {
		APP_LOGS.info("===========XP8_Stability_002============");
		SoftAssert sa = new SoftAssert();
		clearRecentApps();
		
		if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {  // AT&T Device
			
			clearRecentApps();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			MobileData_Switch();
	
		for (int i = 1; i <= itr; i++) {
			clearRecentApps();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToTextContains("Wi");
			turnOn_Wifi();
			customWait(3000);
			remove_connectedNtwrk();
			customWait(3000);
			select_SSIDwifi(ssid);
			enter_Password(pswd);
			clickOn_Connect();
			customWait(3000);
			clearChromeCache();
			launch_an_app("browser");
			clearChromePermission();
			validate_And_BrowseIn_Chrome(i, sa);
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToTextContains("Wi");
			turnOff_Wifi();
			launch_an_app("browser");
			validate_And_BrowseIn_Chrome_Disconnect(i, sa);
			customWait(2000);
		}
	
		} else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15")) {   // Verizon Device
			
			
			clearRecentApps();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			MobileData_Switch();
		
			for (int i = 1; i <= itr; i++) {
				clearRecentApps();
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				turnOn_Wifi();
				customWait(3000);
				remove_connectedNtwrk();
				customWait(3000);
				select_SSIDwifi(ssid);
				enter_Password(pswd);
				clickOn_Connect();
				customWait(2000);
				clearChromeCache();
				launch_an_app("browser");
				clearChromePermission_SPRN();
				validate_And_BrowseIn_Chrome(i, sa);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				turnOff_Wifi();
				launch_an_app("browser");
				validate_And_BrowseIn_Chrome_Disconnect(i, sa);
				customWait(2000);
			
			
		}
		}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {   // Sprint Device
				
				clearRecentApps();
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToText("Mobile network");
				MobileData_Switch();
			
				for (int i = 1; i <= itr; i++) {
					clearRecentApps();
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					turnOn_Wifi();
					customWait(3000);
					remove_connectedNtwrk();
					customWait(3000);
					select_SSIDwifi(ssid);
					enter_Password(pswd);
					clickOn_Connect();
					minWait();
					clearChromeCache();
					launch_an_app("browser");
					clearChromePermission_SPRN();
					validate_And_BrowseIn_Chrome(i, sa);
					launch_an_app("settings");
					clickOn_Networks_and_Internet();
					scrollToTextContains("Wi");
					turnOff_Wifi();
					launch_an_app("browser");
					validate_And_BrowseIn_Chrome_Disconnect(i, sa);
					customWait(2000);
				
				
			}
		}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {   // SL Device
			
			clearRecentApps();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			clickOn_MobileNetwork();
			MobileData_Switch();
		
			for (int i = 1; i <= itr; i++) {
				clearRecentApps();
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				turnOn_Wifi();
				customWait(3000);
				remove_connectedNtwrk();
				customWait(3000);
				select_SSIDwifi(ssid);
				enter_Password(pswd);
				clickOn_Connect();
				customWait(2000);
				clearChromeCache();
				launch_an_app("browser");
				clearChromePermission_SPRN();
				validate_And_BrowseIn_Chrome(i, sa);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				turnOff_Wifi();
				launch_an_app("browser");
				validate_And_BrowseIn_Chrome_Disconnect(i, sa);
				customWait(2000);
			
			
		}
	}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11")) {   // Bell Device
			
			clearRecentApps();
			launch_an_app("settings");
			clickOn_Networks_and_Internet();
			scrollToText("Mobile network");
			MobileData_Switch();
		
			for (int i = 1; i <= itr; i++) {
				clearRecentApps();
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				turnOn_Wifi();
				customWait(3000);
				remove_connectedNtwrk();
				customWait(3000);
				select_SSIDwifi(ssid);
				enter_Password(pswd);
				clickOn_Connect();
				customWait(2000);
				clearChromeCache();
				launch_an_app("browser");
				clearChromePermission();
				validate_And_BrowseIn_Chrome(i, sa);
				launch_an_app("settings");
				clickOn_Networks_and_Internet();
				scrollToTextContains("Wi");
				turnOff_Wifi();
				launch_an_app("browser");
				validate_And_BrowseIn_Chrome_Disconnect(i, sa);
				customWait(2000);
			
			
		}
	}
		sa.assertAll();
	
	}

}
