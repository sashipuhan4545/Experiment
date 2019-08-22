package com.xp8.util;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_SonimCare_Util extends BaseUtil{

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

	public void launch_application(WebElement app) throws InterruptedException{
		try {
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait_SonimCare();
			Locators_SonimCare.AppListIcon.click();
			minWait_SonimCare();
			scroll();
			minWait_SonimCare();
			scroll();
			minWait_SonimCare();
			clickBtn(app);
			APP_LOGS.info("Application launched succesfully");
			minWait_SonimCare();
			clickBtn(Locators_SonimCare.supportTab);
			APP_LOGS.info("Clicked on Support tab succesfully");
			minWait_SonimCare();
		} catch (NoSuchElementException e) {
			APP_LOGS.info("Error: Application not found.");
			e.printStackTrace();
		}

	}

	public void validate_Current_Screen(WebElement sonimCareTitle, String titleText) throws InterruptedException{

		/*
		 * Validates the current screen.
		 * Pass the element to be validated as parameter.
		 * 
		 */
		minWait_SonimCare();
		clickBtn(Locators_SonimCare.careApp);
		minWait_SonimCare();
		//String getTitleText = sonimCareTitle.getText();
		try {
			if(isElementExist(Locators_SonimCare.sonimCareTitle)){
				check = true;
				String getTitleText = sonimCareTitle.getText();
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


	public void launchSonimCare() throws InterruptedException, IOException{

		/*
		 * Launches scout application : SonimCare
		 */		
//		executeShellCommands(2,null,"com.sonim.sonimcustomercare","com.sonim.sonimcustomercare.MainActivity");
		Runtime.getRuntime().exec("cmd /c start /min cmd.exe /K \"adb shell am start -n com.sonim.sonimcustomercare/com.sonim.sonimcustomercare.MainActivity");
		customWait(5000);
		APP_LOGS.info("Launched SonimCare application");
	}

	public void logout() throws InterruptedException {
		longpress(4);
		minWait();
	}

	public void stopAdb() throws InterruptedException, IOException {
		try {
			customWait(4000);
			Runtime.getRuntime().exec("taskkill /IM cmd.exe");
			APP_LOGS.info("Adb logs stopped succesfully. ");
			customWait(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void select_subApplication_sonimCare(WebElement feature) throws InterruptedException{

		/*
		 * Launches feature of sonimcare application
		 * Pass the webelement of feature as parameter
		 */
		try {
			minWait_SonimCare();
			clickBtn(feature);
			minWait_SonimCare();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}


	public void navigateTo_TestAll_screen() throws InterruptedException, IOException{
		/*
		 * Navigates to Test all screen of Diagnosis sub application.
		 */
		try {
			minWait_SonimCare();
			clickBtn(Locators_SonimCare.continueBtn_welcomeScreen_subApp);
			customWait(3000);
			if(isElementExist(Locators_SonimCare.AllowOptn)) {
				Locators_SonimCare.AllowOptn.click();
				APP_LOGS.info("Allow button clicked succesfully. ");
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait_SonimCare();
				clickBtn(Locators_SonimCare.selfDiagnosisTest);
				minWait_SonimCare();
				clickBtn(Locators_SonimCare.continueBtn_welcomeScreen_subApp);
				minWait_SonimCare();
			}
			clickBtn(Locators_SonimCare.continueBtn_selfDiagnosis);
			minWait_SonimCare();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void navigateTo_Individual_feature(String featureName) throws InterruptedException{
		/*
		 * Navigates to any feature in Test all screen.
		 * Pass name of the feature as parameter
		 */
		
		try {
			minWait_SonimCare();
			if(featureName.equalsIgnoreCase("WI-FI")) {
				clickBtn(Locators_SonimCare.WiFiOptn);	
				minWait_SonimCare();
				clickBtn(Locators_SonimCare.AllowOptn);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("Test all"))	{
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}
			else if(featureName.equalsIgnoreCase("BLUETOOTH"))	{
				clickBtn(Locators_SonimCare.BuletoothOptn);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("CLEAR CACHE MEMORY"))	{
				minWait_SonimCare();
				scroll();
				minWait_SonimCare();
				aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.sonim.sonimcustomercare:id/gridItemView\")).scrollIntoView(new UiSelector().text(\"CLEAR CACHE MEMORY\"))");
				minWait_SonimCare();
				clickBtn(Locators_SonimCare.ClearCacheMemoryOptn);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("BACK CAMERA"))  {
				clickBtn(Locators_SonimCare.BckCameraOptn);
				//			minWait_SonimCare();
				clickBtn(Locators_SonimCare.AllowOptn);	
			}
			else if(featureName.equalsIgnoreCase("FLASHLIGHT"))	{
				clickBtn(Locators_SonimCare.flashLightOptn);
				minWait_SonimCare();

			}
			else if(featureName.equalsIgnoreCase("DISPLAY")) {
				minWait_SonimCare();
				clickBtn(Locators_SonimCare.dispalyOptn);
			}
			else if(featureName.equalsIgnoreCase("Keypad")) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("BATTERY"))	{
				clickBtn(Locators_SonimCare.BatteryOptn);
				minWait_SonimCare();

			}
			else if(featureName.equalsIgnoreCase("VIBRATION"))	{
				for(int i=0;i<4;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("EARPIECE"))	{
				for(int i=0;i<5;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();

			}
			else if(featureName.equalsIgnoreCase("HEADSET"))	{
				for(int i=0;i<5;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("SPEAKER"))	{
				for(int i=0;i<6;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("ACCELERATOR SENSOR"))		{
				for(int i=0;i<6;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();

			}
			else if(featureName.equalsIgnoreCase("GPS"))	{
				for(int i=0;i<7;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("NOTIFICATION LED"))	{
				for(int i=0;i<7;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("CLEAR CACHE MEMORY"))	{
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				clickBtn(Locators_SonimCare.ClearCacheMemoryOptn);
				minWait_SonimCare();
			}
			else if(featureName.equalsIgnoreCase("STORAGE INFO"))	{
				for(int i=0;i<8;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}



	public void click_On_coninueBtn_featureScreen() throws InterruptedException{
		/*
		 * Clicks on continue button displayed in each feature screen.
		 */
		try {
			minWait_SonimCare();
			clickBtn(Locators_SonimCare.continueBtnFeatureScreen);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public void validate_wifiScan() throws InterruptedException{
		/*
		 * 
		 * Scans available wifi and performs wifi test.
		 * validates wifi scan
		 */
	
		try {
			minWait_SonimCare();
			clickBtn(Locators_SonimCare.scanForMoreNetworksBtn);
			minWait();
			minWait();
			customWait(4000);
			
			if(isElementExist(Locators_SonimCare.ScrollView)) {

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_LEFT);
					minWait_SonimCare();
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();		
			if(isElementExist(Locators_SonimCare.selfDiagnosisTitle)){
				check=true;
				APP_LOGS.info("Wifi Scan Results displayed succesfully");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Not navigated to test all screen");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}



	public void validate_backCamera(String textToVerify) throws InterruptedException{
		/*
		 * takes a picture and saves.
		 * Validates picture taken.
		 */
		try {		
			customWait(1000);
			if(isElementExist(Locators_SonimCare.AllowOptn)) {
				customWait(1000);
				clickBtn(Locators_SonimCare.AllowOptn);
			}
			
			for(int i=0; i>=3; i++) {
				if(isElementExist(Locators_SonimCare.ScrollView)) {
					clickBtn(Locators_SonimCare.AllowOptn);
				}		
			}	
			if(isElementExist(Locators_SonimCare.AllowOptn)) {
			customWait(1000);
			clickBtn(Locators_SonimCare.AllowOptn);
			}
			
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(4000);
			clickBtn(Locators_SonimCare.cameraIcon);
			minWait_SonimCare();
			customWait(4000);
			customWait(4000);
			customWait(4000);
			clickBtn(Locators_SonimCare.OK_btn_resultConfirmation_dialog);
			APP_LOGS.info("clicked succesfully.");
			customWait(4000);
			if(isElementExist(Locators_SonimCare.cameraCOnfirmation_text)){
				check=true;
				APP_LOGS.info("Test result confirmation message is displayed succesfully.");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Test result confirmation message is not displayed.");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}			
	}


	public void validate_BatteryTest(String bat_Status, String bat_status_value_1, String bat_status_value_2, String bat_Electricity, String bat_Electricity_value ) throws InterruptedException{
		/*
		 * Validates battery test.
		 * Pass battery status and battery electricity as parameters.
		 */
		try {
			minWait_SonimCare();
			String get_bat_status = Locators_SonimCare.batteryStatusLabel.getText();
			System.out.println(get_bat_status);
			if(get_bat_status.equalsIgnoreCase(bat_Status)){
				check1=true;
				APP_LOGS.info("Label:"+get_bat_status);
			}
			String get_bat_value = Locators_SonimCare.batteryStatusValue.getText();
			System.out.println(get_bat_value);
			if(get_bat_value.equalsIgnoreCase(bat_status_value_1)||get_bat_value.equalsIgnoreCase(bat_status_value_2)){
				check2=true;
				APP_LOGS.info("Value:"+get_bat_value);
			}		
			String get_bat_Electricity = Locators_SonimCare.batteryElectricityLabel.getText();
			System.out.println(get_bat_Electricity);
			if(get_bat_Electricity.equalsIgnoreCase(bat_Electricity)){
				check3=true;
				APP_LOGS.info("Label:"+get_bat_Electricity);
			}
			String get_bat_Electricity_value = Locators_SonimCare.batteryElectricityValue.getText();
			System.out.println(get_bat_Electricity_value);
			if(get_bat_Electricity_value.equalsIgnoreCase(bat_Electricity_value)){

				check4=true;
				APP_LOGS.info("Value:"+get_bat_Electricity_value);
			}
			if((check1)&&(check2)&&(check3)&&(check4)){
				check= true;
				APP_LOGS.info("Static parameters are displayed properly.");
				APP_LOGS.info("TC is Passed.");
				clickBtn(Locators_SonimCare.YES_btn_wifiScanResult);
				//selectYESButton();
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info(get_bat_status+": "+check1+"\n"+get_bat_value+": "+check2+"\n"
						+get_bat_Electricity+": "+check3+"\n"+get_bat_Electricity_value+": "+check4);
				clickBtn(Locators_SonimCare.YES_btn_wifiScanResult);
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}
	
	public void validate_ClearCacheMemory() throws InterruptedException{
		/*
		 * validates cache memory feature.
		 */
		try {
			customWait(6000);
			scroll();
			customWait(2000);
			clickBtn(Locators_SonimCare.CacheDataOptn);
			minWait_SonimCare();
			clickBtn(Locators_SonimCare.Ok_Btn_Confirm);
			minWait_SonimCare();
			customWait(4000);
			minWait_SonimCare();		
			String cachedDataValue = Locators_SonimCare.cachedMemory_0.getText();
			if(cachedDataValue.equalsIgnoreCase("0")){
				check=true;
				APP_LOGS.info("Cached memory is cleared succesfully.");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Cached memory is not cleared succesfully.");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}


	public void validate_bluetooth_scan() throws InterruptedException{
		/*
		 * Validates Bluetooth scan feature.
		 * Pass the confirmation message as parameter.
		 */
		customWait(6000);
		try {
			customWait(6000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();		
			if(isElementExist(Locators_SonimCare.selfDiagnosisTitle)){
				check=true;
				APP_LOGS.info("Navigated back to Test All Screen succesfully");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Not navigated to test all screen");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {			
			e.printStackTrace();
		}
	}

	public void click_On_coninueBtn_Bat_Scan() throws InterruptedException{
		/*
		 * Clicks on continue button displayed in each feature screen.
		 */
		try {
			minWait_SonimCare();
			clickBtn(Locators_SonimCare.continueBtn_Bat_scan);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}

	public void validate_batteryDiagnosis() throws InterruptedException{
		/*
		 * Validates battery diagnosis sub application screen.
		 * Validates against all static headings of the screen.
		 */
		try {
			customWait(4000);
			String getBatInfo = Locators_SonimCare.battery_Info.getText();
			if(isElementExist(Locators_SonimCare.battery_Info)){
				check1 = true;
				APP_LOGS.info("Element found: "+getBatInfo);
			}else{
				APP_LOGS.info("Bat info Element not found");
			}
			String getBatHealth = Locators_SonimCare.battery_health.getText();
			if(isElementExist(Locators_SonimCare.battery_health)){
				check2 = true;
				APP_LOGS.info("Element found: "+getBatHealth);
			}else{
				APP_LOGS.info("Bat health element is not found");
			}
			String getBatVoltage= Locators_SonimCare.battery_voltage.getText();
			if(isElementExist(Locators_SonimCare.battery_voltage)){
				check3 = true;
				APP_LOGS.info("Element found: "+getBatVoltage);
			}else{
				APP_LOGS.info("Bat voltage element is not found");
			}
			String getBatTemp = Locators_SonimCare.battery_temperature.getText();
			if(isElementExist(Locators_SonimCare.battery_temperature)){
				check4= true;
				APP_LOGS.info("Element found: "+getBatTemp);
			}else{
				APP_LOGS.info("Bat temperature element is not found");
			}
			if(isElementExist(Locators_SonimCare.battery_history_Layout)){
				check5 = true;
				APP_LOGS.info("Battery history layout element is found");
			}else{
				APP_LOGS.info("Battery history layout element is not found");
			}
			String getBatHistory = Locators_SonimCare.battery_service_History.getText();
			if(isElementExist(Locators_SonimCare.battery_service_History)){
				check6 = true;
				APP_LOGS.info("Battery service history element is found: "+ getBatHistory);
			}else{
				APP_LOGS.info("Battery service hostory element is not found");
			}
			String getBatReport = Locators_SonimCare.battery_view_detailedReport.getText();
			if(isElementExist(Locators_SonimCare.battery_view_detailedReport)){
				check7=true;
				APP_LOGS.info("Battery detailed report element is found: "+getBatReport);
			}else{
				APP_LOGS.info("Battery detailed report element is not found");
			}
			String getBatDiagnosisTitle = Locators_SonimCare.battery_diagnosis_title.getText();
			if(isElementExist(Locators_SonimCare.battery_diagnosis_title)){
				check8=true;
				APP_LOGS.info("Battery detailed report element is found: "+getBatDiagnosisTitle);
			}else{
				APP_LOGS.info("Battery detailed report element is not found");
			}

			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)&&(check8)){
				check= true;
				APP_LOGS.info("All static elements are located succesfully.");
				APP_LOGS.info("Test case is passed");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Test case is failed");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void validate_battery_service_history() throws InterruptedException{
		/*
		 * validates battery service history.
		 * Records static and dynamic values.
		*/
		try {
			customWait(4000);
			clickBtn(Locators_SonimCare.battery_service_History);
			minWait_SonimCare();		
			String getBat_Year_month_Info = Locators_SonimCare.battery_Month_Year_view.getText();
			if(isElementExist(Locators_SonimCare.battery_Month_Year_view)){
				check1 = true;
				APP_LOGS.info("Element found: "+getBat_Year_month_Info);
			}else{
				APP_LOGS.info("Bat year month info Element not found");
			}
			String get_total_no_charges = Locators_SonimCare.battery_Total_No_Charges.getText();
			if(isElementExist(Locators_SonimCare.battery_Total_No_Charges)){
				check2 = true;
				APP_LOGS.info("Element found: "+get_total_no_charges);
			}else{
				APP_LOGS.info("Bat total number of charges element is not found");
			}
			String get_total_no_charges_value= Locators_SonimCare.battery_Total_No_Charges_value.getText();
			if(isElementExist(Locators_SonimCare.battery_Total_No_Charges_value)){
				check3 = true;
				APP_LOGS.info("Element found: "+get_total_no_charges_value);			
			}else{
				APP_LOGS.info("Bat total no of charges value element is not found");
			}
			String get_max_temp_recorded = Locators_SonimCare.battery_Max_temp_recorded.getText();
			if(isElementExist(Locators_SonimCare.battery_Max_temp_recorded)){
				check4= true;
				APP_LOGS.info("Element found: "+get_max_temp_recorded);
			}else{
				APP_LOGS.info("Max battery temperature element is not found");
			}
			String get_max_temp_recorded_value = Locators_SonimCare.battery_Max_temp_recorded_value.getText();
			if(isElementExist(Locators_SonimCare.battery_Max_temp_recorded_value)){
				check5 = true;
				APP_LOGS.info("element is found"+get_max_temp_recorded_value);
			}else{
				APP_LOGS.info("bat_max_temp element is not found");
			}
			String getTotalChargeDuration = Locators_SonimCare.battery_total_charge_duration.getText();
			if(isElementExist(Locators_SonimCare.battery_total_charge_duration)){
				check6 = true;
				APP_LOGS.info("Element is found: "+ getTotalChargeDuration);
			}else{
				APP_LOGS.info("TotalCharge Duration element is not found");
			}
			String getTotalChargeDuration_Value = Locators_SonimCare.battery_total_charge_duration_value.getText();
			if(isElementExist(Locators_SonimCare.battery_total_charge_duration_value)){
				check7=true;
				APP_LOGS.info("Element is found: "+getTotalChargeDuration_Value);
			}else{
				APP_LOGS.info("TotalCharge duration value element is not found");
			}
			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)){
				check=true;
				APP_LOGS.info("All dynamic parameters are displayed");
				APP_LOGS.info("Test case is passed");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Test case failed");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
		
	public void validate_Youtube_Navigation() throws InterruptedException{
		/*
		 * Validates sonimcare to youtube page navigation.
		 */
		try {
			customWait(10000);
			if((isElementExist(Locators_SonimCare.youtube_Page))||(isElementExist(Locators_SonimCare.youtube_Page_))){
				check=true;
				APP_LOGS.info("Navigated to youtube page succesfully.");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Failed to Navigate to youtube page.");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void selectNOButton(String featurename) throws InterruptedException{
		/*
		 * clicks on NO button to exit from the scan.
		 * pass feature name as parameter.
		*/
		try {
			minWait_SonimCare();
			if(featurename.equalsIgnoreCase("wi-fi")) {
				customWait(4000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}else if(featurename.equalsIgnoreCase("bluetooth")) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}else if(featurename.equalsIgnoreCase("back camera")) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
			}else if(featurename.equalsIgnoreCase("flash light")) {	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}else if(featurename.equalsIgnoreCase("display")) {
				for(int i=1;i<7;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
					minWait_SonimCare();}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}else if(featurename.equalsIgnoreCase("battery")) {
				for(int i=1;i<8;i++){
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
					minWait_SonimCare();
				}
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void validate_No_btn_feature(String featureName) throws InterruptedException{
		try {
			customWait(5000);
			customWait(5000);
			selectNOButton(featureName);
			customWait(5000);
			if(isElementExist(Locators_SonimCare.alertText)){
				check=true;
				APP_LOGS.info("Confirmation alert displayed succesfully");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Not navigated to test all screen");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void turnOn_flash_light() throws InterruptedException{
		try {
			customWait(2000);
			clickBtn(Locators_SonimCare.flashswitchIcon);
			minWait_SonimCare();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void capture_image() throws InterruptedException{
		try {
			customWait(4000);
			customWait(4000);
			customWait(4000);
			clickBtn(Locators_SonimCare.cameraIcon);
			minWait_SonimCare();
			customWait(4000);
			customWait(4000);
			customWait(4000);
			clickBtn(Locators_SonimCare.OK_btn_resultConfirmation_dialog);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	public void pressRightKey(){
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
	}
	
	public void pressCenterKey(){
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
	}
	
	public void pressBackKey(){
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
	}
	
	public void pressDownKey(){
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
	}
	
	
	
	public void validate_wifi_links(String featureName ) throws InterruptedException{
		/*
		 * Validates all the hyper links and alerts of wifi feature page.
		*/
		boolean check1_a = false;
		boolean check2_a = false;
		boolean check3_a = false;
		boolean check4_a = false;
		boolean check5_a = false;
		boolean check6_a = false;
		boolean check7_a = false;
		boolean check8_a = false;
		boolean check9_a = false;
		
		try {
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.wifi_State_link))
			{
				check1 = true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("wifi_State_link Element is present");
				clickBtn(Locators_SonimCare.wifi_State_link);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check1_a =true;
					APP_LOGS.info("check1_a: "+check1_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check1_a: "+check1_a);
					APP_LOGS.info("Wifi_state_link_Alert is not present");
				}
			}else	{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("wifi_State_link Element is not present");			
			}	
			pressDownKey();
			minWait_SonimCare();
			pressDownKey();
			minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check2_a =true;
					APP_LOGS.info("check2_a: "+check2_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check2_a: "+check2_a);
					APP_LOGS.info("Connected to link Alert is not present");
				}		
			pressDownKey();
			minWait_SonimCare();			
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check3_a =true;
					APP_LOGS.info("check3_a: "+check3_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check3_a: "+check3_a);
					APP_LOGS.info("status_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
				if(isElementExist(Locators_SonimCare.alert))	{
					check4_a =true;
					APP_LOGS.info("check4_a: "+check4_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check4_a: "+check4_a);
					APP_LOGS.info("RSSI_link Alert is not present");
			}		
			pressDownKey();
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();
				if(isElementExist(Locators_SonimCare.alert)) {
					check5_a =true;
					APP_LOGS.info("check5_a: "+check5_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else {
					APP_LOGS.info("check5_a: "+check5_a);
					APP_LOGS.info("bssID_link Alert is not present");
				}			
			pressDownKey();
			minWait_SonimCare();		
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check6_a =true;
					APP_LOGS.info("check6_a: "+check6_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check6_a: "+check6_a);
					APP_LOGS.info("ssID_link Alert is not present");
		
			pressDownKey();
			minWait_SonimCare();
		
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alertText))
				{
					check7_a =true;
					APP_LOGS.info("check7_a: "+check7_a);
					String getAlertText=Locators_SonimCare.alertText.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check7_a: "+check7_a);
					APP_LOGS.info("ip_address_link Alert is not present");
				}
		
			pressDownKey();
			minWait_SonimCare();
		
			//minWait_SonimCare();		
				
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check9_a =true;
					APP_LOGS.info("check9_a: "+check9_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check9_a: "+check9_a);
					APP_LOGS.info("link_speed_link Alert is not present");
				}
		
			if((check1_a)&&(check2_a)&&(check3_a)&&(check4_a)&&(check5_a)&&(check6_a)&&(check7_a)
					&&(check9_a)){
				check=true;
				APP_LOGS.info("All hyperlinks and alerts of wifi feature screen is displayed");			
				pressDownKey();
				minWait_SonimCare();
			
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Test case failed");			
				pressDownKey();
				minWait_SonimCare();
				
				Assert.fail();
			}
		}
		}catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}
	
	
	public void validate_battery_links(String featureName) throws InterruptedException{
		/*
		 * Validates hyperlinks and alerts of battery feature screen.
		*/
		boolean check1_a = false;
		boolean check2_a = false;
		boolean check3_a = false;
		boolean check4_a = false;
		boolean check5_a = false;
		boolean check6_a = false;
		boolean check7_a = false;
		
		try {
			pressDownKey();
			minWait_SonimCare();
//			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check1_a =true;
					APP_LOGS.info("check1_a: "+check1_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check1_a: "+check1_a);
					APP_LOGS.info("battery_level_link is not present");
				}
		
			pressDownKey();
			minWait_SonimCare();
			pressDownKey();
		
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check2_a =true;
					APP_LOGS.info("check2_a: "+check2_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check2_a: "+check2_a);
					APP_LOGS.info("battery_health_link Alert is not present");
				}
		
				pressDownKey();
		
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check3_a =true;
					APP_LOGS.info("check3_a: "+check3_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check3_a: "+check3_a);
					APP_LOGS.info("battery_status_link Alert is not present");
				}
	
				pressDownKey();
				minWait_SonimCare();
		
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check5_a =true;
					APP_LOGS.info("check5_a: "+check5_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check5_a: "+check5_a);
					APP_LOGS.info("battery_temperature_link Alert is not present");
				}
				pressDownKey();
				minWait_SonimCare();
		
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check6_a =true;
					APP_LOGS.info("check6_a: "+check6_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check6_a: "+check6_a);
					APP_LOGS.info("battery_voltage_link Alert is not present");
				}
			
				pressDownKey();
				minWait_SonimCare();
			
				minWait_SonimCare();	
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				if(isElementExist(Locators_SonimCare.alert))
				{
					check7_a =true;
					APP_LOGS.info("check7_a: "+check7_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check7_a: "+check7_a);
					APP_LOGS.info("battery_scale_link Alert is not present");
				}
		
			
			if((check1_a)&&(check2_a)&&(check3_a)&&(check5_a)&&(check6_a)&&(check7_a)){
				check=true;
				APP_LOGS.info("All hyperlinks and alerts of wifi feature screen is displayed");			
				selectNOButton(featureName);
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Test case failed");			
				selectNOButton(featureName);			
				Assert.fail();
			}
			
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}
		
	public void validate_hyperTexts_alert_selfDiagnosisPage() throws InterruptedException{
		/*
		 * Validates hyperlinks and alerts of self Diagnosis screen.
		 */
		boolean check1_a = false;
		boolean check2_a = false;
		boolean check3_a = false;
		boolean check4_a = false;
		boolean check5_a = false;
		boolean check6_a = false;
		boolean check7_a = false;
		boolean check8_a = false;
		boolean check9_a = false;
		boolean check10_a= false;
		try {	
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.model_link))
			{
				check1 = true;
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("model_link Element is present");
				clickBtn(Locators_SonimCare.model_link);
				minWait_SonimCare();			
				if(isElementExist(Locators_SonimCare.alert))
				{
					check1_a =true;
					APP_LOGS.info("check1_a: "+check1_a);
					String getAlertText=Locators_SonimCare.alert.getText();
					APP_LOGS.info("Alert heading is: "+getAlertText);
					pressBackKey();
					minWait_SonimCare();
				}else{
					APP_LOGS.info("check1_a: "+check1_a);
					APP_LOGS.info("model_link is not present");
				}
			}else
			{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("model_link Element is not present");			
			}	
			pressDownKey();
			customWait(2000);

			pressCenterKey();
			minWait_SonimCare();

			if(isElementExist(Locators_SonimCare.alert))
			{
				check2 = true;
				check2_a =true;
				APP_LOGS.info("check2_a: "+check2_a);
				String getAlertText=Locators_SonimCare.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check2_a: "+check2_a);
				APP_LOGS.info("imei_link to link Alert is not present");
			}
			pressDownKey();
			minWait_SonimCare();

			pressCenterKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.alert))
			{
				check3 = true;
				check3_a =true;
				APP_LOGS.info("check3_a: "+check3_a);
				String getAlertText=Locators_SonimCare.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check3_a: "+check3_a);
				APP_LOGS.info("androidVersion_link Alert is not present");
			}

			pressDownKey();
			minWait_SonimCare();

			pressCenterKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.alert))
			{
				check4 = true;
				check4_a =true;
				APP_LOGS.info("check4_a: "+check4_a);
				String getAlertText=Locators_SonimCare.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check4_a: "+check4_a);
				APP_LOGS.info("build_link Alert is not present");
			}

			pressDownKey();
			minWait_SonimCare();

			pressCenterKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.alert))
			{
				check5 = true;
				check5_a =true;
				APP_LOGS.info("check5_a: "+check5_a);
				String getAlertText=Locators_SonimCare.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check5_a: "+check5_a);
				APP_LOGS.info("phoneNumber_link Alert is not present");
			}

			pressDownKey();
			minWait_SonimCare();

			pressCenterKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.alert))
			{
				check6 = true;
				check6_a =true;
				APP_LOGS.info("check6_a: "+check6_a);
				String getAlertText=Locators_SonimCare.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check6_a: "+check6_a);
				APP_LOGS.info("network_link Alert is not present");
			}

			pressDownKey();
			minWait_SonimCare();

			pressCenterKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.alert))
			{
				check7 = true;
				check7_a =true;
				APP_LOGS.info("check7_a: "+check7_a);
				String getAlertText=Locators_SonimCare.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check7_a: "+check7_a);
				APP_LOGS.info("network_type_link Alert is not present");
			}

			pressDownKey();
			minWait_SonimCare();

			pressCenterKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.alert))
			{
				check8 = true;
				check8_a =true;
				APP_LOGS.info("check8_a: "+check8_a);
				String getAlertText=Locators_SonimCare.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check8_a: "+check8_a);
				APP_LOGS.info("signal_strength_link Alert is not present");
			}
			pressDownKey();
			minWait_SonimCare();
			pressCenterKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.alert))
			{
				check9 = true;
				check9_a =true;
				APP_LOGS.info("check9_a: "+check9_a);
				String getAlertText=Locators_SonimCare.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check9_a: "+check9_a);
				APP_LOGS.info("on_roaming_link Alert is not present");
			}

			pressDownKey();
			minWait_SonimCare();

			pressCenterKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.alert))
			{
				check10 = true;
				check10_a =true;
				APP_LOGS.info("check10_a: "+check10_a);
				String getAlertText=Locators_SonimCare.alert.getText();
				APP_LOGS.info("Alert heading is: "+getAlertText);
				pressBackKey();
				minWait_SonimCare();
			}else{
				APP_LOGS.info("check10_a: "+check10_a);
				APP_LOGS.info("data_link Alert is not present");
			}

			if((check1)&&(check1_a)&&(check2)&&(check2_a)&&(check3)&&(check3_a)&&(check4)&&(check4_a)&&(check5)&&(check5_a)&&(check6)&&(check6_a)&&(check7)&&(check7_a)
					&&(check8)&(check8_a)&&(check9)&(check9_a)&&(check10)&&(check10_a)){
				check=true;
				APP_LOGS.info("All hyperlinks and alerts of self diagnosis screen is displayed successfully.");			
				pressBackKey();
				minWait_SonimCare();
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Test case failed");			
				pressBackKey();
				minWait_SonimCare();
				Assert.fail();

			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}	
	
	public void validate_Device_Info_Self_Diagnosis_screen() throws InterruptedException{
		/*
		 * Validates all the valuese(texts) of device information in self diagnosis screen.
		*/
		try {
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.model_value_text))	{
				check1 = true;
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("model_value_text Element is present");
				String getAlertText = Locators_SonimCare.model_value_text.getText();
				APP_LOGS.info("model_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("model_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.imei_value_text))	{
				check2 = true;
				APP_LOGS.info("check2: " + check2);
				APP_LOGS.info("imei_value_text Element is present");
				String getAlertText = Locators_SonimCare.imei_value_text.getText();
				APP_LOGS.info("imei_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("model_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.android_version_value_text))	{
				check3 = true;
				APP_LOGS.info("check3: " + check3);
				APP_LOGS.info("android_version_value_text Element is present");
				String getAlertText = Locators_SonimCare.android_version_value_text.getText();
				APP_LOGS.info("android_version_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("android_version_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.build_value_text))		{
				check4 = true;
				APP_LOGS.info("check4: " + check4);
				APP_LOGS.info("build_value_text Element is present");
				String getAlertText = Locators_SonimCare.build_value_text.getText();
				APP_LOGS.info("build_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check4: "+check4);
				APP_LOGS.info("build_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.phone_number_value_text))	{
				check5 = true;
				APP_LOGS.info("check5: " + check5);
				APP_LOGS.info("phone_number_value_text Element is present");
				String getAlertText = Locators_SonimCare.phone_number_value_text.getText();
				APP_LOGS.info("phone_number_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check5: "+check5);
				APP_LOGS.info("phone_number_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.network_type_value_text))	{
				check6 = true;
				APP_LOGS.info("check6: " + check6);
				APP_LOGS.info("network_type_value_text Element is present");
				String getAlertText = Locators_SonimCare.network_type_value_text.getText();
				APP_LOGS.info("network_type_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check6: "+check6);
				APP_LOGS.info("network_type_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.signal_strength_value_text)) {
				check7 = true;
				APP_LOGS.info("check7: " + check7);
				APP_LOGS.info("signal_strength_value_text Element is present");
				String getAlertText = Locators_SonimCare.signal_strength_value_text.getText();
				APP_LOGS.info("signal_strength_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check7: "+check7);
				APP_LOGS.info("signal_strength_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.on_roaming_value_text)) {
				check8 = true;
				APP_LOGS.info("check8: " + check8);
				APP_LOGS.info("on_roaming_value_text Element is present");
				String getAlertText = Locators_SonimCare.on_roaming_value_text.getText();
				APP_LOGS.info("on_roaming_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check8: "+check8);
				APP_LOGS.info("on_roaming_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.data_value_text))	{
				check9 = true;
				APP_LOGS.info("check9: " + check9);
				APP_LOGS.info("data_value_text Element is present");
				String getAlertText = Locators_SonimCare.data_value_text.getText();
				APP_LOGS.info("data_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check9: "+check9);
				APP_LOGS.info("data_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.network_value_text))	{
				check10 = true;
				APP_LOGS.info("check10: " + check10);
				APP_LOGS.info("network_value_text Element is present");
				String getAlertText = Locators_SonimCare.network_value_text.getText();
				APP_LOGS.info("network_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check10: "+check10);
				APP_LOGS.info("network_value_text Element is not present");			
			}
			
			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)&&(check8)&&(check9)&&(check10)){
				check=true;
				APP_LOGS.info("All static device info of self diagnosis screen is displayed successfully.");			
				pressBackKey();
				minWait_SonimCare();
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Test case failed");			
				pressBackKey();
				minWait_SonimCare();
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}			
	}
	
	
	public void validate_wifi_info_wifi_feature_screen(String featureName) throws InterruptedException{
		/*
		 * Validates wifi info texts of wifi test result screen of wifi feature screen.
		*/
		try {
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.wifi_state_value_text)) {
				check1 = true;
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("wifi_state_value_text Element is present");
				String getAlertText = Locators_SonimCare.wifi_state_value_text.getText();
				APP_LOGS.info("wifi_state_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("wifi_state_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.connected_to_value_text))	{
				check2 = true;
				APP_LOGS.info("check2: " + check2);
				APP_LOGS.info("connected_to_value_text Element is present");
				String getAlertText = Locators_SonimCare.connected_to_value_text.getText();
				APP_LOGS.info("connected_to_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("connected_to_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.status_value_text)) {
				check3 = true;
				APP_LOGS.info("check3: " + check3);
				APP_LOGS.info("status_value_text Element is present");
				String getAlertText = Locators_SonimCare.status_value_text.getText();
				APP_LOGS.info("status_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("status_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.rssi_value_text))	{
				check4 = true;
				APP_LOGS.info("check4: " + check4);
				APP_LOGS.info("rssi_value_text Element is present");
				String getAlertText = Locators_SonimCare.rssi_value_text.getText();
				APP_LOGS.info("rssi_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check4: "+check4);
				APP_LOGS.info("rssi_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.bssid_value_text))	{
				check5 = true;
				APP_LOGS.info("check5: " + check5);
				APP_LOGS.info("bssid_value_text Element is present");
				String getAlertText = Locators_SonimCare.bssid_value_text.getText();
				APP_LOGS.info("bssid_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check5: "+check5);
				APP_LOGS.info("bssid_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.ssid_value_text))	{
				check6 = true;
				APP_LOGS.info("check6: " + check6);
				APP_LOGS.info("ssid_value_text Element is present");
				String getAlertText = Locators_SonimCare.ssid_value_text.getText();
				APP_LOGS.info("ssid_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check6: "+check6);
				APP_LOGS.info("ssid_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.ip_address_value_text)) {
				check7 = true;
				APP_LOGS.info("check7: " + check7);
				APP_LOGS.info("ip_address_value_text Element is present");
				String getAlertText = Locators_SonimCare.ip_address_value_text.getText();
				APP_LOGS.info("ip_address_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check7: "+check7);
				APP_LOGS.info("ip_address_value_text Element is not present");			
			}			
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.link_speed_value_text)) {
				check9 = true;
				APP_LOGS.info("check9: " + check9);
				APP_LOGS.info("link_speed_value_text Element is present");
				String getAlertText = Locators_SonimCare.link_speed_value_text.getText();
				APP_LOGS.info("link_speed_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check9: "+check9);
				APP_LOGS.info("link_speed_value_text Element is not present");			
			}
			
			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)&&(check9)) {
				check=true;
				APP_LOGS.info("All static device info of wifi test result screen is displayed successfully.");			
				pressDownKey();
				minWait_SonimCare();
				clickBtn(Locators_SonimCare.scanForMoreNetworksBtn);
				customWait(5000);
				selectNOButton(featureName);
				Assert.assertTrue(check);
			}else {
				APP_LOGS.info("Test case failed");			
				pressDownKey();
				minWait_SonimCare();
				clickBtn(Locators_SonimCare.scanForMoreNetworksBtn);
				customWait(5000);
				selectNOButton(featureName);
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}			
	}
	
	public void validate_battery_info_battery_feature_screen() throws InterruptedException{

		/*
		 * Validates battery info texts of battery test result screen of battery feature screen.
		*/
		try {
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.battery_level_value_text)) 	{
				check1 = true;
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("battery_level_value_text Element is present");
				String getAlertText = Locators_SonimCare.battery_level_value_text.getText();
				APP_LOGS.info("battery_level_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("battery_level_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.battery_health_value_text)) {
				check2 = true;
				APP_LOGS.info("check2: " + check2);
				APP_LOGS.info("battery_health_value_text Element is present");
				String getAlertText = Locators_SonimCare.battery_health_value_text.getText();
				APP_LOGS.info("battery_health_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check2: "+check2);
				APP_LOGS.info("battery_health_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.battery_status_value_text))	{
				check3 = true;
				APP_LOGS.info("check3: " + check3);
				APP_LOGS.info("battery_status_value_text Element is present");
				String getAlertText = Locators_SonimCare.battery_status_value_text.getText();
				APP_LOGS.info("battery_status_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check3: "+check3);
				APP_LOGS.info("battery_status_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.battery_scale_value_text))	{
				check4 = true;
				APP_LOGS.info("check4: " + check4);
				APP_LOGS.info("battery_scale_value_text Element is present");
				String getAlertText = Locators_SonimCare.battery_scale_value_text.getText();
				APP_LOGS.info("battery_scale_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check4: "+check4);
				APP_LOGS.info("battery_scale_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.battery_electricity_value_text))	{
				check5 = true;
				APP_LOGS.info("check5: " + check5);
				APP_LOGS.info("battery_electricity_value_text Element is present");
				String getAlertText = Locators_SonimCare.battery_electricity_value_text.getText();
				APP_LOGS.info("battery_electricity_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check5: "+check5);
				APP_LOGS.info("battery_electricity_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.battery_temperature_value_text))	{
				check6 = true;
				APP_LOGS.info("check6: " + check6);
				APP_LOGS.info("battery_temperature_value_text Element is present");
				String getAlertText = Locators_SonimCare.battery_temperature_value_text.getText();
				APP_LOGS.info("battery_temperature_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check6: "+check6);
				APP_LOGS.info("battery_temperature_value_text Element is not present");			
			}
			pressDownKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.battery_voltage_value_text))	{
				check7 = true;
				APP_LOGS.info("check7: " + check7);
				APP_LOGS.info("battery_voltage_value_text Element is present");
				String getAlertText = Locators_SonimCare.battery_voltage_value_text.getText();
				APP_LOGS.info("battery_voltage_value_text: " + getAlertText);
			}else	{
				APP_LOGS.info("check7: "+check7);
				APP_LOGS.info("battery_voltage_value_text Element is not present");			
			}
			if((check1)&&(check2)&&(check3)&&(check4)&&(check5)&&(check6)&&(check7)) {
				check=true;
				APP_LOGS.info("All static info of battery test result screen is displayed successfully.");			
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				Assert.assertTrue(check);
			}else {
				APP_LOGS.info("Test case failed");			
				pressDownKey();
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_RIGHT);
				minWait_SonimCare();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}	
	
	public void validate_diagnosis_report_dialog() throws InterruptedException, IOException{
		/*
		 * validates the strings of self diagnosis report dialog.
		*/
		
		try {
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
			pressBackKey();
			minWait_SonimCare();
			if(isElementExist(Locators_SonimCare.alert)) {
				check1 = true;
				APP_LOGS.info("check1: " + check1);
				APP_LOGS.info("Self diagnosis alert Element is present");
				String getAlertText = Locators_SonimCare.alert.getText();
				APP_LOGS.info("Self diagnosis alert: " + getAlertText);
				if(isElementExist(Locators_SonimCare.alertText)) {
					check2= true;
					APP_LOGS.info("check2: " + check2);
					APP_LOGS.info("Self diagnosis alert message Element is present");
					String getMsgText = Locators_SonimCare.alertText.getText();
					APP_LOGS.info("Self diagnosis alert message: " + getMsgText);
				}else {
					APP_LOGS.info("check2: "+check2);
					APP_LOGS.info("Self diagnosis alert message Element is not present");	
				}
			}else	{
				APP_LOGS.info("check1: "+check1);
				APP_LOGS.info("Self diagnosis alert Element is not present");			
			}
			if((check1)&&(check2)) {
				APP_LOGS.info("All static strings are displayed");
				check=true;
				pressBackKey();
				Assert.assertTrue(check);				
			}else {
				APP_LOGS.info("Test case failed");
				pressBackKey();
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	
	
	public void validate_diagnosis_report_delivery(String TCName) throws InterruptedException, IOException{
		/*
		 * validates successfully delivery of diagnosis report.
		*/
		try {
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			customWait(9000);
			Runtime.getRuntime().exec("taskkill /IM cmd.exe");
			customWait(4000);
			if (searchString("SendDiagnosticReport(), HTTP Response code is: Found: 302", TCName)) {
				APP_LOGS.info("Failed to deliver self diagnosis report.");
				Assert.fail();				
			}else{
				APP_LOGS.info("Self diagnosis report is delivered succesfully.");
				check=true;
				Assert.assertTrue(check);				
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}		
	}	
	
	public void validate_sonimScout_client_presence() throws InterruptedException{
	
		try {
			minWait_SonimCare();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait_SonimCare();
			Locators_SonimCare.AppListIcon.click();
			minWait_SonimCare();
			scroll();
			minWait_SonimCare();
			scroll();
			if(isElementExist(Locators_SonimCare.sonim_scout_AppLauncher)) {
				check=true;
				APP_LOGS.info("Sonim scout app launcher is present in main menu");
				Assert.assertTrue(check);
			}else {
				APP_LOGS.info("Sonim scout app launcher is missing in main menu");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
}
