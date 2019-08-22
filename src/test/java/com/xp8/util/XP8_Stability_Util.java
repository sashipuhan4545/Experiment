package com.xp8.util;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Stability_Util extends BaseUtil{
	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;
	
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

	public boolean check = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	

	public void deleteContacts() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_DeviceStability.ALL_page);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			if(isElementExist(Locators_DeviceStability.longpress_FirstContact)) {
				customWait(1000);
				TouchAction touchaction = new TouchAction(aDriver);
				touchaction.longPress(Locators_DeviceStability.longpress_FirstContact).perform().release();
				minWait();
				clickBtn(Locators_DeviceStability.Selection_menu);
				minWait();
				if(isElementExist(Locators_DeviceStability.ALL_Selection_menu)) {
					clickBtn(Locators_DeviceStability.ALL_Selection_menu);
					minWait();
				}
				else {
					minWait();
					clickBtn(Locators_DeviceStability.one_Selection_menu);
				}
				clickBtn(Locators_DeviceStability.MoreOptnsIcn);
				minWait();
				clickBtn(Locators_DeviceStability.deleteContactOptn);
				minWait();
				clickBtn(Locators_DeviceStability.OKBtn1);
				APP_LOGS.info("Contacts Deleted");
				minWait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
//			Assert.fail();
		}
	}
	
	public void setDefaultAccount_Contact() throws InterruptedException, IOException {

		try {
			clickBtn(Locators_DeviceStability.moreOptions);
			minWait();
			clickBtn(Locators_DeviceStability.settings_Contact);
			minWait();
			clickBtn(Locators_DeviceStability.defaultAccount_NewContacts);
			minWait();
			clickBtn(Locators_DeviceStability.PHONE_RadioBtn);
		    customWait(2000);
		    aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			//clickBtn(Locators_DeviceStability_ATT.navigateUp);
//			Runtime.getRuntime().exec("adb -s "+p_Id+" shell input keyevent 4");
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}


	public void createContact(String name,String num) throws InterruptedException {
		/*
		 * Create contact
		 */
		try {
			
		/*	clickBtn(Locators_DeviceStability.add_NewContact);
			minWait();
			clickBtn(Locators_DeviceStability.contact_SavingTo);
			minWait();
			clickBtn(Locators_DeviceStability.savingTo_Phone);*/
			minWait();
			Locators_DeviceStability.AddtoContact.click();
			minWait();

			if(isElementExist(Locators_DeviceStability.Choose_Phone)) {
				minWait();
				clickBtn(Locators_DeviceStability.Choose_Phone);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if(isElementExist(Locators_DeviceStability.Contacts_Name)) {
				minWait();
			}

				minWait();		
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
				Locators_DeviceStability.AddtoContact.click();
				minWait();
			
			customWait(4000);
			enterTextToInputField(Locators_DeviceStability.Contacts_Name,name);
			minWait();		
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_DeviceStability.Contacts_Phone,num);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_DeviceStability.Contacts_Email,"Sonimtech@gmail.com");
			minWait();
			clickBtn(Locators_DeviceStability.Save_ConatctIcon);
			minWait();

			for(int i=1; i<=3; i++) {
				if(isElementExist(Locators_DeviceStability.AllowOptn))
				{
					clickBtn(Locators_DeviceStability.AllowOptn);
				} 
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such element Found");
		}		
	}


	public void validateContactCreation(int n) throws InterruptedException {
		/*
		 * Validate contact
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			String getcontactNameText = null;
			getcontactNameText=Locators_DeviceStability.ContactTitle.getText();
						System.out.println(getcontactNameText);
			if(getcontactNameText.contains("Test"))
			{	check1 =true;
			APP_LOGS.info("check1: "+check1);
			APP_LOGS.info("Contact Name is: "+getcontactNameText);
			}
			else{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Test case failed");	
//				test.log(LogStatus.ERROR, "Contact creation failed at iteration: "+n);
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			String getPhoneNumText=Locators_DeviceStability.contact_phnNum.getText();
			
			String phoneNum = getPhoneNumText.replaceAll("[^a-zA-Z0-9]", "");
			System.out.println(phoneNum);
			minWait();
			if(phoneNum.contains(HomeController.REFERENCEDEVMDN)||phoneNum.equalsIgnoreCase(HomeController.REFERENCEDEVMDN))
			{	check2 =true;
			APP_LOGS.info("check2: "+check2);
			APP_LOGS.info("Contact Phone Num is: "+phoneNum);
			}
			else {
				APP_LOGS.info("check2: "+check2);
			}

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			customWait(1000);
			String getEmailText =null ;
			getEmailText=Locators_DeviceStability.contact_Emailid.getText();
			//			System.out.println(getEmailText);
			if(getEmailText.contains("Sonimtech"))
			{	check3 =true;
			APP_LOGS.info("check3: "+check3);
			APP_LOGS.info("Contact email id is: "+getEmailText);
			}
			else {
				APP_LOGS.info("check3: "+check3);
			}

			if((check1)&&(check2)&&(check3)){
				check=true;				
				APP_LOGS.info("Contact created with Name,phone_Num, Email_ID and Address is saved in phone memory");
				test.log(LogStatus.INFO,"Contact created at iteration: "+n);
				Sa.assertTrue(check, " ");
			}else{
				APP_LOGS.info("Test case failed");	
				test.log(LogStatus.ERROR, "Contact creation failed at iteration: "+n);
				Sa.fail();
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
			
		}
		Sa.assertAll();
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
		SoftAssert sf = new SoftAssert();
		WebDriverWait wait  =new WebDriverWait(aDriver, 80);
		wait.until(ExpectedConditions.visibilityOf(Locators_DeviceStability.now_Text));	
		customWait(8000);
		try {
			if(isElementExist(Locators_DeviceStability.now_Text)||isElementExist(Locators_DeviceStability.not_Sent_Text)) {
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


	public void endcall() throws InterruptedException {
		/*
		 * disconnect call
		 */
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		minWait();	
	}

	public void searchContact(String name) throws InterruptedException {
		/*
		 * Search contact 
		 */
		try {
			minWait();
			clickBtn(Locators_DeviceStability.Search_ConatctIcon);
			customWait(2000);
			enterTextToInputField(Locators_DeviceStability.findContacts, name);
			minWait();
			clickBtn(Locators_DeviceStability.longpress_FirstContact);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
//			Assert.fail();
		}
	}
	public void initiateCall() throws InterruptedException {
		/*
		 * initiate a call from Saved contacts
		 */
		try {
			minWait();
			clickBtn(Locators_DeviceStability.SavedContact);
			minWait();
			clickBtn(Locators_DeviceStability.contact_phnNum);
			minWait();
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
//			Assert.fail();
		}		
	}

	public void validateCallLog(String str,int n,String callType) throws InterruptedException, IOException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();
		String value = null;
		customWait(10000);
		try {
			
		for(int j=1;j<=100;j++){
		Process child = Runtime.getRuntime().exec("adb -s "+r_Id+" shell service call telecom 27" );
		InputStream lsOut = child.getInputStream();
		InputStreamReader r = new InputStreamReader(lsOut);
		BufferedReader in = new BufferedReader(r);
		value=in.readLine();
		System.out.println(value);
			customWait(2000);
			if(value.contains("00000001")) {
				Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
				test.log(LogStatus.PASS,"MO-Voice call from "+ callType +" is validated at : iteration " + n);
//				s1.assertTrue(check, " ");		
				break;
			}
		/*	else if(value.contains("00000000")){			
				continue;				
			}*/
			else {
				test.log(LogStatus.FAIL, "MO-Voice call Failed at : iteration " + n );
			}
			
		}
		    customWait(6000);
			endcall();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			Thread.sleep(2000);
			Runtime.getRuntime().exec("adb -s "+r_Id+" shell input keyevent 5");
			Thread.sleep(2000);
		}
//		s1.assertAll();
	}

	public void selectPage(WebElement selectpage) throws InterruptedException {
		try {
			minWait();	
			clickBtn(selectpage);
		}
		catch(Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
//			Assert.fail();
		}
	}

	public void callHistory() throws InterruptedException, IOException {
		/*
		 * call save contact
		 */
		minWait();
		clickBtn(Locators_DeviceStability.MoreOptions);
		minWait();		
		customWait(1000);
		clickBtn(Locators_DeviceStability.callHistory);
		minWait();
		
		clickBtn(Locators_DeviceStability.Call_Contact);
		minWait();
		APP_LOGS.info("initiated a call");
		minWait();
		customWait(2000);
	}

	public void performAction() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			enableSwitch(Locators_DeviceStability.enabled_Airplane,Locators_DeviceStability.disabled_Airplane,Locators_DeviceStability.switch_widget);
			customWait(5000);
			disableSwitch(Locators_DeviceStability.disabled_Airplane,Locators_DeviceStability.enabled_Airplane,Locators_DeviceStability.switch_widget );		
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public boolean validate_RIL_Logs(String filename,String validationString,String infoForFailure) throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString,filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			check=true;
			test.log(LogStatus.INFO, "Validated from RIL Logs : IMS is Enable");
		} else {			
			test.log(LogStatus.INFO, "Validation failed from ADB Logs. ==>> "+infoForFailure);
		}
		sf.assertAll();
		return check1;
	}
	
	public boolean validateCSFB(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
	
        customWait(4000);
		if(searchString(str, fileName)) {
			check = false;
			test.log(LogStatus.SKIP, "IMS Registered SIM is available" );
//			Assert.fail();			
		}
		else {		
			check = true;
			test.log(LogStatus.INFO, "Non-IMS registered validated");
//		    assertTrue(check);
		}
		return check;
	}
	

	public boolean validateIMSLog(String str,String fileName) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();

		if(searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "IMS is Enable");
