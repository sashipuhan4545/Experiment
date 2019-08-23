package com.xp8.util;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.UUID;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import mx4j.tools.config.DefaultConfigurationBuilder.New;

public class New_SanityUtil extends BaseUtil {

	public static boolean check = false;
	public static ExtentReports extent;
	public static ExtentTest test;

	public SoftAssert sf1 = new SoftAssert();

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

/*	public boolean scrollToTextContains(String text) {
		
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 

		boolean check = false;
		try {
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable + textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (NoSuchElementException e) {
			return check;
		}
	}*/
	
	public boolean scrollText(String text) {
		/*
		Method used to scroll to an element in the scrollable view
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
		catch(Exception e) {
			return check;
			}
		}

	public boolean scrollToText(String text) {
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
		} catch (NoSuchElementException e) {
			return check;
		}
	}

	public boolean scrollToTextWithoutClick(String text) {
		/*
		 * Method used to select an element on the page by scrolling the Scroll
		 * View/List View
		 */

		boolean check = false;
		try {
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().text(\"" + text + "\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable + textElement);
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		} catch (NoSuchElementException e) {
			return check;
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

	public void launch_An_App(String pkg, String ack) {

		try {

			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			aDriver.startActivity(pkg, ack);
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->launch_An_App()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->launch_An_App()");
		}
	}

	public void dailCallUsingDailPad(String dailNum) throws InterruptedException, IOException {

		try {
			dailNumber(dailNum);
			if (!isElementExist(New_SanityLocators.turnOff_Airplane_PopUp)) {
				try {
					for (int j = 1; j <= 100; j++) {
						Process child = null;
						if (r_b_No.contains("8A.")) {
							child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 27");
						} else if (r_b_No.contains("5SA.")) {
							child = Runtime.getRuntime().exec("adb -s " + r_Id + " shell service call telecom 28");
						}
						InputStream inputStream = child.getInputStream();
						InputStreamReader isr = new InputStreamReader(inputStream);
						BufferedReader bf = new BufferedReader(isr);
						String value = bf.readLine();

						System.out.println(value);
						if (value.contains("00000001") || value.contains("ffffffff")) {
							System.out.println("Phone is ringing so accepting call.");
							Thread.sleep(5000);
							Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");
							break;
						} else {
							continue;
						}
					}
				} catch (Exception e) {
					Thread.sleep(2000);
					Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 5");
					Thread.sleep(2000);
				}
			}
			APP_LOGS.info("Number dailed is: " + dailNum);
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Selected Page is not displayed.");
			e.printStackTrace();
		}
	}

	public void dailNumber(String dailNum) throws IOException {
		try {
			minWait();
			clickBtn(New_SanityLocators.add_Call);
			minWait();
			clickBtn(New_SanityLocators.dailerPad);
			minWait();
			if (isElementExist(New_SanityLocators.Allow_Btn)) {
				clickBtn(New_SanityLocators.Allow_Btn);
			}
			clickBtn(New_SanityLocators.enterNumFiled);
			minWait();
			enterTextToInputField(New_SanityLocators.enterNumFiled, dailNum);
			minWait();
			// Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text "+dailNum);
			minWait();
			clickBtn(New_SanityLocators.dail);
			customWait(4000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->dailNumber()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->dailNumber()");
		}
	}
	
	public void dailNumberforEnggMenu(String dailNum) throws IOException {
		try {
			minWait();
			clickBtn(New_SanityLocators.dailerPad);
			if (isElementExist(New_SanityLocators.Allow_Btn)) {
				clickBtn(New_SanityLocators.Allow_Btn);
			}
			enterTextToInputField(New_SanityLocators.enterNumFiled, dailNum);	
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->dailNumberforEnggMenu()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->dailNumberforEnggMenu()");
		}
	}

	public void end_Call() throws InterruptedException {
		/*
		 * Method is to end Call. Precondition : User should initiate the Call to any
		 * Number.
		 */
		try {
			minWait();
			if (isElementExist(New_SanityLocators.end_Call)) {
				clickBtn(New_SanityLocators.end_Call);
				APP_LOGS.info("Call Ended");
			} else {
				APP_LOGS.info("End Call Button Doesn't exist on screen");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->end_Call()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->end_Call()");
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
			clickBtn(New_SanityLocators.call_History);
			customWait(3000);
			String callLogNum = New_SanityLocators.first_No_In_CallLog.getText().replaceAll("[^0-9+]", "");
			System.out.println("callLogNum " + callLogNum);
			System.out.println("expectedNum " + expectedNum);
			if (callLogNum.contains(expectedNum)) {
				check = true;
				APP_LOGS.info("Dailled Number Successfully Recorded in Call log");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Number Validated in the Call Logs.");
			} else {
				APP_LOGS.info("Dailled Number NOT Recorded in Call log");
				sf.fail();
				test.log(LogStatus.FAIL, "Number NOT found in Call Logs.");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Num_In_CallLog()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->validate_Num_In_CallLog()");
		}
	}

	public void clear_Call_History_O() throws InterruptedException, IOException {
		/*
		 * This Method will Clear all the Call History from Device.
		 */
		try {
			minWait();
			clickBtn(New_SanityLocators.call_History);
			minWait();
			if (!isElementExist(New_SanityLocators.call_Log_Empty)) {
				clickBtn(New_SanityLocators.moreOptions);
				minWait();
				clickBtn(New_SanityLocators.callHistory_MoreOptions);
				minWait();
				clickBtn(New_SanityLocators.moreOptions_CallHistory);
				minWait();
				clickBtn(New_SanityLocators.clear_CallHistory);
				minWait();
				clickBtn(New_SanityLocators.OK_1);
				customWait(2000);
				// clickBtn(New_SanityLocators.navigateUp);
				// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 0 72");
				minWait();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clear_Call_History_O()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clear_Call_History_O()");
		}
	}

	public void endCall_RefDevice() {
		try {
			minWait();
			Runtime.getRuntime().exec("adb -s " + r_Id + " shell input keyevent 6");
			Thread.sleep(1000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->endCall_RefDevice()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->endCall_RefDevice()");
		}
	}

	public void make_Call_from_RefDev() throws InterruptedException, IOException {

		try {
			minWait();
			Runtime.getRuntime()
					.exec("adb -s " + r_Id + " shell am start -a android.intent.action.CALL -d tel:" + pryNum);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->make_Call_from_RefDev()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->make_Call_from_RefDev()");
		}
	}

	public void recieve_Call_PrimaryDev_O() throws IOException, InterruptedException {

		try {
			for (int j = 1; j <= 100; j++) {
				Process child = null;
				if (p_b_No.contains("8A.")) {
					System.out.println("XP8");
					child = Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call telecom 29");
				} else if (p_b_No.contains("5SA.")) {
					System.out.println("XP5");
					child = Runtime.getRuntime().exec("adb -s " + p_Id + " shell service call telecom 28");
				}
				InputStream lsOut = child.getInputStream();
				InputStreamReader r = new InputStreamReader(lsOut);
				BufferedReader in = new BufferedReader(r);
				String value = in.readLine();
				System.out.println(value);
				if (value.contains("00000001")) {
					System.out.println("Phone is ringing so accepting call.");
					Runtime.getRuntime().exec("adb -s " + p_Id + " shell input keyevent 5");
					break;
				} else {
					continue;
				}
			}
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input keyevent 5");
			Thread.sleep(2000);
		}
	}

	public Process p;

	public String startAdbLog() throws AWTException, InterruptedException, IOException {

		Thread.sleep(1000);
		String fileName = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(fileName);
		String path = "src/test/resources/adbLogs/" + fileName + ".txt";
		try {
			Runtime.getRuntime().exec("adb -s " + p_Id + " logcat -c");
			Thread.sleep(1000);
			p = Runtime.getRuntime().exec("cmd /C \"adb -s " + p_Id + " logcat -v time>" + path);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	public void clearCameraPermission() throws InterruptedException {

		try {
			minWait();
			if (isElementExist(New_SanityLocators.allow_Permission)) {
				clickBtn(New_SanityLocators.allow_Permission);
				minWait();
			//	clickBtn(New_SanityLocators.OK);
				minWait();
			}
			Runtime.getRuntime().exec("adb -s" + p_Id + " shell input swipe 889 1414 889 367");
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearCameraPermission()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearCameraPermission()");
		}
	}

	public void clearCamera_cache() throws InterruptedException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s" + p_Id + " shell pm clear org.codeaurora.snapcam");
			customWait(3000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearCamera_cache()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearCamera_cache()");
		}
	}

	public void restore_DefaultStng() throws InterruptedException {
		try {
			minWait();
			clickBtn(New_SanityLocators.camera_MenuBtn);
			clickBtn(New_SanityLocators.camera_SettingBtn);
			scrollToTextClick("Restore defaults");
			clickBtn(New_SanityLocators.Allow_Btn);
			clickBackButton(1);
			minWait();

		} catch (Exception e) {
			e.printStackTrace();
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

	public void clickBackButton(int number) throws InterruptedException {
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			for (int i = 0; i < number; i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in clickBackButton() method");
			e.printStackTrace();
		}
	}

	public void clickOnCapture() throws InterruptedException {

		try {
			minWait();
			clickBtn(New_SanityLocators.capturePicture);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeTo_Front_Back_Cam_O() {
		try {
			minWait();
			clickBtn(New_SanityLocators.front_back_switcher);
			minWait();
		} catch (Exception e) {
		}
	}

	public void validate_ADB_Logs(String filename, String string1, String string2, String string3)
			throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(string1, filename);
		boolean check2 = searchString(string2, filename);
		boolean check3 = searchString(string3, filename);
		SoftAssert sf = new SoftAssert();
		if (check1 && check2 && check3) {
			check = true;
			sf.assertTrue(check, "TestCase Valiation is PASS");
			test.log(LogStatus.PASS, "Test case Status is Pass.");
			test.log(LogStatus.INFO, "Validated from ADB Logs : " + string1 + ", " + string2 + ", " + string3);
		} else {
			sf.fail();
			test.log(LogStatus.FAIL, "Test Case Status is Fail.");
			test.log(LogStatus.INFO, "Validation failed from ADB Logs.");
		}
		sf.assertAll();
	}

	public void validate_ADB_Logs(String filename, String string1, String string2) throws InterruptedException {

		validate_ADB_Logs(filename, string1, string2, "");

	}

	public void validate_ADB_Logs(String filename, String string1) throws InterruptedException {

		validate_ADB_Logs(filename, string1, "", "");
	}

	public void clickVideoButton() throws InterruptedException {

		try {
			minWait();
			clickBtn(New_SanityLocators.video_Button);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateCalcLaunch(SoftAssert sa) {
		boolean check1 = false;
		boolean check2 = false;
		if (isElementExist(New_SanityLocators.calc_add_btn)) {
			check1 = true;
			APP_LOGS.info("Calulator Number Zero Key is Present.");

		}
		if (isElementExist(New_SanityLocators.calc_Delete_btn)) {
			check2 = true;
			APP_LOGS.info("Calculator Delete Button is Present.");
		}

		if ((check1) && (check2)) {
			check = true;
			APP_LOGS.info("Calculator Launched validation is succesful.");
			sa.assertTrue(true, "Calculator Launched validation is succesful.");
			test.log(LogStatus.PASS, "Calculator Launched validation is succesful.");
			Assert.assertTrue(check);
		} else {
			APP_LOGS.info("Calculator Launched validation is unsuccesful.");
			sa.assertFalse(false, "Calculator Launched validation is unsuccesful.");
			test.log(LogStatus.FAIL, "Calculator Launched validation is unsuccesful.");
			Assert.fail();
		}
	}

	// This Method validating calculated value from calci with added value
	// Separately
	public void basicOperationwithoutdecimalpt(SoftAssert sa) throws InterruptedException {
		boolean check1 = false;
		boolean check2 = false;
		boolean check3 = false;
		boolean check4 = false;

		// Addition
		int num1 = firstclick();
		// Random first click
		minWait();
		New_SanityLocators.calc_add_btn.click();
		minWait();
		int num2 = secondclick();
		// random 2nd click
		minWait();
		New_SanityLocators.calc_Eql_btn.click();
		// Manually(Separately) adding 2 num
		int s = (num1 + num2);
		String currentNumberText1 = New_SanityLocators.calc_Edit_text_field.getText();
		minWait();
		clickBtn(New_SanityLocators.calc_Clear_btn);
		// System.out.println(currentNumberText1);
		String sum1 = String.valueOf(s);
		// System.out.println(sum1);
		if (currentNumberText1.equalsIgnoreCase(sum1)) {
			check1 = true;
			APP_LOGS.info("Pass: Addition of 2 numbers verified successfully.");
			sa.assertTrue(true, "Pass: Addition of 2 numbers verified successfully.");
			test.log(LogStatus.PASS, "Pass: Addition of 2 numbers verified successfully.");
			Assert.assertTrue(check1);
		} else {
			APP_LOGS.info("Failed: Addition of 2 numbers verified Unsuccessfully.");
			sa.assertTrue(true, "Failed: Addition of 2 numbers verified Unsuccessfully.");
			test.log(LogStatus.PASS, "Failed: Addition of 2 numbers verified Unsuccessfully.");
			Assert.assertFalse(check1);

		}

		// Subtraction

		int num3 = firstclick();
		minWait();
		New_SanityLocators.calc_sub_btn.click();
		minWait();
		int num4 = secondclick();
		minWait();
		New_SanityLocators.calc_Eql_btn.click();
		String currentNumberText2 = New_SanityLocators.calc_Edit_text_field.getText();
		New_SanityLocators.calc_Clear_btn.click();
		int s1 = (num3 - num4);
		String sum2 = String.valueOf(s1);
		if (currentNumberText2.equalsIgnoreCase(sum2)) {
			check2 = true;
			APP_LOGS.info("Pass:Substraction of 2 numbers verified successfully.");
			sa.assertTrue(true, "Pass:Substraction of 2 numbers verified successfully.");
			test.log(LogStatus.PASS, "Pass:Substraction of 2 numbers verified successfully.");
			Assert.assertTrue(check2);
		} else {
			APP_LOGS.info("Failed: Substraction of 2 numbers verified Unsuccessfully.");
			sa.assertTrue(true, "Failed: Substraction of 2 numbers verified Unsuccessfully.");
			test.log(LogStatus.PASS, "Failed: Substraction of 2 numbers verified Unsuccessfully.");
			Assert.assertFalse(check2);
		}

		// multiplication

		int num5 = firstclick();
		minWait();
		New_SanityLocators.calc_Mul_btn.click();
		minWait();
		int num6 = secondclick();
		minWait();
		New_SanityLocators.calc_Eql_btn.click();
		String currentNumberText3 = New_SanityLocators.calc_Edit_text_field.getText();
		// System.out.println(currentNumberText3);
		New_SanityLocators.calc_Clear_btn.click();
		// Manually(Separately) Mul 2 num
		int s3 = (num5) * (num6);
		String sum3 = String.valueOf(s3);
		// System.out.println(sum3);
		if (currentNumberText3.equalsIgnoreCase(sum3)) {
			check3 = true;
			APP_LOGS.info("Pass: Product of 2 numbers verified successfully.");
			sa.assertTrue(true, "Pass: Product of 2 numbers verified successfully.");
			test.log(LogStatus.PASS, "Pass:Product of 2 numbers verified successfully.");
			Assert.assertTrue(check3);
		} else {
			APP_LOGS.info("Failed: Product of 2 numbers verified Unsuccessfully.");
			sa.assertTrue(true, "Failed: Product of 2 numbers verified Unsuccessfully.");
			test.log(LogStatus.PASS, "Failed: Product of 2 numbers verified Unsuccessfully.");
			Assert.assertFalse(check3);
		}

		// Division
		double num7 = firstclick();
		minWait();
		New_SanityLocators.calc_div_btn.click();
		minWait();
		double num8 = secondclick();
		minWait();
		New_SanityLocators.calc_Eql_btn.click();
		String currentNumberText4 = New_SanityLocators.calc_Edit_text_field.getText();
		minWait();
		String New = currentNumberText4.substring(0, currentNumberText4.indexOf(".") + 1);
		New_SanityLocators.calc_Clear_btn.click();
		// System.out.println(New);
		// Manually(Separately) div 2 num
		double s4 = (num7 / num8);
		String sum4 = String.valueOf(s4);
		String str1 = sum4.substring(0, sum4.indexOf(".") + 1);
		// System.out.println(str1);
		if (New.equalsIgnoreCase(str1)) {
			check4 = true;
			APP_LOGS.info("Pass: Division of 2 numbers verified successfully.");
			sa.assertTrue(true, "Pass: Division of 2 numbers verified successfully.");
			test.log(LogStatus.PASS, "Pass: Division of 2 numbers verified successfully.");
			Assert.assertTrue(check4);
		} else {
			APP_LOGS.info("Failed: Division of 2 numbers verified Unsuccessfully.");
			sa.assertTrue(true, "Failed: Division of 2 numbers verified Unsuccessfully.");
			test.log(LogStatus.FAIL, "Failed: Division of 2 numbers verified Unsuccessfully.");
			Assert.assertFalse(check4);
		}

		customWait(2000);
	}

	// Generates A random click of any int number
	public static int firstclick() throws InterruptedException {
		int first = randonClickOnCalculatorKeyboard();
		// Log.i(Constants.TAG, "First click" + String.valueOf(first));

		switch (first) {
		case 1:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			break;
		case 2:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			break;
		case 3:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			break;
		case 4:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			break;
		case 5:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			break;
		case 6:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			break;
		case 7:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			break;
		case 8:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			break;
		case 9:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			break;
		case 10:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			break;
		}
		return first;
	}

	// Generates A random click of any int number
	public static int secondclick() throws InterruptedException {
		int second = randonClickOnCalculatorKeyboard();
		// Log.i(Constants.TAG, "First click" + String.valueOf(first));

		switch (second) {
		case 1:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_1);
			break;
		case 2:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_2);
			break;
		case 3:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_3);
			break;
		case 4:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_4);
			break;
		case 5:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_5);
			break;
		case 6:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_6);
			break;
		case 7:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_7);
			break;
		case 8:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_8);
			break;
		case 9:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_9);
			break;
		case 10:
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_0);
			break;
		}
		return second;
	}

	// generation of random numbers
	public static int randonClickOnCalculatorKeyboard() throws InterruptedException {
		Random rn = new Random();
		int answer = rn.nextInt(9) + 1;
		return answer;
	}

	public String nameOfCurrentImg() throws InterruptedException {

		try {
			minWait();
			clickBtn(New_SanityLocators.camera_previewThumb_btn);
			clickBtn(New_SanityLocators.camera_details_btn);
			String s1 = New_SanityLocators.camera_imgName.getText();
			System.out.println(s1);
			String s2 = extract_Numerics(s1);
			System.out.println(s2);

			String sub1 = s2.substring(1);

			String imgName = "IMG" + sub1;
			return imgName;

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearCameraPermission()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearCameraPermission()");
		}
		return "0";

	}

	public String extract_Numerics(String stringValue) {
		String numericValue = "";
		for (int i = 0; i < stringValue.length(); i++) {
			if ((stringValue.charAt(i) >= '0' && stringValue.charAt(i) <= '9') || stringValue.charAt(i) == '_')
				numericValue = numericValue + stringValue.charAt(i);
		}
		return numericValue;
	}

	public void validatePictureInFileManaager(SoftAssert sa, String fileName) {
		try {

			if (New_SanityLocators.Permission_AllowBtn.isDisplayed()) {
				clickBtn(New_SanityLocators.Permission_AllowBtn);
			}

			isElementExist(New_SanityLocators.FileMgr_DCIMFldr);
			isElementExist(New_SanityLocators.FileMgr_CameraFldr);

			boolean check1 = scrollToText(fileName);

			if (check1 == true) {

				APP_LOGS.info("File Manager launched Successfully, Camera images are accessible from File Manage");
				sa.assertTrue(true,
						"File Manager launched Successfully, Camera images are accessible from File Manage");
				test.log(LogStatus.PASS,
						"File Manager launched Successfully, Camera images are accessible from File Manage");

			} else {

				APP_LOGS.info("File Manager launched Failed,Camera Images are not Accessible");
				sa.fail();
				test.log(LogStatus.FAIL, "File Manager launched Failed,Camera Images are not Accessible");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validatePictureInFileManaager()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validatePictureInFileManaager()");
		}
	}

	public void turnOnAutomatic_date_Time() throws InterruptedException {

		try {
			minWait();
			scrollToText("Date & time");
			if (New_SanityLocators.systemStng_data_timeBtn.getText().equalsIgnoreCase("OFF")) {
				clickBtn(New_SanityLocators.systemStng_data_timeBtn);
			}
			if (New_SanityLocators.systemStng_time_ZoneBtn.getText().equalsIgnoreCase("OFF")) {
				clickBtn(New_SanityLocators.systemStng_time_ZoneBtn);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOnAutomatic_date_Time()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOnAutomatic_date_Time()");
		}

	}

	public void validateClock(SoftAssert sa) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("h:mm");
			String sysTime = dateFormat.format(new Date()).toString();
			System.out.println("Current time in AM/PM: " + sysTime);

			boolean alarm = isElementExist(New_SanityLocators.clock_alarmIcon);
			boolean clock = isElementExist(New_SanityLocators.clock_ClockIcon);
			boolean timer = isElementExist(New_SanityLocators.clock_TimerIcon);
			boolean stopWatch = isElementExist(New_SanityLocators.clock_StopWatchIcon);

			System.out.println(sysTime);

			if (alarm == true) {

				clickBtn(New_SanityLocators.clock_alarmIcon);
				if (isElementExist(New_SanityLocators.clock_Alarmvalidate)) {

					APP_LOGS.info("Alarm Opened Successfully");
					sa.assertTrue(true, "Alarm Opened Successfully");
					test.log(LogStatus.PASS, "Alarm Opened Successfully");

				} else {
					APP_LOGS.info("Alarm Failed Open");
					sa.fail();
					test.log(LogStatus.FAIL, "Alarm Failed Open");
				}
			}

			if (clock == true) {

				clickBtn(New_SanityLocators.clock_ClockIcon);
				if (isElementExist(New_SanityLocators.clock_clkTimevalidate)) {

					String myTime = sysTime;
					SimpleDateFormat df = new SimpleDateFormat("H:mm");
					Date d = df.parse(myTime);
					Calendar cal1 = Calendar.getInstance();
					cal1.setTime(d);
					cal1.add(Calendar.MINUTE, 1);
					String newTime1 = df.format(cal1.getTime());

					String clockTime = New_SanityLocators.clock_clkTimevalidate.getText();

					String myTime1 = clockTime;
					SimpleDateFormat df1 = new SimpleDateFormat("H:mm");
					Date d1 = df1.parse(myTime1);
					Calendar cal3 = Calendar.getInstance();
					cal3.setTime(d1);
					cal3.add(Calendar.MINUTE, 0);
					String clockTime1 = df1.format(cal3.getTime());

					System.out.println("Mobile Time -> " + clockTime1);

					if (clockTime1.equalsIgnoreCase(sysTime) || clockTime1.equalsIgnoreCase(newTime1)) {
						APP_LOGS.info("Clock Opened & Verified Time Successfully");
						sa.assertTrue(true, "Clock Opened & Verified Time Successfully");
						test.log(LogStatus.PASS, "Clock Opened & Verified Time Successfully");

					} else {
						APP_LOGS.info("Clock Opened & Verified Time Successfully");
						sa.fail();
						test.log(LogStatus.FAIL, "Clock Opened & Verified Time Successfully");
					}

				}

			}

			if (timer == true) {

				clickBtn(New_SanityLocators.clock_TimerIcon);
				if (isElementExist(New_SanityLocators.clock_Timervalidate)) {

					APP_LOGS.info("Timer Opened Successfully");
					sa.assertTrue(true, "Timer Opened Successfully");
					test.log(LogStatus.PASS, "Timer Opened Successfully");

				} else {
					APP_LOGS.info("Timer Failed Open");
					sa.fail();
					test.log(LogStatus.FAIL, "Timer Failed Open");
				}

			}

			if (stopWatch == true) {

				clickBtn(New_SanityLocators.clock_StopWatchIcon);
				if (isElementExist(New_SanityLocators.clock_StopWatchvalidate)) {

					APP_LOGS.info("stopWatch Opened Successfully");
					sa.assertTrue(true, "stopWatch Opened Successfully");
					test.log(LogStatus.PASS, "stopWatch Opened Successfully");

				} else {
					APP_LOGS.info("stopWatch Failed Open");
					sa.fail();
					test.log(LogStatus.FAIL, "stopWatch Failed Open");
				}

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validateClock()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateClock()");
		}
	}

	public void clearClockData() throws InterruptedException {

		try {

			Runtime.getRuntime().exec("adb -s" + p_Id + " shell pm clear com.google.android.deskclock");

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearClockData()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearClockData()");
		}

	}

	public void clearFMdata() throws InterruptedException {

		try {

			Runtime.getRuntime().exec("adb -s" + p_Id + " shell pm clear com.caf.fmradio");

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clearFMdata()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clearFMdata()");
		}

	}

	public void validate_InternalStorage(SoftAssert sa, String prd) {
		try {

			String t1 = New_SanityLocators.storage_totalSpace.getText();
			String totalSpace = t1.substring(8);

			if (totalSpace.equalsIgnoreCase(prd)) {
				APP_LOGS.info("Passed -> Total Space Matches with PRD");
				sa.assertTrue(true, "Passed -> Total Space Matches with PRD");
				test.log(LogStatus.PASS, "Passed -> Total Space Matches with PRD");

			} else {
				APP_LOGS.info("Failed -> Total Space Doesnt Matches with PRD");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Total Space Doesnt Matches with PRD");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_InternalStorage()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_InternalStorage()");
		}
	}

	public void validateFM_ON_Off(SoftAssert sa) {
		try {

			clickBtn(New_SanityLocators.FM_ON_OFF_Swtch);

			if (isElementExist(New_SanityLocators.FM_PlugInMsg)) {

				APP_LOGS.info("Please plug in a Headset to use FM Radio");
				sa.assertTrue(true, "Please plug in a Headset to use FM Radio");
				test.log(LogStatus.SKIP, "Please plug in a Headset to use FM Radio");
			} else if (isElementExist(New_SanityLocators.FM_frequency_Selecter)) {
				APP_LOGS.info("Passed -> Turned ON FM Successfully");
				sa.assertTrue(true, "Passed -> Turned ON FM Successfully");
				test.log(LogStatus.PASS, "Passed -> Turned ON FM Successfully");

				clickBtn(New_SanityLocators.FM_ON_OFF_Swtch);

				if (!isElementExist(New_SanityLocators.FM_frequency_Selecter)
						&& !isElementExist(New_SanityLocators.FM_PlugInMsg)) {
					APP_LOGS.info("Passed -> Turned OFF FM Successfully");
					sa.assertTrue(true, "Passed -> Turned OFF FM Successfully");
					test.log(LogStatus.PASS, "Passed -> Turned OFF FM Successfully");
				}
			} else {
				APP_LOGS.info("FM turn On & Turn Off Failed");
				sa.fail();
				test.log(LogStatus.FAIL, "FM turn On & Turn Off Failed");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validateFM_ON_Off()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateFM_ON_Off()");
		}
	}

	public void validateTuneTOFM_Station(SoftAssert sa) {
		try {
			int count = 0;
			clickBtn(New_SanityLocators.FM_ON_OFF_Swtch);
			if (isElementExist(New_SanityLocators.FM_PlugInMsg)) {

				APP_LOGS.info("Please plug in a Headset to use FM Radio");
				sa.assertTrue(true, "Please plug in a Headset to use FM Radio");
				test.log(LogStatus.SKIP, "Please plug in a Headset to use FM Radio");
			} else if (isElementExist(New_SanityLocators.FM_frequency_Selecter)) {

				clickBtn(New_SanityLocators.FM_MoreOptBtn);
				clickBtn(New_SanityLocators.FM_scanBtn);
				clickBtn(New_SanityLocators.FM_scanAllStationsBtn);
				wait(New_SanityLocators.FM_frequency_Selecter, 30000);
				clickBtn(New_SanityLocators.FM_MoreOptBtn);
				clickBtn(New_SanityLocators.FM_AllChannelsBtn);
				List<AndroidElement> channels = aDriver.findElements(By.xpath(
						"//android.widget.TableLayout/android.widget.TableRow[@index=1]/android.widget.TextView"));

				int channelCount = channels.size();

				for (AndroidElement androidElement : channels) {

					String selectedChannel = androidElement.getText();
					clickBtn(androidElement);
					clickBackButton(1);
					String channel = New_SanityLocators.FM_frequency_SelecterTxt.getText();
					String numericValue = "";
					for (int i = 0; i < channel.length(); i++) {
						if ((channel.charAt(i) >= '0' && channel.charAt(i) <= '9') || channel.charAt(i) == '.')
							numericValue = numericValue + channel.charAt(i);
					}

					System.out.println();
					if (numericValue.equalsIgnoreCase(selectedChannel)) {

						count++;

					}
					clickBtn(New_SanityLocators.FM_MoreOptBtn);
					clickBtn(New_SanityLocators.FM_AllChannelsBtn);

				}

				if (count == channelCount) {
					APP_LOGS.info("Passed -> Turned ON FM Successfully And Playing All Stations Successfully");
					sa.assertTrue(true, "Passed -> Turned ON FM Successfully And Playing All Stations Successfully");
					test.log(LogStatus.PASS,
							"Passed -> Turned ON FM Successfully And Playing All Stations Successfully");
				}

				else {
					APP_LOGS.info("FM Failed TO Play All the Stations");
					sa.fail();
					test.log(LogStatus.FAIL, "FM Failed TO Play All the Stations");

				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validateFM_ON_Off()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateFM_ON_Off()");
		}
	}

	public boolean wait(AndroidElement element, int waitTime) {
		boolean availability = false;
		try {
			new WebDriverWait(aDriver, waitTime).until(ExpectedConditions.visibilityOf(element));
			availability = true;
		} catch (NoSuchElementException e) {
			availability = false;
		}
		return availability;
	}

	public void getNotificationWindow() {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			Runtime r = Runtime.getRuntime();
			r.exec("adb -s " + p_Id + " shell input swipe 10 10 10 1000");
			minWait();
			r.exec("adb -s " + p_Id + " shell input swipe 10 10 10 1000");
			minWait();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Cannot interract with command Prompt");
		}

	}

	public void resetQuickStng() {
		try {
			getNotificationWindow();
			clickBtn(New_SanityLocators.QuickStng_Edit);
			clickBtn(New_SanityLocators.QuickStng_EditMoreOptBtn);
			clickBtn(New_SanityLocators.QuickStng_ResetBtn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->resetQuickStng()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->resetQuickStng()");
		}
	}

	public void turnOnWifi() {

		try {
			if (New_SanityLocators.Wifi_Switch_Btn.getText().equalsIgnoreCase("OFF")) {
				New_SanityLocators.Wifi_Switch_Btn.click();
			}
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOnWifi()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOnWifi()");
		}
	}

	public void turnOnWifi_quickStng() {

		try {
			getNotificationWindow();
			minWait();
			minWait();
			minWait();
			if (New_SanityLocators.QuickStng_WifiBtn.getText().equalsIgnoreCase("OFF")) {
				New_SanityLocators.QuickStng_WifiBtn.click();
			}
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOnWifi()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOnWifi()");
		}
	}

	public void remove_connectedNtwrk() {
		try {
			boolean check1 = scrollToTextClick("Connected");
			boolean check2 = scrollToTextClick("Check password and try again");

			if (check1 == true) {
				scrollToTextClick("FORGET");

				wait(New_SanityLocators.Wifi_PageLogo, 5);
			} else if (check2 == true) {
				scrollToTextLongPress("Check password and try again");
				scrollToTextClick("Forget network");
				wait(New_SanityLocators.Wifi_PageLogo, 5);
			}

		}

		catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->remove_connectedNtwrk()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->remove_connectedNtwrk()");
		}
	}

	public void connect_to_WiFi(String name, String pwsd) {
		try {
			scrollToTextClick(name);
			minWait();
			enterTextToInputField(New_SanityLocators.Wifi_PasswordTxt, pwsd);
			clickBtn(New_SanityLocators.Wifi_connectBtn);
			waituntilnew(New_SanityLocators.Wifi_PageLogo, 20);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->connect_to_WiFi()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->connect_to_WiFi()");
		}
	}

	public void turnOffWifi_quickStng() {

		try {
			getNotificationWindow();
			minWait();
			System.out.println(New_SanityLocators.QuickStng_WifiBtn.getText());
			if (New_SanityLocators.QuickStng_WifiBtn.getText().equalsIgnoreCase("ON")) {
				New_SanityLocators.QuickStng_WifiBtn.click();
				clickBtn(New_SanityLocators.Wifi_ToggleSwitch);
				customWait(2000);
			}
			aDriver.pressKeyCode(AndroidKeyCode.HOME);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOffWifi_quickStng()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOffWifi_quickStng()");
		}
	}

	public void turnOffWifi() {

		try {
			if (New_SanityLocators.Wifi_Switch_Btn.getText().equalsIgnoreCase("ON")) {
				New_SanityLocators.Wifi_Switch_Btn.click();
				customWait(2000);
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOffWifi()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOffWifi()");
		}
	}

	public void clearChromeCache() {
		try {
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell pm clear com.android.chrome");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->clearChromeCache()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clearChromeCache()");
		}
	}

	public void clearChromePermission() throws InterruptedException {
		customWait(2000);
		for (int i = 0; i < 2; i++) {
			if (isElementExist(New_SanityLocators.ACCEPTCONTINUE)) {
				customWait(2000);
				clickBtn(New_SanityLocators.ACCEPTCONTINUE);
				break;
			}
		}
		customWait(2000);
		for (int i = 0; i < 2; i++) {
			if (isElementExist(New_SanityLocators.NEXT)) {
				customWait(2000);
				clickBtn(New_SanityLocators.NEXT);
				break;
			}
		}
		customWait(2000);
		for (int i = 0; i < 2; i++) {
			if (isElementExist(New_SanityLocators.NO_THANKS)) {
				customWait(2000);
				clickBtn(New_SanityLocators.NO_THANKS);
				break;
			}
		}
		customWait(2000);
		for (int i = 0; i < 2; i++) {
			if (isElementExist(New_SanityLocators.cancel)) {
				customWait(2000);
				clickBtn(New_SanityLocators.cancel);
				break;
			}
		}
	}

	public void validate_And_BrowseIn_Chrome(SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validates MobileData enabality by launching the Chrome & browsing google
		 * page.
		 */

		try {
			minWait();
			customWait(2000);
			String url = "https://www.google.com";
			New_SanityLocators.google_urlBar.clear();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text "+url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			wait(New_SanityLocators.google_appsBtn, 20);

			boolean check1 = New_SanityLocators.google_appsBtn.isDisplayed();
			boolean check2 = New_SanityLocators.google_offeredInTxt.isDisplayed();
			boolean check3 = New_SanityLocators.google_signinBtn.isDisplayed();

			if (check1 && check2 && check3) {
				APP_LOGS.info("Suggeted URL Page Displayed Succeessfully");
				soft.assertTrue(true, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Data or WiFi Enabled and URL loaded successfully at iteration ");
			} else {
				APP_LOGS.info("Suggeted URL page didn't get Loaded");
				test.log(LogStatus.FAIL, "Test Case Status is Fail at iteration ");
				test.log(LogStatus.INFO, "URL didn't load.");
				soft.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_And_BrowseIn_Chrome()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_And_BrowseIn_Chrome()");
		}
	}

	public void validate_And_BrowseIn_Chrome_Disconnect(SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validates MobileData enabality by launching the Chrome & browsing google
		 * page.
		 */

		try {
			customWait(3000);
			if (isElementExist(New_SanityLocators.google_Alert_NoInternetOpt)) {

				clickBtn(New_SanityLocators.google_Alert_okBtn);
			} else if (isElementExist(New_SanityLocators.google_savedDataOpt)) {

				clickBtn(New_SanityLocators.google_savedDataOpt_OkBtn);
			}

			customWait(2000);

			String url = "https://www.appium.io";
			New_SanityLocators.google_urlBar.clear();
			enterTextToInputField(New_SanityLocators.google_urlBar, url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			waituntilnew(New_SanityLocators.google_NoInternetOpt, 30);

			if (New_SanityLocators.google_NoInternetOpt.isDisplayed()) {
				APP_LOGS.info("Page Not Displayed do to Data Or Wifi Disconnection");
				soft.assertTrue(true, "Page Not Displayed do to Data Or Wifi Disconnection");
				test.log(LogStatus.PASS, "Page Not Displayed do to Data Or Wifi Disconnection");

			} else {
				APP_LOGS.info("Failed -> Page shown Successfully after Wifi Disconnection Also");
				test.log(LogStatus.FAIL,
						"Failed -> Page shown Successfully after Wifi Disconnection Also in iteration ");
				test.log(LogStatus.INFO, "Failed -> Page shown Successfully after Wifi Disconnection Also");
				soft.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_And_BrowseIn_Chrome_Disconnect()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_And_BrowseIn_Chrome_Disconnect()");
		}
	}

	public void Launch_App(String appName) {

		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			APP_LOGS.info("HOme PAge");
			customWait(3000);
			Runtime r = Runtime.getRuntime();
			r.exec("adb -s " + p_Id + " shell input swipe 534 1647 534 0");
//			clickBtn(New_SanityLocators.AppListIcon);
			customWait(1000);
			switch (appName) {
			case "browser":
				scrollToElements(New_SanityLocators.browser_App);
				clickBtn(New_SanityLocators.browser_App);
				APP_LOGS.info("Clicked on Browser successfully.");

				break;

			case "Setting":
				scrollToElements(New_SanityLocators.SettingLnk);
				clickBtn(New_SanityLocators.SettingLnk);
				APP_LOGS.info("Clicked on Setting successfully.");

				break;

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOffWifi()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOffWifi()");
		}
	}

	public void turnOff_Bluetooth() {

		try {
			minWait();
			Runtime r = Runtime.getRuntime();
			r.exec("adb -s " + p_Id + " shell am start -a android.bluetooth.adapter.action.REQUEST_DISABLE");

			if (isElementExist(New_SanityLocators.Allow_Btn)) {

				clickBtn(New_SanityLocators.Allow_Btn);
			}
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOff_Bluetooth()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOff_Bluetooth()");
		}
	}

	public void turnOn_Blutooth_quickStng() {

		try {
			getNotificationWindow();
			minWait();
			if (New_SanityLocators.QuickStng_BlurtoothBtn.getText().equalsIgnoreCase("OFF")) {
				New_SanityLocators.QuickStng_BlurtoothBtn.click();
			}
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOn_Blutooth_quickStng()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOn_Blutooth_quickStng()");
		}
	}

	public void turnOff_Blutooth_quickStng() {

		try {
			getNotificationWindow();
			minWait();
			if (New_SanityLocators.QuickStng_BlurtoothBtn.getText().equalsIgnoreCase("ON")) {
				New_SanityLocators.QuickStng_BlurtoothBtn.click();
				New_SanityLocators.Wifi_ToggleSwitch.click();
			}
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOff_Blutooth_quickStng()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOff_Blutooth_quickStng()");
		}
	}

	public void validate_Blutooth_TurnOn(SoftAssert sa) {
		try {

			getNotificationWindow();
			minWait();
			String check1 = New_SanityLocators.QuickStng_BlurtoothBtn.getText();

			if (check1.equalsIgnoreCase("ON")) {

				APP_LOGS.info("Bluetooth Turned On Succesfully using Quick Setting");
				sa.assertTrue(true, "Bluetooth Turned On Succesfully using Quick Setting");
				test.log(LogStatus.PASS, "Bluetooth Turned On Succesfully using Quick Setting");

			} else {
				APP_LOGS.info("Bluetooth Failed to Turned On using Quick Setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Bluetooth Failed to Turned On using Quick Setting");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_Blutooth_TurnOn()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Blutooth_TurnOn()");
		}
	}

	public void validate_Blutooth_TurnOff(SoftAssert sa) {
		try {

			getNotificationWindow();
			minWait();
			String check1 = New_SanityLocators.QuickStng_BlurtoothBtn.getText();

			if (check1.equalsIgnoreCase("Off")) {

				APP_LOGS.info("Bluetooth Turned Off Succesfully using Quick Setting");
				sa.assertTrue(true, "Bluetooth Turned Off Succesfully using Quick Setting");
				test.log(LogStatus.PASS, "Bluetooth Turned Off Succesfully using Quick Setting");

			} else {
				APP_LOGS.info("Bluetooth Failed to Turned Off using Quick Setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Bluetooth Failed Off Turned On using Quick Setting");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_Blutooth_TurnOff()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Blutooth_TurnOff()");
		}
	}

	public void increaseDevice_Brightness() {

		try {
			getNotificationWindow();
			minWait();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 977 170");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->increaseDevice_Brightness()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->increaseDevice_Brightness()");
		}
	}

	public void validate_MaximumBrightness(SoftAssert sa) {
		try {

			aDriver.startActivity("com.android.settings", "com.android.settings.Settings$DisplaySettingsActivity");
			minWait();
			String check1 = New_SanityLocators.display_BrightnessPercentTxt.getText();

			if (check1.equalsIgnoreCase("100%")) {

				APP_LOGS.info("Device Brightness Maximized Successfully");
				sa.assertTrue(true, "Device Brightness Maximized Successfully");
				test.log(LogStatus.PASS, "Device Brightness Maximized Successfully");

			} else {
				APP_LOGS.info("Device Maximize Brightness Failed");
				sa.fail();
				test.log(LogStatus.FAIL, "Device Maximize Brightness Failed");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_MaximumBrightness()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_MaximumBrightness()");
		}
	}

	public void decreaseDevice_Brightness() {

		try {
			getNotificationWindow();
			minWait();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 112 170");
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->increaseDevice_Brightness()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->increaseDevice_Brightness()");
		}
	}

	public void validate_MinimumBrightness(SoftAssert sa) {
		try {

			aDriver.startActivity("com.android.settings", "com.android.settings.Settings$DisplaySettingsActivity");
			minWait();
			String check1 = New_SanityLocators.display_BrightnessPercentTxt.getText();

			if (check1.equalsIgnoreCase("0%")) {

				APP_LOGS.info("Device Brightness Minimized Successfully");
				sa.assertTrue(true, "Device Brightness Minimized Successfully");
				test.log(LogStatus.PASS, "Device Brightness Minimized Successfully");

			} else {
				APP_LOGS.info("Device Minimized Brightness Failed");
				sa.fail();
				test.log(LogStatus.FAIL, "Device Minimized Brightness Failed");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_MinimumBrightness()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_MinimumBrightness()");
		}
	}

	public void enterurl(String url1) {

		try {
			String url = url1;
			New_SanityLocators.google_urlBar.clear();
			enterTextToInputField(New_SanityLocators.google_urlBar, url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enterurl()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->enterurl()");
		}
	}

	public void validate_webPage(AndroidElement e1, AndroidElement e2, String url1, SoftAssert soft) {
		try {

			boolean check1 = e1.isDisplayed();
			boolean check2 = e2.isDisplayed();

			if (check1 && check2) {
				APP_LOGS.info("Suggeted " + url1 + " Page Displayed Succeessfully");
				soft.assertTrue(true, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Suggeted " + url1 + " Page Displayed Succeessfully");
			} else {
				APP_LOGS.info("Suggeted " + url1 + " page didn't get Loaded");
				test.log(LogStatus.FAIL, "Test Case Status is Fail at iteration ");
				test.log(LogStatus.INFO, "Suggeted " + url1 + " page didn't get Loaded");
				soft.fail();
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_webPage()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_webPage()");
		}
	}

	public void turnOn_FlashLight() {

		try {
			getNotificationWindow();

			if (New_SanityLocators.QuickStng_flashLightBtn.getText().equalsIgnoreCase("OFF")) {
				New_SanityLocators.QuickStng_flashLightBtn.click();
			}
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOn_FlashLight()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOn_FlashLight()");
		}
	}

	public void turnOff_FlashLight() {

		try {
			getNotificationWindow();

			if (New_SanityLocators.QuickStng_flashLightBtn.getText().equalsIgnoreCase("ON")) {
				New_SanityLocators.QuickStng_flashLightBtn.click();
			}
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->turnOff_FlashLight()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->turnOff_FlashLight()");
		}
	}

	public void validateFlashLight_turnOn(SoftAssert sa) {
		try {

			getNotificationWindow();
			minWait();
			String check1 = New_SanityLocators.QuickStng_flashLightBtn.getText();

			if (check1.equalsIgnoreCase("ON")) {

				APP_LOGS.info("FlashLight Turned On Succesfully using Quick Setting");
				sa.assertTrue(true, "FlashLight Turned On Succesfully using Quick Setting");
				test.log(LogStatus.PASS, "FlashLight Turned On Succesfully using Quick Setting");

			} else {
				APP_LOGS.info("FlashLight Failed to Turned On using Quick Setting");
				sa.fail();
				test.log(LogStatus.FAIL, "FlashLight Failed to Turned On using Quick Setting");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validateFlashLight_turnOn()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateFlashLight_turnOn()");
		}
	}

	public void validateFlashLight_turnOff(SoftAssert sa) {
		try {

			getNotificationWindow();
			minWait();
			String check1 = New_SanityLocators.QuickStng_flashLightBtn.getText();

			if (check1.equalsIgnoreCase("Off")) {

				APP_LOGS.info("FlashLight Turned Off Succesfully using Quick Setting");
				sa.assertTrue(true, "FlashLight Turned Off Succesfully using Quick Setting");
				test.log(LogStatus.PASS, "FlashLight Turned Off Succesfully using Quick Setting");

			} else {
				APP_LOGS.info("FlashLight Failed to Turned Off using Quick Setting");
				sa.fail();
				test.log(LogStatus.FAIL, "FlashLight Failed Off Turned On using Quick Setting");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validateFlashLight_turnOff()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateFlashLight_turnOff()");
		}
	}

	public void create_NewSMS(String number, String message) throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			clickBtn(New_SanityLocators.add_NewSMS);
			minWait();
			enterTextToInputField(New_SanityLocators.TO_Field, number);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(New_SanityLocators.messageField, message);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->create_NewSMS()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->create_NewSMS()");
		}
	}

	public void clickOn_Send() throws InterruptedException, IOException {
		/* Method used to click on Send Button. */
		try {
			customWait(500);
			clickBtn(New_SanityLocators.send_Icon);
			minWait();

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->clickOn_Send()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Send()");
		}
	}

	public void validate_SentMessage(SoftAssert sf) throws InterruptedException {
		/* To validate the Sent Message. */

		WebDriverWait wait = new WebDriverWait(aDriver, 120);
		wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.now_Text));
		try {
			if (isElementExist(New_SanityLocators.now_Text) || isElementExist(New_SanityLocators.not_Sent_Text)) {
				check = true;
				APP_LOGS.info("Message sent Succeccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "SMS or MMS Sent Successfully.");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL, "Message didn't sent.");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_SentMessage()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_SentMessage()");
		}
	}

	public void validate_RecievedMessage(SoftAssert sf) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		WebDriverWait wait = new WebDriverWait(aDriver, 80);
		wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.now_Text));
		customWait(8000);
		try {
			if (isElementExist(New_SanityLocators.now_Text) || isElementExist(New_SanityLocators.not_Sent_Text)) {
				check = true;
				APP_LOGS.info("Message sent Succeccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully.");
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL, "Message didn't Recieved.");
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			sf.fail();
		}
	}

	public LinkedList<String> checkInf_inSwRealease() throws InterruptedException, IOException {

		try {
			while (!isElementExist(New_SanityLocators.CitTestLnk)) {

				clickBackButton(1);
			}

			if (isElementExist(New_SanityLocators.CitTestLnk)) {
				clickBtn(New_SanityLocators.CitTestLnk);
				clickBtn(New_SanityLocators.Phone_Version);
				minWait();

				String modelnum = New_SanityLocators.phnVrsn_ModelNum.getText();
				String builnum = New_SanityLocators.phnVrsn_BuildNum.getText();
				String baseband = New_SanityLocators.phnVrsn_BaseBand.getText();

				System.out.println(modelnum);
				System.out.println(builnum);
				System.out.println(baseband);

				LinkedList<String> al = new LinkedList<String>();

				al.add(0, New_SanityLocators.phnVrsn_ModelNum.getText());
				al.add(1, New_SanityLocators.phnVrsn_BuildNum.getText());
				al.add(2, New_SanityLocators.phnVrsn_BaseBand.getText());

				return al;
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->checkInf_inSwRealease()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->checkInf_inSwRealease()");
		}
		return null;
	}

	public void validateSWinf_withStng(SoftAssert sa, LinkedList<String> al) throws InterruptedException {

		try {

			minWait();
			scrollToTextWithoutClick("Model");
			String modelNum = New_SanityLocators.Stng_phnVrsn_ModelNum.getText();

			scrollToTextWithoutClick("Baseband version");
			String version = New_SanityLocators.Stng_Phone_Version.getText();

			scrollToTextWithoutClick("Build number");
			String buildNum = New_SanityLocators.Stng_phnVrsn_BuildNum.getText();

			minWait();

			System.out.println(modelNum);
			System.out.println(version);
			System.out.println(buildNum);

			System.out.println(al.get(0));
			System.out.println(al.get(1));
			System.out.println(al.get(2));

			if (al.get(0).equalsIgnoreCase(modelNum) && al.get(1).equalsIgnoreCase(buildNum)
					&& al.get(2).equalsIgnoreCase(version)) {

				APP_LOGS.info(
						"Device Model, AP, CP version and Build number is consistent with version mentioned in the SW release Notes");
				sa.assertTrue(true,
						"Device Model, AP, CP version and Build number is consistent with version mentioned in the SW release Notes");
				test.log(LogStatus.PASS,
						"Device Model, AP, CP version and Build number is consistent with version mentioned in the SW release Notes");

			} else {
				APP_LOGS.info(
						"Device Model, AP, CP version and Build number is Not consistent with version mentioned in the SW release Notes");
				sa.fail();
				test.log(LogStatus.FAIL,
						"Device Model, AP, CP version and Build number is Not consistent with version mentioned in the SW release Notes");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validateSWinf_withStng()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateSWinf_withStng()");
		}
	}

	public void launch_APP(AndroidElement appToClick) throws InterruptedException {
		try {
			clickOnAppList();
			for (int i = 0; i < 6; i++) {
				if (isElementExist(appToClick)) {
					test.log(LogStatus.INFO, "\"" + appToClick.getText() + "\" app launched.");
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

	public void clickOnAppList_j() throws InterruptedException {

		try {
			minWait();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input keyevent 3");
			// aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2000);
			clickBtn(New_SanityLocators.app_List);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void close_CIT() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			clickBackButton(3);
			clickBtn(New_SanityLocators.Allow_Btn);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->close_CIT()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->close_CIT()");
		}
	}

	public void validateDate_Time(SoftAssert sa, String SystimeZone) throws InterruptedException {
		try {

			DateFormat dateFormat = new SimpleDateFormat("h:mm aa");
			String sysTime = dateFormat.format(new Date()).toString();
			System.out.println("Current time in AM/PM: " + sysTime);

			String myTime = sysTime;
			SimpleDateFormat df = new SimpleDateFormat("h:mm aa");
			Date d = df.parse(myTime);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(d);
			cal1.add(Calendar.MINUTE, 1);
			String newTime1 = df.format(cal1.getTime());
			
			System.out.println(newTime1);

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String strDate = formatter.format(date);
			formatter = new SimpleDateFormat("MMMM d, yyyy");
			strDate = formatter.format(date);
			System.out.println("Date Format with dd MMMM yyyy : " + strDate);
			
			String strDate1 = formatter.format(date);
			formatter = new SimpleDateFormat("d MMMM yyyy");
			strDate1 = formatter.format(date);
			System.out.println("Date Format with dd MMMM yyyy : " + strDate1);
			

			String time = New_SanityLocators.date_time_timeTxt.getText();
			String Phndate = New_SanityLocators.date_time_dateTxt.getText();
			String timeZone = New_SanityLocators.date_time_timeZoneTxt.getText();

			System.out.println(time);
			System.out.println(Phndate);
			System.out.println(timeZone);
			minWait();

			if ((time.equalsIgnoreCase(sysTime) || time.equalsIgnoreCase(newTime1)) && (strDate.equalsIgnoreCase(Phndate)||strDate1.equalsIgnoreCase(Phndate))
					&& SystimeZone.equalsIgnoreCase(timeZone)) {
				APP_LOGS.info("Date Time and TimeZone Auto Sychronize is done Successfully");
				sa.assertTrue(true, "Date Time and TimeZone Auto Sychronize is done Successfully");
				test.log(LogStatus.PASS, "Date Time and TimeZone Auto Sychronize is done Successfully");

			} else {
				APP_LOGS.info("Date Time and TimeZone Auto Sychronize Failed");
				sa.fail();
				test.log(LogStatus.FAIL, "Date Time and TimeZone Auto Sychronize Failed");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validateDate_Time()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateDate_Time()");
		}
	}

	public void validateBatteryStatus(SoftAssert sa) throws InterruptedException {

		try {

			Process p = Runtime.getRuntime().exec("adb -s " + p_Id + " shell dumpsys battery");
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()), 8 * 1024);
			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			 HashMap<Integer,String> hm=new HashMap<Integer,String>();    
			String s = null;
			while ((s = stdInput.readLine()) != null) {
			/*	//s.replace("[", "").trim().replace("]", "").trim();
				 String[] argStr = s.split(":");
				 LinkedList ll = new LinkedList(Arrays.asList(argStr));
				 System.out.println(ll);
				for (int i=0;i<ll.size();i++) {
					for (int j = 0; j < ll.length; j++) {
						
					}
					if(ll.indexOf(ll.get(i)) % 2) {
						hm.putIfAbsent(key, value)
					}
					System.out.println(ll.get(i));
				//	hm.put(string.indexOf(0), value)
					
				}*/
			/*
			 * if(al.get(0).equalsIgnoreCase(modelNum)&&al.get(1).equalsIgnoreCase(buildNum)
			 * &&al.get(2).equalsIgnoreCase(version)) {
			 * 
			 * 
			 * APP_LOGS.
			 * info("Device Model, AP, CP version and Build number is consistent with version mentioned in the SW release Notes"
			 * ); sa.assertTrue(true,
			 * "Device Model, AP, CP version and Build number is consistent with version mentioned in the SW release Notes"
			 * ); test.log(LogStatus.PASS,
			 * "Device Model, AP, CP version and Build number is consistent with version mentioned in the SW release Notes"
			 * );
			 * 
			 * } else { APP_LOGS.
			 * info("Device Model, AP, CP version and Build number is Not consistent with version mentioned in the SW release Notes"
			 * ); sa.fail(); test.log(LogStatus.FAIL,
			 * "Device Model, AP, CP version and Build number is Not consistent with version mentioned in the SW release Notes"
			 * ); }
			 */
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validateBatteryStatus()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validateBatteryStatus()");
		}
	}

	public void turnOn_AirplaneMode() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			if (New_SanityLocators.airplaneModeSwitch.getText().equalsIgnoreCase("OFF")) {
				New_SanityLocators.airplaneModeSwitch.click();
			}
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOn_AirplaneMode()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOn_AirplaneMode()");
		}
	}

	public void turnOff_AirplaneMode() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			if (New_SanityLocators.airplaneModeSwitch.getText().equalsIgnoreCase("ON")) {
				New_SanityLocators.airplaneModeSwitch.click();
			}
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOff_AirplaneMode()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOff_AirplaneMode()");
		}
	}

	public void validate_AirplaneModeEnable(SoftAssert sa) throws InterruptedException {

		try {
			customWait(2000);
			boolean mob = New_SanityLocators.mobileNtwrk.isEnabled();
			if (mob == false) {

				APP_LOGS.info("Airplane Mode enabled Successfully");
				sa.assertTrue(true, "Airplane Mode enabled Successfully");
				test.log(LogStatus.PASS, "Airplane Mode enabled Successfully");

			} else {
				APP_LOGS.info("Airplane Enabling Failed");
				sa.fail();
				test.log(LogStatus.FAIL, "Airplane Enabling Failed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_AirplaneModeEnable()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_AirplaneModeEnable()");
		}
	}

	public void validate_AirplaneModeDisable(SoftAssert sa) throws InterruptedException {

		try {
			customWait(2000);
			boolean mobileNetwork = scrollToText("Mobile network");

			if (mobileNetwork == true) {

				APP_LOGS.info("Airplane Mode disabled Successfully");
				sa.assertTrue(true, "Airplane Mode disabled Successfully");
				test.log(LogStatus.PASS, "Airplane Mode disabled Successfully");

			} else {
				APP_LOGS.info("Airplane disabling Failed");
				sa.fail();
				test.log(LogStatus.FAIL, "Airplane disabling Failed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_AirplaneModeDisable()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_AirplaneModeDisable()");
		}
	}

	public void turnOn_AirplaneModeQuickStng() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			minWait();
			getNotificationWindow();
			minWait();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input swipe 996 624 38 624");

			minWait();
			if (New_SanityLocators.QuickStng_AirplaneModeBtn.getText().equalsIgnoreCase("Off")) {
				clickBtn(New_SanityLocators.QuickStng_AirplaneModeBtn);
				minWait();
				clickBtn(New_SanityLocators.OK);

			}
			clearRecentApps();
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOn_AirplaneModeQuickStng()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOn_AirplaneModeQuickStng()");
		}
	}

	public void turnOff_AirplaneModeQuickStng() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			getNotificationWindow();

			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input swipe 996 624 38 624");
			customWait(2000);

			if (New_SanityLocators.QuickStng_AirplaneModeBtn.getText().equalsIgnoreCase("ON")) {
				clickBtn(New_SanityLocators.QuickStng_AirplaneModeBtn);
				minWait();
			}
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOff_AirplaneModeQuickStng()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOff_AirplaneModeQuickStng()");
		}
	}

	public void clickOnHomeBtn() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->clickOnHomeBtn()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOnHomeBtn()");
		}
	}

	public void validate_EnggMenu(SoftAssert sa, String enggMode, AndroidElement ele) throws InterruptedException {

		try {
			minWait();
			if (isElementExist(ele)) {

				APP_LOGS.info(enggMode + " Mode Opened Successfully");
				sa.assertTrue(true, enggMode + " Mode Opened Successfully");
				test.log(LogStatus.PASS, enggMode + " Mode Opened Successfully");

			} else {
				APP_LOGS.info(enggMode + " Mode Failed Opened");
				sa.fail();
				test.log(LogStatus.FAIL, enggMode + " Mode Failed Opened");
			}
			clickBackButton(1);
			if (isElementExist(New_SanityLocators.Allow_Btn)) {

				clickBtn(New_SanityLocators.Allow_Btn);
			}
			clickBackButton(1);
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_EnggMenu()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_EnggMenu()");
		}
	}

	public void disable_AirplaneMode() throws InterruptedException {
		try {
			launch_an_app("settings");
			scrollToText("Network & Internet");
			if (New_SanityLocators.airplaneModeSwitch.getText().equalsIgnoreCase("ON")) {
				New_SanityLocators.airplaneModeSwitch.click();
			}
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->disable_AirplaneMode()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->disable_AirplaneMode()");
		}
	}

	public void turnOff_Mobiledata() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			launch_an_app("settings");
			scrollToText("Network & Internet");
			scrollToText("Mobile network");
			if (New_SanityLocators.networkStng_mobileDataSwitch.getText().equalsIgnoreCase("ON")) {
				clickBtn(New_SanityLocators.networkStng_mobileDataSwitch);
				minWait();
				clickBtn(New_SanityLocators.Allow_Btn);
			}
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOff_Mobiledata()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOff_Mobiledata()");
		}
	}

	public void turnOn_Mobiledata() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			launch_an_app("settings");
			scrollToText("Network & Internet");
			scrollToText("Mobile network");
			if (New_SanityLocators.networkStng_mobileDataSwitch.getText().equalsIgnoreCase("OFF")) {
				clickBtn(New_SanityLocators.networkStng_mobileDataSwitch);
				minWait();
			}
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOn_Mobiledata()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOn_Mobiledata()");
		}
	}

	public void navigateTo_Apn() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			launch_an_app("settings");
			scrollToText("Network & Internet");
			scrollToText("Mobile network");
			clickBtn(New_SanityLocators.mobileNtwrk_triangleIcon);
			scrollToText("Access Point Names");
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->navigateTo_Apn()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->navigateTo_Apn()");
		}
	}

	public void add_Apn(String Apn_name, String Apn, String Apn_Type) throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			clickBtn(New_SanityLocators.Apn_addNewApnBtn);

			// Add Apn Name
			clickBtn(New_SanityLocators.APNname);
			enterTextToInputField(New_SanityLocators.APNinput, Apn_name);
			wait(New_SanityLocators.Allow_Btn, 30);
			clickBtn(New_SanityLocators.Allow_Btn);
			minWait();

			// Add Apn No
			clickBtn(New_SanityLocators.APNno);
			enterTextToInputField(New_SanityLocators.APNinput, Apn);
			wait(New_SanityLocators.Allow_Btn, 30);
			clickBtn(New_SanityLocators.Allow_Btn);

			// Add Apn Type
			scrollToText("APN type");
			enterTextToInputField(New_SanityLocators.APNinput, Apn_Type);
			wait(New_SanityLocators.Allow_Btn, 30);
			clickBtn(New_SanityLocators.Allow_Btn);

			// save the apn
			clickBtn(New_SanityLocators.MoreOptionsBtn);
			clickBtn(New_SanityLocators.APN_SaveOpt);

			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->add_Apn()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->add_Apn()");
		}
	}

	public void validate_AddedApn(SoftAssert sa, String Apn_name, String Apn_no) throws InterruptedException {

		try {

			boolean apnName = scrollToText(Apn_name);
			clickBackButton(1);
			boolean apnNo = scrollToText(Apn_no);
			clickBackButton(1);
			if (apnName && apnNo) {

				APP_LOGS.info("New APN Added Successfully");
				sa.assertTrue(true, "New APN Added Successfully");
				test.log(LogStatus.PASS, "New APN Added Successfully");

			} else {
				APP_LOGS.info("Adding New APN Failed");
				sa.fail();
				test.log(LogStatus.FAIL, "Adding New APN Failed");
			}
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_AddedApn()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_AddedApn()");
		}
	}

	public void delete_Apn(String Apn_name) throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			scrollToText(Apn_name);
			clickBtn(New_SanityLocators.MoreOptionsBtn);
			clickBtn(New_SanityLocators.APN_deleteOpt);
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->add_Apn()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->add_Apn()");
		}
	}

	public void validate_DeletedApn(SoftAssert sa, String Apn_name) throws InterruptedException {

		try {
			int Count = 0;

			List<AndroidElement> apnNameRes = New_SanityLocators.Apn_savedApnLst;

			for (AndroidElement androidElement : apnNameRes) {
				if (androidElement.getText().equalsIgnoreCase(Apn_name)) {
					Count++;
				}
			}

			if (Count == 0) {

				APP_LOGS.info("APN Deleted Successfully");
				sa.assertTrue(true, "APN Deleted Successfully");
				test.log(LogStatus.PASS, "APN Deleted Successfully");

			} else if (Count == 1) {

				APP_LOGS.info("Deleting APN Failed");
				sa.fail();
				test.log(LogStatus.FAIL, "Deleting APN Failed");
			}
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_DeletedApn()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_DeletedApn()");
		}
	}

	public void setIPv4_InApn() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			List<AndroidElement> apnNameRes = New_SanityLocators.Apn_savedApnLst;

			for (AndroidElement androidElement : apnNameRes) {
				scrollToText(androidElement.getText());
				scrollToText("APN protocol");
				scrollToText("IPv4");
				scrollToText("APN roaming protocol");
				scrollToText("IPv4");
				clickBackButton(1);
			}
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->setIPv4_InApn()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->setIPv4_InApn()");
		}
	}

