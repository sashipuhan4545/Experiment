package com.xp8.util;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Safeguard_Util extends BaseUtil {

	public SoftAssert softAssert;
	public boolean check = false;
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

	public void scroll() {
		try {
			org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
			System.out.println(size);
			int x=size.getWidth()/2;
			int starty=(int)(size.getHeight()*0.60);
			int endy=(int)(size.getHeight()*0.10);
			aDriver.swipe(x, starty, x, endy, 2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void launchScoutApp() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			minWait();
			Locators_Safeguard.AppListIcon.click();
			scroll();
			minWait();
			scroll();
			minWait();
			scroll();
			minWait();
			clickBtn(Locators_Safeguard.sonim_scout_AppLauncher);
			minWait();
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
				enter_Pin_App();
				minWait();
			}
			clickBtn(Locators_Safeguard.SG_SetUp_String);
			minWait();
			clickBtn(Locators_Safeguard.SG_Safe_Guard_String);
			APP_LOGS.info("Clicked on Safeguard Tab successfully");
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void sf_app_Presence_Validation() throws InterruptedException {
		try {
			SoftAssert SA1=new SoftAssert();
			minWait();
			//clickBtn(Locators_Safeguard.SG_SetUp_String);		
			minWait();	
			String fetch_sf_text=Locators_Safeguard.SG_String.getText();
			APP_LOGS.info("Fetching safeguard string text is " +fetch_sf_text);
			SA1.assertEquals(Locators_Safeguard.SG_String.getText(), "SafeGuard");
			SA1.assertAll();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void sf_app_Verify_Defualt_state() throws InterruptedException, IOException {
		//This method is used to check the default state of SF 
		
		try {
			SoftAssert SA2=new SoftAssert();
			minWait();
			//clickBtn(Locators_Safeguard.SG_Safe_Guard_String);
			minWait();
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				check=true;
				APP_LOGS.info("Activation status is OFF");
				SA2.assertTrue(check, "Deafault Activation status is OFF after flasing the device");
			}
			else {
				APP_LOGS.info("Activation status is ON");
				SA2.fail();
			}	
			SA2.assertAll();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}


	public String sf_app_Check_Activation_Off_On() throws InterruptedException, IOException {
		//This method is used to check wheather activation is ON or OFF.
		String status = null;
		try {
			minWait();
			status = null;
			//		navigateToSafeGuard();
			if(Locators_Safeguard.SG_toggle_btn.getText().equals("OFF")) {
				status=Locators_Safeguard.SG_toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for OFF" +status);
			}
			else if(Locators_Safeguard.SG_toggle_btn.getText().equals("ON")){
				status=Locators_Safeguard.SG_toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for ON" +status);
			}
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public void navigateToSafeGuard() throws InterruptedException, IOException {
		try {
			minWait();
			aDriver.launchApp();
			minWait();
			APP_LOGS.info("Inside Safeguard Settings screen");
			clickBtn(Locators_Safeguard.SG_SetUp_String);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			clickBtn(Locators_Safeguard.SG_Safe_Guard_String);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}


	//This methos is used to verify wheather Version and Help options are enabled or not .
	public void verify_Version_Help_In_Default_State() throws InterruptedException, IOException {

		SoftAssert SA3=new SoftAssert();
		//navigateToSafeGuard();
		try {
			minWait();
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				
				for(int i=0;i<=4;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
				}
				APP_LOGS.info("Scrolled Till Help Text and Version");
				minWait();

				if(Locators_Safeguard.SG_Version.isEnabled() && Locators_Safeguard.SG_Help.isEnabled()){
					check=true;
					APP_LOGS.info("Version and Help text are enabled when safe guard is in OFF mode");
					SA3.assertTrue(check, "Version and Help is Present");
				}
				else {
					APP_LOGS.info("Version and Help text are not enabled when safe guard is in OFF mode");
					SA3.fail();
				}
			} else {
				APP_LOGS.info("Executing Else Part for Verifying version and Help TC");
 
				turn_Off_On_SG();
				for(int i=0;i<=4;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
				}
				APP_LOGS.info("Execting in Else Part :Scrolled Till Help Text and Version");
				minWait();

				if(Locators_Safeguard.SG_Version.isEnabled() && Locators_Safeguard.SG_Help.isEnabled()) {
					check=true;
					APP_LOGS.info("Else Part : Version and Help text are enabled when safe guard is in OFF mode");
					SA3.assertTrue(check, "Version and Help is Present");
				}
				else {
					APP_LOGS.info("Version and Help text are not enabled when safe guard is in OFF mode");
					SA3.fail();
				}
			}
			SA3.assertAll();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	//This Method will turn OFF/turn On the SF
	public void turn_Off_On_SG() throws InterruptedException {
		try {
			minWait();
			clickBtn(Locators_Safeguard.SG_toggle_btn);
			customWait(2000);
			enter_Pin();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}


	public void enter_Pin() throws InterruptedException {
		try {
			minWait();
			minWait();
			aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").sendKeys("1234");
			APP_LOGS.info("PIN has been entered successfully");
			minWait();
			if(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled()) {
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
			}
			else {
				APP_LOGS.info("Pin Ok button is not enabled");
				softAssert_false();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void enter_Pin_App() throws InterruptedException {
		try {
			minWait();
			minWait();
			aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/editText\")").sendKeys("1234");
			APP_LOGS.info("PIN has been entered successfully");
			minWait();
			if(Locators_Safeguard.SG_OK_Btn_for_PinApp.isEnabled()) {
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_OK_Btn_for_PinApp);
			}
			else {
				APP_LOGS.info("Pin Ok button is not enabled");
				softAssert_false();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	//This method will navigate to Help option and check the default Pin.
	public void navigate_To_Help_Fetch_Pin_Check_Version() throws InterruptedException, IOException {

		SoftAssert SA4=new SoftAssert();

		try {
			boolean ch1=true;
			boolean ch2=true;
			minWait();
			// navigateToSafeGuard();
			minWait();
			for(int i=0;i<=4;i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(1000);
			}
			//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.LinearLayout\")).scrollIntoView(new UiSelector().text(\"Help\"))");

			clickBtn(Locators_Safeguard.SG_Help);
			minWait();
			if(Locators_Safeguard.SG_Help_text.getText().contains("Default PIN is 1234")){
				ch1=true;
			}
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.BACK);
			minWait();
			if(Locators_Safeguard.SG_Version_summary_text.getText().equals("")) {
				ch2=true;
			}

			if((ch1) && (ch2)) {
				check=true;
				APP_LOGS.info("TC_Fetching Help Text");
				SA4.assertTrue(check, "");
			}
			else {
				SA4.fail();
			}
			SA4.assertAll();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	//This method is used to check the deafult state of APPS,Features,Pintime out should be accessible 
	public void verify_Default_States_Of_All_Options_SG() throws InterruptedException, IOException {

		SoftAssert SA5=new SoftAssert();

		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {

				turn_Off_On_SG();
				SA5.assertTrue(Locators_Safeguard.SG_Applications_String.isEnabled(),"Application is enabed");
				APP_LOGS.info("Checking the Applications is enabled or not :" +Locators_Safeguard.SG_Applications_String.isEnabled());
				SA5.assertTrue(Locators_Safeguard.SG_Features_String.isEnabled(),"Feature is enabled");
				APP_LOGS.info("Checking the Feature is enabled or not :" +Locators_Safeguard.SG_Features_String.isEnabled());
				SA5.assertTrue(Locators_Safeguard.SG_Change_Pin_String.isEnabled(),"Pin is enabled");
				APP_LOGS.info("Checking the Pin is enabled or not :" +Locators_Safeguard.SG_Change_Pin_String.isEnabled());

				minWait();
				for(int i=0;i<=5;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
				}
				minWait();
				SA5.assertTrue(Locators_Safeguard.SG_Version_String.isEnabled(), "Version is enabled");
				APP_LOGS.info("Checking the Version is enabled or not :" +Locators_Safeguard.SG_Version_String.isEnabled());
				SA5.assertTrue(Locators_Safeguard.SG_Help_String.isEnabled(), "Help is enabled");
				APP_LOGS.info("Checking the Help is enabled or not :" +Locators_Safeguard.SG_Help_String.isEnabled());
			}
			else {
				SA5.assertTrue(Locators_Safeguard.SG_Applications_String.isEnabled(),"Application is enabed");
				APP_LOGS.info("Checking the Applications is enabled or not :" +Locators_Safeguard.SG_Applications_String.isEnabled());
				SA5.assertTrue(Locators_Safeguard.SG_Features_String.isEnabled(),"Feature is enabled");
				APP_LOGS.info("Checking the Feature is enabled or not :" +Locators_Safeguard.SG_Features_String.isEnabled());
				SA5.assertTrue(Locators_Safeguard.SG_Change_Pin_String.isEnabled(),"Pin is enabled");
				APP_LOGS.info("Checking the Pin is enabled or not :" +Locators_Safeguard.SG_Change_Pin_String.isEnabled());

				minWait();
				for(int i=0;i<=5;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
				}
				minWait();

				SA5.assertTrue(Locators_Safeguard.SG_Version_String.isEnabled(), "Version is enabled");
				APP_LOGS.info("Checking the Version is enabled or not :" +Locators_Safeguard.SG_Version_String.isEnabled());
				SA5.assertTrue(Locators_Safeguard.SG_Help_String.isEnabled(), "Help is enabled");
				APP_LOGS.info("Checking the Help is enabled or not :" +Locators_Safeguard.SG_Help_String.isEnabled());

			}
			SA5.assertAll();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}


	public void select_All_Apps_And_Check() throws InterruptedException, IOException {
		//clearRecentApps();
		try {
			customWait(1000);
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One();
			}
			else {
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	//This is a common method used for 
	public void pass_Locators(WebElement loc1,WebElement loc2,WebElement loc3) throws InterruptedException {
		try {
			minWait();
			clickBtn(loc1);
			minWait();
			enter_Pin();
			customWait(2000);
			aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(3000);
			clickBtn(loc2);
			minWait();
			//aDriver.pressKeyCode(AndroidKeyCode.MENU);
			customWait(3000);
			clickBtn(loc3);
			minWait();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	//This method is used to validate the SG Locked screen
	//This methos is used to validate whaether an App is launched sucessfully or not
	//This method will launch All the Apps present in the device one by one and validate it 
	public void launching_All_Apps_One_By_One() throws InterruptedException, IOException {

		boolean ch1=false;
		boolean ch2=false;
		boolean ch3=false;
		boolean ch4=false;
		boolean ch5=false;
		boolean ch6=false;
		boolean ch7=false;
		boolean ch8=false;
		boolean ch9=false;
		boolean ch10=false;
		boolean ch11=false;
		boolean ch12=false;
		boolean ch13=false;
		

		SoftAssert SA7=new SoftAssert();
		//clearRecentApps();
		customWait(1000);
		Runtime.getRuntime().exec("adb shell am start -n  com.caf.fmradio/com.caf.fmradio.FMRadio");
		APP_LOGS.info("FM launched successfully");
		customWait(2000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch1=true;
				APP_LOGS.info("ch1: "+ch1);
			}
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:FM Radio");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ConversationList");
		APP_LOGS.info("mms launched successfully");
		customWait(2000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch2=true;
				APP_LOGS.info("ch2: "+ch2);
			}
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:mms");
		}
		minWait();
		//clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n org.codeaurora.snapcam/com.android.camera.CameraActivity");
		APP_LOGS.info("Camera launched successfully");
		customWait(2000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
				ch3=true;	
				APP_LOGS.info("ch3: "+ch3);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Camera");
		}
		minWait();
		//clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.soundrecorder/com.android.soundrecorder.SoundRecorder");
		APP_LOGS.info("sound recorder launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch4=true;
				APP_LOGS.info("ch4: "+ch4);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Sound Recorder");
		}
		minWait();
		//clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.google.android.deskclock/com.android.deskclock.DeskClock");
		APP_LOGS.info("Clock launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
				ch5=true;
				APP_LOGS.info("ch5: "+ch5);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:clock");
		}
		minWait();
		//clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.chrome/org.chromium.chrome.browser.firstrun.FirstRunActivity");
		APP_LOGS.info("Browser launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
				ch6=true;
				APP_LOGS.info("ch6: "+ch6);
			}
		}
		catch(NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:browser");
		}
		minWait();
		//clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
		APP_LOGS.info("Contact launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch7=true;
				APP_LOGS.info("ch7: "+ch7);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Contact App");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialerMainActivity");
		APP_LOGS.info("Dialer launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch8=true;
				APP_LOGS.info("ch8: "+ch8);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Dialer");
		}

		minWait();
		//clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.gallery3d/com.android.gallery3d.app.Gallery");
		APP_LOGS.info("Gallery launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch9=true;
				APP_LOGS.info("ch9: "+ch9);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Gallery");
		}
		minWait();
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		APP_LOGS.info("Settings launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch10=true;
				APP_LOGS.info("ch10: "+ch10);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Settings");
		}
		minWait();
		//clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.google.android.calculator/com.android.calculator2.Calculator");
		APP_LOGS.info("Calculator launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch11=true;
				APP_LOGS.info("ch11: "+ch11);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Calculator");
		}
		minWait();
		//clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.calendar/com.android.calendar.AllInOneActivity");
		APP_LOGS.info("Calender launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch12=true;
				APP_LOGS.info("ch12: "+ch12);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Calemder");
		}
		minWait();
		//clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.documentsui/com.android.documentsui.FilesActivity");
		APP_LOGS.info("Download launched successfully");
		customWait(3000);
		try {
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch13=true;
				APP_LOGS.info("ch13: "+ch13);
			}
		}
		catch (NoSuchElementException e) {
			e.printStackTrace();
			APP_LOGS.info("Element not Found Execption:Download");
		}
		
		if((ch1) && (ch2) && (ch3) && (ch4) && (ch5) && (ch6) && (ch7) && (ch8) && (ch10) && (ch9) && (ch11) && (ch12) && (ch13)) {
			check=true;
			APP_LOGS.info("All APPS launched successfully");
			SA7.assertTrue(check, "All PASS");
		}
		else {
			APP_LOGS.info("Values of All Apps are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch4+ "," +ch5+ "," +ch6+ "," +ch7+ "," +ch9+ "," +ch10+ "," +ch8+ "," +ch11+ "," +ch12+ "," +ch13);
			SA7.fail();
		}
		SA7.assertAll();
		minWait();
		//clearRecentApps();
	}
	
	
	public void Unselect_All_Apps_And_Check() throws InterruptedException, IOException {
//		clearRecentApps();
		try {
			customWait(2000);	
			if(special_case().equals("OFF")) {
				customWait(2000);
				turn_Off_On_SG();
				pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Un_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One_With_Out_Locked_Screen();
			}
			else {
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				pass_Locators(Locators_Safeguard.SG_Applications_String, Locators_Safeguard.SG_Un_Select_All_Btn, Locators_Safeguard.SG_Save_Btn);
				APP_LOGS.info("Launching All the Apps one by one and checking wheather SG locked screen is displayed or not");
				launching_All_Apps_One_By_One_With_Out_Locked_Screen();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}


	public String special_case() throws InterruptedException, IOException {
		//This method is used to check wheather activation is ON or OFF.
		
		minWait();
		String status = null;
		minWait();
		
//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
//		minWait();
//		minWait();
//		Locators_Safeguard.AppListIcon.click();
//		scroll();
//		minWait();
//		scroll();
//		minWait();
//		scroll();
//		minWait();
//		clickBtn(Locators_Safeguard.sonim_scout_AppLauncher);
//		minWait();
//		aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/editText\")").sendKeys("1234");
//		minWait();
//		aDriver.pressKeyCode(AndroidKeyCode.ENTER);
//		minWait();
//		APP_LOGS.info("Inside Safeguard Settings screen");
//		clickBtn(Locators_Safeguard.SG_SetUp_String);
//		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
//		clickBtn(Locators_Safeguard.SG_Safe_Guard_String);

		try {
			if(Locators_Safeguard.SG_toggle_btn.getText().equals("OFF")) {
				status=Locators_Safeguard.SG_toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for OFF" +status);
			}
			else if(Locators_Safeguard.SG_toggle_btn.getText().equals("ON")){
				status=Locators_Safeguard.SG_toggle_btn.getText();
				APP_LOGS.info("Checking Activation status for ON" +status);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public void clearAllow() throws InterruptedException {
		Thread.sleep(500);
		try {
			while(isElementExist(Locators_Safeguard.allow_Btn)) {
				clickBtn(Locators_Safeguard.allow_Btn);			
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}
	
	public void launching_All_Apps_One_By_One_With_Out_Locked_Screen() throws IOException, InterruptedException {

		boolean ch1=false;
		boolean ch2=false;
		boolean ch3=false;
		boolean ch4=false;
		boolean ch5=false;
		boolean ch6=false;
		boolean ch7=false;
		boolean ch8=false;
		boolean ch9=false;
		boolean ch10=false;
		boolean ch11=false;
		boolean ch12=false;
		boolean ch13=false;
		boolean ch14=false;
		boolean ch15=false;
		boolean ch16=false;
		boolean ch17=false;

		SoftAssert SA8=new SoftAssert();
		Runtime.getRuntime().exec("adb shell am start -n  com.caf.fmradio/com.caf.fmradio.FMRadio");
		APP_LOGS.info("FM launched successfully");
		customWait(2000);
		if(isElementExist(Locators_Safeguard.SG_FM)) {
			ch1=true;
			APP_LOGS.info("ch1: "+ch1);
		}
		minWait();
//		clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.mms/com.android.mms.ui.ConversationList");
		APP_LOGS.info("mms launched successfully");
		customWait(2000);
		customWait(2000);
		if(isElementExist(Locators_Safeguard.SG_Message)){
			ch2=true;
			APP_LOGS.info("ch2: "+ch2);
		}
		minWait();
//		clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n org.codeaurora.snapcam/com.android.camera.CameraActivity");
		APP_LOGS.info("Camera launched successfully");
		customWait(2000);
		if(isElementExist(Locators_Safeguard.SG_Camera)) {
			ch3=true;
			APP_LOGS.info("ch3: "+ch3);
		}
		minWait();
//		clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.soundrecorder/com.android.soundrecorder.SoundRecorder");
		APP_LOGS.info("sound recorder launched successfully");
		customWait(2000);
		if(isElementExist(Locators_Safeguard.SG_Sound_Recoder)){
			ch4=true;
			APP_LOGS.info("ch4: "+ch4);
		}
		minWait();
//		clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.sonimtech.setupwizard/com.sonimtech.setupwizard.SetupLauncherActivity");
		APP_LOGS.info("Setup Wizard launched successfully");
		customWait(2000);
		if(isElementExist(Locators_Safeguard.SG_SetUpwizard)) {
			ch5=true;
			APP_LOGS.info("ch5: "+ch5);
		}
		minWait();
//		clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
		APP_LOGS.info("Contact launched successfully");
		customWait(2000);
		if((isElementExist(Locators_Safeguard.SG_ADD_CONTACT_String) || isElementExist(Locators_Safeguard.SG_Find_Contacts))) {
			ch6=true;
			APP_LOGS.info("ch6: "+ch6);
		}
		minWait();
//		clearRecentApps();
		Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialtactsActivity");
		APP_LOGS.info("Dialer launched successfully"); 
		customWait(2000);
		if(isElementExist(Locators_Safeguard.SG_RecentCalls)) {
			ch7=true;
			APP_LOGS.info("ch7: "+ch7);
		}
	/*	minWait();
//		clearRecentApps();
		aDriver.startActivity("com.sonim.ble.connect", "com.sonim.ble.connect.BLEDiscoverActivity");
		APP_LOGS.info("BLE launched successfully");
		customWait(1000);
		clearAllow();
		if(isElementExist(Locators_Safeguard.SG_BLE_AllowScreen)) {
			clickBtn(Locators_Safeguard.SG_BLE_AllowScreen)	;
		}
		*/
		/*if(isElementExist(Locators_Safeguard.SG_BLE_Connect) || isElementExist(Locators_Safeguard.SG_Bluetooth_Error_Msg)) {
			ch8=true;
			APP_LOGS.info("ch8: "+ch8);
			customWait(1000);
			try {
				aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"android:id/button2\")").click();
			}
			catch (NoSuchElementException exception) {
				clearRecentApps();
			}
		}*/
		if((ch1)&& (ch2)  && (ch3) && (ch4) && (ch5) && (ch6) && (ch7) ) {
			check=true;
			APP_LOGS.info("All APPS launched successfully");
			SA8.assertTrue(check, "All PASS");
		}
		else {
			APP_LOGS.info("Values of All Apps are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch4+ "," +ch5+ "," +ch6+ "," +ch7);
			SA8.fail();
		}
		SA8.assertAll();
		minWait();
	
	}
	
	public void special_Case_Unselect() throws InterruptedException {
			try {
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				enter_Pin();
				customWait(1000);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Un_Select_All_Btn);
				minWait();
				minWait();
				clickBtn(Locators_Safeguard.SG_Save_Btn);
				minWait();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}	
	}

	
	public void select_few_And_Check() throws InterruptedException, IOException {
		try {
			if(sf_app_Check_Activation_Off_On().equals("OFF")) {
				turn_Off_On_SG();
				minWait();
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				enter_Pin();
				customWait(1000);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Select_All_Btn);
				minWait();
				for(int i=1;i<=2;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
				}
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait();
				//aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Save_Btn);
				minWait();
				APP_LOGS.info("Launching few apps and checking");
				launch_Few_Apps();
			}
			else {
				APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
				minWait();
				clickBtn(Locators_Safeguard.SG_Applications_String);
				minWait();
				enter_Pin();
				customWait(1000);
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Select_All_Btn);
				minWait();
				for(int i=1;i<=2;i++) {
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					customWait(1000);
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
				}
				minWait();
				clickBtn(Locators_Safeguard.SG_Save_Btn);
				minWait();
				APP_LOGS.info("Launching Few Apps");
				launch_Few_Apps();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public void launch_Few_Apps() throws IOException, InterruptedException {
		boolean ch1=false;
		boolean ch2=false;
		SoftAssert SA9=new SoftAssert();
//		clearRecentApps();
		try {
			Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialtactsActivity");
			APP_LOGS.info("Dailer launched successfully");
			minWait();
			if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
				ch1=true;
			}
			if(ch1) {
				check=true;
				SA9.assertTrue(check, "True");
			}
			else {
				SA9.fail();
			}
			SA9.assertAll();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	//This method is used to check the feature Tab present in the SG
	public void check_Features_Tab() throws InterruptedException, IOException {

			//clearRecentApps();
			try {
				minWait();
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					minWait();
					clickBtn(Locators_Safeguard.SG_Features_String);
					minWait();
					enter_Pin();
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Select_All_Btn);
					minWait();
					//aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Save_Btn);
					minWait();
					APP_LOGS.info("Launching All features one by one");
					launchFeatures();
				}
				else {
					APP_LOGS.info("Checking the features When Activastion is ON ");
					minWait();
					clickBtn(Locators_Safeguard.SG_Features_String);
					minWait();
					enter_Pin();
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);

					clickBtn(Locators_Safeguard.SG_Select_All_Btn);
					customWait(2000);
//				aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Save_Btn);
					minWait();
					APP_LOGS.info("Launching All the Features");
					launchFeatures();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		
		
		public void launchFeatures() throws IOException, InterruptedException {

			boolean ch1=false;
			boolean ch2=false;
			boolean ch3=false;
			boolean ch4=false;
			boolean ch5=false;
			boolean ch6=false;
			boolean ch7=false;
			boolean ch8=false;

			SoftAssert SA=new SoftAssert();


			try {
				//clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				minWait();
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))");  
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_Bluetooth);
				minWait();
				clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
				customWait(2000);

				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
					ch1=true;
					APP_LOGS.info("Bluetooth "+ch1);
				}


//				clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialtactsActivity");
				customWait(3000);
				clickBtn(Locators_Safeguard.SG_Menu_Contact_String);
//				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(2000);
				//Do horizontal scrolling
				clickBtn(Locators_Safeguard.SG_Settings_dailer);
	//			for(int i=1;i<=8;i++) {
	//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
	//			}
	//			customWait(2000);

				clickBtn(Locators_Safeguard.SG_Call_Screening);
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_Call_Blocking);
				customWait(1000);

				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
					ch2=true;
					APP_LOGS.info("Call Block "+ch2);
				}

//				clearRecentApps();
				customWait(2000);

				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Location\"))");

				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Location);
				minWait();
				clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
					ch3=true;
					APP_LOGS.info("Location"+ch3);
				}
//				clearRecentApps();
				Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
				customWait(2000);

				if(isElementExist(Locators_Safeguard.SG_Contact_In_Search_Box)) {
					customWait(2000);
					
					clickBtn(Locators_Safeguard.SG_FirstEntry_Contact_Btn);
					for(int i=1; i<=4; i++) {
						if(isElementExist(Locators_Safeguard.allow_Btn)) {
							clickBtn(Locators_Safeguard.allow_Btn);
						}
					}
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Contact_Edit_Icon);
					customWait(2000);
					if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)){
						ch4=true;
						APP_LOGS.info("Modify Contact"+ch4);
					}
					if((ch1) && (ch2) && (ch3) && (ch4)) {
						check=true;
						//softAssert_true(check,"Message");
						SA.assertTrue(check, "");
					}
					else {
						APP_LOGS.info("The values for Features are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch4);
						//softAssert_false();
						SA.fail();
					}
				}
				//This part is used for scrolling tof
				minWait();
//				clearRecentApps();
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Backup & reset\"))");
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Factory_reset);

				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

					check5=true;
					APP_LOGS.info("TC Passed FActory Reset" +check5);
					//  softAssert_true(check, "Messages");
					SA.assertTrue(check, "");  
				}
				else {
					APP_LOGS.info("Test is for factory Reset");
					//  softAssert_false();
					SA.fail();
				}

				//This part is used for scrolling tof
//				clearRecentApps();  
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Security\"))");
				clickBtn(Locators_Safeguard.SG_Security);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					check6=true;
					APP_LOGS.info("TC Passed Security" +check6);
					SA.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("Test is for Security");
					SA.fail();
				}
//				clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"More\"))");
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_More);
				customWait(2000);
				try {
					clickBtn(Locators_Safeguard.SG_Teathering);
				}
				catch(NoSuchElementException exception) {
					customWait(2000);
				}
				catch( StaleElementReferenceException exception) {
					customWait(2000);
				}
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {

					check7=true;
					APP_LOGS.info("TC Passed" +check7);
					APP_LOGS.info("TC Passed Hotspot" +check7);
					SA.assertTrue(check, "");
				}
				else{
					APP_LOGS.info("Test is for factory Reset");
					SA.fail();
				}
				//checking USb restricction
//				clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -c android.intent.category.HOME -a android.intent.action.MAIN");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.HOME);
				minWait();
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
				minWait();
				aDriver.openNotifications();
				minWait();
				clickBtn(Locators_Safeguard.SG_Notification_USB);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
					check8=true;
					APP_LOGS.info("TC Passed USB" +check8);
					//softAssert_true(check, "Messages");
					SA.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("Test is for factory Reset");
					//softAssert_false();
					SA.fail();
				}
				//This is for WIFI 
				//clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
				//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"WLAN\"))");
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation) || isElementExist(Locators_Safeguard.SG_Safe_Guard_Locked_Screen_Edit_Box)) {
					check=true;
					APP_LOGS.info("TC Passed" +check);
					//  softAssert_true(check, "Messages");
					SA.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("Test is for factory Reset");
					// softAssert_false();
					SA.fail();
				}
				SA.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}


	
		
		//This method will unselect all features and save
		public void Unselect_All_Features_Options_check() throws IOException, InterruptedException {
			boolean ch1=false;
			boolean ch2=false;
			boolean ch3=false;
			boolean ch4=false;
			boolean ch5=false;
			boolean ch6=false;
			boolean ch7=false;
			boolean ch8=false;
			boolean ch9=false;
			SoftAssert SA11=new SoftAssert();
//			clearRecentApps();
			
			try {
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				minWait();
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Bluetooth\"))");
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_Bluetooth);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Bluetooth)){
					ch1=true;
					APP_LOGS.info("ch1 "+ch1);
					APP_LOGS.info("Bluetooth  screen displayed with out locked screen "+ch1);
				}
//			clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialtactsActivity");
				customWait(3000);
				clickBtn(Locators_Safeguard.SG_Menu_Contact_String);
				customWait(2000);
				//Do horizontal scrolling
				clickBtn(Locators_Safeguard.SG_Settings_dailer);
//			for(int i=1;i<=8;i++) {
//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
//			}
//			customWait(2000);
				clickBtn(Locators_Safeguard.SG_Call_Screening);
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_Call_Blocking);
				customWait(1000);
				if(isElementExist(Locators_Safeguard.SG_Blocked_Number)){
					ch2=true;
					APP_LOGS.info("ch2 "+ch2);
					APP_LOGS.info("Block Num "+ch2);
				}
//			clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Location\"))");
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Location);
				minWait();
				
				clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Location_Off_On_Btn)){
					ch3=true;
					APP_LOGS.info("ch3 "+ch3);
					APP_LOGS.info("Location ch3"+ch3);
				}
