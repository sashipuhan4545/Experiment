package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_SonimCare {
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_SonimCare(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss SonimCare.']")
	 public static WebElement DismissIcon;
	
	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss Sonim Scout.']")
	 public static WebElement DismissIconCare;
	
	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static WebElement AppListIcon;
	
	@AndroidFindBy(xpath ="//android.widget.Button[@text='ACCEPT']")
	 public static WebElement AcceptBtn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='CLEAR ALL']"))
	public static WebElement ClearAll;

	@AndroidFindBy(xpath ="//android.widget.TextView[@content-desc='Sonim Scout']")
	public static WebElement sonim_scout_AppLauncher;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Support']")
	 public static WebElement supportTab;
	
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimCare']")
	 public static WebElement careApp;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/toolbar_title']")
	 public static WebElement sonimCareTitle;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Self-diagnosis Test']")
	 public static WebElement selfDiagnosisTest;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/contineView']")
	 public static WebElement continueBtn_welcomeScreen_subApp;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/headsetViewContinue']")
	 public static WebElement continueBtn_selfDiagnosis;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	 public static WebElement AllowOptn;
	 
	 @AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='com.android.packageinstaller:id/do_not_ask_checkbox']")
	 public static WebElement checkboxDntAskAgn;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/continueTV']")
	 public static WebElement continueBtnFeatureScreen;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='WI-FI']")
	 public static WebElement WiFiOptn;
	 
	
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/startButton']")
	 public static WebElement scanForMoreNetworksBtn;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Self Diagnosis']")
	 public static WebElement selfDiagnosisTitle;
	 
	 @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ScrollView\")")
	 public static WebElement ScrollView;
	 
	 @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\")")
	 public static WebElement AllowBtnCamera;
	 
	 @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resource-id(\"com.sonim.sonimcustomercare:id/btn_pass\").text(\"YES\")")
	 public static WebElement WifiScanResultsPassBtn;
	 