//			s1.assertTrue(check, " ");
		}
		else {
			check = false;
			test.log(LogStatus.SKIP, "IMS is not enabled" );
			
		}
		s1.assertAll();
		return check;
	}
	
	public void validateIMSLogSMS(String str,String fileName,String callType) throws IOException, InterruptedException {
		/*
		 * Validate via log String that MO-Voice  call initiated 
		 */
		SoftAssert s1 = new SoftAssert();

		if(searchString(str, fileName)) {
			check = true;
			test.log(LogStatus.INFO, "SMS over IMS registered from "+ callType +" is validated ");
			s1.assertTrue(check, " ");
		}
		else {
			test.log(LogStatus.SKIP, "IMS Registered SIM not available " );
			s1.fail();
		}
		s1.assertAll();
	}



	//This method will take adb log
	public static void startLog(String fileName) throws AWTException, InterruptedException {

		customWait(2000);
		try {
			APP_LOGS.info("Adb log started sucessfully ");
			Runtime.getRuntime().exec("cmd /C \"adb logcat -b all -v threadtime>src/test/resources/adbLogs/"+fileName+".txt \"");
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Something goes Wrong!!!");  
			e.printStackTrace();  
		}
	}


	public void selectWIFIOptn() throws InterruptedException{
		/*
		 * Select Wifi option from settings List
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(1000);
			//					ScrollToElement(Locators_DeviceStability.SettingsList, "Wi-Fi");
			customWait(2000);
			clickBtn(Locators_DeviceStability.wifi);
			minWait();
			APP_LOGS.info("Selected WIFI option");
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("WIFI : No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi No such Element found");
//			Sa.fail();
		}
		Sa.assertAll();
	}

	public void selectSSIDwifi() throws InterruptedException {
		/*
		 * Select IDC wifi which is available
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			customWait(4000);
			for(int i=1; i<=50; i++) {
				if(isElementExist(Locators_DeviceStability.wifi_IDC)) {
					minWait();
					clickBtn(Locators_DeviceStability.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_DeviceStability.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_DeviceStability.wifi_Dellas);
					APP_LOGS.info("Dellas available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_DeviceStability.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_DeviceStability.wifi_Cannada);
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
//			Sa.fail();
		}
		Sa.fail();
	}


	public void disconnectSSIDifConnected() throws InterruptedException {
		/*
		 * disconnect wifi[SSID]if IDC wifi connected
		 */
		SoftAssert Sa = new SoftAssert();
		try {
			selectSSIDwifi();
			customWait(4000);
			clickBtn(Locators_DeviceStability.wifi_IDC_ForgetBtn);
			APP_LOGS.info("IDC available secured wifi is disconnected");
			customWait(4000);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Wi-Fi Forget button: No such Element found");
			
		}
		Sa.fail();
	}

	
	public void validate_And_BrowseIn_Chrome(String url, AndroidElement autoSuggestion, AndroidElement expectedElement, AndroidElement expectedElement1) throws InterruptedException, IOException {
		/* 
		 * Validates MobileData enabality by launching the Chrome & browsing google page.
		 */
		SoftAssert  sf = new SoftAssert();
		try {
			customWait(2000);
			enterTextToInputField(Locators_DeviceStability.urlBar_Chrome, url);
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
		} catch (Exception e) {
			e.printStackTrace();
//			sf.fail();
		}
//		closeAllTabs();	
		sf.assertAll();
	}
	
	public void selectRefresh() throws InterruptedException {
		/*
		 * Refresh the List SSID 
		 */		
		try {
			minWait();
			//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			clickBtn(Locators_DeviceStability.MoreOptions);
			minWait();
			clickBtn(Locators_DeviceStability.RefreshOptn);
			minWait();
			//refresh btn
			customWait(5000);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, "Wi-Fi : No such Element found");
//			Assert.fail();
		}		
	}

	public void enterPassword() throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			String getSSIDTitle = Locators_DeviceStability.SSIDTxt.getText();
			//		System.out.println(getSSIDTitle);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			changeToNumberMode();
			customWait(2000);
			if(getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
				minWait();
				clickBtn(Locators_DeviceStability.wifi_IDC_Psswd);
				customWait(4000);
				//					enterTextToInputField(Locators_DeviceStability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");		
				customWait(3000);	
			}
			minWait();
			String psswrd = Locators_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(psswrd);
			customWait(1000);
			clickBtn(Locators_DeviceStability.wifi_IDC_ConnectBtn);
			customWait(3000);
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, " No such Element found");
//			Assert.fail();
		}
	}

	public void changeToNumberMode() throws InterruptedException {

		try {
			minWait();
			Locators_DeviceStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if(!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_DeviceStability.wifi_IDC_Psswd.clear();
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, " No such Element found");
//			Assert.fail();
		}
	}

	public void changeToNumberModeSMS() throws InterruptedException {

		try {
			minWait();
			Locators_DeviceStability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_DeviceStability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if(!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_DeviceStability.wifi_IDC_Psswd.clear();
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
			test.log(LogStatus.ERROR, " No such Element found");
//			Assert.fail();
		}
	}
	
	public void validateWifiConnect(int n) throws InterruptedException, IOException {
		/*
		 * Validate wifi connection
		 */
		SoftAssert sf = new SoftAssert();
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			String getTxt = Locators_DeviceStability.connectedWIFI.getText();
			System.out.println(getTxt);
			if(getTxt.contains("IDCSONWAP")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.INFO, "Connected and Disconnected to Secured Wifi succesfully at iteration: "+n);
				sf.assertTrue(check, "Validation is Pass");
			}
			else if (getTxt.contains("1234567890sonim")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.INFO, "Connected and Disconnected to Secured Wifi--Dellas succesfully at iteration: "+n);
				sf.assertTrue(check, "Validation is Pass");
			}
			else if (getTxt.contains("dlink-F092-media")) {
				check = true;
				APP_LOGS.info("Connected to Secured Wifi succesfully.");
				test.log(LogStatus.INFO, "Connected and Disconnected to Secured Wifi--Cannada succesfully at iteration: "+n);
				sf.assertTrue(check, "Validation is Pass");
			}
			else{
				APP_LOGS.info("Connected to Secured Wifi is unsuccesful.");	
				test.log(LogStatus.ERROR, "Connected and Disconnected to Secured Wifi is unsuccesful at iteration: "+n);
				sf.fail();
			}
		} catch (Exception e) {
			APP_LOGS.info("No Element found.");
			test.log(LogStatus.ERROR, " No Such Element Found ");
//			sf.fail();
		}
		sf.assertAll();
	}


	public void validate_ON_OFF_WiFiFeature(WebElement feature,int n) throws InterruptedException, IOException{
		/*
		 * validate Turn On and OFF wifi toggle button	
		 */
		SoftAssert S1 = new SoftAssert();
		try {	   
			customWait(2000);
			enableFeature(Locators_DeviceStability.enabled,Locators_DeviceStability.disabled,Locators_DeviceStability.switch_Title);
			minWait();

			if(isElementExist(Locators_DeviceStability.enabled)) {
				check1 = true;
				APP_LOGS.info(" check1");
			}
			customWait(3000);
			disableFeature(Locators_DeviceStability.enabled,Locators_DeviceStability.disabled);
			if(isElementExist(Locators_DeviceStability.disabled)) {
				check2 = true;
				APP_LOGS.info(" ");
			}
			if((check1) && (check2)) {
				check = true;
				APP_LOGS.info(" Turn ON and OFF feature is verified");
				test.log(LogStatus.INFO, "Turn ON and OFF WIFI feature is verified at iteration: "+n);
				S1.assertTrue(check, " ");
			}
			else {
				APP_LOGS.info(" check1"+check1+"check2"+check2);
				test.log(LogStatus.ERROR, "No Such Element Found");
				APP_LOGS.info(" Turn ON and OFF feature is not verified at iteration: "+ n);
				S1.fail();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No Such Element Found");
//			S1.fail();
		}
		S1.assertAll();

	}

	public void acceptDefault() throws InterruptedException {
		/*
		 * Accept default Options
		 */

		try {
			if(isElementExist(Locators_DeviceStability.AccptBtn)){
				Locators_DeviceStability.AccptBtn.click();
			}
			customWait(4000);
			if(isElementExist(Locators_DeviceStability.NextIcon)){
				Locators_DeviceStability.NextIcon.click();
			}
			customWait(4000);
			if(isElementExist(Locators_DeviceStability.NothanksBtn)){
				Locators_DeviceStability.NothanksBtn.click();
			}
			customWait(4000);
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Url bar: No Such Element Found");
			Assert.fail();		
		}

	}

	public void enterUrl(String newurl) throws InterruptedException, IOException {
		/*
		 * Enter the Website in Url bar
		 */
		SoftAssert Sa = new SoftAssert();
		try {
//			acceptDefault();
	    	customWait(2000);	 
		/*	clickBtn(Locators_DeviceStability.DefaultUrlTxt);
					enterTextToInputField(Locators_DeviceStability.DefaultUrlTxt,newurl);
*/
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.intent.action.VIEW -d http:/"+newurl);
			Thread.sleep(2000);
			APP_LOGS.info(" URl is entered is sucessful.");
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			APP_LOGS.info("Search click is sucessful.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Url bar: No Such Element Found");
			Sa.fail();			
		} 
		Sa.assertAll();
	}


	public void validateUrlEntered(int n)
	{
		/*
		 * validate the website page is loaded via adb logs
		 */
		SoftAssert S2= new SoftAssert();
		try { 
			customWait(20000);
			if(isElementExist(Locators_DeviceStability.networkNotAvailable)){
				test.log(LogStatus.INFO, "Entered Website page not Loaded successfully at iteration: "+n);
				S2.fail();
			} 

			else {
				check = true;
				APP_LOGS.info("Url entered sucessfully.");
				test.log(LogStatus.INFO, "Entered Website page Loaded Successfully at iteration: "+n);
				S2.assertTrue(check, " ");
			}
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
			S2.fail();
		} 
		S2.assertAll();
	}

	
	  public static void imsLogs(String fileName) throws AWTException, InterruptedException {

		   String path = System.getProperty("user.dir");
		   customWait(1000);
		   try {
		    Runtime.getRuntime().exec("cmd /C \"adb logcat -b all -v time>"+path+"\\src\\test\\resources\\adbLogs\\"+fileName+".txt\"");
		    Thread.sleep(1000);
		   }
		   catch(Exception e) {
		    Reporter.log("Something goes Wrong!!!");  
		    e.printStackTrace(); 
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
					if (isElementExist(Locators_DeviceStability.firstSMS_InList)) {
						minWait();
						TouchAction touchaction = new TouchAction(aDriver);
						touchaction.longPress(Locators_DeviceStability.firstSMS_InList).perform().release();
					     minWait();
					     clickBtn(Locators_DeviceStability.delete_Icon_SMS);
					     minWait();
					     if(isElementExist(Locators_DeviceStability.delete_Confirm)) {
								clickBtn(Locators_DeviceStability.delete_Confirm);
								minWait();
							     }
							     else {
							    	 clickBtn(Locators_DeviceStability.delete_moreOption);
										minWait();
							     }
					} 
					else if(isElementExist(Locators_DeviceStability.first_sms_Thread)){
						minWait();
						TouchAction touchaction = new TouchAction(aDriver);
						touchaction.longPress(Locators_DeviceStability.first_sms_Thread).perform().release();
					     minWait();
					     clickBtn(Locators_DeviceStability.delete_Icon);
					     minWait();
					     if(isElementExist(Locators_DeviceStability.delete_Confirm)) {
						clickBtn(Locators_DeviceStability.delete_Confirm);
						minWait();
					     }
					     else {
					    	 clickBtn(Locators_DeviceStability.delete_moreOption);
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
	  
	  
	//======================================================================================================

		public void navigateTo_NewMessage() throws InterruptedException, IOException {
			SoftAssert sf = new SoftAssert();
			try {
//				launch_an_app("messaging");
				minWait();
//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				if(isElementExist(Locators_DeviceStability.add_NewSMS)) {
					clickBtn(Locators_DeviceStability.add_NewSMS);
					minWait();
				}
				
				if(isElementExist(Locators_DeviceStability.new_Message_Icon)) {
					clickBtn(Locators_DeviceStability.new_Message_Icon);
					minWait();
				}
			
			} catch (Exception e) {
				e.printStackTrace();				
				test.log(LogStatus.ERROR, "Didn't navigated to New Message Window");
				sf.fail();
			}
			sf.assertAll();
		}
		
		public void create_NewSMS(String number, String message) throws InterruptedException, IOException {
			/* Method used to create New SMS. */
			navigateTo_NewSMS();
			try {
				enter_Num_ToField(number);
				enterText_MessageField(message);
			} catch (Exception e) {			 
				e.printStackTrace();
			}
		}
		
		public void create_NewSMS1(String number, String message) throws InterruptedException {
			/* Method used to create New SMS. */
			navigateTo_NewSMS1();
			try {
				enter_Num_ToField1(number);
				enterText_MessageField1(message);
			} catch (Exception e) {			 
				e.printStackTrace();
			}
		}
		
		public void navigateTo_NewSMS() throws InterruptedException {
			/* Used to Navigate to New Message Page. */
			try {
				clickBtn(Locators_DeviceStability.add_NewSMS);
				minWait();
			} catch (Exception e) {		 
				e.printStackTrace();
			}
		}
		
		public void delete_SMS1() throws InterruptedException {
			/* This Method delete the First Thread in the List. */

			try {
				minWait();
				clickBtn(Locators_DeviceStability.firstSMS_InList1);
				minWait();
				clickBtn(Locators_DeviceStability.moreOptions);
				minWait();
				clickBtn(Locators_DeviceStability.delete_moreOption);
				minWait();
				clickBtn(Locators_DeviceStability.delete_Confirm);
				minWait();
//				test.log(LogStatus.INFO, "SMS or MMS is deleted.");
			} catch (Exception e) {			 
				e.printStackTrace();
			}
		}
		
		public void delete_SMS() throws InterruptedException {
			/* This Method delete the First Thread in the List. */

			try {
				minWait();
				clickBtn(Locators_DeviceStability.firstSMS_InList);
				minWait();
				clickBtn(Locators_DeviceStability.moreOptions);
				minWait();
				clickBtn(Locators_DeviceStability.delete_Thread);
				minWait();
				clickBtn(Locators_DeviceStability.delete_Confirm);
				minWait();
//				test.log(LogStatus.INFO, "SMS or MMS is deleted.");
			} catch (Exception e) {			 
				e.printStackTrace();
			}
		}
		
		public void clickOn_Send1() throws InterruptedException { 
			/* Method used to click on Send Button. */
			try {
				customWait(500);
				clickBtn(Locators_DeviceStability.send_Icon1);
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		public void navigateTo_NewSMS1() throws InterruptedException {
			/* Used to Navigate to New Message Page. */
			try {
				clickBtn(Locators_DeviceStability.add_NewSMS1);
				minWait();
			} catch (Exception e) {		 
				e.printStackTrace();
			}
		}
		public void enterText_MessageField1(String message) throws InterruptedException {
			/* Methos is Used enter Message into Message Field. */
			try {
				minWait();
				enterTextToInputField(Locators_DeviceStability.messageField1, message);
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void enter_Num_ToField1(String number) throws InterruptedException {
			/* Method is used to Enter Number into TO Field. */
			try {
				minWait();
				enterTextToInputField(Locators_DeviceStability.TO_Field1, number);
				customWait(2000);
				clickBtn(Locators_DeviceStability.contactPicker);
				customWait(2000);			
			} catch (NoSuchElementException e) {			 
				e.printStackTrace();
			}
		}
		
		public void enter_Num_ToField(String number) throws InterruptedException {
			/* Method is used to Enter Number into TO Field. */
			try {
				minWait();
				enterTextToInputField(Locators_DeviceStability.TO_Field1, number);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			} catch (Exception e) {			 
				e.printStackTrace();
			}
		}
		
		public void enterText_MessageField(String message) throws InterruptedException {
			/* Methos is Used enter Message into Message Field. */
			try {
				customWait(1000);
				enterTextToInputField(Locators_DeviceStability.type_text1, message);
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
		
		public void enter_ToField(String cellNo) throws InterruptedException {
			SoftAssert sf = new SoftAssert();
			try {
				customWait(2000);
				if(isElementExist(Locators_DeviceStability.TO_Field1)) {
					enterTextToInputField(Locators_DeviceStability.TO_Field1, cellNo);
					minWait();
				}
				
				if(isElementExist(Locators_DeviceStability.TO_Field_enter)) {
					enterTextToInputField(Locators_DeviceStability.TO_Field_enter, cellNo);
					minWait();
				}
				
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			} catch (Exception e) {
				e.printStackTrace();
//				sf.fail();
				test.log(LogStatus.ERROR, "Error in entering number to ToField");
			}
			sf.assertAll();
		}

		public void type_Message(String typeMessage) throws InterruptedException {
			SoftAssert sf = new SoftAssert();
			try {
				minWait();
				if(isElementExist(Locators_DeviceStability.type_Message)) {
					enterTextToInputField(Locators_DeviceStability.type_text1, typeMessage);
				}
				if(isElementExist(Locators_DeviceStability.type_Message_enter)) {
					enterTextToInputField(Locators_DeviceStability.type_Message_enter, typeMessage);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				sf.fail();
				test.log(LogStatus.ERROR, "Error in Typing a Message.");
			}
			sf.assertAll();
		}
		
		public void send_SMS() throws InterruptedException {
			/*	
			 * 	This method is used to SEND SMS
			 * Precondition : User should create new SMS 
			 */
			SoftAssert sf = new SoftAssert();
			try {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				clickBtn(Locators_DeviceStability.send);
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
//				sf.fail();
				test.log(LogStatus.ERROR, "Error in Sending the Message.");
			}
			sf.assertAll();
		}
		
		
		public void clickOn_Send() throws InterruptedException { 
			/* Method used to click on Send Button. */
			try {
				minWait();
				if(isElementExist(Locators_DeviceStability.send_Icon)) {
					clickBtn(Locators_DeviceStability.send_Icon);
					APP_LOGS.info("Send icon");
					minWait();
				}
				else if(isElementExist(Locators_DeviceStability.send_SMS)) {
					clickBtn(Locators_DeviceStability.send_SMS);
					APP_LOGS.info("Send icon");
					minWait();
				}
				else {
					clickBtn(Locators_DeviceStability.send_MMS_Icon);
					minWait();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void clearSMSPermissions() throws InterruptedException {
			try {	
				customWait(2000);
				clickBtn(Locators_DeviceStability.NEXT_Msg);
				minWait();
				clickBtn(Locators_DeviceStability.allow_Permission);
				minWait();
				//			Runtime run = Runtime.getRuntime();
				//			Process pr = run.exec("adb shell input tap 540 1776");
				//			pr.waitFor();
				//			Runtime run1 = Runtime.getRuntime();
				//			Process pr1 = run1.exec("adb shell input tap 713 1098");
				//			pr1.waitFor();
			} catch (Exception e) {	
				e.getMessage();
			}		 
		}
		
		
	
		public void validate_SentMessage(int n) throws InterruptedException {
			/* To validate the Sent Message. */
			SoftAssert Sa = new SoftAssert();
			
			try {
//				 launch_an_app("messaging");
				 customWait(1000);
				System.out.println("Sent msg");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				customWait(1000);
				if(isElementExist(Locators_DeviceStability.now_Text)||isElementExist(Locators_DeviceStability.justnow_Text)||isElementExist(Locators_DeviceStability.not_Sent_Text)||isElementExist(Locators_DeviceStability.sending_Txt)) {
					check=true;
					APP_LOGS.info("Message sent Succeccfully");
					test.log(LogStatus.PASS, "Test case status is Passed at iteration : "+ n);
					Sa.assertTrue(check, " ");
					
				} else {
					APP_LOGS.info("SMS didn't sent");
					test.log(LogStatus.ERROR, "SMS or MMS didn't Sent, Validation Failed at iteration : "+ n);
					Sa.fail();
				}
				minWait();
			   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			   minWait();
			   aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			} catch (Exception e) {			 
				e.printStackTrace();
//				Sa.fail();
			}		
			Sa.assertAll();
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
				if(isElementExist(Locators_DeviceStability.zero_Characters_FirstPage)) {
					System.out.println("Im out");
					charAndPageNum1 = charAndPageNum.getText();
				}
				else if(isElementExist(Locators_DeviceStability.zero_Characters_FirstPage1)) {
					System.out.println("Im in");
					charAndPageNum1 = Locators_DeviceStability.zero_Characters_FirstPage1.getText();
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
//				sf.fail();
//				test.log(LogStatus.ERROR, "Error in validating the Number of characters and Page Number");
			}
			sf.assertAll();
		}	
		
		public void navigateAttachOthers() throws InterruptedException {
			/* Navigates to */
			try {
				minWait();
				clickBtn(Locators_DeviceStability.attach_icon);
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
		
		public void capturePic_ADD() throws InterruptedException, IOException {
			/* Captures the image and adds to the Message (MMS)
			 * Precondition : User should navigate to "Attach others" Option
			 */
			try {
				for(int i=1;i<=5; i++) {
				if(isElementExist(Locators_DeviceStability.AllowOptn)) {
					clickBtn(Locators_DeviceStability.AllowOptn);
					minWait();
				}
			}
				
				customWait(3000);
				clickBtn(Locators_DeviceStability.cameraIcon);
				customWait(15000);
				clickBtn(Locators_DeviceStability.OK_btn_resultConfirmation_dialog);
				customWait(3000);
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Error in adding Picture to MMS");
//				Assert.fail();
			}
		}
		
		public void validate_AnyAdded_Attachment(AndroidElement attachmentToValidate) throws InterruptedException {

			SoftAssert sf = new SoftAssert();
			minWait();
			customWait(60000);
			if (isElementExist(attachmentToValidate)) {
				check=true;
				APP_LOGS.info("Attachment added Successfully.");
				sf.assertTrue(check, "Attachment added Successfully, validation PASS.");
			} else {				
				test.log(LogStatus.ERROR, "Error in adding the Attachment to SMS.");
				sf.fail();
			}
			sf.assertAll();		
		}
		
		public void captureVideo_ADD() throws InterruptedException {

			/* Captures the Video and adds to the Message (MMS)
			 * Precondition : User should navigate to "Attach others" Option
			 */

			try {
				customWait(2000);
//				
				customWait(10000);				
				clickBtn(Locators_DeviceStability.videoStopIcon);
				customWait(4000);
				clickBtn(Locators_DeviceStability.videoStopIcon);
				customWait(4000);
//				clickBtn(Locators_DeviceStability.videopauseIcon);
				customWait(4000);
				clickBtn(Locators_DeviceStability.OK_btn_resultConfirmation_dialog);
				minWait();
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Error in adding Video to MMS");
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
				if(!isElementExist(Locators_DeviceStability.cellularData_OnState)) {
					clickBtn(Locators_DeviceStability.cellularData_OffState);
					if(isElementExist(Locators_DeviceStability.OK)) {
						clickBtn(Locators_DeviceStability.OK);
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
					if(!isElementExist(Locators_DeviceStability.mobileData_OnState)) {
						clickBtn(Locators_DeviceStability.mobileData_OffState);
						if(isElementExist(Locators_DeviceStability.OK)) {
							clickBtn(Locators_DeviceStability.OK);
						}
						APP_LOGS.info("MobileData is Disabled");
						minWait();
					} else {
						APP_LOGS.info("MobileData is already Disabled");
					}
				} catch (Exception e) {			 
					e.printStackTrace();
				}	
			}	
		}
		
		public void navigateTo_CellularNetworks() throws InterruptedException, IOException {
			/* This Method will Navigate to Cellular Netwotks option. */ 
			minWait();
			try {
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"More\"))").click();
				minWait();
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Cellular networks\"))").click();
				minWait();
			} catch (Exception e) {			 
				e.printStackTrace();
			}
		}
}