//			clearRecentApps();
				customWait(3000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
				customWait(3000);
//			if(isElementExist(Locators_Safeguard.SG_Contact_In_Search_Box)) {
//				customWait(2000);
////				for(int i=1; i<=2; i++) {
//					if(isElementExist(Locators_Safeguard.)) {
//				
//					}
//				}
					clickBtn(Locators_Safeguard.SG_FirstEntry_Contact_Btn);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Contact_Edit_Icon);
					customWait(2000);
					if(isElementExist(Locators_Safeguard.SG_Contact_Edit_Text)){
						ch4=true;
						APP_LOGS.info("ch4 "+ch4);
						APP_LOGS.info("Modify Contact"+ch4);
					}
				
				//Now Executing else part
				minWait();
//			clearRecentApps();
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Backup & reset\"))");
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Factory_reset);
					if(isElementExist(Locators_Safeguard.SG_Factory_reset)){
						ch5=true;
						APP_LOGS.info("ch5 "+ch5);
						APP_LOGS.info("Factory Reset "+ch5);
					}
					Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
					customWait(2000);
					aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Security\"))");
					clickBtn(Locators_Safeguard.SG_Security);
					customWait(2000);
					if(isElementExist(Locators_Safeguard.SG_Security)){
						ch6=true;
						APP_LOGS.info("ch6 "+ch6);
						APP_LOGS.info("Securityr "+ch6);
					}
					customWait(2000);
					Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
					customWait(2000);
					aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"More\"))");
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_More);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Teathering);
//				try {
//					clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
//				}
//				catch(NoSuchElementException exception) {
////					clearRecentApps();
//					customWait(2000);
//				}
//				catch( StaleElementReferenceException exception) {
////					clearRecentApps();
//					customWait(2000);
//				}
					if(isElementExist(Locators_Safeguard.SG_Teathering)){
						ch7=true;
						APP_LOGS.info("ch7 "+ch7);
						APP_LOGS.info("Hotspot"+ch7);
					}
					
					customWait(2000);
					Runtime.getRuntime().exec("adb shell am start -c android.intent.category.HOME -a android.intent.action.MAIN");
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.HOME);
					minWait();
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
					minWait();
					aDriver.openNotifications();
					minWait();
					clickBtn(Locators_Safeguard.SG_Notification_USB);
					customWait(2000);
					if(isElementExist(Locators_Safeguard.SG_Use_USB)) {
						ch8=true;
						APP_LOGS.info("USB ch8 " +ch8);
					}
					else {
						APP_LOGS.info("Test is for factory Reset");
					}
					//This is for WIFI 
