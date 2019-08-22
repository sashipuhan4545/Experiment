package com.xp8.ATTUtil;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

import org.bytedeco.javacpp.tesseract.UNICHAR.const_iterator;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.xp8.util.BaseUtil;
import com.xp8.util.HomeController;
import com.xp8.util.JsonFileReaderAndWriter;
import com.xp8.util.Locators_BaseUtil;
import com.xp8.util.Locators_DeviceStability;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;

public class Stability_Email_Util extends BaseUtil {

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

	public void OCRScreencapture(String fileName) throws InterruptedException {
		//Capture required Screen shots for validation

		OCR.Read_File.takeScreenShotForOcr(fileName);
		Thread.sleep(2000);
		OCR.my_main.validate_Using_OCR(fileName+".png");
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
				System.out.println("Continue; ");
			}   
			System.out.println("Out ");
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}									
	}


	//This method will provide the user a searched  string from the adb log text file  
	public boolean searchStringOCR(String searchstring, String fileName) {

		boolean check = false;
		boolean check1=false;
		boolean check2=false;

		try {

			BufferedReader bf = new BufferedReader(new FileReader("src/test/resources/OCR_FILES/"+fileName+".txt"));
			int linecount = 0;
			String line;
			APP_LOGS.info("Searching for " + searchstring + " in file...");

			while (( line = bf.readLine()) != null){

				boolean indexfound = line.contains(searchstring);

				if (indexfound) {
					check=true;
					APP_LOGS.info("Word "+searchstring+" was found at position " + indexfound + " on line " + linecount);
					break;
				}
				else {
					// APP_LOGS.info("Word "+searchstring+" was not found at position " + indexfound + " on line " + linecount);
					check=false;
					linecount++;
				}
			}
			bf.close();
		}
		catch (IOException e) {
			APP_LOGS.info("IO Error Occurred: " + e.toString());
		}
		return check;
	}

	public String startRIL_Log() throws AWTException, InterruptedException, IOException {

		Thread.sleep(1000);
		String fileName = UUID.randomUUID().toString().replaceAll("-","");
		System.out.println(fileName);
		String path = "src/test/resources/adbLogs/"+fileName+".txt";
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" logcat -c");
			Thread.sleep(1000);
			p=Runtime.getRuntime().exec("cmd /C \"adb -s "+p_Id+" logcat -b all -v threadtime>\""+path);
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public boolean validate_RIL_Logs(String filename,String validationString,String infoForFailure) throws InterruptedException {

		customWait(2000);
		boolean check1 = searchString(validationString,filename);
		SoftAssert sf = new SoftAssert();
		if (check1) {
			check=true;
			test.log(LogStatus.INFO, "Validated from ADB Logs : "+validationString);
		} else {			
			test.log(LogStatus.INFO, "Validation failed from ADB Logs. ==>> "+infoForFailure);
		}
		sf.assertAll();
		return check1;
	}

	

	public void navigateToGmail() throws InterruptedException {
		//navigate all accept agree option when gmail launch first time
		if(isElementExist(Locators_Stability.gmail_gotIt)) {
			minWait();
			clickBtn(Locators_Stability.gmail_gotIt);		
		}
		clickBtn(Locators_Stability.add_email_address);	
		minWait();
		clickBtn(Locators_Stability.googleAccount);
		waituntilnew(Locators_Stability.memory_Panel, 1500000);
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

	public void clickOnAccounts() {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Accounts\"))").click();
			minWait();
		} catch (Exception e) {

		}
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
			if(!isElementExist(Locators_Stability.cellularData_OnState)) {
				clickBtn(Locators_Stability.cellularData_OffState);
				if(isElementExist(Locators_Stability.OK)) {
					clickBtn(Locators_Stability.OK);
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
				if(!isElementExist(Locators_Stability.mobileData_OnState)) {
					clickBtn(Locators_Stability.mobileData_OffState);
					if(isElementExist(Locators_Stability.OK)) {
						clickBtn(Locators_Stability.OK);
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
	
	public void clickOnWifi() {
		try {
			minWait();
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"Wi-Fi\"))").click();
			minWait();
		} catch (Exception e) {
		}
	}

	public void setUp_And_Enable_WiFi() {
		try {
			minWait();
			if (!isElementExist(Locators_Stability.wifi_ConnecteState)) {
				customWait(2000);
				clickBtn(Locators_Stability.switch_OffState);
				customWait(10000);
				if (!isElementExist(Locators_Stability.wifi_ConnecteState)) {
					aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.support.v7.widget.RecyclerView\")).scrollIntoView(new UiSelector().text(\"IDCSONWAP\"))").click();
					minWait();
					enterTextToInputField(Locators_Stability.wifiPasswordTextBox,"1dcS0n1md0tc0MbLr");
					minWait();
					clickBtn(Locators_Stability.connect);
					customWait(7000);
				}
			}			
		} catch (Exception e) {
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
		} catch (NoSuchElementException e) {			 
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

	public void click_NewMail() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_Stability.compose_gmail);
			minWait();
		} catch (Exception e) {
		}
	}

	public void compose_NewMail(String TO, String subject, String mailContent) throws InterruptedException {

		try {
			customWait(4000);
			enterTextToInputField(Locators_Stability.To_gmail, TO);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			enterTextToInputField(Locators_Stability.subject_gmail, subject);
			minWait();
			enterTextToInputField(Locators_Stability.compose_Mail, mailContent);
			minWait();
		} catch (Exception e) {
		}		
	}

	public void send_Mail() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Stability.send_gmail);
			minWait();
		} catch (Exception e) {
		}
	}

	public void attach_Mail() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Stability.attach_gmail);
			minWait();
			clickBtn(Locators_Stability.attach_File);
			minWait();
			clickBtn(Locators_Stability.File);
			minWait();
		} catch (Exception e) {
		}
	}



	public void navigate_MailOptns() throws InterruptedException {
		//navigate to sent mail options
		try {
			minWait();
			clickBtn(Locators_Stability.navigate_OptionMail);
			minWait();

			clickBtn(Locators_Stability.sent_MailOptn);
			minWait();
		} catch (Exception e) {
		}
	}



	public void forwardMail(String TO,String subject, String mailContent,WebElement clicK_Mail) throws InterruptedException {
		try {
			minWait();
			clickBtn(clicK_Mail);
			minWait();
			clickBtn(Locators_Stability.moreOptions_Mail);
			minWait();
			clickBtn(Locators_Stability.forward_Mail);
			minWait();
			customWait(2000);
			enterTextToInputField(Locators_Stability.To_gmail, TO);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			minWait();
			//			enterTextToInputField(Locators_Stability.subject_gmail, subject);
			minWait();
			enterTextToInputField(Locators_Stability.compose_Mail, mailContent);
			minWait();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteAllmails() throws InterruptedException {

		try {
			minWait();
			clickBtn(Locators_Stability.dismiss_icon_Gmail);
			minWait();			
			while(isElementExist(Locators_Stability.select_Mail)) {
				clickBtn(Locators_Stability.select_Mail);
				minWait();
				clickBtn(Locators_Stability.delete_gmail);
				minWait();
			}

		} catch (Exception e) {
		}
	}

	public void validate_Mail(String validationText, int n) throws InterruptedException {
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'unsent in Outbox')]")));
			System.out.println("In Validation");
			minWait();
			boolean check1 = aDriver.findElementByXPath("//*[contains(@content-desc,'"+validationText+"')]").isDisplayed();
			System.out.println(check1);
			SoftAssert sf = new SoftAssert();
			if(check1) {
				check=true;
				APP_LOGS.info("Mail sent Succeccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Email message has been successfully sent at iteration : "+ n);
			} else {
				APP_LOGS.info("Mail didn't sent");
				sf.fail();
				test.log(LogStatus.FAIL,"Email didn't Sent.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void validate_OpenMail(String validationText, int n) throws InterruptedException {
		WebDriverWait wt = new WebDriverWait(aDriver, 60);
		try {
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'unsent in Outbox')]")));
			System.out.println("In Validation");
			minWait();
			boolean check1 = aDriver.findElementByXPath("//*[contains(@text,'"+validationText+"')]").isDisplayed();
			System.out.println(check1);
			SoftAssert sf = new SoftAssert();
			if(check1) {
				check=true;
				APP_LOGS.info("Mail Open Succeccfully");
				sf.assertTrue(check, "TestCase Valiation is PASS");
				test.log(LogStatus.PASS, "Email message has been successfully Open at iteration : "+ n);
			} else {
				APP_LOGS.info("Mail didn't Open");
				sf.fail();
				test.log(LogStatus.FAIL,"Email didn't Open.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	public void clickOn_StopRecord() throws InterruptedException {
		try {
			clickBtn(Locators_Stability.stopButton);
			minWait();
		} catch (NoSuchElementException e) {			 
			e.printStackTrace();
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
		if(isElementExist(Locators_Stability.recording_ListTitle)) {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		}
		clickOn_Record();
		clearAllow();
	   customWait(1000);
		clickOn_StopRecord();
		clickOn_Save(fileName);
		customWait(3000);
		TouchAction action = new TouchAction(aDriver);
		action.longPress(Locators_Stability.file_list_SoundRec).release().perform();;
		customWait(1000);
		clickBtn(Locators_Stability.share_File);
		minWait();
		clickBtn(Locators_Stability.gmail_Share);
		minWait();
		clearAllow();
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
	
	
	///********************************************** new methods for Android orio ******************************** ////////
	

	public void clickOn_Networks_and_Internet() {
		try {
			minWait();
			scrollToText("Network & Internet");
			minWait();
		} catch (Exception e) {
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
		} catch (Exception e) {
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
				if(isElementExist(Locators_Stability.wifi_IDC)) {
					customWait(2000);
					clickBtn(Locators_Stability.wifi_IDC);
					APP_LOGS.info("IDC available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_Stability.wifi_Dellas)) {
					minWait();
					clickBtn(Locators_Stability.wifi_Dellas);
					APP_LOGS.info("Dellas available secured wifi is Selected");
					break;
				}
				else if(isElementExist(Locators_Stability.wifi_Cannada)) {
					minWait();
					clickBtn(Locators_Stability.wifi_Cannada);
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
			if(isElementExist(Locators_Stability.SSIDTxt)) {
			String getSSIDTitle = Locators_Stability.SSIDTxt.getText();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			changeToNumberMode();
			customWait(2000);
			if(getSSIDTitle.equalsIgnoreCase("IDCSONWAP")) {
				minWait();
				clickBtn(Locators_Stability.wifi_IDC_Psswd);
				customWait(4000);
				//					enterTextToInputField(Locators_DeviceStability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");		
				customWait(3000);	
			}
			minWait();
			String psswrd = Locators_Stability.wifi_IDC_Psswd.getText();
			System.out.println(psswrd);
			customWait(1000);
			clickBtn(Locators_Stability.wifi_IDC_ConnectBtn);
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
			Locators_Stability.wifi_IDC_Psswd.sendKeys("123");
			customWait(1500);
			String text = Locators_Stability.wifi_IDC_Psswd.getText();
			System.out.println(text);
			if(!text.equals("123")) {
				for (int i = 0; i < 3; i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_POUND);
					minWait();
				}
			}
			Locators_Stability.wifi_IDC_Psswd.clear();
		} catch (Exception e) {
			e.printStackTrace();
			APP_LOGS.info("Wi-Fi Forget button: No such Element found");
		}
	}
	
	public void performAction_O() throws IOException, InterruptedException {
		/*
		 * Perform action for IMS registered check
		 */
		try {
			customWait(2000);
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell am start -a android.settings.AIRPLANE_MODE_SETTINGS");
			minWait();
			for(int i=1; i<=9; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			enableSwitch(Locators_Stability.enabled_Airplane,Locators_Stability.disabled_Airplane,Locators_Stability.switch_widget);
			customWait(5000);
			disableSwitch(Locators_Stability.disabled_Airplane,Locators_Stability.enabled_Airplane,Locators_Stability.switch_widget );		
			customWait(15000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void remove_GoogleAcccount_Orio() {
		//remove added google Account if any 
		try {
			scrollToText("Users & accounts");
//			clickOnAccounts();
			minWait();
			if(isElementExist(Locators_Stability.connectedAccount)) {
				System.out.println("Account is present");
				minWait();
				clickBtn(Locators_Stability.connectedAccount);
				minWait();
				clickBtn(Locators_Stability.REMOVE_ACCOUNT);
				minWait();
				clickBtn(Locators_Stability.REMOVE_ACCOUNT);
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
			clickBtn(Locators_Stability.add_Account);
			minWait();
			clickBtn(Locators_Stability.google_Account);
			customWait(1600);
			wt.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(@text,'Checking info')]")));
			minWait();
		} catch (Exception e) {

		}
	}
}
