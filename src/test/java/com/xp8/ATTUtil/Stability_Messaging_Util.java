package com.xp8.ATTUtil;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.BaseUtil;
import com.xp8.util.HomeController;
import com.xp8.util.JsonFileReaderAndWriter;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;
import com.xp8.util.Locators_XP8_Sanity;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class Stability_Messaging_Util extends BaseUtil{

	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;
	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	boolean value = false;
	public  String p_Id	 = "";								// Primary Device Serial Number.
	public String r_Id	 = "";								// Reference Device Serial Number.
	public String p_b_No = "";			   					// Primary Device Build Number.
	public String r_b_No = ""; 								// Reference Device Build Number.
	public String pryNum = HomeController.PRIMARYDEVMDN;	// Reference Device MDN. 
	public String refNum = HomeController.REFERENCEDEVMDN;	// Reference Device MDN.


	public void fetch_Devices_Details() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id=JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id=JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No=JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No=JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public void installAPK() throws InterruptedException, IOException {

		System.out.println(" +++");
		customWait(1000);
		Process p1 = Runtime.getRuntime().exec("adb -s "+p_Id+" install src/test/resources/StorageFile/FillMemory.apk");
		customWait(2000);
		//		 aDriver.installApp("src/test/resources/StorageFile/FillMemory.apk");
		Thread.sleep(6000);
		p1.destroy();
		launch_an_app("fillmemory");
		if(isElementExist(Locators_Stability.startFilling)) {
			System.out.println("Verified");
			test.log(LogStatus.INFO, "FillMemory App is not present in the device ");
		}
		else {
			System.out.println("Not Verified");
			test.log(LogStatus.INFO, "FillMemory App is not present in the device ");
		}		  
	}

	public void performAction() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			customWait(1000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			enableSwitch(Locators_Stability.enabled_Airplane,Locators_Stability.disabled_Airplane,Locators_Stability.switch_widget);
			customWait(5000);
			disableSwitch(Locators_Stability.disabled_Airplane,Locators_Stability.enabled_Airplane,Locators_Stability.switch_widget );		
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " Airplane settings page is not found");
		}
	}

	public boolean validateIMSLog(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();

		if(searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "IMS is Enable");
			s1.assertTrue(check, " ");
		}
		else {
			check = false;
			test.log(LogStatus.SKIP, "IMS is not enabled" );

		}
		s1.assertAll();
		return check;
	}


	public void memoryFill() throws InterruptedException {
		/*
		 * Fillinternal memory to 92%
		 */

		try {
			launch_an_app("fillmemory");
			customWait(1000);
			enterTextToInputField(Locators_Stability.enterFill_Memory,"92");
			customWait(1000);
			Locators_Stability.startFilling.click();
			minWait();
			OCRScreencapture("File");
			int i=0;
			while(searchStringOCR("Filling Internal Memory", "OCR")) {				

				System.out.println("Screenshot  "+ i);
				i++;
				customWait(10000);
				OCRScreencapture("File");

				if(aDriver.isLocked()){
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 3");
					minWait();
					Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 82");
					customWait(500);
					System.out.println("InSide if");
					continue;
				}
				continue;
				//				System.out.println("Continue; ");
			}   
			System.out.println("Out ");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}									
	}


	public boolean validate_RIL_Logs(String filename,String validationString,String infoForFailure) throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString,filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			//			check=true;
			test.log(LogStatus.INFO, "Validated from RIL Logs : "+validationString);
		} else {			
			test.log(LogStatus.INFO, "Validation failed from ADB Logs. ==>> "+infoForFailure);
		}
		sf.assertAll();
		return check1;
	}


	public void clearSMSPermissions() throws InterruptedException {
		try {	
			customWait(2000);
			clickBtn(Locators_Stability.NEXT_Msg);
			minWait();
			clickBtn(Locators_Stability.allow_Permission);
			minWait();
		} catch (Exception e) {	
			e.getMessage();
		}		 
	}


	public void delete_All_Threads() throws InterruptedException {
		/*
		 * Method is used to delete All the SMS Threads.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			while (true) {
				if (isElementExist(Locators_Stability.firstSMS_InList)) {
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_Stability.firstSMS_InList).perform().release();
					minWait();
					clickBtn(Locators_Stability.delete_Icon_SMS);
					minWait();
					if(isElementExist(Locators_Stability.delete_Confirm)) {
						clickBtn(Locators_Stability.delete_Confirm);
						minWait();
					}
					else {
						clickBtn(Locators_Stability.delete_moreOption);
						minWait();
					}
				} 
				else if(isElementExist(Locators_Stability.first_sms_Thread)){
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_Stability.first_sms_Thread).perform().release();
					minWait();
					clickBtn(Locators_Stability.delete_Icon);
					minWait();
					if(isElementExist(Locators_Stability.delete_Confirm)) {
						clickBtn(Locators_Stability.delete_Confirm);
						minWait();
					}
					else {
						clickBtn(Locators_Stability.delete_moreOption);
						minWait();
					}
				}
				else {

					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Deleting the Messages.");
		}
	}

	public void clearCameraPermission() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_XP8_Sanity.allow_Permission);
			customWait(2000);
			clickBtn(Locators_XP8_Sanity.OK);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void cleargalleryPermission() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_Stability.notNow);
			customWait(2000);
		/*	clickBtn(Locators_XP8_Sanity.OK);
			minWait();*/
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

	public void delete_All_Photos() throws InterruptedException {
		/*
		 * Method is used to delete All the SMS Threads.
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			while (true) {
				if (isElementExist(Locators_Stability.photoView)) {
					System.out.println("Deleting....");
					minWait();
					TouchAction touchaction = new TouchAction(aDriver);
					touchaction.longPress(Locators_Stability.photoView).perform().release();
					minWait();
					clickBtn(Locators_Stability.delete_Icon_photo);
					minWait();
					if(isElementExist(Locators_Stability.move_To_Trash)) {
						clickBtn(Locators_Stability.move_To_Trash);
						minWait();
					}
					if(isElementExist(Locators_Stability.move_To_Trash1)) {
						System.out.println("Deleted Trash");
						clickBtn(Locators_Stability.move_To_Trash1);
						minWait();
					}
					
					clickBtn(Locators_Stability.cancel_Photos);
					System.out.println("Deleted");
					
					if(isElementExist(Locators_Stability.photoViewempty)) {
						System.out.println("empty");
						break;
					}
				} 

				else {

					Reporter.log("No Threads Found");
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
//			sf.fail();
			test.log(LogStatus.ERROR, "Error in Deleting the Gallery Photos/Videos");
		}
	}
	

	public void navigateTo_NewMessage() throws InterruptedException, IOException {
		SoftAssert sf = new SoftAssert();
		try {
//			launch_an_app("messaging");
			minWait();
//			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			minWait();
			if(isElementExist(Locators_Stability.add_NewSMS)) {
				clickBtn(Locators_Stability.add_NewSMS);
				minWait();
			}
			
			if(isElementExist(Locators_Stability.new_Message_Icon)) {
				clickBtn(Locators_Stability.new_Message_Icon);
				minWait();
			}
		
		} catch (Exception e) {
			e.printStackTrace();				
			test.log(LogStatus.ERROR, "Didn't navigated to New Message Window");
			sf.fail();
		}
		sf.assertAll();
	}
	
	
	

	public void remove_GoogleAcccount() {
		//remove added google Account if any 
		try {
			clickOnAccounts();
			minWait();
			if(isElementExist(Locators_Stability.google_Account)) {
				System.out.println("Account is present");
				minWait();
				clickBtn(Locators_Stability.google_Account);
				minWait();
				clickBtn(Locators_Stability.moreOptions);
				minWait();
				clickBtn(Locators_Stability.remove_Account);
				minWait();
				clickBtn(Locators_Stability.REMOVE_ACCOUNT);
				customWait(3000);
				//				test.log(LogStatus.INFO, "Removed Google account");
			}
			else {
				System.out.println("No Google account present");
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
		//navigate to settings option Add google Account
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			clickOnAccounts();
			clickBtn(Locators_Stability.add_Account);
			minWait();
			clickBtn(Locators_Stability.google_Account);
			customWait(1600);
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
		} catch (Exception e) {

		}
	}
	
	public void clear_GmailPermission() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_Stability.skip_Mail);
			minWait();
			clickBtn(Locators_Stability.gmail_gotIt);
			minWait();
			clickBtn(Locators_Stability.TAKE_ME_TO_GMAIL);
			minWait();
		} catch (Exception e) {
		}
	}
	
	public void navigate_MailOptns(WebElement typeSample) throws InterruptedException {
		
		//navigate to sent mail options
		try {
			minWait();
			clickBtn(Locators_Stability.navigate_OptionMail);
			minWait();

			clickBtn(Locators_Stability.draft_MailOptn);
			minWait();
		clickBtn(typeSample);
		customWait(2000);
		clickBtn(Locators_Stability.saveOptions);
		minWait();
		if(isElementExist(Locators_Stability.allow_Permission)) {
			clickBtn(Locators_Stability.allow_Permission);
			minWait();
		}
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
		}
	}
	
	public void attachFile(String fileName) throws InterruptedException {
		//Share file via gmail to attachment


		launch_an_app("soundRecorder");		
		if(isElementExist(Locators_Stability.recording_ListTitle)) {	
		    minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		minWait();
		clickBtn(Locators_Stability.recording_List);
		minWait();
		deleteAllFile();
		minWait();
		clearAllow();
		clickOn_Record();
		clearAllow();
	   customWait(6000);
		clickOn_StopRecord();
		clickOn_Save(fileName);
		/*customWait(3000);
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_Stability.file_list_SoundRec).release().perform();;
		customWait(1000);
		clickBtn(Locators_Stability.share_File);
		minWait();
		clickBtn(Locators_Stability.gmail_Share);
		minWait();
		clearAllow();*/
	}
	
	public void clickOn_StopRecord() throws InterruptedException {
		try {
			clickBtn(Locators_Stability.stopButton);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	
	public void navigateAttachOthers() throws InterruptedException {
		/* Navigates to */
		try {
			minWait();
			clickBtn(Locators_Stability.attach_icon);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in navigating to 'Attach others' Option");
		}
	}
	
	
	
	public void clickOnAttach_Options(AndroidElement optionToClick) throws InterruptedException {
		/* Clicks on Capture Picture in the Attach Others option.*/
		try {
			minWait();		
			for (int i = 0; i < 10; i++) {
				if (isElementExist(optionToClick)) {
					minWait();
					clickBtn(optionToClick);
					minWait();
					break;
				} else {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					Thread.sleep(500);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Error in clicking Attach Option.");
		}
	}
	
	public void clickOn_Save(String fileName) throws InterruptedException {
		try {
			minWait();
			enterTextToInputField(Locators_Stability.rename_Edit_Text, fileName);
			minWait();
			clickBtn(Locators_Stability.save_SoundRec);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}



	public void clickOn_Record() throws InterruptedException {
		try {
			clickBtn(Locators_Stability.recordButton);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}
	
	
	public void navigateTo_NewSMS() throws InterruptedException {
		/* Used to Navigate to New Message Page. */
		try {
			clickBtn(Locators_Stability.add_NewSMS);
			minWait();
		} catch (Exception e) {		 
			e.printStackTrace();
		}
	}
	
	public void type_New_Message(String cell_No, String typeMessage) throws InterruptedException, IOException {
		/*
		 * This Method is to type New Meassage.
		 */
		customWait(1000);
		type_Message(typeMessage);	
		enter_ToField(cell_No);

	}
	
	
	public void clickOn_Send() throws InterruptedException { 
		/* Method used to click on Send Button. */
		try {
			minWait();
			if(isElementExist(Locators_Stability.send_Icon)) {
				clickBtn(Locators_Stability.send_Icon);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else if(isElementExist(Locators_Stability.send_SMS)) {
				clickBtn(Locators_Stability.send_SMS);
				APP_LOGS.info("Send icon");
				minWait();
			}
			else {
				clickBtn(Locators_Stability.send_MMS_Icon);
				minWait();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void validate_CharacterAndPageNumber(WebElement charAndPageNum,String expectedcharAndPageNum) throws InterruptedException {
		/*
		 * This Meathod is to validate Characters and Page Number
		 * Precondition : User should be in Message window with Typed Message
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			String charAndPageNum1 = null;
			if(isElementExist(Locators_Stability.zero_Characters_FirstPage)) {
				System.out.println("Im out");
				charAndPageNum1 = charAndPageNum.getText();
			}
			else if(isElementExist(Locators_Stability.zero_Characters_FirstPage1)) {
				System.out.println("Im in");
				charAndPageNum1 = Locators_Stability.zero_Characters_FirstPage1.getText();
			}
			
			if (check=charAndPageNum1.equals(expectedcharAndPageNum)) {
				APP_LOGS.info("Character and Page Number Displayed is correct");
				sf.assertTrue(check, "Validation is Pass");
			} else {
				APP_LOGS.info("Character and Page Number Displayed is NOT correct");
				sf.fail();
			} 
		} catch (Exception e) {
			e.printStackTrace();
//			sf.fail();
//			test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number");
		}
		sf.assertAll();
	}	
	
	public void type_Message(String typeMessage) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			if(isElementExist(Locators_Stability.type_Message)) {
				enterTextToInputField(Locators_Stability.messageField, typeMessage);
			}
			if(isElementExist(Locators_Stability.type_Message_enter)) {
				enterTextToInputField(Locators_Stability.type_Message_enter, typeMessage);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			sf.fail();
			test.log(LogStatus.ERROR, "Error in Typing a Message.");
		}
		sf.assertAll();
	}
	
	public void enter_ToField(String cellNo) throws InterruptedException {
		SoftAssert sf = new SoftAssert();
		try {
			customWait(2000);
			if(isElementExist(Locators_Stability.TO_Field)) {
				enterTextToInputField(Locators_Stability.TO_Field, cellNo);
				minWait();
			}
			
			if(isElementExist(Locators_Stability.TO_Field_enter)) {
				enterTextToInputField(Locators_Stability.TO_Field_enter, cellNo);
				minWait();
			}
			
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
//			sf.fail();
			test.log(LogStatus.ERROR, "Error in entering number to ToField");
		}
		sf.assertAll();
	}
	
	public void sendSMS_fromRefDevice(String AutomationMessagee) {

		// To validate MT Message User should be inside Messaging APP of Primary Device.
		try {
			minWait();
//			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input tap 777 1083");
			minWait();
			System.out.println("Ref"+ r_b_No + r_Id);
			if (r_b_No.contains("8A.")) {
				if (r_b_No.contains("-10.")||r_b_No.contains("-30.")) {
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

	public void validate_RecievedMessage(int n) throws InterruptedException {
		/* User should navigate to the Messaging APP home Page in the Primary device. */
		System.out.println("Receive SMS");
		SoftAssert sf = new SoftAssert();
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);
		wait.until(ExpectedConditions.visibilityOf(Locators_Stability.now_Text));	
		customWait(8000);
		try {
			if(isElementExist(Locators_Stability.now_Text)||isElementExist(Locators_Stability.not_Sent_Text)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Message Recieved Successfully at iteration : "+ n);
			} else {
				APP_LOGS.info("SMS didn't sent");
				sf.fail();
				test.log(LogStatus.PASS, "Message didn't Recieved at iteration : "+ n);
			}
		} catch (Exception e) {			 
			e.printStackTrace();
//			sf.fail();
		}
		sf.assertAll();
	}



	
	public void validate_SentMessage(int n, String str) throws InterruptedException {
		/* To validate the Sent Message. */
		SoftAssert Sa = new SoftAssert();
		
		try {
//			 launch_an_app("messaging");
			 customWait(1000);
			System.out.println("Sent msg");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			customWait(1000);
			if(isElementExist(Locators_Stability.now_Text)||isElementExist(Locators_Stability.justnow_Text)||isElementExist(Locators_Stability.not_Sent_Text)||isElementExist(Locators_Stability.sending_Txt)) {
				check=true;
				APP_LOGS.info("Message sent Succeccfully");
				test.log(LogStatus.PASS, "Send "+ str +"Test case status is Passed at iteration : "+ n);
				Sa.assertTrue(check, " ");
				
			} else {
				APP_LOGS.info("SMS didn't sent");
				test.log(LogStatus.ERROR,str+ " didn't Sent, Validation Failed at iteration : "+ n);
				Sa.fail();
			}
			minWait();
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		   minWait();
		   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {			 
			e.printStackTrace();
//			Sa.fail();
		}		
		Sa.assertAll();
	}
	
	
	public void selectAudio() throws InterruptedException {
		//Select External audio
		
		clickBtn(Locators_Stability.External_Txt);
		minWait();
		clickBtn(Locators_Stability.selectAudio);
		minWait();
		clickBtn(Locators_Stability.OKBtn);
	}
	
	public void delete_SMS() throws InterruptedException {
		/* This Method delete the First Thread in the List. */

		try {
			minWait();
			clickBtn(Locators_Stability.firstSMS_InList);
			minWait();
			clickBtn(Locators_Stability.moreOptions);
			minWait();
			clickBtn(Locators_Stability.delete_Thread);
			minWait();
			clickBtn(Locators_Stability.delete_Confirm);
			minWait();
//			test.log(LogStatus.INFO, "SMS or MMS is deleted.");
		} catch (Exception e) {			 
			e.printStackTrace();
		}
	}

	public void clickOn_RecordList() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Stability.listButton_SoundRec);
			customWait(2000);
			clickBtn(Locators_BaseUtil.allow_PopUp);
			customWait(2000);
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
		}
	}

	
	public void deleteAllFile() throws InterruptedException {
		//delete all recorded file
		try {
			if(isElementExist(Locators_Stability.file_list_SoundRec)){
				System.out.println("In if ");
			for(int i=1; i<=10; i++) {
			minWait();
			TouchAction action = new TouchAction(aDriver);
			action.longPress(Locators_Stability.file_list_SoundRec).release().perform();
			minWait();
			Locators_Stability.DeleteIcon.click();
			APP_LOGS.info("Pressed Delete Icon sucessfully");
			minWait();
			Locators_Stability.DeleteBtn.click();
			APP_LOGS.info("Pressed Delete Button sucessfully");
			Thread.sleep(2000);
			minWait();	
			break;
			}
			}
			customWait(2000);
		clickBtn(Locators_Stability.NavigateUpSrchEng);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

	
	
	public void add_GoogleAccount(String emailId, String password) {
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			minWait();
			enterTextToInputField(Locators_Stability.email_googleAcnt, emailId);
			minWait();
			clickBtn(Locators_Stability.next);
			minWait();
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Welcome' or @resource-id='profileIdentifier']")));
			minWait();
			enterTextToInputField(Locators_Stability.password_googleAcnt, password);
			customWait(5000);
			clickBtn(Locators_Stability.next);
		customWait(3000);
		scroll() ;
		scroll() ;
		minWait();
		clickBtn(Locators_Stability.skip_);
//		aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.Button\")).scrollIntoView(new UiSelector().textContains(\"Skip\"))").click();
		minWait();
//		    scrollToText("Skip");
			if (p_b_No.contains("-11.")||p_b_No.contains("-12.")||p_b_No.contains("-18.")||p_b_No.contains("-26.")||p_b_No.contains("-29.")) {
				try {
					aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.webkit.WebView\")).scrollIntoView(new UiSelector().textContains(\"Yes\"))").click();
					minWait();
				} catch (Exception e) {
				}
			}		
			customWait(1000);
			clickBtn(Locators_Stability.i_agree);
			minWait();
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
			clickBtn(Locators_Stability.MORE);
			minWait();
			clickBtn(Locators_Stability.ACCEPTorAGREE);
			customWait(3000);
		} catch (Exception e) {
		}
	}
}