//				clearRecentApps();
					customWait(2000);
					Runtime.getRuntime().exec("adb shell am start -a android.intent.action.MAIN -n com.android.settings/.wifi.WifiSettings");
					//aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"WLAN\"))");
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
					customWait(2000);
					
					if(isElementExist(Locators_Safeguard.SG_WLAN_Validation)) {
						ch9=true;
						APP_LOGS.info(" ch9" +ch9);
					}
					else {
						APP_LOGS.info("Test is for factory Reset");
					}
					
//				boolean returnresult=subApps();
					if((ch1) && (ch2) && (ch3) && (ch5) && (ch6) && (ch7) && (ch8) && (ch9) ) {
						check=true;
						SA11.assertTrue(check, "Safeguard Pin is unselected");
					}
					else {
						APP_LOGS.info("else part for unchecking all apps ");
						APP_LOGS.info("The Values with out checking the Apps are :" +ch1+ "," +ch2+ "," +ch3+ "," +ch5+ "," +ch6+ "," +ch7+ "," +ch8+ "," +ch9);
						SA11.fail();
					}
					SA11.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}	
		}
		
		//This part is used for scrolling tof
		public boolean subApps() throws IOException, InterruptedException {	

			boolean chk1=false;
			boolean chk2=false;
			boolean chk3=false;
			boolean chk4=false;
			boolean chk5=false;
			boolean result=false;
//			clearRecentApps();
			try {
				customWait(1000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Factory reset\"))");
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Factory_reset);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Factory_reset)) {
					chk1=true;
				}
				//This part is used for scrolling tof
				minWait();