//	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/btn_pass']")
//	 public static WebElement WifiScanResultsPassBtn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='0']/android.widget.Button[@index='5']"))
	 public static WebElement FailBtn1;
	 
	 @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resource-id(\"com.sonim.sonimcustomercare:id/btn_fail\").text(\"NO\")")
	 public static WebElement FailBtn;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/camera_Conformation_view']")
	 public static WebElement cameraCOnfirmation_text;
	 
	 @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/shutter_button']")
	 public static WebElement cameraIcon;
	 
	 @AndroidFindBy(xpath="//android.widget.Switch[@resource-id='com.sonim.sonimcustomercare:id/switch1']")
	 public static WebElement flashswitchIcon;
	 
	 @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/preview_btn_done']")
	 public static WebElement OK_btn_resultConfirmation_dialog;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
	 public static WebElement Ok_Btn_Confirm;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='BACK CAMERA']")
	 public static WebElement BckCameraOptn;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='FLASHLIGHT']")
	 public static WebElement flashLightOptn;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryLevelLabel']")
	 public static WebElement batteryStatusLabel;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheStatusView']")
	 public static WebElement batteryStatusValue;
		
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryElectricityLabel']")
	 public static WebElement batteryElectricityLabel;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheElectricityView']")
	 public static WebElement batteryElectricityValue;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/btn_pass']")
	 public static WebElement YES_btn_wifiScanResult;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/btn_fail']")
	 public static WebElement NO_btn_wifiScanResult;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='BATTERY']")
	 public static WebElement BatteryOptn;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='DISPLAY']")
	 public static WebElement dispalyOptn;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='0']")
	 public static WebElement cachedMemory_0;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='CLEAR CACHE MEMORY']")
	 public static WebElement ClearCacheMemoryOptn;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cached data']")
	 public static WebElement CacheDataOptn;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='BLUETOOTH']")
	 public static WebElement BuletoothOptn;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/continue_btn']")
	 public static WebElement continueBtn_Bat_scan;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Battery Diagnosis']")
	 public static WebElement batteryDiagnosis;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryHeading']")
	 public static WebElement battery_Info;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryhealthLabel']")
	 public static WebElement battery_health;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryvoltageLabel']")
	 public static WebElement battery_voltage;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batterytempLabel']")
	 public static WebElement battery_temperature;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/line1']")
	 public static WebElement battery_service_History;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/line2']")
	 public static WebElement battery_view_detailedReport;
	 
	 @AndroidFindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.sonim.sonimcustomercare:id/historylayout']")
	 public static WebElement battery_history_Layout;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Battery Diagnosis']")
	 public static WebElement battery_diagnosis_title;
	 
	 @AndroidFindBy(xpath="//android.widget.ProgressBar[@resource-id='com.sonim.sonimcustomercare:id/progressBar']")
	 public static WebElement battery_progress_Bar;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batterylevelview']")
	 public static WebElement battery_progress_Indicator;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryhealthview']")
	 public static WebElement battery_health_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryvoltageview']")
	 public static WebElement battery_voltage_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batterytempview']")
	 public static WebElement battery_temperature_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/calendar_month_year_textview']")
	 public static WebElement battery_Month_Year_view;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Wifi Scan Results']")
	 public static WebElement wifiScanResults;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Total no.of Charges']")
	 public static WebElement battery_Total_No_Charges;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Max temp recorded']")
	 public static WebElement battery_Max_temp_recorded;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Total Charge Duration']")
	 public static WebElement battery_total_charge_duration;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/chargecycletextview']")
	 public static WebElement battery_Total_No_Charges_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/maxTempRecordedTV']")
	 public static WebElement battery_Max_temp_recorded_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/chargeduartionTextview']")
	 public static WebElement battery_total_charge_duration_value;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Videos']")
	 public static WebElement videos; 
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Subscribe to this channel']")
	 public static WebElement youtube_Page;
	 
	 
	 @AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='YouTube']")
	 public static WebElement youtube_Page_;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Click here for tips to solve the problem ']")
	 public static WebElement alertText;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/alertTitle']")
	 public static WebElement alert;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/wifi_statelable']")
	 public static WebElement wifi_State_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/wifi_ConnectedLable']")
	 public static WebElement connectedTo_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/SupplicantLable']")
	 public static WebElement status_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bssilable']")
	 public static WebElement bssID_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/ssidlable']")
	 public static WebElement ssID_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/ipaddresslable']")
	 public static WebElement ip_address_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/macaddresslable']")
	 public static WebElement mac_address_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/linkspeedlable']")
	 public static WebElement link_speed_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/device_model_textview']")
	 public static WebElement model_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/device_model_textValue']")
	 public static WebElement model_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/device_imei_textvalue']")
	 public static WebElement imei_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/os_vesion_textvalue']")
	 public static WebElement android_version_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/os_build_textvalue']")
	 public static WebElement build_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/phone_number_textvalue']")
	 public static WebElement phone_number_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/network_info_textvalue']")
	 public static WebElement network_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/network_type_textvalue']")
	 public static WebElement network_type_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/signal_strength_textvalue']")
	 public static WebElement signal_strength_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/roaming_textvalue']")
	 public static WebElement on_roaming_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/data_textvalue']")
	 public static WebElement data_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/wifi_state_view']")
	 public static WebElement wifi_state_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/wifi_ConnectedView']")
	 public static WebElement connected_to_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/SupplicantView']")
	 public static WebElement status_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/rssiview']")
	 public static WebElement rssi_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bssiview']")
	 public static WebElement bssid_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/ssidview']")
	 public static WebElement ssid_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/ipaddressview']")
	 public static WebElement ip_address_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/macaddressview']")
	 public static WebElement mac_address_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/linkspeedview']")
	 public static WebElement link_speed_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryLevelView']")
	 public static WebElement battery_level_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryhealthView']")
	 public static WebElement battery_health_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheStatusView']")
	 public static WebElement battery_status_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryscaleView']")
	 public static WebElement battery_scale_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheElectricityView']")
	 public static WebElement battery_electricity_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheTemperatureView']")
	 public static WebElement battery_temperature_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheVoltageView']")
	 public static WebElement battery_voltage_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.LinearLayout[0]/android.widget.LinearLayout[0]/android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bluetooth_name']")
	 public static WebElement bluetooth_name_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.LinearLayout[0]/android.widget.LinearLayout[1]/android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bluetooth_name']")
	 public static WebElement bluetooth_address_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/message']")
	 public static WebElement self_diagnosis_alert_msg;
	 
//	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim Scout']")
//	 public static WebElement sonim_scout_AppLauncher;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@text='SEARCH BLUETOOTH']")
	 public static WebElement searchForBluetooth;
	 
	 
}
