package com.xp8.ATTUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.xp8.util.Locators_XP8_Sanity;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class Stability_Browser_Util extends BaseUtil {

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

	public String fetchOperatorName() throws FileNotFoundException, IOException, ParseException {
//		product_DUT.setText("XP5800");

		if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT")) {



			return("ATT"); 
		}

		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-18.")) {

			return("Rogers"); 

		}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11.")) {

			return("Bell"); 

		}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-12.")) {

			return("Telus"); 

		}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29.")) {


			return("Sprint"); 
		}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26.")) {


			return("SL"); 
		}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-32.")) {

			return("ACG"); 

		}
		else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-34.")) {

			return("USC"); 

		}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15.")) {

			return("Verizon"); 

		}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-00")) {

			return("MainLine"); 
		}
		return "GEN";

	}
	
	
	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		return dateFormat.format(date);
	}
	
	public static String getCurrentTime() {
		
		  Date date = new Date();
		    String strDateFormat = "hh-mm-ss";
		    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		    String formattedDate= dateFormat.format(date);
		    System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
		return formattedDate;
		
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

	public void clearChromePermission() {
		try {
			customWait(3000);
			clickBtn(Locators_Stability.ACCEPTCONTINUE);
			customWait(5000);
			clickBtn(Locators_Stability.NEXT);
			customWait(3000);
			clickBtn(Locators_Stability.NO_THANKS);
			customWait(2000);
			//Runtime.getRuntime().exec("adb shell input tap 298 1764");
			//minWait();
			//Runtime.getRuntime().exec("adb shell input tap 408 1764");
			//minWait();
			//Runtime.getRuntime().exec("adb shell input tap 48 1764");
		} catch (Exception e) {	 	
			e.printStackTrace();
		}			
	}

	public void moreOptions() throws InterruptedException {
		try {
			customWait(2000);
			if(isElementExist(Locators_Stability.moreOptions)) {
				clickBtn(Locators_Stability.moreOptions);
			}
			else if(isElementExist(Locators_Stability.moreUpdateOptions)) {
				clickBtn(Locators_Stability.moreUpdateOptions);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void setDataSaver_On() throws InterruptedException {
		try {
			minWait();
			moreOptions();
			minWait();
			scrollToText("Settings");
			minWait();
			scrollToText("Data Saver");
			minWait();
			clickBtn(Locators_Stability.switch_Off_State);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (Exception e) {
		}		
	}

	public void editHomepageOpen(String newUrl) throws InterruptedException 
	//Navigate to homepage and set AT&T home page
	{ 
		try {
			minWait();
			moreOptions();
			minWait();
			scrollToText("Settings");
			minWait();
			scrollToText("Home page");
			minWait();
			scrollToText("Open this page");
			minWait();
			//			 Locators_Stability.OpnPage.click();
			minWait();
			enterTextToInputField(Locators_Stability.OpnPgeField,newUrl);
			customWait(2000);
			Locators_Stability.SaveOptns.click();
			navigateback();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			navigateback();
		}

	}

	public void navigateback() throws InterruptedException {
		//click on navigate Icon

		try {
			minWait();
			for(int i=1; i<=4; i++) {
				if(isElementExist(Locators_Stability.NavigateUpSrchEng)) {
					minWait();
					Locators_Stability.NavigateUpSrchEng.click();
					minWait(); 
				}
				else if(isElementExist(Locators_Stability.NavigateBack)) {
					minWait();
					Locators_Stability.NavigateBack.click();
					minWait(); 
				}
				else if(isElementExist(Locators_Stability.closeIcon)) {
					minWait();
					Locators_Stability.closeIcon.click();
					minWait(); 
				}
				
				else if(isElementExist(Locators_Stability.close)) {
					minWait();
					System.out.println("In Closing");
					Locators_Stability.close.click();
					minWait(); 
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void checkBookmarkPage() throws InterruptedException {
		//Check page is Bookmarked or not
		try {
			minWait();
			minWait();
			moreOptions();
			minWait();
			if(isElementExist(Locators_Stability.editBookmark)) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			}
			else if(isElementExist(Locators_Stability.BookmarkIcon)) {
				minWait();
				clickBtn(Locators_Stability.BookmarkIcon);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public void navigateBrowserMenu(String url) throws InterruptedException {
		//Navigate to Browser menu to any option
		try {
			System.out.println("Entering URL");
			customWait(2000);
			enterTextToInputField(Locators_Stability.urlBar_Chrome, url);
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail();
		}

	}
	
	public void validateUrlEntered(String web,int n, int numitr)
	{
		/*
		 * validate the website page is loaded via adb logs
		 */
		SoftAssert S2= new SoftAssert();
		try { 
			customWait(25000);
			if(isElementExist(Locators_Stability.BlockBtn)) {
				clickBtn(Locators_Stability.BlockBtn);
				minWait();
			}
			if(isElementExist(Locators_Stability.networkNotAvailable)||isElementExist(Locators_Stability.WebPageBlocked)){
				test.log(LogStatus.FAIL, "Entered Website page " + "\""+ web +"\"" + " not Loaded successfully at iteration: "+n);
			} 
			else {
				check = true;
				APP_LOGS.info("Url entered sucessfully.");
				test.log(LogStatus.INFO, "Entered Website page " + "\""+ web +"\"" + " Loaded Successfully at iteration: "+n);
				S2.assertTrue(check, " ");
			}
		}
		catch (Exception e) {
			APP_LOGS.info("Element not Found");
			e.printStackTrace();
		} 
		S2.assertAll();
	}

	public void navigateBookmark() throws InterruptedException {
		//Navigate to Browser menu to any option
		try {
			System.out.println("Bookmark");
			customWait(2000);

			moreOptions();
			minWait();
			if(isElementExist(Locators_Stability.editBookmark)) {
				minWait();
				scrollToText("Bookmarks");
				minWait();
				clickBtn(Locators_Stability.MobileBookMark);
				minWait();
				for(int i=1; i<=10;i++) {
					if (isElementExist(Locators_Stability.BookmarkmoreOptions)) {
						clickBtn(Locators_Stability.BookmarkmoreOptions);
						minWait();
						clickBtn(Locators_Stability.deleteContactOptn);
						minWait();			
					}
					navigateback();
				}
			}
			else {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				System.out.println("No Bookmarks Page");
			}

		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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


	public void bookmarkOpen(WebElement page,String openPage) throws InterruptedException {
		//open bookmarked website

		try {
			System.out.println("In BookMArk ");
			minWait();
			moreOptions();
			minWait();
			scrollToText("Bookmarks");
			minWait();
			clickBtn(Locators_Stability.MobileBookMark);
			minWait();
			scrollToText(openPage);
			minWait();
			clickBtn(page);
			customWait(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*scrollToText("Amazon.com");
        minWait();
        clickBtn(Locators_Stability.GooglePage);*/
	}

	
	public void linkToLink(int n) throws InterruptedException {
		//click on Sign Up link
		
		try {
			scrollToText("SIGN UP");
			minWait();
			clickBtn(Locators_Stability.SignUpLink);
			customWait(2000);
			
			if(isElementExist(Locators_Stability.SignUpPage)) {
				test.log(LogStatus.INFO, "Verified Navigation Link to Link at iteration : "+n);
				
			}
			else {
				test.log(LogStatus.ERROR, "Unverified Navigation Link to Link at iteration : "+n);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void validateHomePageLoad(int n) {
		//Validate ATT Home PAge is loaded
		
		try {
			customWait(2000);
			if(isElementExist(Locators_Stability.attnet)) {
				System.out.println("AT&T Homepage");
				test.log(LogStatus.INFO, "Navigated to AT&T Homepage at iteration : "+n);
			}
			else {
				System.out.println("AT&T Homepage");
				test.log(LogStatus.ERROR, "AT&T Homepage not loaded at iteration : "+n);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void validateLoadWebsites(int n,String webPage) throws InterruptedException {
		//validate till load website completely
		
		try {
			WebDriverWait wait = new WebDriverWait(aDriver, 30);
			wait.until(ExpectedConditions.visibilityOf(Locators_Stability.toolbarSlider));
			minWait();
			
			if(isElementExist(Locators_Stability.toolbarSlider)) {
				test.log(LogStatus.INFO, "Website "+webPage+" loaded successfully at iteration : "+n);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void selectHistoryPg() throws InterruptedException {
		try {
			customWait(5000);
			moreOptions();
			APP_LOGS.info("Chrome Menu is Selected sucessfully");
			minWait();
			scrollToText("History");
			APP_LOGS.info("History Option is Selected sucessfully");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clearHistry() throws InterruptedException {
		try {
			selectHistoryPg();
			minWait();
			Locators_Stability.ClearBrowsngDataOptn.click();
			APP_LOGS.info("Clear Browsing Data.. is Clicked sucessfully");
			minWait();
			Locators_Stability.ClrDataDropdown.click();
			APP_LOGS.info("Clear Data option is Clicked sucessfully");
			minWait();
			Locators_Stability.begningTimeOptn.click();
			APP_LOGS.info("From Begning of time Option is Selected sucessfully");
			minWait();
			Locators_Stability.ClrDataBtn.click();
			APP_LOGS.info("History Clear Data Button is Selected sucessfully");
			minWait();
			if(isElementExist(Locators_Stability.HistryClear)) {
				Locators_Stability.HistryClear.click();
				minWait();
			}	
			customWait(2000);
			navigateback();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		}
		catch (Exception e) {
			e.printStackTrace();
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
		catch(Exception e) {
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
		catch(Exception e) {
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
				//					enterTextToInputField(Locators_Stability.wifi_IDC_Psswd,"1dcS0n1md0tc0MbLr");
//				Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1dcS0n1md0tc0MbLr");		
				passwordEnter();
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

	
	public void passwordEnter() throws IOException, InterruptedException {
		minWait();
		 Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1");
	     minWait();
	     Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text dcS");
	     minWait();
	     Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 0");
	     minWait();
	     Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text n");
	     minWait();
	     Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 1");
	     minWait();
	     Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text md");
	     minWait();
	     Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 0");
	     minWait();
	     Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text tc");
	     minWait();
	     Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text 0");
	     minWait();
	     Runtime.getRuntime().exec("adb -s "+p_Id+" shell input text MbLr");
	     minWait();
	    
	}
	
	

}