//			clearRecentApps();  
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"Security\"))");
				clickBtn(Locators_Safeguard.SG_Security);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Security)) {
					chk2=true;
				}
//			clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"More\"))");
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_More);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Teathering);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_Teathering)) {
					chk3=true;
				}
				//checking usb restricction
//			clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -c android.intent.category.HOME -a android.intent.action.MAIN");
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.MENU);
				customWait(3000);
				clickBtn(Locators_Safeguard.SG_MyNotification);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.ENTER);
				customWait(3000);
				if(isElementExist(Locators_Safeguard.SG_Use_USB_to)) {
					chk4=true;
					APP_LOGS.info("TC Passed" +check);
				}
				//This is for WIFI 
//			clearRecentApps();
				customWait(2000);
				Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
				customWait(2000);
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/main_content\")).scrollIntoView(new UiSelector().text(\"WLAN\"))");
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_WIFI);
				customWait(2000);
				clickBtn(Locators_Safeguard.SG_Location_Off_On_Btn);
				customWait(2000);
				if(isElementExist(Locators_Safeguard.SG_WIFI)) {
					chk5=true;
				}
				if((chk1) && (chk2) && (chk3) && (chk4) && (chk5)) {
					result=true;
				}
				else {
					APP_LOGS.info("SubApp values are :"+chk1+ "," +chk2+ "," +chk3+ "," +chk4+ "," +chk5 );
					result=false;
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
			return result;
		}
		//Here it ends the Method 
				
		//This method is used for changing the Pin
		public void change_Pin(String newPin, String OldPin) throws InterruptedException, IOException {
			APP_LOGS.info("Chaning the PIN TC ");
			SoftAssert SA19=new SoftAssert();
			
			try {
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					minWait();
					clickBtn(Locators_Safeguard.SG_Change_Pin_String);
					minWait();
					enterTextToInputField(Locators_Safeguard.SG_Old_Pin, OldPin);
					minWait();
					enterTextToInputField(Locators_Safeguard.SG_New_Pin, newPin);
					minWait();
					enterTextToInputField(Locators_Safeguard.SG_Reenter_Pin, newPin);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
					minWait();
					clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
					minWait();
					clickBtn(Locators_Safeguard.SG_toggle_btn);
					minWait();
					enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, newPin);
					APP_LOGS.info(" Changed Pin entered successfully");
					clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
					minWait();
					if(isElementExist(Locators_Safeguard.SG_Activation_Text)) {
						check=true;
						SA19.assertTrue(check, "");
					}
					else {
						SA19.fail();
					}
				}
				else{
					APP_LOGS.info("Checking for the Pin change when activation is in On state ");
					minWait();
					clickBtn(Locators_Safeguard.SG_Change_Pin_String);
					minWait();
					enterTextToInputField(Locators_Safeguard.SG_Old_Pin, OldPin);
					minWait();
					enterTextToInputField(Locators_Safeguard.SG_New_Pin, newPin);
					minWait();
					enterTextToInputField(Locators_Safeguard.SG_Reenter_Pin, newPin);
					minWait();
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
					minWait();
//				clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
					minWait();
					clickBtn(Locators_Safeguard.SG_toggle_btn);
					minWait();
					enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, newPin);
					APP_LOGS.info(" Changed Pin entered successfully");
					clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
					minWait();
					if(isElementExist(Locators_Safeguard.SG_Activation_Text)) {
						check=true;
						SA19.assertTrue(check, "");
					}
					else {
						SA19.fail();
					}
				}
				SA19.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		//End of the Method
		
		//Enable Activation toggle
		public void changePinAfter() throws InterruptedException {
			try {
				APP_LOGS.info("Changing the PIN After TC ");
				clickBtn(Locators_Safeguard.SG_toggle_btn);
				customWait(2000);
				minWait();
				aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").sendKeys("4545");
				APP_LOGS.info("PIN has been entered successfully");
				minWait();
				if(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled()) {
					customWait(1000);
					clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		//This Method is used to originate emergency call on top of the locked screen
		public void emergency_Call() throws InterruptedException, IOException {

			SoftAssert SA10=new SoftAssert();
//			clearRecentApps();
			try {
				minWait();
				APP_LOGS.info("=====Emergency call==========");
				Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialtactsActivity");
				minWait();
				aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/emergencybutton\")").click();
				customWait(1000);
				aDriver.findElementByAndroidUIAutomator("UiSelector().className(\"android.widget.EditText\")").sendKeys("101");
				customWait(1000);
				aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"android:id/button1\")").click();
				minWait();
				if(isElementExist(Locators_Safeguard.SG_Emergency_No)) {
					check=true;
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
					SA10.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("TC failed");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
					SA10.fail();
				}
				SA10.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}		
		
		//This Method will unselect all features and check
		public void  Unselect_All_Features_And_Check() throws IOException, InterruptedException {
			
			try {
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					minWait();
					clickBtn(Locators_Safeguard.SG_Features_String);
					minWait();
					enter_Pin();
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Un_Select_All_Btn);
					minWait();
//				aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Save_Btn);
					minWait();
					APP_LOGS.info("Launching All features one by one and checking that locked screen should not be present");
					Unselect_All_Features_Options_check();
				}
				else {
					APP_LOGS.info("Checking the features When Activastion is ON ");
					minWait();
					clickBtn(Locators_Safeguard.SG_Features_String);
					minWait();
					enter_Pin();
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Un_Select_All_Btn);
					customWait(2000);
//				aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Save_Btn);
					minWait();
					APP_LOGS.info("Launching All the Features");
					Unselect_All_Features_Options_check();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		
		//This Method is used for Application Pin timeout
		public void application_Pin_30Sec_TimeOut() throws InterruptedException, IOException{
			APP_LOGS.info("-----------------------------------------");
			APP_LOGS.info("Application Pin Time Out");
			try {
				SoftAssert SA13=new SoftAssert();
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_String);
					customWait(1000);
					APP_LOGS.info("Clicked on 30 Seconds in Applciaiton Time out");
					clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_30_Sec);
//				customWait(30000);
//				Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialtactsActivity");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
					minWait();
					clickBtn(Locators_Safeguard.SG_Phone_Icon);
					customWait(2000);
					customWait(50000);
					APP_LOGS.info("Dailer launched successfully");
					if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
						check=true;
						SA13.assertTrue(check, "locked screen");
					}
					else {
						SA13.fail();
					}
				}
				else {
					clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_String);
					minWait();
					APP_LOGS.info("Clicked on 30 Seconds in Applciaiton Time out");
					clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_30_Sec);
					customWait(10000);
					System.out.println("Waiting for 30 seconds...");
					customWait(10000);
