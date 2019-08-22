package com.xp8.util;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Wifi extends BaseUtil{

	private static final String String = null;
	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;
	public boolean check = false;
	public boolean check1 = false;

	boolean value = false;
	public String p_Id = ""; // Primary Device Serial Number.
	public String r_Id = ""; // Reference Device Serial Number.
	public String p_b_No = ""; // Primary Device Build Number.
	public String r_b_No = ""; // Reference Device Build Number.
	public String pryNum = HomeController.PRIMARYDEVMDN; // Reference Device MDN.
	public String refNum = HomeController.REFERENCEDEVMDN; // Reference Device MDN.

	public void fetch_Devices_Details()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id = JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id = JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No = JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No = JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}
	
	public void wifi_enable()
	{
		 try
		  {
			  clickBtn(Locator_wifi_hospot.networkandinternetopt);
			  clickBtn(Locator_wifi_hospot.wifiradiobtn);
			  if(Locator_wifi_hospot.wifiradiobtn.isEnabled())
			  {
				  
			  }
			  else {
				  clickBtn(Locator_wifi_hospot.wifiradiobtn);
			  }
		  }
		  catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators->wifi_enable()");
				e.printStackTrace();
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->wifi_enable()");
				e.printStackTrace();
			}
		
	}
	public void validate_FBApplication(SoftAssert sa) throws InterruptedException, IOException {
		try {
			
			clickBtn(Locators_Data_Setting.googletextfield);
			enterTextToInputField(Locators_Data_Setting.textfieldinchrome, "https://www.facebook.com");

			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			if (isElementExist(Locator_wifi_hospot.nointernet)) {

				APP_LOGS.info("Failed to Launch the browser ");
				sa.assertTrue(true, "Failed to Launch the browser ");
				test.log(LogStatus.PASS, "Failed to Launch the browser");

			}

			else {

				APP_LOGS.info("Launch the browser sucessfully");
				sa.assertTrue(true, "Launch the browser sucessfully");
				test.log(LogStatus.PASS, "Launch the browser sucessfully");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->open_Browser(()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->open_Browser()");
		}
	

  }
	
	
	public void clickOn_Map(String appName) {

		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			APP_LOGS.info("HOme PAge");
			customWait(2000);
			Locators_BaseUtil.AppListIcon.click();
			customWait(1000);
			switch (appName) {
			case "map":
				scrollToElements(Locator_wifi_hospot.maps);
				clickBtn(Locator_wifi_hospot.maps);

				APP_LOGS.info("Clicked on Chrome successfully.");

				break;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_App()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_App()");
		}
	}
	public void clickOn_Youtube(String appName) {

		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			APP_LOGS.info("HOme PAge");
			customWait(2000);
			Locators_BaseUtil.AppListIcon.click();
			customWait(1000);
			switch (appName) {
			case "map":
				scrollToElements(Locator_wifi_hospot.youtube);
				clickBtn(Locator_wifi_hospot.youtube);

				APP_LOGS.info("Clicked on Chrome successfully.");

				break;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_App()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_App()");
		}
	}
	public void validate_Youtube(SoftAssert sa)
	{
		try
		{
			clickBtn(Locator_wifi_hospot.youtubesearchbtn);
			enterTextToInputField(Locator_wifi_hospot.searchyoutubeenter, "sonim ");
			if(Locator_wifi_hospot.xp8.isDisplayed())
			{
				APP_LOGS.info(" launch the vedio sucessfully  ");
				sa.assertTrue(true, " launch the vedio sucessfully ");
				test.log(LogStatus.PASS, "launch the vedio sucessfully ");
			}
			else
			{
				APP_LOGS.info(" failed to launch the vedio  ");
				sa.fail();
				test.log(LogStatus.PASS, "Failed to launch the vedio ");
			}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Youtube()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Youtube()");
		}
	}
	
	public void validate_google_Map(SoftAssert sa) {
		try {
			if (Locator_wifi_hospot.netwkerr.isDisplayed()) {
				APP_LOGS.info(" Failed to navigate the direction");
				sa.assertTrue(true, "Failed to navigate the direction");
				test.log(LogStatus.PASS, "Failed to navigate the direction");
			} else {
				APP_LOGS.info(" Successfully Navigate the direction");
				sa.assertTrue(true, " Successfully Navigate the direction");
				test.log(LogStatus.PASS, " Successfully Navigate the direction");

			}
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
	}
	public void Gmail()
	{
		try
		{
			clickBtn(Locator_wifi_hospot.gmailmenu);
			clickBtn(Locator_wifi_hospot.starred);
		}
		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_App()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_App()");
		}
	}
	
	public void validate_Gmail(SoftAssert sa) {
		try {
			if (Locator_wifi_hospot.gmailerr.isDisplayed()) {
				APP_LOGS.info("Failed to launch the gmail application");
				sa.assertTrue(true, "Failed to launch the gmail application");
				test.log(LogStatus.PASS, "Failed to launch the gmail application");
			} else {
				APP_LOGS.info("Successfully launch the gmail application");
				sa.assertTrue(true, "Successfully launch the gmail application");
				test.log(LogStatus.PASS, "Successfully launch the gmail application");
			}
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
	}

}
