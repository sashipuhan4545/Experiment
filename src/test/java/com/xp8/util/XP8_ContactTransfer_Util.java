package com.xp8.util;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;

public class XP8_ContactTransfer_Util extends BaseUtil {

	public SoftAssert softAssert;

	public boolean check  = false;
	public boolean check1 = false;
	public boolean check2 = false;
	public boolean check3 = false;
	public boolean check4 = false;
	public boolean check5 = false;
	public boolean check6 = false;
	public boolean check7 = false;
	public boolean check8 = false;
	public boolean check9 = false;
	public boolean check10= false;
	//public static AndroidDriver<AndroidElement> aDriver;
	public static AndroidDriver<MobileElement> mdriver;


	public void logout() throws InterruptedException {
		longpress(4);
		minWait();
	}

	public void stopAdb() throws InterruptedException, IOException {
		customWait(4000);
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		APP_LOGS.info("Adb logs stopped succesfully. ");
		customWait(2000);
	}

	public void scroll() {
		try {
			org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
			System.out.println(size);
			int x=size.getWidth()/2;
			int starty=(int)(size.getHeight()*0.60);
			int endy=(int)(size.getHeight()*0.10);
			aDriver.swipe(x, starty, x, endy, 2000);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}


	public void launch_application(WebElement app) throws InterruptedException{
		/*
		 * Launches sonim scout and navigates to supports tab..
		 */
		try {
			//			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			Locators_ContactTransfer.AppListIcon.click();
			minWait();
			scroll();
			minWait();
			scroll();
			minWait();
			clickBtn(app);
			APP_LOGS.info("Application launched succesfully");
			minWait();
			clickBtn(Locators_ContactTransfer.utilitiesTab);
			minWait();
			clickBtn(Locators_ContactTransfer.ContactTransferApp);
			minWait();
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Application not found.");
			e.printStackTrace();
		}	
	}
	
	public void validate_Current_Screen(WebElement contactTransferTitle, String titleText) throws InterruptedException{

		/*
		 * Validates the current screen.
		 * Pass the element to be validated as parameter.
		 * 
		 */

		//String getTitleText = sonimCareTitle.getText();
		try {
			if(isElementExist(Locators_ContactTransfer.ContactTransferTitle)){
				check = true;
				String getTitleText = contactTransferTitle.getText();
				APP_LOGS.info("Current Screen is verified succesfully.");
				APP_LOGS.info("Current Screen title: "+getTitleText);
				softAssert_true(check, "Test case is pass");
			}else{
				APP_LOGS.info("Testcase failed");
				softAssert_false();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}

	public void validate_Default_TransferMode() throws InterruptedException {
		/*
		 * Check the mode of transfer in contact transfer screen.
		 * validate default mode
		 */
		try {		
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			if(!(Locators_ContactTransfer.BtRadioBtn).isSelected()){
				check1=true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			}
			if(!(Locators_ContactTransfer.MDBRadioBtn).isSelected()){
				check2=true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			}
			if(!(Locators_ContactTransfer.VCFRadioBtn).isSelected()){
				check3=true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			}
			if(!(Locators_ContactTransfer.CSVRadioBtn).isSelected()){
				check4=true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);	
			}

			if((check1)&&(check2)&&(check3)&&(check4)) {
				check= true;
				APP_LOGS.info("Default No selected Transfer Mode verified");
				Assert.assertTrue(check);
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+"\n"+": "+check4+ "\n");
				Assert.fail();
			}		
		}
		catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
		}
	}


	public void selectMoreOptions() throws InterruptedException {
		/*
		 * Selects more options
		 */
		try {
			minWait();
			clickBtn(Locators_ContactTransfer.CT_MoreOptns);
			minWait();
			clickBtn(Locators_ContactTransfer.AbtContactTransfer);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
		}
	}