//				clickBtn(Locators_Safeguard.SG_Navigate_Up);
					Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialtactsActivity");
					minWait();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
					minWait();
					clickBtn(Locators_Safeguard.SG_Phone_Icon);
					customWait(2000);
//				Runtime.getRuntime().exec("adb shell am start -n com.android.dialer/com.android.dialer.DialtactsActivity");
					APP_LOGS.info("Dailer launched successfully");
					customWait(2000);
					if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
						check=true;
						SA13.assertTrue(check, "locked screen");
					}
					else {
						SA13.fail();
					}
				}
				SA13.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		//End of the Method
		
		
		public void special_Case_Select() throws InterruptedException, IOException {
			minWait();
//			clearRecentApps();
			try {
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					minWait();
					clickBtn(Locators_Safeguard.SG_Applications_String);
					minWait();
					enter_Pin();
					customWait(2000);
					//Here select two apps
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Select_All_Btn);
					customWait(2000);
//				aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Save_Btn);
					minWait();
				}
				else {
					APP_LOGS.info("Else Part : Activation is already ON and checking all the Apps one by one");
					minWait();
					clickBtn(Locators_Safeguard.SG_Applications_String);
					minWait();
					enter_Pin();
					customWait(2000);
					aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Select_All_Btn);
					customWait(2000);