	public void validat_browse_Url(SoftAssert soft) throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			clearChromeCache();
			launch_an_app("browser");
			clearChromePermission();

			New_SanityLocators.google_urlBar.clear();
			customWait(2000);
			enterTextToInputField(New_SanityLocators.google_urlBar, "https://start.att.net");		
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			wait(New_SanityLocators.Att_homepageLogo, 15);
			boolean check1 = New_SanityLocators.Att_homepageLogo.isDisplayed();
			wait(New_SanityLocators.Att_mailIcon, 15);
			boolean check2 = New_SanityLocators.Att_mailIcon.isDisplayed();
			minWait();

			
			

			if (check1 && check2) {
				APP_LOGS.info("Suggeted URL Page Displayed Succeessfully using IPv4");
				soft.assertTrue(true, "Suggeted URL Page Displayed Succeessfully using IPv4");
				test.log(LogStatus.PASS, "Suggeted URL Page Displayed Succeessfully using IPv4");
			} else {
				APP_LOGS.info("Suggeted URL page didn't get Loaded using IPv4");
				test.log(LogStatus.FAIL, "Test Case Status is Fail at iteration ");
				test.log(LogStatus.INFO, "URL didn't load.");
				soft.fail();
			}
			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validat_browse_Url()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validat_browse_Url()");
		}
	}

	public void check_mobileNtwrkEnabled(SoftAssert soft) throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			scrollToText("Mobile network");
			if (!New_SanityLocators.networkStng_mobileDataSwitch.isEnabled()) {
				APP_LOGS.info("Mobile Data Service is disabled, Check Sim card Inserted Properly");
				soft.assertTrue(true, "Mobile Data Service is disabled, Check Sim card Inserted Properly");
				test.log(LogStatus.SKIP, "Mobile Data Service is disabled, Check Sim card Inserted Properly");

			}

			aDriver.pressKeyCode(AndroidKeyCode.HOME);

			customWait(2000);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->check_mobileNtwrkEnabled()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->check_mobileNtwrkEnabled()");
		}
	}

	public void turnOn_DataServiceQuickStng() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			minWait();
			getNotificationWindow();
			minWait();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input swipe 996 624 38 624");

			minWait();
			if (New_SanityLocators.QuickStng_dataServicesBtn.getText().equalsIgnoreCase("Off")) {
				clickBtn(New_SanityLocators.QuickStng_dataServicesBtn);
				minWait();
			}
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			customWait(2000);
			// clearRecentApps();

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOn_DataServiceQuickStng()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOn_DataServiceQuickStng()");
		}
	}

	public void validate_dataServiceOn(SoftAssert soft) throws InterruptedException {
		/* Method used to validate data service is turned On Successfully */

		try {
			
			Launch_App("browser");
			validate_And_BrowseIn_Chrome(soft);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validat_browse_Url()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validat_browse_Url()");
		}
	}

	public void disable_wifi() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			minWait();

			Runtime.getRuntime()
					.exec("adb -s " + p_Id + " shell am broadcast -a io.appium.settings.wifi --es setstatus disable");

			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOn_DataServiceQuickStng()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOn_DataServiceQuickStng()");
		}
	}

	public void turnOff_DataServiceQuickStng() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			minWait();
			getNotificationWindow();
			minWait();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input swipe 996 624 38 624");

			minWait();
			if (New_SanityLocators.QuickStng_dataServicesBtn.getText().equalsIgnoreCase("On")) {
				clickBtn(New_SanityLocators.QuickStng_dataServicesBtn);
				customWait(2000);
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 837 1277");
				minWait();
			}
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			customWait(2000);
			// clearRecentApps();

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->turnOff_DataServiceQuickStng()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->turnOff_DataServiceQuickStng()");
		}
	}

	public void validate_dataServiceOff(SoftAssert soft) throws InterruptedException, IOException {
		/*
		 * Validates MobileData enabality by launching the Chrome & browsing google
		 * page.
		 */

		try {
			clearChromeCache();
			Launch_App("browser");
			clearChromePermission();

			customWait(2000);

			String url = "https://www.google.com";
			New_SanityLocators.google_urlBar.clear();
			enterTextToInputField(New_SanityLocators.google_urlBar, url);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			waituntilnew(New_SanityLocators.google_NoInternetOpt, 30);

			if (New_SanityLocators.google_NoInternetOpt.isDisplayed()) {
				APP_LOGS.info("Page Not Displayed do to Data Or Wifi Disconnection");
				soft.assertTrue(true, "Page Not Displayed do to Data Or Wifi Disconnection");
				test.log(LogStatus.PASS, "Page Not Displayed do to Data Or Wifi Disconnection");

			} else {
				APP_LOGS.info("Failed -> Page shown Successfully after Wifi Disconnection Also");
				test.log(LogStatus.FAIL,
						"Failed -> Page shown Successfully after Wifi Disconnection Also in iteration ");
				test.log(LogStatus.INFO, "Failed -> Page shown Successfully after Wifi Disconnection Also");
				soft.fail();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_dataServiceOff()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_dataServiceOff()");
		}
	}

	public void validate_IMS_Registered(SoftAssert sa) {
		try {

			scrollToText("IMS registration status");
			boolean check1 = isElementExist(New_SanityLocators.Phone_IMS_Register);

			if (check1 == true) {
				APP_LOGS.info("Passed -> IMS is Registered in this device");
				sa.assertTrue(true, "Passed -> IMS is Registered in this device");
				test.log(LogStatus.PASS, "Passed -> IMS is Registered in this device");

			} else if (New_SanityLocators.Phone_IMS_Register.getText().equalsIgnoreCase("not registered")) {
				APP_LOGS.info("Skipped -> IMS is Not Registered in this device");
				test.log(LogStatus.SKIP, "Skipped -> IMS is Not Registered in this device");
			} else {
				APP_LOGS.info("Test case is Failed");
				test.log(LogStatus.SKIP, "Test case is Failed");

			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_IMS_Registered()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_IMS_Registered()");
		}
	}

	public void validate_ussd_no(SoftAssert sa) throws InterruptedException {
		/*
		 * This is To validate Ussd Session Request Sending And Receiving Properly
		 */

		try {

			boolean check1 = scrollToTextContains("Call waiting");

			if (check1 == true) {

				APP_LOGS.info("Successfully Session Request Sent And Received");
				sa.assertTrue(true, "Successfully Session Request Sent And Received");
				test.log(LogStatus.PASS, "Successfully Session Request Sent And Received");

			} else {
				APP_LOGS.info("Failed To Send Session Request");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed To Send Session Request");
			}
			clickBtn(New_SanityLocators.Allow_Btn);
			customWait(2000);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_ussd_no()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_ussd_no()");
		}
	}

	public void reject_Call_With_SMS_O(String message) throws InterruptedException {

		try {
		minWait();
			
			// clickBtn(New_SanityLocators.incoming_Call);
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 660 160");
			minWait();
			aDriver.swipe(80, 1840, 300, 600, 750);
			clickBtn(New_SanityLocators.write_Your_Own);
			minWait();
			//Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text"+ message);
			enterTextToInputField(New_SanityLocators.msg_popup, message);
			//Runtime.getRuntime().exec("adb -s " + p_Id + " shell input text " + message);
			minWait();
			clickBtn(New_SanityLocators.SEND);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->reject_Call_With_SMS_O()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->reject_Call_With_SMS_O()");
		}
	}

	public void callfrom_CallHistory() throws InterruptedException {
		/* Method used to create New SMS. */

		try {
			minWait();
			clickBtn(New_SanityLocators.dialer_callHistoryBtn);
			minWait();
			clickBtn(New_SanityLocators.dialer_callHistory1stNobtn);
			minWait();
			
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->callfrom_CallHistory()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->callfrom_CallHistory()");
		}
	}
	
		public void validate_callHistory(SoftAssert sa) throws InterruptedException {
		/*
		 * This is To validate the call history for Outgoing call for the first num
		 */

		try {
			minWait();
			clickBtn(New_SanityLocators.dialer_callHistory1stLnk);
			scrollToText("Call details");
			minWait();
			if(New_SanityLocators.dialer_callDetails.getText().equalsIgnoreCase("Outgoing call")) {
				APP_LOGS.info("Successfully Device is able to call from Call History");
				sa.assertTrue(true, "Successfully Device is able to call from Call History");
				test.log(LogStatus.PASS, "Successfully Device is able to call from Call History");
				
			}
			 else {
				APP_LOGS.info("Failed To make call from call history");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed To make call from call history");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_callHistory()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_callHistory()");
		}
	}
		
		public void changeTo_FrontCam() {
			try {
				minWait();
				if(isElementExist(New_SanityLocators.flash_Button)) {
					minWait();
					clickBtn(New_SanityLocators.front_back_switcher);
					minWait();
				}
			}  catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in locators->changeTo_FrontCam()");
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->changeTo_FrontCam()");
			}
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
			}  catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in locators->swipe_NotificationBar()");
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->swipe_NotificationBar()");
			}	
		}
		
		public void swipe_QuickPanel_SecondPage() throws InterruptedException {
			try {
				minWait();
				aDriver.swipe(860, 660, 140, 660, 750);
				minWait();
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in locators->swipe_QuickPanel_SecondPage()");
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->swipe_QuickPanel_SecondPage()");
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
					clickBtn(New_SanityLocators.OK_1);
					minWait();
				} else {
					APP_LOGS.info("Requested Shortcut Already Enabled");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in locators->enable_Shortcuts_In_QuickPanel()");
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->enable_Shortcuts_In_QuickPanel()");
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
				if(isElementExist(New_SanityLocators.turnOff_Airplane_PopUp)) {
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
				clickBtn(New_SanityLocators.OK);
				minWait();
				clickBtn(New_SanityLocators.CANCEL);
				minWait();
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in locators->validate_Airplane_Enable()");
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->validate_Airplane_Enable()");
			}	
			sf.assertAll();
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
					clickBtn(New_SanityLocators.switch_On_State);
					APP_LOGS.info("Requested Shortcut is disabled in Quick Panel");
					minWait();
				} else {
					APP_LOGS.info("Requested Shortcut Already disabled");
				}
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in locators->disable_Shortcuts_QuickPanel()");
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->disable_Shortcuts_QuickPanel()");
			}	
		}
		
		public void validate_Airplane_Disable(String dailNum) throws InterruptedException, IOException {
			/* 
			 * Method can be Used Validate Airplane Mode activation via by making the call.
			 */
			SoftAssert sf = new SoftAssert();
			try {
				minWait();			
				minWait();
				if(!isElementExist(New_SanityLocators.turnOff_Airplane_PopUp)) {
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
				clickBtn(New_SanityLocators.OK);
				minWait();
			}catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in locators->validate_Airplane_Disable()");
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->validate_Airplane_Disable()");
			}	
			sf.assertAll();
		}
		
		public void delete_SMS() throws InterruptedException {
			/* This Method delete the First Thread in the List. */
			try {
				minWait();
				System.out.println(refNum);
				String refNum_O = New_SanityLocators.message_1stMsg.getText();
				String res = "";
				for (int i = 0; i < refNum_O.length(); i++) {
					if ((refNum_O.charAt(i) >= '0' && refNum_O.charAt(i) <= '9') || refNum_O.charAt(i) == '_' || refNum_O.charAt(i) == '+')
						res = res + refNum_O.charAt(i);
				}
				System.out.println(res);
				if(isElementExist(New_SanityLocators.message_1stMsg)) {
					if(New_SanityLocators.message_1stMsg.getText().equalsIgnoreCase(refNum)) {
						clickBtn(New_SanityLocators.firstSMS_InList);
						minWait();
						clickBtn(New_SanityLocators.moreOptions);
						minWait();
						clickBtn(New_SanityLocators.delete_Thread);
						minWait();
						clickBtn(New_SanityLocators.delete_Confirm);
						minWait();
					}
				}
				
				
				test.log(LogStatus.INFO, "SMS or MMS is deleted.");
			} catch (NoSuchElementException e) {			 
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
			wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.now_Text));	
			customWait(8000);
			try {
				if(isElementExist(New_SanityLocators.now_Text)||isElementExist(New_SanityLocators.not_Sent_Text)) {
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
		
		
		public void validate_ableToCntWifi(SoftAssert sa,String ssid,String pswd){

			try {
				minWait();
				connect_to_WiFi(ssid,pswd);
				
				boolean check1 = scrollToText("Connected");
				
					if(check1 == true){
						
						APP_LOGS.info("Successfully Wifi is connected in Secured Network");
						sa.assertTrue(true, "Successfully Wifi is connected in Secured Network");
						test.log(LogStatus.PASS,"Successfully Wifi is connected in Secured Network");	


					}
					else {
						
						APP_LOGS.info("Failed -> Wifi not connect in Secured Network");
						sa.assertFalse(false, "Failed -> Wifi not connect in Secured Network");
						test.log(LogStatus.FAIL, "Failed -> Wifi not connect in Secured Network");

					}

				} catch (org.openqa.selenium.NoSuchElementException e) {

					test.log(LogStatus.ERROR,"Error in locators->validate_ableToCntWifi()");
					
				}catch (Exception e) {
					test.log(LogStatus.ERROR,"Exeption in ->validate_ableToCntWifi()");
				}
			}
		public void turnOffWifi_adb() throws InterruptedException, IOException {
			/*
			 *  Method is to disable wifi using ADB cmd
			 * 
			 */

			try {		
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am broadcast -a io.appium.settings.wifi --es setstatus disable");
					minWait();
					
			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in locators->turnOffWifi_adb()");
			} catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->turnOffWifi_adb()");
			}	
		}
		
	public void clickOn_Battery() {

		try {

			scrollToTextClick("Battery");

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Battery()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->clickOn_Battery()");
		}
	}
	
	public void validate_DeviceCharging(SoftAssert sa){

		try {
			
			String batteryStatus = New_SanityLocators.battery_statusTxt.getText();			
			
				if(batteryStatus.equalsIgnoreCase("Charging")||batteryStatus.equalsIgnoreCase("Full")){
					
					APP_LOGS.info("Successfully DUT is in Charging");
					sa.assertTrue(true, "Successfully DUT is in Charging");
					test.log(LogStatus.PASS,"Successfully DUT is in Charging");	


				}
				else {
					
					APP_LOGS.info("Failed -> Dut is not charging");
					sa.assertFalse(false, "Failed -> Dut is not charging");
					test.log(LogStatus.FAIL, "Failed -> Dut is not charging");

				}

			} catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->validate_usbConnected()");
				
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->validate_usbConnected()");
			}
		}
		
	public void increaseDevice_Brightness(int result) {
		/*
		 * This method set the device to particular Brigthness
		 */
		try {
			
			getNotificationWindow();
			if(result >= 0 && result <=10) {
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 112 170");
				minWait();
			}
			else if(result >= 20 && result <=30) {
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 322 170");
				minWait();
			}
			else if(result >= 70 && result <=80) {
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 549 170");
				minWait();
			}
			else if(result >= 45 && result <=55) {
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 761 170");
				minWait();
			}
			else if(result >= 90 && result <=100) {
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 977 170");
				minWait();
			}
			
			aDriver.pressKeyCode(AndroidKeyCode.HOME);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->increaseDevice_Brightness()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exeption in ->increaseDevice_Brightness()");
		}
	}
	
	public void validate_Brightness(SoftAssert sa,int percent){

		try {
			
			aDriver.startActivity("com.android.settings", "com.android.settings.Settings$DisplaySettingsActivity");
			minWait();
			String check1 = New_SanityLocators.display_BrightnessPercentTxt.getText();
			
			String numericValue = "";
			for (int i = 0; i < check1.length(); i++) {
				if ((check1.charAt(i) >= '0' && check1.charAt(i) <= '9'))
					numericValue = numericValue + check1.charAt(i);
			}
			
		  int result = Integer.parseInt(numericValue);

		  if(result >= 0 && result <=10) {
			  	APP_LOGS.info("Device Brightness Successfully adjusted To -> " +check1 );
				sa.assertTrue(true, "Device Brightness Successfully adjusted To -> " +check1 );
				test.log(LogStatus.PASS, "Device Brightness Successfully adjusted To -> " +check1 );
			}
			else if(result >= 20 && result <=30) {
				APP_LOGS.info("Device Brightness Successfully adjusted To -> " +check1 );
				sa.assertTrue(true, "Device Brightness Successfully adjusted To -> " +check1 );
				test.log(LogStatus.PASS, "Device Brightness Successfully adjusted To -> " +check1 );
			}
			else if(result >= 70 && result <=80) {
				APP_LOGS.info("Device Brightness Successfully adjusted To -> " +check1 );
				sa.assertTrue(true, "Device Brightness Successfully adjusted To -> " +check1 );
				test.log(LogStatus.PASS, "Device Brightness Successfully adjusted To -> " +check1 );
			}
			else if(result >= 45 && result <=55) {
				APP_LOGS.info("Device Brightness Successfully adjusted To" +check1 );
				sa.assertTrue(true, "Device Brightness Successfully adjusted To" +check1 );
				test.log(LogStatus.PASS, "Device Brightness Successfully adjusted To" +check1 );
			}
			else if(result >= 90 && result <=100) {
				APP_LOGS.info("Device Brightness Successfully adjusted To -> " +check1 );
				sa.assertTrue(true, "Device Brightness Successfully adjusted To -> " +check1 );
				test.log(LogStatus.PASS, "Device Brightness Successfully adjusted To -> " +check1 );
		
			} else {
				APP_LOGS.info("Device Brightness Adjustment Failed");
				sa.fail();
				test.log(LogStatus.FAIL, "Device Brightness Adjustment Failed");
			}

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in locators->validate_Brightness()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_Brightness()");
		}
	}
	
	
		
		//////////////////////////////// Gobi /////////////////////////////////////////////////////
		
		public void Check_for_Scoutmodule() throws InterruptedException {
			SoftAssert sa =new SoftAssert();
			WebDriverWait wait = new WebDriverWait(aDriver, 60);
			try {
				minWait();
				String getName1 = New_SanityLocators.ScoutSetup.getText();
				String getName2 = New_SanityLocators.ScoutUtilities.getText();
				String getName3 = New_SanityLocators.ScoutSupport.getText();

				if(getName1.equalsIgnoreCase("Setup") && getName2.equalsIgnoreCase("Utilities") && 
						getName3.equalsIgnoreCase("Support") )
				{
					minWait();
					System.out.println(getName1);
					System.out.println(getName2);
					System.out.println(getName3);
					System.out.println("Scout modules are present");
					APP_LOGS.info("Scout modules are present");
					test.log(LogStatus.PASS, "Scout modules are present");
					aDriver.pressKeyCode(AndroidKeyCode.HOME);

				}else {
					minWait();
					System.out.println("Scout modules are not present");
					APP_LOGS.info("Scout modules are not present");
					aDriver.pressKeyCode(AndroidKeyCode.HOME);
				}
			}
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();

			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sa.assertAll();
		}

		public void Lock_UnLock_SIM_card() throws InterruptedException {
			SoftAssert sa =new SoftAssert();
			WebDriverWait wait = new WebDriverWait(aDriver, 60);
			try {
				minWait();
				scrollToText("Security & location");
				minWait();
				scrollToText("SIM card lock");
				minWait();
				clickBtn(New_SanityLocators.Switch);
				minWait();
				enterTextToInputField(New_SanityLocators.editPIN, "1234");
				minWait();
				clickBtn(multi_Loc_Strategy(New_SanityLocators.OK, New_SanityLocators.Ok, New_SanityLocators.OK_1,
						New_SanityLocators.OK_2, null, 777,852));
				minWait();
				System.out.println("SIM Lock done,PIN set as 1234");
				minWait();

				clickBtn(New_SanityLocators.Switch);
				minWait();
				enterTextToInputField(New_SanityLocators.editPIN, "1234");
				minWait();
				clickBtn(multi_Loc_Strategy(New_SanityLocators.OK, New_SanityLocators.Ok, New_SanityLocators.OK_1,
						New_SanityLocators.OK_2, null, 777,852));
				minWait();
				System.out.println("SIM UnLock done,with PIN 1234");
				minWait();
				APP_LOGS.info("Lock_UnLock_SIM_card");
				test.log(LogStatus.PASS, "Lock_UnLock_SIM_card");
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			}
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();

			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sa.assertAll();
		}

		public void deleteContacts() throws InterruptedException {
			try {
				minWait();
				System.out.println("IM in Contacts");
				if (isElementExist(New_SanityLocators.No_Contacts)) {
					APP_LOGS.info("No Contact"); 
					System.out.println("No Contact");
					minWait();
				} else {
					clickBtn(New_SanityLocators.deleteContactOptn1);
					minWait();
					clickBtn(New_SanityLocators.Selection_menu);
					minWait();
					if(isElementExist(New_SanityLocators.ALL_Selection_menu)) {
						clickBtn(New_SanityLocators.ALL_Selection_menu);
						minWait();
					}
					else {
						minWait();
						clickBtn(New_SanityLocators.one_Selection_menu);
					}
					clickBtn(New_SanityLocators.OKBtn1);
					minWait();
					clickBtn(New_SanityLocators.OKBtn);
					APP_LOGS.info("Contacts Deleted");
					minWait();
				}
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
		}

		public void addcontactsTO_SIM(String name,String Phone1,String Phone2,String email,SoftAssert sa) 
				throws InterruptedException {
			try {
				minWait();
				System.out.println("IM in Contacts");
				minWait();
				clickBtn(New_SanityLocators.ContactsMoreOptions);
				minWait();
				scrollText("SIM");
				minWait();
				clickBtn(New_SanityLocators.AccountSIMOPt);
				minWait();
				clickBtn(New_SanityLocators.AddcontactBtn);
				minWait();
				enterTextToInputField(New_SanityLocators.nameField, name);
				customWait(3000);
				enterTextToInputField(New_SanityLocators.phoneField, Phone1);
				customWait(3000);
				enterTextToInputField(New_SanityLocators.phoneField, Phone2);
				customWait(3000);
				enterTextToInputField(New_SanityLocators.emailField, email);
				customWait(3000);
				clickBtn(New_SanityLocators.SaveBtn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				System.out.println("Contacts added in SIM");
				APP_LOGS.info("Contacts added in SIM");
				test.log(LogStatus.PASS, "Contacts added in SIM");
				minWait();
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();

			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
		}

		public void addcontactsTO_Phone(String name,String Phone1,String email,String Address) throws InterruptedException {
			try {
				minWait();
				System.out.println("IM in Contacts");
				minWait();
				clickBtn(New_SanityLocators.ContactsMoreOptions);
				minWait();
				scrollToElements(New_SanityLocators.AccountsPhoneOPt1);
				//		scrollText("PHONE");
				minWait();
				clickBtn(New_SanityLocators.AccountsPhoneOPt1);
				minWait();
				clickBtn(New_SanityLocators.AddcontactBtn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				clickBtn(New_SanityLocators.Morefields);
				minWait();
				enterTextToInputField(New_SanityLocators.FirstnameField, name); 
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				scrollToElements(New_SanityLocators.phoneField);
				minWait();
				enterTextToInputField(New_SanityLocators.phoneField, Phone1);
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				scrollToElements(New_SanityLocators.EmailField);
				minWait();
				enterTextToInputField(New_SanityLocators.EmailField, email);
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				scrollToElements(New_SanityLocators.Addressfield);
				minWait();
				enterTextToInputField(New_SanityLocators.Addressfield, Address);
				customWait(3000);
				if (isElementExist(New_SanityLocators.OK)){
					clickBtn(New_SanityLocators.OK);
					minWait();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				clickBtn(New_SanityLocators.SaveBtn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				APP_LOGS.info("Contacts added in Phone");
				test.log(LogStatus.PASS, "Contacts added in Phone");
				minWait();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();

			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
		}

		public void deleteSIMContacts() throws InterruptedException {
			try {
				minWait();
				System.out.println("IM in Contacts");
				minWait();
				clickBtn(New_SanityLocators.ContactsMoreOptions);
				minWait();
				scrollText("SIM");
				minWait();
				clickBtn(New_SanityLocators.AccountSIMOPt);
				minWait();
				if (isElementExist(New_SanityLocators.No_Contacts)) {
					APP_LOGS.info("No Contact"); 
					System.out.println("No Contact");
					minWait();
				} else {
					clickBtn(New_SanityLocators.deleteContactOptn1);
					minWait();
					clickBtn(New_SanityLocators.Selection_menu);
					minWait();
					if(isElementExist(New_SanityLocators.ALL_Selection_menu)) {
						clickBtn(New_SanityLocators.ALL_Selection_menu);
						minWait();
					}
					else {
						minWait();
						clickBtn(New_SanityLocators.one_Selection_menu);
					}
					clickBtn(New_SanityLocators.OKBtn1);
					minWait();
					clickBtn(New_SanityLocators.OKBtn);
					test.log(LogStatus.PASS, "Contact Deleted");
					APP_LOGS.info("Contact Deleted");
					minWait();
				}
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
		}

		public void deletePHONEContacts() throws InterruptedException {
			try {
				minWait();
				System.out.println("IM in Contacts");
				minWait();
				clickBtn(New_SanityLocators.ContactsMoreOptions);
				minWait();
				scrollToElements(New_SanityLocators.AccountsPhoneOPt1);
				//		scrollText("PHONE");
				minWait();
				clickBtn(New_SanityLocators.AccountsPhoneOPt1);
				minWait();
				if (isElementExist(New_SanityLocators.No_Contacts)) {
					APP_LOGS.info("No Contact"); 
					System.out.println("No Contact");
					minWait();
				} else {
					clickBtn(New_SanityLocators.deleteContactOptn1);
					minWait();
					clickBtn(New_SanityLocators.Selection_menu);
					minWait();
					if(isElementExist(New_SanityLocators.ALL_Selection_menu)) {
						clickBtn(New_SanityLocators.ALL_Selection_menu);
						minWait();
					}
					else {
						minWait();
						clickBtn(New_SanityLocators.one_Selection_menu);
					}
					clickBtn(New_SanityLocators.OKBtn1);
					minWait();
					clickBtn(New_SanityLocators.OKBtn);
					test.log(LogStatus.PASS, "Contact Deleted");
					APP_LOGS.info("Contact Deleted");
					minWait();
				}
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
		}

		public void Make_call_to_contact() throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(aDriver, 60);
			try {
				minWait();
				System.out.println("IM in Contacts");
				minWait();
				clickBtn(New_SanityLocators.ContactsMoreOptions);
				minWait();
				clickBtn(New_SanityLocators.contactsBtn);
				minWait();
				clickBtn(New_SanityLocators.Test_1);
				minWait();
				clickBtn(New_SanityLocators.callBtn);

				wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.KeypadOPT));
				if(isElementExist(New_SanityLocators.KeypadOPT)) {
					clickBtn(New_SanityLocators.endBtn);
					minWait();
					System.out.println("Call initiated");
					APP_LOGS.info("Call initiated");
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.HOME);
					minWait();
					test.log(LogStatus.PASS, "Call initiated,Test case Status is PASS");
				}else {
					APP_LOGS.info("Call did't initiated");
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.HOME);
					minWait();
					test.log(LogStatus.INFO, "Call did't initiated,network issue");
				}
			}	
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();

			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
		}

		public void clickOn_Networks_and_Internet() {
			try {
				minWait();
				scrollToText("Network & Internet");
				minWait();
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in clickOn_Networks_and_Internet() ");
				e.printStackTrace();
			}
		}

		public void ON_Switch(String switch_To_ON,SoftAssert sa) {
			try {
				minWait();

				aDriver.findElementByXPath("//*[contains(@text,'"+switch_To_ON+"')]/../..//*[@text='OFF']").click();
				minWait();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in clicking Switch");
				e.printStackTrace();
			}
			sa.assertAll();
		}

		public boolean scrollToTextContains(String text) {
			/*
				  Method used to select an element on the page by scrolling the Scroll View/List View
			 */
			boolean check = false;
			try {  
				String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
				String textElement = ".scrollIntoView(new UiSelector().description(\""+ text +"\"))";
				aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
				APP_LOGS.info("Searched application is found sucessfully : ");
				check = true;
				return check;
			}		
			catch(NoSuchElementException e) {
				return check;
			}
		}

		public void checkWifiConnected1() throws InterruptedException {
			try {
				customWait(4000);
				if(isElementExist(New_SanityLocators.connectedWIFI1)) {
					String getTxt = New_SanityLocators.connectedWIFI1.getText();
					System.out.println("Connected "+getTxt);
					//					test.log(LogStatus.INFO, "Wi-Fi is Connected");
				}
				else {
					System.out.println("Not Connected");
					selectSSIDwifi();	
					enterPassword();
				}
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in WifiConnected1");
				e.printStackTrace();
			}

			//		catch (Exception e) {
			//			// TODO Auto-generated catch block
			//			e.printStackTrace();
			//		}
		}

		public void checkWifiConnected() throws InterruptedException {
			try {
				customWait(4000);
				if(isElementExist(New_SanityLocators.connectedWIFI1)) {
					String getTxt = New_SanityLocators.connectedWIFI1.getText();
					System.out.println(getTxt);
					test.log(LogStatus.INFO, "Wi-Fi is Connected : will be disconnecting");
					disconnectSSIDifConnected();
				}
				else {
					System.out.println("Not Connected");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void disconnectSSIDifConnected() throws InterruptedException {
			/*
			 * disconnect wifi[SSID]if IDC wifi connected
			 */
			SoftAssert Sa = new SoftAssert();
			try {
				selectSSIDwifi();
				customWait(4000);
				clickBtn(New_SanityLocators.wifi_IDC_ForgetBtn);
				APP_LOGS.info("IDC available secured wifi is disconnected");
				System.out.println("Disconnected");
				customWait(4000);
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Wi-Fi Forget button: No such Element found");		
			}
		}

		public void selectSSIDwifi() throws InterruptedException {
			/*
			 * Select IDC wifi which is available
			 */
			SoftAssert Sa = new SoftAssert();
			try {
				customWait(4000);
				for(int i=1; i<=50; i++) {
					if(isElementExist(New_SanityLocators.wifi_IDC)) {
						customWait(2000);
						clickBtn(New_SanityLocators.wifi_IDC);
						APP_LOGS.info("IDC available secured wifi is Selected");
						System.out.println("IDC available secured wifi is Selected");
						break;
					}
					else if(isElementExist(New_SanityLocators.wifi_Dellas)) {
						minWait();
						clickBtn(New_SanityLocators.wifi_Dellas);
						APP_LOGS.info("Dellas available secured wifi is Selected");
						break;
					}
					else if(isElementExist(New_SanityLocators.wifi_Cannada)) {
						minWait();
						clickBtn(New_SanityLocators.wifi_Cannada);
						APP_LOGS.info("Cannada available secured wifi is Selected");
						break;
					}
					else {
						minWait();
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
						continue;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				APP_LOGS.info("WIFI : No such Element found");
				test.log(LogStatus.ERROR, "Wi-Fi is not available : No such Element found");
			}
		}

		public void enterPassword() throws InterruptedException, IOException {
			/*
			 * enter Password for SSID
			 */
			try {
				minWait();
				if(isElementExist(New_SanityLocators.SSIDTxt)) {
					String getSSIDTitle = New_SanityLocators.SSIDTxt.getText();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					changeToNumberMode();
					customWait(2000);
					if(getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
						minWait();
						clickBtn(New_SanityLocators.wifi_IDC_Psswd);
						customWait(4000);
						//					enterTextToInputField(New_SanityLocators.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
						Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");		
						customWait(3000);	
					}
					minWait();
					String psswrd = New_SanityLocators.wifi_IDC_Psswd.getText();
					System.out.println(psswrd);
					customWait(1000);
					clickBtn(New_SanityLocators.wifi_IDC_ConnectBtn);
					customWait(3000);
				}
			} catch (Exception e) {
				e.printStackTrace();
				APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			}
		}

		public void changeToNumberMode() throws InterruptedException {

			try {
				minWait();
				New_SanityLocators.wifi_IDC_Psswd.sendKeys("123");
				customWait(1500);
				String text = New_SanityLocators.wifi_IDC_Psswd.getText();
				System.out.println(text);
				if(!text.equals("123")) {
					for (int i = 0; i < 3; i++) {
						aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
						minWait();
					}
				}
				New_SanityLocators.wifi_IDC_Psswd.clear();
			} catch (Exception e) {
				e.printStackTrace();
				APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			}
		}

		public void remove_GoogleAcccount_Orio(SoftAssert sa) {
			//remove added google Account if any 
			try {
				scrollToText("Users & accounts");
				//				clickOnAccounts();
				minWait();
				if(isElementExist(New_SanityLocators.connectedAccount)) {
					System.out.println("Account is present");
					minWait();
					clickBtn(New_SanityLocators.connectedAccount);
					minWait();
					clickBtn(New_SanityLocators.REMOVE_ACCOUNT);
					minWait();
					clickBtn(New_SanityLocators.REMOVE_ACCOUNT);
					customWait(3000);
				}
				else {
					System.out.println("No Google account present");
				}
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception");
				e.printStackTrace();
			}
			sa.assertAll();
		}

		public void navigateTo_AddGoogleAccount_Orio(SoftAssert sa) {
			//navigate to settings option Add google Account
			WebDriverWait wt = new WebDriverWait(aDriver, 60);
			try {
				scrollToText("Users & accounts");
				clickBtn(New_SanityLocators.add_Account);
				minWait();
				clickBtn(New_SanityLocators.google_Account);
				customWait(2000);
				//		wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
				minWait();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in navigateTo_AddGoogleAccount_Orio() ");
				e.printStackTrace();
			}
			sa.assertAll();
		}

		public void add_GoogleAccount(String emailId, String password,SoftAssert sa) {
			WebDriverWait wt = new WebDriverWait(aDriver, 60);
			try {
				minWait();
				enterTextToInputField(New_SanityLocators.email_googleAcnt, emailId);
				minWait();
				clickBtn(New_SanityLocators.next);
				minWait();
				customWait(6000);
				//		wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Welcome' or @resource-id='profileIdentifier']")));
				minWait();
				enterTextToInputField(New_SanityLocators.password_googleAcnt, password);
				customWait(5000);
				clickBtn(New_SanityLocators.next);
				customWait(3000);
				if(isElementExist(New_SanityLocators.I_agreeOtn))
				{
					clickBtn(New_SanityLocators.I_agreeOtn);
					minWait();
				}
				clickBtn(multi_Loc_Strategy(New_SanityLocators.skip_, New_SanityLocators.MOREOtn, null, null, null,0,0));
				scroll() ;
				scroll() ;
				minWait();
				//			clickBtn(multi_Loc_Strategy(New_SanityLocators.skip_, New_SanityLocators.MOREOtn, null, null, null,0,0));
				//			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.Button\")).scrollIntoView(new UiSelector().textContains(\"Skip\"))").click();
				minWait();
				//			    scrollToText("Skip");
				//			if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
				//				try {
				//					aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.webkit.WebView\")).scrollIntoView(new UiSelector().textContains(\"Yes\"))").click();
				//					minWait();
				//				}
				//				catch (org.openqa.selenium.NoSuchElementException e) {
				//					test.log(LogStatus.ERROR, "Error in the locators");
				//					e.printStackTrace();
				//				}
				//				catch (Exception e) {
				//					test.log(LogStatus.ERROR, "Exception");
				//					e.printStackTrace();
				//				}
				//				//				catch (Exception e) {
				//				//				}
				//			}		
				//			customWait(1000);
				//			clickBtn(New_SanityLocators.i_agree);
				//			minWait();
				//			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
				//			minWait();
				//			clickBtn(New_SanityLocators.MORE);
				//			minWait();
				clickBtn(multi_Loc_Strategy(New_SanityLocators.skip_, New_SanityLocators.MOREOtn, New_SanityLocators.ACCEPTorAGREE,
						null, null,0,0));
				customWait(3000);
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception");
				e.printStackTrace();
			}
			sa.assertAll();
			//		catch (Exception e) {
			//		}
		}

		public void validate_Installed_App(String appName,SoftAssert soft) {

			try {
				boolean check = false;
				clickOnAppList();
				enterTextToInputField(New_SanityLocators.searchApps, appName);
				minWait();
				if (isElementExist(New_SanityLocators.apkExtractor)) {
					check=true;
					soft.assertTrue(check, "TestCase Valiation is PASS");
					test.log(LogStatus.PASS, "APK installed " + "\" "+ appName +"\"" + " Successfully");
				} else {		
					test.log(LogStatus.FAIL,"APK didn't Installed "+ "\" "+ appName + "\"" +"at iteration: ");
					soft.fail();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in validate_Installed_App");
				soft.fail();
				e.printStackTrace();
			}	
			//		catch (Exception e) {
			//			soft.fail();
			//		}

		}

		public void clickOnAppList() throws InterruptedException {

			//try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);			
			customWait(2000);			
			clickBtn(New_SanityLocators.app_List);
			minWait();
			//	} catch (Exception e) {
			//	e.printStackTrace();
			//	}

		}

		public void CheckInstalled_App(String appName) throws InterruptedException {
			//Search Application is installed and uninstall

			try {
				clickOnAppList();

				enterTextToInputField(New_SanityLocators.searchApps, appName);
				minWait();
				System.out.println("Checking....");
				if (isElementExist(New_SanityLocators.apkExtractor)) {
					System.out.println("Yes App is Present...");
					minWait();
					launch_APP(New_SanityLocators.PlayStore);
					unInstall_App("Twitter");

				}

				else {
					System.out.println("App is not there");
					launch_APP(New_SanityLocators.PlayStore);
					unInstall_App(appName);		
				}
				System.out.println("Uninstalled");

			} 
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locator");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in CheckInstalled_App");
				e.printStackTrace();
			}
		}

		public void TurnOFF_App_Autoupdate() throws InterruptedException {
			//TurnOFF App Auto update in playstore settings

			try {
				minWait();
				clickBtn(multi_Loc_Strategy(New_SanityLocators.more_Btn2, New_SanityLocators.more_Btn,
						null, null, null, 0,0));
				minWait();
				scrollToText("Settings");
				minWait();
				clickBtn(New_SanityLocators.Auto_update_apps);
				minWait();
				clickBtn(New_SanityLocators.Dont_auto_update_apps);
				minWait();
				clickBtn(New_SanityLocators.done_Btn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
				minWait();
				System.out.println("App Autoupdate is turn off");

			} 
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locator");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in CheckInstalled_App");
				e.printStackTrace();
			}

		}

	/*	public void launch_APP(AndroidElement appToClick) throws InterruptedException {
			try {
				clickOnAppList();
				for (int i = 0; i < 6; i++) {
					if (isElementExist(appToClick)) {
						//						test.log(LogStatus.INFO, "\""+appToClick.getText()+"\" app launched.");
						clickBtn(appToClick);
						minWait();
						break;
					} else {
						scroll();
						minWait();
					}
				}
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in launch_APP()");
				e.printStackTrace();
			}
			//		catch (Exception e) {
			//			e.printStackTrace();
			//		}
		}*/

		public void unInstall_App(String appName) {

			WebDriverWait wait = new WebDriverWait(aDriver, 180);
			try {
				minWait();
				TurnOFF_App_Autoupdate();
				minWait();
				clickBtn(multi_Loc_Strategy(New_SanityLocators.search_PlayStore2, New_SanityLocators.google_Play,
						null, null, null, 0,0));
				minWait();
				enterTextToInputField(multi_Loc_Strategy(New_SanityLocators.search_PlayStore2, New_SanityLocators.google_Play,
						New_SanityLocators.search_PlayStore, null, null, 0,0), appName);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				customWait(5000);
				if(isElementExist(New_SanityLocators.installed_Playstore1)) {
					customWait(3000);
					clickBtn(New_SanityLocators.installed_Playstore1);
				}
				if (isElementExist(New_SanityLocators.UNINSTALL)) {
					System.out.println("Uninstalling");
					minWait();
					clickBtn(New_SanityLocators.UNINSTALL);
					minWait();
					clickBtn(New_SanityLocators.OK);
					minWait();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
					minWait();					
				} 
				else if(isElementExist(New_SanityLocators.UNINSTALL1)){
					System.out.println("Uninstalling....");
					minWait();
					clickBtn(New_SanityLocators.UNINSTALL1);
					minWait();
					clickBtn(New_SanityLocators.OK);
					minWait();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
					minWait();
				}

				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (Exception e) {

			}
		}

		public void install_App(String appName,WebElement element) throws InterruptedException, IOException {

			if(isElementExist(New_SanityLocators.account_Page)) {
				clearRecentApps();
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell pm clear com.android.vending"); //  Clear Playstore cache
				minWait();
				launch_APP(New_SanityLocators.PlayStore);
			}

			WebDriverWait wait = new WebDriverWait(aDriver, 120);
			try {
				System.out.println("To Be install "+appName);
				customWait(3000);
				clickBtn(New_SanityLocators.google_Play);
				customWait(3000);
				enterTextToInputField(multi_Loc_Strategy(New_SanityLocators.search_PlayStore,
						New_SanityLocators.search_PlayStore1, null, null, null, 216,102),appName);
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				customWait(8000);
				System.out.println("Scrolling");
				scrollToElementWithDpadDown(element);
				minWait();

				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='INSTALL']")));
				customWait(6000);
				if(isElementExist(New_SanityLocators.installed_Playstore1)) {
					customWait(3000);
					clickBtn(New_SanityLocators.installed_Playstore1);
				}
				if (isElementExist(New_SanityLocators.INSTALL)) {
					minWait();
					clickBtn(New_SanityLocators.INSTALL);
					minWait();
					//		clickBtn(New_SanityLocators.ACCEPT);
					minWait();
					wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='UNINSTALL']")));
					minWait();
				} else {
					test.log(LogStatus.ERROR, "App already installed.");
				}
				System.out.println("Installed");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				test.log(LogStatus.ERROR, "Exception in install_App() ");
				e.printStackTrace();
			}
			//		catch (Exception e) {
			//			//                e.printStackTrace();
			//		}
		}

		public void scrollToElementWithDpadDown(WebElement element) {
			/*
			 * Clicks up button till element is available
			 */
			System.out.println("Clicking Element1");
			if(isElementExist(element)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				System.out.println("Clicking Element");
				element.click();
			}
			else {
				System.out.println("Clicking First Element");
				clickBtn(New_SanityLocators.firstApp_Suggetion);
			}
		}

		public void PlayandPause_music(SoftAssert sa) throws InterruptedException {
			try {
				minWait();
				if (isElementExist(New_SanityLocators.skipBtn)) {
					minWait();
					clickBtn(New_SanityLocators.skipBtn);
					minWait();
					APP_LOGS.info("SkipBtn is there"); 
					System.out.println("SkipBtn is there");
					minWait();
				} else {
					minWait();
					System.out.println("SkipBtn is not there");
					minWait();
				}
				minWait();
				clickBtn(New_SanityLocators.searchOpt);
				minWait();
				enterTextToInputField(multi_Loc_Strategy(New_SanityLocators.Search1, New_SanityLocators.Search2,
						null, null, null, 204,94), "Ninja Tuna");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				minWait();
				clickBtn(New_SanityLocators.musicfile);
				minWait();
				clickBtn(New_SanityLocators.playBtn);
				customWait(6000);
				clickBtn(New_SanityLocators.play_pause_Btn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				minWait();
				APP_LOGS.info("play_pause working fine");
				test.log(LogStatus.PASS, "play_pause working fine");
				minWait();

			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();
		}

		public void Configure_Gmail(SoftAssert sa) throws InterruptedException {
			try {
				minWait();

				if(isElementExist(New_SanityLocators.skipOtn) || isElementExist(New_SanityLocators.gotitBtn) )
				{
					minWait();

					clickBtn(multi_Loc_Strategy(New_SanityLocators.skipOtn, New_SanityLocators.gotitBtn, null, null, null,0,0));
					customWait(5000);
					clickBtn(multi_Loc_Strategy(New_SanityLocators.TAKE_ME_TO_GMAIL, null, null, null, null,0,0));
					minWait();
						
				}
				if (isElementExist(New_SanityLocators.next))
				{
				clickBtn(New_SanityLocators.next);
				}

				if (isElementExist(New_SanityLocators.OK))
				{
				clickBtn(New_SanityLocators.OK);
				}


				isElementExist(multi_Loc_Strategy(New_SanityLocators.PrimaryOtn, New_SanityLocators.Search_mail, 
						null, null, null,0,0));
				APP_LOGS.info("Gmail main page"); 
				System.out.println("Gmail main page");
				minWait();
				test.log(LogStatus.PASS, "Gmail Configuration done");
				minWait();

				// 
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}


		public void Configure_browser_URL(SoftAssert sa) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(aDriver, 120);
			try {
				minWait();

				if(isElementExist(New_SanityLocators.Accept_continuebtn))
				{
					minWait();
					clickBtn(New_SanityLocators.Accept_continuebtn);
					wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.Nextbtn));
				}	

				if(isElementExist(New_SanityLocators.Nextbtn))
				{
					minWait();
					clickBtn(New_SanityLocators.Nextbtn);
					wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(New_SanityLocators.No_thanksbtn2,
							New_SanityLocators.No_thanksbtn, null,null, null, 48,1764)));
				}
				if(isElementExist(New_SanityLocators.No_thanksbtn) || isElementExist(New_SanityLocators.No_thanksbtn2))
				{
					minWait();
					clickBtn(multi_Loc_Strategy(New_SanityLocators.No_thanksbtn2, New_SanityLocators.No_thanksbtn, null,
							null, null, 48,1764));
					customWait(4000);
				}
				customWait(10000);

				Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d https://www.bbc.com");
				wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.Homepage));
				//			isElementExist(multi_Loc_Strategy(New_SanityLocators.bbcURL, New_SanityLocators.bbcURL2, null, null, null,0,0));
				APP_LOGS.info("browser configured for bbc"); 
				System.out.println("browser configured for bbc");
				test.log(LogStatus.PASS, "browser Configuration done");
				minWait();
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}

		public void Configure_PHOTOS(SoftAssert sa) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(aDriver, 120);
			try {
				customWait(5000);
				if(isElementExist(New_SanityLocators.Notnow))
				{
					minWait();
					clickBtn(New_SanityLocators.Notnow);
					customWait(5000);
				}	

				if(isElementExist(New_SanityLocators.Backup_sync1)  || isElementExist(New_SanityLocators.Backup_sync2) )
				{
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait();
					clickBtn(New_SanityLocators.Keep_off);
					customWait(5000);
				}
				APP_LOGS.info("PHOTOS configured"); 
				System.out.println("PHOTOS configured");
				test.log(LogStatus.PASS, "PHOTOS Configuration done");
				minWait();
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}

		public void Configure_camera(SoftAssert sa) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(aDriver, 120);
			try {
				customWait(3000);
				clickBtn(New_SanityLocators.shutter_button);
				wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.preview_thumb));
				minWait();
				clickBtn(New_SanityLocators.video_button);
				customWait(15000);
				clickBtn(New_SanityLocators.video_button);
				wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.preview_thumb));
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.HOME);

				APP_LOGS.info("camera configured"); 
				System.out.println("camera configaration done");
				minWait();
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}

		public void Search_and_play(SoftAssert sa) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(aDriver, 120);
			try {
				//			customWait(3000);
				//			if(isElementExist(New_SanityLocators.NOT_NOWBtn)){
				//				minWait();
				//				System.out.println("NOT_NOWBtn is there");
				//				clickBtn(New_SanityLocators.NOT_NOWBtn);
				//				customWait(3000);
				//			}
				//			else{
				//				System.out.println("NOT_NOWBtn is not there");
				//			}
				//			wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(New_SanityLocators.SearchOpn1,
				//					New_SanityLocators.SearchOpn2, null, null, null, 780,72)));
				//			clickBtn(multi_Loc_Strategy(New_SanityLocators.SearchOpn1,
				//					New_SanityLocators.SearchOpn2, null, null, null, 780,72));
				//
				//			wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.Search_YouTube));
				//			minWait();
				//			enterTextToInputField(New_SanityLocators.Search_YouTube, "WWE ");
				//			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
//				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start  https://www.youtube.com/watch?v=JELtUgW7otM");
				customWait(10000);

	//
//				clickBtn(multi_Loc_Strategy(New_SanityLocators.Screen2, New_SanityLocators.Screen, null, null, null, 478,351));
//				clickBtn(multi_Loc_Strategy(New_SanityLocators.Screen2, New_SanityLocators.Screen, null, null, null, 478,351));
//				wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(New_SanityLocators.Full_Screen2, 
//						New_SanityLocators.Full_Screen,New_SanityLocators.Full_Screen3, null, null, 960,553)));
//				clickBtn(multi_Loc_Strategy(New_SanityLocators.Full_Screen2, New_SanityLocators.Full_Screen,
//						New_SanityLocators.Full_Screen3, null, null, 960,553));			
//				customWait(10000);
				aDriver.pressKeyCode(AndroidKeyCode.HOME);

				APP_LOGS.info("Youtube Stream done"); 
				System.out.println("Youtube Stream done");
				test.log(LogStatus.PASS, "Youtube Stream done");
				minWait();
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}

		public void Configure_calendar(SoftAssert sa) throws InterruptedException {
			try {
				customWait(3000);
				if(isElementExist(New_SanityLocators.right_arrow)){
					clickBtn(New_SanityLocators.right_arrow);	
				}
				minWait();
				if(isElementExist(New_SanityLocators.right_arrow)){
					clickBtn(New_SanityLocators.right_arrow);	
				}
				minWait();
				if(isElementExist(New_SanityLocators.GOT_it)){
					clickBtn(New_SanityLocators.GOT_it);	
				}
				customWait(5000);

				if(isElementExist(New_SanityLocators.Tap_to_create1) || isElementExist(New_SanityLocators.Tap_to_create2) )
				{
					minWait();
					System.out.println("No event is there");

				}
				clickBtn(multi_Loc_Strategy(New_SanityLocators.action_button, null,null, null, null,0,0));
				minWait();
				clickBtn(multi_Loc_Strategy(New_SanityLocators.EventBtn2, New_SanityLocators.EventBtn1,null, null, null,0,0));
				minWait();
				enterTextToInputField(New_SanityLocators.Enter_title, "Test Event");
				minWait();
				clickBtn(New_SanityLocators.SwitchBtn);
				minWait();
				aDriver.hideKeyboard();
				minWait();
				scrollToText("Add location");
				minWait();
				if(isElementExist(New_SanityLocators.permissionPopUp)) {
					check=true;
					minWait();
					for(int i=0;i<3;i++){
						minWait();
						clickBtn(New_SanityLocators.allowBtn);
					}
				}
				minWait();
				enterTextToInputField(New_SanityLocators.Add_location, "Bengaluru");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				minWait();
				scrollToText("Add note");
				minWait();
				aDriver.hideKeyboard();
				customWait(2000);
				System.out.println(p_Id);
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text Testnote");

				customWait(2000);
				clickBtn(New_SanityLocators.Save);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				minWait();
				APP_LOGS.info("calander notification created"); 
				System.out.println("calander notification created");
				minWait();
				test.log(LogStatus.PASS, "calander notification created");
				minWait();
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}

		public void Check_for_notification(SoftAssert sa) throws InterruptedException {
			try {
				customWait(3000);
				if(isElementExist(New_SanityLocators.right_arrow)){
					clickBtn(New_SanityLocators.right_arrow);	
				}
				minWait();
				if(isElementExist(New_SanityLocators.right_arrow)){
					clickBtn(New_SanityLocators.right_arrow);	
				}
				minWait();
				if(isElementExist(New_SanityLocators.GOT_it)){
					clickBtn(New_SanityLocators.GOT_it);	
				}
				customWait(2000);

				clickBtn(New_SanityLocators.eventBtn);
				customWait(2000);
				if(isElementExist(New_SanityLocators.Test_Event))
				{
					minWait();
					System.out.println("calander notification is there");
					minWait();
					APP_LOGS.info("calander notification is there"); 
					minWait();
					test.log(LogStatus.PASS, "calander notification is there");
					minWait();
				}

				clickBtn(New_SanityLocators.More_options);
				minWait();
				clickBtn(New_SanityLocators.Delete2);
				minWait();
				clickBtn(New_SanityLocators.Delete);
				minWait();


			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}

		public void send_messaging(SoftAssert sa) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(aDriver, 60);
			try {
				customWait(3000);
				clickBtn(New_SanityLocators.createBtn);
				wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.TO_Field));
				enterTextToInputField(New_SanityLocators.TO_Field,refNum );
				customWait(2000);
				enterTextToInputField(New_SanityLocators.Type_message,"Test message");
				minWait();
				clickBtn(New_SanityLocators.sendBtn);
				minWait();
				if(isElementExist(New_SanityLocators.now_Text1)||isElementExist(New_SanityLocators.justnow_Text)||
						isElementExist(New_SanityLocators.not_Sent_Text1)||isElementExist(New_SanityLocators.sending_Txt)) {
					APP_LOGS.info("Message sent Succeccfully");
					System.out.println("Message sent Succeccfully");
					test.log(LogStatus.PASS, "Message sent Succeccfully");
				}
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}

		public void send_MSISDN_mailId_url_simultanously(SoftAssert sa) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(aDriver, 60);
			try {
				customWait(3000);
				clickBtn(New_SanityLocators.createBtn);
				wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.TO_Field));
				enterTextToInputField(New_SanityLocators.TO_Field,refNum );
				customWait(2000);
				enterTextToInputField(New_SanityLocators.Type_message,refNum);
				minWait();
				clickBtn(New_SanityLocators.sendBtn);
				minWait();
				wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(New_SanityLocators.now_Text1,
						New_SanityLocators.justnow_Text, New_SanityLocators.not_Sent_Text1, New_SanityLocators.sending_Txt,
						null, 0,0)));
				if(isElementExist(New_SanityLocators.now_Text1)||isElementExist(New_SanityLocators.justnow_Text)||
						isElementExist(New_SanityLocators.not_Sent_Text1)||isElementExist(New_SanityLocators.sending_Txt)) {
					APP_LOGS.info("MSISDN sent Succeccfully");
					System.out.println("MSISDN sent Succeccfully");
					test.log(LogStatus.PASS, "MSISDN sent Succeccfully");
				}
				minWait();
				enterTextToInputField(New_SanityLocators.Type_message,"test@gmail.com");
				minWait();
				clickBtn(New_SanityLocators.sendBtn);
				minWait();
				wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(New_SanityLocators.now_Text1,
						New_SanityLocators.justnow_Text, New_SanityLocators.not_Sent_Text1, New_SanityLocators.sending_Txt,
						null, 0,0)));
				if(isElementExist(New_SanityLocators.now_Text1)||isElementExist(New_SanityLocators.justnow_Text)||
						isElementExist(New_SanityLocators.not_Sent_Text1)||isElementExist(New_SanityLocators.sending_Txt)) {
					APP_LOGS.info("mailId sent Succeccfully");
					System.out.println("mailId sent Succeccfully");
					test.log(LogStatus.PASS, "mailId sent Succeccfully");
				}
				minWait();
				enterTextToInputField(New_SanityLocators.Type_message,"https://test.sonimcloud.com");
				minWait();
				clickBtn(New_SanityLocators.sendBtn);
				minWait();
				wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(New_SanityLocators.now_Text1,
						New_SanityLocators.justnow_Text, New_SanityLocators.not_Sent_Text1, New_SanityLocators.sending_Txt,
						null, 0,0)));
				if(isElementExist(New_SanityLocators.now_Text1)||isElementExist(New_SanityLocators.justnow_Text)||
						isElementExist(New_SanityLocators.not_Sent_Text1)||isElementExist(New_SanityLocators.sending_Txt)) {
					APP_LOGS.info("url sent Succeccfully");
					System.out.println("url sent Succeccfully");
					test.log(LogStatus.PASS, "url sent Succeccfully");
				}
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}

		public void receive_messaging(SoftAssert sa) {

			// To validate MT Message User should be inside Messaging APP of Primary Device.
			try {

				// Below Code To clear Battery PopUp.
				//			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083"); 
				minWait();
				if (r_b_No.contains("8A.")) {
					if (r_b_No.contains("-10.")||r_b_No.contains("-30.")) {

						System.out.println("SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS");

						Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

						/*minWait();
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
						minWait();*/
					} else if(r_b_No.contains("-11.")||r_b_No.contains("-12.")||r_b_No.contains("-18.")||r_b_No.contains("-26.")||r_b_No.contains("-29.")){
						/*minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.google.android.apps.messaging");
						customWait(2000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
						customWait(6000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 1756");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 930 976");
						minWait();*/
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

					}else if(r_b_No.contains("-15.")){
						minWait();
						/*System.out.println("IN Android O");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.verizon.messaging.vzmsgs");
						customWait(4000);
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
						customWait(6000);
						System.out.println("Sending Message...");
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1769");
						minWait();
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 948 1022");
						minWait();*/
						Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

					}
				} else if (r_b_No.contains("5SA.")){
					minWait();
					/*Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am force-stop com.android.mms");
					customWait(2000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell am start -a android.intent.action.SENDTO -d sms:"+pryNum+" --es sms_body \""+AutomationMessagee+"\" --ez exit_on_sent true");
					customWait(6000);
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 66");
					minWait();*/
					Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call isms 7 i32 0 s16 \"com.android.mms.service\" s16 "+pryNum+" s16 \"null\" s16 \"SMAT\" s16 \"null\" s16 \"null\"");

				}

				test.log(LogStatus.PASS, "SMS received Succeccfully");

			}  catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR,"Error in locators->sendSMS_fromRefDevice()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exeption in ->sendSMS_fromRefDevice()");
			}
			sa.assertAll();
		}

		public void addcontactsTO_Phone(SoftAssert sa) throws InterruptedException {
			try {
				minWait();
				System.out.println("IM in Contacts");
				minWait();
				clickBtn(New_SanityLocators.ContactsMoreOptions);
				minWait();
				scrollToElements(New_SanityLocators.AccountsPhoneOPt1);
				//		scrollText("PHONE");
				minWait();
				clickBtn(New_SanityLocators.AccountsPhoneOPt1);
				minWait();
				clickBtn(New_SanityLocators.AddcontactBtn);
				minWait();
				enterTextToInputField(New_SanityLocators.FirstnameField, "Refname"); 
				customWait(3000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				enterTextToInputField(New_SanityLocators.phoneField, refNum);
				customWait(3000);
				clickBtn(New_SanityLocators.SaveBtn);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				minWait();
				APP_LOGS.info("Contacts added in Phone");
				System.out.println("Contacts added in Phone");
				minWait();

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
		}

		public void send_SMS_to_Saved_Contact(SoftAssert sa) throws InterruptedException {
			WebDriverWait wait = new WebDriverWait(aDriver, 120);
			try {
				customWait(3000);
				clickBtn(New_SanityLocators.createBtn);
				wait.until(ExpectedConditions.visibilityOf(New_SanityLocators.TO_Field));
				//			enterTextToInputField(New_SanityLocators.TO_Field,refNum);

				customWait(2000);
				clickBtn(New_SanityLocators.Type_message);
				enterTextToInputField(New_SanityLocators.Type_message,"Test message");
				minWait();
				clickBtn(New_SanityLocators.add_con_Btn);
				minWait();
				scrollToText("Refname");
				minWait();
				clickBtn(New_SanityLocators.Ok);
				minWait();
				wait.until(ExpectedConditions.visibilityOf(multi_Loc_Strategy(New_SanityLocators.sendbtn, New_SanityLocators.sendBtn,
						null, null, null, 0,0)));
				clickBtn(multi_Loc_Strategy(New_SanityLocators.sendbtn, New_SanityLocators.sendBtn,null, null, null, 0,0));
				minWait();
				if(isElementExist(New_SanityLocators.now_Text1)||isElementExist(New_SanityLocators.justnow_Text)||
						isElementExist(New_SanityLocators.not_Sent_Text1)||isElementExist(New_SanityLocators.sending_Txt)) {
					APP_LOGS.info("Message sent Succeccfully to saved contact");
					System.out.println("Message sent Succeccfully to saved contact");
					test.log(LogStatus.PASS, "Message sent Succeccfully to saved contact");
					aDriver.pressKeyCode(AndroidKeyCode.BACK);
					aDriver.pressKeyCode(AndroidKeyCode.HOME);
				}
			} 
			catch (org.openqa.selenium.NoSuchElementException e) {

				test.log(LogStatus.ERROR, "Error in the locators");
				e.printStackTrace();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such element Found");
			}
			sa.assertAll();

		}

		public void makeCall(String contactNo) {
			try {
				if(isElementExist(New_SanityLocators.keypad)) {
					clickBtn(New_SanityLocators.keypad);
					minWait();
				}if(isElementExist(New_SanityLocators.enterNumber)) {
					enterTextToInputField(New_SanityLocators.enterNumber,contactNo);
					minWait();
				}if(isElementExist(New_SanityLocators.call)) {
					clickBtn(New_SanityLocators.call);
				}
			}catch (NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in the locators => makeCall()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exception in functionality=> makeCall()");
				e.printStackTrace();
			}
		}
		
		public void validate_Airplane_Enable(SoftAssert soft) throws InterruptedException, IOException {
			/* 
			* Method can be Used Validate Airplane Mode activation via by making the call.
			*/
			try {
			customWait(2000);
			if(isElementExist(New_SanityLocators.turnOff_Airplane_PopUp)) {
			minWait();
			APP_LOGS.info("Turn off Airplane mode popup is displayed -> Airplane mode is enabled successfully");
			soft.assertTrue(true, "Turn off Airplane mode popup is displayed -> Airplane mode is enabled successfully");
			test.log(LogStatus.PASS, "Turn off Airplane mode popup is displayed -> Airplane mode is enabled successfully");
			} else {
			minWait();
			APP_LOGS.info("TurnOff Airplane Mode Popup is not Displayed");	
			soft.fail();
			test.log(LogStatus.FAIL,"TurnOff Airplane Mode Popup is not Displayed");
			}
			} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR,"Error in the locators => validate_Airplane_Enable()");
			e.printStackTrace();
			}catch (Exception e) {
			test.log(LogStatus.ERROR,"Exception in functionality=> validate_Airplane_Enable()");
			e.printStackTrace();
			}
			}
		
		
		public void validate_Airplane_Disable(SoftAssert soft) throws InterruptedException, IOException {
			/* 
			 * Method can be Used Validate Airplane Mode activation via by making the call.
			 */
			try {		
				minWait();
				if(!isElementExist(New_SanityLocators.turnOff_Airplane_PopUp)) {
					minWait();
					check=true;
					APP_LOGS.info("Turn off Airplane mode popup is not displayed -> Airplane mode is disabled successfully");
					soft.assertTrue(true, "Turn off Airplane mode popup is not displayed -> Airplane mode is disabled successfully");
					test.log(LogStatus.PASS, "Turn off Airplane mode popup is not displayed -> Airplane mode is disabled successfully");
				} else  {
					minWait();
					APP_LOGS.info(" Airplane mode is not disabled");	
					soft.fail();
					test.log(LogStatus.FAIL,"Airplane mode is not disabled");
				}
				minWait();
				minWait();
			} catch (NoSuchElementException e) {
				test.log(LogStatus.ERROR,"Error in the locators => validate_Airplane_Disable()");
				e.printStackTrace();
			}catch (Exception e) {
				test.log(LogStatus.ERROR,"Exception in functionality=> validate_Airplane_Disable()");
				e.printStackTrace();
			}
		}
		
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////
	
}