	public void validateAboutText()	{
		/*
		 * check the String present in About Option
		 */
		try {
			String AboutInfo=Locators_ContactTransfer.About_CT_Text.getText();
			//		System.out.println(AboutInfo);
			if(AboutInfo.contains("All rights reserved")) {
				check= true;
				APP_LOGS.info("Default No selected Transfer Mode verified");
				Assert.assertTrue(check);
			}else{
				//			APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+"\n"+": "+check4+ "\n");
				Assert.fail();
			}	
			clickBtn(Locators_ContactTransfer.OKBtn_Abt);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void select_Transfer_Mode(String mode) throws InterruptedException{
		/*
		 * Selects the mode of transfer in contact transfer screen.
		 * Pass mode as parameter.
		 */

		try {
			if(mode.equalsIgnoreCase("Bluetooth")){
				clickBtn(Locators_ContactTransfer.BluetoothOptn);
				minWait();
				customWait(4000);
				for(int i=1; i<=3; i++) {
					minWait();
					if(isElementExist(Locators_ContactTransfer.AllowBtn)) {
						clickBtn(Locators_ContactTransfer.AllowBtn);
						minWait();
					}
					if(isElementExist(Locators_ContactTransfer.Allow_BT_Btn)) {
						clickBtn(Locators_ContactTransfer.Allow_BT_Btn);
						minWait();
					}
				}
			}else if(mode.equalsIgnoreCase("mdb")){
				clickBtn(Locators_ContactTransfer.MDBOptn);
				minWait();
				for(int i=1; i<=3; i++) {
					minWait();
					if(isElementExist(Locators_ContactTransfer.AllowBtn)) {
						clickBtn(Locators_ContactTransfer.AllowBtn);
						minWait();
					}
				}
			}else if(mode.equalsIgnoreCase("vcf")){
				clickBtn(Locators_ContactTransfer.VCFOptn);
				minWait();
				for(int i=1;i<=2; i++) {
					if(isElementExist(Locators_ContactTransfer.AllowBtn)) {
						clickBtn(Locators_ContactTransfer.AllowBtn);
					}
				}
			}else if(mode.equalsIgnoreCase("csv")){
				clickBtn(Locators_ContactTransfer.CSVOptn);
				minWait();
				for(int i=1;i<=2; i++) {
					if(isElementExist(Locators_ContactTransfer.AllowBtn)) {
						clickBtn(Locators_ContactTransfer.AllowBtn);
					}
				}
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateTransferModesPresent() throws InterruptedException {
		/*
		 * Validating by elements for all transfer modes present 
		 */
		try {
			minWait();
			if(isElementExist(Locators_ContactTransfer.BluetoothOptn)){
				check1=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(Locators_ContactTransfer.VCFOptn)){
				check2=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(Locators_ContactTransfer.MDBOptn)){
				check3=true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			} if(isElementExist(Locators_ContactTransfer.CSVOptn)){
				check4=true;

			}if((check1)&&(check2)&&(check3)&&(check4)) {
				check= true;
				APP_LOGS.info("All Transfer Mode Present");
				Assert.assertTrue(check);
			}
			else{
				APP_LOGS.info(check1+"\n"+": "+check2+"\n"+": "+check3+"\n"+": "+check4+ "\n");
				Assert.fail();
			}	
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
		}
	}

	public void selectContactfileToImport(int num) throws InterruptedException {
		/*
		 * Select file and choose all contact to import
		 */
		try {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Download\"))");
			minWait();
			clickBtn(Locators_ContactTransfer.DownloadOptn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//selects All option
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			for(int i=0;i<=num;i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
		}	
	}

	public void validateAllContactImport() {
		/*
		 * String validate Imported summary
		 */
		try {
			String AboutInfo=Locators_ContactTransfer.ImprtSummry_Text.getText();
			//		System.out.println(AboutInfo);
			if(AboutInfo.contains("IMPORTED CONTACTS SUMMARY")) {
				check= true;
				APP_LOGS.info("Contact Imported Summary verified");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Imported Contact is not completed");
				Assert.fail();
			}	
			clickBtn(Locators_ContactTransfer.DoneOptn);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateNotificationBar() throws InterruptedException {
		/*
		 * Verify Notification present when the Import Contact is completed
		 */
		try {
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
			minWait();
			aDriver.openNotifications();
			minWait();
			if(isElementExist(Locators_ContactTransfer.NotifctnTitle)) {
				check= true;
				APP_LOGS.info("Contact Imported at Notification is verified");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Contact Imported at Notification is unverified");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateCancelImport() throws InterruptedException {
		/*
		 * Verify the cancel functionality while importing contacts
		 */
		//		minWait();
		//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		minWait();
		try {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Download\"))");
			minWait();
			clickBtn(Locators_ContactTransfer.DownloadOptn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			for(int i=0;i<=45;i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
		}	
	}


	public void DisableFile(String file, WebElement mode, int num) throws InterruptedException {
		/*
		 * Validate Disable option when same mode of transfer file is choosen
		 */
		try {
			select_Transfer_Mode(file);
			minWait();
			customWait(4000);
			clickBtn(Locators_ContactTransfer.CT_MoreOptns);
			for(int i=0; i<=num; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if((mode).isSelected()){
				check=true;
				APP_LOGS.info("Verified Import option is disabled"+ file);
				Assert.assertTrue(check);
			}
			else {
				APP_LOGS.info("Import option is not disabled"+ file);
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void enterSearchField() throws InterruptedException, IOException {
		/*
		 *  Scrolls to and selectes the Download folder to be selected
		 */
		try {
			aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\")).scrollIntoView(new UiSelector().text(\"Download\"))");
			minWait();
			clickBtn(Locators_ContactTransfer.DownloadOptn);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);//Selects File
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			
//			((AndroidDeviceActionShortcuts) aDriver).pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
		}catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Element not found.");
			e.printStackTrace();
		}	
	}


	public void validateSearch() throws InterruptedException, IOException {
		/*
		 * Validate Entered Search name is filtered
		 */
		try {
			minWait();
			customWait(5000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait();
			if((isElementExist(Locators_ContactTransfer.ContactTransferTitle))){
				check=true;
				APP_LOGS.info("Verified Search field option");
				Assert.assertTrue(check);
			}
			else {
				APP_LOGS.info("Search field is unverified");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateFewImportContact() throws InterruptedException {
		/*
		 * validate selected contact imported 
		 */
		try {
			for(int i=0; i<=3;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}
			for(int i=0; i<=2;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			/*	
			((AndroidDeviceActionShortcuts) aDriver).pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();*/
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();
			String AboutInfo=Locators_ContactTransfer.ImprtSummry_Text.getText();
			if(AboutInfo.contains("IMPORTED CONTACTS SUMMARY")) {
				check= true;
				APP_LOGS.info("Few Contact Imported Summary verified");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Few Imported Contact is not completed");
				Assert.fail();
			}	
			clickBtn(Locators_ContactTransfer.DoneOptn);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}


	public void validateUFMIFormat() throws InterruptedException {
		/*
		 * Validate UFMI format contact can be imported
		 */
		try {
			for(int i=0; i<=4;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}
			for(int i=0; i<=3;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait();
			}
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			minWait();

			String UFMI_Num=Locators_ContactTransfer.UFMI_Search.getText();
			System.out.println(UFMI_Num);
			if(UFMI_Num.contains("*")) {
				check= true;
				APP_LOGS.info("UFMI Search and Contact Imported verified");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("\"UFMI Search and Contact Importedis not completed");
				Assert.fail();
			}	clickBtn(Locators_ContactTransfer.DoneOptn);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateBrowse_Title(WebElement title, String BrowseTitle) throws InterruptedException {
		/*
		 * After selecting mode of transfer validate directed Browse file Title
		 */
		try {
			customWait(4000);
			customWait(4000);
			String s=title.getText();
			System.out.println(s);
			if(s.equalsIgnoreCase(BrowseTitle)) {
				check= true;
				APP_LOGS.info("verified Browse Title Contact Imported "+ title);
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Unverified Browse Title Contact Imported "+ title);
				Assert.fail();
			}	clickBtn(Locators_ContactTransfer.DoneOptn);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateImportSummaryPageAfterReopen() throws InterruptedException {
		/*
		 * Verify Import Complete Summary Screen launching the App from Notification Bar 
		 */
		try {
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
			minWait();
			aDriver.openNotifications();
			minWait();
			clickBtn(Locators_ContactTransfer.NotifctnTitle);
			String AboutInfo=Locators_ContactTransfer.ImprtSummry_Text.getText();
			if(AboutInfo.contains("IMPORTED CONTACTS SUMMARY")) {
				check= true;
				APP_LOGS.info("Summary Imported Contact screen is Opened");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Summary Imported Contact screen is not opened");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validateDeafaultModeMoreOptions() throws InterruptedException {
		/*
		 * check the default state of More options
		 */

		try {
			clickBtn(Locators_ContactTransfer.CT_MoreOptns);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait();
			if((Locators_ContactTransfer.ImportBTOptn).isEnabled()) {
				check1 = true;
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if((Locators_ContactTransfer.ImportMBDOptn).isEnabled()) {
				check2 = true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if((Locators_ContactTransfer.ImportVCFOptn).isEnabled()) {
				check3 = true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if((Locators_ContactTransfer.ImportCSVOptn).isEnabled()) {
				check4 = true;
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}

			if((check1)&&(check2)&&(check3)&&(check4)){
				check=true;
				APP_LOGS.info("Verified Default Mode of MoreOptions");
				Assert.assertTrue(check);
			}
			else {
				APP_LOGS.info("Verified Default Mode of MoreOptions");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
}