//				aDriver.pressKeyCode(AndroidKeyCode.MENU);
					customWait(2000);
					clickBtn(Locators_Safeguard.SG_Save_Btn);
					minWait();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		
		
		//This Method is used to  check the application time out functionality whcih is set to 1 minute .

		public void pin_Time_Out_Set_To_1_Min() throws InterruptedException, IOException {

			APP_LOGS.info("-------TC for PinTime out Set to 1 minutes--------------------------");
			SoftAssert SA15=new SoftAssert();
//			clearRecentApps();
			try {
				customWait(1000);
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					customWait(1000);
					clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_String);
					customWait(1000);
					clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_1_Min);
					customWait(50000);
					Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
					System.out.println("1 Mintime out is working fine");
					System.out.println("Now launching browser.......");
//				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
//				minWait();
//				clickBtn(Locators_Safeguard.SG_Phone_Icon);
					customWait(2000);
					if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
						check=true;
						APP_LOGS.info("------------Locked screen exist---------");
						SA15.assertTrue(check, "");
					}
					else {
						APP_LOGS.info("----------Locked screen does not exist----"+check);
						SA15.fail();
					}
				}
				else {
					APP_LOGS.info("--------Activation is ON and executing else part ----------------");
					clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_String);
					customWait(1000);
					clickBtn(Locators_Safeguard.SG_App_Pin_TimeOut_1_Min);
					customWait(50000);
					 Runtime.getRuntime().exec("adb shell am start -n com.android.contacts/com.android.contacts.activities.PeopleActivity");
					System.out.println("1 Mintime out is working fine");
					//  customWait(30000);
					System.out.println("launching Contacts App ....");
