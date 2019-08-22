package com.xp8.util;


import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Sanity_Util extends BaseUtil {

	public static boolean check=false;
	public static ExtentReports extent;
	public static ExtentTest test;

	public SoftAssert sf1 = new SoftAssert();

	public String p_Id	 = "";						// Primary Device Serial Number.
	public String r_Id	 = "";						// Reference Device Serial Number.
	public String p_b_No = "";			   			// Primary Device Build Number.
	public String r_b_No = ""; 						// Reference Device Build Number.
	public String pryNum = HomeController.PRIMARYDEVMDN;		// Reference Device MDN.
	public String refNum = HomeController.REFERENCEDEVMDN;	// Reference Device MDN.


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {
		minWait();
		p_Id   = JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id   = JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No = JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No = JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}
	
	public void clickBackButton(int number) throws InterruptedException
	{
		/*
		 * clicks on back button with iteration as user input
		 */
			try {
		for(int i=0;i<number;i++){
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			}
	}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in clickBackButton() method");
			e.printStackTrace();
		}
	}
	
	public boolean scrollToTextLongPress(String text) {
		/*
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 */

		boolean check = false;
		try {
			TouchAction ta = new TouchAction(aDriver);
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";
			AndroidElement ele = aDriver.findElementByAndroidUIAutomator(scrollable + textElement);
			ta.longPress(ele).perform();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (Exception e) {
			return check;
		}
	}

	public boolean scrollToTextClick(String text) {
		/*
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 */

		boolean check = false;
		try {
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable + textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (Exception e) {
			return check;
		}
	}
	
	public void scrollUp() {
		try {
			/*
		   	org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
		   	System.out.println(size);
		   	int x=size.getWidth()/2;
		   	int starty=(int)(size.getHeight()*0.60);
		   	int endy=(int)(size.getHeight()*0.10);
			 */
			aDriver.swipe(600, 1800, 600, 400, 1000);
		} catch (NoSuchElementException e) {   
			e.printStackTrace();
		}
	}

	public void scrollDown() {

		try {
			aDriver.swipe(600, 400, 600, 1800, 1500);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void dailNumber(String dailNum) throws IOException {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.add_Call);
			minWait();
			clickBtn(Locators_XP8_Sanity.dailerPad);
			minWait();
			clickBtn(Locators_XP8_Sanity.enterNumFiled);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.enterNumFiled, dailNum);
			minWait();
			//Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+dailNum);
			minWait();
			clickBtn(Locators_XP8_Sanity.dail);
			customWait(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void dailCallUsingDailPad(String dailNum) throws InterruptedException, IOException {

		try {
			dailNumber(dailNum);
			if (!isElementExist(Locators_XP8_Sanity.turnOff_Airplane_PopUp)) {
				try {
					for(int j=1;j<=100;j++){
						Process child = null;
						if (r_b_No.contains("8A.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
						} else if(r_b_No.contains("5SA.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
						}
						InputStream inputStream = child.getInputStream();
						InputStreamReader isr = new InputStreamReader(inputStream);
						BufferedReader bf = new BufferedReader(isr);
						String  value=bf.readLine();

						System.out.println(value);
						if(value.contains("00000001")||value.contains("ffffffff")) {
							System.out.println("Phone is ringing so accepting call.");
							Thread.sleep(3000);
							Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
							break;
						}else {
							continue;
						}
					}
				}catch(Exception e) {
					Thread.sleep(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					Thread.sleep(2000);
				}
			}
			APP_LOGS.info("Number dailed is: "+dailNum);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void dailCallUsingDailPad_sprint(String dailNum) throws InterruptedException, IOException {

		try {
			dailNumber(dailNum);
			if (!isElementExist(Locators_XP8_Sanity.turnOff_Airplane_PopUp)) {
				try {
					for(int j=1;j<=100;j++){
						Process child = null;
						if (r_b_No.contains("8A.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27");
						} else if(r_b_No.contains("5SA.")) {
							child=Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 28");
						}
						InputStream inputStream = child.getInputStream();
						InputStreamReader isr = new InputStreamReader(inputStream);
						BufferedReader bf = new BufferedReader(isr);
						String  value=bf.readLine();

						System.out.println(value);
						if(value.contains("00000001")||value.contains("ffffffff")) {
							System.out.println("Phone is ringing so accepting call.");
							Thread.sleep(15000);
							Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
							break;
						}else {
							continue;
						}
					}
				}catch(Exception e) {
					Thread.sleep(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
					Thread.sleep(2000);
				}
			}
			APP_LOGS.info("Number dailed is: "+dailNum);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	
	public void clearSMSPermissions() throws InterruptedException {
		try {	
			customWait(2000);
			clickBtn(Locators_XP8_Sanity.NEXT_Msg);
			minWait();
			clickBtn(Locators_XP8_Sanity.allow_Permission);
			minWait();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void  navigateTo_Sound() throws InterruptedException {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Sound\"))").click();
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_DefaultSounds() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			boolean check1 = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"AT&T Firefly\"))").isDisplayed();
			minWait();
			boolean check2 = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Wave\"))").isDisplayed();
			minWait();
			boolean check3 = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Dance\"))").isDisplayed();
			minWait();

			if (check1&&check2&&check3) {
				check= true;
				sf.assertTrue(check, "Validation is PASS.");
				test.log(LogStatus.PASS, "TestCase Status is PASS.");
				test.log(LogStatus.INFO, "All Default Ringtones are correct.");
			} else {
				sf.fail();
				test.log(LogStatus.FAIL, "TestCase is Fail.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_DefaultSounds1() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			boolean check1 = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Digital Phone\"))").isDisplayed();
			minWait();
			boolean check2 = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Merope\"))").isDisplayed();
			minWait();
			boolean check3 = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Beep-Beep-Beep Alarm\"))").isDisplayed();
			minWait();

			if (check1&&check2&&check3) {
				check= true;
				sf.assertTrue(check, "Validation is PASS.");
				test.log(LogStatus.PASS, "TestCase Status is PASS.");
				test.log(LogStatus.INFO, "All Default Ringtones are correct.");
			} else {
				sf.fail();
				test.log(LogStatus.FAIL, "TestCase is Fail.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_DefaultSounds2() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().textContains(\"Power\"))");
			minWait();
			boolean check1 = aDriver.findElementByXPath("//*[contains(@text,'SonimPower') or contains(@text,'Power')]").isDisplayed();
			minWait();
			boolean check2 = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Wave\"))").isDisplayed();
			minWait();
			boolean check3 = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Dance\"))").isDisplayed();
			minWait();

			if (check1&&check2&&check3) {
				check= true;
				sf.assertTrue(check, "Validation is PASS.");
				test.log(LogStatus.PASS, "TestCase Status is PASS.");
				test.log(LogStatus.INFO, "All Default Ringtones are correct.");
			} else {
				sf.fail();
				test.log(LogStatus.FAIL, "TestCase is Fail.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void clearCameraPermission() throws InterruptedException {

		try {
			minWait();
			if(isElementExist(Locators_XP8_Sanity.allow_Permission)) {
				clickBtn(Locators_XP8_Sanity.allow_Permission);
				customWait(2000);
				clickBtn(Locators_XP8_Sanity.OK);
				minWait();
			}
			Runtime.getRuntime().exec("adb -s"+p_Id+" shell input tap 886 1136");
			customWait(3000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickOnCapture() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.capturePicture);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_CapturedPic() throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		try {
			boolean check1 = false;
			boolean check2 = false;

			minWait();
			clickBtn(Locators_XP8_Sanity.previewPicture);
			minWait();
			clickBtn(Locators_XP8_Sanity.pictureDetails);
			minWait();	

			String date = Locators_XP8_Sanity.picture_Date_Camera.getText();
			String time = Locators_XP8_Sanity.picture_Time_Camera.getText();

			Calendar cal = Calendar.getInstance();

			Formatter fmt = new Formatter();
			//actual arguments for below method("%tB %td %tY", cal, cal, cal) and "," is concatenated.
			fmt.format("%tB %te"+","+" %tY", cal, cal, cal);
			String date1 = fmt.toString();
			check1=date.equalsIgnoreCase(date1);

			Formatter fmt2 = new Formatter();
			fmt2.format("%tI"+":"+"%tM", cal,cal);
			String time1 = fmt2.toString().substring(1, 5);
			check2=time.contains(time1);

			//System.out.println(date);
			//System.out.println(time);
			//System.out.println(date1);
			//System.out.println(time1);

			if (check1&&check2) {
				check=true;
				sf.assertTrue(check, "Valiadtion is Pass.");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
			} else {
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}


	public void clickVideoButton() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.video_Button);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeTo_TestMode(String dailNum) throws InterruptedException, IOException {

		try {
			minWait();
			dailCallUsingDailPad(dailNum);
			minWait(); 
			clickBtn(Locators_XP8_Sanity.usbMode_Test);
			minWait();
			clickBtn(Locators_XP8_Sanity.test_Mode);
			minWait();
			//Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input keyevent --longpress KEYCODE_BACK");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			//Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell input keyevent --longpress KEYCODE_BACK");
			minWait();
			clickBtn(Locators_XP8_Sanity.yes);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void navigateTo_DateAndTime() throws InterruptedException, IOException {
		/*
		 * This Method is to navigate to "Date & Time"
		 */
		try {

			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Date & time\"))").click();
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}	

	public void enableAuto_DateTime_Timezone() throws InterruptedException, IOException {
		/*
		 * This Method is to Enable "Automatic date&time", "Automatic time zone" and "Use 24-hour format"
		 */

		try {
			minWait();
			if (!isElementExist(Locators_XP8_Sanity.auto_DateTime_Enabled)) {
				minWait();
				clickBtn(Locators_XP8_Sanity.auto_DateTime_Disabled);
				APP_LOGS.info("Automatic Date and Time is Enabled");
				minWait();
			}
			if (!isElementExist(Locators_XP8_Sanity.auto_TimeZone_Enabled)) {
				minWait();
				clickBtn(Locators_XP8_Sanity.auto_TimeZone_Disabled);
				APP_LOGS.info("Automatic TimeZone is Enabled");
				minWait();	
			}
			if (!isElementExist(Locators_XP8_Sanity.hour_format_Enabled)) {
				minWait();
				clickBtn(Locators_XP8_Sanity.hour_format_Disabled);
				APP_LOGS.info("Use 24-hour format is Enabled");
				minWait();	
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void endCall_RefDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 6");
			Thread.sleep(1000);
		} catch (Exception e) {
		}
	}

	public void validate_DateTime_And_Timezone() throws InterruptedException {
		/*
		 * This Method is to validate Time, Date and Timezone.
		 * Precondition : Automatic date&time and Automatic Time zone are Enabled.
		 * 				  User should be in Date & Time window.
		 */	
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Date & time\"))").click();
			minWait();
		} catch (NoSuchElementException e) {		 
			e.printStackTrace();
		}

		SoftAssert sf = new SoftAssert();
		try {
			boolean check1=false;
			boolean check2=false;
			customWait(3000);
			String date = Locators_XP8_Sanity.date_in_SetDate.getText();
			String time = Locators_XP8_Sanity.time_in_SetTime.getText();
			String timezone = Locators_XP8_Sanity.timeZone.getText();
			minWait();
			System.out.println(date+" "+time+" "+timezone);

			Formatter fmt = new Formatter();
			Calendar cal = Calendar.getInstance();
			//actual arguments for below method("%tB %td %tY", cal, cal, cal) and "," is concatenated.
			fmt.format("%tB %te"+","+" %tY", cal, cal, cal);
			System.out.println(fmt);
			String date1 = fmt.toString();
			check1=date.equalsIgnoreCase(date1);

			java.util.Date today = new java.util.Date();
			Time fmt1 = new java.sql.Time(today.getTime());
			System.out.println(fmt1);
			String time1 = fmt1.toString();
			check2=time1.contains(time);

			if(check1&&check2) {
				APP_LOGS.info("Displayed Date And Time in the Device is Correct.");
				check=true;
				sf.assertTrue(check, "Test Case Valiadtion is PASS");
				test.log(LogStatus.PASS, "Displayed Date And Time in the Device is Correct.");
			} else {
				APP_LOGS.info("Displayed Date And Time is Not Correct");
				sf.fail();
				test.log(LogStatus.FAIL,"Displayed Date And Time is Not Correct");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void acceptPlayprotect() throws  InterruptedException{
		/* 
		 * This Method is Click OK on "Battery is Full" PopUp.
		 */
		try {
			minWait();
			if(isElementExist(Locators_XP8_Sanity.ACCEPT)) {
				clickBtn(Locators_XP8_Sanity.ACCEPT);
				minWait();
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void navigateTo_Battery() throws InterruptedException, IOException {
		/* This Method is to Navigate To Battery Options in Setting.*/
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Battery\"))").click();
			minWait();
		} catch (NoSuchElementException e) {		 
			e.printStackTrace();
		}		
	}

	public void validate_BatteryCharging_Or_Full() throws InterruptedException, IOException {
		/*
		 * This Method is to Validate Battery Charging Status or Full Message in Battery window.
		 */
		SoftAssert sf = new SoftAssert();
		try {		
			minWait();
			check=isElementExist(Locators_XP8_Sanity.charging_over_USB)||isElementExist(Locators_XP8_Sanity.battery_Full);
			if(check) {
				APP_LOGS.info("Battery is 'Charging OR Full' is Displayed");
				sf.assertTrue(check, "Test case Valiation is PASS");
				test.log(LogStatus.PASS, "Battery is 'Charging OR Full' is Displayed");
			}else {
				APP_LOGS.info("Battery is Charging OR Full is NOT Displayed");
				sf.fail();
				test.log(LogStatus.FAIL,"Battery is Charging OR Full is NOT Displayed");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void navigateTo_More() throws InterruptedException, IOException {
		/* 	This Method will navigate to MORE Options in Seetings. */

		minWait();
		try {

			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"More\"))").click();

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		minWait();
	}

	public void enable_AirplaneMode() throws InterruptedException, IOException {
		/* This Method is to Enable Airplane Mode */
		try {
			minWait();
			if(!isElementExist(Locators_XP8_Sanity.airplane_OnState)) {
				clickBtn(Locators_XP8_Sanity.airplane_OffState);
				APP_LOGS.info("AirplaneMode is Enabled");
				minWait();
			} else {
				APP_LOGS.info("AirplaneMode is already Enabled");
			}
		} catch (NoSuchElementException e) {		 
			e.printStackTrace();
		}		
	}

	public void disable_AirplaneMode() throws InterruptedException, IOException {
		/* This Method is to Disable Airplane Mode.*/
		try {
			minWait();
			if(!isElementExist(Locators_XP8_Sanity.airplane_OffState)) {
				minWait();
				clickBtn(Locators_XP8_Sanity.airplane_OnState);
				APP_LOGS.info("AirplaneMode is Disabled");
				minWait();
			} else {
				APP_LOGS.info("AirplaneMode is already Disabled");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}

	public void validate_Airplane_Enable(String dailNum) throws InterruptedException, IOException {
		/* 
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();

			minWait();
			if(isElementExist(Locators_XP8_Sanity.turnOff_Airplane_PopUp)) {
				minWait();
				check=true;
				APP_LOGS.info("TurnOff Airplane Mode PopUp Displayed Successfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "TestCase status is PASS");
				test.log(LogStatus.INFO, "TurnOff Airplane Mode PopUp is Displayed");
			} else  {
				minWait();
				APP_LOGS.info("TurnOff Airplane Mode PopUp NOT Displayed");				
				sf.fail();
				test.log(LogStatus.FAIL,"TurnOff Airplane Mode PopUp NOT Displayed");
			}
			minWait();
			clickBtn(Locators_XP8_Sanity.OK);
			minWait();
			clickBtn(Locators_XP8_Sanity.CANCEL);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}

	public void validate_Airplane_Disable(String dailNum) throws InterruptedException, IOException {
		/* 
		 * Method can be Used Validate Airplane Mode activation via by making the call.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();			
			minWait();
			if(!isElementExist(Locators_XP8_Sanity.turnOff_Airplane_PopUp)) {
				minWait();
				check=true;
				APP_LOGS.info("TurnOff Airplane Mode PopUp NOT Displayed.");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "TestCase status is PASS");
				test.log(LogStatus.INFO, "TurnOff Airplane Mode PopUp is NOT Displayed");
			} else  {
				minWait();
				APP_LOGS.info("TurnOff Airplane Mode PopUp is Displayed");				
				sf.fail();
				test.log(LogStatus.FAIL,"TurnOff Airplane Mode PopUp is Displayed");
			}
			minWait();
			clickBtn(Locators_XP8_Sanity.OK);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}	
		sf.assertAll();
	}

	public void navigateTo_CellularNetworks() throws InterruptedException, IOException {
		/* This Method will Navigate to Cellular Netwotks option. */ 
		minWait();
		try {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"More\"))").click();
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Cellular networks\"))").click();
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_Preffered_NetworkMode_NotSupported() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		minWait();
		boolean value=false;
		try {
			value = aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().text(\"Preferred network type\"))").isDisplayed();
		} catch (Exception e) {
			if(p_b_No.contains("-10.")) {
				if (!value) {
					check=true;
					sf.assertTrue(check, "Valiadtion is Pass.");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "Preferred network type NOT displayed in ATT");
				} else {
					sf.fail();
					test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				}
			}
			sf.assertAll();
			return ;
		}

		minWait();
		System.out.println(value);	
		if (value) {
			check=true;
			sf.assertTrue(check, "Valiadtion is Pass.");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
		} else {
			sf.fail();
			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
		}
		sf.assertAll();		
	}

	public void enable_DataRoaming() throws InterruptedException {
		try {
			minWait();
			if(!isElementExist(Locators_XP8_Sanity.dataRoaming_OnState)) {
				clickBtn(Locators_XP8_Sanity.dataRoaming_OffState);
				APP_LOGS.info("Data Roaming is Enabled");
				minWait();
				clickBtn(Locators_XP8_Sanity.OK);
				minWait();
			} else {
				APP_LOGS.info("Data Roaming is already Enabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disable_DataRoaming() throws InterruptedException {
		try {
			minWait();
			if (!isElementExist(Locators_XP8_Sanity.dataRoaming_OffState)) {
				clickBtn(Locators_XP8_Sanity.dataRoaming_OnState);
				APP_LOGS.info("Data Roaming is disaabled");
				minWait();
				clickBtn(Locators_XP8_Sanity.OK);
				minWait();
			} else {
				APP_LOGS.info("Data Roaming is already disabled");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void clickOnAPNs() throws InterruptedException{
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().text(\"Access Point Names\"))").click();
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}	

	public void validate_APN_Diaplay() throws InterruptedException {
		/* This method will validate weather operators APN is displayed in APNs. */
		SoftAssert sf = new SoftAssert();
		try {		
			int no=Locators_XP8_Sanity.apns.size();
			if(no>=2||no>=1) {
				check=true;
				APP_LOGS.info("Operator Name is Displayed in APN's");
				sf.assertTrue(check, "Test case validation is PASS.");
				test.log(LogStatus.PASS, "Operator Name is Displayed in APN's");
			} else {
				APP_LOGS.info("Operator Name is NOT Displayed in APN's.");
				sf.fail();
				test.log(LogStatus.FAIL,"Operator Name is NOT Displayed in APN's.");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void unlock_Phone() throws InterruptedException, IOException {
		/*
		 *  Method is to Unlock the Device
		 * Precondition : Only Swipe lock. 
		 */

		try {
			if (aDriver.isLocked()) {				
				minWait();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 3");
				customWait(600);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 82");
				minWait();
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void unlock_RefPhone() throws InterruptedException, IOException {
		/*
		 *  Method is to Unlock the Device
		 * Precondition : Only Swipe lock. 
		 */

		try {		
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
				customWait(600);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 82");
				minWait();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_OperatorName_Lockscreen() throws InterruptedException {
		/* To Validate the Operater Name Present on the Lockscreen. */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POWER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POWER);
			minWait();
			if(isElementExist(Locators_XP8_Sanity.operatorName_Lockscreen)) {
				check=true;
				String operatorName = Locators_XP8_Sanity.operatorName_Lockscreen.getText();
				minWait();
				APP_LOGS.info("Operator Name is Displayed on LockScren : "+operatorName);
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Operator Name is Displayed on LockScren : "+operatorName);
			} else {
				APP_LOGS.info("Operator Nmae is NOT Displayed on Lockscreen.");
				sf.fail();
				test.log(LogStatus.FAIL,"Operator Nmae is NOT Displayed on Lockscreen.");
			}					
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void swipe_NotificationBar() throws InterruptedException {
		/* Method is to swipe Notification Bar. */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
			aDriver.openNotifications();
			minWait();
			aDriver.swipe(600, 150, 600, 1200, 400);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}

	public void enable_Shortcuts_In_QuickPanel(AndroidElement offStateElelment) throws InterruptedException {
		/*
		 * This Method is to Enable Any Quick panel Settings.
		 */
		try {
			minWait();
			if(isElementExist(offStateElelment)) {
				clickBtn(offStateElelment);
				APP_LOGS.info("Requested Shortcut is Enabled in Quick Panel");
				minWait();
				clickBtn(Locators_XP8_Sanity.OK_1);
				minWait();
			} else {
				APP_LOGS.info("Requested Shortcut Already Enabled");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void disable_Bluetooth() throws InterruptedException, IOException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.bluetooth);
			minWait();
			if (!isElementExist(Locators_XP8_Sanity.switch_Off_State)) {
				minWait();
				clickBtn(Locators_XP8_Sanity.switch_On_State);
				minWait();
			} else {
				APP_LOGS.info("Bluetooth is already Disabled");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}	

	public void disable_Shortcuts_QuickPanel(WebElement onStateElelment) throws InterruptedException {
		/*
		 * This Method is to cliuck on the shortcut in Quickpanel. 
		 * Precondition : User Should remain in that perticular window which functionality they enabled.
		 */		
		try {
			minWait();
			if(isElementExist(onStateElelment)) {
				minWait();
				clickBtn(onStateElelment);
				customWait(2000);
				clickBtn(Locators_XP8_Sanity.switch_On_State);
				APP_LOGS.info("Requested Shortcut is disabled in Quick Panel");
				minWait();
			} else {
				APP_LOGS.info("Requested Shortcut Already disabled");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}	
	}

	public void clickOnHomeBtn() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void clickOn_BackBtn() throws InterruptedException {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validate_QuickPanel_Enable() throws InterruptedException {
		/*
		 * This Method is to validate Enabled shortcut of Quick Panel in Settings.
		 * Precondition : User Should remain in that perticular window, which functionality they enable.
		 */		
		try {			
			if(isElementExist(Locators_XP8_Sanity.switch_On_State)) {
				minWait();
				clickBtn(Locators_XP8_Sanity.done_Btn);
				APP_LOGS.info("Requested Shortcut is enabled in Quick Panel");
				check=true;
				Assert.assertTrue(check);
			}else {
				minWait();
				APP_LOGS.info("Switch is NOT in the Enabled State");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		minWait();
	}

	public void disable_Switch_QuickPanel(WebElement switchToDisable) throws InterruptedException {
		/*
		 * This Method is to disable the Switch in Quick Panel 
		 * Precondition : User Should remain in that perticular window which functionality they enabled.
		 */	
		try {
			minWait();
			clickBtn(switchToDisable);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			APP_LOGS.info("Disabled the Switch Successfully");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}

	public void validate_AutoRotate() throws InterruptedException {
		/*
		 * This Method is Validate the Auto-Rotate.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_XP8_Sanity.autoRotate_On)) {
				APP_LOGS.info("Auto Rotate enabled Successfully");
				check=true;
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Auto rotate validation is PASS");
			} else {
				APP_LOGS.info("Auto-Rotate is NOT Enabled");
				sf.fail();
				test.log(LogStatus.FAIL,"Auto rotate validation is FAIL");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void clickOn_Dispaly() {

		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Display\"))").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void clickOn_Sound() {

		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Sound\"))").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void clickOn_Location() {

		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Location\"))").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void clickOn_PhoneRingtone() {

		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Phone ringtone\"))").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void select_Ringtone(String rintoneName) {

		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().text(\""+rintoneName+"\"))").click();
			minWait();
			clickBtn(Locators_XP8_Sanity.OK);
			minWait();
		} catch (Exception e) {
		}
	}

	public void clickOn_Sleep() {

		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Sleep\"))").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void disable_AutoRotate() throws InterruptedException  {
		/* This Mehod will Disable the Auto Rotate */
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Display\"))").click();
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"When device is rotated\"))").click();
			minWait();
			clickBtn(Locators_XP8_Sanity.stayInPortritView);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void click_SettingIcon_QuickPanel() throws InterruptedException {
		/* This Method will Direct you to Setting from Notification Bar */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.settingsIcon_QuickPanel);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}

	public void validate_SettingsLaunch() throws InterruptedException {
		/* Method is to validate Settings launch */
		SoftAssert sf= new SoftAssert();
		try {
			minWait();		
			if (isElementExist(Locators_XP8_Sanity.settingsText_SettingsApp)) {
				APP_LOGS.info("Settings App launched Successfully");
				check=true;
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Validated launch Settings from Quick panel : PASS");
			} else {
				APP_LOGS.info("Settings App NOT Launched");
				sf.fail();
				test.log(LogStatus.FAIL,"Validation launch Settings from Quick panel : FAIL");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void end_Call() throws InterruptedException {
		/* Method is to end Call.
		 * Precondition : User should initiate the Call to any Number. */
		try {
			minWait();
			if(isElementExist(Locators_XP8_Sanity.end_Call)) {
				clickBtn(Locators_XP8_Sanity.end_Call);
				APP_LOGS.info("Call Ended");
			}else {
				APP_LOGS.info("End Call Button Doesn't exist on screen");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validate_Num_In_CallLog(String expectedNum) throws InterruptedException {
		/* 
		 * This Method will Validate the Dailled Number in Call Log history.
		 * Precondition : User should be in Dialler Home Page.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.call_History);
			customWait(3000);
			String callLogNum = Locators_XP8_Sanity.first_No_In_CallLog.getText().replaceAll("[^0-9+]", "");
			System.out.println("callLogNum "+callLogNum);
			System.out.println("expectedNum "+expectedNum);
			if (callLogNum.contains(expectedNum)) {
				check=true;
				APP_LOGS.info("Dailled Number Successfully Recorded in Call log");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Number Validated in the Call Logs.");
			}else {
				APP_LOGS.info("Dailled Number NOT Recorded in Call log");
				sf.fail();
				test.log(LogStatus.FAIL,"Number NOT found in Call Logs.");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}		
		sf.assertAll();
	}

	public void clear_Call_History() throws InterruptedException, IOException {
		/* 
		 * This Method will Clear all the Call History from Device.
		 */		
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.call_History);
			minWait();
			if (!isElementExist(Locators_XP8_Sanity.call_Log_Empty)) {				
				clickBtn(Locators_XP8_Sanity.moreOptions);
				minWait();
				clickBtn(Locators_XP8_Sanity.callHistory_MoreOptions);
				minWait();
				clickBtn(Locators_XP8_Sanity.moreOptions_CallHistory);
				minWait();
				clickBtn(Locators_XP8_Sanity.clear_CallHistory);
				minWait();
				clickBtn(Locators_XP8_Sanity.selected_Option);
				minWait();
				clickBtn(Locators_XP8_Sanity.selectAll);
				minWait();
				clickBtn(Locators_XP8_Sanity.clear);
				minWait();
				clickBtn(Locators_XP8_Sanity.OK_1);
				customWait(2000);
				clickBtn(Locators_XP8_Sanity.navigateUp);
				minWait();
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_CallBtn() throws InterruptedException {
		/*This method is to click on Dialer icon in the Call Log Tab.*/
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.call_History);
			minWait();
			clickBtn(Locators_XP8_Sanity.dialBtn_Call_Log);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void search_Contact(String contactName) throws InterruptedException {
		/* To Search for the Required Contact. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.searchContacts);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.find_Contacts, contactName);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}

	public void setDefaultAccount_Contact() throws InterruptedException, IOException {

		try {
			System.out.println("Inside Default....");
			clickBtn(Locators_XP8_Sanity.open_Navigation_Drawer);
			minWait();
			clickBtn(Locators_XP8_Sanity.settings_Contact);
			minWait();
			clickBtn(Locators_XP8_Sanity.defaultAccount_NewContacts);
			minWait();
			clickBtn(Locators_XP8_Sanity.PHONE_RadioBtn);
			minWait();
			//clickBtn(Locators_XP8_Sanity_ATT.navigateUp);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent KEYCODE_BACK");
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}

	public void deleteContact_IfPresent(String contactName) throws InterruptedException, IOException {

		try {
			customWait(3000);
			clickBtn(Locators_XP8_Sanity.searchContacts);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.find_Contacts, contactName);
			minWait();
			if (isElementExist(Locators_XP8_Sanity.no_Contacts)) {
				APP_LOGS.info("No Contact of Same Name"); 
				System.out.println("No Contact of Same Name");
				clickBtn(Locators_XP8_Sanity.backButton);
				minWait();
			} else { 
				clickBtn(Locators_XP8_Sanity.searched_Contact);
				minWait();
				clearAllow();
				clickBtn(Locators_XP8_Sanity.moreOptions_Contact);
				minWait();
				clickBtn(Locators_XP8_Sanity.delete_moreOption);
				minWait();
				clickBtn(Locators_XP8_Sanity.delete_Confirm);
				minWait(); 
				clickBtn(Locators_XP8_Sanity.backButton);
				minWait();
				APP_LOGS.info("Contact deleted Successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();		   
		}  
	}

	public void add_NewContact(int enter1forPhone_2forSimMemory,String contactName, String contactNum, String contactEmail, String address) throws InterruptedException, IOException {
		/* Method is to Add Contact in Contacts.
		 * Precondition:User should give 1 for first argument to save for Phone Memory and 2 fo rsim Memory.
		 */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.add_NewContact);
			minWait();
			if(enter1forPhone_2forSimMemory==1) {
				clickBtn(Locators_XP8_Sanity.contact_SavingTo);
				minWait();
				clickBtn(Locators_XP8_Sanity.savingTo_Phone);
				minWait();
				enterTextToInputField(Locators_XP8_Sanity.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_XP8_Sanity.phone_NewContact, contactNum);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_XP8_Sanity.email_NewContact, contactEmail);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				clickBtn(Locators_XP8_Sanity.moreFields_NewContact);
				minWait();
				scrollUp();
				minWait();
				enterTextToInputField(Locators_XP8_Sanity.address_NewContact, address);
				minWait();  
			} else if (enter1forPhone_2forSimMemory==2) {
				clickBtn(Locators_XP8_Sanity.contact_SavingTo);
				minWait();
				clickBtn(Locators_XP8_Sanity.savingTo_SIM);
				minWait();
				enterTextToInputField(Locators_XP8_Sanity.name_NewContact, contactName);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(Locators_XP8_Sanity.phone_NewContact, contactNum);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				//enterTextToInputField(Locators_XP8_Sanity.phone_NewContact, contactNum);
				//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				//minWait();			
			}		
			clickBtn(Locators_XP8_Sanity.save_Icon);
			minWait();
			clearAllow();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}

	public void add_NewContact_withAllFields(String contactName, String phoneticName, String nickName,String company, String title, String phone, String email,String address, String IM,String webSite, String notes) {

		try {
			clickBtn(Locators_XP8_Sanity.add_NewContact);
			minWait();
			clickBtn(Locators_XP8_Sanity.contact_SavingTo);
			minWait();
			clickBtn(Locators_XP8_Sanity.savingTo_Phone);
			minWait();
			scrollToText("More fields");
			//clickBtn(Locators_XP8_Sanity.more_Fields);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Name\"))").sendKeys(contactName);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Phonetic name\"))").sendKeys(phoneticName);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Nickname\"))").sendKeys(nickName);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Company\"))").sendKeys(company);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Title\"))").sendKeys(title);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Phone\"))").sendKeys(phone);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Email\"))").sendKeys(email);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Address\"))").sendKeys(address);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"IM\"))").sendKeys(IM);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Website\"))").sendKeys(webSite);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Notes\"))").sendKeys(notes);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Group name\"))").click();
			minWait();
			clickBtn(Locators_XP8_Sanity.co_workers);
			minWait();
			//clickBtn(Locators_XP8_Sanity.save_Icon);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 1000 150");
			minWait();
			clickBtn(Locators_XP8_Sanity.save_Icon);
			minWait();
			clearAllow();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_Added_Contact(String expectedContactName) throws InterruptedException, IOException {
		/*   To validate the Added contact.  */
		SoftAssert sf = new SoftAssert();
		try {			
			search_Contact(expectedContactName);
			String getContactName = Locators_XP8_Sanity.searched_Contact.getText();
			minWait();

			if(getContactName.equalsIgnoreCase(expectedContactName)) {
				check=true;
				APP_LOGS.info("New Contact Displayed Succceccfully in Contact List");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Contact added to the device Successfully");
			} else {
				APP_LOGS.info("Added Contact NOT present in Contact List");
				sf.fail();
				test.log(LogStatus.FAIL,"Contact didn't added.");
			}
			minWait(); 
			clickBtn(Locators_XP8_Sanity.backButton);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void delete_Contact(String contactName) throws InterruptedException, IOException {
		/* This Method to Delete the Contact 
		 * Precondition : User should creted the Contact with same Name.
		 */
		try {
			search_Contact(contactName);
			if(isElementExist(Locators_XP8_Sanity.searched_Contact)) {			
				clickBtn(Locators_XP8_Sanity.searched_Contact);
				minWait();
				clearAllow();
				clickBtn(Locators_XP8_Sanity.moreOptions);
				minWait();
				clickBtn(Locators_XP8_Sanity.delete_moreOption);
				minWait();
				clickBtn(Locators_XP8_Sanity.delete_Confirm);
				minWait();	
				clickBtn(Locators_XP8_Sanity.backButton);
				minWait();
				test.log(LogStatus.INFO, "Contact Deleted : "+contactName);
				APP_LOGS.info("Contact deleted Successfully");
			} else { 
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				APP_LOGS.info("Contact doesn't Exist");			

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			Assert.fail();
		}	
	}

	public void call_FromContact(String contactName) throws InterruptedException, IOException {
		/* This method is used to make from contact List.*/

		try {
			search_Contact(contactName);
			clickBtn(Locators_XP8_Sanity.searched_Contact);
			clearAllow();
			minWait();
			clickBtn(Locators_XP8_Sanity.callButton_Contact);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}

	public void validate_ContactDelete(String contactName) throws InterruptedException, IOException {
		/* Method is used to validate the deleted Contact.*/
		SoftAssert sf = new SoftAssert();
		try {
			search_Contact(contactName);
			if(isElementExist(Locators_XP8_Sanity.no_Contacts)) {
				check=true;
				APP_LOGS.info("Contact deleted Successfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Contact deleted Successfully.");
			} else {
				APP_LOGS.info("Contact doesn't deleted");
				sf.fail();
				test.log(LogStatus.FAIL,"Contact didn't deleted.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void clickOnAddAPN() throws InterruptedException {

		minWait();
		clickBtn(Locators_XP8_Sanity.add_APN);
		minWait();
	}

	public void add_Or_Edit_APN(String apnName, String apn) throws InterruptedException, IOException {


		clickBtn(Locators_XP8_Sanity.name_AddAPN);
		minWait();
		Locators_XP8_Sanity.textBox.clear();
		minWait();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+apnName);
		minWait();
		clickBtn(Locators_XP8_Sanity.OK);
		minWait();
		clickBtn(Locators_XP8_Sanity.apn_AddAPN);
		minWait();
		Locators_XP8_Sanity.textBox.clear();
		minWait();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+apn);
		minWait();
		clickBtn(Locators_XP8_Sanity.OK);
		minWait();
		clickBtn(Locators_XP8_Sanity.moreOptions);
		minWait();
		clickBtn(Locators_XP8_Sanity.save);
		minWait();
	}

	public void click_APN(String apnName) throws InterruptedException {
		minWait();
		aDriver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\""+apnName+"\")").click();
		minWait();
	}


	public void delete_APN() throws InterruptedException {

		minWait();
		clickBtn(Locators_XP8_Sanity.moreOptions);
		minWait();
		clickBtn(Locators_XP8_Sanity.deleteApn);
		minWait();

	}

	public void launch_SMS() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			clickBtn(Locators_XP8_Sanity.all_apps_handle);
			minWait();		
			aDriver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")").click();	
			APP_LOGS.info("SMS App Launched successfully");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			aDriver.swipe(540, 1880, 540, 130, 2000);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")").click();
			minWait();
		}
	}

	public void navigateTo_NewSMS() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_XP8_Sanity.add_NewSMS);
			minWait();
		} catch (NoSuchElementException e) {		 
			e.printStackTrace();
		}
	}
	
	public void navigateTo_NewSMS_Sprint() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_XP8_Sanity.add_NewSMS_Sprint);
			minWait();
		} catch (NoSuchElementException e) {		 
			e.printStackTrace();
		}
	}

	public void navigateTo_NewSMS1() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_XP8_Sanity.add_NewSMS1);
			minWait();
		} catch (NoSuchElementException e) {		 
			e.printStackTrace();
		}
	}

	public void enter_Num_ToField(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.TO_Field, number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	
	public void enter_Num_ToField_sprint(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void enter_Num_ToField1(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.TO_Field1, number);
			customWait(2000);
			clickBtn(Locators_XP8_Sanity.contactPicker);
			customWait(2000);			
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void enterText_MessageField(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.messageField, message);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	

	public void enterText_MessageField1(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.messageField1, message);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void create_NewSMS(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */
		navigateTo_NewSMS();
		try {
			enter_Num_ToField(number);
			enterText_MessageField(message);
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	
	public void create_NewSMS_Sprint(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.add_NewSMS_Sprint);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.TO_Field1, number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.messageField2, message);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	
	public void create_NewSMS_bell(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */
		try {
			clickBtn(Locators_XP8_Sanity.new_SmsBtn_bell);
			enterTextToInputField(Locators_XP8_Sanity.sms_ToField, number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.sms_TextField_bell, message);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->create_NewSMS_bell()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->create_NewSMS_bell()");
		}
	
	}
	public void click_NewSMS_bell() throws InterruptedException {
		/* Method used to create New SMS. */
		try {
			clickBtn(Locators_XP8_Sanity.new_SmsBtn_bell);
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->click_NewSMS_bell()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->click_NewSMS_bell()");
		}
	
	}
	
	public void clickOn_contact() throws InterruptedException {
		/* Method used to create New SMS. */
		try {
			clickBtn(Locators_XP8_Sanity.contactPicker_bell);
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_contact()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_contact()");
		}
	
	}

	public void create_NewSMS1(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */
		navigateTo_NewSMS1();
		try {
			enter_Num_ToField1(number);
			enterText_MessageField1(message);
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_Send() throws InterruptedException, IOException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_XP8_Sanity.send_Icon);
			minWait();
			//			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent KEYCODE_BACK");
			//			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void clickOn_Send_bell() throws InterruptedException, IOException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_XP8_Sanity.sms_sendBtn_bell);
			minWait();
			//			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent KEYCODE_BACK");
			//			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void clickOn_Send1() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_XP8_Sanity.send_Icon1);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void addContactToMsg_FromContacts(String contactName) throws InterruptedException, IOException {
		/* To add Contact to To Field From Reciepents. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.recipients_Picker);
			minWait();
			//clickBtn(Locators_XP8_Sanity.searchContacts);
			//minWait();
			scrollToText(contactName);
			minWait();
			clickBtn(Locators_XP8_Sanity.Ok);
			//Runtime.getRuntime().exec("adb -s "+p_b_No+" shell input text "+contactName);
			//enterTextToInputField(Locators_XP8_Sanity.search_2, contactName);
			//Locators_XP8_Sanity.search_2.sendKeys(contactName);
			//minWait();
			//clickBtn(Locators_XP8_Sanity.searched_Contact_1);
			//minWait();
			//clickBtn(Locators_XP8_Sanity.Ok_SMS);
			//minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}

	public void addContactToMsg_FromContacts1(String contactName) throws InterruptedException {
		/* To add Contact to To Field From Reciepents. */
		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.TO_Field1, contactName);
			minWait();
			clickBtn(Locators_XP8_Sanity.contactPicker);
			customWait(2000);
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}


	public void validate_SentMessage() throws InterruptedException {
		/* To validate the Sent Message. */
		SoftAssert sf = new SoftAssert();
		WebDriverWait wait  =new WebDriverWait(aDriver, 120);
		wait.until(ExpectedConditions.visibilityOf(Locators_XP8_Sanity.now_Text));	
		try {
			if(isElementExist(Locators_XP8_Sanity.now_Text)||isElementExist(Locators_XP8_Sanity.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "SMS or MMS Sent Successfully.");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL,"Message didn't sent.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void delete_SMS() throws InterruptedException {
		/* This Method delete the First Thread in the List. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.firstSMS_InList);
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_Thread);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_Confirm);
			minWait();
			
			test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	
	public void delete_SMS_sprint() throws InterruptedException {
		/* This Method delete the First Thread in the List. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.firstSMS_InList_Bell);
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_Sprint);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_ConfirmBtn_bell);
			minWait();
			
			test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	
	public void clearChromeCache() {
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.chrome");
			/*launch_an_app("settings");
			minWait();
			clickBtn(Locators_DeviceStability.setting_app_notificationLnk_Txt1);
			minWait();
			clickBtn(Locators_DeviceStability.setting_seeAllApps_Txt1);
			minWait();
			scrollToText("Chrome");
			minWait();
			scrollText("Storage");
			minWait();
			clickBtn(Locators_DeviceStability.setting_manageSpaceBtn_Txt1);
			minWait();
			clickBtn(Locators_DeviceStability.setting_ClearAllBtn_Txt1);
			minWait();
			Locators_DeviceStability.OkBtn_ID.click();
			minWait();*/
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->clearChromeCache()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clearChromeCache()");
		}
	}
	
	public void turnOff_wifi_adb() {
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am broadcast -a io.appium.settings.wifi --es setstatus disable");

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOff_wifi_adb()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOff_wifi_adb()");
		}
	}
	
	public void turnOn_wifi_adb() {
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am broadcast -a io.appium.settings.wifi --es setstatus enable");

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOff_wifi_adb()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOff_wifi_adb()");
		}
	}
	
	
	
	

	public void delete_All_SMS() throws InterruptedException {
		try {
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(2L, TimeUnit.MINUTES);
			while (System.nanoTime() < endTime) {
				System.out.println("Inside While ");
				if (isElementExist(Locators_XP8_Sanity.firstSMS_InList)) {
					System.out.println("Inside IFFFFFFFF");
					minWait();
					clickBtn(Locators_XP8_Sanity.firstSMS_InList);
					minWait();
					clickBtn(Locators_XP8_Sanity.moreOptions);
					minWait();
					clickBtn(Locators_XP8_Sanity.delete_Thread);
					minWait();
					clickBtn(Locators_XP8_Sanity.delete_Confirm);
					minWait();
				}else {
					System.out.println("Inside ELLLSEEE.");
					break;
				}
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	
	public void delete_All_SMS_Bell() throws InterruptedException {
		try {
			
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(2L, TimeUnit.MINUTES);
			while (System.nanoTime() < endTime) {
				System.out.println("Inside While ");
				if (isElementExist(Locators_XP8_Sanity.firstSMS_InList_Bell)) {
					System.out.println("Inside IFFFFFFFF");
					minWait();
					clickBtn(Locators_XP8_Sanity.firstSMS_InList_Bell);
					minWait();
					clickBtn(Locators_XP8_Sanity.moreOptions);
					minWait();
					clickBtn(Locators_XP8_Sanity.delete_Btn_Bell);
					minWait();
					clickBtn(Locators_XP8_Sanity.delete_ConfirmBtn_bell);
					minWait();
				}else {
					System.out.println("Inside ELLLSEEE.");
					break;
				}
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void delete_All_SMS1() throws InterruptedException {
		try {
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(3L, TimeUnit.MINUTES);
			while (System.nanoTime() < endTime) {
				if (isElementExist(Locators_XP8_Sanity.firstSMS_InList1)) {
					minWait();
					clickBtn(Locators_XP8_Sanity.firstSMS_InList1);
					minWait();
					clickBtn(Locators_XP8_Sanity.moreOptions);
					minWait();
					clickBtn(Locators_XP8_Sanity.delete_moreOption);
					minWait();
					clickBtn(Locators_XP8_Sanity.delete_Confirm);
					minWait();
				}else {
					break;
				}
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	
	public void launch_MessageApp() throws InterruptedException {
		try {
			
			aDriver.startActivity("com.google.android.apps.messaging", "com.google.android.apps.messaging.ui.ConversationListActivity");
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->launch_MessageApp()");
			e.printStackTrace();

		}catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->launch_MessageApp()");
		}
	
	}

	public void delete_SMS1() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.firstSMS_InList1);
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_moreOption);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_Confirm);
			minWait();
			test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	
	public void delete_presentAudioFiles() throws InterruptedException {
		/* This Method delete the All Audio files present in Sound Recorder */

		try {
			
			clickBtn(Locators_XP8_Sanity.AudioFiles_ListBtn);
			if (isElementExist(Locators_BaseUtil.allow_PopUp)) {
				clickBtn(Locators_BaseUtil.allow_PopUp);
				minWait();
			};
			 List<AndroidElement> AudioFiles = Locators_XP8_Sanity.AudioFile_Lists;
			 int size = AudioFiles.size();
			 if(size>0)
			 {
				 for (AndroidElement androidElement : AudioFiles) {
					
					 TouchAction ta = new TouchAction(aDriver);
					 ta.longPress(androidElement).release().perform();
					 
					clickBtn(Locators_XP8_Sanity.AudioFiles_SelecterBtn);
					if(isElementExist(Locators_XP8_Sanity.AudioFile_SelectAllBtn)) {
						clickBtn(Locators_XP8_Sanity.AudioFile_SelectAllBtn);
					}
					else {
						clickBackButton(1);
					}
					
					clickBtn(Locators_XP8_Sanity.AudioFiles_deleteActionBtn);
					clickBtn(Locators_XP8_Sanity.deleteBtn);
					clickBackButton(1);
					break;
					 
				}
			 }
			
			
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void enable_MobileData() throws InterruptedException, IOException {
		/* 
		 * This Method is to enable MobileData.
		 */
		if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-30.")) {
			try {
				minWait();
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Data usage\"))").click();
				minWait();
			} catch (Exception e) {
			}
			if(!isElementExist(Locators_XP8_Sanity.cellularData_OnState)) {
				clickBtn(Locators_XP8_Sanity.cellularData_OffState);
				if(isElementExist(Locators_XP8_Sanity.OK)) {
					clickBtn(Locators_XP8_Sanity.OK);
				}
				APP_LOGS.info("MobileData is Disabled");
				minWait();
			} else {
				APP_LOGS.info("MobileData is already Disabled");
			}
		} else if(p_b_No.contains("-10.")){
			try {
				navigateTo_CellularNetworks();
				minWait();
				if(!isElementExist(Locators_XP8_Sanity.mobileData_OnState)) {
					clickBtn(Locators_XP8_Sanity.mobileData_OffState);
					if(isElementExist(Locators_XP8_Sanity.OK)) {
						clickBtn(Locators_XP8_Sanity.OK);
					}
					APP_LOGS.info("MobileData is Disabled");
					minWait();
				} else {
					APP_LOGS.info("MobileData is already Disabled");
				}
			} catch (NoSuchElementException e) {			 
				e.printStackTrace();
			}	
		}	
	}

	public void disable_MobileData() throws InterruptedException, IOException {

		/* This Method is to Disable MobileData. */

		if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")||p_b_No.contains("-30.")) {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Data usage\"))").click();
			minWait();
			if(!isElementExist(Locators_XP8_Sanity.cellularData_OffState)) {
				clickBtn(Locators_XP8_Sanity.cellularData_OnState);
				if(isElementExist(Locators_XP8_Sanity.OK)) {
					clickBtn(Locators_XP8_Sanity.OK);
				}
				APP_LOGS.info("MobileData is Disabled");
				minWait();
			} else {
				APP_LOGS.info("MobileData is already Disabled");
			}
		}else if(p_b_No.contains("-10.")){
			try {	
				navigateTo_CellularNetworks();
				minWait();
				if(!isElementExist(Locators_XP8_Sanity.mobileData_OffState)) {
					clickBtn(Locators_XP8_Sanity.mobileData_OnState);
					if(isElementExist(Locators_XP8_Sanity.OK)) {
						clickBtn(Locators_XP8_Sanity.OK);
					}
					APP_LOGS.info("MobileData is Disabled");
					minWait();
				} else {
					APP_LOGS.info("MobileData is already Disabled");
				}
			} catch (NoSuchElementException e) {			 
				e.printStackTrace();
			}	
		}
	}

	public void closeAllTabs() throws InterruptedException {
		/* To close all tha Tabs in Chrome Browser. */

		//customWait(2000);
		//long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(3L, TimeUnit.MINUTES);
		try {
			//for (int i = 0; i < 5; i++) {
			customWait(1000);
			clickBtn(Locators_XP8_Sanity.switcherButton_Chrome);
			customWait(2000);
			clickBtn(Locators_XP8_Sanity.menuButton_Chrome);
			customWait(2000);
			clickBtn(Locators_XP8_Sanity.closeAllTabs_Chrome);
			customWait(2000);
			//				String[] CT = null;
			//				try {
			//					CT = Locators_XP8_Sanity.switcherButton_Chrome.getAttribute("name").split(" ");
			//					System.out.println(CT[0]);
			//					int noOfPage = Integer.parseInt(CT[0]);
			//					if (noOfPage >1) {
			//						continue;
			//					} else {
			//						break;
			//					}
			//				} catch (Exception e) {
			//					e.printStackTrace();
			//				}
			//				
			//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearChromePermission() throws InterruptedException {		
		customWait(2000);
		for (int i = 0; i < 5; i++) {			
			if (isElementExist(Locators_XP8_Sanity.ACCEPTCONTINUE)) {
				customWait(2000);
				clickBtn(Locators_XP8_Sanity.ACCEPTCONTINUE);
				minWait();
			} 
		}		
		customWait(2000);
		for (int i = 0; i < 5; i++) {	
			if (isElementExist(Locators_XP8_Sanity.NEXT)) {
				customWait(2000);
				clickBtn(Locators_XP8_Sanity.NEXT);
				minWait();
			} 
		}
		customWait(2000);
		for (int i = 0; i < 5; i++) {	
			if (isElementExist(Locators_XP8_Sanity.NO_THANKS)) {
				customWait(2000);
				clickBtn(Locators_XP8_Sanity.NO_THANKS);
				minWait();
			}
		}

		//		try {
		//			customWait(3000);
		//			clickBtn(Locators_XP8_Sanity.ACCEPTCONTINUE);
		//			customWait(5000);
		//			clickBtn(Locators_XP8_Sanity.NEXT);
		//			customWait(3000);
		//			clickBtn(Locators_XP8_Sanity.NO_THANKS);
		//			customWait(2000);
		//Runtime.getRuntime().exec("adb shell input tap 298 1764");
		//minWait();
		//Runtime.getRuntime().exec("adb shell input tap 408 1764");
		//minWait();
		//Runtime.getRuntime().exec("adb shell input tap 48 1764");
		//		} catch (Exception e) {	 	
		//			e.printStackTrace();
		//		}			
	}

	public void launchChrome() throws InterruptedException, IOException {

		minWait();
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -n com.android.chrome/com.google.android.apps.chrome.Main");
		minWait();
	}

	public void clear_SearchBox() throws InterruptedException {
		try {
			customWait(2000);
			String[] CT = Locators_XP8_Sanity.switcherButton_Chrome.getAttribute("name").split(" ");
			int noOfPage = Integer.parseInt(CT[0]);
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(3L, TimeUnit.MINUTES);
			System.out.println("Number of Pages "+noOfPage);
			for (int i = 0; i < 5; i++) {			
				if (noOfPage > 1) {
					System.out.println("Inside Clear.....");
					minWait();
					closeAllTabs();
					minWait();
					launch_APP(Locators_XP8_Sanity.chrome);
					minWait();
					//clickBtn(Locators_XP8_Sanity.Google_chrome);
					customWait(5000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_And_BrowseIn_Chrome(String url, AndroidElement autoSuggestion, AndroidElement expectedElement, AndroidElement expectedElement1) throws InterruptedException, IOException {
		/* 
		 * Validates MobileData enabality by launching the Chrome & browsing google page.
		 */
		SoftAssert  sf = new SoftAssert();
		try {
			customWait(2000);
			enterTextToInputField(Locators_XP8_Sanity.urlBar_Chrome, url);
			customWait(4000);
			clickBtn(autoSuggestion);
			customWait(10000);
			try {
				if(expectedElement.isDisplayed()) {
					check=true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					sf.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "URL loaded successfully : "+url);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					sf.fail();
					test.log(LogStatus.FAIL,"Test Case Status is Fail.");
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			} catch (Exception e) {
				if(expectedElement1.isDisplayed()) {
					check=true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					sf.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "URL loaded successfully : "+url);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					sf.fail();
					test.log(LogStatus.FAIL,"Test Case Status is Fail.");
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		closeAllTabs();	
		sf.assertAll();
	}

	public void validate_MobileData_Disable() throws InterruptedException {
		/* 
		 * Validates MobileData disabality by launching the Chrome.
		 */
		SoftAssert sf = new SoftAssert();
		//		Set<String> handles = aDriver.getContextHandles();
		//		for (String s:handles) {
		//			System.out.println(s);
		//		}
		//		aDriver.context((String)handles.toArray()[1]);
		customWait(2000);
		enterTextToInputField(Locators_XP8_Sanity.urlBar_Chrome, "www.google.co.in");
		customWait(4000);

		clickBtn(Locators_XP8_Sanity.googleCoIn_Text);
		customWait(9000);
		try {			
			customWait(3000);
			if(!isElementExist(Locators_XP8_Sanity.google_Logo)||!isElementExist(Locators_XP8_Sanity.google_Logo)){
				minWait();
				clickBtn(Locators_XP8_Sanity.OK);
				minWait();
				check=true;
				APP_LOGS.info("MobbileData Disabled Successfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "TestCase Status is PASS.");	
				test.log(LogStatus.INFO, "Disable Data OR Wi-Fi  validation is PASS.");			
			} else {
				APP_LOGS.info("Mobiledata is NOT Disabled");
				sf.fail();
				test.log(LogStatus.FAIL,"Disable Data OR Wi-Fi validation is FAIL.");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		closeAllTabs();
		sf.assertAll();
	}	

	public void navigateTo_TetheringAndPortableHotspot() throws InterruptedException, IOException {
		/* Navigates to Tethering And Portable Hotspot*/
		minWait();
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"More\"))").click();
			//clickBtn(Locators_XP8_Sanity.more);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Tethering & portable hotspot\"))").click();
			//clickBtn(Locators_XP8_Sanity.tetheringAndPortableHotspot);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}



	public void enable_USBTethering() throws InterruptedException {

		/* Enables the USB tethering. */
		try {
			minWait();
			if(!isElementExist(Locators_XP8_Sanity.usbTethering_OnState)) {
				clickBtn(Locators_XP8_Sanity.usbTethering_OffState);
				APP_LOGS.info("USB Tethering is Enabled");
				minWait();
			} else {
				APP_LOGS.info("USB Tethering is already Enabled");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void enable_BT_Tethering() throws InterruptedException {

		/* Enables the Bluetooth tethering. */
		try {
			minWait();
			if(!isElementExist(Locators_XP8_Sanity.BT_Tethering_OnState)) {
				clickBtn(Locators_XP8_Sanity.BT_Tethering_OffState);
				APP_LOGS.info("BT Tethering is Enabled");
				minWait();
			} else {
				APP_LOGS.info("BT Tethering is already Enabled");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void disable_BT_Tethering() throws InterruptedException {

		/* Enables the Bluetooth tethering. */
		try {
			minWait();
			if(!isElementExist(Locators_XP8_Sanity.BT_Tethering_OffState)) {
				clickBtn(Locators_XP8_Sanity.BT_Tethering_OnState);
				APP_LOGS.info("BT Tethering is Enabled");
				minWait();
			} else {
				APP_LOGS.info("BT Tethering is already Enabled");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}	

	public void enable_portableWiFi_hotspot() throws InterruptedException {

		/* Enables the Portable WiFi hotspot. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.portableWiFi_hotspot);
			minWait();

			try {clickBtn(Locators_XP8_Sanity.later_Btn);} catch (Exception e) { }

			if(!isElementExist(Locators_XP8_Sanity.portableWiFi_hotspot_OnState)) {
				clickBtn(Locators_XP8_Sanity.portableWiFi_hotspot_OffState);
				APP_LOGS.info("Portable WiFi_hotspot is Enabled");
				minWait();
				try {clickBtn(Locators_XP8_Sanity.skip_Btn);} catch (Exception e) { }
			} else {
				APP_LOGS.info("Portable WiFi_hotspot is already Enabled");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void disable_portableWiFi_hotspot() throws InterruptedException {

		/* Enables the Portable WiFi hotspot. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.portableWiFi_hotspot);
			minWait();
			if(!isElementExist(Locators_XP8_Sanity.portableWiFi_hotspot_OffState)) {
				clickBtn(Locators_XP8_Sanity.portableWiFi_hotspot_OnState);
				APP_LOGS.info("Portable WiFi_hotspot is Enabled");
				minWait();
				try {clickBtn(Locators_XP8_Sanity.skip_Btn);} catch (Exception e) { }
			} else {
				APP_LOGS.info("Portable WiFi_hotspot is already Enabled");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_EntitlementError() throws InterruptedException {
		/* 
		 * Validates Entitlement Error for USB Tethering, BT Tethering and Portable WiFi Hotspot.
		 * Precondition : Mobile Data should be Enabled. 
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			WebDriverWait wait= new WebDriverWait(aDriver, 180);
			wait.until(ExpectedConditions.visibilityOf(Locators_XP8_Sanity.entitlement_Error));
			minWait();
			if(isElementExist(Locators_XP8_Sanity.entitlement_Error)) {
				check=true;
				APP_LOGS.info("Entitlement Error Displayed successfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.PASS, "Entitlement Error Displayed successfully");
			} else {
				APP_LOGS.info("Entitlement Error NOT Displayed");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO,"Entitlement Error NOT Displayed");

			}
			minWait();
			clickBtn(Locators_XP8_Sanity.OK);
			minWait();

			if(isElementExist(Locators_XP8_Sanity.later_Btn)) {
				clickBtn(Locators_XP8_Sanity.later_Btn);
				minWait();							
			} else if (isElementExist(Locators_XP8_Sanity.skip_Btn)) {
				clickBtn(Locators_XP8_Sanity.skip_Btn);
				minWait();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void hangUp_Call() throws InterruptedException {
		/*	This Method Hang Up the call in Notification Bar Clicking on Hang Up.	*/
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
			aDriver.openNotifications();
			minWait();		
			try {
				Locators_XP8_Sanity.hangUp.click();
				minWait();
			} catch (NoSuchElementException e) {
				clickBtn(Locators_XP8_Sanity.ongoingCall_Icon);
				minWait();
				end_Call();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_Call_In_NotificationBar() throws InterruptedException {

		/* Used to Validate the call in background i.e in Notification Bar.*/
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
			aDriver.openNotifications();
			minWait();
			if(isElementExist(Locators_XP8_Sanity.hangUp)||isElementExist(Locators_XP8_Sanity.ongoingCall_Icon)) {
				check=true;
				APP_LOGS.info("Dailing the Call in Background");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "On going Call validated in the Notification panel.");
			} else {
				APP_LOGS.info("Background Call is Not Working");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
			aDriver.swipe(600, 1600, 600, 300, 400);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void navigateTo_LanguagesAndInput() throws InterruptedException, IOException {

		/* Navigate to Languages And Input*/
		minWait();	
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Languages & input\"))").click();
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clickOnLanguages() throws InterruptedException {
		/* Click's on Language  */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.languages);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_LanguageDiplay(String languageToSearch, AndroidElement expectedLanguage) throws InterruptedException {

		/* Validates expected Language from Device weather it is present or not*/
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.addLanguage);
			minWait();
			clickBtn(Locators_XP8_Sanity.search);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.typeLanguageName, languageToSearch);
			minWait();
			if(isElementExist(expectedLanguage)) {
				check=true;
				APP_LOGS.info("Expected Language Displayed Successfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, languageToSearch+" language is Displayed Successfully.");
			} else {
				APP_LOGS.info("Language doesn't exist on Device");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}		
		sf.assertAll();
	}

	/*
	public void validate_DefaultBookmarks() throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		aDriver.swipe(600, 1800, 600, 600, 500);
		minWait();
		clickBtn(Locators_XP8_Sanity.chrome_App);
		customWait(3000);
		clickBtn(Locators_XP8_Sanity.moreOptions);
		minWait();
		clickBtn(Locators_XP8_Sanity.bookmarks_Chrome);
		minWait();
		if (isElementExist(Locators_XP8_Sanity.partner_Bookmarks)) {
			check=true;
			APP_LOGS.info("Partner Bookmarks are avialable");
			Assert.assertTrue(check);
		} else {
			APP_LOGS.info("Partner bookmarks are NOT availble");
			Assert.fail();
		}		
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		minWait();closeAllTabs();
	}
	 */

	public void restoreAPNs() throws InterruptedException {
		/* To Restore Default APNs
		 * Precondition : User shoul be in APNs window.
		 */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			clickBtn(Locators_XP8_Sanity.restore);
			customWait(5000);
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void enable_Torch() throws InterruptedException {
		try {
			minWait();
			if (!isElementExist(Locators_XP8_Sanity.torch_On_Notification)) {
				clickBtn(Locators_XP8_Sanity.flashLight_Icon);
				minWait();
			} else {
				APP_LOGS.info("Torch is already enabled");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_TorchEnabled_Notification() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if (isElementExist(Locators_XP8_Sanity.torch_On_Notification)) {
				check=true;
				APP_LOGS.info("Torch Enabled Notification Displayed Successsfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass : Torch Enabled. ");
			} else {
				APP_LOGS.info("Torch Enabled Notification NOT Displayed");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void disable_Torch() throws InterruptedException {
		try {
			minWait();
			if (isElementExist(Locators_XP8_Sanity.torch_On_Notification)) {
				clickBtn(Locators_XP8_Sanity.flashLight_Icon);
				minWait();
			} else {
				APP_LOGS.info("Torch is already disabled");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clear_Calculator() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.equal);
			minWait();
			clickBtn(Locators_XP8_Sanity.clear_Calci);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}	
	}

	public static void validate_Calci_Result(String expectedAns) {
		SoftAssert sf = new SoftAssert();
		try {
			String obtainedAns = Locators_XP8_Sanity.result_Calci.getText();
			if (obtainedAns.equalsIgnoreCase(expectedAns)) {
				check=true;
				APP_LOGS.info("Operation of Numbers is Correct");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Expected :"+expectedAns+" and obtained :"+obtainedAns+" results are equal.");
			} else {
				APP_LOGS.info("Operation is NOT correct");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void addition_Calculator(AndroidElement fisrtNum, AndroidElement secondNum) throws InterruptedException {
		try {
			minWait();
			clickBtn(fisrtNum);
			minWait();
			clickBtn(Locators_XP8_Sanity.plus);
			minWait();
			clickBtn(secondNum);
			minWait();
			clickBtn(Locators_XP8_Sanity.equal);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void subtraction_Calculator(AndroidElement fisrtNum, AndroidElement secondNum) throws InterruptedException {
		try {
			minWait();
			clickBtn(fisrtNum);
			minWait();
			clickBtn(Locators_XP8_Sanity.minus);
			minWait();
			clickBtn(secondNum);
			minWait();			
			clickBtn(Locators_XP8_Sanity.equal);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void multiplication_Calculator(AndroidElement fisrtNum, AndroidElement secondNum) throws InterruptedException {
		try {
			minWait();
			clickBtn(fisrtNum);
			minWait();
			clickBtn(Locators_XP8_Sanity.multiply);
			minWait();
			clickBtn(secondNum);
			minWait();			
			clickBtn(Locators_XP8_Sanity.equal);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void division_Calculator(AndroidElement fisrtNum, AndroidElement secondNum) throws InterruptedException {
		try {
			minWait();
			clickBtn(fisrtNum);
			minWait();
			clickBtn(Locators_XP8_Sanity.divide);
			minWait();
			clickBtn(secondNum);
			minWait();			
			clickBtn(Locators_XP8_Sanity.equal);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_Record() throws InterruptedException {
		try {
			clickBtn(Locators_XP8_Sanity.recordButton);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_RecordList() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.listButton_SoundRec);
			customWait(2000);
			clickBtn(Locators_BaseUtil.allow_PopUp);
			customWait(2000);
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_StopRecord() throws InterruptedException {
		try {
			clickBtn(Locators_XP8_Sanity.stopButton);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_Save(String fileName) throws InterruptedException {
		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.rename_Edit_Text, fileName);
			minWait();
			clickBtn(Locators_XP8_Sanity.save_SoundRec);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_SoundRecList(String expectedListName) throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String obtainedListName = Locators_XP8_Sanity.file_list_SoundRec.getText();
			System.out.println(expectedListName);
			System.out.println(obtainedListName);
			if (expectedListName.equalsIgnoreCase(obtainedListName)) {
				check=true;
				APP_LOGS.info("Recorded file list Exist");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Recorded File Present in the Record List.");
			} else {
				APP_LOGS.info("File list doesn't exist");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {		 
			e.printStackTrace();
			sf.fail();
		}		
		sf.assertAll();
	}

	public void delete_SoundRecList() throws InterruptedException, IOException {		

		customWait(2000);
		try {			
			if (!isElementExist(Locators_XP8_Sanity.list_Empty)) {

				TouchAction action = new TouchAction(aDriver);
				action.longPress(Locators_XP8_Sanity.file_list_SoundRec).release().perform();
				minWait();
				clickBtn(Locators_XP8_Sanity.selectionSpinner);
				minWait();
				if (isElementExist(Locators_XP8_Sanity.selectAll)) {
					clickBtn(Locators_XP8_Sanity.selectAll);
					minWait();
				} else {
					clickBtn(Locators_XP8_Sanity.oneSelected);
					minWait();
				}				
				clickBtn(Locators_XP8_Sanity.delete_SoundRec);
				minWait();
				clickBtn(Locators_XP8_Sanity.delete_Confirm);
				minWait();
				//clickBtn(Locators_XP8_Sanity.navigate_Up);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent KEYCODE_BACK");
				minWait();
				APP_LOGS.info("Sound Rec Deleted Successfully");
			} else {

				APP_LOGS.info("Sound Rec doesn't exist");
				minWait();
				//clickBtn(Locators_XP8_Sanity.navigate_Up);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent KEYCODE_BACK");
				minWait();
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_File_Explorer() throws InterruptedException {

		minWait();
		boolean check1 = Locators_XP8_Sanity.actions_FileExpo.isDisplayed();
		boolean check2 = Locators_XP8_Sanity.search_FileExpo.isDisplayed();
		SoftAssert sf = new SoftAssert();
		if (check1&&check2) {
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
		} else {
			sf.fail();
			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
		}
		sf.assertAll();	
	}

	public void clickOnFm_ON_OFF() throws InterruptedException {
		minWait();
		clickBtn(Locators_XP8_Sanity.fm_OnOff_Button);
		minWait();
	}

	public void FM_ON() throws InterruptedException {
		minWait();
		if (!isElementExist(Locators_XP8_Sanity.record_Btn_FM)) {
			minWait();
			clickBtn(Locators_XP8_Sanity.fm_OnOff_Button);
			minWait();
		}
	}

	public void FM_OFF() throws InterruptedException {
		minWait();
		if (isElementExist(Locators_XP8_Sanity.record_Btn_FM)) {
			clickBtn(Locators_XP8_Sanity.fm_OnOff_Button);
			minWait();
		}
	}

	public void scan_AllStations() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			if (isElementExist(Locators_XP8_Sanity.scan_FM)) {
				clickBtn(Locators_XP8_Sanity.scan_FM);
				minWait();
				clickBtn(Locators_XP8_Sanity.all_Stations_FM);
				minWait();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.Button[@text='STOP']")));
				minWait();
			} else {
				test.log(LogStatus.INFO, "Ear Phone didn't Connected Properly.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addAndDelete_Alarm() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.alarm);
			minWait();
			clickBtn(Locators_XP8_Sanity.add_Alarm);
			minWait();
			clickBtn(Locators_XP8_Sanity.OK);
			customWait(2500);
			clickBtn(Locators_XP8_Sanity.delete_Alarm);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validate_TimeAndDate_InClock() throws InterruptedException {

		boolean check1=false;
		boolean check2=false;
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.CLOCK);
			minWait();
			String date = Locators_XP8_Sanity.date_InClock.getText();
			String time = Locators_XP8_Sanity.time_InClock.getText();
			System.out.println(date+" "+time);		

			Formatter fmt = new Formatter();
			Calendar cal = Calendar.getInstance();
			fmt.format("%ta"+","+" %tb %td", cal, cal, cal);
			System.out.println(fmt);
			String date1 = fmt.toString();
			check1=date.equalsIgnoreCase(date1);

			java.util.Date today = new java.util.Date();
			Time fmt1 = new java.sql.Time(today.getTime());
			System.out.println(fmt1);	
			String time1 = fmt1.toString();
			check2=time1.contains(time);

			boolean check3 = Locators_XP8_Sanity.deskClock.isDisplayed();

			if (check1&&check2&&check3) {
				check=true;
				APP_LOGS.info("Diplayed Time and Date is Correct");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Validated Time and Date in the Clock.");
			} else {
				APP_LOGS.info("Displayed Time and Date NOT correct");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_TimerDisplay() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.TIMER);
			minWait();
			check=Locators_XP8_Sanity.timer_Setup_Time.isDisplayed();
			if (check) {
				APP_LOGS.info("Timer is Displayed");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Validated Timer Display.");
			} else {
				APP_LOGS.info("Timer is NOT displayed");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_StopWatch() throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.STOPWATCH);
			minWait();
			boolean check1 = Locators_XP8_Sanity.stopwatch_Circle.isDisplayed();
			boolean check2 = Locators_XP8_Sanity.play_Stopwatch.isDisplayed();
			if (check1&&check2) {
				check=true;
				APP_LOGS.info("Stopwatch Displayed successfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Validated Stop Watch display.");
			} else {
				APP_LOGS.info("Stopwatch is NOT Displayed");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void navigateTo_Storage() throws InterruptedException {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Storage\"))").click();
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_StorageSpace(String expectedStorage) {
		SoftAssert sf = new SoftAssert();
		try {
			System.out.println(Locators_XP8_Sanity.summary.getText());
			String[] strings = Locators_XP8_Sanity.summary.getText().split(" ");
			System.out.println(strings[2]);
			if (strings[2].equalsIgnoreCase(expectedStorage)) {
				check=true;
				APP_LOGS.info("Storage space displayed is Correct.");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Validated the Storage space.");
			} else {
				APP_LOGS.info("Storage space displayed is NOT Correct.");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_StorageSpace_O(String expectedStorage) {
		SoftAssert sf = new SoftAssert();
		try {
			System.out.println(Locators_XP8_Sanity.summary.getText());
			String[] strings = Locators_XP8_Sanity.summary.getText().split(" ");
			System.out.println(strings[2]);
			if (strings[2].equalsIgnoreCase(expectedStorage)) {
				check=true;
				APP_LOGS.info("Storage space displayed is Correct.");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Validated the Storage space.");
			} else {
				APP_LOGS.info("Storage space displayed is NOT Correct.");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void navigateTo_Security() throws InterruptedException {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Security\"))").click();
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}

	public void setUpSIMcardLock(String PIN) throws InterruptedException {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Set up SIM card lock\"))");
			if(isElementExist(Locators_XP8_Sanity.setUpSIM_cardLock)) {
				clickBtn(Locators_XP8_Sanity.setUpSIM_cardLock);
				if (isElementExist(Locators_XP8_Sanity.lockSIMcard_disabledState)) {
					clickBtn(Locators_XP8_Sanity.lockSIMcard_disabledState);
					enterTextToInputField(Locators_XP8_Sanity.PIN_TextField, PIN);
					minWait();
					clickBtn(Locators_XP8_Sanity.OK);
					minWait();					
				} else {
					APP_LOGS.info("SIM card lock already Enabled");
					test.log(LogStatus.INFO, "SIM card lock already Enabled");
				}			
			} else {
				APP_LOGS.info("No SIM Card");
				test.log(LogStatus.INFO, "Please insert the SIM Card.");
				Assert.fail();				
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}

	public void validate_SIMcardLock(String PIN) throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.changeSIM_PIN);
			minWait();
			if (isElementExist(Locators_XP8_Sanity.oldSIM_PINText)) {
				enterTextToInputField(Locators_XP8_Sanity.PIN_TextField, PIN);
				minWait();
				clickBtn(Locators_XP8_Sanity.OK);
				minWait();
				if (isElementExist(Locators_XP8_Sanity.new_SIMPINText)) {
					enterTextToInputField(Locators_XP8_Sanity.PIN_TextField, PIN);
					minWait();
					clickBtn(Locators_XP8_Sanity.OK);
					minWait();
					enterTextToInputField(Locators_XP8_Sanity.PIN_TextField, PIN);
					minWait();
					clickBtn(Locators_XP8_Sanity.OK);
					minWait();
					APP_LOGS.info("PIN changed succesfully");
					check=true;
					sf.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "SIM PIN changed succesfully.");
				} else {
					APP_LOGS.info("New SIM PIN Text not visible.");
				}
			} else {
				APP_LOGS.info("Old SIM PIN Text not visible.");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void unlock_SIMCardPin(String PIN) throws InterruptedException {
		try {
			minWait();
			if (isElementExist(Locators_XP8_Sanity.lockSIMcard_EnabledState)) {
				clickBtn(Locators_XP8_Sanity.lockSIMcard_EnabledState);
				minWait();
				enterTextToInputField(Locators_XP8_Sanity.PIN_TextField, PIN);
				minWait();
				clickBtn(Locators_XP8_Sanity.OK);
			} else {
				APP_LOGS.info("SIM card already unlocked.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void navigateTo_AboutStatus() throws InterruptedException {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"About phone\"))").click();
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void checkFor_SoftwareUpdate() throws InterruptedException {

		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().textContains(\"pdate\"))").click();
			minWait();
			clickBtn(Locators_XP8_Sanity.checkForUpdates);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void validate_softwareUpdate() throws InterruptedException {

		minWait();
		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Processing')]")));
		customWait(2000);
		boolean	check1 = isElementExist(Locators_XP8_Sanity.currentSoftwareUptoDate);
		boolean	check2 = isElementExist(Locators_XP8_Sanity.noUpdatesAvailable);		
		boolean check3 = isElementExist(Locators_XP8_Sanity.softwareUpdateAvailable);
		minWait();
		SoftAssert sf = new SoftAssert();
		if (check1||check2) {	
			clickBtn(Locators_XP8_Sanity.OK);
			minWait();
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Current Software is upto date displayed succesfully.");
		} else if(check3) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Software Update is available displayed succesfully.");
		} else {
			sf.fail();
			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
		}
		sf.assertAll();	
	}

	public void clickOn_Status() throws InterruptedException {
		try {
			clickBtn(Locators_XP8_Sanity.status);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_SIM_Status() throws InterruptedException {
		try {
			clickBtn(Locators_XP8_Sanity.SIM_Status);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}

	public boolean validate_CellularNetworkType() throws InterruptedException {
		try {
			minWait();
			check=Locators_XP8_Sanity.cellularNetworkType.isDisplayed();
			if (check) {
				APP_LOGS.info("Cellular Network Type is LTE");
				Assert.assertTrue(check);
			} else {
				APP_LOGS.info("Cellular Network Type is NOT LTE");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
		return check;
	}

	public void clickOn_USBPowerSaving() throws InterruptedException {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"USB Power Saving\"))").click();
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_USBPowerSaving() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if (isElementExist(Locators_XP8_Sanity.automatic_PowerON)) {
				check=true;
				APP_LOGS.info("User is able to navigate to USB power saving Mode");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
			} else {
				APP_LOGS.info("User is NOT able to navigate to USB power saving Mode");
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void clickOn_MobileData_QuickPanel() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.mobileData_OnStateQuickPanel);
			minWait();
			try {
				clickBtn(Locators_XP8_Sanity.OK);
			} catch (Exception e) {				
			}
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void validate_RecentAppWindow() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {

			minWait();
			if (isElementExist(Locators_BaseUtil.no_Recent_Items)||isElementExist(Locators_XP8_Sanity.recent_App)) {
				APP_LOGS.info("No Recent App Window Displayed Succesfully");
				check=true;
				sf.assertTrue(check, "Validation ia Pass.");
				test.log(LogStatus.PASS	, "Validation is Pass");
			} else {
				APP_LOGS.info("Didn't switched to Recent Apps.");
				sf.fail();
				test.log(LogStatus.FAIL	, "Validation is Fail");
			}

		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();

	}

	public void validate_HomeScreen() throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if (isElementExist(Locators_XP8_Sanity.all_apps_handle)||isElementExist(Locators_XP8_Sanity.googleSearchBox)) {
				APP_LOGS.info("No Recent App Window Displayed Succesfully");
				check=true;
				sf.assertTrue(check, "Validation ia Pass.");
				test.log(LogStatus.PASS	, "Validation is Pass");
			} else {
				APP_LOGS.info("Didn't switched to Recent Apps.");
				sf.fail();
				test.log(LogStatus.FAIL	, "Validation is Fail");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_BuildNumber() throws IOException, InterruptedException {


		Process child = Runtime.getRuntime().exec("adb -s "+p_Id+" shell getprop ro.build.display.id");
		InputStream lsOut = child.getInputStream();
		InputStreamReader r = new InputStreamReader(lsOut);
		BufferedReader in = new BufferedReader(r);
		String builNo = in.readLine();

		System.out.println(builNo);
		minWait();
		scrollTo("Build number");
		minWait();
		String builNo1 = Locators_XP8_Sanity.buildNumber.getText();
		System.out.println(builNo1);
		SoftAssert sf = new SoftAssert();
		if (builNo.equals(builNo1)) {
			APP_LOGS.info("Build Number is Validated.");
			check=true;
			sf.assertTrue(check, "Validation ia Pass.");
			test.log(LogStatus.PASS	, "Validation is Pass");
			test.log(LogStatus.INFO, "Build Number is Validated : "+builNo);
		} else {
			APP_LOGS.info("Build Number NOT Validated.");
			sf.fail();
			test.log(LogStatus.FAIL	, "Validation is Fail: Build Number is NOT correct.");
		}
	}

	public void validate_BasebandVersion() throws IOException, InterruptedException {


		Process child = Runtime.getRuntime().exec("adb -s "+p_Id+" shell getprop gsm.version.baseband");
		InputStream lsOut = child.getInputStream();
		InputStreamReader r = new InputStreamReader(lsOut);
		BufferedReader in = new BufferedReader(r);
		String basebandversion = in.readLine();

		System.out.println(basebandversion);
		minWait();
		scrollTo("Baseband version");
		minWait();
		String basebandversion1 = Locators_XP8_Sanity.basebandVersion.getText();
		System.out.println(basebandversion1);
		SoftAssert sf = new SoftAssert();
		if (basebandversion.equals(basebandversion1)) {
			APP_LOGS.info("Build Number is Validated.");
			check=true;
			sf.assertTrue(check, "Validation ia Pass.");
			test.log(LogStatus.PASS	, "Validation is Pass");
			test.log(LogStatus.INFO, "Baseband Version is Validated : "+basebandversion);
		} else {
			APP_LOGS.info("Baseband Version NOT Validated.");
			sf.fail();
			test.log(LogStatus.FAIL	, "Validation is Fail: Baseband version is NOT correct.");
		}
	}

	public Process p;

	public String startAdbLog() throws AWTException, InterruptedException, IOException {

		Thread.sleep(1000);
		String fileName = UUID.randomUUID().toString().replaceAll("-","");
		System.out.println(fileName);
		String path = "src/test/resources/adbLogs/"+fileName+".txt";
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" logcat -c");
			Thread.sleep(1000);
			p=Runtime.getRuntime().exec("cmd /C \"adb -s "+p_Id+" logcat -v time>"+path);
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public String startRIL_Log() throws AWTException, InterruptedException, IOException {

		Thread.sleep(1000);
		String fileName = UUID.randomUUID().toString().replaceAll("-","");
		System.out.println(fileName);
		String path = "src/test/resources/adbLogs/"+fileName+".txt";
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" logcat -c");
			Thread.sleep(1000);
			p=Runtime.getRuntime().exec("cmd /C \"adb -s "+p_Id+" logcat -b all -v threadtime>"+path);
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public void validate_ADB_Logs(String filename,String string1, String string2, String string3) throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(string1,filename);
		boolean check2 = searchString(string2,filename);
		boolean check3 = searchString(string3,filename);
		SoftAssert sf = new SoftAssert();
		if (check1&&check2&&check3) {
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Validated from ADB Logs : "+string1+", "+string2+", "+string3);
		} else {
			sf.fail();
			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			test.log(LogStatus.INFO, "Validation failed from ADB Logs.");
		}
		sf.assertAll();
	}

	public void validate_ADB_Logs(String filename,String string1, String string2) throws InterruptedException {

		validate_ADB_Logs(filename, string1, string2,"");

	}

	public void validate_ADB_Logs(String filename,String string1) throws InterruptedException {

		validate_ADB_Logs(filename, string1,"","");

	}	

	public void validate_RIL_Logs(String filename,String validationString,String infoForFailure) throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString,filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Validated from ADB Logs : "+validationString);
		} else {
			sf.fail();
			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			test.log(LogStatus.INFO, "Validation failed from ADB Logs. ==>> "+infoForFailure);
		}
		sf.assertAll();
	}

	public void launch_APP(AndroidElement appToClick) throws InterruptedException {
		try {
			clickOnAppList();
			for (int i = 0; i < 6; i++) {
				if (isElementExist(appToClick)) {
					test.log(LogStatus.INFO, "\""+appToClick.getText()+"\" app launched.");
					clickBtn(appToClick);
					minWait();
					break;
				} else {
					scroll();
					minWait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void restore_DefaultStng() throws InterruptedException {
		try {
				minWait();
				clickBtn(Locators_XP8_Sanity.camera_MenuBtn);
				clickBtn(Locators_XP8_Sanity.camera_SettingBtn);
				scrollToTextClick("Restore defaults");
				clickBtn(Locators_XP8_Sanity.camera_OKBtn);
				clickBackButton(1);
				minWait();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public void clickOnAppList() throws InterruptedException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 3");
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);		
			customWait(2000);			
			clickBtn(Locators_XP8_Sanity.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void validate_Locators3(AndroidElement e1,AndroidElement e2,AndroidElement e3) throws InterruptedException {

		customWait(2000);
		boolean check1 = false;
		try {
			check1 = e1.isDisplayed();
		} catch (Exception e) {
		}
		boolean check2 = false;
		try {
			check2 = e2.isDisplayed();
		} catch (Exception e) {
		}
		boolean check3 = false;
		try {
			check3 = e3.isDisplayed();
		} catch (Exception e) {
		}
		SoftAssert sf = new SoftAssert();
		if (check1&&check2&&check3) {
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Provided Elements are Displayed.");
		} else {
			sf.fail();
			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			test.log(LogStatus.INFO, "Elements doesn't exist.");
		}
		sf.assertAll();
	}

	public void validate_Locators2(AndroidElement e1,AndroidElement e2) throws InterruptedException {

		customWait(2000);
		boolean check1 = false;
		try {
			check1 = e1.isDisplayed();
		} catch (Exception e) {
		}
		boolean check2 = false;
		try {
			check2 = e2.isDisplayed();
		} catch (Exception e) {
		}
		SoftAssert sf = new SoftAssert();
		if (check1&&check2) {
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Provided Elements are Displayed.");
		} else {
			sf.fail();
			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			test.log(LogStatus.INFO, "Elements doesn't exist.");
		}
		sf.assertAll();
	}

	public void validate_Locators(AndroidElement e1) throws InterruptedException {

		minWait();
		boolean check1 = false;		
		try {
			check1 = e1.isDisplayed();
		} catch (Exception e) {
		}
		if (check1) {
			check=true;
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Provided Elements are Displayed.");
			test.log(LogStatus.INFO, "------------------------------------------");
			sf1.assertTrue(check, "TestCase Valiation is PASS");
		} else {
			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			test.log(LogStatus.INFO, "Elements doesn't exist.");
			test.log(LogStatus.INFO, "------------------------------------------");
			sf1.fail();
		}
	}

	public void validate_Locators1(AndroidElement e1) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		minWait();
		boolean check1 = false;		
		try {
			check1 = e1.isDisplayed();
			if (check1) {
				check=true;
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Provided Elements are Displayed.");
				sf.assertTrue(check, "TestCase Valiation is PASS");
			} else {
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO, "Elements doesn't exist.");
				sf.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		sf.assertAll();
	}

	public void clickOn_BT() {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))").click();
			minWait();
		} catch (Exception e) {
		}
	}
	
	public void search_Video(String videoName){
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.Search);
			enterTextToInputField(Locators_XP8_Sanity.setdata,videoName);minWait();
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->search_Video()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->search_Video()");
			e.printStackTrace();
		}

	}
	
	public void clear_UpdateNotification() {
		try {
			if(isElementExist(Locators_XP8_Sanity.youtube_NotNowBtn)) {
				clickBtn(Locators_XP8_Sanity.youtube_NotNowBtn);
			}
		} catch (Exception e) {
		}
	}

	public void clickOnWifi() {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Wi-Fi\"))").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void setUp_And_Enable_WiFi() {
		/* SSID and password are hard coded for Sonim IDC. */
		try {
			minWait();
			if (!isElementExist(Locators_XP8_Sanity.wifi_ConnecteState)) {
				customWait(2000);
				clickBtn(Locators_XP8_Sanity.switch_OffState);
				customWait(10000);
				if (!isElementExist(Locators_XP8_Sanity.wifi_ConnecteState)) {
					aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"IDCSONWAP\"))").click();
					minWait();
					enterTextToInputField(Locators_XP8_Sanity.wifiPasswordTextBox,"1dcS0n1md0tc0MbLr");
					minWait();
					clickBtn(Locators_XP8_Sanity.connect);
					customWait(7000);
				}
			}			
		} catch (Exception e) {
		}
	}
	
	public void remove_connectedNtwrk() {
		try {
			
			boolean check1 = scrollToTextClick("Connected");
			boolean check2 = scrollToText("Check password and try again");
			if (check1 == true) {
				customWait(3000);
				scrollToTextClick("FORGET");
			} else if (check2 == true) {
				scrollToTextLongPress("Check password and try again");
				scrollToTextClick("Forget network");
			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in locators->remove_connectedNtwrk()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->remove_connectedNtwrk()");
		}
	}
	
	public void select_SSIDwifi(String ssid) {
		/*
		 * Selectin SSid And Clicking By scrolling
		 */
		try {

			scrollToText(ssid);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->select_SSIDwifi()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->select_SSIDwifi()");
		}
	}
	
	public void enter_Password(String Pswd) {

		try {
			minWait();
			enterTextToInputField(multi_Loc_Strategy(Locators_XP8_Sanity.wifi_PsswdBx,Locators_XP8_Sanity.wifi_PsswdBx_index, Locators_XP8_Sanity.wifi_PsswdBx_instance, null, null, 250, 500), Pswd);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enter_Password()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enter_Password()");
		}
	}
	
	public void clickOn_Connect() {

		try {
			clickBtn(Locators_XP8_Sanity.Wifi_connectBtn);
			customWait(3000);

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_Connect()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Connect()");
		}
	}

	public void enable_Switch() {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.switch_OffState);
			minWait();
		} catch (Exception e) {
		}
	}	

	public void disable_Switch() {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.switch_OnState);
			minWait();
		} catch (Exception e) {
		}
	}

	public void enable_BT_RefDevice() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.settings.BLUETOOTH_SETTINGS");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.bluetooth.adapter.action.REQUEST_ENABLE");
			minWait();		
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 22");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 22");
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
			customWait(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_BT_Devices() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(aDriver, 30);
		wait.until(ExpectedConditions.visibilityOf(Locators_XP8_Sanity.Available_devices));
		minWait();
		boolean check=Locators_XP8_Sanity.Available_devices.isDisplayed();
		minWait();
		SoftAssert sf = new SoftAssert();
		if (check) {
			check=true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Available Devices displayed under Bluethooth.");
		} else {
			sf.fail();
			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			test.log(LogStatus.INFO, "Available Devices NOT displayed under Bluethooth.");
		}
		sf.assertAll();
	}

	public void clickOn_AddGroup() {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.groups);
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			clickBtn(Locators_XP8_Sanity.add_Group);
			minWait();
			clickBtn(Locators_XP8_Sanity.PHONE_RadioBtn);
			minWait();
		} catch (Exception e) {
		}
	}

	public void create_GroupDetails(String groupName, String contactName) {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 150 500");
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+groupName);
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 150 640");
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+contactName);
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 150 770");
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 280 180");
			minWait();
		} catch (Exception e) {

		}
	}

	public void validate_GroupName(String groupName) throws InterruptedException {

		try {
			customWait(2000);
			String gorupName1 = Locators_XP8_Sanity.groupName_InBarTitle.getText();
			SoftAssert sf = new SoftAssert();
			if (gorupName1.equals(groupName)) {
				check=true;
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Contacts Group Created Successfully.");
			} else {
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO,"Contacts Group NOT Created.");
			}
			sf.assertAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_LabelName(String groupName) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			customWait(2000);
			String gorupName1 = Locators_XP8_Sanity.label_Name_InTitleBar.getText();

			if (gorupName1.equals(groupName)) {
				check=true;
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Contacts Label Created Successfully.");
			} else {
				sf.fail();
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO,"Contacts Group NOT Created.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void delete_Group() {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_moreOption);
			minWait();
			clickBtn(Locators_XP8_Sanity.OK);
			minWait();
		} catch (Exception e) {
		}
	}

	public void delete_Label() {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_Label);
			minWait();
		} catch (Exception e) {
		}
	}

	public void changeTo_FrontCam() {
		try {
			minWait();
			if(isElementExist(Locators_XP8_Sanity.flash_Button)) {
				minWait();
				clickBtn(Locators_XP8_Sanity.front_back_switcher);
				minWait();
			}
		} catch (Exception e) {

		}
	}

	public void clickOnAccounts() {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Accounts\"))").click();
			minWait();
		} catch (Exception e) {

		}
	}

	public void navigateTo_AddGoogleAccount() {
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			clickBtn(Locators_XP8_Sanity.add_Account);
			minWait();
			clickBtn(Locators_XP8_Sanity.google_Account);
			customWait(1600);
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add_GoogleAccount(String emailId, String password) {
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.email_googleAcnt, emailId);
			minWait();
			clickBtn(Locators_XP8_Sanity.next);
			minWait();
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Welcome' or @resource-id='profileIdentifier']")));
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.password_googleAcnt, password);
			minWait();
			clickBtn(Locators_XP8_Sanity.next);
			minWait();			
			if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
				try {
					aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.webkit.WebView\")).scrollIntoView(new UiSelector().textContains(\"Yes\"))").click();
					minWait();
				} catch (Exception e) {
				}
			}			
			clickBtn(Locators_XP8_Sanity.i_agree);
			minWait();
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
			clickBtn(Locators_XP8_Sanity.MORE);
			minWait();
			clickBtn(Locators_XP8_Sanity.ACCEPTorAGREE);
			customWait(3000);
		} catch (Exception e) {
		}
	}

	public void validate_GoogleAcccount(String email1) {
		try {
			System.out.println(email1);
			minWait();
			launch_APP(Locators_XP8_Sanity.settings);
			scrollToText("Users & accounts");
			minWait();
			clickBtn(Locators_XP8_Sanity.google_Account);
			minWait();
			String email2 = Locators_XP8_Sanity.googleAccount_email.getText();	
			System.out.println(email2);
			SoftAssert sf = new SoftAssert();
			if (email1.equals(email2)) {
				check=true;
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Google Account added Successfully.");
			} else {				
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO,"Google account didn't Added.");
				sf.fail();
			}
			sf.assertAll();
		} catch (Exception e) {

		}
	}

	public void remove_GoogleAcccount() {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.google_Account);
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			clickBtn(Locators_XP8_Sanity.remove_Account);
			minWait();
			clickBtn(Locators_XP8_Sanity.REMOVE_ACCOUNT);
			customWait(3000);
		} catch (Exception e) {

		}
	}

	public void install_App(String appName) {

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.google_Play);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.search_PlayStore, appName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(8000);
			//scrollToTextContains("appName");
			//clickBtn(Locators_XP8_Sanity.firstApp_Suggetion);
			aDriver.findElementByXPath("//*[contains(@text,'"+appName+"')]").click();
			customWait(4000);
			if (isElementExist(Locators_XP8_Sanity.INSTALL)) {
				minWait();
				clickBtn(Locators_XP8_Sanity.INSTALL);
				minWait();
				clickBtn(Locators_XP8_Sanity.ACCEPT);
				minWait();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='UNINSTALL']")));
				minWait();
			} else {
				test.log(LogStatus.FAIL, "App already installed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validate_Installed_App(String appName) {
		SoftAssert sf = new SoftAssert();
		try {
			clickOnAppList();
			enterTextToInputField(Locators_XP8_Sanity.searchApps, appName);
			minWait();
			if (isElementExist(Locators_XP8_Sanity.apkExtractor)) {
				check=true;
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "APK installed Successfully.");
			} else {				
				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO,"APK didn't Installed.");
				sf.fail();
			}
			sf.assertAll();
		} catch (Exception e) {

		}
	}

	public void unInstall_App(String appName) {

		WebDriverWait wait = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.google_Play);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.search_PlayStore, appName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			customWait(5000);
			if (isElementExist(Locators_XP8_Sanity.installed_Playstore)) {
				customWait(3000);
				clickBtn(Locators_XP8_Sanity.installed_Playstore);
				minWait();
				clickBtn(Locators_XP8_Sanity.UNINSTALL);
				minWait();
				clickBtn(Locators_XP8_Sanity.OK);
				minWait();
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
				minWait();
			} else {
				test.log(LogStatus.INFO, "APP is NOT installed to Uninstall.");
			}
		} catch (Exception e) {

		}
	}

	public void sendSMS_fromRefDevice(String AutomationMessagee) {

		// To validate MT Message User should be inside Messaging APP of Primary Device.
		try {
			minWait();
			// Below Code To clear Battery PopUp.
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); 
			minWait();
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.")||r_b_No.contains("-30.")||r_b_No.contains("-00.")) {
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 540 1776");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 713 1098");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 1699");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 918 952");
					minWait();
				} else if(r_b_No.contains("-11.")||r_b_No.contains("-12.")||r_b_No.contains("-18.")||r_b_No.contains("-26.")||r_b_No.contains("-29.")){
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.google.android.apps.messaging");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 1756");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 976");
					minWait();
				}else if(r_b_No.contains("-15.")){
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.verizon.messaging.vzmsgs");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1769");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1022");
					minWait();
				}
			} else if (r_b_No.contains("5SA.")){
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
				minWait();
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
				customWait(2000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
				customWait(6000);
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
				minWait();
			}
		} catch (Exception e) {

		}
	}

	public void validate_RecievedMessage() throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		SoftAssert sf = new SoftAssert();
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);
		wait.until(ExpectedConditions.visibilityOf(Locators_XP8_Sanity.now_Text));	
		customWait(8000);
		try {
			if(isElementExist(Locators_XP8_Sanity.now_Text)||isElementExist(Locators_XP8_Sanity.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully.");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL,"Message didn't Recieved.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void make_Call_from_RefDev() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.CALL -d tel:"+pryNum);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearRecentApps() throws InterruptedException {	 			
		try {
			customWait(3000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 3"); 
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(3000);
			if(isElementExist(Locators_BaseUtil.no_Recent_Items)) {
				APP_LOGS.info("No Recent Applications Present");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
			}else {
				long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(1L, TimeUnit.MINUTES);
				while( System.nanoTime() < endTime) {
					if(isElementExist(Locators_BaseUtil.clear_all)) {			
						Locators_BaseUtil.clear_all.click();
						break;
					}
					aDriver.swipe(600, 300, 600, 1400, 250);
					minWait();						 					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		 					 	
	}

	public void recieve_Call_PrimaryDev() throws IOException, InterruptedException {

		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");					
					child = Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call telecom 27");
				} else if(p_b_No.contains("5SA.")) {
					System.out.println("XP5");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
				}
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				String  value=in.readLine();
				System.out.println(value);
				if(value.contains("00000001")) {
					System.out.println("Phone is ringing so accepting call.");

					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
					break;
				}else {
					continue;
				}
			}
		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}

	public void clickAttachment() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.attachment);
			minWait();
		} catch (Exception e) {
		}
	}

	public void add_Picture() throws InterruptedException {

		try {
			if (p_b_No.contains("-10.")||p_b_No.contains("-30.")) {
				clickAttachment();
				minWait();
				clickBtn(Locators_XP8_Sanity.capturePicture_MMS);
				minWait();
				clearAllow();
				clickBtn(Locators_XP8_Sanity.capturePicture);
				customWait(10000);
				clickBtn(Locators_XP8_Sanity.done);
				minWait();
			} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){
				clickAttachment();
				minWait();
				clickBtn(Locators_XP8_Sanity.capture_pictures_or_video);
				minWait();
				clearAllow();
				clickBtn(Locators_XP8_Sanity.capture_pictures_or_video);
				minWait();
				clickBtn(Locators_XP8_Sanity.take_picture_MMS);
				customWait(10000);			
			}
		} catch (Exception e) {
		}		
	}

	public void clear_GmailPermission() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.GOT_IT);
			minWait();
			clickBtn(Locators_XP8_Sanity.TAKE_ME_TO_GMAIL);
			minWait();
		} catch (Exception e) {
		}
	}

	public void click_NewMail() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.compose_gmail);
			minWait();
		} catch (Exception e) {
		}
	}

	public void compose_NewMail(String TO, String subject, String mailContent) throws InterruptedException {

		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.To_gmail, TO);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.subject_gmail, subject);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.compose_Mail, mailContent);
			minWait();
		} catch (Exception e) {
		}		
	}

	public void send_Mail() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.send_gmail);
			minWait();
		} catch (Exception e) {
		}
	}

	public void deleteAllmails() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.dismiss_icon_Gmail);
			minWait();			
			while(isElementExist(Locators_XP8_Sanity.select_Mail)) {
				clickBtn(Locators_XP8_Sanity.select_Mail);
				minWait();
				clickBtn(Locators_XP8_Sanity.delete_gmail);
				minWait();
			}

		} catch (Exception e) {
		}
	}

	public void validate_Mail(String validationText) throws InterruptedException {

		minWait();
		boolean check1 = aDriver.findElementByXPath("//*[contains(@content-desc,'"+validationText+"')]").isDisplayed();
		SoftAssert sf = new SoftAssert();
		if(check1) {
			check=true;
			APP_LOGS.info("Mail sent Succeccfully");
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Mail Sent and Recieved Successfully.");
		} else {
			APP_LOGS.info("Mail didn't sent");
			sf.fail();
			test.log(LogStatus.FAIL,"Mail didn't Sent.");
		}

	}

	public void validate_RingtoneChange(AndroidElement e1) throws InterruptedException {

		SoftAssert sf = new SoftAssert();
		minWait();
		boolean check1 = false;
		minWait();
		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Phone ringtone\"))");
		minWait();
		try {
			check1 = e1.isDisplayed();
		} catch (Exception e) {
		}

		if (check1) {
			check=true;
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Ringtone Changed Successfully.");
			sf.assertTrue(check, "TestCase Valiation is PASS");
		} else {

			test.log(LogStatus.FAIL,"Test Case Status is Fail.");
			test.log(LogStatus.INFO, "Ringtonr didn't Changed");
			sf.fail();
		}
		sf.assertAll();		
	}

	public void clear_Calender_Permission() throws InterruptedException {

		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		minWait();
		try {
			for (int i = 1; i <=3; i++) {
				clickBtn(Locators_XP8_Sanity.right_arrow);
				minWait();
			}
			minWait();
			clickBtn(Locators_XP8_Sanity.GOT_IT_1);
			minWait();
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
		} catch (Exception e) {
		}
	}

	public void add_CalenderEvent(String eventname) throws InterruptedException {

		try {
			minWait();
			System.out.println("Inside ADD calender.");
			clickBtn(Locators_XP8_Sanity.addEvent);
			minWait();
			clickBtn(Locators_XP8_Sanity.event);
			minWait();
			Locators_XP8_Sanity.title_calender.click();
			minWait();
			clearAllow();
			enterTextToInputField(Locators_XP8_Sanity.title_calender, eventname);
			minWait();
			clickBtn(Locators_XP8_Sanity.done_Btn);
			minWait();
			clickBtn(Locators_XP8_Sanity.save);
			minWait();
		} catch (Exception e) {
		}
	}

	public void validate_CalenderEvent(String eventName) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			boolean check1 = false;
			try {
				check1 =aDriver.findElementByXPath("//*[contains(@content-desc,'"+eventName+"')]").isDisplayed();
			} catch (Exception e) {
			}

			if (check1) {
				check=true;
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Calender Event created Successfully.");
				sf.assertTrue(check, "TestCase Valiation is PASS");
			} else {

				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO, "calender Event didn't added.");
				sf.fail();
			}	
		} catch (Exception e){
		}
		sf.assertAll();		
	}

	public void delete_calenderEvent(String eventName) throws InterruptedException {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@content-desc,'"+eventName+"')]").click();
			customWait(8000);
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_moreOption);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_Confirm);
			minWait();
		} catch (Exception e) {
		}
	}

	public void reject_Call_With_SMS(String message) throws InterruptedException {

		try {
			minWait();
			//clickBtn(Locators_XP8_Sanity.incoming_Call);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 540 170");
			minWait();
			aDriver.swipe(540, 1360, 540, 960, 1000);
			minWait();
			clickBtn(Locators_XP8_Sanity.write_Your_Own);
			minWait();
			//enterTextToInputField(Locators_XP8_Sanity.msg_popup, message);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+message);
			minWait();
			clickBtn(Locators_XP8_Sanity.SEND);
			minWait();
		} catch (Exception e) {
		}		
	}

	public void validate_Call_Details_and_Delete(String expectedNum) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {			
			minWait();
			clickBtn(Locators_XP8_Sanity.call_location_and_date);
			minWait();
			clickBtn(Locators_XP8_Sanity.call_Details);
			minWait();
			String numIn_Details = Locators_XP8_Sanity.caller_name_callDetails.getText().replaceAll("[^0-9+]", "");
			System.out.println(numIn_Details);
			System.out.println(expectedNum);
			boolean check1 = numIn_Details.contains(expectedNum);
			System.out.println(check1);
			String[] strings = Locators_XP8_Sanity.call_duration_callDetails.getText().split(" ");
			int duration = Integer.parseInt(strings[0]);
			System.out.println(duration);
			boolean check2 = duration>=0;
			System.out.println(check2);
			if (check1&&check2) {
				check=true;
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Number found in the Call Details and call duration is Displayed.");
				sf.assertTrue(check, "TestCase Valiation is PASS");
			} else {

				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO, "Number incorrect in the Call Details OR call duration is NOT Displayed properly.");
				sf.fail();
			}
			minWait();
			clickBtn(Locators_XP8_Sanity.call_detail_delete_menu_item);
			minWait();
		} catch (NumberFormatException e) {
			sf.fail();
		}	
		sf.assertAll();
	}

	public void navigateprogrammablekey() throws InterruptedException, IOException {
		try {
			minWait();
			scrollToText("Programmable Keys");
			minWait();
		}catch (NoSuchElementException e) {
			APP_LOGS.warn("Error: Application not found.");
			e.printStackTrace();
		}
	}

	public boolean scrollTo(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */

		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public boolean scrollToText(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public boolean scrollToAndEnterText(String scrollTo,String stringToEnter) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ scrollTo +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).sendKeys(stringToEnter);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public boolean scrollToTextContains(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().textContains(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public boolean clickBtnWithText(String text) {
		boolean check = false;
		try {//android.widget.Button
			String clickable = "//*[@text=\'";
			String textElement = text+"\']";
			System.out.println(clickable+textElement);
			aDriver.findElementByXPath(clickable+textElement).click();
			//aDriver.
			//aDriver.findElementByAndroidUIAutomator("new UiSelector().childSelector()(new UiSelector().childSelector(new UiSelector().className(\"android.widget.TextView\").text(\"Clock\")))").click();
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public void press_PTT_Key() throws InterruptedException, IOException {
		/*
		 * Long press event of PTT key is fired.
		 */
		minWait();//"cmd /c  \
		Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent --longpress KEYCODE_PTT");
		customWait(5000);
	}

	public void validate_ProgrammableKeySummaryText() throws InterruptedException {
		/*
		 * Retrieves programmable key summary text.
		 */
		minWait();
		try {
			if(isElementExist(Locators_XP8_Sanity.summaryText)){
				APP_LOGS.info("Programmable key summary is displayed successfully"); 
				String getSummaryText = Locators_XP8_Sanity.summaryText.getText();
				APP_LOGS.info("App assigned to programmable key is: "+getSummaryText);
				minWait();
				System.out.println(getSummaryText);
				if (!getSummaryText.equalsIgnoreCase("Phone")) {
					clickBtnWithText("Select PTT Key app");
					minWait();
					scrollToText("Phone");
					minWait();
					clickBtn(Locators_XP8_Sanity.OK);
					minWait();
				}
				check=true;
				softAssert_true(check, "Programmable key is assigned to Phone app"); 
			}        
		} catch (NoSuchElementException e) {   
			e.printStackTrace();
			APP_LOGS.info("Programmable key is not assigned to Phone app");
			softAssert_false();
		}
	}

	public void precondition() throws InterruptedException {

		try {
			minWait();
			scrollToText("Display");
			minWait();
			scrollToText("Sleep");
			minWait();
			clickBtn(Locators_XP8_Sanity.thirtyMin_Sleep);
			minWait();
			scrollToText("When device is rotated");
			minWait();
			clickBtn(Locators_XP8_Sanity.stayInPortritView);
			minWait();
		} catch (Exception e) {
		}
	}

	public boolean isElementExistWithText(String text) {
		boolean isPresent = false;
		String element = "//android.widget.TextView[@text=\'";
		String elementtext = (text+"\']");
		try {
			WebElement webelement = aDriver.findElementByXPath(element+elementtext);
			// isPresent = e.isDisplayed();
			isPresent = true;

		} catch (NoSuchElementException s) {
			isPresent = false;   
		} catch (NullPointerException npe) {
			isPresent = false;
		}
		return isPresent;
	}

	public void setAlarmUsingGoogle() throws InterruptedException {
		/*
		 * This Method sets the alarm using Google 
		 * 
		 */
		boolean check = false;

		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		minWait();
		if (isElementExist(Locators_XP8_Sanity.GoogleSearch_HomePage)) {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.GoogleSearch_HomePage, "set alarm 1 minute from now");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			if (check = isElementExistWithText("Alarm")) {
				softAssert_true(check, "Alarm is set");
				System.out.println("Alarm text is present");
				APP_LOGS.info("Alarm is set using google");
			} 
			else {
				softAssert_false();
				APP_LOGS.info("Alarm is not set");
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		}
		else {
			APP_LOGS.info("Google search is not found");
			Assert.fail();
			test.log(LogStatus.SKIP,"Test case is skipped since Alarm couldnt be set");
		}

	}

	public void validateAlarmRing() throws InterruptedException {
		boolean check = false;
		boolean check1 = false;
		boolean check2 = false;

		SoftAssert ringAlarm = new SoftAssert();
		WebDriverWait wait = new WebDriverWait(aDriver, 100);
		try { 
			wait.until(ExpectedConditions.visibilityOf(Locators_XP8_Sanity.dismissAlarmHeadsUp));
			//wait.until(a)
			//aDriver.
			clickBtn(Locators_XP8_Sanity.alarmHeadsUp);
			minWait();
			check = true;
			ringAlarm.assertTrue(check, "Alarm is ringing");
			if (isElementExist(Locators_XP8_Sanity.alarmRinging)) {
				Locators_XP8_Sanity.alarmRinging.swipe(SwipeElementDirection.RIGHT, 3000);
				minWait();
				check1 = true;
				APP_LOGS.info("Alarm ringing page is displayed");
				ringAlarm.assertTrue(check1, "Alarm ringing page is displayed");
			}
			else {
				APP_LOGS.info("Alarm ringing page is not displayed");
				ringAlarm.fail();
			}

		}
		catch (Exception e) {
			ringAlarm.fail();
		}
	}

	public void setDataSaver_On() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOptions);
			minWait();
			scrollToText("Settings");
			minWait();
			scrollToText("Data Saver");
			minWait();
			clickBtn(Locators_XP8_Sanity.switch_Off_State);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void add_Contact_ADB_Command(String contctName, String phoneNum) throws InterruptedException, IOException {

		try {
			System.out.println("Inside ADB Command............");
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.INSERT -t vnd.android.cursor.dir/contact -e name '"+contctName+"' -e phone "+phoneNum);
			minWait();
			clickBtn(Locators_XP8_Sanity.SAVE);
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 936 84");
			customWait(2000);
			clearAllow();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void google_Search(String searchText) throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			launch_APP(Locators_XP8_Sanity.google);
			if(isElementExist(Locators_XP8_Sanity.google_NothanksNotification)) {
				clickBtn(Locators_XP8_Sanity.google_NothanksNotification);
			}
			
			/*clickBtn(Locators_XP8_Sanity.googleSearch_HomeScreen);
			minWait();
			clickBtn(Locators_XP8_Sanity.NO_THANKS);
			minWait();*/
			clickBtn(Locators_XP8_Sanity.Google_search_field);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.Search_Google, searchText);
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
		} catch (Exception e) {
		}
	}


	//================================================================================================================================
	//================================================= ANdroid O Methods ============================================================
	//================================================================================================================================

	public void ON_Switch(String switch_To_ON) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/../..//*[@text='OFF']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void OFF_Switch(String switch_To_OFF) {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_OFF+"')]/../..//*[@text='ON']").click();
			minWait();
			if(isElementExist(Locators_XP8_Sanity.OK)) {
				clickBtn(Locators_XP8_Sanity.OK);
			}
		} catch (Exception e) {
		}
	}

	public void wiFi_OFF() {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'Wi')]/../../..//*[@text='ON']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void wiFi_ON() {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'Wi')]/../../..//*[@text='OFF']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void BT_OFF() {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'Bluetooth')]/../../..//*[@text='ON']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void BT_ON() {
		try {
			minWait();
			aDriver.findElementByXPath("//*[contains(@text,'Bluetooth')]/../../..//*[@text='OFF']").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void precondition_O() throws InterruptedException {

		try {
			minWait();
			scrollToText("Display");
			minWait();
			clickBtn(Locators_XP8_Sanity.down_Triangle);
			minWait();
			scrollToText("Sleep");
			minWait();
			clickBtn(Locators_XP8_Sanity.thirtyMin_Sleep);
			minWait();
			OFF_Switch("Auto-rotate screen");
			minWait();
		} catch (Exception e) {
		}
	}

	public void clickOn_Networks_and_Internet() {
		try {
			minWait();
			scrollToText("Network & Internet");
			minWait();
		} catch (Exception e) {
		}
	}

	public void clear_Call_History_O() throws InterruptedException, IOException {
		/* 
		 * This Method will Clear all the Call History from Device.
		 */		
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.call_History);
			minWait();
			if (!isElementExist(Locators_XP8_Sanity.call_Log_Empty)) {				
				clickBtn(Locators_XP8_Sanity.moreOptions);
				minWait();
				clickBtn(Locators_XP8_Sanity.callHistory_MoreOptions);
				minWait();
				clickBtn(Locators_XP8_Sanity.moreOptions_CallHistory);
				minWait();
				clickBtn(Locators_XP8_Sanity.clear_CallHistory);
				minWait();
				clickBtn(Locators_XP8_Sanity.OK_1);
				customWait(2000);
				//clickBtn(Locators_XP8_Sanity.navigateUp);
				//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 0 72");
				minWait();
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clearRecentApps_O() throws InterruptedException, IOException {		
		// For Android O.
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(2000);
			if(isElementExist(Locators_BaseUtil.no_Recent_Items)) {
				APP_LOGS.info("No Recent Applications Present");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
			}else {
				long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(1L, TimeUnit.MINUTES);
				while( System.nanoTime() < endTime) {
					if(isElementExist(Locators_BaseUtil.clear_all)) {					
						Locators_BaseUtil.clear_all.click();
						break;
					}
					minWait();
					aDriver.swipe(600, 300, 600, 1400, 500);
					minWait();						 					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void clearSMSPermissions_O() throws InterruptedException {
		try {	
			customWait(2000);
			clickBtn(Locators_XP8_Sanity.StartMessaging);
			minWait();
			clickBtn(Locators_XP8_Sanity.skipProvisioning);
			minWait();
		} catch (Exception e) {	
			e.getMessage();
		}
	}

	public void navigateTo_NewSMS_O() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_XP8_Sanity.add_NewSMS_O);
			minWait();
		} catch (NoSuchElementException e) {		 
			e.printStackTrace();
		}
	}

	public void enter_Num_ToField_O(String number) throws InterruptedException {
		/* Method is used to Enter Number into TO Field. */
		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.TO_Field_O, number);
			customWait(2000);
			//			clickBtn(Locators_XP8_Sanity.contactPicker);
			//			customWait(2000);			
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void enterText_MessageField_O(String message) throws InterruptedException {
		/* Methos is Used enter Message into Message Field. */
		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.messageField_O, message);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void create_NewSMS_O(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */
		navigateTo_NewSMS_O();
		try {
			enter_Num_ToField_O(number);
			enterText_MessageField_O(message);
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_Send_O() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(Locators_XP8_Sanity.send_Icon_O);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validate_SentMessage_O() throws InterruptedException {
		/* To validate the Sent Message. */
		SoftAssert sf = new SoftAssert();
		WebDriverWait wait  =new WebDriverWait(aDriver, 60);
		wait.until(ExpectedConditions.visibilityOf(Locators_XP8_Sanity.Delivered));
		try {
			if(isElementExist(Locators_XP8_Sanity.Delivered)||isElementExist(Locators_XP8_Sanity.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "SMS or MMS Sent Successfully.");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL,"Message didn't sent.");
			}
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void delete_SMS_O() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.firstSMS_InList_O);
			minWait();
			clickBtn(Locators_XP8_Sanity.moreOption_O);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_Messages);
			minWait();
			clickBtn(Locators_XP8_Sanity.ALL_Msg);
			minWait();
			clickBtn(Locators_XP8_Sanity.delete_Btn);
			minWait();
			clickBtn(Locators_XP8_Sanity.Delete);
			minWait();
			test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void recieve_Call_PrimaryDev_O() throws IOException, InterruptedException {

		try {
			for(int j=1;j<=100;j++){
				Process child = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");					
					child = Runtime.getRuntime().exec("adb -s "+ p_Id+" shell service call telecom 29");
				} else if(p_b_No.contains("5SA.")) {
					System.out.println("XP5");
					child=Runtime.getRuntime().exec("adb -s "+p_Id+" shell service call telecom 28");
				}
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				String  value=in.readLine();
				System.out.println(value);
				if(value.contains("00000001")) {
					System.out.println("Phone is ringing so accepting call.");
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
					break;
				} else {
					continue;
				}
			}
		}catch(Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
	}

	public void delete_All_SMS_O() throws InterruptedException {
		try {
			long endTime = System.nanoTime() + TimeUnit.NANOSECONDS.convert(3L, TimeUnit.MINUTES);
			while (System.nanoTime() < endTime) {
				if (isElementExist(Locators_XP8_Sanity.firstSMS_InList1)) {
					minWait();
					clickBtn(Locators_XP8_Sanity.firstSMS_InList1);
					minWait();
					clickBtn(Locators_XP8_Sanity.moreOptions);
					minWait();
					clickBtn(Locators_XP8_Sanity.delete_moreOption);
					minWait();
					clickBtn(Locators_XP8_Sanity.delete_Confirm);
					minWait();
				}else {
					break;
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void reject_Call_With_SMS_O(String message) throws InterruptedException {

		try {
			customWait(5000);
			//clickBtn(Locators_XP8_Sanity.incoming_Call);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 660 160");
			customWait(3000);
			aDriver.swipe(80, 1840, 300, 600, 750);
			customWait(2000);
			clickBtn(Locators_XP8_Sanity.write_Your_Own);
			minWait();
			//enterTextToInputField(Locators_XP8_Sanity.msg_popup, message);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+message);
			minWait();
			clickBtn(Locators_XP8_Sanity.SEND);
			minWait();
		} catch (Exception e) {
		}		
	}

	public void validate_Call_Details_and_Delete_O(String expectedNum) throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {			
			minWait(); 
			clickBtn(Locators_XP8_Sanity.call_location_and_date_O);
			minWait();
			clickBtn(Locators_XP8_Sanity.call_Details);
			minWait();
			String numIn_Details = Locators_XP8_Sanity.caller_name_callDetails_O.getText().replaceAll("[^0-9+]", "");
			boolean check1 = numIn_Details.contains(expectedNum);
			String[] strings = Locators_XP8_Sanity.call_duration_callDetails_O.getText().split("s");
			int duration = Integer.parseInt(strings[0]);
			boolean check2 = duration>0;
			if (check1&&check2) {
				check=true;
				test.log(LogStatus.PASS, "Test case Status is Pass.");
				test.log(LogStatus.INFO, "Number found in the Call Details and call duration is Displayed.");
				sf.assertTrue(check, "TestCase Valiation is PASS");
			} else {

				test.log(LogStatus.FAIL,"Test Case Status is Fail.");
				test.log(LogStatus.INFO, "Number incorrect in the Call Details OR call duration is NOT Displayed properly.");
				sf.fail();
			}
			minWait();
			clickBtn(Locators_XP8_Sanity.call_detail_delete_menu_item_O);
			minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 0 72");
		} catch (NumberFormatException e) {
			sf.fail();
		}	
		sf.assertAll();
	}

	public void setDefaultAccount_Contact_O() throws InterruptedException, IOException {

		try {
			clickBtn(Locators_XP8_Sanity.open_Navigation_Drawer);
			minWait();
			clickBtn(Locators_XP8_Sanity.settings_Contact);
			minWait();
			clickBtn(Locators_XP8_Sanity.defaultAccount_NewContacts);
			minWait();
			clickBtn(Locators_XP8_Sanity.PHONE_RadioBtn);
			minWait();
			//clickBtn(Locators_XP8_Sanity_ATT.navigateUp);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent KEYCODE_BACK");
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}

	public void addContactToMsg_FromContacts_O(String contactName) throws InterruptedException {
		/* To add Contact to To Field From Reciepents. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.addContact_Msg_O);
			minWait();
			clickBtn(Locators_XP8_Sanity.searchContacts);
			customWait(2000);
			enterTextToInputField(Locators_XP8_Sanity.search_contacts, contactName);
			minWait();
			clickBtn(Locators_XP8_Sanity.firstContact_OnSearch);
			customWait(2000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void deleteContact_O(String contactName) throws InterruptedException, IOException {

		try {
			customWait(3000);
			clickBtn(Locators_XP8_Sanity.searchContacts);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.search_contacts, contactName);
			minWait();
			if (isElementExist(Locators_XP8_Sanity.no_Contacts)) {
				APP_LOGS.info("No Contact of Same Name"); 
				System.out.println("No Contact of Same Name");
				clickBtn(Locators_XP8_Sanity.backButton);
				minWait();
			} else {
				clickBtn(Locators_XP8_Sanity.searched_Contact);
				minWait();
				clearAllow();
				clickBtn(Locators_XP8_Sanity.moreOptions_Contact);
				minWait();
				clickBtn(Locators_XP8_Sanity.delete_moreOption);
				minWait();
				clickBtn(Locators_XP8_Sanity.delete_Confirm);
				minWait(); 
				clickBtn(Locators_XP8_Sanity.backButton);
				minWait();
				APP_LOGS.info("Contact deleted Successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}

	public void add_Contact_ADB_Command_O(String contctName, String phoneNum) throws InterruptedException, IOException {

		try {
			System.out.println("Inside ADB Command............");
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.INSERT -t vnd.android.cursor.dir/contact -e name '"+contctName+"' -e phone "+phoneNum);
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 888 84");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			clearAllow();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void validate_RecievedMessage_O() throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */

		SoftAssert sf = new SoftAssert();
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@resource-id,'com.verizon.messaging.vzmsgs:id/from')]")));
		customWait(8000);
		clickBtn(Locators_XP8_Sanity.firstSMS_InList_O);
		try {
			if(isElementExist(Locators_XP8_Sanity.unread_Conv_Messages)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully.");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL,"Message didn't Recieved.");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void capturePic_MMS_O(){
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.button_Attach_SMS);
			minWait();
			clickBtn(Locators_XP8_Sanity.camera_MMS);
			customWait(2000);
			clickBtn(Locators_XP8_Sanity.cameraCapture_Icon);
			minWait();
			clickBtn(Locators_XP8_Sanity.send_Cam_SMS);
			customWait(3000);
		} catch (Exception e) {

		}
	}

	public void add_NewContact_withAllFields_O(String contactName, String lastName,String phoneticlastName,
			String phoneticmiddleName,String phoneticfirstName, String nickName,String company, String title,
			String phone,String SIP, String email,String address, String IM,String webSite,String relationship, String notes) {

		try {
			clickBtn(Locators_XP8_Sanity.add_ContactIcon_O);
			minWait();
			clickBtn(Locators_XP8_Sanity.contact_SavingTo);
			minWait();
			clickBtn(Locators_XP8_Sanity.savingTo_Phone);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			clickBtn(Locators_XP8_Sanity.more_Fields);
			minWait();
			scrollToAndEnterText("First name", contactName);
			minWait();
			scrollToAndEnterText("Last name", lastName);
			minWait();
			scrollToAndEnterText("Phonetic last name", phoneticlastName);
			minWait();
			scrollToAndEnterText("Phonetic middle name", phoneticmiddleName);
			minWait();
			scrollToAndEnterText("Phonetic first name", phoneticfirstName);
			minWait();
			scrollToAndEnterText("Nickname", nickName);
			minWait();
			scrollToAndEnterText("Company", company);
			minWait();
			scrollToAndEnterText("Title", title);
			minWait();
			scrollToAndEnterText("Phone", phone);
			minWait();
			scrollToAndEnterText("SIP", SIP);
			minWait();
			scrollToAndEnterText("Email", email);
			minWait();
			scrollToAndEnterText("Address", address);
			minWait();
			scrollToAndEnterText("IM", IM);
			minWait();
			scrollToAndEnterText("Website", webSite);
			minWait();
			scrollToAndEnterText("Relationship", relationship);
			minWait();
			scrollToAndEnterText("Notes", notes);
			minWait();		
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input tap 888 84");
			minWait();
			//			clickBtn(Locators_XP8_Sanity.SAVE);
			//			minWait();
			clearAllow();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void search_Contact_O(String contactName) throws InterruptedException {
		/* To Search for the Required Contact. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.searchContacts);
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.search_contacts, contactName);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}		
	}


	public void validate_Added_Contact_O(String expectedContactName) throws InterruptedException, IOException {
		/*   To validate the Added contact.  */
		SoftAssert sf = new SoftAssert();
		try {			
			search_Contact_O(expectedContactName);
			String getContactName = Locators_XP8_Sanity.searched_Contact.getText();
			minWait();

			if(getContactName.contains(expectedContactName)) {
				check=true;
				APP_LOGS.info("New Contact Displayed Succceccfully in Contact List");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Contact added to the device Successfully");
			} else {
				APP_LOGS.info("Added Contact NOT present in Contact List");
				sf.fail();
				test.log(LogStatus.FAIL,"Contact didn't added.");
			}
			minWait(); 
			clickBtn(Locators_XP8_Sanity.backButton);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}
	
	public void validate_deleted_Contact(String expectedContactName) throws InterruptedException, IOException {
		/*   To validate the Added contact.  */
		SoftAssert sf = new SoftAssert();
		try {			
			search_Contact_O(expectedContactName);
			if(isElementExist(Locators_XP8_Sanity.searched_Contact)) {
				String getContactName = Locators_XP8_Sanity.searched_Contact.getText();
				minWait();

				if(getContactName.contains(expectedContactName)) {
					check=true;
					APP_LOGS.info("Failed - > in Deleting the Contact");
					sf.fail();
					test.log(LogStatus.FAIL,"Contact didn't deleted");
					
				} 
			}
			else {
				APP_LOGS.info("Contact Deleted Succceccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Contact Deleted Succceccfully");
			}
			
			minWait(); 
			clickBtn(Locators_XP8_Sanity.backButton);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public boolean clickOnText(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiSelector()";
			String textElement = ".text(\""+ text +"\")";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public void swipe_QuickPanel_SecondPage() throws InterruptedException {
		try {
			minWait();
			aDriver.swipe(860, 660, 140, 660, 750);
			minWait();
		} catch (Exception e) {
		}		
	}

	public void validate_BatteryCharging_Or_Full_O() throws InterruptedException, IOException {
		/*
		 * This Method is to Validate Battery Charging Status or Full Message in Battery window.
		 */
		SoftAssert sf = new SoftAssert();
		try {			
			minWait();
			check=isElementExist(Locators_XP8_Sanity.charging_over_USB_O)||isElementExist(Locators_XP8_Sanity.battery_Full_O);
			if(check) {
				APP_LOGS.info("Battery is 'Charging OR Full' is Displayed");
				sf.assertTrue(check, "Test case Valiation is PASS");
				test.log(LogStatus.PASS, "Battery is 'Charging OR Full' is Displayed");
			}else {
				APP_LOGS.info("Battery is Charging OR Full is NOT Displayed");
				sf.fail();
				test.log(LogStatus.FAIL,"Battery is Charging OR Full is NOT Displayed");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void validate_DateTime_And_Timezone_O() throws InterruptedException {
		/*
		 * This Method is to validate Time, Date and Timezone.
		 * Precondition : Automatic date&time and Automatic Time zone are Enabled.
		 * 				  User should be in Date & Time window.
		 */	
		//		try {
		//			minWait();
		//			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Date & time\"))").click();
		//			minWait();
		//		} catch (NoSuchElementException e) {			 
		//			e.printStackTrace();
		//		}

		SoftAssert sf = new SoftAssert();
		try {
			boolean check1=false;
			boolean check2=false;
			customWait(3000);
			String date = Locators_XP8_Sanity.date_in_SetDate.getText();
			String time = Locators_XP8_Sanity.time_in_SetTime.getText();
			String timezone = Locators_XP8_Sanity.timeZone.getText();
			minWait();
			System.out.println(date+" "+time+" "+timezone);

			Formatter fmt = new Formatter();
			Calendar cal = Calendar.getInstance();
			//actual arguments for below method("%tB %td %tY", cal, cal, cal) and "," is concatenated.
			fmt.format("%tB %te"+","+" %tY", cal, cal, cal);
			System.out.println(fmt);
			String date1 = fmt.toString();
			check1=date.equalsIgnoreCase(date1);

			if(!check1) {
				Formatter fmt1 = new Formatter();
				fmt1.format("%te %tB %tY", cal, cal, cal);
				System.out.println(fmt1);
				date1 = fmt1.toString();
				check1=date.equalsIgnoreCase(date1);
			}

			java.util.Date today = new java.util.Date();
			Time fmt1 = new java.sql.Time(today.getTime());
			System.out.println(fmt1);
			String time1 = fmt1.toString();
			check2=time1.contains(time);

			if(check1&&check2) {
				APP_LOGS.info("Displayed Date And Time in the Device is Correct.");
				check=true;
				sf.assertTrue(check, "Test Case Valiadtion is PASS");
				test.log(LogStatus.PASS, "Displayed Date And Time in the Device is Correct.");
			} else {
				APP_LOGS.info("Displayed Date And Time is Not Correct");
				sf.fail();
				test.log(LogStatus.FAIL,"Displayed Date And Time is Not Correct");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		sf.assertAll();
	}

	public void navigateTo_APN_O() throws InterruptedException, IOException {
		/* This Method will Navigate to Cellular Netwotks option. */ 
		minWait();
		try {
			scrollToText("Network & Internet");
			minWait();
			scrollToText("Mobile network");
			minWait();
			clickBtn(Locators_XP8_Sanity.down_Triangle);
			minWait();
			aDriver.swipe(770, 1780, 800, 700, 1000);
			minWait();
			clickBtn(Locators_XP8_Sanity.accessPointNames);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	public void changeTo_Front_Back_Cam_O() {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.front_back_switcher);
			minWait();
		} catch (Exception e) {
		}
	}

	public void remove_GoogleAcccount_O() {
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.google_Account);
			minWait();
			clickBtn(Locators_XP8_Sanity.REMOVE_ACCOUNT);
			minWait();
			clickBtn(Locators_XP8_Sanity.REMOVE_ACCOUNT);
			customWait(3000);
		} catch (Exception e) {

		}
	}

	public void add_Picture_O() throws InterruptedException {

		try {
			if (p_b_No.contains("-10.")||p_b_No.contains("-30.")||r_b_No.contains("-00.")) {
				clickAttachment();
				minWait();
				clickBtn(Locators_XP8_Sanity.capturePicture_MMS);
				minWait();
				clearAllow();
				clickBtn(Locators_XP8_Sanity.capturePicture);
				customWait(10000);
				clickBtn(Locators_XP8_Sanity.done_1);
				minWait();
			} else if(p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")){
				clickAttachment();
				minWait();
				clickBtn(Locators_XP8_Sanity.capture_pictures_or_video);
				minWait();
				clearAllow();
				clickBtn(Locators_XP8_Sanity.capture_pictures_or_video);
				minWait();
				clickBtn(Locators_XP8_Sanity.take_picture_MMS);
				customWait(10000);			
			}
		} catch (Exception e) {
		}		
	}

	public void clickOn_CallBtn_O() throws InterruptedException {
		/*This method is to click on Dialer icon in the Call Log Tab.*/
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.call_History);
			minWait();
			clickBtn(Locators_XP8_Sanity.dialBtn_Call_Log_O);
			minWait();
			clickBtn(Locators_XP8_Sanity.dialBtn_Call_Log);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void closeAllTabs_O() throws InterruptedException {
		/* To close all tha Tabs in Chrome Browser. */
		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.switcherButton_Chrome_O);
			minWait();
			clickBtn(Locators_XP8_Sanity.menuButton_Chrome_O);
			minWait();
			clickBtn(Locators_XP8_Sanity.closeAllTabs_Chrome_O);
			minWait();
		} catch (NoSuchElementException e) {		 
			e.printStackTrace();
		}
	}

	public void validate_And_BrowseIn_Chrome_O(String url, AndroidElement autoSuggestion, AndroidElement expectedElement, AndroidElement expectedElement1) throws InterruptedException, IOException {
		/* 
		 * Validates MobileData enabality by launching the Chrome & browsing google page.
		 */
		SoftAssert  sf = new SoftAssert();
		try {
			customWait(2000);
			enterTextToInputField(Locators_XP8_Sanity.urlBar_Chrome, url);
			customWait(4000);
			clickBtn(autoSuggestion);
			customWait(12000);
			try {
				if(expectedElement.isDisplayed()) {
					check=true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					sf.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "URL loaded successfully : "+url);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					sf.fail();
					test.log(LogStatus.FAIL,"Test Case Status is Fail.");
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			} catch (Exception e) {
				if(expectedElement1.isDisplayed()) {
					check=true;
					APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
					sf.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "Test case Status is Pass.");
					test.log(LogStatus.INFO, "URL loaded successfully : "+url);
				} else {
					APP_LOGS.info("Suggeted URL page didn't get Loaded");
					sf.fail();
					test.log(LogStatus.FAIL,"Test Case Status is Fail.");
					test.log(LogStatus.INFO, "URL didn't load.");
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
		closeAllTabs_O();	
		sf.assertAll();
	}	

	public void validate_MobileData_Disable_O() throws InterruptedException {
		/* 
		 * Validates MobileData disabality by launching the Chrome.
		 */
		SoftAssert sf = new SoftAssert();
		//Set<String> handles = aDriver.getContextHandles();
		//for (String s:handles) {
		//System.out.println(s);
		//}
		//aDriver.context((String)handles.toArray()[1]);
		//enterTextToInputField(Locators_XP8_Sanity.urlBar_Chrome, "www.google.co.in");
		//customWait(4000);
		//clickBtn(Locators_XP8_Sanity.urlBar_Chrome);
		customWait(9000);
		try {			
			//customWait(3000);
			if(isElementExist(Locators_XP8_Sanity.Alert_Popup) || !(isElementExist(Locators_XP8_Sanity.google_Logo))) {
				if(isElementExist(Locators_XP8_Sanity.OK_2)) {
					clickBtn(Locators_XP8_Sanity.OK_2);
				}
				check=true;
				APP_LOGS.info("MobbileData Disabled Successfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "TestCase Status is PASS.");
				test.log(LogStatus.INFO, "Disable 'Data' OR 'Wi-Fi'  validation is PASS.");			
			} else {
				APP_LOGS.info("Mobiledata is NOT Disabled");
				sf.fail();
				test.log(LogStatus.FAIL,"Disable Data OR Wi-Fi validation is FAIL.");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}		
		//		clickBtn(Locators_XP8_Sanity.OK_2);
		//		closeAllTabs();
		sf.assertAll();

	}

	public void navigate_Create_Label() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.open_Navigation_Drawer);
			minWait();
			clickBtn(Locators_XP8_Sanity.create_Label);
			minWait();
			clickBtn(Locators_XP8_Sanity.savingTo_Phone);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	public void create_Label(String labelname) throws InterruptedException {

		try {
			minWait();
			enterTextToInputField(Locators_XP8_Sanity.label_Name, labelname);
			minWait();
			clickBtn(Locators_XP8_Sanity.OK_1);
			customWait(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteLabel_IfPresent(String labelName) throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.open_Navigation_Drawer);
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\""+labelName+"\")").click();
			minWait();
			delete_Label();
		} catch (WebDriverException e) {
			e.printStackTrace();
		}		
	}

	public void pressKeyCode(int keyNumber) throws InterruptedException {

		minWait();
		aDriver.pressKeyCode(keyNumber); 

	}



	//This method will launch app only in primary device	
	public void only_For_CallPerformance_clickOnAppList_Primary() throws InterruptedException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 3");
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);		
			customWait(2000);			
			clickBtn(Locators_XP8_Sanity.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	//This method will launch app only in primary device
	public void launch_APP_Only_For_CallPerformance_For_Primary_Device(AndroidElement appToClick) throws InterruptedException {
		try {
			fetch_Devices_Details();
			only_For_CallPerformance_clickOnAppList_Primary();
			for (int i = 0; i < 6; i++) {
				if (isElementExist(appToClick)) {

					clickBtn(appToClick);
					minWait();
					break;
				} else {
					scroll();
					minWait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	//This launch app method is used only for call 3G-perfomrance module 	
	public void launch_APP_Only_For_CallPerformance(AndroidElement appToClick) throws InterruptedException {
		try {
			fetch_Devices_Details();
			only_For_CallPerformance_clickOnAppList();
			for (int i = 0; i < 6; i++) {
				if (isElementExist(appToClick)) {

					clickBtn(appToClick);
					minWait();
					break;
				} else {
					scroll();
					minWait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	//This  method is used only for call 3G-perfomrance module for making call from MT or REF device 		
	public void only_For_CallPerformance_clickOnAppList() throws InterruptedException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
			//aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);		
			customWait(2000);			
			clickBtn(Locators_XP8_Sanity.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void remove_GoogleAcccount_Orio() {
		//remove added google Account if any 
		try {
		//	scrollToText("Users & accounts");
//			clickOnAccounts();
			minWait();
			if(isElementExist(Locators_XP8_Sanity.connectedAccount)) {
				System.out.println("Account is present");
				minWait();
				clickBtn(Locators_XP8_Sanity.connectedAccount);
				minWait();
				clickBtn(Locators_XP8_Sanity.REMOVE_ACCOUNT);
				minWait();
				clickBtn(Locators_XP8_Sanity.REMOVE_ACCOUNT);
				customWait(3000);
			}
			else {
				System.out.println("No Google account present");
			}


		} catch (Exception e) {

		}
	}
	
	public void navigateTo_AddGoogleAccount_Orio() {
		//navigate to settings option Add google Account
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			scrollToText("Users & accounts");
			clickBtn(Locators_XP8_Sanity.add_Account);
			minWait();
			clickBtn(Locators_XP8_Sanity.google_Account);
			customWait(1600);
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
		} catch (Exception e) {

		}
	}
	






}