//				Runtime.getRuntime().exec("adb shell am start -n com.android.browser/com.android.browser.BrowserActivity");
//				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
//				minWait();
//				clickBtn(Locators_Safeguard.SG_Phone_Icon);
					customWait(2000);
					if(isElementExist(Locators_Safeguard.SG_Locked_Screen_Validation)) {
						check=true;
						APP_LOGS.info("------------Locked screen exist---------");
						SA15.assertTrue(check, "");
					}
					else {
						APP_LOGS.info("----------Locked screen does not exist----"+check);
						SA15.fail();
					}
				}
				SA15.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		//This Ends the Method 

		
		
		//THis method is used for min pin code verification
		public void min_Code_Verification() throws InterruptedException, IOException {
//			clearRecentApps();
			minWait();
			boolean ch1=false;
			boolean ch2=false;
			boolean ch3=false;
			boolean ch4=false;
			SoftAssert SA16=new SoftAssert();
			APP_LOGS.info("--------------Min Pin Code Verification-----------");
//			navigateToSafeGuard();
			try {
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_toggle_btn);
				minWait();
				enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "1");
				APP_LOGS.info("Entering a single digit number in pin field");
				customWait(1000);
				if(!(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled())) {
					ch1=true;
					APP_LOGS.info("Ok button is disabled");
				}
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_toggle_btn);
				customWait(1000);
				enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "12");
				APP_LOGS.info("Entering a single digit number in pin field");
				customWait(1000);
				if(!(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled())) {
					ch2=true;
					APP_LOGS.info("Ok button is disabled");
				}
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_toggle_btn);
				customWait(1000);
				enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "123");
				APP_LOGS.info("Entering a single digit number in pin field");
				customWait(1000);
				if(!(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled())) {
					ch3=true;
					APP_LOGS.info("Ok button is disabled");
				}
				aDriver.pressKeyCode(AndroidKeyCode.BACK);
				customWait(1000);
				clickBtn(Locators_Safeguard.SG_toggle_btn);
				customWait(1000);
				enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "1234");
				customWait(1000);
				if(Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled()) {
					ch4=true;
					APP_LOGS.info("Ok button is Enabled");
				}
				if((ch1) && (ch2) && (ch3) && (ch4)) {
					check=true;
					APP_LOGS.info("TC passed");
					SA16.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("Checking for values:" +ch1+ "," +ch2+ "," +ch3+ "," +ch4);
					SA16.fail();
				}
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		//End of the Method

		
		//This method is used to fetch max entered pin
		public void max_Limit() throws InterruptedException, IOException {
			try {
				SoftAssert SA17=new SoftAssert();
//			clearRecentApps();
				minWait();
//			navigateToSafeGuard();
//			customWait(1000);
				clickBtn(Locators_Safeguard.SG_toggle_btn);
				customWait(1000);
				aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").sendKeys("123456789");
				customWait(1000);
				String fetch_pin=aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.sonim.safeguard:id/dialog_edit_text\")").getText();
				if(fetch_pin.length()==8) {
					check=true;
					APP_LOGS.info("Pin Length is :"+fetch_pin.length());
					SA17.assertTrue(check, "");
				}
				else {
					APP_LOGS.info("Pin Length is :"+fetch_pin.length());
					SA17.fail();
				}
				SA17.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}	
		}
		//End of the Method 
		
		
		//This Method is used to check for Invalid Pin
		public void Check_Invalid_Pin() throws InterruptedException, IOException {
//			clearRecentApps();
			APP_LOGS.info("--------------------------------------------------------------");
			APP_LOGS.info("Invalid Pincheck TC");
			SoftAssert SA20=new SoftAssert();

			try {
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					minWait();
					clickBtn(Locators_Safeguard.SG_Applications_String);
					minWait();
					//Here trying to enter wrong Pin
					enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "2222");
					minWait();
					clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
					minWait();
					if(isElementExist(Locators_Safeguard.SG_Input_PIN)) {
						check=true;
						APP_LOGS.info("Invalid Pin verification test Case Passed");
						SA20.assertTrue(check, "");
					}
					else {
						SA20.fail();
					}
				}
				else {
					clickBtn(Locators_Safeguard.SG_Applications_String);
					minWait();
					//Here trying to enter wrong Pin
					enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "2222");
					minWait();
					clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
					minWait();
					if(isElementExist(Locators_Safeguard.SG_Input_PIN)) {
						check=true;
						APP_LOGS.info("Invalid Pin verification test Case Passed");
						SA20.assertTrue(check, "");
					}
					else {
						SA20.fail();
					}
				}
				SA20.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}

		//End of Method

		
		
		//This method is used to check the wheather a Pin edit box accepts only number or not
		public void Pin_Acccepts_Only_Num() throws InterruptedException, IOException {
			APP_LOGS.info("-----------------------------------------------------------");
			APP_LOGS.info("TC for entering alphabets iin Pin edit box");
			SoftAssert SA21=new SoftAssert();
			try {
				if(sf_app_Check_Activation_Off_On().equals("OFF")) {
					turn_Off_On_SG();
					minWait();
					clickBtn(Locators_Safeguard.SG_Applications_String);
					minWait();
					//Here trying to enter alphnumeric number Pin
					enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "abcd");
					minWait();
					if(!Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled()) {
						clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
						minWait();
						if(isElementExist(Locators_Safeguard.SG_Input_PIN)) {
							check=true;
							APP_LOGS.info("Invalid Pin verification test Case Passed");
							SA21.assertTrue(check, "");
						}
						else {
							SA21.fail();
						}
					}
					else {
						APP_LOGS.info("Pin Ok button is not enabled");
						SA21.fail();
					}
				}
				else {
					clickBtn(Locators_Safeguard.SG_Applications_String);
					minWait();
					//Here trying to enter wrong Pin
					enterTextToInputField(Locators_Safeguard.SG_Enter_Pin, "abcd");
					minWait();
					if(!Locators_Safeguard.SG_OK_Btn_for_Pin.isEnabled()) {
						clickBtn(Locators_Safeguard.SG_OK_Btn_for_Pin);
						minWait();
						if(isElementExist(Locators_Safeguard.SG_Input_PIN)) {
							check=true;
							APP_LOGS.info("Invalid Pin verification test Case Passed");
							SA21.assertTrue(check, "");
						}
						else {
							SA21.fail();
						}
					}
					else {
						APP_LOGS.info("Pin Ok button is not enabled");
						SA21.fail();
					}
				}
				SA21.assertAll();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			}
		}
		//End of Method	

}
